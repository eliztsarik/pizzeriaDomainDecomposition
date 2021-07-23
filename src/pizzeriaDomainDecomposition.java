import domains.Ingridients;
import service.OrderManager;

import java.util.HashSet;

public class pizzeriaDomainDecomposition {

    public static void main(String[] args) {

        var order = OrderManager.startOrder();
        order.addPizza(10L, null, null, null);
        order.addPizza(12L, "MARGARITA", null, null);
        order.addPizza(9L, "Pepperoni", null, true);

        order.showPizzas();
        order.showPizzaInfoByName("MARGARITA");
        order.showPizzaInfoByName("Pepperoni");

        order.printCheck();

        var order2 = OrderManager.startOrder();
        order.addPizza(-4L, null, null, null);

        var ingr = new HashSet<Ingridients>();
        ingr.add(Ingridients.CHEESE);
        ingr.add(Ingridients.OLIVES);
        ingr.add(Ingridients.PEPPER);


        order2.addPizza(2L, "Liza's pizza", ingr, true);

        ingr.add(Ingridients.CHEESE);
        ingr.add(Ingridients.OLIVES);
        ingr.add(Ingridients.PEPPER);
        ingr.add(Ingridients.BACON);
        ingr.add(Ingridients.TOMATO_PASTE);
        ingr.add(Ingridients.CORN);
        ingr.add(Ingridients.GARLIC);
        ingr.add(Ingridients.SALAMI);

        order2.addPizza(6L, "Maximum pizza name. Oh my god, how awful it is!", ingr, false);

        order2.printCheck();

        order2.showPizzas();

        order2.showPizzaInfoByName("Liza's pizza");


    }
}
