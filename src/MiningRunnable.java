import javax.security.auth.callback.Callback;
import java.rmi.Naming;
import java.rmi.Remote;

 class MiningRunnable implements Runnable{
    private int i;
    private String data;
    private Client client;
    public MiningRunnable(int i, String data){
        this.i=i;
        this.data=data;
        this.client=client;
    }
    @Override
    public void run() {
        try{
            Remote test = Naming.lookup("rmi://localhost:1099/Noeud"+i);
            System.out.println("started mining in block "+i);
            ((RemoteInterface) test).miner(data);
            System.out.println("bloque "+i+" a temine le mining  ");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}