<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Input Item</title>
</head>
<body>
    <h1>Input Item</h1>
        <form action="ItemServlet.do?action=input_item" method="post">
            Item Code: <input type="text" name="itemCode"><br>
            Description: <input type="text" name="description"><br>
            Type: <input type="text" name="type"><br>
            Price: <input type="text" name="price"><br>
            Taxable: <input type="checkbox" name="taxable" value="true"><br>
            	<input type="hidden" name="taxable" value="false"><br>
            <input type="submit" value="Save">
        </form>
  
    
</body>
</html>