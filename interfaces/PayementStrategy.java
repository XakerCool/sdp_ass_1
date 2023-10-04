package org.example.interfaces;

public interface PayementStrategy {
    boolean processPayment(int totalPrice, int amount);
    String getPaymentType();
}
