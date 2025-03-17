<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Ajouter un Entraîneur - SportFlow</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Inclure le même CSS -->
</head>
<body>
<div class="main-content">
    <div class="dashboard">
        <h2>Ajouter un Entraîneur</h2>
        <form action="add-entraineur" method="post">
            <div class="mb-3">
                <label for="nom" class="form-label">Nom</label>
                <input type="text" class="form-control" id="nom" name="nom" required>
            </div>
            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="email" class="form-control" id="email" name="email" required>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Mot de passe</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
            <div class="mb-3">
                <label for="specialite" class="form-label">Spécialité</label>
                <input type="text" class="form-control" id="specialite" name="specialite" required>
            </div>
            <button type="submit" class="btn btn-custom">Ajouter</button>
        </form>
    </div>
</div>
</body>
</html>