package RMI;
import Structure.Bloque;
import java.util.concurrent.CountDownLatch;


public class MiningCountdown extends CountDownLatch {
    public Bloque getNouveauBloqueMine() {
        return nouveauBloqueMine;
    }

    private Bloque nouveauBloqueMine;
    public MiningCountdown(int i) {
        super(i);
    }

    public void countDown(Bloque bloque) {
        this.nouveauBloqueMine=bloque;
        super.countDown();
    }
}
