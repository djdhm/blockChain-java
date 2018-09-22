package Vues;
import RMI.ClientRMI;
import Structure.Bloque;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AjouterNouvelleDonnees extends JFrame {
    public  AjouterNouvelleDonnees(ClientRMI clientRMI){
        this.setTitle("Ajouter une nouvelle données à la chaine");
        this.setSize(600,250);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JPanel pan=new JPanel();
        JTextField inputNouvelleDonnee=new JTextField();
        inputNouvelleDonnee.setPreferredSize(new Dimension(400,50));
        inputNouvelleDonnee.setFont(new Font("Arial", Font.PLAIN, 20));


        JButton ajouterBoutton=new JButton("Ajouter la nouvelle donnée");
        ajouterBoutton.setPreferredSize(new Dimension(400,100));
        ajouterBoutton.setFont(new Font("Arial", Font.PLAIN, 20));
        ajouterBoutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               Bloque nouveauBloque=clientRMI.ajouterData(inputNouvelleDonnee.getText());

                pan.removeAll();
                pan.add(new JLabel("Nouveau bloque miné"),BorderLayout.NORTH);
                pan.add(new AffichageBloque(nouveauBloque,1),BorderLayout.CENTER);
                JButton retour=new JButton("Retour");
                retour.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        close();
                    }
                });
                pan.add(retour,BorderLayout.PAGE_END);
                raffraichir(pan);
            }
        } );

        pan.add(inputNouvelleDonnee);
        pan.add(ajouterBoutton);
        this.setContentPane(pan);
        this.setVisible(true);


    }

    private void raffraichir(JPanel pan) {
        this.setContentPane(pan);
        this.repaint();
    }

    private void close() {
    this.dispose();
    }
}
