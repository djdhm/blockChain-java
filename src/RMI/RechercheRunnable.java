package RMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class RechercheRunnable implements Runnable {
    private String mot;
    private String addresse;
    private RechercheCountdown countdown;
    public RechercheRunnable(String addresse, RechercheCountdown countdown, String mot){
        this.mot=mot;
        this.countdown=countdown;
        this.addresse=addresse;
    }
    @Override
    public void run() {
        try {
            RemoteNoeud noeud=(RemoteNoeud) Naming.lookup(addresse);
            ArrayList<String> res=noeud.rechercher(mot);
            if(res!=null) countdown.countDown(res);


        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
