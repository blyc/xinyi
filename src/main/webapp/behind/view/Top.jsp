<%@ page import="behind.bean.Adminuser" %><%--
  Created by IntelliJ IDEA.
  User: Lyc
  Date: 2018/11/5
  Time: 8:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Top</title>
</head>
<body>
<%
    Adminuser adminuser = (Adminuser)session.getAttribute("adminuser");
    if(adminuser==null){
        response.sendRedirect("../Login.jsp");
    }else{
        out.println("您已登陆，欢迎您 "+adminuser.getUsername()+"<br>");
        out.println("session 60*60 s 后过期 <br>");
    }
%>
<form action="/Servlet_LoginOut" method="post"><input type="submit" value="注销"></form>
</body>
</html>
