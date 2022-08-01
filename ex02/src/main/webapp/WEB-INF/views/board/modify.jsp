<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<jsp:include page="../common/head.jsp"></jsp:include>
</head>
<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">
                <jsp:include page="../common/nav.jsp"/>

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <h1 class="h3 text-gray-800 mb-4">Board Modify Page</h1>

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary float-left">DataTables Example</h6>
                            <a class="btn btn-primary btn-sm float-right" href="register${page.cri.params}">글작성</a>
                        </div>
                        <div class="card-body">
                        <form method="post">
						  <div class="form-group">
						    <label for="title">title</label>
						    <input type="text" class="form-control" placeholder="title" id="title" name="title" value="${board.title}">
						  </div>
						  <div class="form-group">
						    <label for="content">content</label>
						    <textarea class="form-control" placeholder="content" id="content" name="content">${board.content}</textarea>
						  </div>
						  <div class="form-group">
						    <label for="writer">writer</label>
						    <input type="text" class="form-control" placeholder="writer" id="writer" name="writer" required value="${board.writer}">
						  </div>
						  <div class="form-group uploadDiv">
						  	<label for="attach" class="btn btn-success btn-sm">첨부</label>
						  	<input type="file" class="form-control d-none" placeholder="attach" id="attach" name="attach" multiple>
						  </div>
						  	<input type="hidden" name="bno" value="${board.bno}">
						  	<input type="hidden" name="pageNum" value="${cri.pageNum}">
							<input type="hidden" name="amount" value="${cri.amount}">
							<input type="hidden" name="type" value="${cri.type}">
							<input type="hidden" name="keyword" value="${cri.keyword}">
							<sec:authorize access="isAuthenticated() && principal.username == #board.writer">
							<button type="submit" class="btn btn-warning btn-sm" formaction="modify" id="btnMod">수정</button>
							<button type="submit" class="btn btn-danger btn-sm" formaction="remove">삭제</button>
							<sec:csrfInput/>
							</sec:authorize>
							
							<a class="btn btn-secondary btn-sm" href="list${cri.params}">목록</a>
						</form>
					</div>	
                </div>
                
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->
            <jsp:include page="../common/footer.jsp"/>
			<script>
				$(function() {
					var bno = '${board.bno}';
					// 파일첨부
					var showFiles = function(post) {
						var ajaxObj = {
							url :"/board/attachs",
							data:{bno:bno},
							method:'get',
							dataType:'json'
						};
						if(post) {
							var formData = new FormData();
							for(var i in post.files) {
								if(!validateFiles(post.files[i].name), this.files[i].size)) {
									return false;
							}
							formData.append("files", post.files[i]);
						}
						console.log(post.files);
						ajaxObj.processData = false;
						ajaxObj.contendType = false;
						ajaxObj.data = formData;
						ajaxObj.method = 'post';
						ajaxObj.url= '/upload';
					}
					$.ajax(ajaxObj)
					.done(function(result) {
						console.log(result);
						$(".uploadDiv").html($clone.html());
						var str = "";
						var thumbStr = "";
						for(var i in result){
							console.log(result[i]);
							console.log($.param(result[i]));
							if(result[i].image){
								thumbStr += getThumbStr(result[i]);
							}
						}
						$(".upload-files").append(str);
						$(".thumbs").append(thumbStr);
					})
				})
					
					showFiles();

					lightbox.option({
					      'resizeDuration': 200,
					      'wrapAround': true,
					      'imageFadeDuration' : 100
			     	 })
					
				// 파일 삭제 이벤트 
				$(".upload-files, .thumbs").on("click", ".close", function(){
					var $dom = $(this).closest("[data-uuid]");
					var uuid = $dom.data("uuid");
					$("[data-uuid='" + uuid + "']").remove();
				});
					
				function getAttachStr(attach) {
					var str = "";
					str += '<li class="list-group-item" data-uuid="'+ attach.uuid +'" data-path="'+ attach.path +'" data-image="'+ attach.image +'" data-origin="'+ attach.origin +'" ><a href="download?'+ $.param(attach)+'">'
					+ attach.origin+'</a><button type="button" class="close"><span>&times;</span></button></li>'
					return str;
				}	
				
				function getAttachStr(attach) {
					var str = "";
					var o = {...attach};  // clone
					o.uuid = 's_' + o.uuid;
					Str += '<div class="col-6 col-sm-4 col-lg-3 col-xl-2" data-uuid="'+ attach.uuid +'" data-path="'+ attach.path +'" data-image="'+ attach.image +'" data-origin="'+ attach.origin +'">';
					Str += '	<figure class="d-inline-block" style="position: relative; overflow: ">';
					Str += '		<figcaption><button type="button" class="close" style="position: absolute; top: 15px; right: 7px"><span>&times;</span></button></figcaption>';
					Str += '		<a href="/display?'+ $.param(result[i]) +'" data-lightbox="img" data-title="'+ o.origin +'"><img alt="" src="/display?'+ $.param(result[i]) +'" class="mx-1 my-2 img-thumbnail"></a>';
					Str += '	</figure>';
					Str += '</div>';
					return str;
				}	
			})
			
			// 게시글 수정 이벤트
				$("#btnMod").click(function(){
					event.preventDefault();
					var str = "";
					var attrArr = ['uuid', 'origin', 'path', 'image'];
					$(".upload-files li").each(function(i){
						for(var j in attrArr){
							str += 
								$("<input>")
								.attr("type", "hidden")
								.attr("name", "attachs["+ i +"]." + attrArr[j])
								.attr("value", $(this).data(attrArr[j])).get(0).outerHTML + "\n";
						}
					});
					console.log(str);
					$(this).closest("form").append(str).submit();
				});
			</script>            
        </div>


        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->
	<script>
	$(function() {
		var result = '${result}';
		check(result);
		function check(result){
			if(!result || history.state) return;
				
			if(parseInt(result) > 0){
				alert(result + "번 게시글이 작성되었습니다");
			}
		}
		history.replaceState({}, null, null);
	})
	</script>
</body>

</html>