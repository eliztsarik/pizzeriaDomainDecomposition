package domains;

import java.util.Set;

public class OrderItem {
    private long pizzaID;
    private Pizza pizza;
    private long count;
    private double sum;


    public Pizza getPizza() {
        return pizza;
    }

    public long getCount() {
        return count;
    }

    public double getSum() {
        return sum;
    }

    private void setSum() {
        this.sum = pizza.getSum() * count;
    }


    public OrderItem(long count, String name, Set<Ingridients> ingridients, boolean isCalzone, long pizzaID) {
        this.pizzaID = pizzaID;
        this.count = count;
        this.pizza = new Pizza(name, ingridients, isCalzone);
        setSum();
    }

//    public OrderItem(long count, String name, Set<Ingridients> ingridients, long pizzaID) {
//        this.pizzaID = pizzaID;
//        this.count = count;
//        this.pizza = new Pizza(name, ingridients, false);
//    }
//
//    public OrderItem(long count, String name, long pizzaID) {
//        this.pizzaID = pizzaID;
//        this.count = count;
//        this.pizza = new Pizza(name, false);
//    }
//
//    public OrderItem(long count, String name, boolean isCalzone, long pizzaID) {
//        this.pizzaID = pizzaID;
//        this.count = count;
//        this.pizza = new Pizza(name, isCalzone);
//    }

}
