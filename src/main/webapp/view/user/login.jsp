<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://code.jquery.com/mobile/1.4.3/jquery.mobile-1.4.3.min.css"/>
    <script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="https://code.jquery.com/mobile/1.4.3/jquery.mobile-1.4.3.min.js"></script>

    <title>login</title>

</head>
<body>

<div data-role="page" id="login">
    <%
        if (request.getSession().getAttribute("user") != null)
            request.getRequestDispatcher("/view/user/home.jsp").forward(request, response);
    %>
    <h1>login</h1>
    <%
        String user = (String) request.getSession().getAttribute("user");
        if (user != null) {
    %>
    <h2><%="Hello " + user   %>
    </h2>
    <%
    } else {
    %>
    <h2><%="Hello " %>
    </h2>
    <%
        }
    %>
    <div>
        <form method="post" action="${pageContext.request.contextPath}/router/user/login">
            <div style="padding:10px 20px;align-items: center">
                <h3>Please sign in</h3>
                <label for="un" class="ui-hidden-accessible">Username:</label>
                <input type="text" name="username" id="un" placeholder="username" data-theme="a">
                <br/>
                <label for="pw" class="ui-hidden-accessible">Password:</label>
                <input type="password" name="password" id="pw" placeholder="password" data-theme="a">
                <br/>
            </div>
            <input type="submit" value="Sign in">
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

        <a href="${pageContext.request.contextPath}/router/user/login"
           data-role="button" data-icon="" data-theme="b">login</a>
        <a href="${pageContext.request.contextPath}/router/user/registration"
           data-role="button" data-icon="" data-theme="b">registration</a>
    </div>
</div>


</body>
</html>
