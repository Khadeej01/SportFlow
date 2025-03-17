package Seance.Servlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Seance.DAO.SeanceDAO;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/delete-seance")
public class DeleteSeanceServlet extends HttpServlet {
    private SeanceDAO seanceDAO;

    @Override
    public void init() throws ServletException {
        seanceDAO = new SeanceDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        try {
            seanceDAO.deleteSeance(id);
            response.sendRedirect(request.getContextPath() + "/admin-dashboard.jsp?success=Séance supprimée");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/admin-dashboard.jsp?error=Erreur lors de la suppression");
        }
    }
}