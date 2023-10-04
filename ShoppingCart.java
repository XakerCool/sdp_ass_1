package org.example;

import org.example.interfaces.PayementStrategy;
import org.example.strategies.CardPaymentStrategy;
import org.example.strategies.CashPaymentStrategy;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private static ShoppingCart instance;
    private static int totalPrice;
    private static List<Product> productList;

    private ShoppingCart() {
        productList = new ArrayList<Product>();
        totalPrice = 0;
    }
    public static ShoppingCart getInstance() {
        if (instance == null) {
            instance = new ShoppingCart();
        }
        return instance;
    }
    public void addProductToCart(Product product) {
        productList.add(product);
    }
    public List<Product> getProductList() {
        return productList;
    }
    public void setPrice() {
        for (Product product: productList) {
            totalPrice += product.getPrice() * product.getQuantity();
        }
    }
    public int getTotalPrice() {
        return totalPrice;
    }
    public String checkingOut(PayementStrategy paymentStrategy, UserWallet wallet) {
        System.out.println(paymentStrategy.getPaymentType());
        switch (paymentStrategy.getPaymentType()) {
            case "card":
                paymentStrategy = new CardPaymentStrategy();
                if (paymentStrategy.processPayment(totalPrice, wallet.getCardMoney()))
                    return "You have no enough money on your card mister";
                else
                    wallet.setCardMoney(wallet.getCardMoney() - totalPrice);
            case "cash":
                paymentStrategy = new CashPaymentStrategy();
                if (paymentStrategy.processPayment(totalPrice, wallet.getCash()))
                    return "You have no enough cash mister";
                else
                    wallet.setCash(wallet.getCash() - totalPrice);
                break;
            default:
                return "There is no such type of payment type";
        }
        productList.clear();
        return "Congratulations! You bought our product, bye!";
    }
}
