
import java.rmi.Naming;
import java.rmi.Remote;
import java.util.concurrent.CountDownLatch;


class MiningRunnable implements Runnable{
    private int i;
    private String data;
    private MiningCountdown callback;
    public MiningRunnable(int i, String data,MiningCountdown callback){
        this.i=i;
        this.data=data;
        this.callback=callback;
    }
    @Override
    public void run() {
        try{
            Remote test = Naming.lookup("rmi://localhost:1099/Noeud"+i);
            System.out.println(Thread.currentThread().getName()+": Commencement du mining dans le bloque "+i);
            Bloque nouveau= ((RemoteInterface) test).miner(data);
            callback.countDown(nouveau);

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}