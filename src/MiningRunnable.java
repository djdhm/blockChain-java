
import java.rmi.Naming;
import java.rmi.Remote;
import java.util.concurrent.CountDownLatch;


class MiningRunnable implements Runnable{
    private String addresse;
    private String data;
    private MiningCountdown callback;
    public MiningRunnable(String addresse, String data,MiningCountdown callback){
        this.addresse=addresse;
        this.data=data;
        this.callback=callback;
    }
    @Override
    public void run() {
        try{
            Remote test = Naming.lookup(addresse);
            System.out.println(Thread.currentThread().getName()+": Commencement du mining dans le bloque "+addresse);
            Bloque nouveau= ((RemoteInterface) test).miner(data);
            callback.countDown(nouveau);

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}