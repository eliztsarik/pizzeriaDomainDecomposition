package domains;

public enum Ingridients {
    TOMATO_PASTE(1.0),
    CHEESE(1.0),
    SALAMI(1.5),
    BACON(1.2),
    GARLIC(0.3),
    CORN(0.7),
    PEPPER(0.6),
    OLIVES(0.5);

    private final double price;

    public double getPrice() {
        return price;
    }

    Ingridients(double price) {
        this.price = price;
    }
}
