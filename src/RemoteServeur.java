import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RemoteServeur extends Remote{

    public boolean enregistrerNoeud(String addresse) throws RemoteException;
    public boolean ajouterData(String data) throws RemoteException;
    public ArrayList<String> occurenceMot(String mot) throws RemoteException;


}
