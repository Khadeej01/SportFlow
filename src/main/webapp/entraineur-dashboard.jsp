<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="Entraineur.Model.Entraineur" %>
<%@ page import="Membre.DAO.MembreDAO" %>
<%@ page import="Seance.DAO.SeanceDAO" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tableau de Bord Entraîneur - SportFlow</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #000000;
            color: #ffffff;
            font-family: Arial, sans-serif;
        }
        .container {
            max-width: 800px;
            margin-top: 50px;
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
    Entraineur entraineur = (currentSession != null) ? (Entraineur) currentSession.getAttribute("user") : null;
    if (entraineur == null || !"entraineur".equals(entraineur.getRole())) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<div class="container">
    <h2>Tableau de Bord Entraîneur</h2>
    <p>Bienvenue, <%= entraineur.getNom() %> !</p>
    <a href="logout" class="btn btn-secondary mb-3">Déconnexion</a>

    <!-- Affichage des messages -->
    <% String error = request.getParameter("error"); %>
    <% String success = request.getParameter("success"); %>
    <% if (error != null) { %>
    <div class="alert alert-danger" role="alert">
        <%= error %>
    </div>
    <% } %>
    <% if (success != null) { %>
    <div class="alert alert-success" role="alert">
        <%= success %>
    </div>
    <% } %>

    <!-- Affichage des informations -->
    <div class="card bg-dark text-white mb-3">
        <div class="card-body">
            <h5>Vos Informations</h5>
            <p><strong>Nom :</strong> <%= entraineur.getNom() %></p>
            <p><strong>Email :</strong> <%= entraineur.getEmail() %></p>
            <p><strong>Spécialité :</strong> <%= entraineur.getSpecialite() %></p>
        </div>
    </div>

    <!-- Formulaire de modification -->
    <div class="card bg-dark text-white mb-3">
        <div class="card-body">
            <h5>Modifier vos informations</h5>
            <form action="update-entraineur" method="post">
                <input type="hidden" name="id" value="<%= entraineur.getId() %>">
                <div class="mb-3">
                    <label for="nom" class="form-label">Nom</label>
                    <input type="text" class="form-control" id="nom" name="nom" value="<%= entraineur.getNom() %>" required>
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" id="email" name="email" value="<%= entraineur.getEmail() %>" required>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Nouveau mot de passe (laisser vide si inchangé)</label>
                    <input type="password" class="form-control" id="password" name="password">
                </div>
                <div class="mb-3">
                    <label for="specialite" class="form-label">Spécialité</label>
                    <input type="text" class="form-control" id="specialite" name="specialite" value="<%= entraineur.getSpecialite() %>" required>
                </div>
                <button type="submit" class="btn btn-custom">Mettre à jour</button>
            </form>
        </div>
    </div>

    <!-- Mes Séances -->
    <div class="card bg-dark text-white">
        <div class="card-body">
            <h5>Mes Séances</h5>
            <table class="table table-dark">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Membre</th>
                    <th>Date et Heure</th>
                </tr>
                </thead>
                <tbody>
                <%
                    SeanceDAO seanceDAO = new SeanceDAO();
                    MembreDAO membreDAO = new MembreDAO();
                    List<Seance> mesSeances = seanceDAO.getSeancesByEntraineurId(entraineur.getId());
                    for (Seance seance : mesSeances) {
                        Membre membre = membreDAO.getAllMembres().stream().filter(m -> m.getId() == seance.getMembreId()).findFirst().orElse(null);
                %>
                <tr>
                    <td><%= seance.getId() %></td>
                    <td><%= membre != null ? membre.getNom() : "Inconnu" %></td>
                    <td><%= new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm").format(seance.getDateHeure()) %></td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </div>
    <!-- Suppression du compte -->
    <div class="card bg-dark text-white">
        <div class="card-body">
            <h5>Supprimer votre compte</h5>
            <p>Attention : Cette action est irréversible !</p>
            <a href="delete-entraineur?id=<%= entraineur.getId() %>" class="btn btn-danger" onclick="return confirm('Êtes-vous sûr de vouloir supprimer votre compte ?');">Supprimer mon compte</a>
        </div>
    </div>
</div>
</body>
</html>