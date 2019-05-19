<%--
  Created by IntelliJ IDEA.
  User: Lyc
  Date: 2018/11/5
  Time: 8:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Left</title>
    <link rel="stylesheet" href="../css/zTreeStyle/zTreeStyle.css">
    <script src="../js/jquery-3.3.1.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="../js/jquery.ztree.all.min.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript" charset="utf-8">
        let zTreeObj,
        setting = {
            view: {
                selectedMulti: false,
                showLine: false
            }
        },
        zTreeNodes = [
            {"name":"用户管理", open:true,iconOpen:"../css/zTreeStyle/img/diy/1_open.png", iconClose:"../css/zTreeStyle/img/diy/1_close.png",
            children: [
                    { "name":"后台用户管理", "url":"adminMg/AdminMg.jsp", "target":"view",icon:"../css/zTreeStyle/img/diy/3.png"},
                    { "name":"前台用户管理", "url":"userMg/UserMg.jsp", "target":"view",icon:"../css/zTreeStyle/img/diy/3.png"}
                ]
            },
            {"name":"分类管理", open:false,iconOpen:"../css/zTreeStyle/img/diy/1_open.png", iconClose:"../css/zTreeStyle/img/diy/1_close.png",
                children: [
                    { "name":"一级分类管理", "url":"categoryMg/CategoryMg.jsp", "target":"view",icon:"../css/zTreeStyle/img/diy/5.png"},
                    { "name":"二级分类管理", "url":"categorySecondMg/CategorySecondMg.jsp", "target":"view",icon:"../css/zTreeStyle/img/diy/5.png"}
                ]
            },
            {"name":"商品管理", open:false,iconOpen:"../css/zTreeStyle/img/diy/1_open.png", iconClose:"../css/zTreeStyle/img/diy/1_close.png",
                children: [
                    { "name":"商品管理", "url":"productMg/ProductMg.jsp", "target":"view",icon:"../css/zTreeStyle/img/diy/4.png"}
                ]
            },
            {"name":"订单", open:false,iconOpen:"../css/zTreeStyle/img/diy/1_open.png", iconClose:"../css/zTreeStyle/img/diy/1_close.png",
                children: [
                    { "name":"订单列表", "url":"http://localhost:8080/decision/view/report?viewlet=WorkBook1.cpt", "target":"view",icon:"../css/zTreeStyle/img/diy/7.png"}
                ]
            }/*,
            {"name":"小项目", open:false,iconOpen:"../css/zTreeStyle/img/diy/1_open.png", iconClose:"../css/zTreeStyle/img/diy/1_close.png",
                children: [
                    { "name":"学生", "url":"#", "target":"view",icon:"../css/zTreeStyle/img/diy/8.png"},
                    { "name":"商品", "url":"#", "target":"view",icon:"../css/zTreeStyle/img/diy/8.png"},
                    { "name":"车辆信息", "url":"#", "target":"view",icon:"../css/zTreeStyle/img/diy/8.png"}
                ]
            }*/
        ];
        $(document).ready(function(){
            zTreeObj = $.fn.zTree.init($(".ztree"), setting, zTreeNodes);
        });
    </script>
</head>
<body>
<div class="ztree" style="height: 500px;"></div>
</body>
</html>
