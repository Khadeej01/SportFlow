<%@ page import="Admin.Model.Admin" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Tableau de Bord Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2>Tableau de Bord Administrateur</h2>
    <p>Bienvenue, <%= ((Admin.Model.Admin) session.getAttribute("user")).getNom() %> !</p>
    <div class="list-group">
        <a href="membre" class="list-group-item list-group-item-action">Gérer les Membres</a>
        <a href="entraineur" class="list-group-item list-group-item-action">Gérer les Entraîneurs</a>
        <a href="seance" class="list-group-item list-group-item-action">Gérer les Séances</a>
        <a href="logout" class="list-group-item list-group-item-action">Déconnexion</a>
    </div>
</div>
</body>
</html>