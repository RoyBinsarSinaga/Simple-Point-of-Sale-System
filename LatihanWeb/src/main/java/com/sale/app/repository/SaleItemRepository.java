package com.sale.app.repository;

import com.sale.app.domain.Sale;
import com.sale.app.domain.SaleItem;
import com.sale.app.exception.RepositoryException;

public interface SaleItemRepository {
    void save(SaleItem saleItem) throws RepositoryException;
    
    void saveSale(Sale sale) throws RepositoryException;
}

