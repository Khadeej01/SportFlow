package Entraineur.servlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Entraineur.DAO.EntraineurDAO;
import Entraineur.Model.Entraineur;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/update-entraineur")
public class UpdateEntraineurServlet extends HttpServlet {
    private EntraineurDAO entraineurDAO;

    @Override
    public void init() throws ServletException {
        entraineurDAO = new EntraineurDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nom = request.getParameter("nom");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String specialite = request.getParameter("specialite");

        Entraineur entraineur = (Entraineur) request.getSession().getAttribute("user");
        if (entraineur.getId() != id) {
            response.sendRedirect("login.jsp"); // Sécurité
            return;
        }

        entraineur.setNom(nom);
        entraineur.setEmail(email);
        if (password != null && !password.isEmpty()) {
            entraineur.setPassword(password);
        }
        entraineur.setSpecialite(specialite);

        try {
            entraineurDAO.updateEntraineur(entraineur);
            request.getSession().setAttribute("user", entraineur); // Mise à jour de la session
            response.sendRedirect(request.getContextPath() + "/entraineur-dashboard.jsp?success=Mise à jour réussie");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/entraineur-dashboard.jsp?error=Erreur lors de la mise à jour");
        }
    }
}