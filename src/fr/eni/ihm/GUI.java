package fr.eni.ihm;

import fr.eni.papeterie.bo.Couleurs;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

    private JPanel panePrincipal;
    private JPanel paneHaut;
    private JPanel paneBas;
    private JPanel paneType;
    private JPanel paneGrammage;
    //Labels
    private JLabel l_ref;
    private JLabel l_designation;
    private JLabel l_marque;
    private JLabel l_stock;
    private JLabel l_prix;
    private JLabel l_type;
    private JLabel l_grammage;
    private JLabel l_Couleur;

    //Textfield
    private JTextField tf_ref;
    private JTextField tf_designation;
    private JTextField tf_marque;
    private JTextField tf_stock;
    private JTextField tf_prix;


    //Boutons du bas
    private JButton b_precedentArticle;
    private JButton b_newArticle;
    private JButton b_validerArticle;
    private JButton b_removeArticle;
    private JButton b_suivantArticle;

    //Icones des coutons du bas
    private ImageIcon ic_back = getImageIcon("Back24.gif");
    private ImageIcon ic_new = getImageIcon("New24.gif");
    private ImageIcon ic_save = getImageIcon("Save24.gif");
    private ImageIcon ic_delete = getImageIcon("Delete24.gif");
    private ImageIcon ic_next = getImageIcon("Forward24.gif");
    //Types
    //private ButtonGroup bg_type;
    private JRadioButton jr_Stylo;
    private JRadioButton jr_Ramette;
    //Grammage
    //private ButtonGroup bg_grammage;
    private JCheckBox jc_80;
    private JCheckBox jc_100;
    //Couleur
    private JComboBox b_couleurs;
    private Couleurs[] tab_couleurs = Couleurs.values();

    /**
     * Constructeur de la fenêtre
     */
    public GUI() {
        //Création du support en bois
        this.setTitle("IHM");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setSize(new Dimension(400, 400));
        //this.pack(); //Permet de reduire au mieux la fenetre
        this.setContentPane(getPanePrincipal()); //Je colle le panneau principal au support en bois
        this.setVisible(true);
    }


    //Les différents panneaux
    public JPanel getPanePrincipal() {
        if(panePrincipal == null) {
            panePrincipal = new JPanel();
            panePrincipal.setBackground(new Color(92,89,83));
            panePrincipal.setLayout(new BorderLayout());
            panePrincipal.add(getPaneHaut(), BorderLayout.PAGE_START);
            panePrincipal.add(getPaneBas(), BorderLayout.PAGE_END);

        }
        return panePrincipal;
    }

    public JPanel getPaneHaut() {
        if(paneHaut == null) {
            paneHaut = new JPanel();
            paneHaut.setLayout(new GridBagLayout());
            paneHaut.setBackground(new Color(92,89,83));
            GridBagConstraints gbc = new GridBagConstraints(); //On crée une contrainte
            /*---------------------------COLONNE 0--------------------------------------------------------*/
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.insets = new Insets(0,15,0,0);
            paneHaut.add(getJLabel(l_ref, "Référence"), gbc);
            gbc.gridx = 0;
            gbc.gridy = 1;
            paneHaut.add(getJLabel(l_designation, "Désignation"), gbc);
            gbc.gridx = 0;
            gbc.gridy = 2;
            paneHaut.add(getJLabel(l_marque, "Marque"), gbc);
            gbc.gridx = 0;
            gbc.gridy = 3;
            paneHaut.add(getJLabel(l_stock, "Stock"), gbc);
            gbc.gridx = 0;
            gbc.gridy = 4;
            paneHaut.add(getJLabel(l_prix, "Prix"), gbc);
            gbc.gridx = 0;
            gbc.gridy = 5;
            paneHaut.add(getJLabel(l_type, "Type"), gbc);
            gbc.gridx = 0;
            gbc.gridy = 6;
            paneHaut.add(getJLabel(l_grammage, "Grammage"), gbc);
            gbc.gridx = 0;
            gbc.gridy = 9;
            gbc.insets.set(0,15,0,0);
            paneHaut.add(getJLabel(l_Couleur, "Couleur"), gbc);
            /*---------------------------COLONNE 1--------------------------------------------------------*/
            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.weightx = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets.set(5,15,5,15);
            paneHaut.add(getJTextField(tf_ref), gbc);
            gbc.gridx = 1;
            gbc.gridy = 1;
            gbc.weightx = 1;
            paneHaut.add(getJTextField(tf_designation), gbc);
            gbc.gridx = 1;
            gbc.gridy = 2;
            gbc.weightx = 1;
            paneHaut.add(getJTextField(tf_marque), gbc);
            gbc.gridx = 1;
            gbc.gridy = 3;
            gbc.weightx = 1;
            paneHaut.add(getJTextField(tf_stock), gbc);
            gbc.gridx = 1;
            gbc.gridy = 4;
            gbc.weightx = 1;
            paneHaut.add(getJTextField(tf_prix), gbc);
            gbc.gridx = 1;
            gbc.gridy = 5;
            /*---------------------------COLONNE 1--------------------------------------------------------*/
            gbc.fill = GridBagConstraints.CENTER;
            paneHaut.add(getPaneType(), gbc);
            gbc.gridx = 1;
            gbc.gridy = 6;
            paneHaut.add(getPaneGrammage(),gbc);
            gbc.gridx = 1;
            gbc.gridy = 9;
            paneHaut.add(getB_couleurs(b_couleurs, tab_couleurs), gbc);
        }
        return paneHaut;
    }

    public JPanel getPaneType() {
        if (paneType == null) {
            paneType = new JPanel();
            paneType.setLayout(new BorderLayout());
            paneType.add(getJRadioButton(jr_Ramette, "Ramette"), BorderLayout.PAGE_START);
            paneType.add(getJRadioButton(jr_Stylo, "Stylo"), BorderLayout.PAGE_END);
            ButtonGroup bg_type = new ButtonGroup();
            bg_type.add(jr_Ramette);
            bg_type.add(jr_Stylo);
        }
        return paneType;
    }

    public JPanel getPaneGrammage() {
        if (paneGrammage == null) {
            paneGrammage = new JPanel();
            paneGrammage.setLayout(new BorderLayout());
            paneGrammage.add(getJCheckBox(jc_80, "80 Grammes"), BorderLayout.PAGE_START);
            paneGrammage.add(getJCheckBox(jc_100, "100 Grammes"), BorderLayout.PAGE_END);
            ButtonGroup bg_grammage = new ButtonGroup();
            bg_grammage.add(jc_80);
            bg_grammage.add(jc_100);
        }
        return paneGrammage;
    }

    public JPanel getPaneBas() {
        if(paneBas == null) {
            paneBas = new JPanel();
            paneBas.setBackground(new Color(92,89,83));
            paneBas.setLayout(new FlowLayout());
            paneBas.add(getButton(b_precedentArticle, ic_back, "Article précédent")); //J'ajoute le bouton au panneau principal
            paneBas.add(getButton(b_newArticle, ic_new, "Ajouter article"));
            paneBas.add(getButton(b_validerArticle, ic_save, "Sauvegarder l'article"));
            paneBas.add(getButton(b_removeArticle, ic_delete, "Supprimer l'article"));
            paneBas.add(getButton(b_suivantArticle, ic_next, "Article suivant"));

        }
        return paneBas;
    }

    /**
     * Méthode pour récuperer les icones
     * @param path
     * @return
     */
    public ImageIcon getImageIcon(String path) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        }
        else {
            System.err.println("Icone non trouvé");
            return null;
        }
    }


    /**
     * Méthode Singleton pour la création d'un bouton, en fonction d'une icone et d'un tooltip
     * @param b
     * @param ic
     * @param tooltip
     * @return
     */
    public JButton getButton(JButton b, ImageIcon ic, String tooltip) {
        if (b == null) {
            b = new JButton(ic);
            b.setToolTipText(tooltip);
        }
        return b;
    }

    /**
     * Méthode singleton pour la création d'un Label, en fonction d'un text
     * @param j
     * @param text
     * @return
     */
    public JLabel getJLabel(JLabel j, String text) {
        if (j == null) {
            j = new JLabel(text);
            j.setForeground(new Color(255, 184, 42));
        }
        return j;
    }

    /**
     * Méthode singleton pour la création d'uun JTextField
     * @param jt
     * @return
     */
    public JTextField getJTextField(JTextField jt) {
        if (jt == null) {
            jt = new JTextField();
            jt.setForeground(new Color(255, 255, 255));
            jt.setBackground(new Color(77,75,69));
        }
        return jt;
    }

    /**
     * Méthode singleton qui créé des JRadioButton en fonction d'un texte
     * @param jrb
     * @param text
     * @return
     */
    public JRadioButton getJRadioButton(JRadioButton jrb, String text) {
        if (jrb == null) {
            jrb = new JRadioButton(text);
            jrb.setBackground(new Color(92,89,83));
            jrb.setForeground(new Color(255, 184, 42));
        }
        return jrb;
    }

    /**
     * Méthode singleton qui crée un JCheckBox en fonction d'un texte
     * @param jcb
     * @param text
     * @return
     */
    public JCheckBox getJCheckBox(JCheckBox jcb, String text) {
        if (jcb == null) {
            jcb = new JCheckBox(text);
            jcb.setBackground(new Color(92,89,83));
            jcb.setForeground(new Color(255, 184, 42));
        }
        return jcb;
    }

    /**
     * Méthode singleton qui créer une Liste déroulante en fonction d'une liste de mot
     * @param j
     * @param tab
     * @return
     */
    public JComboBox getB_couleurs(JComboBox j, Couleurs[] tab) {
        if (j == null) {
            j = new JComboBox(tab);
        }
        return j;
    }
}