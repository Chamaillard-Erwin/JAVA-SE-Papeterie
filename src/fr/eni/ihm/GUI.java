package fr.eni.ihm;

import fr.eni.bll.CatalogueManager;
import fr.eni.exception.BLLException;
import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Couleurs;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;

import javax.lang.model.util.ElementScanner6;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

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
    private JLabel l_error;

    //Textfield
    private JTextField tf_ref;
    private JTextField tf_designation;
    private JTextField tf_marque;
    private JTextField tf_stock;
    private JTextField tf_prix;

    //Boutons du bas
    private JButton b_precedentArticle;
    private JButton b_newArticle;
    private JButton b_modifierArticle;
    private JButton b_removeArticle;
    private JButton b_suivantArticle;

    //Icones des coutons du bas
    private ImageIcon ic_back = getImageIcon("Back24.gif");
    private ImageIcon ic_new = getImageIcon("New24.gif");
    private ImageIcon ic_save = getImageIcon("Save24.gif");
    private ImageIcon ic_delete = getImageIcon("Delete24.gif");
    private ImageIcon ic_next = getImageIcon("Forward24.gif");
    //Types
    private ButtonGroup bg_type;
    private JRadioButton jr_Stylo;
    private JRadioButton jr_Ramette;
    //Grammage
    private ButtonGroup bg_grammage;
    private JCheckBox jc_80;
    private JCheckBox jc_100;
    private final int C_80GR = 80;
    private final int C_100GR = 100;
    private int v_grammage =0;
    //Couleur
    private JComboBox b_couleurs;
    private Couleurs[] tab_couleurs = Couleurs.values();
    //Catalogue Manager
    private CatalogueManager cm = CatalogueManager.getInstance();
    private Integer indexNav = 0;

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
        //TODO VOIR POUR RECUPERER L'ID MIN ET MAX ET CALIBRER NAVINDEX EN FONCTION

    }


    //PANNEAU PRINCIPAL
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
    //PANNEAU DU HAUT
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
            gbc.gridx = 0;
            gbc.gridy = 10;
            gbc.insets.set(0,15,0,0);
            paneHaut.add(getL_error(), gbc);
            /*---------------------------COLONNE 1--------------------------------------------------------*/
            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.weightx = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets.set(5,15,5,15);
            paneHaut.add(getTF_ref(), gbc);
            gbc.gridx = 1;
            gbc.gridy = 1;
            gbc.weightx = 1;
            paneHaut.add(getTF_designation(), gbc);
            gbc.gridx = 1;
            gbc.gridy = 2;
            gbc.weightx = 1;
            paneHaut.add(getTF_marque(), gbc);
            gbc.gridx = 1;
            gbc.gridy = 3;
            gbc.weightx = 1;
            paneHaut.add(getTF_stock(), gbc);
            gbc.gridx = 1;
            gbc.gridy = 4;
            gbc.weightx = 1;
            paneHaut.add(getTF_prix(), gbc);
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
            paneHaut.add(getB_couleurs(), gbc);
        }
        return paneHaut;
    }
    //PANNEAU DU BAS
    public JPanel getPaneBas() {
        if(paneBas == null) {
            paneBas = new JPanel();
            paneBas.setBackground(new Color(92,89,83));
            paneBas.setLayout(new FlowLayout());
            paneBas.add(getB_precedentArticle()); //J'ajoute le bouton au panneau principal
            paneBas.add(getB_newArticle());
            paneBas.add(getB_modifierArticle());
            paneBas.add(getB_removeArticle());
            paneBas.add(getB_suivantArticle());

        }
        return paneBas;
    }
    //PANNEAU POUR LE TYPE D'ARTICLE
    public JPanel getPaneType() {
        if (paneType == null) {
            paneType = new JPanel();
            paneType.setLayout(new BorderLayout());
            //CREATION DES BOUTONS RADIO
            paneType.add(getJRB_Ramette(), BorderLayout.PAGE_START);
            paneType.add(getJRB_Stylo(), BorderLayout.PAGE_END);
            //CREATION D'UN GROUPE DE BOUTON + DEBUG
            bg_type = new ButtonGroup();
            bg_type.add(getJRB_Ramette());
            bg_type.add(getJRB_Stylo());
        }
        return paneType;
    }
    //PANNEAU POUR LE CHOIX DU GRAMMAGE
    public JPanel getPaneGrammage() {
        if (paneGrammage == null) {
            paneGrammage = new JPanel();
            paneGrammage.setLayout(new BorderLayout());
            paneGrammage.add(getJCB_80gr(), BorderLayout.PAGE_START);
            paneGrammage.add(getJCB_100gr(), BorderLayout.PAGE_END);
            bg_grammage = new ButtonGroup();
            bg_grammage.add(getJCB_80gr());
            bg_grammage.add(getJCB_100gr());
        }
        return paneGrammage;
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
     * Méthode singleton qui crée un JCheckBox en fonction d'un texte
     * @return
     */
    public JCheckBox getJCB_80gr() {
        if (jc_80 == null) {
            jc_80 = new JCheckBox();
            jc_80.setText("80 Grammes");
            jc_80.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    v_grammage = C_80GR;
                }
            });
        }
        return jc_80;
    }

    public JCheckBox getJCB_100gr() {
        if (jc_100 == null) {
            jc_100 = new JCheckBox();
            jc_100.setText("100 Grammes");
            jc_100.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    v_grammage = C_100GR;
                }
            });
        }
        return jc_100;
    }



    /**
     * Méthode singleton qui créer une Liste déroulante en fonction d'une liste de mot
     * @return
     */
    public JComboBox getB_couleurs() {
        if (b_couleurs == null) {
            b_couleurs = new JComboBox(tab_couleurs);
            b_couleurs.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });
        }
        return b_couleurs;
    }

    /**
     * Méthode singleton qui créé des JRadioButton en fonction d'un texte
     * @return
     */
    public JRadioButton getJRB_Ramette() {
        if (jr_Ramette == null) {
            jr_Ramette = new JRadioButton();
            jr_Ramette.setText("Ramette");
            jr_Ramette.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    b_couleurs.setEnabled(false);
                    jc_80.setEnabled(true);
                    jc_100.setEnabled(true);
                }
            });
        }
        return jr_Ramette;
    }


    public JRadioButton getJRB_Stylo() {
        if (jr_Stylo == null) {
            jr_Stylo = new JRadioButton();
            jr_Stylo.setText("Stylo");
            jr_Stylo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    b_couleurs.setEnabled(true);
                    bg_grammage.clearSelection();
                    jc_80.setEnabled(false);
                    jc_100.setEnabled(false);
                }
            });

        }
        return jr_Stylo;
    }

    public JButton getB_suivantArticle() {
        if (b_suivantArticle == null) {
            b_suivantArticle = new JButton(ic_next);
            b_suivantArticle.setToolTipText("Article suivant");
            b_suivantArticle.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    indexNav++;
                    try {
                        System.err.println(cm.getCatalogue().size());
                        if (indexNav >= cm.getCatalogue().size()) {
                            indexNav = cm.getCatalogue().size();
                        }
                        System.err.println(indexNav);
                        if (cm.getArticle(indexNav) != null) {
                            tf_designation.setText(cm.getArticle(indexNav).getDesignation());
                            tf_marque.setText(cm.getArticle(indexNav).getMarque());
                            tf_prix.setText(NumberFormat.getNumberInstance().format(cm.getArticle(indexNav).getPrixUnitaire()));
                            tf_ref.setText(cm.getArticle(indexNav).getReference());
                            tf_stock.setText(NumberFormat.getNumberInstance().format(cm.getArticle(indexNav).getQteStock()));

                            if (cm.getArticle(indexNav) instanceof Stylo) {
                                jr_Stylo.setSelected(true);
                                bg_grammage.clearSelection();
                                jc_80.setEnabled(false);
                                jc_100.setEnabled(false);
                                b_couleurs.setEnabled(true);
                                switch (((Stylo) cm.getArticle(indexNav)).getCouleur()) {
                                    case "jaune" : b_couleurs.setSelectedItem(Couleurs.JAUNE); break;
                                    case "vert" : b_couleurs.setSelectedItem(Couleurs.VERT); break;
                                    case "rouge" : b_couleurs.setSelectedItem(Couleurs.ROUGE); break;
                                    case "noir" : b_couleurs.setSelectedItem(Couleurs.NOIR); break;
                                    case "bleu" : b_couleurs.setSelectedItem(Couleurs.BLEU); break;
                                }
                            }
                            else if (cm.getArticle(indexNav) instanceof Ramette) {
                                jr_Ramette.setSelected(true);
                                b_couleurs.setEnabled(false);
                                jc_80.setEnabled(true);
                                jc_100.setEnabled(true);
                                switch (((Ramette) cm.getArticle(indexNav)).getGrammage()) {
                                    case 80 : jc_80.setSelected(true); break;
                                    case 100 : jc_100.setSelected(true); break;
                                }
                            }
                            l_error.setEnabled(false);
                            l_error.setText("");
                        }
                        else {
                            l_error.setEnabled(true);
                            l_error.setText("ARTICLE INCONNU");
                            tf_designation.setText("");
                            tf_marque.setText("");
                            tf_prix.setText("");
                            tf_ref.setText("");
                            tf_stock.setText("");
                            bg_grammage.clearSelection();
                            bg_type.clearSelection();
                            b_couleurs.setEnabled(true);
                        }

                    } catch (BLLException bllException) {
                        bllException.printStackTrace();

                    }
                }
            });
        }
        return b_suivantArticle;
    }

    public JButton getB_modifierArticle() {
        if (b_modifierArticle == null) {
            b_modifierArticle = new JButton(ic_save);
            b_modifierArticle.setToolTipText("Modifier article");
        }
        return b_modifierArticle;
    }

    public JButton getB_removeArticle() {
        if (b_removeArticle == null) {
            b_removeArticle = new JButton(ic_delete);
            b_removeArticle.setToolTipText("Supprimer article");
        }
        b_removeArticle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (cm.getArticle(indexNav) != null) {
                        cm.removeArticle(indexNav);
                        l_error.setEnabled(true);
                        l_error.setText("ARTICLE INCONNU");
                        tf_designation.setText("");
                        tf_marque.setText("");
                        tf_prix.setText("");
                        tf_ref.setText("");
                        tf_stock.setText("");
                        bg_grammage.clearSelection();
                        bg_type.clearSelection();
                        b_couleurs.setEnabled(true);
                    }
                    else {
                        l_error.setEnabled(true);
                        l_error.setText("ARTICLE DEJA SUPPRIME");
                    }

                } catch (BLLException bllException) {
                    bllException.printStackTrace();
                }
            }
        });
        return b_removeArticle;
    }

    public JButton getB_precedentArticle() {
        if (b_precedentArticle == null) {
            b_precedentArticle = new JButton(ic_back);
            b_precedentArticle.setToolTipText("Article précédent");
            b_precedentArticle.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    indexNav--;
                    try {
                        if (indexNav < 0) {
                            indexNav = 0;
                        }
                        System.err.println(indexNav);
                        if (cm.getArticle(indexNav) != null) {
                            tf_designation.setText(cm.getArticle(indexNav).getDesignation());
                            tf_marque.setText(cm.getArticle(indexNav).getMarque());
                            tf_prix.setText(NumberFormat.getNumberInstance().format(cm.getArticle(indexNav).getPrixUnitaire()));
                            tf_ref.setText(cm.getArticle(indexNav).getReference());
                            tf_stock.setText(NumberFormat.getNumberInstance().format(cm.getArticle(indexNav).getQteStock()));

                            if (cm.getArticle(indexNav) instanceof Stylo) {
                                jr_Stylo.setSelected(true);
                                bg_grammage.clearSelection();
                                jc_80.setEnabled(false);
                                jc_100.setEnabled(false);
                                b_couleurs.setEnabled(true);
                                switch (((Stylo) cm.getArticle(indexNav)).getCouleur()) {
                                    case "jaune" : b_couleurs.setSelectedItem(Couleurs.JAUNE); break;
                                    case "vert" : b_couleurs.setSelectedItem(Couleurs.VERT); break;
                                    case "rouge" : b_couleurs.setSelectedItem(Couleurs.ROUGE); break;
                                    case "noir" : b_couleurs.setSelectedItem(Couleurs.NOIR); break;
                                    case "bleu" : b_couleurs.setSelectedItem(Couleurs.BLEU); break;
                                }
                            }
                            else if (cm.getArticle(indexNav) instanceof Ramette) {
                                jr_Ramette.setSelected(true);
                                b_couleurs.setEnabled(false);
                                jc_80.setEnabled(true);
                                jc_100.setEnabled(true);
                                switch (((Ramette) cm.getArticle(indexNav)).getGrammage()) {
                                    case 80 : jc_80.setSelected(true); break;
                                    case 100 : jc_100.setSelected(true); break;
                                }
                            }
                            l_error.setEnabled(false);
                            l_error.setText("");

                        }
                        else {
                            l_error.setEnabled(true);
                            l_error.setText("ARTICLE INCONNU");
                            tf_designation.setText("");
                            tf_marque.setText("");
                            tf_prix.setText("");
                            tf_ref.setText("");
                            tf_stock.setText("");
                            bg_grammage.clearSelection();
                            bg_type.clearSelection();
                            b_couleurs.setEnabled(true);
                        }

                    } catch (BLLException bllException) {
                        bllException.printStackTrace();
                    }
                }
            });
        }
        return b_precedentArticle;
    }

    public JButton getB_newArticle() {
        if (b_newArticle == null) {
            b_newArticle = new JButton(ic_new);
            b_newArticle.setToolTipText("Insérer l'article");
            b_newArticle.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Article article = null;
                        float f = Float.parseFloat(tf_prix.getText().trim());
                        int i = Integer.parseInt(tf_stock.getText().trim());
                        if (jr_Ramette.isSelected()) {
                            article = new Ramette(tf_marque.getText(), tf_ref.getText(), tf_designation.getText(), f, i, v_grammage);
                        }
                        else if (jr_Stylo.isSelected()) {
                            article = new Stylo(tf_marque.getText(), tf_ref.getText(), tf_designation.getText(), f, i, b_couleurs.getSelectedItem().toString());

                        }
                        cm.addArticle(article);
                    } catch (BLLException bllException) {
                        bllException.printStackTrace();
                    }
                }
            });
        }
        return b_newArticle;
    }

    public JTextField getTF_prix() {
        if(tf_prix == null) {
            tf_prix = new JTextField();
            tf_prix.setForeground(new Color(255, 255, 255));
            tf_prix.setBackground(new Color(77,75,69));
        }
        return tf_prix;
    }

    public JTextField getTF_stock() {
        if(tf_stock == null) {
            tf_stock = new JTextField();
            tf_stock.setForeground(new Color(255, 255, 255));
            tf_stock.setBackground(new Color(77,75,69));
        }
        return tf_stock;
    }

    public JTextField getTF_marque() {
        if(tf_marque == null) {
            tf_marque = new JTextField();
            tf_marque.setForeground(new Color(255, 255, 255));
            tf_marque.setBackground(new Color(77,75,69));
        }
        return tf_marque;
    }

    public JTextField getTF_designation() {
        if(tf_designation == null) {
            tf_designation = new JTextField();
            tf_designation.setForeground(new Color(255, 255, 255));
            tf_designation.setBackground(new Color(77,75,69));
        }
        return tf_designation;
    }

    public JTextField getTF_ref() {
        if(tf_ref == null) {
            tf_ref = new JTextField();
            tf_ref.setForeground(new Color(255, 255, 255));
            tf_ref.setBackground(new Color(77,75,69));
        }
        return tf_ref;
    }

    public JLabel getL_error() {
        if(l_error == null) {
            l_error = new JLabel();
            l_error.setForeground(new Color(240, 0, 0));
            l_error.setEnabled(false);
        }
        return l_error;
    }

}
