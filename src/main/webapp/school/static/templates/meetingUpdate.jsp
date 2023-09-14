<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/school/static/css/meetingUpdate.css">
    <title>Update Meeting</title>
</head>
<body>
<h1 class="header">Updating Meeting: </h1>
<form action="${pageContext.request.contextPath}/schoolapp/updateMeeting" method="post">

    <label for="id">Meeting ID</label>
    <input id="id" type="text" name="id" value="${param.id}" readonly><br>

    <label for="teacherId">Teacher's ID:</label>
    <input type="text" id="teacherId" name="teacherId" value="${param.teacherId}" required><br>

    <label for="studentId">Students ID:</label>
    <input type="text" id="studentId" name="studentId" value="${param.studentId}" required><br>

    <label for="room">Room:</label>
    <input type="text" id="room" name="room" value="${param.room}" required><br>

    <label for="meetingDate">Meeting Date:</label>
    <input type="text" id="meetingDate" name="meetingDate" value="${param.meetingDate}" required><br>

    <input type="submit" value="Update">

    <c:if test="${requestScope.isError}">
        <p>${requestScope.message}</p>
    </c:if>
</form>
</body>
</html>
