package Entraineur.DAO;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Entraineur.Model.Entraineur ;
import utils.DatabaseConnection;

public class EntraineurDAO {

    public void createEntraineur(Entraineur entraineur) throws SQLException {
        String sql = "INSERT INTO entraineur (nom, email, password, specialite) VALUES (?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, entraineur.getNom());
            stmt.setString(2, entraineur.getEmail());
            stmt.setString(3, entraineur.getPassword());
            stmt.setString(4, entraineur.getSpecialite());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                entraineur.setId(rs.getInt(1));
            }
        } finally {
            if (stmt != null) stmt.close();
            DatabaseConnection.closeConnection(conn);
        }
    }


    public Entraineur readEntraineur(int id) throws SQLException {
        String sql = "SELECT * FROM entraineur WHERE id_entraineur = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return new Entraineur(
                        rs.getInt("id_entraineur"),
                        rs.getString("nom"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("specialite")
                );
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            DatabaseConnection.closeConnection(conn);
        }
        return null;
    }


    public void updateEntraineur(Entraineur entraineur) throws SQLException {
        String sql = "UPDATE entraineur SET nom = ?, email = ?, password = ?, specialite = ? WHERE id_entraineur = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, entraineur.getNom());
            stmt.setString(2, entraineur.getEmail());
            stmt.setString(3, entraineur.getPassword());
            stmt.setString(4, entraineur.getSpecialite());
            stmt.setInt(5, entraineur.getId());
            stmt.executeUpdate();
        } finally {
            if (stmt != null) stmt.close();
            DatabaseConnection.closeConnection(conn);
        }
    }


    public void deleteEntraineur(int id) throws SQLException {
        String sql = "DELETE FROM entraineur WHERE id_entraineur = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } finally {
            if (stmt != null) stmt.close();
            DatabaseConnection.closeConnection(conn);
        }
    }

    // Lire un entraîneur par email (pour l'authentification)
    public Entraineur readEntraineurByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM entraineur WHERE email = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return new Entraineur(
                        rs.getInt("id_entraineur"),
                        rs.getString("nom"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("specialite")
                );
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            DatabaseConnection.closeConnection(conn);
        }
        return null;
    }
}