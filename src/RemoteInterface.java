
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteInterface extends Remote{
    public Bloque miner(String data) throws RemoteException;
    public boolean validerChaine(ChaineBloque chaineBloque)throws RemoteException;
    public boolean ajouterBloque(Bloque bloque) throws RemoteException;
    public void arreterMining(Bloque bloque) throws RemoteException;
}
