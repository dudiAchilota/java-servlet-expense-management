<%@ page import="il.ac.hit.expensemanagement.controller.Utils" %>
<%@ page language="java" contentType="text/html; charset=windows-1255"
         pageEncoding="windows-1255" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://code.jquery.com/mobile/1.4.3/jquery.mobile-1.4.3.min.css"/>
    <script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="https://code.jquery.com/mobile/1.4.3/jquery.mobile-1.4.3.min.js"></script>
    <meta charset="windows-1255">

    <title>add</title>

</head>
<body>
<div data-role="page" id="home">
    <% Utils.ifHaveUser(request, response);%>
    <div data-role="header">
        <h1>Add cost item</h1>
        <h2><%= "Hello " + request.getSession().getAttribute("user")%>
        </h2>
    </div>

    <form method="post" action="${pageContext.request.contextPath}/router/user/add">
        <div data-role="content" data-theme="a">

            <div data-role="fieldcontain">
                <label>sum:</label>
                <input type="number" name="sum" id="sum" placeholder="Sum input" step="0.01"/>
            </div>

            <div data-role="fieldcontain">
                <label for="category" class="select">category:</label>
                <select name="category" id="category" data-native-menu="false">
                    <option value="">select category</option>
                    <option value="Food">Food</option>
                    <option value="Education">Education</option>
                    <option value="Travel">Travel</option>
                    <option value="House">House</option>
                </select>
            </div>

            <div data-role="fieldcontain">
                <label for="description">description:</label>
                <input id="description" type="text" name="description" placeholder="Text input"
                       style="font-family: David"/>
            </div>

            <div data-role="fieldcontain">
                <label for="date">Date:</label>
                <input id="date" type="date" name="date">
            </div>

            <script>
                function restart() {
                    document.getElementById("sum").value = "";
                    document.getElementById("category").value = "";
                    document.getElementById("description").value = "";
                    document.getElementById("date").value = "";
                }
            </script>


        </div>
        <div data-role="controlgroup" data-type="horizontal" data-mini="true" style="text-align: center">
            <input type="submit" value="add">
            <a onclick="restart()" href="#" data-role="button" data-icon="delete" data-theme="b">restart</a>
        </div>
    </form>

    <%
        String mes = (String) request.getAttribute("mes");
        if (mes != null) {
    %>
    <h1 style="align-items: center"><%= mes %>
    </h1>
    <% } %>

    <div data-role="footer">
        <a href="${pageContext.request.contextPath}/router/user/items"
           data-role="button" data-icon="grid" data-theme="b">table Items</a>
        <a href="${pageContext.request.contextPath}/router/user/add"
           data-role="button" data-icon="grid" data-theme="b">add</a>
    </div>
    <%
        String xx = (String) request.getAttribute("username");
        if (xx != null) {
    %>
    <h1 style="align-items: center"><%= xx %>
    </h1>
    <% } %>

    <%
        if (request.getSession().getAttribute("user") == null) {
            request.getRequestDispatcher("/view/user/login.jsp").forward(request, response);
        }
    %>
</div>


</body>
</html>