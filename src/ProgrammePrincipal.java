public class ProgrammePrincipal {
    public static void main(String[] argv) {
        //On commence par lancer le serveur
        Serveur serveur=new Serveur();
        serveur.main();
        //Puis on lance le client
        Client client=new Client();
        client.main();
    }
}
