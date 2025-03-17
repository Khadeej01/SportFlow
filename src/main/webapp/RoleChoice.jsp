<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Choose Role</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="flex justify-center items-center h-screen bg-gray-100">
<div class="bg-white p-8 rounded-lg shadow-md w-96">
    <h2 class="text-xl font-semibold text-center mb-6">Choose Your Role</h2>
    <div class="space-y-4">
        <a href="membre-form.jsp" class="block text-center bg-blue-500 text-white py-2 rounded-lg hover:bg-blue-600">I am a Member</a>
        <a href="entraineur-form.jsp" class="block text-center bg-green-500 text-white py-2 rounded-lg hover:bg-green-600">I am a Coach</a>
        <a href="admin-dashboard.jsp" class="block text-center bg-blue-500 text-white py-2 rounded-lg hover:bg-blue-600">I am a Admin</a>
    </div>
</div>
</body>
</html>