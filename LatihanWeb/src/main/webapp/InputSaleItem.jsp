<%@ page import="java.util.List" %>
<%@ page import="com.sale.app.domain.Item" %>
<%@ page import="com.sale.app.domain.SaleItem" %>
<%@ page import="com.sale.app.usecase.ProsesUseCaseSale" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    HttpSession sessions = request.getSession(true);
    ProsesUseCaseSale useCaseSale = new ProsesUseCaseSale();
    List<Item> itemList = useCaseSale.findAll();
    String itemCode = (String) request.getAttribute("itemCode");
%>
<html>
<head>
    <!-- ... -->
</head>
<body>
    <h2>Input Sale Item</h2>
    <form action="ItemServlet.do" method="post">
    <input type="hidden" name="action" value="input_sale_item">
    <input type="hidden" name="itemCode" value="<%= itemCode %>">
    
    Item Code: <%= itemCode %><br>
    
    <label for="quantity">Quantity:</label>
    <input type="number" name="quantity" required>

    <input type="submit" value="Save Sale Item">
    <input type="submit" name="saveAndAddAnother" value="Save and Add Another Item">
</form>

    <!-- ... -->
</body>
</html>
