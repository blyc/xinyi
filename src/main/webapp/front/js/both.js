$(function () {
	//一级分类跳转
	toProductlist();

	//登录
	$(".i2").click(function () {
		var message = {};
		message.username = $("form input[name='username']").val();
		message.password = $("form input[name='password']").val();
		console.log(message);
		$.ajax({
			type:"GET",
			url:"http://47.100.205.249/jqueryProject/UserLogin",
			dataType:"jsonp",
			data:message,
			jsonpCallback:"success_jsonpCallback",
			success:function(data){
				console.log(data);
				if (data.flag) {
					$("#wenzi").html(message.username);
				}else{
					alert("用户名或者密码不正确!");
				}
			}
		});
	});
});
//一级菜单
function loadMain() {
	$.ajax({
		type:"GET",
		url:"http://47.100.205.249/jqueryProject/CategoryList",
		dataType:"jsonp",
		jsonpCallback:"success_jsonpCallback",
		success:function(data){
			data.forEach(v => $(".section_headerder ul").append(`<li onclick="searchCategory(${v.cid})"><a href="#">${v.cname}</a></li>`));
		}
	});
}
//跳转
function toProductlist() {
	$(".section_headerder ul").on("click","li",function () {
		var index = $(this).index(".section_headerder ul li");
		if(index == 0){
			window.location = "index.html";
		}else{
			window.location = "productlist.html";
		}
	});
}