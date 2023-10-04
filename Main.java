package org.example;
import org.example.interfaces.PayementStrategy;
import org.example.strategies.*;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        Product apple = new Product("apple", 10, 5);
        Product orange = new Product("orange", 50, 3);
        Product pear = new Product("pear", 13, 4);

        UserWallet wallet = new UserWallet();
        System.out.print("Input your card money amount: ");
        wallet.setCardMoney(Integer.parseInt(scanner.nextLine()));
        System.out.print("Input your cash amount: ");
        wallet.setCash(Integer.parseInt(scanner.nextLine()));
        System.out.println("#######################################");

        ShoppingCart cart = ShoppingCart.getInstance();
        PayementStrategy payementStrategy;

        System.out.println("Hello! Today in our shop we have: " + apple.getQuantity() + " " + apple.getName() + "s, " + orange.getQuantity() + " " + orange.getName() + "s, " + pear.getQuantity() + " " + pear.getName() + "s");
        System.out.println("1 " + apple.getName() + " costs " + apple.getPrice() + ", 1 " + orange.getName() + " costs " + orange.getPrice() + ", 1 " + pear.getName() + " costs " + pear.getPrice());

        String userChoice = "";

        while(!userChoice.equals("exit")) {
            System.out.println("=================================================");
            System.out.print("What you would like to buy? Or you can enter exit, if you want to quit: ");
            userChoice = scanner.nextLine();

            switch (userChoice) {
                case "apple" -> {
                    cart.addProductToCart(apple);
                    System.out.println("Apples added to your cart");
                }
                case "orange" -> {
                    cart.addProductToCart(orange);
                    System.out.println("Oranges added to your cart");
                }
                case "pear" -> {
                    cart.addProductToCart(pear);
                    System.out.println("Pears added to your cart");
                }
                case "exit" -> {
                    System.out.println("Bye!");
                    break;
                }
                default -> {
                    System.out.println("There is no such option, try again");
                    continue;
                }
            }
            System.out.println("-------------------------------------------------");
            System.out.println("Now would you like to pay, or you like to continue adding products to your cart? pay/continue (or exit to quit)");
            do {
                userChoice = scanner.nextLine();
                switch (userChoice) {
                    case "pay":
                        do {
                            System.out.println("Would you like to pay with card or cash? (or exit)");
                            userChoice = scanner.nextLine();
                            switch (userChoice) {
                                case "card":
                                    payementStrategy = new CardPaymentStrategy();
                                    System.out.println(cart.checkingOut(payementStrategy, wallet));
                                    break;
                                case "cash":
                                    payementStrategy = new CashPaymentStrategy();
                                    System.out.println(cart.checkingOut(payementStrategy, wallet));
                                    break;
                                case "exit":
                                    break;
                                default:
                                    System.out.println("There is no such option, try again");
                            }
                        } while (!userChoice.matches("exit|card|cash"));
                        break;
                    case "continue":
                        System.out.println("Okay!");
                        break;
                    case "exit":
                        break;
                    default:
                        System.out.println("There is no such option, try again");
                }
            } while (!userChoice.matches("exit|card|cash"));
        }
    }
}