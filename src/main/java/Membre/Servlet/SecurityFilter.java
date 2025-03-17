//package Membre.Servlet;
//
//import jakarta.servlet.Filter;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.FilterConfig;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.ServletRequest;
//import jakarta.servlet.ServletResponse;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
//import java.io.IOException;
//
//@WebFilter("/*")
//public class SecurityFilter implements Filter {
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {}
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//        HttpSession session = httpRequest.getSession(false);
//
//        String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
//
//        // Chemins protégés pour les membres
//        if (path.startsWith("/membre-dashboard.jsp") || path.startsWith("/seance-list")) {
//            if (session == null || session.getAttribute("user") == null) {
//                httpResponse.sendRedirect("login.jsp");
//                return;
//            }
//            Object user = session.getAttribute("user");
//            if (!(user instanceof Membre.Model.Membre)) {
//                httpResponse.sendRedirect("login.jsp");
//                return;
//            }
//        }
//
//        // Chemins protégés pour les entraîneurs
//        if (path.startsWith("/entraineur-dashboard.jsp")) {
//            if (session == null || session.getAttribute("user") == null) {
//                httpResponse.sendRedirect("login.jsp");
//                return;
//            }
//            Object user = session.getAttribute("user");
//            if (!(user instanceof Entraineur.Model.Entraineur)) {
//                httpResponse.sendRedirect("login.jsp");
//                return;
//            }
//        }
//
//        // Permettre l'accès aux ressources publiques (ex. login.jsp)
//        if (path.equals("/login.jsp") || path.equals("/")) {
//            chain.doFilter(request, response);
//            return;
//        }
//
//        // Continuer la chaîne pour les autres requêtes
//        chain.doFilter(request, response);
//    }
//
//    @Override
//    public void destroy() {}


package Membre.Servlet;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class SecurityFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());

        // Chemins protégés pour les membres
        if (path.startsWith("/membre-dashboard.jsp") || path.startsWith("/seance-list")) {
            if (session == null || session.getAttribute("user") == null) {
                httpResponse.sendRedirect("login.jsp");
                return;
            }
            Object user = session.getAttribute("user");
            if (!(user instanceof Membre.Model.Membre)) {
                httpResponse.sendRedirect("login.jsp");
                return;
            }
        }

        // Chemins protégés pour les entraîneurs
        if (path.startsWith("/entraineur-dashboard.jsp")) {
            if (session == null || session.getAttribute("user") == null) {
                httpResponse.sendRedirect("login.jsp");
                return;
            }
            Object user = session.getAttribute("user");
            if (!(user instanceof Entraineur.Model.Entraineur)) {
                httpResponse.sendRedirect("login.jsp");
                return;
            }
        }

        // Chemins protégés pour les admins
        if (path.startsWith("/admin-dashboard.jsp")) {
            if (session == null || session.getAttribute("user") == null) {
                httpResponse.sendRedirect("login.jsp");
                return;
            }
            Object user = session.getAttribute("user");
            if (!(user instanceof Admin.Model.Admin)) {
                httpResponse.sendRedirect("login.jsp");
                return;
            }
        }

        // Permettre l'accès aux ressources publiques (ex. login.jsp)
        if (path.equals("/login.jsp") || path.equals("/")) {
            chain.doFilter(request, response);
            return;
        }

        // Continuer la chaîne pour les autres requêtes
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {}
}