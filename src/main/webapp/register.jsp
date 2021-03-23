<%--
  Created by IntelliJ IDEA.
  User: 17708
  Date: 2021/3/16
  Time: 23:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a>New User Registration!</a>
<form method="get ">
    <input type="text" value="Username" id="002"><br>
    <input type="password" value="Password" id="000" minlength="8"><br>
    <input type="email" value="Email" id="001"><br>
    Gender<input type="radio" name="Gender">Male
    <input type="radio" name="Gender">Female<br>
    <input type="datetime-local" value="Date of Birth(yyyy-mm-dd)"><br>
    <input type="submit" value="Register"><br>
    git config --global http.sslVerify false
</form>
</body>
</html>
