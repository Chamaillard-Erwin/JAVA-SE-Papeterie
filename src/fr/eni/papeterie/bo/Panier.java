package fr.eni.papeterie.bo;

import java.util.ArrayList;
import java.util.List;

public class Panier extends Ligne{

    private float montant;
    private List<Ligne> lignesPanier;

    //-------------------CONSTRUCTEUR--------------------------------------------------
    public Panier() {
        float montant;
        lignesPanier = new ArrayList<Ligne>();
    }

    //-------------------GETTERS--------------------------------------------------

    public float getMontant() {
        return montant;
    }

    public List<Ligne> getlignesPanier() {
        return lignesPanier;
    }

    public Ligne getLigne(int index){
        return lignesPanier.get(index);
    }

    /**
     * Méthode qui ajoute une Ligne à la liste de ligne en fonction d'un article et de sa quantité
     * @param article
     * @param qte
     */
    public void addLigne(Article article, int qte) {
        lignesPanier.add(new Ligne(article, qte));
    }

    /**
     * Méthode qui met à jour la quantité d'une ligne de la liste en fonction de son index
     * @param index
     * @param newQte
     */
    public void updateLigne(int index, int newQte) {
        this.getLigne(index).setQte(newQte);
    }

    /**
     * Méthode qui supprime une ligne en fonction d'un index
     * @param index
     */
    public void removeLigne(int index) {
        lignesPanier.remove(index);
    }

    /**
     * Méthode d'affichage des Lignes du Panier à partir de la liste de Ligne
     * @return
     */
    @Override
    public String toString() {
        return "Panier{" +
                "lignesPanier=" + lignesPanier +
                '}';
    }
}
