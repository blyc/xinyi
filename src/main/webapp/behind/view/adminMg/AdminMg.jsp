<%--
  Created by IntelliJ IDEA.
  User: Lyc
  Date: 2018/11/5
  Time: 10:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        String path = request.getContextPath();
    %>
    <title>AdminMg</title>
    <link rel="stylesheet" href="../../css/all.css">
    <script src="../../js/jquery-3.3.1.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="../../js/page.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript" charset="utf-8">
        $(function () {
            var curPage = 1; //当前页码
            var trs = ["编号","用户名","密码"];
            var json1 = ["adminuserList","uid","username","password"];
            var json2 = json1.slice();
            var url = "<%=path%>/Servlet_AdminMg";
            json2.shift();
            //列名数组
            trs.push("编辑","删除");
            append(trs);
            update();
            function update() {
                //servlet的url  【json中的list数组名,对象属性名】  当前页
                subup(url,json1,curPage)
            }
            $("#page").on('click','span',function () {
                curPage = upBar(this,curPage);
                update();
            });
            $("tbody").on('click','#update',function () {
                update();
            });
            $("tbody").on('click','.del',function () {
                //servlet的url  id  json返回的isDel布尔值 结束后执行update
                del(url,$(this).parent().parent().children(0).html(),"isDel",update);
            });
            $("tbody").on('click','.edit',function () {
                //同目录下Edit.jsp   该列tr    同上subup处，无数组名
                edit($(this).parent().parent(),json2);
            });
            $("body").on('click','#insert',function () {
                insert();
            });
        });
    </script>
</head>
<body>
</body>
</html>
