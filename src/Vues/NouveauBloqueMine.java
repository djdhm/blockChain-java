package Vues;

import Structure.Bloque;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NouveauBloqueMine extends JPanel {
    private  JFrame fenetre;
    private Bloque bloque;
        public NouveauBloqueMine(JFrame fenetre, Bloque bloque){
            this.fenetre=fenetre;
            this.bloque=bloque;
            init();
        }

        public void init() {
            JPanel pan=new JPanel();
            pan.add(new JLabel("Nouveau bloque miné"), BorderLayout.NORTH);
            pan.add(new AffichageBloque(bloque,0),BorderLayout.CENTER);
            JButton retour=new JButton("Retour");
            retour.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    close();
                }
            });
            System.out.println("Affichage jdyd");
            pan.add(retour,BorderLayout.PAGE_END);
            raffraichir(pan);
        }

    private void raffraichir(JPanel pan) {
            this.fenetre.setContentPane(pan);
            this.fenetre.repaint();
    }

    private void close() {
            this.fenetre.dispose();
    }
}
