package fr.eni.papeterie.bo;

public abstract class Article {

    //Attributs d'instances
    private int idArticle;
    private String reference;
    private String marque;
    private String designation;
    private float prixUnitaire;
    private int qteStock;

    //-------------------CONSTRUCTEURS--------------------------------------------------
    public Article(int idArticle, String marque, String ref, String designation, float pu, int qte) {
        this.idArticle = idArticle;
        this.marque = marque;
        this.reference = ref;
        this.designation = designation;
        this.prixUnitaire = pu;
        this.qteStock = qte;
    }

    public Article(String marque, String ref, String designation, float pu, int qte) {
        this.reference = ref;
        this.marque = marque;
        this.designation = designation;
        this.prixUnitaire = pu;
        this.qteStock = qte;
    }

    public Article() {

    }

    /**
     * Méthode pour afficher les détails de l'article
     * @return
     */
    @Override
    public String toString() {
        return "Article{" +
                "idArticle=" + idArticle +
                ", reference='" + reference + '\'' +
                ", marque='" + marque + '\'' +
                ", designation='" + designation + '\'' +
                ", prixUnitaire=" + prixUnitaire +
                ", qteStock=" + qteStock +
                '}';
    }



    //-------------------SETTERS--------------------------------------------------
    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setPrixUnitaire(float prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public void setQteStock(int qteStock) {
        this.qteStock = qteStock;
    }


    //-------------------GETTERS--------------------------------------------------
    public int getIdArticle() {
        return idArticle;
    }

    public String getReference() {
        return reference;
    }

    public String getMarque() {
        return marque;
    }

    public String getDesignation() {
        return designation;
    }

    public float getPrixUnitaire() {
        return prixUnitaire;
    }

    public int getQteStock() {
        return qteStock;
    }

}
