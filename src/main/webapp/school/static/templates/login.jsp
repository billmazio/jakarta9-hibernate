<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href=${pageContext.request.contextPath}"/school/static/css/login.css">

    <script>
        function toggleRegistration() {
            var form = document.getElementById('registrationForm');
            form.style.display = form.style.display === 'none' ? 'block' : 'none';
        }
    </script>
</head>
<body>

<div class="container-fluid">
    <div class="container">
        <div class="row">
            <h1 class="text-grey">Login</h1>
        </div>
        <form method="POST" action="${pageContext.request.contextPath}/login">
            <div class="row">
                <input style="width: 100%; box-sizing: border-box; padding: 10px; border: 1px solid #ccc;" type="username" name="username" required placeholder="username">
                <span></span>
            </div>
            <div class="row">
                <input style="width: 100%; box-sizing: border-box; padding: 10px; border: 1px solid #ccc;" type="password" name="password" required placeholder="Password">
                <span></span>
            </div>



            <div class="row">
                <button type="submit">Sign In</button>
            </div>
        </form>
        <div class="row text-grey">
            <a href="#">Lost your password?</a>
        </div>
    </div>
    <div class="row" style="text-align: center;">
        <p>Don't have an account? <a href="#" onclick="toggleRegistration(); return false;" class="signup-link">Sign up here!</a></p>
    </div>

    <!-- Registration Form -->
    <div id="registrationForm" style="display: none; text-align: center;">
        <form method="POST" action="${pageContext.request.contextPath}/register">
            <div class="row">
                <input style="width: 50%; box-sizing: border-box; padding: 10px; border: 1px solid #ccc;" type="text" name="username" required placeholder="username">
                <span></span>
            </div>
            <div class="row">
                <input style="width: 50%; box-sizing: border-box; padding: 10px; border: 1px solid #ccc;" type="password" name="password" required placeholder="Password">
                <span></span>
            </div>

            <div class="row">
                <button type="submit">Register</button>
            </div>
        </form>


    </div>

</div>
<c:if test="${not empty successMessage}">
    <p style="color: green;">${successMessage}</p>
</c:if>

<%-- Display the error message --%>
<c:if test="${not empty errorMessage}">
    <p style="color: red;">${errorMessage}</p>
</c:if>


<div class="container">
    <c:if test="${requestScope.isError eq 'true'}">
        <p style="color: red">Login Error</p>
    </c:if>
</div>
</body>
</html>
