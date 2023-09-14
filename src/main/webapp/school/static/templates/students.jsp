<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/school/static/css/students.css">
  <title>Students Found</title>
</head>
<body>
<div>
  <table>
    <tr>
      <th>ID</th>
      <th>First Name</th>
      <th>Last Name</th>
      <th>Gender</th>
      <th>Birthdate</th>
      <th>City</th>
      <th>Delete</th>
      <th>Update</th>

    </tr>
    <c:forEach var="student" items="${requestScope.students}">
      <tr>
        <td>${student.id}</td>
        <td>${student.firstname}</td>
        <td>${student.lastname}</td>
        <td>${student.gender}</td>
        <td>${student.birthdate}</td>
        <td>${student.city}</td>
        <td>
          <a href="${pageContext.request.contextPath}/schoolapp/deleteStudent?id=${student.id}&firstname=${student.firstname}&lastname=${student.lastname}&gender=${student.gender}&birthdate=${student.birthdate}&cityId=${student.city.id}"
             onclick="return confirm('Are you sure you want to delete this student?')">Delete</a>
        </td>
        <td>
          <a href="${pageContext.request.contextPath}/school/static/templates/studentUpdate.jsp?id=${student.id}&firstname=${student.firstname}&lastname=${student.lastname}&gender=${student.gender}&birthdate=${student.birthdate}&cityId=${student.city.id}">Update</a>
        </td>
      </tr>
    </c:forEach>
  </table>
</div>

<div>
  <c:if test="${requestScope.deleteAPIError}">
    <p>${requestScope.message}</p>
  </c:if>
</div>

<div>
  <c:if test="${requestScope.updateAPIError}">
    <p>Something went wrong in Update</p>
  </c:if>
</div>

</body>
</html>
