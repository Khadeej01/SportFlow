package Seance.Servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Seance.DAO.SeanceDAO;

@WebServlet("/add-seance")
public class SeanceServlet extends HttpServlet {
    private SeanceDAO seanceDAO;

    @Override
    public void init() throws ServletException {
        seanceDAO = new SeanceDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Récupérer les paramètres du formulaire
            int idMembre = Integer.parseInt(request.getParameter("idMembre"));
            int idEntraineur = Integer.parseInt(request.getParameter("idEntraineur"));
            String dateHeureStr = request.getParameter("dateHeure");

            // Convertir la chaîne de date en java.util.Date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date dateHeure = sdf.parse(dateHeureStr);

            // Appeler la méthode createSeance
            seanceDAO.createSeance(idMembre, idEntraineur, dateHeure);


            response.sendRedirect("admin-dashboard.jsp");
        } catch (NumberFormatException | ParseException | SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Erreur lors de l'ajout de la séance : " + e.getMessage());
            request.getRequestDispatcher("admin-dashboard.jsp").forward(request, response);
        }
    }
}