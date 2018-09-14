import java.util.ArrayList;

public class ArretRunnable implements Runnable {

    ArrayList<Thread> liste;
    public ArretRunnable(ArrayList<Thread> liste){
        this.liste=liste;
    }
    @Override
    public void run() {
        for (Thread thread:
             this.liste) {
            System.out.println("arret de thread "+thread.getName());
            thread.stop();
        }

        System.out.println("Tache terminée");
    }
    public void supprimerThread(Thread thread){
        liste.remove(thread);
    }
}
