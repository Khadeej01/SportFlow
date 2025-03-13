package utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/SportFlow";
    private static final String USER = "root";
    private static final String PASSWORD = "Root@123"; // Remplace par ton mot de passe MySQL


    public static Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
            // Enregistrement du driver JDBC (optionnel avec les versions récentes de Java)
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connexion à la base de données réussie !");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver MySQL non trouvé : " + e.getMessage());
            throw new SQLException("Erreur de chargement du driver JDBC", e);
        } catch (SQLException e) {
            System.err.println("Erreur de connexion à la base de données : " + e.getMessage());
            throw e;
        }
        return conn;
    }

    // Méthode pour fermer la connexion (bonne pratique)
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Connexion fermée avec succès !");
            } catch (SQLException e) {
                System.err.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
            }
        }
    }
}
