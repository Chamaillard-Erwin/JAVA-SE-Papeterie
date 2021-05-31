package fr.eni.dal;

import fr.eni.papeterie.dal.jdbc.ArticleDAO;
import fr.eni.papeterie.dal.jdbc.ArticleDAOJdbcImpl;

public class DAOFactory {

    public static ArticleDAO getArticleDAO() {
        return new ArticleDAOJdbcImpl();
    }

}
