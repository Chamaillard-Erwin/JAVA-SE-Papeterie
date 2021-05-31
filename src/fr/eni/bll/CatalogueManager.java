package fr.eni.bll;

import fr.eni.exception.BLLException;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;
import fr.eni.papeterie.dal.jdbc.ArticleDAO;
import fr.eni.exception.DALException;
import fr.eni.dal.DAOFactory;
import fr.eni.papeterie.bo.Article;

import java.util.List;

public class CatalogueManager {

    ArticleDAO articleDAO = DAOFactory.getArticleDAO();

    //Création d'une instance du type du Manager en privé
    private static CatalogueManager instance;

    //Création d'un constructeur privé
    private CatalogueManager() {

    }

    //Lors de la demande d'instance on vérifie si elle existe si OUI alors on retourne l'instance
    //si NON on en crée une nouvelle

    public static CatalogueManager getInstance() {
        if (instance == null) {
            instance = new CatalogueManager();
        }
        return instance;
    }



    public List<Article> getCatalogue () throws BLLException {

        List<Article> listArticle;

        try {
            listArticle = articleDAO.selectAll();
        }
        catch(DALException e) {
            throw new BLLException();
        }
        return listArticle;
    }

    //----------------------------------------------------------------------------------------------------------------//

    public Article getArticle (Integer p_idArticle) throws BLLException {

        Article article;

        try {
            article = articleDAO.selectById(p_idArticle);
        }
        catch (DALException e) {
            throw new BLLException();
        }

        return article;
    }

    //----------------------------------------------------------------------------------------------------------------//

    public void updateArticle(Article article) throws BLLException {

        try {
            validerArticle(article);
            articleDAO.update(article);
        }
        catch (DALException e) {
            throw new BLLException();
        }
    }

    //----------------------------------------------------------------------------------------------------------------//

    public void addArticle(Article article) throws BLLException {

        try{
            validerArticle(article);
            articleDAO.insert(article);
        }
        catch (DALException e) {
            throw new BLLException();
        }

    }

    //----------------------------------------------------------------------------------------------------------------//

    public void removeArticle(int id) throws BLLException {
        try {
            articleDAO.delete(id);
        }
        catch (DALException e) {
            throw new BLLException();
        }

    }

    //----------------------------------------------------------------------------------------------------------------//


    /**
     * Méthode qui vérifie les contraintes lors de l'ajout ou la mise a jour d'un article
     * @param article
     * @throws BLLException
     */
    public void validerArticle(Article article) throws BLLException {
        if (article.getQteStock() <= 0) {
            throw new BLLException();
        }
        if (article instanceof Ramette) {
            if (((Ramette)article).getGrammage() <= 0) {
                throw new BLLException();
            }
        }
        if (article instanceof Stylo) {
            if (((Stylo)article).getCouleur().equals(null)) {
                throw new BLLException();
            }
        }
        if (article.getReference().equals(null) || article.getMarque().equals(null) || article.getDesignation().equals(null)
        || article.getPrixUnitaire() <= 0) {
            throw new BLLException();
        }
    }

}
