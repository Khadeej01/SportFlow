package Membre.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Membre.DAO.MembreDAO;
import Membre.Model.Membre;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/delete-membre")
public class DeleteMembreServlet extends HttpServlet {
    private MembreDAO membreDAO;

    @Override
    public void init() throws ServletException {
        membreDAO = new MembreDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Membre membre = (Membre) request.getSession().getAttribute("user");
        if (membre.getId() != id) {
            response.sendRedirect("login.jsp"); // Sécurité
            return;
        }

        try {
            membreDAO.deleteMembre(id); // Gestion de l'exception ici
        } catch (SQLException e) {
            e.printStackTrace(); // Log l'erreur pour le débogage
            response.sendRedirect("membre-dashboard.jsp?error=delete_failed"); // Redirection avec message d'erreur
            return;
        }

        request.getSession().invalidate(); // Déconnexion
        response.sendRedirect("login.jsp");
    }
}