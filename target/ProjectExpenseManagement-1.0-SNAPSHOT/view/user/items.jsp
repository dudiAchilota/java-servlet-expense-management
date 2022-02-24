<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="il.ac.hit.expensemanagement.model.*" %>
<%@ page import="java.util.List" %>
<%@ page import="il.ac.hit.expensemanagement.controller.Utils" %>
<html>
<head>
    <link rel="stylesheet" href="https://code.jquery.com/mobile/1.4.3/jquery.mobile-1.4.3.min.css"/>
    <script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="https://code.jquery.com/mobile/1.4.3/jquery.mobile-1.4.3.min.js"></script>


    <title>items</title>
</head>

<body>
<div data-role="page" id="home">
    <% Utils.ifHaveUser(request, response); %>
    <div data-role="header">
        <h1>table</h1>
        <h2><%= "Hello " + request.getSession().getAttribute("user")  %>
        </h2>
    </div>
    <div>
        <table border='5' style="font-family: Arial;align-items: center">
            <colgroup>
                <col style="background-color: deepskyblue"/>
                <col style="background-color: lightsalmon"/>
            </colgroup>
            <tr style="align-items: center">
                <th>sum</th>
                <th>category</th>
                <th>date</th>
                <th>description</th>
            </tr>
            <%
                if (request.getAttribute("data") != null) {
                    List<ExpenseItem> items = (List<ExpenseItem>) request.getAttribute("data");
                    for (ExpenseItem item : items) {
            %>
            <tr>
                <td><%=item.getSum()%>
                </td>
                <td><%=item.getCategory()%>
                </td>
                <td><%=item.getDate()%>
                </td>
                <td><%=item.getDescription()%>
                </td>
            </tr>
            <%
                    }
                }
            %>
        </table>
    </div>

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
