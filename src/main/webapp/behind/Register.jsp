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
    <title>注册</title>
    <script src="js/jquery-3.3.1.min.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript" charset="utf-8">
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
        function checkRpw() {
            let isChecked = false;
            let pwd = $("#password").val();
            let repwd = $("#repassword").val();
            if(pwd===repwd){
                $("#repassword_prompt").css("color","#53ff4b");
                $("#repassword_prompt").html("√");
                isChecked = true;
            }else{
                $("#repassword_prompt").css("color","#ff1f13");
                $("#repassword_prompt").html("×");
            }
            return isChecked;
        }
        $(function () {
            $("form").submit(function (){
                let a=checkUm();
                if(checkPw()&&checkRpw()&&a){
                    return true;
                }else{
                    return false;
                }
            })
        });
    </script>
</head>
<body>
<form action="/Servlet_Register" method="post">
    <input type="hidden" name="type" value="register">
    用户姓名: <input type="text" id="username" name="username" placeholder="4-18位的字母" onblur="checkUm()"><span id="username_prompt"></span><br>
    用户密码:<input type="password" id="password" name="password" placeholder="6-18位的数字字母组成" onblur="checkPw()"><span id="password_prompt"></span><br>
    确认密码:<input type="password" id="repassword" onblur="checkRpw()"><span id="repassword_prompt"></span><br>
    <input type="submit" value="注册">
</form>
</body>
</html>
