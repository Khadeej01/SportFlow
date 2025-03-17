package Seance.Servlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Seance.DAO.SeanceDAO;
import Seance.Model.Seance;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

@WebServlet("/add-seance")
public class AddSeanceServlet extends HttpServlet {
    private SeanceDAO seanceDAO;

    @Override
    public void init() throws ServletException {
        seanceDAO = new SeanceDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int membreId = Integer.parseInt(request.getParameter("membreId"));
        int entraineurId = Integer.parseInt(request.getParameter("entraineurId"));
        String dateHeureStr = request.getParameter("dateHeure");

        Seance seance = new Seance();
        seance.setMembreId(membreId);
        seance.setEntraineurId(entraineurId);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            seance.setDateHeure(sdf.parse(dateHeureStr));
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/admin-dashboard.jsp?error=Format de date invalide");
            return;
        }

        try {
            seanceDAO.createSeance(seance);
            response.sendRedirect(request.getContextPath() + "/admin-dashboard.jsp?success=Séance ajoutée");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/admin-dashboard.jsp?error=Erreur lors de l'ajout");
        }
    }
}
