<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.1/js/bootstrap.min.js" integrity="sha512-fHY2UiQlipUq0dEabSM4s+phmn+bcxSYzXP4vAXItBvBHU7zAM/mkhCZjtBEIJexhOMzZbgFlPLuErlJF2b+0g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.1/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/school/static/css/index.css"> <!-- Replace with your CSS path -->
</head>
<body>
<div class="container-fluid">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">
                <img src="./school/static/img/aueb.jpg"> <!-- Replace with your logo path -->
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavDropdown">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="#">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">About</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/login">Login</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <div class="slider">
        <!-- Slider content here -->
    </div>
    <div class="main">
        <div class="title">
            <p>Welcome to Our Website</p>
        </div>
        <div class="subtitle">
            <p>This is a subtitle</p>
        </div>
        <div class="main-text">
            <p>This is a paragraph in the main content area of the page.</p>
        </div>
    </div>
    <div class="footer">
        <div class="copyright">
            <p>&copy; 2023 My Website</p>
        </div>
        <div class="social">
            <p class="follow-us">Follow us:</p>
            <!-- Social media icons here -->
        </div>
    </div>
</div>
</body>
</html>
