<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/school/static/css/studentDeleted.css">
    <title>Insert title here</title>
</head>
<body>
<p>Student: ${requestScope.studentDTO.id} ${requestScope.studentDTO.firstname} ${requestScope.studentDTO.lastname}
            ${requestScope.studentDTO.gender} ${requestScope.studentDTO.birthdate} ${requestScope.studentDTO.city}

    was deleted</p>
<a href="${pageContext.request.contextPath}/schoolapp/studentInsert">Επιστροφή</a>
</body>
</html>z
