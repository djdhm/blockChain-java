package Vues;
import RMI.ClientRMI;
import Structure.Bloque;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AfficherChaine extends JFrame {
    public AfficherChaine(ClientRMI clientRMI){
        this.setTitle("Afficher la chaine de bloques");
        this.setSize(800,800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JPanel pan=new JPanel();
        ArrayList<Bloque> chaineDeBloque=clientRMI.afficherChaine();
        if(chaineDeBloque==null){
            JTextArea vide=new JTextArea("La chaine est vide");
            vide.setFont(new Font("Arial", Font.PLAIN, 40));
            pan.add(vide);
        }
        else{
            for(Bloque b:chaineDeBloque){
                JTextArea donnesBloque=new JTextArea(b.getData());
                donnesBloque.setFont(new Font("Arial", Font.PLAIN, 40));
                pan.add(donnesBloque);
            }
        }



        this.setContentPane(pan);
        setVisible(true);
    }
}
