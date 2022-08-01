<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>2022. 5. 2.오전 10:13:35</title>
</head>
<body>
<form method="post" enctype="multipart/form-data">
	<input type="file" name="uploadFile" multiple>
	<button>전송</button>
${uploadFile}
</form>
</body>
</html>