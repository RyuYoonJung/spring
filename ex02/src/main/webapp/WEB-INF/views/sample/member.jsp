<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8">
<title>data오전 1:08:03</title>
</head>
<body>
	<h1>sample/member</h1>
	<form action="/logout" method="post">
	<sec:csrfInput/>
	<button>logout</button>
	</form>
</body>
</html>