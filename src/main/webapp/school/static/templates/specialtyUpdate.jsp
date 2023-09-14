<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
<head>
  <title>Specialty Update</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/school/static/css/specialtyUpdate.css">
</head>
<body>

<form method="POST" action="${pageContext.request.contextPath}/schoolapp/updateSpecialty">
  <label for="sid">Κωδικός</label>
  <input id="sid" type="text" name="id" value="${param.id}" readonly><br>
  <label for="name">Specialty Name</label>
  <input id="name" type="text" name="name" value="${param.name}"><br>

  <input type="submit" value="Update">
  <c:if test="${requestScope.isError}">
    <p>${requestScope.message}</p>
  </c:if>
</form>

</body>
</html>
