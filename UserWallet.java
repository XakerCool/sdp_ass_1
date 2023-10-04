package org.example;

public class UserWallet {
    private int cardMoney;
    private int cash;
    public UserWallet() {
        cardMoney = 0;
        cash = 0;
    }
    public UserWallet(int cardMoney, int cash) {
        this.cardMoney = cardMoney;
        this.cash = cash;
    }
    public void setCardMoney(int cardMoney) {
        this.cardMoney = cardMoney;
    }
    public int getCardMoney() {
        return cardMoney;
    }
    public void setCash(int cash) {
        this.cash = cash;
    }
    public int getCash() {
        return cash;
    }
}
