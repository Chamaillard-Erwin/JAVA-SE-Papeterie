/**
 * Classe de méthodes (Couche DAL)
 * Contient des méthodes avec requete SQL
 * @author echamaillard
 */

package fr.eni.papeterie.dal.jdbc;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDAOJdbcImpl implements ArticleDAO{

    //Les requetes SQL préparés
    private final String SQL_SELECT_ALL ="SELECT idArticle, reference, marque, designation, prixUnitaire, qteStock, grammage, couleur, type FROM Articles;";
    private final String SQL_INSERT ="INSERT INTO Articles (reference, marque, designation, prixUnitaire, qteStock, grammage, couleur, type) VALUES( ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String SQL_UPDATE ="UPDATE Articles SET reference=?, marque=?, designation=?, prixUnitaire=?, qteStock=?, grammage=?, couleur=?, type=? WHERE idArticle=?;";
    private final String SQL_SELECT_BY_ID ="SELECT idArticle, reference, marque, designation, prixUnitaire, qteStock, grammage, couleur, type FROM Articles WHERE idArticle =?";
    private final String SQL_DELETE_BY_ID ="DELETE FROM Articles WHERE idArticle =?;";

    //----------------------------------------------------------------------------------------------------------------//

    /**
     * Méthode pour récupérer toute les données de la Table Articles
     */
    @Override
    public List<Article> selectAll () throws DALException {

        List<Article> articleList = new ArrayList<>();

        try(PreparedStatement statPrepa = JdbcTools.recupConnection().prepareStatement(SQL_SELECT_ALL)) {

            ResultSet rs = statPrepa.executeQuery();
            while (rs.next()) {
                if (rs.getString("couleur" ) == null) {
                    articleList.add(new Ramette(
                            rs.getInt("idArticle"),
                            rs.getString("marque"),
                            rs.getString("reference"),
                            rs.getString("designation"),
                            rs.getFloat("prixUnitaire"),
                            rs.getInt("qteStock"),
                            rs.getInt("grammage")));

                }
                else if (rs.getString("grammage") == null) {
                    articleList.add(new Stylo(
                            rs.getInt("idArticle"),
                            rs.getString("marque"),
                            rs.getString("reference"),
                            rs.getString("designation"),
                            rs.getFloat("prixUnitaire"),
                            rs.getInt("qteStock"),
                            rs.getString("couleur")));
                }
            }
        }
        catch(SQLException throwables) {
            throwables.printStackTrace();
            throw new DALException();
        }
        return articleList;
    }

    //----------------------------------------------------------------------------------------------------------------//

    /**
     * Méthode qui récupère tout les articles en fonction d'un idArticle
     * @param p_idArticle
     */
    @Override
    public Article selectById (Integer p_idArticle) throws DALException {

        Article article = null;

        try (PreparedStatement statPrepa = JdbcTools.recupConnection().prepareStatement(SQL_SELECT_BY_ID)) {

            statPrepa.setInt(1, p_idArticle);
            ResultSet rs = statPrepa.executeQuery();

            if (rs.next()) {
                if (rs.getString("type" ).trim().equalsIgnoreCase("RAMETTE")) {
                    article = new Ramette(
                            p_idArticle,
                            rs.getString("marque"),
                            rs.getString("reference"),
                            rs.getString("designation"),
                            rs.getFloat("prixUnitaire"),
                            rs.getInt("qteStock"),
                            rs.getInt("grammage"));

                }
                else if (rs.getString("type").trim().equalsIgnoreCase("STYLO")) {
                    article = new Stylo(
                            p_idArticle,
                            rs.getString("marque"),
                            rs.getString("reference"),
                            rs.getString("designation"),
                            rs.getFloat("prixUnitaire"),
                            rs.getInt("qteStock"),
                            rs.getString("couleur"));
                }
            }
        }
        catch(SQLException throwables) {
            throwables.printStackTrace();
            throw new DALException();
        }

        return article;
    }

    //----------------------------------------------------------------------------------------------------------------//

    /**
     * Méthode qui modifie les attributs d'un article
     * @param article
     */
    @Override
    public void update(Article article) throws DALException {

        try (PreparedStatement statPrepa = JdbcTools.recupConnection().prepareStatement(SQL_UPDATE)) {

            statPrepa.setString(1, article.getReference());
            statPrepa.setString(2, article.getMarque());
            statPrepa.setString(3, article.getDesignation());
            statPrepa.setFloat(4, article.getPrixUnitaire());
            statPrepa.setInt(5, article.getQteStock());
            statPrepa.setInt(9, article.getIdArticle());

            if (article instanceof Stylo) {
                statPrepa.setString(7, ((Stylo) article).getCouleur());
                statPrepa.setString(8, "Stylo");
            }
            else if (article instanceof Ramette){
                statPrepa.setInt(6, ((Ramette) article).getGrammage());
                statPrepa.setString(8, "Ramette");
            }

            statPrepa.executeUpdate();
        }
        catch(SQLException throwables) {
            throwables.printStackTrace();
            throw new DALException();
        }
    }

    //----------------------------------------------------------------------------------------------------------------//

    /**
     * Méthode qui insert un nouvel article dans la BDD
     * @param article
     */
    @Override
    public void insert(Article article) throws DALException {

        try (PreparedStatement statPrepa = JdbcTools.recupConnection().prepareStatement(SQL_INSERT)){

            statPrepa.setString(1, article.getReference());
            statPrepa.setString(2, article.getMarque());
            statPrepa.setString(3, article.getDesignation());
            statPrepa.setFloat(4, article.getPrixUnitaire());
            statPrepa.setInt(5, article.getQteStock());

            if (article instanceof Stylo) {
                statPrepa.setString(7, ((Stylo)article).getCouleur());
                statPrepa.setString(8, "Stylo");

            }
            else if (article instanceof Ramette){
                statPrepa.setInt(6, ((Ramette)article).getGrammage());
                statPrepa.setString(8, "Ramette");

            }
            statPrepa.executeUpdate();
            ResultSet rs = statPrepa.getGeneratedKeys();
            if (rs.next()) {
                article.setIdArticle(rs.getInt(1)); //On attribut
            }
        }

        catch(SQLException throwables) {
            throwables.printStackTrace();
            throw new DALException();
        }
    }

    //----------------------------------------------------------------------------------------------------------------//

    /**
     * Méthode pour supprimer une ligne de la Table Articles en fonction de l'id
     * @param id
     */
    @Override
    public void delete(int id) throws DALException {

        try(PreparedStatement statPrepa = JdbcTools.recupConnection().prepareStatement(SQL_DELETE_BY_ID)) {

            statPrepa.setInt(1, id);
            statPrepa.executeUpdate();
        }
        catch(SQLException throwables) {
            throwables.printStackTrace();
            throw new DALException();
        }
    }


}
