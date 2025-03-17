package Entraineur.servlet;


import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Entraineur.DAO.EntraineurDAO ;
import Entraineur.Model.Entraineur;
import java.sql.SQLException;


@WebServlet("/entraineur")
public class EntraineurServlet extends HttpServlet {
    private EntraineurDAO entraineurDAO;

    @Override
    public void init() throws ServletException {
        entraineurDAO = new EntraineurDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/entraineur-form.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String nom = request.getParameter("nom");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String specialite = request.getParameter("specialite");


        Entraineur entraineur = new Entraineur(0, nom, email, password, specialite);

        try {
            entraineurDAO.createEntraineur(entraineur);
            response.sendRedirect("login.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("index.jsp");
        }
    }
}