//package Membre.Servlet;
//
//import java.io.IOException;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import User.Model.User;
//import Membre.DAO.MembreDAO;
//import Entraineur.DAO.EntraineurDAO;
//
//@WebServlet("/login")
//public class LoginServlet extends HttpServlet {
//    private MembreDAO membreDAO;
//    private EntraineurDAO entraineurDAO;
//
//    @Override
//    public void init() throws ServletException {
//        membreDAO = new MembreDAO();
//        entraineurDAO = new EntraineurDAO();
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String email = request.getParameter("email");
//        String password = request.getParameter("password");
//
//        try {
//            User user = null;
//
//            // Vérifier si c'est un membre
//            user = membreDAO.readMembreByEmail(email);
//            System.out.println("Email recherché : " + email);
//            System.out.println("Membre trouvé : " + (user != null ? user.getEmail() : "null"));
//            System.out.println("Mot de passe saisi : " + password);
//            System.out.println("Mot de passe stocke : " + (user != null ? user.getPassword() : "null"));
//
//            if (user == null) {
//                // Vérifier si c'est un entraîneur
//                user = entraineurDAO.readEntraineurByEmail(email);
//                System.out.println("Entraîneur trouvé : " + (user != null ? user.getEmail() : "null"));
//                System.out.println("Mot de passe stocké pour entraîneur : " + (user != null ? user.getPassword() : "null"));
//            }
//
//            if (user != null && user.getPassword().equals(password)) {
//                HttpSession session = request.getSession();
//                session.setAttribute("user", user);
//                System.out.println("Utilisateur connecté : " + user.getNom());
//
//
//                String role = user.getRole();
//                if ("membre".equals(role)) {
//                    response.sendRedirect("membre-dashboard.jsp");
//                } else if ("entraineur".equals(role)) {
//                    response.sendRedirect("entraineur-dashboard.jsp");
//                }
//            } else {
//                System.out.println("Authentification échouée pour : " + email);
//                response.sendRedirect("login.jsp?error=invalid");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Erreur lors de l'authentification : " + e.getMessage());
//            response.sendRedirect("login.jsp?error=error");
//        }
//    }
//}

package Membre.Servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import User.Model.User;
import Membre.DAO.MembreDAO;
import Entraineur.DAO.EntraineurDAO;
import Admin.DAO.AdminDAO;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private MembreDAO membreDAO;
    private EntraineurDAO entraineurDAO;
    private AdminDAO adminDAO;

    @Override
    public void init() throws ServletException {
        membreDAO = new MembreDAO();
        entraineurDAO = new EntraineurDAO();
        adminDAO = new AdminDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            User user = null;

            // Vérifier si c'est un membre
            user = membreDAO.readMembreByEmail(email);
            System.out.println("Email recherché : " + email);
            System.out.println("Membre trouvé : " + (user != null ? user.getEmail() : "null"));
            System.out.println("Mot de passe saisi : " + password);
            System.out.println("Mot de passe stocké : " + (user != null ? user.getPassword() : "null"));

            if (user == null) {
                // Vérifier si c'est un entraîneur
                user = entraineurDAO.readEntraineurByEmail(email);
                System.out.println("Entraîneur trouvé : " + (user != null ? user.getEmail() : "null"));
                System.out.println("Mot de passe stocké pour entraîneur : " + (user != null ? user.getPassword() : "null"));
            }

            if (user == null) {
                // Vérifier si c'est un admin
                user = adminDAO.readAdminByEmail(email);
                System.out.println("Admin trouvé : " + (user != null ? user.getEmail() : "null"));
                System.out.println("Mot de passe stocké pour admin : " + (user != null ? user.getPassword() : "null"));
            }
            if(user.getPassword().equals(password)) {
                System.out.println("Mot de passe saisi : " + password);
            }

            if (user != null && user.getPassword().equals(password)) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                System.out.println("Utilisateur connecté : " + user.getEmail());

                // Redirection en fonction du rôle
                String role = user.getRole();
                if ("membre".equals(role)) {
                    response.sendRedirect("membre-dashboard.jsp");
                } else if ("entraineur".equals(role)) {
                    response.sendRedirect("entraineur-dashboard.jsp");
                } else if ("admin".equals(role)) {
                    response.sendRedirect("admin-dashboard.jsp");
                }
            } else {
                System.out.println("Authentification échouée pour : " + email);
                response.sendRedirect("login.jsp?error=invalid");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur lors de l'authentification : " + e.getMessage());
            response.sendRedirect("login.jsp?error=error");
        }
    }
}