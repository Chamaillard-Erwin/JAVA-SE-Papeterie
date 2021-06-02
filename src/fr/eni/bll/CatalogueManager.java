/**
 * Classe Singleton BLL qui communique avec les classes DAL
 */

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

    private ArticleDAO articleDAO = DAOFactory.getArticleDAO();

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


    /**
     * Méthode qui demande à la DAL tout les articles
     * @return
     * @throws BLLException
     */
    public List<Article> getCatalogue () throws BLLException {

        List<Article> listArticle;

        try {
            listArticle = articleDAO.selectAll();
        }
        catch(DALException e) {
            throw new BLLException(e.toString());
        }
        return listArticle;
    }

    //----------------------------------------------------------------------------------------------------------------//

    /**
     * Méthode qui demande à la DAL un article selon un id
     * @param p_idArticle
     * @return
     * @throws BLLException
     */
    public Article getArticle (Integer p_idArticle) throws BLLException {

        Article article;

        try {
            article = articleDAO.selectById(p_idArticle);
        }
        catch (DALException e) {
            throw new BLLException(e.toString());
        }

        return article;
    }

    //----------------------------------------------------------------------------------------------------------------//

    /**
     * Méthode qui demande à la DAL une mise à jour d'un article selon un article
     * @param article
     * @throws BLLException
     */
    public void updateArticle(Article article) throws BLLException {

        try {
            validerArticle(article);
            articleDAO.update(article);
        }
        catch (DALException e) {
            throw new BLLException(e.toString());
        }
    }

    //----------------------------------------------------------------------------------------------------------------//

    /**
     * Méthode qui demande à la DAL l'insertion d'un nouvel article
     * @param article
     * @throws BLLException
     */
    public void addArticle(Article article) throws BLLException {

        try{
            validerArticle(article);
            articleDAO.insert(article);
        }
        catch (DALException e) {
            throw new BLLException(e.toString());
        }

    }

    //----------------------------------------------------------------------------------------------------------------//

    /**
     * Méthode qui demande à la DAL de supprimer un article selon un id
     * @param id
     * @throws BLLException
     */
    public void removeArticle(int id) throws BLLException {
        try {
            articleDAO.delete(id);
        }
        catch (DALException e) {
            throw new BLLException(e.toString());
        }

    }

    //----------------------------------------------------------------------------------------------------------------//


    /**
     * Méthode qui vérifie les contraintes lors de l'ajout ou la mise a jour d'un article
     * @param article
     * @throws BLLException
     */
    private void validerArticle(Article article) throws BLLException {
        if (article.getQteStock() <= 0) {
            throw new BLLException("Le stock ne peut pas être négatif");
        }
        if (article instanceof Ramette && ((Ramette)article).getGrammage() <= 0) {
            throw new BLLException("Le grammage de dit pas être null");
        }
        if (article instanceof Stylo && (((Stylo)article).getCouleur() == null || ((Stylo)article).getCouleur().trim().length() == 0)) {
                throw new BLLException("La couleur ne peut pas être null");
        }
        if (article.getReference() == null || article.getMarque() == null || article.getDesignation() == null
        || article.getPrixUnitaire() <= 0) {
            throw new BLLException("Il manque un attribut");
        }
    }

}
