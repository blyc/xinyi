$(function () {
	//一级分类
	loadMain();

	//注册点击跳转
	$("#register").click(function () {
		window.location = "register.html";
	});

	//登录键盘事件 Enter==13
	$(document).keypress(function(event){
		if(event.keyCode==13) {
			$(".i2").click();
		}
	});

	//轮播图 1s
	var imgs = ["lunbo1.jpg","lunbo2.jpg","lunbo3.jpg","lunbo4.jpg"];
	$(".lunbo_div1").eq(0).css("backgroundColor","violet");
	var i = 1;
	setInterval(function () {
		$(".lunbo_div1").css("backgroundColor","");
		$(".lunbo_div1").eq(i).css("backgroundColor","violet");
		$(".lunbo #img").attr("src","images/front/"+imgs[i++]);
		if (i>=4) {i=0;}
	},1000);

	//热门商品&最新商品
	hot_new();
	function hot_new() {
		//热门商品
		$.ajax({
			type:"GET",
			url:"http://47.100.205.249/jqueryProject/ProductHotList",
			dataType:"jsonp",
			jsonpCallback:"success_jsonpCallback1",
			success:function(data){
				var i = 0;
				for (var info of data) {
					if (i<=4) {
						$(".product .images:eq(0)").append(`<img src="images/upload/${info.image}">`);
					}else{
						$(".product .images:eq(1)").append(`<img src="images/upload/${info.image}">`);
					}
					i++;
				}
			}
		});
		//最新商品
		$.ajax({
			type:"GET",
			url:"http://47.100.205.249/jqueryProject/ProductNewList",
			dataType:"jsonp",
			jsonpCallback:"success_jsonpCallback2",
			success:function(data){
				var i = 0;
				for (var info of data) {
					if (i<=4) {
						$(".product .images:eq(2)").append(`<img src="images/upload/${info.image}">`);
					}else{
						$(".product .images:eq(3)").append(`<img src="images/upload/${info.image}">`);
					}
					i++;
				}
			}
		});
	}
});