
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="header.jsp"%>
<%
    if(request.getAttribute("message") != null) {
        out.print("<h3 style='color: red'>"+ request.getAttribute("message") +"</h3>");
    }
%>
<form action="login" method="post">
    <h1>Login</h1>
    username: <input type="text" name="username">
    password: <input type="password" name="password">
    <input type="submit" value="login">
</form>
<%@include file="footer.jsp"%>
</body>
</html>
