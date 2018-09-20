package RMI;
import Structure.*;


import java.rmi.RemoteException;
// C'est un exemple qui illustre l'interet du mining: Proof of work
public class NoeudTricheur extends Noeud {

   public NoeudTricheur(){
       super();
   }

   @Override
   public Bloque miner(String data) throws RemoteException {
       if(this.chaineBloque.taille()==0)
           this.bloqueActuel=new Bloque(Bloque.GENESIS_BLOQUE,data);
       else{
           this.bloqueActuel=new Bloque(this.chaineBloque.dernierBloque().getHash(),data);

       }
       System.out.println("noeud tricheur a fini de miner ");
       return bloqueActuel;
   }
}
