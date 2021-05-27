package fr.eni.dal.jdbc;


import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDaoJdbcImpl {

    private final String url = "jdbc:sqlite:PAPETERIE_DB";

    //----------------------------------------------------------------------------------------------------------------//

    /**
     * Méthode pour récupérer toute les données de la Table Articles
     */
    public List<Article> selectAll () {

        List<Article> articleList = new ArrayList<>();

        try {
            String sql ="SELECT * FROM Articles;";

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
        }
        return articleList;
    }

    //----------------------------------------------------------------------------------------------------------------//

    /**
     * Méthode qui récupère tout les articles en fonction d'un idArticle
     * @param p_idArticle
     */
    public Article selectById (int p_idArticle) {

        Article article = null;

        try {

            String sql ="SELECT * FROM Articles WHERE idArticle = " + p_idArticle + ";";

            Connection connection = DriverManager.getConnection(this.url);
            Statement etatClassique = connection.createStatement();
            ResultSet rs = etatClassique.executeQuery(sql);

            if (rs.getString("couleur" ) == null) {
                article = new Ramette(
                        rs.getInt("idArticle"),
                        rs.getString("marque"),
                        rs.getString("reference"),
                        rs.getString("designation"),
                        rs.getFloat("prixUnitaire"),
                        rs.getInt("qteStock"),
                        rs.getInt("grammage"));

            }
            else if (rs.getString("grammage") == null) {
                article = new Stylo(
                        rs.getInt("idArticle"),
                        rs.getString("marque"),
                        rs.getString("reference"),
                        rs.getString("designation"),
                        rs.getFloat("prixUnitaire"),
                        rs.getInt("qteStock"),
                        rs.getString("couleur"));
            }

        }
        catch(SQLException throwables) {
            throwables.printStackTrace();
        }

        return article;
    }

    //----------------------------------------------------------------------------------------------------------------//

    /**
     * Méthode qui modifie les attributs d'un article
     * @param article
     */
    public void update(Article article) {

        try {

            Connection connection = DriverManager.getConnection(this.url);
            Statement etatClassique = connection.createStatement();

            String sql ="UPDATE Articles SET" +
                    " reference = '" + article.getReference() + "'" +
                    ", marque = '" + article.getMarque() + "'" +
                    ", designation = '" + article.getDesignation() + "'" +
                    ", prixUnitaire = '" + article.getPrixUnitaire() + "'" +
                    ", qteStock = '" + article.getQteStock() + "'";

            if (article instanceof Stylo) {
                sql += ", couleur = " + ((Stylo) article).getCouleur() + "'";

            }
            else if (article instanceof Ramette){
                sql += ", grammage = ''" + ((Ramette)article).getGrammage() + "'";
            }

            sql += " WHERE idArticle = " + article.getIdArticle() + ";";


            etatClassique.executeUpdate(sql);
        }
        catch(SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //----------------------------------------------------------------------------------------------------------------//

    /**
     * Méthode qui modifie les attributs d'un article
     * @param article
     */
    public void insert(Article article) {

        String sql = "";
        String sqlComp = "";

        if (article instanceof Stylo) {
            sqlComp = ", couleur = '" + ((Stylo)article).getCouleur() + "');";

        }
        else if (article instanceof Ramette){
            sqlComp = ", grammage = '" + ((Ramette)article).getGrammage() + "');";
        }

            sql ="INSERT INTO Articles VALUES (" +
                "reference = '" + article.getReference() + "'" +
                ", marque = '" + article.getMarque() + "'" +
                ", designation = '" + article.getDesignation() + "'" +
                ", prixUnitaire = '" + article.getPrixUnitaire() + "'" +
                ", qteStock = '" + article.getQteStock() + "'"
                + sqlComp;

        try {
            Connection connection = DriverManager.getConnection(this.url);
            Statement etatClassique = connection.createStatement();
            etatClassique.executeUpdate(sql);
        }
        catch(SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //----------------------------------------------------------------------------------------------------------------//

    /**
     * Méthode pour supprimer une ligne de la Table Articles en fonction de l'id
     * @param index
     */
    public void delete(int index) {

        String sql ="DELETE FROM Articles WHERE idArticle = " + index + ";";

        try {
            Connection connection = DriverManager.getConnection(this.url);
            Statement etatClassique = connection.createStatement();
            etatClassique.executeUpdate(sql);
        }
        catch(SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
