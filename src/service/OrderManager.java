package service;

import domains.Client;
import domains.Order;

import static helpers.Helpers.generateFiveDigitNumber;

public class OrderManager {

    public static void start() {
        System.out.println("Welcome to pizzeria 'Palmetto'!\n");
    }

    public static void notifyPizzaAdded(String name, long orderId) {
        System.out.printf("-----\nPizza %s was added to order %d\n-----\n", name, orderId);
    }

    public static Order startOrder() {
        var client = new Client(generateFiveDigitNumber());
        System.out.println("-----\nGenerate new order:");
        return new Order(client);

    }
}
