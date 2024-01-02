package com.sale.app.usecase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import com.sale.app.domain.Item;
import com.sale.app.domain.Sale;
import com.sale.app.domain.SaleItem;
import com.sale.app.exception.RepositoryException;
import com.sale.app.exception.UseCaseSaleException;
import com.sale.app.repository.ItemRepository;
import com.sale.app.repository.SaleItemRepository;
import com.sale.app.repository.impl.ItemDummyMysqlRepository;
import com.sale.app.repository.impl.SaleMysqlRepository;

public class ProsesUseCaseSale {
	private ItemRepository itemRepository;
	 private SaleItemRepository saleItemRepository;
	
	public ProsesUseCaseSale() {
		itemRepository = new ItemDummyMysqlRepository();
		saleItemRepository = new SaleMysqlRepository();
	}
	
	public Item findItemByCode(String itemCode) throws UseCaseSaleException {
		Item item = null;
		try {
			item = itemRepository.findItemById(itemCode);
		} catch (RepositoryException e) {
			throw new UseCaseSaleException(e.getMessage());
		}
		return item;
	}
	
	public List<Item> findAll() throws UseCaseSaleException{
		List<Item> items = null;
		try {
			items = itemRepository.findAll();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			throw new UseCaseSaleException(e.getMessage());
		}
		return items;
	}
	
	public void saveItem(Item item) throws UseCaseSaleException{
		
		try {
			itemRepository.save(item);
		}catch(RepositoryException e) {
			throw new UseCaseSaleException(e.getMessage());
		}
		
	}
	
	public void updateItem(Item item) throws UseCaseSaleException{
		
		try {
			itemRepository.update(item);
		}catch(RepositoryException e) {
			throw new UseCaseSaleException(e.getMessage());
		}
		
	}
	
	 public void saveSaleItem(SaleItem saleItem) throws UseCaseSaleException {
	        try {
	            saleItemRepository.save(saleItem);
	        } catch (RepositoryException e) {
	            throw new UseCaseSaleException(e.getMessage());
	        }
	 }
	 
	 private List<SaleItem> saleItems = new ArrayList<>();

	 public List<SaleItem> findAllSaleItems() {
	        return saleItems;
	 }

	 public void clearSaleItems() {
	        saleItems.clear();
	 }

	 public void saveSale(Sale sale,  HttpSession session) throws UseCaseSaleException {
	    	
		 try {
	            // Menggunakan salenumber yang benar untuk Sale
	            String saleNumber = sale.getSaleItems().get(0).getSaleNumber();
	            sale.setSaleNumber(saleNumber);

	            saleItemRepository.saveSale(sale);

	            for (SaleItem saleItem : sale.getSaleItems()) {
	                saveSaleItem(saleItem);
	            }
	            
	            clearTempSaleItems(session);
	      } catch (RepositoryException e) {
	            throw new UseCaseSaleException(e.getMessage());
	      }
	 }
	    
	 public void clearTempSaleItems(HttpSession session) {
	        session.removeAttribute("tempSaleItems");
	        session.removeAttribute("currentSaleNumber");
	 }

	    
	 public double calculateTotalSalePrice(List<SaleItem> saleItems) {
	        double totalSalePrice = 0.0;

	        for (SaleItem saleItem : saleItems) {
	            totalSalePrice += saleItem.getTotalPrice();
	        }

	        return totalSalePrice;
	 }
	    
	 public void addTempSaleItem(SaleItem saleItem, HttpSession session) {
	       List<SaleItem> tempSaleItems = getTempSaleItems(session);
	        
	        // Mendapatkan atau membuat salenumber baru
	        String saleNumber = (String) session.getAttribute("currentSaleNumber");
	        if (saleNumber == null) {
	            saleNumber = generateUniqueSaleNumber(session);
	            session.setAttribute("currentSaleNumber", saleNumber);
	        }
	        
	        saleItem.setSaleNumber(saleNumber);
	        tempSaleItems.add(saleItem);
	        setTempSaleItems(session, tempSaleItems);
	 }
	    
	 public String generateUniqueSaleNumber(HttpSession session) {
	        String currentSaleNumber = (String) session.getAttribute("currentSaleNumber");

	        if (currentSaleNumber != null) {
	            return currentSaleNumber;
	        } else {
	            return "SALE-" + System.currentTimeMillis();
	        }
	 }


	 public List<SaleItem> getTempSaleItems(HttpSession session) {
	        @SuppressWarnings("unchecked")
			List<SaleItem> tempSaleItems = (List<SaleItem>) session.getAttribute("tempSaleItems");
	        if (tempSaleItems == null) {
	            tempSaleItems = new ArrayList<>();
	        }
	        return tempSaleItems;
	 }

	 public void setTempSaleItems(HttpSession session, List<SaleItem> tempSaleItems) {
	        session.setAttribute("tempSaleItems", tempSaleItems);
	 }

	 public double getTotalSalePrice(HttpSession session) {
	        List<SaleItem> tempSaleItems = getTempSaleItems(session);
	        return calculateTotalSalePrice(tempSaleItems);
	 }

	
}
