package domains;

import Exceptions.CustomExceptions;
import service.OrderManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static helpers.Helpers.generateFiveDigitNumber;
import static helpers.Helpers.printError;

public class Order {
    private static final int MIN_PIZZA_NAME_LENGTH = 4;
    private static final int MAX_PIZZA_NAME_LENGTH = 20;

    private final long clientId;
    private final long orderId;
    private ArrayList<OrderItem> orderItems;
    private double sum = 0.0;

    public Order(Client client) {
        this.orderId = generateFiveDigitNumber();
        this.clientId = client.getId();
        this.orderItems = new ArrayList<>(); //do we need to init it here, or exactly in the private variable?
    }

    private void showPizza(OrderItem orderItem) {
        System.out.printf("%d:%d:%s:%d\n", orderId, clientId, orderItem.getPizza().getName(), orderItem.getCount());
    }

    public void showPizzas() {
        orderItems.forEach(this::showPizza);
    }

    public void showPizzaInfoByName(String name) {
        try {
            showPizza(getPizzaByName(name));
        } catch (CustomExceptions.NotFoundPizzaException ex) {
            printError(ex.getMessage());
        }
    }

    //TODO: not working as I expected
    private OrderItem getPizzaByName(String name) throws CustomExceptions.NotFoundPizzaException {
        return orderItems.stream()
                .filter(item -> item.getPizza().getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new CustomExceptions.NotFoundPizzaException("Pizza with that name is not found"));
    }


    private String geDefaultName() {
        return clientId + "_" + (orderItems.size() + 1);
    }

    private String getValidName(String name) {
        try {
            if (name.length() > MAX_PIZZA_NAME_LENGTH || name.length() < MIN_PIZZA_NAME_LENGTH) {
                throw new CustomExceptions.NotProperPizzaName("Name length is more than 20 or less than 4 symbols. We add pizza with the default name.");
            } else {
                return name;
            }
        } catch (CustomExceptions.NotProperPizzaName ex) {
            printError(ex.getMessage());
            return geDefaultName();
        }
    }

    private void setSum() {
        this.sum = orderItems.stream().mapToDouble(OrderItem::getSum).sum();
    }


    public void addPizza(Long count, String name, Set<Ingridients> ingridients, Boolean isCalzone) {
        try {
            var validCount = checkCount(count);
            var validName = (name == null) ? geDefaultName() : getValidName(name);
            var validIngridients = (ingridients == null) ? new HashSet<Ingridients>() : ingridients;
            var validCalzone = isCalzone != null && isCalzone;
            this.orderItems.add(
                    new OrderItem(
                            validCount,
                            validName,
                            validIngridients,
                            validCalzone,
                            this.orderId
                    ));
            setSum();
            OrderManager.notifyPizzaAdded(validName, orderId);
        } catch (CustomExceptions.ValueOutOfBounds ex) {
            printError(ex.getMessage());
        }
    }

    private long checkCount(Long count) throws CustomExceptions.ValueOutOfBounds {
        if (count == null || count > 10 || count < 0) {
            throw new CustomExceptions.ValueOutOfBounds("Value is more than 10 or less than 0");
        } else {
            return count;
        }
    }

    //TODO: search for the prettier solution, can move to service class
    public void printCheck() {
        String euro = "\u20ac";
        System.out.printf("\nЧЕК\n*****\n");
        System.out.printf("Заказ: %d\n", this.orderId);
        System.out.printf("Клиент: %d\n", this.clientId);
        orderItems.forEach((item) -> {
            System.out.printf("Название: %s\n", item.getPizza().getName());
            System.out.printf("-----\n");
            System.out.printf("\tPizza Base: %.2f\n", item.getPizza().getFullDoughSum());
            item.getPizza().getIngridients().forEach(ingridient -> {
                System.out.printf("\t%s:   %.2f %s\n", ingridient.name(), ingridient.getPrice(), euro);
            });
            System.out.printf("-----\n");
            System.out.printf("Стоимость пиццы: %.2f\n", item.getPizza().getSum());
            System.out.printf("Кол-во: %d\n", item.getCount());
            System.out.printf("Всего: %.2f\n", item.getSum());
            System.out.printf("------\n");
        });
        System.out.printf("Общая сумма: %.2f\n", this.sum);
        System.out.printf("*****\n");
    }


//    public void addPizza(long count) {
//        try {
//            var validCount = checkCount(count);
//            this.orderItems.add(new OrderItem(validCount, geDefaultName(), orderId));
//        }
//        catch (CustomExceptions.ValueOutOfBounds ex) {
//            printError(ex.getMessage());
//        }
//    }
//
//    public void addPizza(long count, boolean isCalzone) {
//        try {
//            var validCount = checkCount(count);
//            this.orderItems.add(new OrderItem(validCount, geDefaultName(), isCalzone, orderId));
//        } catch (CustomExceptions.ValueOutOfBounds ex) {
//            printError(ex.getMessage());
//        }
//    }
//
//    public void addPizza(long count, String name) {
//        try {
//            var validCount = checkCount(count);
//            this.orderItems.add(new OrderItem(validCount, getValidName(name), orderId));
//        } catch (CustomExceptions.ValueOutOfBounds ex) {
//            printError(ex.getMessage());
//        }
//    }
//
//    public void addPizza(long count, Set<Ingridients> ingridients) {
//        try {
//            var validCount = checkCount(count);
//            this.orderItems.add(new OrderItem(validCount, geDefaultName(), ingridients, orderId));
//        } catch (CustomExceptions.ValueOutOfBounds ex) {
//            printError(ex.getMessage());
//        }
//    }
//
//    public void addPizza(long count, String name, boolean isCalzone) {
//        var validCount = checkCount(count);
//        this.orderItems.add(new OrderItem(count, getValidName(name), isCalzone, orderId));
//    }
//
//    public void addPizza(long count, Set<Ingridients> ingridients, boolean isCalzone) {
//        var validCount = checkCount(count);
//        this.orderItems.add(new OrderItem(checkCount(count), geDefaultName(), ingridients, isCalzone, orderId));
//    }
//
//    public void addPizza(long count, String name, Set<Ingridients> ingridients) {
//        try {
//            this.orderItems.add(new OrderItem(checkCount(count), getValidName(name), ingridients, orderId));
//            setSum();
//        } catch (CustomExceptions.ValueOutOfBounds ex) {
//            printError(ex.getMessage());
//        }
//    }
}
