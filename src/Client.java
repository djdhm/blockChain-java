import sun.nio.ch.ThreadPool;

import java.rmi.Naming;
import java.rmi.Remote;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class Client {
    final static int MAX_T=2;
    public static MiningCountdown countdown = new MiningCountdown(1);
    public static ArrayList<RemoteInterface> noeuds=new ArrayList<>();
    public static void main(String[] argv) {
        try {




            Remote r = Naming.lookup("rmi://localhost:1099/Noeud1");
            Remote r2 = Naming.lookup("rmi://localhost:1099/Noeud2");
            Remote r3 = Naming.lookup("rmi://localhost:1099/Noeud3");

            RemoteInterface stub1 = (RemoteInterface) r;
            RemoteInterface stub2=(RemoteInterface) r2;
            RemoteInterface stub3=(RemoteInterface) r3;
            noeuds.add(stub1);
            noeuds.add(stub2);
            noeuds.add(stub3);
            String data="exemple data";
            for(int i=1;i<4;i++){
                System.out.println(i);
                Thread thread = new Thread(new MiningRunnable(i, data, countdown));
                thread.start();

            }
            countdown.await();
            System.out.println("un noud a terminé ");
            for (RemoteInterface noeud:noeuds){
                System.out.println("ARRET IMMEDIAT");
                System.out.println(countdown.getNouveauBloqueMine());
                noeud.arreterMining(countdown.getNouveauBloqueMine());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
