package fr.eni.papeterie.bo;

public class Stylo extends Article{
    //Attributs
    private String couleur;

    //-------------------CONSTRUCTEURS--------------------------------------------------
    public Stylo() {
        super();
    }

    public Stylo(int idArticle, String marque, String ref, String designation, float pu, int qte, String couleur) {
        super(idArticle, marque, ref, designation, pu, qte);
        this.couleur = couleur;
    }

    public Stylo(String marque, String ref, String designation, float pu, int qte, String couleur) {
        super(marque, ref, designation, pu, qte);
        this.couleur = couleur;
    }

    //-------------------GETTERS--------------------------------------------------

    public String getCouleur() {
        return couleur;
    }

    //-------------------SETTERS--------------------------------------------------
    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }


    /**
     * Méthode d'affichage des détails d'un Stylo
     * @return
     */
    @Override
    public String toString() {
        return "Stylo{" +
                "couleur='" + couleur + '\'' +
                '}';
    }
}
