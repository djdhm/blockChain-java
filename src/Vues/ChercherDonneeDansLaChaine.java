package Vues;
import RMI.ClientRMI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ChercherDonneeDansLaChaine extends JFrame {
    public ChercherDonneeDansLaChaine(ClientRMI clientRMI){
        this.setTitle("Chercher une données dans la chaine");
        this.setSize(800,400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel pan=new JPanel();
        JTextField inputRecherche=new JTextField();
        inputRecherche.setPreferredSize(new Dimension(400,50));
        inputRecherche.setFont(new Font("Arial", Font.PLAIN, 20));


        JButton chercherBoutton=new JButton("Chercher");
        chercherBoutton.setPreferredSize(new Dimension(180,60));
        chercherBoutton.setFont(new Font("Arial", Font.PLAIN, 14));

        chercherBoutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> res=clientRMI.ChercherUnMot(inputRecherche.getText());
                System.out.println("res dans client "+res);
                pan.removeAll();
                if(res.size()==0){
                    JTextArea vide=new JTextArea("Cette données n'exite pas dans la chaine");
                    vide.setPreferredSize(new Dimension(400,100));
                    vide.setFont(new Font("Arial", Font.PLAIN, 20));
                    pan.add(vide);
                }
                else{
                    for(String s:res){
                        JTextArea donneeUnBloque=new JTextArea(s);
                        pan.add(donneeUnBloque);
                    }
                }
            rafraichier(pan);

            }




        });
        pan.add(inputRecherche);
        pan.add(chercherBoutton);
        this.setContentPane(pan);
        this.setVisible(true);

    }


    public void rafraichier(JPanel panel) {
        System.out.println("Rafraichissement");
        this.setContentPane(panel);
        this.repaint();
    }




}

