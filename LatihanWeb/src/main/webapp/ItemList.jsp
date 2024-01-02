<%@ page import="java.util.List" %>
<%@ page import="com.sale.app.domain.Item" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Item List View</title>
</head>
<body>
    <h1>Item List View</h1>
   
    <a href="InputItem.jsp">
        <button>Add Item</button>
    </a>
    
    <br> </br>
    
    <table border="2">
        <tr>
            <th>Item Code</th>
            <th>Description</th>
            <th>Type</th>
            <th>Price</th>
            <th>Taxable</th>
            <th>Action</th>
        </tr>
        
        <c:forEach items="${data_item}" var="i">
            <tr>
                <td><a href="<c:out value="${i.itemCode}" > </c:out>"><c:out value="${i.itemCode}" > </c:out></a></td>
                <td><c:out value="${i.description}" > </c:out></td>
                <td><c:out value="${i.type}" > </c:out></td>
                <td><c:out value="${i.price}" > </c:out></td>
                <td><c:out value="${i.taxable}" > </c:out></td>
                <td>
                    <form action="ItemServlet.do" method="post">
                        <input type="hidden" name="action" value="add_sale_item">
                        <input type="hidden" name="itemCode" value="${i.itemCode}">
                        <button type="submit">Add Sale Item</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
