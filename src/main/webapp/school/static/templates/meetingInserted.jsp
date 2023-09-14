<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/school/static/css/meetingInserted.css">
  <title> Meeting Inserted Successfully</title>
</head>
<body>
<h1>Meeting inserted successfully</h1>
<div>
  <p>Teacher's ID: ${requestScope.insertedMeeting.teacher}</p>
  <p>Student's ID: ${requestScope.insertedMeeting.student}</p>
  <p>Room: ${requestScope.insertedMeeting.room}</p>
  <p>Meeting Date: ${requestScope.insertedMeeting.meetingDate}</p>
</div>

<div>
  <a href="${pageContext.request.contextPath}/schoolapp/meetingInsert">Go Back</a>
</div>
</body>
</html>

