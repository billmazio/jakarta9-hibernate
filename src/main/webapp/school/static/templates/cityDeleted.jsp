<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">

    <title>City Deleted Successfully</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/school/static/css/cityDeleted.css">

</head>
<body>
<p>City: ${requestScope.cityDTO.id} ${requestScope.cityDTO.name}
    was deleted</p>
<a href="${pageContext.request.contextPath}/schoolapp/cityInsert">Go back</a>
</body>
</html>
