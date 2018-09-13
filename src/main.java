import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        int niveauDifficulte = 4;/*c'est le nombre de 0 que les noeuds doivent résoudre.Vous pouvez changer sa valeur*/
        ChaineBloque chaineBloque = new ChaineBloque();
        Scanner in = new Scanner(System.in);

        while (true) {

            System.out.println("Implementation d'un blockchain");
            System.out.println("______________________________");
            System.out.println("Introduire un choix pour tester une opération");
            System.out.println("______________________________");
            System.out.println("1. Créer une nouvelle chaine de bloques");
            System.out.println("2. Ajouter N bloques à la chaine existante");
            System.out.println("3. Afficher la chaine de bloques");
            System.out.println("4. Afficher les données d'un bloque");
            System.out.println("5. Quitter\n");
            int choix = 0;
            String data = "";
            choix = in.nextInt();
            switch (choix) {
                case 1:
                    System.out.println("Introduisez La valeur de difficulté de l'acceptation de hash : ");
                    try{
                        niveauDifficulte=in.nextInt();
                        System.out.println("Le niveau de difficulté ");
                    }catch (Exception e){
                        niveauDifficulte=4;
                        System.out.println("Le niveau de difficulté par défaut est 4");
                    }
                    chaineBloque.setDifficulte(niveauDifficulte);
                    in=new Scanner(System.in);
                    System.out.println("Introduire la donnée correspondante au bloque");
                    data = in.nextLine();
                    Bloque bloque = new Bloque(Bloque.GENESIS_BLOQUE, data);
                    bloque.minerBlock(niveauDifficulte);/* miner le bloque avant de l'ajouter à la chaine du bloque*/
                    chaineBloque.getListeBloque().add(bloque);
                    break;
                case 2:
                    System.out.println("Combien de bloques voulez vous ajouter à la chaine ?");
                    in = new Scanner(System.in);
                    int N = in.nextInt();
                    if(chaineBloque.getListeBloque().size()==0){
                        System.out.println("Introduire la donnée correspondante au bloque numéro " + 1);
                        in = new Scanner(System.in);
                        data = in.nextLine();
                        Bloque nouveauBloque = new Bloque(Bloque.GENESIS_BLOQUE, data);
                        chaineBloque.getListeBloque().add(nouveauBloque);
                        N=N-1;
                    }
                    int tailleChaine=chaineBloque.getListeBloque().size();
                    for (int i = 0; i < N; i++) {

                        System.out.println("Introduire la donnée correspondante au bloque numéro " + (tailleChaine+i));
                        in = new Scanner(System.in);
                        data = in.nextLine();
                        /*Pour recuperer le hash du bloque precedent*/
                        Bloque precedent = chaineBloque.getListeBloque().get(chaineBloque.getListeBloque().size()-1);
                        /*Une fois que nous avons la data du bloque et le hash du precedent on crée ce bloque et on l'ajoute à la chaine*/
                        Bloque nouveauBloque = new Bloque(precedent.getHash(), data);
                        nouveauBloque.minerBlock(niveauDifficulte);/* miner le bloque avant de l'ajouter à la chaine du bloque*/
                        chaineBloque.getListeBloque().add(nouveauBloque);
                    }
                    break;
                case 3:
                    System.out.println("La chaine de bloques:");
                    for (int i = 0; i < chaineBloque.getListeBloque().size(); i++) {
                        System.out.println(" ----------------------------------");
                        System.out.println("|                                  |");
                        System.out.println("| " + chaineBloque.getListeBloque().get(i).getHash().substring(0,32) + " |");
                        System.out.println("| " + chaineBloque.getListeBloque().get(i).getHash().substring(32) + " |");
                        System.out.println("|                                  |");
                        System.out.println(" ----------------------------------");
                        System.out.println("                 |                 ");
                        System.out.println("                 |                 ");
                        System.out.println("                 |                 ");
                    }
                    break;
                case 4:
                    System.out.println("Introduire le numéro du bloque que vous voulez afficher");
                    in = new Scanner(System.in);
                    int numBloque = in.nextInt();
                    Bloque bloqueSelectionne = chaineBloque.getListeBloque().get(numBloque - 1);
                    System.out.println("|--------------------------------------------------------------------------------------|");
                    System.out.println("|       ATTRIBUT |    VALEUR                                                           | ");
                    System.out.println("|----------------|---------------------------------------------------------------------|");
                    System.out.println("|     HASH       | "+bloqueSelectionne.getHash()+"    ");
                    System.out.println("| HASH PRECEDENT | "+bloqueSelectionne.getHashPrecedent()+" ");
                    System.out.println("|    DONNEES     | "+bloqueSelectionne.getData()+" ");
                    System.out.println("|    TIMESTAMP   | "+bloqueSelectionne.getTimeStamp()+"   ");
                    System.out.println("|    NONCE       | "+bloqueSelectionne.getNonce()+"     ");
                    System.out.println("|----------------|---------------------------------------------------------------------|");
                    break;
                case 5:
                    System.exit(0);
                    break;

            }
        }
    }
}
