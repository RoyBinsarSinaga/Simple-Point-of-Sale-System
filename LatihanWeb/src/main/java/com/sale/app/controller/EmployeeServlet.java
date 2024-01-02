package com.sale.app.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sale.app.domain.Employee;
import com.sale.app.domain.Item;
import com.sale.app.exception.UseCaseSaleException;
import com.sale.app.repository.impl.EmployeeRepository;
import com.sale.app.usecase.ProsesUseCaseSale;

/**
 * Servlet implementation class ItemServlet
 */
@WebServlet("/EmployeeServlet.do") 
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ProsesUseCaseSale useCaseSale = new ProsesUseCaseSale();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		request.setAttribute(getServletInfo(), response)
//		request.getRequestDispatcher(page.jsp).forward(request,response);
//		sendRedirect
		
		EmployeeRepository e = new EmployeeRepository(); 
		List<Employee> empList = e.findAll();
		
		request.setAttribute("data_employee", empList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/EmployeeList.jsp");
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String itemCode = request.getParameter("itemCode");
			Integer price = Integer.parseInt(request.getParameter("price"));
			String description = request.getParameter("description");
			String type = request.getParameter("type");
			Boolean isTax = Boolean.valueOf(request.getParameter("isTax"));
			
			Item item = new Item(itemCode, price, description, type, isTax);
			
			useCaseSale.saveItem(item);
			
			PrintWriter out = response.getWriter();
			out.write("<h1>Data Item Berhasil Disimpan</h1>");
			
			
		}catch(NullPointerException e) {
			e.printStackTrace();
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}catch(UseCaseSaleException e) {
			e.printStackTrace();
		}
	}

	private void printItem(List<Item> items, PrintWriter out) {
		Iterator<Item> i = items.iterator();
		out.write("<table border=10 width=70%>");
		out.write("<tr>" 
				+ "<th> Item Code </th>"
				+ "<th> Price </th>"
				+ "<th> Description </th>"
				+ "<th> Type </th>"
				+ "<th> Is Taxable </th>");
		while(i.hasNext()) {
			Item item = i.next();
			out.write("<tr>");
			out.write("<td> " + item.getItemCode() + " </td>");
			out.write("<td> " + item.getPrice() + " </td>");
			out.write("<td> " + item.getDescription() + " </td>");
			out.write("<td> " + item.getType() + " </td>");
			out.write("<td> " + item.isTaxable() + " </td>");
			out.write("</tr>");
		}
		out.write("</table>");
	}
	
}
