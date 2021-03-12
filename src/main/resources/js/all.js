function ensureLogin() {
    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "http://localhost:8080/friendLooking/ensure" ,//url
        xhrFields: { withCredentials: true },
        data:null,
        crossDomain: true,
        async:false,
        cache:false,
        processData:false,
        contentType:false,
        success: function (result) {
            console.log(result);
            if(result.status!=null) {
                //alert(result.status);
                if (result.status == 1) {

                } else{
                    alert("您现在尚未登录！");
                    $(window).attr('location',"login.html");
                }
            }
        },
        error : function() {
            console.log("fail");
            alert("异常！");
        }
    });

}
function openNav() {
    document.getElementById("myNav").style.height = "100%";
    /*$("#myNav a").show();*/
}

function closeNav() {
    /*$("#myNav a").hide();*/
    document.getElementById("myNav").style.height = "0";
}

function exit() {
    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "http://localhost:8080/friendLooking/exit" ,//url
        xhrFields: { withCredentials: true },
        data:null,
        crossDomain: true,
        async:false,
        cache:false,
        processData:false,
        contentType:false,
        success: function (result) {
            console.log(result);
            if(result.status!=null) {
                //alert(result.status);
                if (result.status == 1) {
                    alert("退出成功！");
                    $(window).attr('location',"login.html");
                } else{
                    alert("退出失败！");
                }
            }
        },
        error : function() {
            console.log("fail");
            alert("异常！");
        }
    });
}

function alertCon(content) {
    $("#alertCon").text(content);
    $("#myModal").css('display','block');
}

function closeAlert() {
    $("#myModal").css('display','none');
}

function reflush() {
    window.location.reload();
}