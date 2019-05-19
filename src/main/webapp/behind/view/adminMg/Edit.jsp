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
        var username = $("#username").val();
        $("body").on("click","#edit",function () {
            if(($("#username").val() ==username||checkUm())&&checkPw()){
                //url  json返回的判断值
                edit("/Servlet_AdminMg","isEdit");
            }
        });
        $("body").on("blur","#username",function () {
            if ($("#username").val() ==username){
                $("#username").next("#username_prompt").remove();
                $("#username").after("<span id=\"username_prompt\"></span>");
                $("#username_prompt").css("color","#53ff4b");
                $("#username_prompt").html("√");
            } else{
                $("#username").next("#username_prompt").remove();
                $("#username").after("<span id=\"username_prompt\"></span>");
                checkUm();
            }
        });
        $("body").on("blur","#password",function () {
            $("#password").next("#password_prompt").remove();
            $("#password").after("<span id=\"password_prompt\"></span>");
            checkPw();
        });
        function checkUm() {
            let isChecked = false;
            let pwd = $("#username").val();
            let reg = /^[A-Za-z]{4,18}$/;
            if(reg.test(pwd)){
                $.ajax({
                    type: "POST",
                    url: "/Servlet_Register",
                    dataType: "json",
                    async:false,
                    data: {type: "select",username:$("#username").val()},
                    success: function (data) {
                        if (!data.isRepetition){
                            $("#username_prompt").css("color","#53ff4b");
                            $("#username_prompt").html("√");
                            isChecked = true;
                        }else{
                            $("#username_prompt").css("color","#ff1f13");
                            $("#username_prompt").html("×");
                        }
                    },
                    error: function () {
                        alert("数据加载失败");
                    }
                });
            }else{
                $("#username_prompt").css("color","#ff1f13");
                $("#username_prompt").html("×");
            }
            return isChecked;
        }
        function checkPw() {
            let isChecked = false;
            let pwd = $("#password").val();
            let reg = /^[A-Za-z0-9]{6,18}$/;
            if(reg.test(pwd)){
                $("#password_prompt").css("color","#53ff4b");
                $("#password_prompt").html("√");
                isChecked = true;
            }else{
                $("#password_prompt").css("color","#ff1f13");
                $("#password_prompt").html("×");
            }
            return isChecked;
        }
    })
</script>
</body>
</html>
