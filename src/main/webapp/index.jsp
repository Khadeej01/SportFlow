<%@ page contentType="text/html; charset=UTF-8" language="java" %>
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
            background-color: #A1A1A9; /* Gris neutre comme fond principal */
            color: #CDD4DF; /* Bleu-gris très clair pour le texte */
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .container-fluid {
            min-height: 100vh;
            display: flex;
            width: 100%;
            max-width: 1200px;
            overflow: hidden;
        }
        .left-section {
            flex: 1;
            padding: 40px;
            background-color: #852AB3; /* Violet foncé pour la section gauche */
            height: 100vh;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: flex-start;
        }
        .left-section h1 {
            font-size: 3rem;
            color: #A9C4D8; /* Bleu pâle pour le titre */
        }
        .left-section h1 .highlight {
            color: #ff4d4d; /* Rouge pour "STRONG" */
        }
        .left-section p {
            font-size: 1.2rem;
            margin-bottom: 20px;
            color: #BEB9D8; /* Bleu-gris clair */
        }
        .right-section {
            flex: 1;
            background-image: url('https://images.unsplash.com/photo-1593079831268-3381b0db4a77?ixlib=rb-4.0.3&auto=format&fit=crop&w=1350&q=80');
            background-size: cover;
            background-position: center;
            height: 100vh;
            position: relative;
            filter: brightness(0.8); /* Assombrit légèrement l'image pour un effet néon */
        }
        .neon-text {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            font-size: 4rem;
            color: #ffffff;
            text-shadow: 0 0 10px #ffffff, 0 0 20px #ffffff, 0 0 30px #ff00de, 0 0 40px #ff00de;
            font-weight: bold;
            text-align: center;
        }
        .login-form {
            background-color: #CDD4DF; /* Bleu-gris très clair pour le formulaire */
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
            width: 100%;
            max-width: 400px;
            margin-top: 20px;
        }
        .btn-custom {
            background-color: #852AB3; /* Violet foncé pour les boutons */
            color: #A9C4D8; /* Bleu pâle pour le texte des boutons */
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            width: 100%;
        }
        .btn-custom:hover {
            background-color: #BEB9D8; /* Bleu-gris clair au survol */
            color: #852AB3; /* Violet foncé */
        }
        .nav-links {
            position: absolute;
            top: 20px;
            right: 20px;
        }
        .nav-links a {
            color: #A9C4D8;
            margin-left: 15px;
            text-decoration: none;
            font-size: 1.1rem;
        }
        .nav-links a:hover {
            color: #BEB9D8;
        }
    </style>
</head>
<body>

<div class="nav-links">
    <a href="login.jsp">Connexion</a>
    <a href="RoleChoice.jsp">Inscription</a>
</div>

<div class="container-fluid">

    <div class="left-section">
        <h1>Build a <span class="highlight">STRONG</span> BODY</h1>
        <p>Rejoignez SportFlow pour atteindre vos objectifs sportifs.</p>


        <div class="login-form">
            <h3 style="color: #852AB3;">Connexion</h3>
            <form action="login" method="post">
                <div class="mb-3">
                    <label for="email" class="form-label" style="color: #A1A1A9;">Email</label>
                    <input type="email" class="form-control" id="email" name="email" required>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label" style="color: #A1A1A9;">Mot de passe</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                </div>
                <button type="submit" class="btn btn-custom">Se connecter</button>
            </form>
            <% if (request.getParameter("error") != null) { %>
            <p class="text-danger mt-3">
                <%= request.getParameter("error").equals("invalid") ? "Email ou mot de passe incorrect." : "Une erreur s'est produite." %>
            </p>
            <% } %>
        </div>
    </div>


    <div class="right-section">
        <div class="neon-text">GYM</div>
    </div>
</div>
</body>
</html>