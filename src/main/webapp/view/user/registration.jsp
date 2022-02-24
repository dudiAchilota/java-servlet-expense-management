<%@ page import="il.ac.hit.expensemanagement.controller.Utils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>registration</title>
    <link rel="stylesheet" href="https://code.jquery.com/mobile/1.4.3/jquery.mobile-1.4.3.min.css"/>
    <script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="https://code.jquery.com/mobile/1.4.3/jquery.mobile-1.4.3.min.js"></script>
</head>
<body>
<div>

    <h2>registration</h2>

    <div>
        <form method="post" action="${pageContext.request.contextPath}/router/user/registration">
            <div style="padding:10px 20px;align-items: center">
                <h3>registration</h3>
                <label for="un" class="ui-hidden-accessible">Username:</label>
                <input type="text" name="username" id="un" placeholder="username" data-theme="a">
                <br/>
                <label for="pw" class="ui-hidden-accessible">Password:</label>
                <input type="text" name="password" id="pw" placeholder="password" data-theme="a">
                <br/>
            </div>
            <input type="submit" value="registration">
        </form>
    </div>

    <%
        String mes = (String) request.getAttribute("mes");
        if (mes != null) {
    %>
    <h1 style="align-items: center;background-color: lightgoldenrodyellow"><%= mes %>
    </h1>
    <% } %>
    <div data-role="footer">
        <a href="${pageContext.request.contextPath}/router/user/items"
           data-role="button" data-icon="grid" data-theme="b">table Items</a>
        <a href="${pageContext.request.contextPath}/router/user/add"
           data-role="button" data-icon="grid" data-theme="b">add</a>
        <a href="${pageContext.request.contextPath}/router/user/login"
           data-role="button" data-icon="" data-theme="b">login</a>
    </div>
</div>

</body>
</html>
