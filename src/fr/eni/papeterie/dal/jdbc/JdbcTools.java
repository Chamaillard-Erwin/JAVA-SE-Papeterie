package fr.eni.papeterie.dal.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcTools {

    private static String url = Settings.getProp("url");

    public static Connection recupConnection() throws SQLException {
        return DriverManager.getConnection(url);
    }
}
