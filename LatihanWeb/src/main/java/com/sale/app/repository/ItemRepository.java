package com.sale.app.repository;

import java.util.List;
import java.util.Map;

import com.sale.app.domain.Item;
import com.sale.app.exception.RepositoryException;


public interface ItemRepository {

	public void save(Item item) throws RepositoryException;
	
	public Item findItemById(String itemCode) throws RepositoryException;
	
	public List<Item> findAll() throws RepositoryException;

	public void update(Item item) throws RepositoryException;
}
