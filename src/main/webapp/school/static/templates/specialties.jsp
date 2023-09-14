<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/school/static/css/specialties.css">
    <title>Specialties Found</title>
</head>
<body>
<div>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Delete</th>
            <th>Update</th>
        </tr>
        <c:forEach var="specialty" items="${requestScope.specialties}">
            <tr>
                <td>${specialty.id}</td>
                <td>${specialty.name}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/schoolapp/deleteSpecialty?id=${specialty.id}&name=${specialty.name}"
                       onclick="return confirm('Are you sure you want to delete this specialty?')">Delete</a>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/school/static/templates/specialtyUpdate.jsp?id=${specialty.id}&name=${specialty.name}">Update</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

<div>
    <c:if test="${requestScope.deleteSpecialtyAPIError}">
        <p>${requestScope.message}</p>
    </c:if>
</div>

<div>
    <c:if test="${requestScope.updateSpecialtyAPIError}">
        <p>Something went wrong in Specialty Update</p>
    </c:if>
</div>

</body>
</html>
