package Vues;
import RMI.ClientRMI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FenetrePrincipal extends JFrame {
    public static final Dimension PREFERRED_SIZE = new Dimension(180, 60);
    private ClientRMI clientRMI;

    public FenetrePrincipal(ClientRMI clientRMI){
        this.clientRMI=clientRMI;

        this.setTitle("Application BLOCKCHAIN");
        this.setSize(800,600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel pan=new JPanel();

        JButton boutton1=new JButton("Créer une nouvelle chaine de bloques");
        boutton1.setPreferredSize(PREFERRED_SIZE);
        boutton1.setFont(new Font("Arial", Font.PLAIN, 20));
        boutton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame InterfaceCreerNouvelleChaine=new CreerNouvelleChaine(clientRMI);
            }
        } );

        JButton boutton2=new JButton("Afficher la chaine de bloques");
        boutton2.setPreferredSize(PREFERRED_SIZE);
        boutton2.setFont(new Font("Arial", Font.PLAIN, 20));
        boutton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame InterfaceAfficherChaine=new AfficherChaine(clientRMI);
            }
        } );


        JButton boutton3=new JButton("Ajouter une nouvelle donnée à la chaine");
        boutton3.setPreferredSize(PREFERRED_SIZE);
        boutton3.setFont(new Font("Arial", Font.PLAIN, 20));
        boutton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame InterfaceAjouterDonnes=new AjouterNouvelleDonnees(clientRMI);
            }
        } );



        JButton boutton4=new JButton("Chercher une données dans la chaine");
        boutton4.setPreferredSize(PREFERRED_SIZE);
        boutton4.setFont(new Font("Arial", Font.PLAIN, 20));
        boutton4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame interfaceChercherDonnees=new ChercherDonneeDansLaChaine(clientRMI);
            }
        } );

        this.setLayout(new BorderLayout());
        GridLayout mgr = new GridLayout(5,1);
        mgr.setHgap(60);
        mgr.setVgap(20);
        pan.setLayout(mgr);

        pan.add(boutton1);
        pan.add(boutton2);
        pan.add(boutton3);
        pan.add(boutton4);
        this.setContentPane(pan);
        this.setVisible(true);
    }
}
