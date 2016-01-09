package dk.renner.pixlr.utils;

/**
 *
 * @author NiklasRenner
 */
public abstract class Utility {
    
    public static void sleep(int ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            // TODO handle?
        }
    }
    
}
