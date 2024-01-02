package com.sale.app.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sale.app.domain.Item;
import com.sale.app.domain.Sale;
import com.sale.app.domain.SaleItem;
import com.sale.app.exception.UseCaseSaleException;
import com.sale.app.usecase.ProsesUseCaseSale;

@WebServlet("/ItemServlet.do")
public class ItemServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final ProsesUseCaseSale useCaseSale = new ProsesUseCaseSale();

    public ItemServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Item> itemList = null;
        try {
            itemList = useCaseSale.findAll();
        } catch (UseCaseSaleException e) {
            e.printStackTrace();
        }

        request.setAttribute("data_item", itemList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/ItemList.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            HttpSession session = request.getSession();

            if ("input_item".equalsIgnoreCase(action)) {
                String itemCode = request.getParameter("itemCode");
                Integer price = Integer.parseInt(request.getParameter("price"));
                String description = request.getParameter("description");
                String type = request.getParameter("type");
                Boolean isTax = Boolean.valueOf(request.getParameter("taxable"));

                Item newItem = new Item(itemCode, price, description, type, isTax);
                useCaseSale.saveItem(newItem);
                request.setAttribute("input_item", newItem);
            } else if ("input_sale_item".equalsIgnoreCase(action)) {
                String itemCode = request.getParameter("itemCode");
                Integer quantity = Integer.parseInt(request.getParameter("quantity"));
                String saleNumber = useCaseSale.generateUniqueSaleNumber(session);

                Item item = useCaseSale.findItemByCode(itemCode);

                if (item != null) {
                    SaleItem saleItem = new SaleItem(saleNumber, item, quantity);
                    useCaseSale.addTempSaleItem(saleItem, session); 

                    request.setAttribute("itemCode", itemCode);

                    RequestDispatcher dispatcher = request.getRequestDispatcher("/ViewSaleItem.jsp");

                    dispatcher.forward(request, response);
                    return;
                } else {
                    response.sendRedirect("ErrorPage.jsp");
                    return;
                }
            } else if ("save_sale".equalsIgnoreCase(action)) {
            
                String saleNumber = useCaseSale.generateUniqueSaleNumber(session);

                List<SaleItem> saleItems = useCaseSale.findAllSaleItems();
                double totalSalePrice = useCaseSale.calculateTotalSalePrice(saleItems);

                saleItems.addAll(useCaseSale.getTempSaleItems(request.getSession()));

                Sale sale = new Sale(saleNumber, saleItems);

                // Save Sale and clear SaleItems from session
                useCaseSale.saveSale(sale, session);
                useCaseSale.clearSaleItems();
                useCaseSale.clearTempSaleItems(session);

                request.setAttribute("sale", sale);
                request.setAttribute("data_sale_items", saleItems);
                request.setAttribute("totalSalePrice", totalSalePrice);

                RequestDispatcher dispatcher = request.getRequestDispatcher("/ViewSaleItem.jsp");
                dispatcher.forward(request, response);
                return;
            } else if ("add_sale_item".equalsIgnoreCase(action)) {
                String itemCode = request.getParameter("itemCode");
                request.setAttribute("itemCode", itemCode);

                List<Item> itemList = useCaseSale.findAll();
                request.setAttribute("data_item", itemList);

                RequestDispatcher dispatcher = request.getRequestDispatcher("/InputSaleItem.jsp");
                dispatcher.forward(request, response);
                return;
            }

            List<Item> itemList = useCaseSale.findAll();
            request.setAttribute("data_item", itemList);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/ItemList.jsp");
            dispatcher.forward(request, response);
        } catch (NumberFormatException | UseCaseSaleException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error");
        }
    }

 
}
