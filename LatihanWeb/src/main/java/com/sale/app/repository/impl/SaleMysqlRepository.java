package com.sale.app.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.sale.app.connection.DataSource;
import com.sale.app.domain.Sale;
import com.sale.app.domain.SaleItem;
import com.sale.app.exception.RepositoryException;
import com.sale.app.repository.SaleItemRepository;

public class SaleMysqlRepository implements SaleItemRepository {

    @Override
    public void save(SaleItem saleItem) throws RepositoryException {
        try (Connection connection = DataSource.getDataSource()) {
            connection.setAutoCommit(false);

            String SQL_SALE_ITEM_SAVE = "INSERT INTO saleitem (item_code, sale_number, quantity, total_price) VALUES (?, ?, ?, ?)";

            PreparedStatement ps = connection.prepareStatement(SQL_SALE_ITEM_SAVE);

            ps.setString(1, saleItem.getItem().getItemCode());
            ps.setString(2, saleItem.getSaleNumber());
            ps.setInt(3, saleItem.getQuantity());
            ps.setDouble(4, saleItem.getTotalPrice());

            int result = ps.executeUpdate();

            if (result > 0) {
                System.out.println("BERHASIL SIMPAN DATA SALE ITEM");
            }

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RepositoryException("Error saving sale item: " + e.getMessage());
        } catch (Exception e) {
            throw new RepositoryException(e.getMessage());
        }
    }
    
 // Tambahkan metode untuk menyimpan Sale ke tabel sale
    public void saveSale(Sale sale) throws RepositoryException {
        try (Connection connection = DataSource.getDataSource()) {
            connection.setAutoCommit(false);

            String SQL_SALE_SAVE = "INSERT INTO sale (sale_number, total_sale_price) VALUES (?, ?)";

            PreparedStatement ps = connection.prepareStatement(SQL_SALE_SAVE);

            ps.setString(1, sale.getSaleNumber());
            ps.setDouble(2, sale.getTotalSalePrice());

            int result = ps.executeUpdate();

            if (result > 0) {
                System.out.println("BERHASIL SIMPAN DATA SALE");
            }

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RepositoryException("Error saving sale: " + e.getMessage());
        } catch (Exception e) {
            throw new RepositoryException(e.getMessage());
        }
    }
}

