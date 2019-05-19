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
    <title>Insert</title>
    <script src="../../js/jquery-3.3.1.min.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
    商品名称:<input type="text" id="pname" name="pname" required><br>
    市场价格:<input type="text" id="marketprice" name="marketprice" required><br>
    商品价格:<input type="text" id="shopprice" name="shopprice" required><br>
    图片:<input type="file" id="image" name="image" required><br>
    是否热卖:<select id="ishot" name="ishot"><option value="0">否</option><option value="1">是</option></select><br>
    二级分类名称:<select id="csid" name="csid"></select><br>
    商品说明:<input type="text" id="pdesc" name="pdesc" required><br>
    <button id="insert">确定</button>
<script type="text/javascript" charset="utf-8">

    $(function () {
        let $csid = $("#csid");
        $.ajax({
            type:"POST",
            url:opener.url,
            dataType:"json",
            data:{type:"selectSP"},
            success:function(data){
                $.each(data.categorySecondList,function (i, v) {
                    $csid.append(`<option value="${v.csid}">${v.csname}</option>`)
                })
            },
            error:function(){
                alert("数据加载失败");
            }
        });

        $("body").on("click","#insert",function () {
            insert(opener.url,"isInsert");
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
                let dt = new FormData();
                dt.append("type","insert");
                for (let i = 0; i < $body.length ; i++) {
                    if($body.eq(i).attr("id")=="image"){
                        dt.append($body.eq(i).attr("id"),$body.eq(i)[0].files[0]);
                        continue;
                    }
                    dt.append($body.eq(i).attr("id"),$body.eq(i).val());
                }
                $.ajax({
                    type: "POST",
                    url: url,
                    dataType: "json",
                    contentType: false,
                    processData: false,
                    async:false,
                    cache: false,
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
    })
</script>
</body>
</html>
