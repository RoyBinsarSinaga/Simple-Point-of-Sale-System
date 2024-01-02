<%@ page import="com.sale.app.domain.Employee"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Employee List View</h1>
<%
	List<Employee> empList = (List<Employee>) request.getAttribute("data_employee");
%>
<table border = 10>
	<tr>
		<th>Name</th>
		<th>Address</th>
	</tr>
<%
	for(Employee emp : empList){ %>
	<tr>
		<td><a href="employee.jsp?id=<%= emp.getName() %>"><%= emp.getName() %></a>
		<td><%= emp.getName() %></td>
		<td><%= emp.getAddress() %></td>
	</tr>
<% } %>
</table>
</body>
</html>