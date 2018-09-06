import java.util.ArrayList;

public class ChaineBloque {


    private ArrayList<Bloque> listeBloque;
    public static int difficulte = 5;

    public ChaineBloque(){
        this.listeBloque=new ArrayList<Bloque>();

    }


    public boolean estValide(){
        Bloque bloqueCourant,bloquePrecedent=listeBloque.get(0);
        String hashTarget = new String(new char[difficulte]).replace('\0', '0');

        if(!bloquePrecedent.getHash().equals(bloquePrecedent.calculerHash())) {
           return false;}
        for(int i=1;i<this.listeBloque.size();i++){
            bloqueCourant=this.listeBloque.get(i);
            bloquePrecedent=this.listeBloque.get(i-1);
            if(!bloqueCourant.getHash().equals(bloqueCourant.calculerHash())) {
                System.out.println("hash courrant non valide");
                return false;}

            if(!bloqueCourant.getHashPrecedent().equals(bloquePrecedent.getHash()))
            {    System.out.println("hash precedent non valide");
                return false;}
            //verifier si le puzzle a ete resolu
            if(!bloqueCourant.getHash().substring( 0, difficulte).equals(hashTarget)) {
                System.out.println("ce bloque n'a pas ete resolu");
                return false;
            }

        }
        return true;
    }

    public ArrayList<Bloque> getListeBloque() {
        return listeBloque;
    }

    public void setListeBloque(ArrayList<Bloque> listeBloque) {
        this.listeBloque = listeBloque;
    }
}
