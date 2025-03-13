package Seance.Servlet;


import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Seance.DAO.SeanceDAO;
import Seance.Model.Seance ;

@WebServlet("/seance")
public class SeanceServlet extends HttpServlet {
    private SeanceDAO seanceDAO;

    @Override
    public void init() throws ServletException {
        seanceDAO = new SeanceDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/seance-form.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer les données du formulaire
        String idMembreStr = request.getParameter("idMembre");
        String idEntraineurStr = request.getParameter("idEntraîneur");
        String dateHeureStr = request.getParameter("dateHeure");

        // Convertir les chaînes en types appropriés
        java.util.Date dateHeure = null;
        try {
            dateHeure = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(dateHeureStr);
        } catch (ParseException e) {
            e.printStackTrace();
            response.sendRedirect("seance-error.jsp");
            return;
        }

        int idMembre;
        int idEntraineur;
        try {
            idMembre = Integer.parseInt(idMembreStr);
            idEntraineur = Integer.parseInt(idEntraineurStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("seance-error.jsp");
            return;
        }

        // Créer une nouvelle séance
        Seance seance = new Seance(0, idMembre,idEntraineur, dateHeure);

        try {
            seanceDAO.createSeance(seance);
            response.sendRedirect("seance-success.jsp");
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout de la séance : " + e.getMessage());
            response.sendRedirect("seance-error.jsp");
        }
    }
}