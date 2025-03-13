package Membre.Servlet;


import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Membre.DAO.MembreDAO;
import Membre.Model.Membre;

@WebServlet("/membre")
public class MembreServlet extends HttpServlet {
    private MembreDAO membreDAO;

    @Override
    public void init() throws ServletException {
        membreDAO = new MembreDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/membre-form.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String dateNaissanceStr = request.getParameter("dateNaissance");
        String sportPratique = request.getParameter("sportPratique");

        java.util.Date dateNaissance = null;
        try {
            dateNaissance = new SimpleDateFormat("yyyy-MM-dd").parse(dateNaissanceStr);
        } catch (ParseException e) {
            e.printStackTrace();
            response.sendRedirect("membre-dashboard.jsp");
            return;
        }

        Membre membre = new Membre(0, nom, email, password, new java.sql.Date(dateNaissance.getTime()), sportPratique);

        try {
            membreDAO.createMembre(membre);
            response.sendRedirect("membre-dashboard.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("membre-dashboard.jsp");
        }
    }
}
