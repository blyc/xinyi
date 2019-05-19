$(function () {
    let $head = $("#head",window.opener.document);
    let $hidden = $(".hidden",window.opener.document);
    for (let i = 0; i < $hidden.length ; i++) {
        if(i==0){
            $("body").append(`${$head.children(":eq("+i+")").html()}:<input id="${$hidden.eq(i).attr("id")}" type="text" required disabled value="${$hidden.eq(i).val()}"><br>`);
        }else{
            $("body").append(`${$head.children(":eq("+i+")").html()}:<input id="${$hidden.eq(i).attr("id")}" type="text" required value="${$hidden.eq(i).val()}"><br>`);
        }
    }
    $("body").append(`<button id="edit">确定</button>`);
});

function edit(url,message){
    let isN = true;
    let $body = $("body").children("input,select");
    for (let i = 0; i < $body.length ; i++) {
        if ($body.eq(i).val()==null||$body.eq(i).val()==""){
            isN = false;
            return;
        }
    }
    if(isN){
        let dt = {type:"edit"};
        for (let i = 0; i < $body.length ; i++) {
            dt[$body.eq(i).attr("id")] = $body.eq(i).val();
        }
        $.ajax({
            type: "POST",
            url: url,
            dataType: "json",
            data: dt,
            success: function (data) {
                if(data[message]){
                    alert("修改成功!");
                    $("#update",window.opener.document).click();
                    window.opener=null;window.close();
                }else{
                    alert("修改失败!");
                    window.opener=null;window.close();
                }
            },
            error: function () {
                alert("数据加载失败");
            }
        });
    }
}
