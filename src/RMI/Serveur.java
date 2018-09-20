package RMI;

import Structure.Bloque;

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

    public static void main(String [] args) {
//
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
           RemoteServeur serveurSkeleton = (RemoteServeur) UnicastRemoteObject.exportObject(new Serveur(),1099); // Génère un stub vers notre service.
            Naming.rebind(Noeud.ADDRESSE_SERVEUR,serveurSkeleton);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        System.out.println("RMI.Serveur is running ");

    }

    @Override
    public boolean enregistrerNoeud(String addresse) throws RemoteException {
        System.out.println("Ajout de neoud avec success");
       this.listeAddresseNoeuds.add(addresse);

        return true;
    }

    @Override
    public boolean ajouterData(String data) throws RemoteException {
        MiningCountdown countdown=new MiningCountdown(1);
        for (String addresse:
             listeAddresseNoeuds) {

                //RMI.RemoteNoeud noeud=(RMI.RemoteNoeud) Naming.lookup(addresse);
                Thread thread=new Thread(new MiningRunnable(addresse,data,countdown ));
                thread.start();
        }


        try {
            countdown.await();
            for (String addresse:listeAddresseNoeuds
                 ) {
                RemoteNoeud r=(RemoteNoeud) Naming.lookup(addresse);
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
        System.out.println("Occurence mot dans Serveur");
        RechercheCountdown countdown=new RechercheCountdown(0);
        for (String addresse:listeAddresseNoeuds
             ) {
                Thread thread=new Thread(new RechercheRunnable(addresse,countdown,mot));
                thread.start();
        }
        try {
            ArrayList<String> res=countdown.getResultat();
            countdown.await();
            return res;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return  null;
    }

    @Override
    public ArrayList<Bloque> afficherChaineBloques() throws RemoteException{
        try{
            RemoteNoeud n=(RemoteNoeud) Naming.lookup(this.listeAddresseNoeuds.get(0));
            return n.afficherChaineBloque();
        }catch (NotBoundException e){
            e.printStackTrace();
        }
        catch (MalformedURLException e){
            e.printStackTrace();
        }
        return null;
    }
}
