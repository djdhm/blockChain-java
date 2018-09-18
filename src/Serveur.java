import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Serveur implements RemoteServeur,Serializable {
    ArrayList<String> listeAddresseNoeuds;

    public Serveur(){
        this.listeAddresseNoeuds=new ArrayList<>();
    }
    public boolean ajouterNoeud(String addresse){
        this.listeAddresseNoeuds.add(addresse);
        return true;
    }
    public static void main(String [] args) {
//        try {
//            // 1099 est le port sur lequel sera publié le service. Nous devons le préciser  à la création du stub.
//            RemoteInterface skeleton1 = (RemoteInterface) UnicastRemoteObject.exportObject(new Noeud(),1099); // Génère un stub vers notre service.
//            RemoteInterface skeleton2 = (RemoteInterface) UnicastRemoteObject.exportObject(new Noeud(),1099); // Génère un stub vers notre service.
//            RemoteInterface skeleton3 = (RemoteInterface) UnicastRemoteObject.exportObject(new NoeudTricheur(),1099); // Génère un stub vers notre service.
//
//            Registry registry = LocateRegistry.createRegistry(1099);
//            Naming.rebind("rmi://localhost:1099/Noeud1", skeleton1); // publie la 1ere instance sous le nom "Noeud1"
//            Naming.rebind("rmi://localhost:1099/Noeud2", skeleton2); // publie la 2eme instance sous le nom "Noeud2"
//            Naming.rebind("rmi://localhost:1099/Noeud3", skeleton3); // publie la 3eme instance sous le nom "Noeud3"
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
           RemoteServeur serveurSkeleton = (RemoteServeur) UnicastRemoteObject.exportObject(new Serveur(),1099); // Génère un stub vers notre service.
            Naming.rebind("rmi://localhost:1099/Serveur",serveurSkeleton);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        System.out.println("Serveur is running ");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public boolean enregistrerNoeud(String addresse) throws RemoteException {
        this.ajouterNoeud(addresse);

        return true;
    }
}
