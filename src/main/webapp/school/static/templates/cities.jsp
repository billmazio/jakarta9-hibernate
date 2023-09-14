<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/school/static/css/cities.css">
    <title>Cities Found</title>
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
        <c:forEach var="city" items="${requestScope.cities}">
            <tr>
                <td>${city.id}</td>
                <td>${city.name}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/schoolapp/deleteCity?id=${city.id}&name=${city.name}"
                       onclick="return confirm('Are you sure you want to delete this city?')">Delete</a>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/school/static/templates/cityUpdate.jsp?id=${city.id}&name=${city.name}">Update</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

<div>
    <c:if test="${requestScope.deleteCityAPIError}">
        <p>${requestScope.message}</p>
    </c:if>
</div>

<div>
    <c:if test="${requestScope.updateCityAPIError}">
        <p>Something went wrong in City Update</p>
    </c:if>
</div>

</body>
</html>
