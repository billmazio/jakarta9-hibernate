<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Specialty Deleted Successfully</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/school/static/css/specialtyDeleted.css">

</head>
<body>
<p>Specialty: ${requestScope.specialtyDTO.id} ${requestScope.specialtyDTO.name} was deleted</p>
<a href="${pageContext.request.contextPath}/schoolapp/specialtyInsert">Go back</a>
</body>
</html>
