$(function () {
	//二级分类
	loadSub();
	function loadSub() {
		$.ajax({
			type:"GET",
			url:"http://47.100.205.249/jqueryProject/CategorysecondList",
			dataType:"jsonp",
			jsonpCallback:"success_jsonpCallback",
			complete:loadMain,//请求完成时运行的函数,在请求成功或失败之后均调用 100次未测试出错,不确定是否存在出错
			// beforeSend:loadMain,//发送请求前运行 出错率较大降低,但仍存在
			success:function(data){
				var cid;
				var $div;
				$(data).each(function () {
					if (this.cid==cid) {
						$div.append(`<span searchCategorysecond(${this.csid})>${this.csname}</span>`);
					}else{
						cid = this.cid;
						$(".productcategory").append(`<div onclick="searchCategory(${cid})" class="category">${this.category.cname}</div>`);
						$div = $("<div></div>");
						$(".productcategory").append($div);
						$div.append(`<span searchCategorysecond(${this.csid})>${this.csname}</span>`);
					}
				});
			}
		});
	}
	//商品列表
	// loadP();
	// function loadP() {
	// 	$.ajax({
	// 		type:"GET",
	// 		url:"http://47.100.205.249/jqueryProject/ProductList",
	// 		dataType:"jsonp",
	// 		jsonpCallback:"success_jsonpCallback1",
	// 		success:function(data){
	// 			data.forEach(v => $(".products").append(`<div class="product"><img src="images/upload/${v.image}"><div class="productName">${v.pname}</div><div class="productPrice">商城价：￥${v.shopprice}</div></div>`));
	// 		}
	// 	});
	// }
	var curPage = 1; //当前页码
	var total;//总记录数
	var totalPage;//总页数
	var message;//总记录
	var pageSize = 10;//每页显示条数
	$.ajax({
    type:"GET",
    url:"http://47.100.205.249/jqueryProject/ProductList",
    dataType:"jsonp",
    jsonpCallback:"success_jsonpCallback1",
    success:function(data){
        message = data;
        total = data.length; //总记录数
        totalPage = Math.ceil(total/pageSize); //总页数
    },
    complete:function(){
      getData();
      $("#lastPage").text(totalPage);
			$("#sl2").text("...");
			$("#sl1").text("...");
      getPageBar();

    },
    error:function(){
        alert("数据加载失败");
    }
	});
	function getData(){
    $(".products").html("");
    $.each(message,function(i,v){
        if (i<(curPage-1)*10||i>=curPage*10) {}
        else{
            $(".products").append(`<div class="product"><img src="images/upload/${v.image}"><div class="productName">${v.pname}</div><div class="productPrice">商城价：￥${v.shopprice}</div></div>`);
        }
    });
    getPageBar();
	}
	function getPageBar(){
		var t;
		var $num = $(".num");
		if(curPage==1){
			$("#previousPage").css("display","none");
			$("#nextPage").css("display","");
		}else if(curPage==totalPage){
			$("#previousPage").css("display","");
			$("#nextPage").css("display","none");
		}else{
			$("#previousPage").css("display","");
			$("#nextPage").css("display","");
		}
		if (totalPage>7&&(curPage-5)<1) {
			$("#sl1").css("display","none");
			$("#sl2").css("display","");
			t = 2;
			$num.each(function (i,n) {
				$(n).text(t++);
			});
		}else if(totalPage>7&&(curPage+5)>totalPage){
			$("#sl1").css("display","");
			$("#sl2").css("display","none");
			t = totalPage-1;
			for (var i = 4; i >= 0; i--) {
				$($num[i]).text(t--);
			}
		}else if(totalPage>7){
			$("#sl1").css("display","");
			$("#sl2").css("display","");
			t = curPage-2;
			$num.each(function (i,n) {
				$(n).text(t++);
			});
		}else{
			$("#sl1").css("display","none");
			$("#sl2").css("display","none");
			t = 2;
			$num.each(function (i,n) {
				if(t<totalPage){
					$(n).text(t++);
				}else{
					$(n).css("display","none");
				}
			});
		}
		$(".page span").css("backgroundColor","lightblue");
		$(".page span").each(function (i,n) {
			if($(n).text()==curPage){
				$(n).css("backgroundColor","rgb(19, 209, 190)");
				return;
			}
		});
	}
	$(".page").on('click','span',function(){
		var v = $(this).text();
    if(v=="＞"){
    	curPage++;
    }else if(v=="＜"){
    	curPage--;
    }else if(v=="..."){
    }else{
    	curPage=parseInt(v);
    }
    getData();
	});
});