import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
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
        System.out.println("Ajout de neoud avec success");
        this.ajouterNoeud(addresse);

        return true;
    }

    @Override
    public boolean ajouterData(String data) throws RemoteException {
        MiningCountdown countdown=new MiningCountdown(1);
        for (String addresse:
             listeAddresseNoeuds) {

                //RemoteInterface noeud=(RemoteInterface) Naming.lookup(addresse);
                Thread thread=new Thread(new MiningRunnable(addresse,data,countdown ));
                thread.start();
        }


        try {
            countdown.await();
            for (String addresse:listeAddresseNoeuds
                 ) {
                RemoteInterface r=(RemoteInterface) Naming.lookup(addresse);
                r.arreterMining(countdown.getNouveauBloqueMine());

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        System.out.println("Nouveau bloc miné avec succés");
        return true;
    }

    @Override
    public ArrayList<String> occurenceMot(String mot) throws RemoteException {
        RechercheCountdown countdown=new RechercheCountdown(0);
        for (String addresse:listeAddresseNoeuds
             ) {
                Thread thread=new Thread(new RechercheRunnable(addresse,countdown,mot));
                thread.start();
        }
        try {
            countdown.await();
            return countdown.getResultat();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return  null;
    }
}
