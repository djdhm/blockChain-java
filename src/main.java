import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        int niveauDifficulte = 4;/*c'est le nombre de 0 que les noeuds doivent résoudre.Vous pouvez changer sa valeur*/
        ChaineBloque chaineBloque = new ChaineBloque();
        while (true) {

            System.out.println("Implementation d'un blockchain");
            System.out.println("______________________________");
            System.out.println("Introduire un choix pour tester une opération");
            System.out.println("______________________________");
            System.out.println("1. Créer une nouvelle chaine de bloques");
            System.out.println("2. Ajouter N bloques à la chaine existante");
            System.out.println("3. Afficher la chaine de bloques");
            System.out.println("4. Afficher les données d'un bloque");
            System.out.println("5. Quitter");
            int choix = 0;
            String data = "";
            Scanner in = new Scanner(System.in);
            choix = in.nextInt();
            switch (choix) {
                case 1:
                    System.out.println("Introduire la donnée correspondante au bloque");
                    in = new Scanner(System.in);
                    data = in.nextLine();
                    Bloque bloque = new Bloque("Genesis Bloque", data);
                    bloque.minerBlock(niveauDifficulte);/* miner le bloque avant de l'ajouter à la chaine du bloque*/
                    chaineBloque.getListeBloque().add(bloque);
                    break;
                case 2:
                    System.out.println("Combien de bloques voulez vous ajouter à la chaine ?");
                    in = new Scanner(System.in);
                    int N = in.nextInt();
                    for (int i = 0; i < N; i++) {
                        System.out.println("Introduire la donnée correspondante au bloque numéro " + i + 1);
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
                    }
                    break;
                case 4:
                    System.out.println("Introduire le numéro du bloque que vous voulez afficher");
                    in = new Scanner(System.in);
                    int numBloque = in.nextInt();
                    Bloque bloqueSelectionne = chaineBloque.getListeBloque().get(numBloque - 1);
                    System.out.println("Le hash du bloque " + bloqueSelectionne.getHash());
                    System.out.println("Le hash précedent du bloque " + bloqueSelectionne.getHashPrecedent());
                    System.out.println("La données du bloque " + bloqueSelectionne.getData());
                    System.out.println("Le timestamp du bloque " + bloqueSelectionne.getTimeStamp());
                    System.out.println("Le nonce du bloque" + bloqueSelectionne.getNonce());
                    break;
                case 5:
                    System.exit(0);
                    break;

            }
        }
    }
}
