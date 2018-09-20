package Vues;
import RMI.ClientRMI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreerNouvelleChaine extends JFrame {
    public  CreerNouvelleChaine(ClientRMI clientRMI){
        this.setTitle("Création d'une nouvelle chaine");
        this.setSize(600,400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JPanel pan=new JPanel();
        JTextField donnesBloqueInitial=new JTextField("Introduire la donneé du premier bloque");
        donnesBloqueInitial.setPreferredSize(new Dimension(400,100));
        donnesBloqueInitial.setFont(new Font("Arial", Font.PLAIN, 20));


        JButton bouttonCreer=new JButton("Créer");
        bouttonCreer.setPreferredSize(new Dimension(400,100));
        bouttonCreer.setFont(new Font("Arial", Font.PLAIN, 20));
        bouttonCreer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clientRMI.ajouterData(donnesBloqueInitial.getText());
            }
        } );


        pan.add(donnesBloqueInitial);
        pan.add(bouttonCreer);
        this.setContentPane(pan);
        this.setVisible(true);

    }
}
