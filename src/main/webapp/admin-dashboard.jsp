<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="User.Model.User" %>
<%@ page import="Membre.DAO.MembreDAO" %>
<%@ page import="Membre.Model.Membre" %>
<%@ page import="Entraineur.DAO.EntraineurDAO" %>
<%@ page import="Entraineur.Model.Entraineur" %>
<%@ page import="Seance.DAO.SeanceDAO" %>
<%@ page import="Seance.DAO.SeanceDAO.Seance" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tableau de Bord Admin - SportFlow</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #000000;
            color: #ffffff;
            font-family: Arial, sans-serif;
        }
        .container {
            max-width: 1200px;
            margin-top: 50px;
        }
        .card {
            background-color: #1a1a1a;
            border: none;
            margin-bottom: 20px;
        }
        .btn-custom {
            background-color: #00ff00;
            color: #000000;
        }
        .btn-custom:hover {
            background-color: #00cc00;
            color: #ffffff;
        }
        .btn-danger {
            background-color: #ff4d4d;
        }
        .btn-danger:hover {
            background-color: #cc0000;
        }
    </style>
</head>
<body>
<%
    HttpSession currentSession = request.getSession(false);
    User user = (currentSession != null) ? (User) currentSession.getAttribute("user") : null;
    if (user == null || !"admin".equals(user.getRole())) {
        response.sendRedirect("login.jsp");
        return;
    }
    MembreDAO membreDAO = new MembreDAO();
    EntraineurDAO entraineurDAO = new EntraineurDAO();
    SeanceDAO seanceDAO = new SeanceDAO();
    List<Membre> membres = membreDAO.getAllMembres();
    List<Entraineur> entraineurs = entraineurDAO.getAllEntraineurs();
    List<SeanceDAO.Seance> seances = seanceDAO.getAllSeances();
%>
<div class="container">
    <h2>Tableau de Bord Admin</h2>
    <p>Bienvenue, <%= user.getNom() %> !</p>
    <a href="logout" class="btn btn-secondary mb-3">Déconnexion</a>

    <!-- Gestion des Membres -->
    <div class="card">
        <div class="card-body">
            <h5>Gestion des Membres</h5>
            <form action="add-membre" method="post" class="mb-3">
                <div class="row">
                    <div class="col">
                        <input type="text" class="form-control" name="nom" placeholder="Nom" required>
                    </div>
                    <div class="col">
                        <input type="email" class="form-control" name="email" placeholder="Email" required>
                    </div>
                    <div class="col">
                        <input type="password" class="form-control" name="password" placeholder="Mot de passe" required>
                    </div>
                    <div class="col">
                        <input type="text" class="form-control" name="sport" placeholder="Sport" required>
                    </div>
                    <div class="col">
                        <button type="submit" class="btn btn-custom">Ajouter</button>
                    </div>
                </div>
            </form>
            <table class="table table-dark">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Nom</th>
                    <th>Email</th>
                    <th>Sport</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <% for (Membre membre : membres) { %>
                <tr>
                    <td><%= membre.getId() %></td>
                    <td><%= membre.getNom() %></td>
                    <td><%= membre.getEmail() %></td>
                    <td><%= membre.getSportPratique() %></td>
                    <td>
                        <a href="delete-membre?id=<%= membre.getId() %>" class="btn btn-danger btn-sm">Supprimer</a>
                    </td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </div>

    <!-- Gestion des Entraîneurs -->
    <div class="card">
        <div class="card-body">
            <h5>Gestion des Entraîneurs</h5>
            <form action="add-entraineur" method="post" class="mb-3">
                <div class="row">
                    <div class="col">
                        <input type="text" class="form-control" name="nom" placeholder="Nom" required>
                    </div>
                    <div class="col">
                        <input type="email" class="form-control" name="email" placeholder="Email" required>
                    </div>
                    <div class="col">
                        <input type="password" class="form-control" name="password" placeholder="Mot de passe" required>
                    </div>
                    <div class="col">
                        <input type="text" class="form-control" name="specialite" placeholder="Spécialité" required>
                    </div>
                    <div class="col">
                        <button type="submit" class="btn btn-custom">Ajouter</button>
                    </div>
                </div>
            </form>
            <table class="table table-dark">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Nom</th>
                    <th>Email</th>
                    <th>Spécialité</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <% for (Entraineur entraineur : entraineurs) { %>
                <tr>
                    <td><%= entraineur.getId() %></td>
                    <td><%= entraineur.getNom() %></td>
                    <td><%= entraineur.getEmail() %></td>
                    <td><%= entraineur.getSpecialite() %></td>
                    <td>
                        <a href="delete-entraineur?id=<%= entraineur.getId() %>" class="btn btn-danger btn-sm">Supprimer</a>
                    </td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </div>

    <!-- Gestion des Séances -->
    <div class="card">
        <div class="card-body">
            <h5>Gestion des Séances</h5>
            <form action="add-seance" method="post" class="mb-3">
                <div class="row">
                    <div class="col">
                        <select class="form-control" name="membreId" required>
                            <option value="">Choisir un membre</option>
                            <% for (Membre membre : membres) { %>
                            <option value="<%= membre.getId() %>"><%= membre.getNom() %></option>
                            <% } %>
                        </select>
                    </div>
                    <div class="col">
                        <select class="form-control" name="entraineurId" required>
                            <option value="">Choisir un entraîneur</option>
                            <% for (Entraineur entraineur : entraineurs) { %>
                            <option value="<%= entraineur.getId() %>"><%= entraineur.getNom() %></option>
                            <% } %>
                        </select>
                    </div>
                    <div class="col">
                        <input type="datetime-local" class="form-control" name="dateHeure" required>
                    </div>
                    <div class="col">
                        <button type="submit" class="btn btn-custom">Ajouter</button>
                    </div>
                </div>
            </form>
            <table class="table table-dark">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Membre</th>
                    <th>Entraîneur</th>
                    <th>Date et Heure</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <% for (SeanceDAO.Seance seance : seances) { %>
                <tr>
                    <td><%= seance.getId() %></td>
                    <td><%= seance.getMembreNom() %></td>
                    <td><%= seance.getEntraineurNom() %></td>
                    <td><%= seance.getDateHeure() %></td>
                    <td>
                        <a href="delete-seance?id=<%= seance.getId() %>" class="btn btn-danger btn-sm">Supprimer</a>
                    </td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>