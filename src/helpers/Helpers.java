package helpers;

import java.util.concurrent.ThreadLocalRandom;

public class Helpers {

    public static long generateFiveDigitNumber() {
        return ThreadLocalRandom.current().nextInt(10000, 99999 + 1);
    }

    public static void printError(String errorMessage) {
        System.out.printf("-----\nERROR: %s\n-----\n", errorMessage);
    }
}
