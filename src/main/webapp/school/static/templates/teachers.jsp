<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">

  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/school/static/css/teachers.css">
  <title>Teachers Found</title>

</head>
<body>
  <div>
    <table>
      <tr>
        <th>ID</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Specialty</th>
        <th>Delete</th>
        <th>Update</th>
      </tr>
      <c:forEach var = "teacher" items = "${requestScope.teachers}">
        <tr>
          <td>${teacher.id}</td>
          <td>${teacher.firstname}</td>
          <td>${teacher.lastname}</td>
          <td>${teacher.specialty}</td>
          <td><a href="${pageContext.request.contextPath}/schoolapp/deleteTeacher?id=${teacher.id}&firstname=${teacher.firstname}&lastname=${teacher.lastname}&specialtyId=${teacher.specialty.id}"
                 onclick="return confirm('Are you sure you want to delete this teacher?')">Delete</a></td>

          <td><a href="${pageContext.request.contextPath}/school/static/templates/teacherUpdate.jsp?id=${teacher.id}&firstname=${teacher.firstname}&lastname=${teacher.lastname}&specialtyId=${teacher.specialty.id}">Update</a></td>
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
