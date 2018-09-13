import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;

public class Client {

    public static void main(String[] argv) {
        try {


            //    Registry registry = LocateRegistry.getRegistry("localhost",1099);


            Remote r = Naming.lookup("rmi://localhost:1099/Noeud1");
            Remote r2 = Naming.lookup("rmi://localhost:1099/Noeud2");

            RemoteInterface stub1 = (RemoteInterface) r;
            RemoteInterface stub2=(RemoteInterface) r2;
            String data="test data";
            for(int i=1;i<3;i++){
                System.out.println(i);
                new Thread(new MiningRunnable(i,data)).start();

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
