<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/school/static/css/teacherInserted.css">
<title>Teacher inserted successfull</title>
</head>
<body>
	<h1>Teacher inserted successfully</h1>
	<div>

		<p>First Name: ${requestScope.insertedTeacher.lastname}</p>
		<p>Last Name:${requestScope.insertedTeacher.firstname}</p>
		<p>Specialty:${requestScope.insertedTeacher.specialty}</p>
		<p></p>
	</div>	
	 	
	<div>
		<a href="${pageContext.request.contextPath}/schoolapp/teacherInsert">Go Back</a>
	</div> 	
</body>
</html>