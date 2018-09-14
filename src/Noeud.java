import java.rmi.RemoteException;

public class Noeud implements RemoteInterface {

    protected  ChaineBloque chaineBloque;
    protected Bloque bloqueActuel;
    public Noeud(){
        this.chaineBloque=new ChaineBloque();

    }

    @Override
    public Bloque miner(String data) throws RemoteException {

        if(this.chaineBloque.taille()==0)
        this.bloqueActuel=new Bloque(Bloque.GENESIS_BLOQUE,"exemple de data");
        else{
            this.bloqueActuel=new Bloque(this.chaineBloque.dernierBloque().getHash(),data);

        }

        bloqueActuel.minerBlockaDistance(6);
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
        System.out.println("le nouveau bloque k j ai recu ");
        System.out.println(bloque.getHash());
        if (        this.chaineBloque.validerNouveauBloque(bloque)) {
            System.out.println("Nouveau Bloque Valide");
            bloqueActuel.arreterMining();
        }
        else
            System.out.println("Nouveau bloque refusé ");

    }
}
