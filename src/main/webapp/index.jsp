<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SportFlow</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            margin: 0;
            font-family: Arial, sans-serif;
            min-height: 100vh;
            background-color: #A1A1A9; /* Gris neutre comme fond de secours */
            color: #CDD4DF; /* Bleu-gris très clair pour le texte */
            display: flex;
            flex-direction: column;
        }
        .main-section {
            flex: 1;
            background-image: url('https://images.unsplash.com/photo-1593079831268-3381b0db4a77?ixlib=rb-4.0.3&auto=format&fit=crop&w=1350&q=80');
            background-size: cover;
            background-position: center;
            height: 100vh;
            position: relative;
            filter: brightness(0.8); /* Assombrit légèrement l'image pour un effet néon */
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .neon-text {
            font-size: 4rem;
            color: #ffffff;
            text-shadow: 0 0 10px #ffffff, 0 0 20px #ffffff, 0 0 30px #ff00de, 0 0 40px #ff00de;
            font-weight: bold;
            text-align: center;
        }
    </style>
</head>
<body>
<!-- Barre de navigation (from "Cours" page) -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="#">SportFlow Gym</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item"><a class="nav-link" href="admin-form.jsp">Tableau de bord</a></li>
                <li class="nav-item"><a class="nav-link" href="seances.jsp">Cours</a></li>
                <li class="nav-item"><a class="nav-link" href="entraineur-form.jsp">Coachs</a></li>
                <li class="nav-item"><a class="nav-link" href="membre-form.jsp">Profil</a></li>
                <li class="nav-item"><a class="nav-link btn btn-danger ms-2" href="login.jsp">Déconnexion</a></li>
            </ul>
        </div>
    </div>
</nav>

<!-- Main Content: Full Background Image with Neon Text -->
<div class="main-section">
    <div class="neon-text"></div>
</div>

<!-- Pied de page (from "Cours" page) -->
<footer class="text-center mt-5 p-3 bg-light">
    <p>© 2025 SportFlow Gym. Restez en forme, restez forts ! 💪</p>
</footer>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>