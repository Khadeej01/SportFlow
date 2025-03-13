package Membre.Servlet;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Membre.Model.Membre;
import Membre.DAO.MembreDAO;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private MembreDAO membreDAO;

    @Override
    public void init() throws ServletException {
        membreDAO = new MembreDAO();
    }



        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            try {
                Membre membre = membreDAO.readMembreByEmail(email);
                System.out.println("Email recherché : " + email);
                System.out.println("Membre trouvé : " + (membre != null ? membre.getEmail() : "null"));
                System.out.println("Mot de passe saisi : " + password);
                System.out.println("Mot de passe stocké : " + (membre != null ? membre.getPassword() : "null"));

                if (membre != null && membre.getPassword().equals(password)) {
                    request.getSession().setAttribute("user", membre);
                    System.out.println("Membre logged in: " + membre.getNom());
                    response.sendRedirect("membre-dashboard.jsp");
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
