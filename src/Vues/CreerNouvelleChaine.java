package Vues;
import RMI.ClientRMI;
import Structure.Bloque;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreerNouvelleChaine extends JFrame {
    public  CreerNouvelleChaine(ClientRMI clientRMI){
        this.setTitle("Création d'une nouvelle chaine de bloques");
        this.setSize(640,480);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JPanel pan=new JPanel();
        JLabel labelCreation=new JLabel("Introduire la donneé du premier bloque");
        JLabel avertissementVide=new JLabel("Veuillez introduire des données .. ne laissez pas ce champ vide");
        JTextField dataBlockInitial=new JTextField(25);

        labelCreation.setPreferredSize(new Dimension(400,100));
        labelCreation.setFont(new Font("Arial", Font.PLAIN, 20));


        JButton bouttonCreer=new JButton("Créer");

        bouttonCreer.setPreferredSize(new Dimension(120,70));
        bouttonCreer.setFont(new Font("Arial", Font.PLAIN, 14));
        bouttonCreer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                if(dataBlockInitial.getText().length()==0){
                    avertissementVide.setVisible(true);
                    return;
                }
                Bloque premierBloque=clientRMI.creerChaine(dataBlockInitial.getText());
                pan.removeAll();
                pan.add(new JLabel("Nouveau bloque miné"),BorderLayout.NORTH);
                pan.add(new AffichageBloque(premierBloque,1),BorderLayout.CENTER);
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

        pan.setLayout(new BorderLayout());
        pan.add(labelCreation,BorderLayout.NORTH);
        pan.add(dataBlockInitial,BorderLayout.CENTER);
        pan.add(avertissementVide,BorderLayout.SOUTH);
        avertissementVide.setVisible(false);
        pan.add(bouttonCreer,BorderLayout.PAGE_END);
        this.setContentPane(pan);
        this.setVisible(true);

    }

    private void raffraichir(JPanel panel) {
       setContentPane(panel);
        repaint();
    }
    private void close(){
        this.dispose();
    }
}
