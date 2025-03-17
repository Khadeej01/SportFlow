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
import java.text.SimpleDateFormat;

@WebServlet("/update-membre")
public class UpdateMembreServlet extends HttpServlet {
    private MembreDAO membreDAO;

    @Override
    public void init() throws ServletException {
        membreDAO = new MembreDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { // Propagation de SQLException
        int id = Integer.parseInt(request.getParameter("id"));
        String nom = request.getParameter("nom");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String dateNaissanceStr = request.getParameter("dateNaissance");
        String sport = request.getParameter("sport");

        Membre membre = (Membre) request.getSession().getAttribute("user");
        if (membre.getId() != id) {
            response.sendRedirect("login.jsp");
            return;
        }

        membre.setNom(nom);
        membre.setEmail(email);
        if (password != null && !password.isEmpty()) {
            membre.setPassword(password);
        }
        membre.setSportPratique(sport);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            membre.setDateNaissance(sdf.parse(dateNaissanceStr));
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("membre-dashboard.jsp?error=Format de date invalide");
            return;
        }

        try {
            membreDAO.updateMembre(membre); // SQLException propagée au conteneur
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.getSession().setAttribute("user", membre);
        response.sendRedirect("membre-dashboard.jsp?success=Mise à jour réussie");
    }
}

