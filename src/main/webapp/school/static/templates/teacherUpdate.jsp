<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">


  <link rel="stylesheet" href="${pageContext.request.contextPath}/school/static/css/teacherUpdate.css">
  <title>Teacher Update</title>
</head>
<body>

  <form method="POST" action="${pageContext.request.contextPath}/schoolapp/updateTeacher">
    <label for="id">Κωδικός</label>
    <input id="id" type="text" name="id" value="${param.id}" readonly><br>
    <label for="firstname">Όνομα</label>
    <input id="firstname" type="text" name="firstname" value="${param.firstname}"><br>
    <label for="lastname">Επώνυμο</label>
    <input id="lastname" type="text" name="lastname" value="${param.lastname}"><br><br>
    <label for="specialtyId">SpecialtyId:</label>
    <input id="specialtyId" type="text" name="specialtyId" value="${param.specialtyId}"><br>

    <input type="submit" value="Update">
    <c:if test="${requestScope.isError}">

      <p>${requestScope.message}</p>
    </c:if>

  </form>


</body>
</html>
