import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;

public class Noeud implements RemoteInterface {

    public static final String ADDRESSE_SERVEUR="rmi://localhost:1099/Serveur";
    protected  ChaineBloque chaineBloque;
    protected Bloque bloqueActuel;
    public Noeud(){
        this.chaineBloque=new ChaineBloque(5);

    }

    @Override
    //Miner le nouveau bloque
    public Bloque miner(String data) throws RemoteException {

        if(this.chaineBloque.taille()==0)
        this.bloqueActuel=new Bloque(Bloque.GENESIS_BLOQUE,data);
        else{
            this.bloqueActuel=new Bloque(this.chaineBloque.dernierBloque().getHash(),data);

        }

        bloqueActuel.minerBlockaDistance(this.chaineBloque.getDifficulte());
        return bloqueActuel;
    }

    @Override
    public boolean validerChaine(ChaineBloque chaineBloque) throws RemoteException {
        return chaineBloque.estValide();
    }

    @Override
    public boolean ajouterBloque(Bloque bloque) throws RemoteException {
            return this.chaineBloque.validerNouveauBloque(bloque);

    }

    @Override
    public void arreterMining(Bloque bloque) throws RemoteException {
        System.out.println("Arret de mining dans le noeud ");
        System.out.println("je suis le neoud "+this.toString());
        System.out.println("le nouveau bloque que j'ai reçu ");
        System.out.println(bloque.getHash());
        if (        this.chaineBloque.validerNouveauBloque(bloque)) {
            System.out.println("Nouveau Bloque validé");
            bloqueActuel.arreterMining();
        }
        else
            System.out.println("Nouveau bloque refusé ");

    }

    @Override
    public ArrayList<String> rechercher(String mot) throws RemoteException {
        ArrayList<String> liste=new ArrayList<>();
        if(!chaineBloque.estValide()) return null;
        Iterator<Bloque> iterator=chaineBloque.getListeBloque().iterator();
        while (iterator.hasNext()){
            Bloque temp=iterator.next();
            if(temp.getData().contains(mot)) liste.add(temp.getData());
        }

        return liste;
    }

    public void enregistreMoi(){
        try {
            String addresse="rmi://localhost:1099/"+this.hashCode();
            Registry registry = LocateRegistry.getRegistry(1099);
            RemoteInterface serveurSkeleton = (RemoteInterface) UnicastRemoteObject.exportObject(this,1099); // Génère un stub vers notre service.
            Naming.rebind(addresse,serveurSkeleton);

            RemoteServeur r = (RemoteServeur) Naming.lookup(ADDRESSE_SERVEUR);
            r.enregistrerNoeud(addresse);
            System.out.println("Noeud ajoute avec success au serveur : "+ADDRESSE_SERVEUR);

        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
