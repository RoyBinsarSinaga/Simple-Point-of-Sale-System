package com.wide.task.repository.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import com.wide.task.repository.SaleRepository;
import com.wide.task2.domain.Item;
import com.wide.task2.domain.Sale;
import com.wide.task2.domain.SaleItem;

public class SaleRepositoryDummy implements SaleRepository {

    @Override
    public Sale findByNumber(int saleNumber) {
        String username = "root";
        String password = "";
        String port = "3306";
        String jdbcUrl = "jdbc:mysql://localhost:3306/db1";

        Sale sale = new Sale(saleNumber, "Jaki", new Date());

        Item item1 = new Item("122", "Pallet", "Art", 50000, true);
        SaleItem saleItem1 = new SaleItem(2, item1);

        Item item2 = new Item("202", "Soap", "Hygiene", 10000, false);
        SaleItem saleItem2 = new SaleItem(3, item2);

        Item item3 = new Item("500", "Candle", "Interior", 2500, true);
        SaleItem saleItem3 = new SaleItem(1, item3);

        sale.addSaleItem(saleItem1);
        sale.addSaleItem(saleItem2);
        sale.addSaleItem(saleItem3);

        try {
            FileReader fileReader = new FileReader("customer.txt");
            BufferedReader bufReader = new BufferedReader(fileReader);
            String line = null;
            while ((line = bufReader.readLine()) != null) {
                String[] tokens = line.split(";");
                Item item = new Item();
                item.setItemCode(tokens[0].trim());
                item.setDescription(tokens[1].trim());
                item.setType(tokens[2].trim());

                int number = Integer.parseInt(tokens[3].trim());
                item.setPrice(number);

                String strValue = tokens[4].trim();
                boolean boolValue = strValue.equals("1");
                item.setTaxable(boolValue);

                SaleItem saleItem = new SaleItem();
                saleItem.setQuantity(3);
                saleItem.setItem(item);
                sale.addSaleItem(saleItem);
            }

            Class.forName("com.mysql.jdbc.Driver");

            Connection conn = DriverManager.getConnection(jdbcUrl, username, password);

            String sqlQuery = "SELECT * FROM item";

            Statement stm = conn.createStatement();

            ResultSet rs = stm.executeQuery(sqlQuery);

            while (rs.next()) {
                Item item = new Item();
                item.setItemCode(rs.getString("itemCode"));
                item.setDescription(rs.getString("description"));
                item.setType(rs.getString("type"));
                item.setPrice(rs.getInt("price"));
                String strValue = rs.getString("isTaxable");
                boolean boolValue = strValue.equals("1");
                item.setTaxable(boolValue);

                SaleItem saleItem = new SaleItem();
                saleItem.setQuantity(2);
                saleItem.setItem(item);
                sale.addSaleItem(saleItem);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File tidak ketemu");
        } catch (IOException e) {
            System.out.println("File Corrupted");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return sale;
    }

    @Override
    public void save(Sale sale) {
        // TODO Auto-generated method stub
    }
}
