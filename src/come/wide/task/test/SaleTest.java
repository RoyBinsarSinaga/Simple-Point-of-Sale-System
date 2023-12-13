package come.wide.task.test;

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
import java.util.List;

import com.wide.task.domain.CashPayment;
import com.wide.task.domain.Item;
import com.wide.task.domain.Payment;
import com.wide.task.domain.QrisPayment;
import com.wide.task.domain.Sale;
import com.wide.task.domain.SaleItem;

public class SaleTest {
    public static void main(String[] args) {
        String username = "root";
        String password = "";
        String port = "3306";
        String jdbcUrl = "jdbc:mysql://localhost:3306/db1";

        Sale sale = new Sale(new Date());
        sale.setSaleNumber(1);
        sale.setCashier("Jaki");

        Item item1 = new Item("122", "Pallet", "Art", 50000, true);
        SaleItem saleItem1 = new SaleItem(2, item1);

        Item item2 = new Item();
        item2.setItemCode("202");
        item2.setDescription("Soap");
        item2.setType("Hygiene");
        item2.setPrice(10000);
        item2.setTaxable(false);
        SaleItem saleItem2 = new SaleItem();
        saleItem2.setQuantity(3);
        saleItem2.setItem(item2);

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

            // Load Driver
            Class.forName("com.mysql.jdbc.Driver");

            // Get DB connection
            Connection conn = DriverManager.getConnection(jdbcUrl, username, password);

            String sqlQuery = "SELECT * FROM item";

            Statement stm = conn.createStatement();

            ResultSet rs = stm.executeQuery(sqlQuery);

            // Read result
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
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        displaySaleInformation(sale);

        int paymentMethodChoice = 1;

        boolean isPaid = true;
        if (isPaid) {
            if (paymentMethodChoice == 1) {
                double cashAmount = 900000;
                Payment cashPayment = new CashPayment(cashAmount);
                processPayment(sale, cashPayment);
            } else if (paymentMethodChoice == 2) {
                Payment qrisPayment = new QrisPayment();
                processPayment(sale, qrisPayment);
            } else {
                System.out.println("Choose valid payment method");
            }
        } else {
            System.out.println("Payment has not been made");
        }
    }

    public static void processPayment(Sale sale, Payment payment) {
        payment.processPayment(sale);
    }

    public static void displaySaleInformation(Sale sale) {
        System.out.println("Sale Information:");
        System.out.println("Sale Number: " + sale.getSaleNumber());
        System.out.println("Cashier: " + sale.getCashier());
        System.out.println("Date: " + sale.getDate());

        List<SaleItem> saleItemsList = sale.getSaleItem();
        for (int i = 0; i < saleItemsList.size(); i++) {
            SaleItem saleItem = saleItemsList.get(i);
            System.out.println("SaleItem " + (i + 1) + ":");
            System.out.println("  Quantity: " + saleItem.getQuantity());
            System.out.println("  Item Code: " + saleItem.getItem().getItemCode());
            System.out.println("  Item Name: " + saleItem.getItem().getDescription());
            System.out.println("  Item Type: " + saleItem.getItem().getType());
            System.out.println("  Item Price: " + saleItem.getItem().getPrice());
            System.out.println("  Tax: " + saleItem.calculateTax());
            System.out.println("  Total Price: " + saleItem.calculateTotalPrice());
            System.out.println();
        }

        System.out.println("Total Sale Price: " + sale.calculateTotalPrices());
        System.out.println("Tax Total: " + sale.calculateTotalTax());
        System.out.println("Total Sale Price + Tax: " + sale.calculateTotalPricenTax());
    }
}
