<%--
  Created by IntelliJ IDEA.
  User: Lyc
  Date: 2018/11/2
  Time: 9:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main</title>
</head>
<style type="text/css">
    *{margin: 0;padding: 0}
    body{width: 100%;height: 100%}
    #top{width: 100%;height: 13%;background-color: #C5C0DD}
    #main{width: 100%;height: 87%}
    #left{width: 10%;height: 100%;float: left;background-color: #83dda5
    }
    #right{width: 90%;height: 100%;float: right;background-color: #ddda8e
    }
</style>
<script src="../js/jquery-3.3.1.min.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" charset="utf-8">
    $(function () {
        $("#top").load("Top.jsp");
        $("#left").load("Left.jsp");
        $("#right").load("Right.jsp");
    })
</script>
<div id="top"></div>
<div id="main">
    <div id="left"></div>
    <div id="right"></div>
</div>
</html>
