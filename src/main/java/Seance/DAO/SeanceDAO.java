package Seance.DAO;

import Seance.Model.Seance;
import Membre.DAO.MembreDAO;
import Entraineur.DAO.EntraineurDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SeanceDAO {
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/SportFlow", "root", "votre_mot_de_passe");
    }

    // Créer une séance
    public void createSeance(Seance seance) {
        String sql = "INSERT INTO seance (id_membre, id_entraineur, date_heure) VALUES (?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, seance.getMembreId());
            stmt.setInt(2, seance.getEntraineurId());
            stmt.setTimestamp(3, new Timestamp(seance.getDateHeure().getTime()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Lire toutes les séances
    public List<Seance> getAllSeances() {
        List<Seance> seances = new ArrayList<>();
        String sql = "SELECT * FROM seance";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Seance seance = new Seance();
                seance.setId(rs.getInt("id_seance"));
                seance.setMembreId(rs.getInt("id_membre"));
                seance.setEntraineurId(rs.getInt("id_entraineur"));
                seance.setDateHeure(rs.getTimestamp("date_heure"));
                seances.add(seance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seances;
    }

    // Lire les séances d'un membre
    public List<Seance> getSeancesByMembreId(int membreId) {
        List<Seance> seances = new ArrayList<>();
        String sql = "SELECT * FROM seance WHERE id_membre = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, membreId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Seance seance = new Seance();
                seance.setId(rs.getInt("id_seance"));
                seance.setMembreId(rs.getInt("id_membre"));
                seance.setEntraineurId(rs.getInt("id_entraineur"));
                seance.setDateHeure(rs.getTimestamp("date_heure"));
                seances.add(seance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seances;
    }

    // Lire les séances d'un entraîneur
    public List<Seance> getSeancesByEntraineurId(int entraineurId) {
        List<Seance> seances = new ArrayList<>();
        String sql = "SELECT * FROM seance WHERE id_entraineur = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, entraineurId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Seance seance = new Seance();
                seance.setId(rs.getInt("id_seance"));
                seance.setMembreId(rs.getInt("id_membre"));
                seance.setEntraineurId(rs.getInt("id_entraineur"));
                seance.setDateHeure(rs.getTimestamp("date_heure"));
                seances.add(seance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seances;
    }

    // Mettre à jour une séance
    public void updateSeance(Seance seance) {
        String sql = "UPDATE seance SET id_membre = ?, id_entraineur = ?, date_heure = ? WHERE id_seance = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, seance.getMembreId());
            stmt.setInt(2, seance.getEntraineurId());
            stmt.setTimestamp(3, new Timestamp(seance.getDateHeure().getTime()));
            stmt.setInt(4, seance.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Supprimer une séance
    public void deleteSeance(int id) {
        String sql = "DELETE FROM seance WHERE id_seance = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}