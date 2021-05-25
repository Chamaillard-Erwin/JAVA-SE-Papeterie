package fr.eni.papeterie.bo;

public class Ramette extends Article{

    //Attributs de l'instance
    private int grammage;

    //-------------------CONSTRUCTEURS--------------------------------------------------
    public Ramette() {
        super();
    }

    public Ramette(int idArticle, String marque, String ref, String designation, float pu, int qte, int grammage) {
        super(idArticle, marque, ref, designation, pu, qte);
        this.grammage = grammage;
    }

    public Ramette(String marque, String ref, String designation, float pu, int qte, int grammage) {
        super(marque, ref, designation, pu, qte);
        this.grammage = grammage;
    }

    //-------------------GETTERS--------------------------------------------------

    public int getGrammage() {
        return grammage;
    }

    //-------------------SETTERS--------------------------------------------------

    public void setGrammage(int grammage) {
        this.grammage = grammage;
    }

    /**
     * Méthode d'affichage pour les détails d'une Ramette
     * @return
     */
    @Override
    public String toString() {
        return "Ramette{" +
                "grammage=" + grammage +
                '}';
    }
}
