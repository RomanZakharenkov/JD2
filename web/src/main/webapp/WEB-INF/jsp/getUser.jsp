<%--
  Created by IntelliJ IDEA.
  User: Рома
  Date: 14.10.2018
  Time: 19:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User</title>
</head>
<body>
<h1>HELLO!</h1><br>
<h1>${requestScope.user.id}</h1><br>
<h1>${requestScope.user.role.name}</h1><br>
<h1>${requestScope.user.firstName}</h1><br>
<h1>${requestScope.user.lastName}</h1><br>
<h1>${requestScope.user.email}</h1><br>
<h1>${requestScope.user.number}</h1>
</body>
</html>
