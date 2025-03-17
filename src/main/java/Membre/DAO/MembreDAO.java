package Membre.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Membre.Model.Membre;
import utils.DatabaseConnection;


public class MembreDAO {
    public void createMembre(Membre membre) throws SQLException {
        String sql = "INSERT INTO membre (nom, email, password, date_naissance, sport_pratique) VALUES (?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, membre.getNom());
            stmt.setString(2, membre.getEmail());
            stmt.setString(3, membre.getPassword());
            stmt.setDate(4, new java.sql.Date(membre.getDateNaissance().getTime()));
            stmt.setString(5, membre.getSportPratique());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                membre.setId(rs.getInt(1));
            }
        } finally {
            if (stmt != null) stmt.close();
            DatabaseConnection.closeConnection(conn);
        }
    }

    public Membre readMembre(int id) throws SQLException {
        String sql = "SELECT * FROM membre WHERE id_membre = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return new Membre(
                        rs.getInt("id_membre"),
                        rs.getString("nom"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getDate("date_naissance"),
                        rs.getString("sport_pratique")
                );
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            DatabaseConnection.closeConnection(conn);
        }
        return null;
    }

    public void updateMembre(Membre membre) throws SQLException {
        String sql = "UPDATE membre SET nom = ?, email = ?, password = ?, date_naissance = ?, sport_pratique = ? WHERE id_membre = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, membre.getNom());
            stmt.setString(2, membre.getEmail());
            stmt.setString(3, membre.getPassword());
            stmt.setDate(4, new java.sql.Date(membre.getDateNaissance().getTime()));
            stmt.setString(5, membre.getSportPratique());
            stmt.setInt(6, membre.getId());
            stmt.executeUpdate();
        } finally {
            if (stmt != null) stmt.close();
            DatabaseConnection.closeConnection(conn);
        }
    }

    public void deleteMembre(int id) throws SQLException {
        String sql = "DELETE FROM membre WHERE id_membre = ?";
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



    public Membre readMembreByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM membre WHERE email = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Membre membre = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            rs = stmt.executeQuery();

            if (rs.next()) {
                membre = new Membre(
                        rs.getInt("id_membre"),
                        rs.getString("nom"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getDate("date_naissance"),
                        rs.getString("sport_pratique")
                );
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la lecture du membre : " + e.getMessage());
            throw e;
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (stmt != null) try { stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            DatabaseConnection.closeConnection(conn);
        }
        return membre;
    }

    public List<Membre> getAllMembres() throws SQLException {
        List<Membre> membres = new ArrayList<>();
        String sql = "SELECT * FROM membre";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Membre membre = new Membre(
                        rs.getInt("id_membre"),
                        rs.getString("nom"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getDate("date_naissance"),
                        rs.getString("sport_pratique")
                );
                membres.add(membre);
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            DatabaseConnection.closeConnection(conn);
        }
        return membres;
    }
}



