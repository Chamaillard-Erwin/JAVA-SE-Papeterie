package fr.eni.papeterie.dal.jdbc;


import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDAOJdbcImpl {

    private final String url = "jdbc:sqlite:identifier.sqlite";

    //----------------------------------------------------------------------------------------------------------------//

    /**
     * Méthode pour récupérer toute les données de la Table Articles
     */
    public List<Article> selectAll () throws DALException {

        List<Article> articleList = new ArrayList<>();

        try {
            String sql ="SELECT idArticle, reference, marque, designation, prixUnitaire, qteStock, grammage, couleur, type FROM Articles;";

            Connection connection = DriverManager.getConnection(this.url);
            Statement etatClassique = connection.createStatement();
            ResultSet rs = etatClassique.executeQuery(sql);
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
    public Article selectById (Integer p_idArticle) throws DALException {

        Article article = null;

        try (
             Connection connection = DriverManager.getConnection(this.url);
             Statement etatClassique = connection.createStatement()) {

            String sql ="SELECT idArticle, reference, marque, designation, prixUnitaire, qteStock, grammage, couleur, type FROM Articles WHERE idArticle = " + p_idArticle + ";";

            ResultSet rs = etatClassique.executeQuery(sql);

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
    public void update(Article article) throws DALException {

        String sql = "";
        String sqlComp = "";
        String sqlFin = " WHERE idArticle = " + article.getIdArticle() + ";";

        try (
                Connection connection = DriverManager.getConnection(this.url);
                Statement etatClassique = connection.createStatement()) {



            if (article instanceof Stylo) {
                sqlComp = ", couleur = '" + ((Stylo) article).getCouleur() + "'";

            }
            else if (article instanceof Ramette){
                sqlComp = ", grammage = '" + ((Ramette)article).getGrammage() + "'";
            }

            sql ="UPDATE Articles SET" +
                    " reference = '" + article.getReference() + "'" +
                    ", marque = '" + article.getMarque() + "'" +
                    ", designation = '" + article.getDesignation() + "'" +
                    ", prixUnitaire = '" + article.getPrixUnitaire() + "'" +
                    ", qteStock = '" + article.getQteStock() + "'"
                    + sqlComp + sqlFin;



            etatClassique.executeUpdate(sql);
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
    public void insert(Article article) throws DALException {

        try (Connection connection = DriverManager.getConnection(this.url);
                Statement etatClassique = connection.createStatement()){

            String sql = "INSERT INTO Articles" +
                    "(reference, marque, designation, prixUnitaire, qteStock, grammage, couleur, type)" +
                    "VALUES ('" +
                    article.getReference() + "'," +
                    article.getMarque() + "', '" +
                    article.getDesignation() + "', '" +
                    article.getPrixUnitaire() + "', '" +
                    article.getQteStock() + "', '";


            String sqlComp = "";


            if (article instanceof Stylo) {
                sqlComp = "'" + ((Stylo)article).getCouleur() + "', 'Stylo');";
                sql ="INSERT INTO Articles (reference, marque, designation, prixUnitaire, qteStock, couleur, type) VALUES (" +
                        "'" + article.getReference() + "', " +
                        "'" + article.getMarque() + "', " +
                        "'" + article.getDesignation() + "', " +
                        "'" + article.getPrixUnitaire() + "', " +
                        "'" + article.getQteStock() + "', "
                        + sqlComp;

            }
            else if (article instanceof Ramette){
                sqlComp = "'" + ((Ramette)article).getGrammage() + "', 'Ramette' );";
                sql ="INSERT INTO Articles (reference, marque, designation, prixUnitaire, qteStock, grammage, type) VALUES (" +
                        "'" + article.getReference() + "', " +
                        "'" + article.getMarque() + "', " +
                        "'" + article.getDesignation() + "', " +
                        "'" + article.getPrixUnitaire() + "', " +
                        "'" + article.getQteStock() + "', "
                        + sqlComp;

            }

            etatClassique.executeUpdate(sql);
            ResultSet rs = etatClassique.getGeneratedKeys();
            if (rs.next()) {
                Integer id = rs.getInt(1);
                article.setIdArticle(id);
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
     * @param index
     */
    public void delete(int index) throws DALException {

        String sql ="DELETE FROM Articles WHERE idArticle = " + index + ";";

        try(Connection connection = DriverManager.getConnection(this.url);
            Statement etatClassique = connection.createStatement()) {

            etatClassique.executeUpdate(sql);
            connection.close();
        }
        catch(SQLException throwables) {
            throwables.printStackTrace();
            throw new DALException();
        }
    }


}
