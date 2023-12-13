package come.wide.task2.test;

import java.util.List;

import com.wide.task.repository.SaleRepository;
import com.wide.task.repository.impl.SaleRepositoryDummy;
import com.wide.task2.domain.CashPayment;
import com.wide.task2.domain.Payment;
import com.wide.task2.domain.QrisPayment;
import com.wide.task2.domain.Sale;
import com.wide.task2.domain.SaleItem;

public class SaleDataTest {

    public static void main(String[] args) {
        SaleRepository saleRepo = new SaleRepositoryDummy();
        Sale sale = saleRepo.findByNumber(1);
        System.out.println("SALE INFORMATION"); 
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
                System.out.println("Choose valid payment method.");
            }
        } else {
            System.out.println("Payment has not been made.");
        }

        sale.finish(saleRepo);
    }

    public static void processPayment(Sale sale, Payment payment) {
        payment.processPayment(sale);
    }
}
