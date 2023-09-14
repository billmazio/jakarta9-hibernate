<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/school/static/css/teacherUpdated.css">
  <title>Insert title here</title>
</head>
<body>
<div>
<h1>Teacher updated successfully</h1>
<p>ID: ${requestScope.updatedTeacher.id}</p>
<p>First Name: ${requestScope.updatedTeacher.firstname}</p>
<p>Last Name: ${requestScope.updatedTeacher.lastname}</p>
  <p>Specialty: ${requestScope.updatedTeacher.specialty}</p>

</div>
<div>
<a href="${pageContext.request.contextPath}/schoolapp/teacherInsert">Back</a>
</div>
</body>
</html>
