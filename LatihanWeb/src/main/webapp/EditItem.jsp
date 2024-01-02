<%@ page import="com.sale.app.domain.Item"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Edit Item</title>
</head>
<body>
    <h1>Edit Item</h1>

    <form action="ItemServlet.do?action=update_item" method="post">
        <input type="hidden" id="editItemCode" name="itemCode" value="<%= request.getParameter("item_code") %>">
        Item Code: <input type="text" name="itemCode" value="<%= request.getParameter("item_code") %>" disabled="disabled"><br>
        Description: <input type="text" name="description" value="<%= request.getParameter("description") %>"><br>
        Type: <input type="text" name="type" value="<%= request.getParameter("type") %>"><br>
        Price: <input type="text" name="price" value="<%= request.getParameter("price") %>"><br>
        Taxable: <input type="checkbox" name="taxable" value="true" <% if ("true".equals(request.getParameter("is_taxable"))) { %>checked<% } %>><br>
        <input type="submit" value="Save">
    </form>

 
</body>
</html>
