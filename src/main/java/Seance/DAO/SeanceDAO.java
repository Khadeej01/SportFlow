package Seance.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Seance.Model.Seance;
import utils.DatabaseConnection;

public class SeanceDAO {

    public void createSeance(Seance seance) throws SQLException {
        String sql = "INSERT INTO seance (id_membre, id_entraineur, date_heure) VALUES (?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, seance.getIdMembre());
            stmt.setInt(2, seance.getIdEntraineur());
            stmt.setTimestamp(3, new java.sql.Timestamp(seance.getDateHeure().getTime()));
            stmt.executeUpdate();

            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                seance.setIdSeance(rs.getInt(1));
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la création de la séance : " + e.getMessage());
            throw e;
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (stmt != null) try { stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            DatabaseConnection.closeConnection(conn);
        }
    }


    public Seance readSeance(int id) throws SQLException {
        String sql = "SELECT * FROM seance WHERE id_seance = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return new Seance(
                        rs.getInt("id_seance"),
                        rs.getInt("id_membre"),
                        rs.getInt("id_entraineur"),
                        rs.getTimestamp("date_heure")
                );
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la lecture de la séance : " + e.getMessage());
            throw e;
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (stmt != null) try { stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            DatabaseConnection.closeConnection(conn);
        }
        return null;
    }


    public void updateSeance(Seance seance) throws SQLException {
        String sql = "UPDATE seance SET id_membre = ?, id_entraineur = ?, date_heure = ? WHERE id_seance = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, seance.getIdMembre());
            stmt.setInt(2, seance.getIdEntraineur());
            stmt.setTimestamp(3, new java.sql.Timestamp(seance.getDateHeure().getTime()));
            stmt.setInt(4, seance.getIdSeance());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur lors de la mise à jour de la séance : " + e.getMessage());
            throw e;
        } finally {
            if (stmt != null) try { stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            DatabaseConnection.closeConnection(conn);
        }
    }


    public void deleteSeance(int id) throws SQLException {
        String sql = "DELETE FROM seance WHERE id_seance = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression de la séance : " + e.getMessage());
            throw e;
        } finally {
            if (stmt != null) try { stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            DatabaseConnection.closeConnection(conn);
        }
    }
}