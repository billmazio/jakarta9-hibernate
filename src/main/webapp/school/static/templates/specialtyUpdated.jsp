<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
<head>
    <title>Specialty Updated</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/school/static/css/specialtyUpdated.css">
</head>
<body>
<div>
    <h1>Specialty updated successfully</h1>

    <p>ID: ${requestScope.updatedSpecialty.id}</p>
    <p>Specialty Name: ${requestScope.updatedSpecialty.name}</p>

</div>
<div>
    <a href="${pageContext.request.contextPath}/schoolapp/specialtyInsert">Go Back</a>
</div>

</body>
</html>
