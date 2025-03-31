package Utilities;

public class Utility {

    public  void genericWaitTime(int milliseconds){
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
