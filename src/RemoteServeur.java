import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteServeur extends Remote{

    public boolean enregistrerNoeud(String addresse) throws RemoteException;

}
