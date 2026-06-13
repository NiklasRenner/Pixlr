package dk.renner.pixlr.utils;

public abstract class Utility {

    public static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            System.out.println("Interrupted.");
        }
    }
}
