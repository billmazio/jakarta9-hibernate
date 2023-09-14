<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/school/static/css/meetings.css">
  <title>Meetings Found</title>
</head>
<body>
<div>
  <table>
    <tr>
      <th>ID</th>
      <th>Teacher's ID</th>
      <th>Students ID</th>
      <th>Room</th>
      <th>Meeting Date</th>
      <th>Delete</th>
      <th>Update</th>
    </tr>
    <c:forEach var="meeting" items="${requestScope.meetings}">
      <tr>
        <td>${meeting.id}</td>
        <td>${meeting.teacher}</td>
        <td>${meeting.student}</td>
        <td>${meeting.room}</td>
        <td>${meeting.meetingDate}</td>
        <td>
          <a href="${pageContext.request.contextPath}/schoolapp/deleteMeeting?id=${meeting.id}&teacherId=${meeting.teacher.id}&studentId=${meeting.student.id}&room=${meeting.room}&meetingDate=${meeting.meetingDate}"
             onclick="return confirm('Are you sure you want to delete this meeting?')">Delete</a>
        </td>
        <td>
          <a href="${pageContext.request.contextPath}/schoolapp/updateMeeting?id=${meeting.id}&teacherId=${meeting.teacher.id}&studentId=${meeting.student.id}&room=${meeting.room}&meetingDate=${meeting.meetingDate}">Update</a>
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
