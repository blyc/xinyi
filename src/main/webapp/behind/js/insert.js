$(function () {
    let $head = $("#head",window.opener.document);
    let $hidden = $(".hidden",window.opener.document);
    for (let i = 1; i < $hidden.length ; i++) {
        $("body").append(`${$head.children(":eq("+i+")").html()}:<input id="${$hidden.eq(i).attr("id")}" type="text" required><br>`);
    }
    $("body").append(`<button id="insert">确定</button>`);
});

function insert(url,message){
    let isN = true;
    let $body = $("body").children("input,select");
    for (let i = 0; i < $body.length ; i++) {
        if ($body.eq(i).val()==null||$body.eq(i).val()==""){
            isN = false;
            return;
        }
    }
    if(isN){
        let dt = {type:"insert"};
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
                    alert("添加成功!");
                    $("#update",window.opener.document).click();
                    window.opener=null;window.close();
                }else{
                    alert("添加失败!");
                    window.opener=null;window.close();
                }
            },
            error: function () {
                alert("数据加载失败");
            }
        });
    }
}
