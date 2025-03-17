<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Membre.DAO.MembreDAO" %>
<%@ page import="Membre.Model.Membre" %>
<%@ page import="Entraineur.DAO.EntraineurDAO" %>
<%@ page import="Entraineur.Model.Entraineur" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Ajouter une Séance - SportFlow</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Inclure le même CSS -->
</head>
<body>
<%
    MembreDAO membreDAO = new MembreDAO();
    EntraineurDAO entraineurDAO = new EntraineurDAO();
    List<Membre> membres = membreDAO.getAllMembres();
    List<Entraineur> entraineurs = entraineurDAO.getAllEntraineurs();
%>
<div class="main-content">
    <div class="dashboard">
        <h2>Ajouter une Séance</h2>
        <form action="add-seance" method="post">
            <div class="mb-3">
                <label for="membre" class="form-label">Membre</label>
                <select class="form-control" id="membre" name="membreId" required>
                    <% for (Membre membre : membres) { %>
                    <option value="<%= membre.getId() %>"><%= membre.getNom() %></option>
                    <% } %>
                </select>
            </div>
            <div class="mb-3">
                <label for="entraineur" class="form-label">Entraîneur</label>
                <select class="form-control" id="entraineur" name="entraineurId" required>
                    <% for (Entraineur entraineur : entraineurs) { %>
                    <option value="<%= entraineur.getId() %>"><%= entraineur.getNom() %></option>
                    <% } %>
                </select>
            </div>
            <div class="mb-3">
                <label for="dateHeure" class="form-label">Date et Heure</label>
                <input type="datetime-local" class="form-control" id="dateHeure" name="dateHeure" required>
            </div>
            <button type="submit" class="btn btn-custom">Ajouter</button>
        </form>
    </div>
</div>
</body>
</html>