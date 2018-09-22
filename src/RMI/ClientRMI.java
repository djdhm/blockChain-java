package RMI;

import Structure.Bloque;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;


public class ClientRMI {
    private RemoteServeur serveur;
    public ClientRMI(){
        try {
            this.serveur=(RemoteServeur) Naming.lookup(Noeud.ADDRESSE_SERVEUR);

        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public Bloque creerChaine(String data){
        try {
            return  this.serveur.nouvelleChaine(data);
        }catch (RemoteException e){

        }
        return null;
    }
    public Bloque ajouterData(String data){
        try {
            return this.serveur.ajouterData(data);
        }catch (RemoteException e){
            e.printStackTrace();
        }
        return null;
    }


    public ArrayList<String> ChercherUnMot(String mot){
        try {
            System.out.println("Chercher un mot dans clientRMI");
            return this.serveur.occurenceMot(mot);
        }catch (RemoteException e){
            e.printStackTrace();
        }
        return null;
    }
    public ArrayList<Bloque> afficherChaine(){
        try{
           return  this.serveur.afficherChaineBloques();
        }catch (RemoteException e){
            e.printStackTrace();
        }
        return  null;
    }

    public static void main(String[] argv) {
        System.out.println("Lancement du clientRMI");

    }

}
