package Vues;
import RMI.ClientRMI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FenetrePrincipal extends JFrame {
    private ClientRMI clientRMI;

    public FenetrePrincipal(ClientRMI clientRMI){
        this.clientRMI=clientRMI;

        this.setTitle("Application BLOCKCHAIN");
        this.setSize(800,600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel pan=new JPanel();

        JButton boutton1=new JButton("Créer une nouvelle chaine de bloques");
        boutton1.setPreferredSize(new Dimension(400,100));
        boutton1.setFont(new Font("Arial", Font.PLAIN, 20));
        boutton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame InterfaceCreerNouvelleChaine=new CreerNouvelleChaine(clientRMI);
            }
        } );

        JButton boutton2=new JButton("Afficher la chaine de bloques");
        boutton2.setPreferredSize(new Dimension(400,100));
        boutton2.setFont(new Font("Arial", Font.PLAIN, 20));
        boutton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame InterfaceAfficherChaine=new AfficherChaine(clientRMI);
            }
        } );


        JButton boutton3=new JButton("Ajouter une nouvelle donnée à la chaine");
        boutton3.setPreferredSize(new Dimension(400,100));
        boutton3.setFont(new Font("Arial", Font.PLAIN, 20));
        boutton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame InterfaceAjouterDonnes=new AjouterNouvelleDonnees(clientRMI);
            }
        } );



        JButton boutton4=new JButton("Chercher une données dans la chaine");
        boutton4.setPreferredSize(new Dimension(400,100));
        boutton4.setFont(new Font("Arial", Font.PLAIN, 20));
        boutton4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame interfaceChercherDonnees=new ChercherDonneeDansLaChaine(clientRMI);
            }
        } );



        pan.add(boutton1);
        pan.add(boutton2);
        pan.add(boutton3);
        pan.add(boutton4);
        this.setContentPane(pan);
        this.setVisible(true);
    }
}
