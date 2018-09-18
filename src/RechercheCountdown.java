import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class RechercheCountdown extends CountDownLatch {
    /**
     * Constructs a {@code CountDownLatch} initialized with the given count.
     *
     * @param count the number of times {@link #countDown} must be invoked
     *              before threads can pass through {@link #await}
     * @throws IllegalArgumentException if {@code count} is negative
     */
    private ArrayList<String> resultat;
    public RechercheCountdown(int count) {
        super(count);

    }


    public void countDown(ArrayList<String> res) {
        this.resultat=res;
        super.countDown();
    }

    public ArrayList<String> getResultat() {
        return resultat;
    }
}

