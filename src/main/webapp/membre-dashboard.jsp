<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="Membre.Model.Membre" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tableau de Bord Membre - SportFlow</title>
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
    Membre membre = (currentSession != null) ? (Membre) currentSession.getAttribute("user") : null;
    if (membre == null || !"membre".equals(membre.getRole())) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<div class="container">
    <h2>Tableau de Bord Membre</h2>
    <p>Bienvenue, <%= membre.getNom() %> !</p>
    <a href="logout" class="btn btn-secondary mb-3">Déconnexion</a>

    <!-- Affichage des informations -->
    <div class="card bg-dark text-white mb-3">
        <div class="card-body">
            <h5>Vos Informations</h5>
            <p><strong>Nom :</strong> <%= membre.getNom() %></p>
            <p><strong>Email :</strong> <%= membre.getEmail() %></p>
            <p><strong>Date de naissance :</strong> <%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(membre.getDateNaissance()) %></p>
            <p><strong>Sport pratiqué :</strong> <%= membre.getSportPratique() %></p>
        </div>
    </div>

    <!-- Formulaire de modification -->
    <div class="card bg-dark text-white mb-3">
        <div class="card-body">
            <h5>Modifier vos informations</h5>
            <form action="update-membre" method="post">
                <input type="hidden" name="id" value="<%= membre.getId() %>">
                <div class="mb-3">
                    <label for="nom" class="form-label">Nom</label>
                    <input type="text" class="form-control" id="nom" name="nom" value="<%= membre.getNom() %>" required>
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" id="email" name="email" value="<%= membre.getEmail() %>" required>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Nouveau mot de passe (laisser vide si inchangé)</label>
                    <input type="password" class="form-control" id="password" name="password">
                </div>
                <div class="mb-3">
                    <label for="dateNaissance" class="form-label">Date de naissance</label>
                    <input type="date" class="form-control" id="dateNaissance" name="dateNaissance" value="<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(membre.getDateNaissance()) %>" required>
                </div>
                <div class="mb-3">
                    <label for="sport" class="form-label">Sport pratiqué</label>
                    <input type="text" class="form-control" id="sport" name="sport" value="<%= membre.getSportPratique() %>" required>
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
                    <th>Entraîneur</th>
                    <th>Date et Heure</th>
                </tr>
                </thead>
                <tbody>
                <%
                    SeanceDAO seanceDAO = new SeanceDAO();
                    EntraineurDAO entraineurDAO = new EntraineurDAO();
                    List<Seance> mesSeances = seanceDAO.getSeancesByMembreId(membre.getId());
                    for (Seance seance : mesSeances) {
                        Entraineur entraineur = entraineurDAO.getAllEntraineurs().stream().filter(e -> e.getId() == seance.getEntraineurId()).findFirst().orElse(null);
                %>
                <tr>
                    <td><%= seance.getId() %></td>
                    <td><%= entraineur != null ? entraineur.getNom() : "Inconnu" %></td>
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
            <a href="delete-membre?id=<%= membre.getId() %>" class="btn btn-danger" onclick="return confirm('Êtes-vous sûr de vouloir supprimer votre compte ?');">Supprimer mon compte</a>
        </div>
    </div>
</div>
</body>
</html>