package RMI;

import Structure.Bloque;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RemoteServeur extends Remote{
    public Bloque nouvelleChaine(String data) throws RemoteException;
    public boolean enregistrerNoeud(String addresse) throws RemoteException;
    public Bloque ajouterData(String data) throws RemoteException;
    public ArrayList<String> occurenceMot(String mot) throws RemoteException;
    public ArrayList<Bloque> afficherChaineBloques() throws  RemoteException;


}
