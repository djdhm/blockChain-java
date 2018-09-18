import sun.nio.ch.ThreadPool;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;

public class Client {


    public static void main(String[] argv) {
        try {
            RemoteServeur serveur=(RemoteServeur) Naming.lookup(Noeud.ADDRESSE_SERVEUR);
            System.out.println("Test ajout");
            serveur.ajouterData("Test data");
            serveur.ajouterData("Test mouna");
            serveur.ajouterData("mouna data");
            serveur.ajouterData("texte test");
            serveur.ajouterData("Mouna mouna ");
            serveur.ajouterData("bloque finale");

            System.out.println("Fin d'ajout ");
            System.out.println("Recherche de mot ");

            Iterator<String> iterator=serveur.occurenceMot("mouna").iterator();
            System.out.println("Resultat");
            while (iterator.hasNext()){
                System.out.println(iterator.next());
            }


        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
