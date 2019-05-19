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
    <title>AdminMg</title>
    <link rel="stylesheet" href="../../css/all.css">
    <script src="../../js/jquery-3.3.1.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="../../js/page.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript" charset="utf-8">
        var url = "/Servlet_UserMg";
        $(function () {
            var curPage = 1; //当前页码
            var trs = ["序号","用户名","密码","姓名","邮箱","电话号码","地址","是否激活"];
            var json1 = ["userList","uid","username","password","name","email","phone","str","state"];
            var json2 = json1.slice();
            json2.shift();
            //列名数组
            trs.push("编辑","删除");
            append(trs);
            update();
            function update() {
                //servlet的url  【json中的list数组名,对象属性名】  当前页
                subup(url,json1,curPage,function (v,message) {
                    let $tr = $("<tr></tr>");
                    for(let i=1;i<message.length;i++){
                        let mi = message[i];
                        if(mi=="state"){
                            if(v[mi]==0){
                                $tr.append(`<td>未激活</td>`);
                            }else{
                                $tr.append(`<td>已激活</td>`);
                            }
                            continue;
                        }
                        $tr.append(`<td>${v[mi]}</td>`);
                    }
                    $tr.append(`<td><button class="edit">编辑</button></td><td><button class="del">删除</button></td>`);
                    $("tbody").append($tr);
                })
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
