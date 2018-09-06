public class main {
    public static void main(String[] args) {

        int x=0;

        ChaineBloque chaineBloque=new ChaineBloque();
        Bloque bloque=new Bloque("Genesis Bloque","Data a ajouter ");
        bloque.minerBlock(5);

        chaineBloque.getListeBloque().add(bloque);
        while( x<10){
            Bloque precedent=bloque;
            bloque=new Bloque(precedent.getHash(),"nouvelle data"+x);
            bloque.minerBlock(5);
            chaineBloque.getListeBloque().add(bloque);
            x=x+1;
        }

        System.out.println("verifier la validite de la chaine  ");
        System.out.println(chaineBloque.estValide());



    }
}
