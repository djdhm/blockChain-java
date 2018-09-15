import java.util.ArrayList;

public class ChaineBloque {


    private ArrayList<Bloque> listeBloque;
    private int difficulte;

    public ChaineBloque(int difficulte){
        this.listeBloque=new ArrayList<Bloque>();
        this.difficulte=difficulte;
    }
    public int taille(){
        return this.listeBloque.size();

    }
    public Bloque dernierBloque(){
        return this.listeBloque.get(this.listeBloque.size()-1);

    }

    //Verifie si la chaine des bloques est valide en faisant appel à la methode estBloqueValide du Bloque
    public boolean estValide(){
        Bloque bloqueCourant,bloquePrecedent=listeBloque.get(0);
        if(!bloquePrecedent.estBloqueValide(Bloque.GENESIS_BLOQUE,difficulte)) return false;
        for(int i=1;i<this.listeBloque.size();i++){
            bloqueCourant=this.listeBloque.get(i);
            bloquePrecedent=this.listeBloque.get(i-1);
            if(!bloqueCourant.estBloqueValide(bloquePrecedent.getHash(),difficulte))
                return false;

        }
        return true;
    }
    //Si un bloque est valide on l'ajoute à la chaine des bloques
    public  boolean validerNouveauBloque(Bloque bloque){
        this.listeBloque.add(bloque);
        if(this.estValide()) {
            return  true;
        }
        this.listeBloque.remove(bloque);
        return false;
    }

    public ArrayList<Bloque> getListeBloque() {
        return listeBloque;
    }
    public void setDifficulte(int difficulte) {
        this.difficulte = difficulte;
    }
    public int getDifficulte(){
        return this.difficulte;
    }
}
