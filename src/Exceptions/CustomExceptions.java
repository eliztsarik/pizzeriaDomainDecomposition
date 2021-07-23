package Exceptions;

public class CustomExceptions {

    public static class ValueOutOfBounds extends RuntimeException {
        public ValueOutOfBounds(String message) {
            super(message);
        }
    }

    public static class NotProperPizzaName extends RuntimeException {
        public NotProperPizzaName(String errorMessage) {
            super(errorMessage);
        }
    }

    public static class NotFoundPizzaException extends RuntimeException {
        public NotFoundPizzaException(String message) {
            super(message);
        }
    }
}
