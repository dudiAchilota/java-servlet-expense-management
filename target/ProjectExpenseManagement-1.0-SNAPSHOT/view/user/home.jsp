<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <link rel="stylesheet" href="https://code.jquery.com/mobile/1.4.3/jquery.mobile-1.4.3.min.css"/>
    <script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="https://code.jquery.com/mobile/1.4.3/jquery.mobile-1.4.3.min.js"></script>

    <title>home</title>

</head>
<body>

<div data-role="page">

    <h1>home</h1>
    <%
        String user = (String) request.getSession().getAttribute("user");
        if (user != null) {
    %>
    <h2><%="Hello " + user   %>
    </h2>
    <%
    } else {
    %>
    <h2><%="Hello "   %>
    </h2>
    <%
        }
    %>

    <div data-role="footer">
        <a href="${pageContext.request.contextPath}/router/user/items"
           data-role="button" data-icon="grid" data-theme="b">table Items</a>
        <a href="${pageContext.request.contextPath}/router/user/add"
           data-role="button" data-icon="grid" data-theme="b">add</a>
        <a href="${pageContext.request.contextPath}/router/user/logout"
           data-role="button" data-icon="grid" data-theme="b">log-out</a>
    </div>
</div>


</body>
</html>
