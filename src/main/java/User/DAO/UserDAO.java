package User.DAO;

import User.Model.User;
import Membre.Model.Membre;
import Entraineur.Model.Entraineur;
import Admin.Model.Admin;
import java.sql.*;

public class UserDAO {
    private Connection conn;

    public UserDAO() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sportflow", "root", "your_password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserByEmail(String email) {
        String sql = "SELECT id, nom, email, password, role FROM utilisateurs WHERE email = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String role = rs.getString("role");
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String password = rs.getString("password");

                switch (role) {
                    case "membre":
                        return new Membre(id, nom, email, password);
                    case "entraineur":
                        return new Entraineur(id, nom, email, password);
                    case "admin":
                        return new Admin(id, nom, email, password);
                    default:
                        return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addUser(User user) {
        String sql = "INSERT INTO utilisateurs (nom, email, password, role) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getNom());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getRole());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int id) {
        String sql = "DELETE FROM utilisateurs WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}