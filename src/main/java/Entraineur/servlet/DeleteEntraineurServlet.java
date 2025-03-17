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

@WebServlet("/delete-entraineur")
public class DeleteEntraineurServlet extends HttpServlet {
    private EntraineurDAO entraineurDAO;

    @Override
    public void init() throws ServletException {
        entraineurDAO = new EntraineurDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Entraineur entraineur = (Entraineur) request.getSession().getAttribute("user");
        if (entraineur.getId() != id) {
            response.sendRedirect("login.jsp"); // Sécurité
            return;
        }

        try {
            entraineurDAO.deleteEntraineur(id);
            request.getSession().invalidate(); // Déconnexion
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/entraineur-dashboard.jsp?error=Erreur lors de la suppression");
        }
    }
}
