<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/school/static/css/studentInserted.css">
    <title>Student Inserted Successfully</title>
</head>
<body>
<h1>Student inserted successfully</h1>
<div>

    <p>First Name: ${requestScope.insertedStudent.firstname}</p>
    <p>Last Name: ${requestScope.insertedStudent.lastname}</p>
    <p>Gender: ${requestScope.insertedStudent.gender}</p>
    <p>Birthdate: ${requestScope.insertedStudent.birthdate}</p>
    <p>City: ${requestScope.insertedStudent.city}</p>

</div>

<div>
    <a href="${pageContext.request.contextPath}/schoolapp/studentInsert">Go Back</a>
</div>
</body>
</html>
