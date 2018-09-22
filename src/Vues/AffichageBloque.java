package Vues;

import Structure.Bloque;

import javax.swing.*;
import java.util.Iterator;
import java.util.Spliterator;

public class AffichageBloque extends JPanel {
    final int LENGTH=16;

        public AffichageBloque(Bloque bloque, int indice){
            super();
            String data = bloque.getData();
            addText("----------------------------------\n");
            addText("|      Bloque Num : "+indice+ "         |\n");
            addText("----------------------------------\n");
            addText("|       Contenu de Bloque\t|\n");
            addText("----------------------------------\n");
            afficher(data);
            this.setLayout(new BoxLayout(this,BoxLayout
            .Y_AXIS));
            addText("----------------------------------\n");

            addText("|               Hash                  |\n");
            addText("----------------------------------\n");
            data=bloque.getHash();
            afficher(data);
            addText("----------------------------------\n");
            data=bloque.getHashPrecedent();
            addText("|          Hash Précédent   |\n");
            addText("----------------------------------\n");
            afficher(data);
            addText("----------------------------------\n");

        }

    private void afficher(String data) {
        for (int i = 0; i< data.length(); i+=LENGTH) {
            int indice=Math.min(i+LENGTH,data.length());
            addText(" "+data.substring(i,indice)+"  ");
        }
    }

    private void addText(String texte){
            this.add(new JLabel(texte));
        }
}
