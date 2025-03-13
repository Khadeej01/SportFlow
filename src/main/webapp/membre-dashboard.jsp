<%@ page import="Membre.Model.Membre" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Tableau de Bord Membre</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #212529; /* Fond sombre comme dans l'exemple */
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            color: #000;
        }
        .dashboard-container {
            background-color: #8ab4f8; /* Bleu clair comme dans l'exemple */
            padding: 20px;
            border-radius: 15px;
            text-align: center;
            width: 80%;
            max-width: 600px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
        }
        .welcome-text {
            font-size: 1.5rem;
            font-weight: bold;
            margin-bottom: 20px;
        }
        .info-section {
            background-color: #7eb6ff; /* Légèrement plus foncé pour la section info */
            padding: 15px;
            border-radius: 10px;
            margin-top: 20px;
        }
        .info-text {
            font-size: 1.2rem;
            margin-bottom: 10px;
        }
        .btn-custom {
            background-color: #007bff;
            border: none;
            padding: 10px 20px;
            margin: 5px;
            color: white;
            border-radius: 5px;
        }
        .btn-custom:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<div class="dashboard-container">
    <h2 class="mb-4">Tableau de Bord Membre</h2>
    <%
        Object user = session.getAttribute("user");
        if (user instanceof Membre) {
            Membre membre = (Membre) user;
    %>
    <p class="welcome-text">Bienvenue, <%= membre.getNom() %> !</p>


    <div class="info-section">
        <h3 class="info-text">INFORMATION</h3>
        <p class="info-text">Nom: <%= membre.getNom() %></p>
        <p class="info-text">Email: <%= membre.getEmail() %></p> <!-- Assure-toi que getEmail() existe -->
        <p class="info-text">Sport: <%= membre.getSportPratique() != null ? membre.getSportPratique() : "Non défini" %></p> <!-- Assure-toi que getSportPratique() existe -->
        <div>
            <a href="edit-member?id=<%= membre.getId() %>" class="btn btn-custom">Modifier</a>
            <a href="delete-member?id=<%= membre.getId() %>" class="btn btn-custom" onclick="return confirm('Êtes-vous sûr de vouloir supprimer votre compte ?')">Supprimer</a>
        </div>
    </div>
    <%
    } else {
    %>
    <p class="text-danger">Vous n'êtes pas connecté ou vous n'êtes pas un membre. <a href="login.jsp">Connectez-vous</a>.</p>
    <%
        }
    %>
</div>
</body>
</html>

