package fr.eni.papeterie.bo;

public class Ligne extends Article{

    //Attributs de l'instance
    protected int qte;
    protected Article article;

    //-------------------CONSTRUCTEURS--------------------------------------------------

    public Ligne() {

    }

    public Ligne(Article article, int qte) {
        this.article = article;
        this.qte = qte;
    }

    //-------------------GETTERS--------------------------------------------------
    public Article getArticle() {
        return this.article;
    }

    public int getQte() {
        return qte;
    }

    public float getPrix() {
        return article.getPrixUnitaire();
    }

    //-------------------SETTERS--------------------------------------------------
    private void setArticle(Article article) {
        this.article = article;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    /**
     * Méthode d'affichage d'une Ligne de Panier
     * @return
     */
    @Override
    public String toString() {
        return "Ligne{" +
                "qte=" + qte +
                ", article=" + article +
                '}';
    }
}
