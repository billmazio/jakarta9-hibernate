<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>City Updated</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/school/static/css/cityUpdated.css">
</head>
<body>
<div>
<h1>City updated successfully</h1>

<p>ID: ${requestScope.updatedCity.id}</p>
<p>City Name: ${requestScope.updatedCity.name}</p>

</div>
<div>
    <a href="${pageContext.request.contextPath}/schoolapp/cityInsert">Go Back</a>
</div>

</body>
</html>
