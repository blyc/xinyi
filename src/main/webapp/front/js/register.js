$(function () {
	//随机验证码
	Ic();

	//修改提示颜色
	$(".notice").css("color","red");

	//用户名验证
	function checkUn() {
	    var val = $("#username").val();
	    var reg = /^[A-Za-z0]\w{5,17}$/;
	    if (reg.test(val)) {
	        return true;
	    }else{
	        return false;
	    }
	}
	//事件绑定用户名验证
	$("#username").blur(function () {
		if(checkUn()){
			$("#usernameNotice").text("");
		}else{
			$("#usernameNotice").html("以字母开头，长度6~18，只能包含字符、数字和下划线");
		}
	});
	//密码验证
	function checkPw() {
	    var val = $("#password").val();
	    var reg = /^[A-Za-z0]\w{5,17}$/;
	    if (reg.test(val)) {
	        return true;
	    }else{
	        return false;
	    }
	}
	//事件绑定密码验证
	$("#password").blur(function () {
		if(checkPw()){
			$("#passwordNotice").text("");
		}else{
			$("#passwordNotice").html("以字母开头，长度6~18，只能包含字符、数字和下划线");
		}
	});
	//二次密码验证
	function checkRpw() {
	    var val = $("#repeatPassword").val();
	    if (val==$("#password").val()) {
	        return true;
	    }else{
	        return false;
	    }
	}
	//事件绑定二次密码验证
	$("#repeatPassword").blur(function () {
		if(checkRpw()){
			$("#repeatPasswordNotice").text("");
		}else{
			$("#repeatPasswordNotice").html("两次密码不一致");
		}
	});
	//邮箱验证
	function checkEm() {
	    var val = $("#email").val();
	    var reg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
	    if (reg.test(val)) {
	        return true;
	    }else{
	        return false;
	    }
	}
	//事件绑定邮箱验证
	$("#email").blur(function () {
		if(checkEm()){
			$("#emailNotice").text("");
		}else{
			$("#emailNotice").html("邮箱格式不正确");
		}
	});
	//手机号码验证
	function checkTl() {
	    var val = $("#phone").val();
	    var reg = /^1[34578]\d{9}$/;
	    if (reg.test(val)) {
	        return true;
	    }else{
	        return false;
	    }
	}
	//事件绑定手机号码验证
	$("#phone").blur(function () {
		if(checkTl()){
			$("#phoneNotice").text("");
		}else{
			$("#phoneNotice").html("电话格式不正确");
		}
	});
	//验证码验证
	function checkIc() {
	    var val = $("#inputCode").val();
	    if(val.length<=0){
	    	$("#codeNotice").html("请输入验证码");
	      return false;
	    }else if(val==$("#imgCode").text()){
	    	$("#codeNotice").html("");
	      return true;
	    }else{
	    	$("#codeNotice").html("验证码输入有误");
	      return false;
	    }
	}
	//事件绑定验证码验证
	$("#inputCode").blur(function () {checkIc();});
	//验证码点击刷新
	$("#imgCode").click(function () {Ic();});
	//#wenzi div鼠标悬浮事件
	$("#wenzi").hover(function () {
		$("#wenzi").attr("name","t");
	},function () {
		$("#wenzi").attr("name","f");
	});
	//键盘事件 Enter
	$(document).keypress(function(event){
		if(event.keyCode==13) {
			if ($("#wenzi").attr("name")=="t") {
				$(".i2").click();
			}else{
				$("#ok").click();
			}
		}
	});
	//点击登录
	$("#ok").click(function () {
		if (checkUn()&&checkPw()&&checkRpw()&&checkEm()&&checkTl()&&checkIc()) {
			var message = {};
			message.username = $("#username").val();
			message.password = $("#password").val();
			message.email = $("#email").val();
			message.phone = $("#phone").val();
			message.addr = $("#addr").val();
			$.ajax({
				type:"GET",
				url:"http://47.100.205.249/jqueryProject/UserInsert",
				dataType:"jsonp",
				data:message,
				jsonpCallback:"success_jsonpCallback",
				success:function(data){
					if (data.flag) {
						window.location = "index.html";
					}else{
						alert("注册失败!");
					}
				}
			});
		}else{
			$("#username").blur();
			$("#password").blur();
			$("#repeatPassword").blur();
			$("#email").blur();
			$("#phone").blur();
			alert("请将个人信息补充完整!");
		}
	});
});
//验证码生成
var code;
function Ic() {
	code="";
	var codeLength=6;
	var selectChar=new Array(1,2,3,4,5,6,7,8,9,'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z');
	for(var i=0;i<codeLength;i++){
		var charIndex=Math.floor(Math.random()*61);
		code += selectChar[charIndex];
	}
	if (code.length != codeLength) {
		Ic();
	}
	$("#imgCode").text(code);
}