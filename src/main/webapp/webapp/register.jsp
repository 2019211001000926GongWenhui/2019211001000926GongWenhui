
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a>New User Registration!</a>
<form method="post" action="/register">
    username<input type="text" name="Username" ><br>
    password<input type="password" name="Password"><br>
    Email<input type="text" name="email"><br>
    Gender:<input type="radio" name="Gender" value="male">Male<input type="radio" name="Gender" value="female">Female<br>
    Date of Birth<input type="text name" name="birthDate"><br>
    <input type="submit" value="Register"><br>
</form>
</body>
</html>
