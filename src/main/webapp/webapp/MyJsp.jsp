
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="index.jsp">go to ecjtu</a><!--method is GET-->
<form method="get "><!--what is method when we use form?--><!-- its GET,why?default is GET-->
   <!-- its better to use POSTin form-->
    Name:<input type="text" name="name"><br/>
    ID:<input type="text" name="id"><br/>
    <input type ="submit" value="send data to server"/>
</form>
</body>
</html>
