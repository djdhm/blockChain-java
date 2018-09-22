package Vues;
import RMI.ClientRMI;
import Structure.Bloque;

import javax.swing.*;
import javax.swing.text.ComponentView;
import javax.swing.text.View;
import java.awt.*;
import java.util.ArrayList;

public class AfficherChaine extends JFrame {
    JScrollPane scrollPane;
    public AfficherChaine(ClientRMI clientRMI){
        this.setTitle("Afficher la chaine de bloques");
        this.setSize(640,480);
        this.setLocationRelativeTo(null);
        JPanel pan=new JPanel(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        ArrayList<Bloque> chaineDeBloque=clientRMI.afficherChaine();

        if(chaineDeBloque.size()==0){
            pan.setLayout(new BorderLayout());
            JTextArea vide=new JTextArea("La chaine est vide");
            vide.setFont(new Font("Arial", Font.PLAIN, 40));
            pan.add(vide,BorderLayout.CENTER);
        }
        else{
            JPanel liste=new JPanel();
            BoxLayout horizontalLayout=new BoxLayout(liste,BoxLayout.X_AXIS);
            liste.setLayout(horizontalLayout);
            int indice=1;
            for(Bloque b:chaineDeBloque){
                liste.add(new JLabel("---------->"));
                AffichageBloque donnesBloque=new AffichageBloque(b,indice);
                liste.add(donnesBloque);
                indice++;

            }
            scrollPane=new JScrollPane(liste);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//            scrollPane.add(liste);
            //scrollPane.setBounds(50,50,500,400);
            pan.add(scrollPane);

        }


        this.setContentPane(scrollPane);

        setVisible(true);
    }
}
