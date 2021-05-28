/**
 * Classe pour récupérer le fichier settings.properties
 */

package fr.eni.papeterie.dal.jdbc;

import java.util.Properties;

public class Settings {

    private static Properties prop;

    static {

        try {
            prop = new Properties();
            prop.load(Settings.class.getResourceAsStream("settings.properties"));

        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static String getProp(String cle) {
        String parametre = prop.getProperty(cle, null);
        return parametre;
    }
}
