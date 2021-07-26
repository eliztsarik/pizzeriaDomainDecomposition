package domains;

import java.util.HashSet;
import java.util.Set;

public class Pizza {


    private static final double DOUGH = 1.0;
    private static final double CALZONE = 0.5;

    private String name;

    public Set<Ingridients> getIngridients() {
        return ingridients;
    }

    private Set<Ingridients> ingridients = new HashSet<>();
    private boolean isCalzone = false;
    private double sum;

    public String getName() {
        return name;
    }

    public double getSum() {
        return sum;
    }

    public Pizza(String name, Set<Ingridients> ingridients, boolean isCalzone) {
        this.name = name;
        this.ingridients.addAll(ingridients);
        this.isCalzone = isCalzone;
        setSum();
    }


    private void setSum() {
        var sum = DOUGH;
        sum += isCalzone ? CALZONE : 0.0;
        sum += ingridients.stream().mapToDouble(Ingridients::getPrice).sum();
        this.sum = sum;
    }

    public void addIngridient(Ingridients ingridient) {
        this.ingridients.add(ingridient);
        setSum();
    }

    public double getFullDoughSum() {
        return (isCalzone) ? DOUGH + CALZONE : DOUGH;
    }


}
