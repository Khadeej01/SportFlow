//package Admin.Servlet;
//
//import java.io.IOException;
//import java.sql.SQLException;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import Admin.DAO.AdminDAO;
//import Admin.Model.Admin;
//
//@WebServlet("/admin")
//public class AdminServlet extends HttpServlet {
//    private AdminDAO adminDAO;
//
//    @Override
//    public void init() throws ServletException {
//        adminDAO = new AdminDAO();
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // Affiche le formulaire d'ajout d'un admin
//        request.getRequestDispatcher("/admin-form.jsp").forward(request, response);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // Récupérer les données du formulaire
//        String nom = request.getParameter("nom");
//        String email = request.getParameter("email");
//        String password = request.getParameter("password");
//        String role = request.getParameter("role");
//
//        // Créer un nouvel admin (rôle par défaut si vide)
//        if (role == null || role.trim().isEmpty()) {
//            role = "admin";
//        }
//        Admin admin = new Admin(0, nom, email, password, role);
//
//        try {
//            adminDAO.createAdmin(admin);
//            response.sendRedirect("admin-success.jsp");
//        } catch (SQLException e) {
//            System.err.println("Erreur lors de l'ajout de l'admin : " + e.getMessage());
//            request.setAttribute("errorMessage", e.getMessage());
//            response.sendRedirect("admin-error.jsp");
//        }
//    }
//}

package Admin.Servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Membre.DAO.MembreDAO;
import Entraineur.DAO.EntraineurDAO;
import Seance.DAO.SeanceDAO;

@WebServlet(urlPatterns = {"/delete-membre", "/delete-entraineur", "/delete-seance"})
public class AdminServlet extends HttpServlet {
    private MembreDAO membreDAO;
    private EntraineurDAO entraineurDAO;
    private SeanceDAO seanceDAO;

    @Override
    public void init() throws ServletException {
        membreDAO = new MembreDAO();
        entraineurDAO = new EntraineurDAO();
        seanceDAO = new SeanceDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        try {
            switch (path) {
                case "/delete-membre":
                    int membreId = Integer.parseInt(request.getParameter("id"));
                    membreDAO.deleteMembre(membreId);
                    break;
                case "/delete-entraineur":
                    int entraineurId = Integer.parseInt(request.getParameter("id"));
                    entraineurDAO.deleteEntraineur(entraineurId);
                    break;
                case "/delete-seance":
                    int seanceId = Integer.parseInt(request.getParameter("id"));
                    seanceDAO.deleteSeance(seanceId);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("admin-dashboard.jsp");
    }
}