<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/school/static/css/meetingDeleted.css">
    <title>Meeting Deleted Successfully</title>
</head>
<body>
<h1>Meeting deleted successfully</h1>

<p>Meeting: ${requestScope.meetingDTO.id} ${requestScope.meetingDTO.teacher} ${requestScope.meetingDTO.student} ${requestScope.meetingDTO.room} ${requestScope.meetingDTO.meetingDate}
    was deleted</p>
<a href="${pageContext.request.contextPath}/schoolapp/meetingInsert">Go Back</a>
</div>
</body>
</html>
