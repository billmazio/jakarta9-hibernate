<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/school/static/css/teacherDeleted.css">
<title>Insert title here</title>
</head>
<body>
<div>
	<p>Teacher: ${requestScope.teacherDTO.id} ${requestScope.teacherDTO.firstname} ${requestScope.teacherDTO.lastname} ${requestScope.teacherDTO.specialty}
		was deleted</p>
	<a href="${pageContext.request.contextPath}/schoolapp/teacherInsert">Back</a>
</div>
</body>
</html>
