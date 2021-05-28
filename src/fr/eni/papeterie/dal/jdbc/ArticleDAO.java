package fr.eni.papeterie.dal.jdbc;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface ArticleDAO {

    List<Article> selectAll () throws DALException;

    //----------------------------------------------------------------------------------------------------------------//

    /**
     * Méthode qui récupère tout les articles en fonction d'un idArticle
     * @param p_idArticle
     */
    Article selectById (Integer p_idArticle) throws DALException;

    //----------------------------------------------------------------------------------------------------------------//

    /**
     * Méthode qui modifie les attributs d'un article
     * @param article
     */
    void update(Article article) throws DALException;

    //----------------------------------------------------------------------------------------------------------------//

    /**
     * Méthode qui insert un nouvel article dans la BDD
     * @param article
     */
    void insert(Article article) throws DALException;

    //----------------------------------------------------------------------------------------------------------------//

    /**
     * Méthode pour supprimer une ligne de la Table Articles en fonction de l'id
     * @param id
     */
    void delete(int id) throws DALException;

}
