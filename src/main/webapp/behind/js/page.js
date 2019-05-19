function append(trs) {
    $("body").append(`<button id="insert">添加</button><table border="1" cellspacing="0"><thead><tr id="head"></tr></thead><tbody></tbody></table>
    <div id="page">
        <span id="previousPage">＜</span><span id="firstPage">1</span><span id="sl1">...</span>
        <span id="num1" class="num"></span><span id="num2" class="num"></span><span id="num3" class="num"></span><span id="num4" class="num"></span><span id="num5" class="num"></span>
        <span id="sl2">...</span><span id="lastPage"></span><span id="nextPage">＞</span>
    </div>`);
    $.each(trs,function (i,v) {
        $("#head").append(`<td>${v}</td>`);
    });
}
function subup(url,message,curPage,uf) {
    let totalPage;
    $.ajax({
        type:"POST",
        url:url,
        dataType:"json",
        data:{type:"select",curPage:curPage},
        success:function(data){
            $("tbody").empty();
            let m0 = message[0];
            $.each(data[m0],function (i, v) {
                if(uf==null){
                    let $tr = $("<tr></tr>");
                    for(let i=1;i<message.length;i++){
                        let mi = message[i];
                        $tr.append(`<td>${v[mi]}</td>`)
                    }
                    $tr.append(`<td><button class="edit">编辑</button></td><td><button class="del">删除</button></td>`);
                    $("tbody").append($tr);
                }else{
                    uf(v,message)
                }
            });
            for(let i=1;i<message.length;i++){
                $("tbody").append(`<input class="hidden" id="${message[i]}" type="hidden">`)
            }
            $("tbody").append(`<div style="display: none;"><button id="update"></button></div>`)
            totalPage = data.totalPage; //总页数
        },
        complete:function(){
            $("#lastPage").text(totalPage);
            getPageBar(curPage,totalPage);
        },
        error:function(){
            alert("数据加载失败");
        }
    });
}
function getPageBar(curPage,totalPage){
    if(totalPage==1){
        $("#page").css("display","none");
    }else{
        $("#page").css("display","");
        let t;
        let $num = $(".num");
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
        $("#page span").css("backgroundColor","lightblue");
        $("#page span").each(function (i,n) {
            if($(n).text()==curPage){
                $(n).css("backgroundColor","rgb(19, 209, 190)");
                return;
            }
        });
    }
}
function upBar(arm,curPage){
    let v = $(arm).text();
    if(v=="＞"){
        curPage++;
    }else if(v=="＜"){
        curPage--;
    }else if(v=="..."){
    }else{
        curPage=parseInt(v);
    }
    return curPage;
}
function del(url,id,message,cm) {
    if(confirm("确定删除?")==true){
        let jsonData = {type:"del",idName:id};
        jsonData[$(".hidden").eq(0).attr("id")] = id;
        $.ajax({
            type:"POST",
            url:url,
            dataType:"json",
            data:jsonData,
            success:function (data) {
                if(data[message]){
                    alert("删除成功!")
                }else{
                    alert("删除失败!")
                }
            },
            complete:cm,
            error:function(){
                alert("error");
            }
        });
    }
}
function edit(arm,message) {
    for(let i=0;i<message.length;i++){
        $("#"+message[i]).val(arm.children(":eq("+i+")").html());
    }
    window.open ("Edit.jsp","","height=220,width=320,top=200,left=500,location=0,menubar=0,resizable=0,scrollbars=0,status=0,titlebar=0,toolbar=0");
}
function insert() {
    window.open ("Insert.jsp","","height=220,width=320,top=200,left=500,location=0,menubar=0,resizable=0,scrollbars=0,status=0,titlebar=0,toolbar=0");
}