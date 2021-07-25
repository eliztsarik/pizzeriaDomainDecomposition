package Exceptions;

public class NotFoundPizzaException extends RuntimeException {
    public NotFoundPizzaException(String message) {
        super(message);
    }
}
