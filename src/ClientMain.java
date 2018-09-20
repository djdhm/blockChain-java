import RMI.ClientRMI;
import RMI.Noeud;
import RMI.Serveur;
import Vues.FenetrePrincipal;

import javax.swing.*;

public class ClientMain {
    public static void main(String[] argv) {

        //On commence par lancer le serveur
        Serveur serveur=new Serveur();
        serveur.main(new String[1]);
        //On ajoute les deux noeuds au serveur
        Noeud noeud1=new Noeud();
        Noeud noeud2=new Noeud();
        noeud1.enregistreMoi();
        noeud2.enregistreMoi();
        //Puis on crée le client RMI
         ClientRMI clientRMI=new ClientRMI();
        clientRMI.main(new String[1]);
        //On affiche la fenetre principale
        JFrame fenetrePricipale=new FenetrePrincipal(clientRMI);









    }
}
