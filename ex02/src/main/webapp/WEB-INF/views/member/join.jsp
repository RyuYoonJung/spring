<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Register</title>

    <!-- Custom fonts for this template-->
    <link href="/resources/assets/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="/resources/assets/https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="/resources/assets/css/sb-admin-2.min.css" rel="stylesheet">

</head>

<body class="bg-gradient-primary">

    <div class="container">

        <div class="card o-hidden border-0 shadow-lg my-5">
            <div class="card-body p-0">
                <!-- Nested Row within Card Body -->
                <div class="row">
                    <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
                    <div class="col-lg-7">
                        <div class="p-5">
                            <div class="text-center">
                                <h1 class="h4 text-gray-900 mb-4">Create an Account!</h1>
                            </div>
                            <form class="user" method="post">
                                <div class="form-group">
                                    <input type="text" class="form-control form-control-user" placeholder="userid" name="userid">
                                </div>
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <input type="password" class="form-control form-control-user"
                                            id="exampleInputPassword" placeholder="Password" name="userpw">
                                    </div>
                                    <div class="col-sm-6">
                                        <input type="password" class="form-control form-control-user"
                                            id="exampleRepeatPassword" placeholder="Repeat Password" name="password2">
                                    </div>
                                </div>
                                <div class="form-group">
                                	<input type="text" class="form-control form-control-user" placeholder="name" name="username">
                                </div>
                                <div class="form-group input-group">
                                	<input type="text" class="form-control form-control-user" placeholder="주소검색" name="username" id="addrKeyword">
                                	<div class="input-group-append">
								    	<button class="btn btn-success" type="button" id="btnAddrSearch">주소검색</button>
							  		</div>
                                </div>
                                <div>
                                	<div class="form-group">
							  			<input type="text" class="form-control form-control-user" name="jibun" id="#jibun" readonly>
							  			<input type="text" class="form-control form-control-user" name="roadAddr" id="#roadAddr" readonly>
							  		</div>
                                </div>
                                </div>
                                <div class="form-group row addr-result">
                                	<div class="col-10">
	                                	<div>
	                                		<span>지번주소</span>
	                                		<span class="small">458-6</span>
	                                	</div>
	                                	<div>
	                                		<span>도로명주소</span>
	                                		<span class="small">부일로 425</span>
	                                	</div>
                                	</div>
                                	<div class="col-2">
                                		<button class="btn btn-success small" id="">선택</button>
                                	</div>
                                </div>
                                
                                <button href="login.html" class="btn btn-primary btn-user btn-block">
                                    Register Account
                                </button>
                                <hr>
                                <sec:csrfInput/>
                            </form>
                            <hr>
                            <div class="text-center">
                                <a class="small" href="login">Already have an account? Login!</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="/resources/assets/vendor/jquery/jquery.min.js"></script>
    <script src="/resources/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="/resources/assets/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="/resources/assets/js/sb-admin-2.min.js"></script>
	<script>
	$(function() {
		$("#btnAddrSearch").click(function() {
			$.getJSON("/member/juso", {keyword:$("#addrKeyword").val()}).done(function(result) {
				console.log(result);
				var str = "";
				for(var i in result){
/* 						<div class="col-10">
	                		<div>
		                		<span>지번주소</span>
		                		<span class="small">458-6</span>
	                		</div>
	                		<div>
		                		<span>도로명주소</span>
		                		<span class="small">부일로 425</span>
	                		</div>
	            		</div>
	            		<div class="col-2">
	            			<button class="btn btn-success small" id="">선택</button>
	            		</div> */
				}
				$(".addr-result").html(str);
			})
		})
	})
	</script>
</body>

</html>