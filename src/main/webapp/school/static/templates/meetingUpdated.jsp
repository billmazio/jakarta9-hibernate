<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/school/static/css/meetingUpdated.css">
    <title>Meeting Updated Successfully</title>
</head>
<body>
<h1>Meeting updated successfully</h1>
<div>
    <p>Meeting ID: ${requestScope.updatedMeeting.id}</p>
    <p>Teacher's ID: ${requestScope.updatedMeeting.teacher}</p>
    <p>Students ID: ${requestScope.updatedMeeting.student}</p>
    <p>Room: ${requestScope.updatedMeeting.room}</p>
    <p>Meeting Date: ${requestScope.updatedMeeting.meetingDate}</p>
</div>

<div>
    <a href="${pageContext.request.contextPath}/schoolapp/meetingInsert">Go Back</a>
</div>
</body>
</html>
