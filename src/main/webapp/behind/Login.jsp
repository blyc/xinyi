<%@ page import="behind.bean.Adminuser" %>
<%@ page import="javax.xml.stream.Location" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  Adminuser: Lyc
  Date: 2018/10/15
  Time: 18:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
<%

    String path = request.getContextPath();
    String adminUsername = "";
    String adminPassword = "";
    Cookie [] cookies =request.getCookies();
    if (cookies != null) {
        for (Cookie cookie:cookies) {
            if("adminUsername".equals(cookie.getName())){
                adminUsername = cookie.getValue();
            }else if("adminPassword".equals(cookie.getName())){
                adminPassword = cookie.getValue();
            }
        }
        if(adminUsername!=null&&adminPassword!=null&&!"".equals(adminUsername)&&!"".equals(adminPassword)){
            request.setAttribute("username",adminUsername);
            request.setAttribute("password",adminPassword);
            request.getRequestDispatcher(path+"/Servlet_LoginIn").forward(request, response);
        }
    }
%>
<form action="<%=path%>/Servlet_LoginIn" method="post">
    用户名:<input type="text" name="username" required value="<%=adminUsername%>"><br>
    密码:<input type="password" name="password" required value="<%=adminPassword%>"><br>
    <input type="submit" value="登录">
</form>
<a href="Register.jsp">注册</a>
</body>
</html>
