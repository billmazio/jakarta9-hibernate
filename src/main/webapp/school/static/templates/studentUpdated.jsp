<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/school/static/css/studentUpdated.css">
  <title>Student Updated Successfully</title>
</head>
<body>
<div>
<h1>Student updated successfully</h1>

  <p>ID: ${requestScope.updatedStudent.id}</p>
  <p>First Name: ${requestScope.updatedStudent.firstname}</p>
  <p>Last Name: ${requestScope.updatedStudent.lastname}</p>
  <p>Gender: ${requestScope.updatedStudent.gender}</p>
  <p>Birthdate: ${requestScope.updatedStudent.birthdate}</p>
  <p>City: ${requestScope.updatedStudent.city}</p>

</div>

<div>
  <a href="${pageContext.request.contextPath}/schoolapp/studentInsert">Go Back</a>
</div>
</body>
</html>
