<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User</title>
</head>
<body>
<h2>HELLO!</h2><br>
<h2>${requestScope.user.id}</h2><br>
<h2>${requestScope.user.role.name}</h2><br>
<h2>${requestScope.user.firstName}</h2><br>
<h2>${requestScope.user.lastName}</h2><br>
<h2>${requestScope.user.email}</h2><br>
<h2>${requestScope.user.number}</h2>
</body>
</html>
