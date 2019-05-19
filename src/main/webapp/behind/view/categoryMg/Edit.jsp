<%--
  Created by IntelliJ IDEA.
  User: Lyc
  Date: 2018/11/7
  Time: 9:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit</title>
    <script src="../../js/jquery-3.3.1.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="../../js/edit.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
<script type="text/javascript" charset="utf-8">
    $(function () {
        $("body").on("click","#edit",function () {
            console.log(opener.url)
            edit(opener.url,"isEdit");
        });
    })
</script>
</body>
</html>
