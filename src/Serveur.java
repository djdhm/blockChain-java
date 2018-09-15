import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Serveur  {

    public static void main() {
        try {
            // 1099 est le port sur lequel sera publié le service. Nous devons le préciser  à la création du stub.
            RemoteInterface skeleton1 = (RemoteInterface) UnicastRemoteObject.exportObject(new Noeud(),1099); // Génère un stub vers notre service.
            RemoteInterface skeleton2 = (RemoteInterface) UnicastRemoteObject.exportObject(new Noeud(),1099); // Génère un stub vers notre service.
            RemoteInterface skeleton3 = (RemoteInterface) UnicastRemoteObject.exportObject(new NoeudTricheur(),1099); // Génère un stub vers notre service.

            Registry registry = LocateRegistry.createRegistry(1099);
            Naming.rebind("rmi://localhost:1099/Noeud1", skeleton1); // publie la 1ere instance sous le nom "Noeud1"
            Naming.rebind("rmi://localhost:1099/Noeud2", skeleton2); // publie la 2eme instance sous le nom "Noeud2"
            Naming.rebind("rmi://localhost:1099/Noeud3", skeleton3); // publie la 3eme instance sous le nom "Noeud3"

            System.out.println("Serveur is running ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
