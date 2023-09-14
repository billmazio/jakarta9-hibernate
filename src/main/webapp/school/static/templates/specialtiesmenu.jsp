<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Specialties Search and Insert</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/school/static/css/specialtiesmenu.css">
</head>
<body>
<div class="center">
    <p>Hello ${sessionScope.loginName}</p>
</div>

<div class="center">
    <!-- Specialties Search -->
    <div class="search-wrapper">
        <div class="bot-gap">
            <span class="title">Specialties Search</span>
        </div>
        <div class="bot-gap">
            <form method="POST" action="${pageContext.request.contextPath}/schoolapp/searchSpecialty">
                <input name="name" type="text" class="search rounded" placeholder="Insert specialty name" autofocus/>
                <br><br>
                <button class="search-btn rounded color-btn" type="submit">Search</button>
            </form>
        </div>
    </div>

    <!-- Specialties Insert -->
    <div class="insert-wrapper">
        <div class="bot-gap">
            <span class="title">Specialties Insert</span>
        </div>
        <div class="bot-gap">
            <form method="POST" action="${pageContext.request.contextPath}/schoolapp/specialtyInsert">
                <input name="name" type="text" value="${requestScope.insertedSpecialty.name}" class="insert rounded" placeholder="Specialty name" autofocus /><br>
                <br>
                <button class="search-btn rounded color-btn" type="submit">Insert</button>
            </form>
        </div>
    </div>
</div>

<div class="center">
    <c:if test="${requestScope.sqlError}">
        <p>${requestScope.message}</p>
    </c:if>
</div>

<div class="center">
    <c:if test="${requestScope.specialtiesNotFound}">
        <p>No specialties found</p>
    </c:if>

    <p>${requestScope.error}</p>
</div>
<div class="center">
    <a href="${pageContext.request.contextPath}/school/static/templates/controlPanel.jsp" class="back-btn">
        <i class="fas fa-arrow-left"></i> Back to Control Panel
    </a>
</div>

</body>
</html>
