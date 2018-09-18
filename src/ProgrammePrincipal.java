import java.rmi.RemoteException;

public class ProgrammePrincipal {
    public static void main(String[] argv) {
        //On commence par lancer le serveur
        Serveur serveur=new Serveur();
        serveur.main(new String[1] );
        //Puis on lance le client
        //Client client=new Client();
    //    client.main();
        Noeud noeud1=new Noeud();
        Noeud noeud2=new Noeud();
        Noeud noeud3=new Noeud();
        noeud1.enregistreMoi();
        noeud2.enregistreMoi();
        noeud3.enregistreMoi();

    }
}
