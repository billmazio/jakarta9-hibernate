
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<html>
<head>
    <title>Meetings Menu</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/school/static/css/meetingsmenu.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

</head>
<body>
<div class="center">
    <p>Hello ${sessionScope.loginName}</p>
</div>

<div class="center">
    <!-- Meetings Search -->
    <div class="search-wrapper">
        <div class="bot-gap">
            <span class="title">Meetings Search</span>
        </div>
        <div class="bot-gap">
            <form method="POST" action="${pageContext.request.contextPath}/schoolapp/searchMeeting">
                <input name="room" type="text" class="search rounded" placeholder="Insert meeting's Room" autofocus/>
                <br><br>
                <button class="search-btn rounded color-btn" type="submit">Search</button>


            </form>
        </div>
    </div>

    <!-- Meeting Insert -->
    <div class="insert-wrapper">
        <div class="bot-gap">
            <span class="title">Meetings Insert</span>
        </div>
        <div class="bot-gap">
            <form method="POST" action="${pageContext.request.contextPath}/schoolapp/meetingInsert">
    <label for="teacherId">Choose a Teacher:</label>
    <div class="select-wrapper">
        <select name="teacherId" id="teacherId">
            <c:forEach var="teacher" items="${teachers}">
                <option value="${teacher.id}">${teacher.lastname}</option>
            </c:forEach>
        </select>
    </div>
    <br>
    <label for="studentId">Choose a Student:</label>
    <div class="select-wrapper">
        <select name="studentId" id="studentId">
            <c:forEach var="student" items="${students}">
                <option value="${student.id}">${student.lastname}</option>
            </c:forEach>
        </select>
    </div>
<br>
    <br>
                <input name="room" type="text" value="${requestScope.insertedMeeting.room}" class="insert rounded room-input" placeholder="Room: " autofocus/>
                <br>
                <input name="meetingDate" type="text" value="${requestScope.insertedMeeting.meetingDate}" class="insert rounded" placeholder="Meeting Date: " autofocus/>
                <br>

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
    <c:if test="${requestScope.meetingsNotFound}">
        <p>No meetings found !</p>
    </c:if>

    <p>${requestScope.error}</p>
</div>
<div class="center">
    <a href="${pageContext.request.contextPath}/school/static/templates/controlPanel.jsp" class="back-btn">
        <i class="fas fa-arrow-left"></i> Back to Control Panel
    </a>
</div>
<div id="meetingsTable"></div>

<script>
    $(document).ready(function() {
        function fetchMeetings() {
            var teacherId = $("#teacherSelect").val();
            var studentId = $("#studentSelect").val();

            $.ajax({
                url: "${pageContext.request.contextPath}/schoolapp/meetingInsert",
                method: "GET",
                data: {teacherId: teacherId, studentId: studentId},
                dataType: "json",
                success: function(data) {
                    var meetingsDiv = $("#meetingsTable");
                    meetingsDiv.empty();  // Clear previous content

                    // Populate with the new data
                    if (data && data.length > 0) {
                        var table = $("<table>");
                        table.append("<tr><th>Meeting ID</th><th>Room</th><th>Date</th></tr>");

                        $.each(data, function(index, meeting) {
                            table.append('<tr><td>' + meeting.id + '</td><td>' + meeting.room + '</td><td>' + meeting.date + '</td></tr>');
                        });

                        meetingsDiv.append(table);
                    } else {
                        meetingsDiv.append("<p>No meetings found for the selected teacher and student.</p>");
                    }
                },
                error: function(error) {
                    console.error("Failed to fetch meetings.", error);
                }
            });
        }

        // Bind the change event to fetch meetings when dropdowns are updated
        $("#teacherSelect, #studentSelect").change(fetchMeetings);
    });
</script>


</body>
</html>
