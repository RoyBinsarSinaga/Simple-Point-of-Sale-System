<%@ page import="java.util.List" %>
<%@ page import="com.sale.app.domain.SaleItem" %>
<%@ page import="com.sale.app.usecase.ProsesUseCaseSale" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    HttpSession sessions = request.getSession(true);
    ProsesUseCaseSale useCaseSale = new ProsesUseCaseSale();
    List<SaleItem> tempSaleItems = useCaseSale.getTempSaleItems(session);
    double totalSalePrice = useCaseSale.calculateTotalSalePrice(tempSaleItems);
%>
<html>
<head>
</head>
<body>
    <h2>View Sale Item</h2>

   
    <h3>Total Sale Price: <%= totalSalePrice %></h3>

    <table>
        <thead>
            <tr>
                <th>Item Code</th>
                <th>Quantity</th>
                <th>Total Price</th>
            </tr>
        </thead>
        <tbody>
           <% 
    String currentSaleNumber = null;
    for (SaleItem saleItem : tempSaleItems) { 
        if (currentSaleNumber == null) {
            currentSaleNumber = saleItem.getSaleNumber();
        } else if (!currentSaleNumber.equals(saleItem.getSaleNumber())) {
            continue;
        }
%>
    <tr>
        <td><%= saleItem.getItem().getItemCode() %></td>
        <td><%= saleItem.getQuantity() %></td>
        <td><%= saleItem.getTotalPrice() %></td>
    </tr>
<% } %>

        </tbody>
    </table>

    <form action="ItemServlet.do" method="post">
        <input type="hidden" name="action" value="save_sale">
        <input type="submit" value="Save Sale">
    </form>

    <form action="ItemServlet.do" method="post">
        <input type="hidden" name="action" value="add_sale_item">
        <input type="submit" value="Add Sale Item">
    </form>

</body>
</html>
