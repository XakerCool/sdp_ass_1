package org.example.strategies;

import org.example.interfaces.PayementStrategy;

public class CashPaymentStrategy implements PayementStrategy {
    @Override
    public boolean processPayment(int totalPrice, int amount) {
        return totalPrice > amount;
    }
    @Override
    public String getPaymentType() {
        return null;
    }
}
