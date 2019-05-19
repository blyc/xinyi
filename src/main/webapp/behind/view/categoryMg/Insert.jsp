<%--
  Created by IntelliJ IDEA.
  User: Lyc
  Date: 2018/11/2
  Time: 11:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Insert</title>
    <script src="../../js/jquery-3.3.1.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="../../js/insert.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>

<script type="text/javascript" charset="utf-8">
    $(function () {
        $("body").on("click","#insert",function () {
            insert(opener.url,"isInsert");
        });
    })
</script>
</body>
</html>
