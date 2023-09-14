<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/school/static/css/specialtyInserted.css">
  <title>Specialty Inserted Successfully</title>
</head>
<body>
<h1>Specialty inserted successfully</h1>
<div>

  <p> Name: ${requestScope.insertedSpecialty.name}</p>

</div>

<div>
  <a href="${pageContext.request.contextPath}/schoolapp/specialtyInsert">Go Back</a>
</div>
</body>
</html>
