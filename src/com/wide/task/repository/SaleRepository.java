package com.wide.task.repository;

import com.wide.task.domain.Sale;

public interface SaleRepository {
	public Sale findByNumber(int saleNumber);
	public void save(Sale sale);
}

