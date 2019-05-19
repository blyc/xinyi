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
        var url = "/Servlet_ProductMg";
        $(function () {
            var curPage = 1; //当前页码
            var trs = ["序号","商品名称","图片","商品价格","是否热卖","二级分类名称"];
            var json1 = ["productList","pid","pname","image","shopprice","ishot","csname"];
            var json2 = json1.slice();
            json2.shift();
            trs.push("编辑","删除");

            append(trs);
            update();
            function update() {
                //servlet的url  【json中的list数组名,对象属性名】  当前页
                subup(url,json1,curPage,function (v,message) {
                    let $tr = $("<tr></tr>");
                    for(let i=1;i<message.length;i++){
                        let mi = message[i];
                        if(mi=="ishot"){
                            if(v[mi]==0){
                                $tr.append(`<td>否</td>`);
                            }else{
                                $tr.append(`<td>是</td>`);
                            }
                            continue;
                        }else if(mi=="image"){
                            $tr.append(`<td><img src="../../../images/upload/${v[mi]}" height="60px;"></td>`);
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
