import java.rmi.RemoteException;

public class Noeud implements RemoteInterface {

    private ChaineBloque chaineBloque;
    private Bloque bloqueActuel;
    public Noeud(){
        this.chaineBloque=new ChaineBloque();

    }

    @Override
    public Bloque miner(String data) throws RemoteException {

        if(this.chaineBloque.taille()==0)
        this.bloqueActuel=new Bloque(Bloque.GENESIS_BLOQUE,"fgs");
        else{
            this.bloqueActuel=new Bloque(this.chaineBloque.dernierBloque().getHash(),data);

        }
        bloqueActuel.minerBlock(6);
        return bloqueActuel;
    }

    @Override
    public boolean validerChaine(ChaineBloque chaineBloque) throws RemoteException {
        return false;
    }

    @Override
    public boolean ajouterBloque(Bloque bloque) throws RemoteException {
        return false;
    }
}
