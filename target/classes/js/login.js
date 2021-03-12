function registered() {
    if(ensurePassword()){
        var formData = new FormData($("#registered")[0]);
        $.ajax({
            //几个参数需要注意一下
            type: "POST",//方法类型
            dataType: "json",//预期服务器返回的数据类型
            url: "http://localhost:8080/friendLooking/registered" ,//url
            //xhrFields: { withCredentials: true },
            data:formData,
            async:false,
            cache:false,
            processData:false,
            contentType:false,
            success: function (result) {
                if(result.status==0){
                    alert("该用户名已存在！");
                }else{
                    alert("注册成功！");
                }
            },
            error : function() {
                alert("异常！");
            }
        });
    }else{
        alert("密码不一致！");
    }
}

function login() {
    var formData = new FormData($("#login")[0]);
    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "http://localhost:8080/friendLooking/login" ,//url
        xhrFields: { withCredentials: true },
        data:formData,
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
                    alert("登录成功！");
                    $("#login").attr("action",'index.html');
                    //
                } else if (result.status == 2) {
                    alert("密码错误！");
                } else {
                    alert("找不到该用户！");
                }
            }
        },
        error : function() {
            console.log("fail");
            alert("异常！");
        }
    });
}
function ensurePassword() {
    var password=document.getElementById("password").value;
    var password2=document.getElementById("password2").value;
    if(password==password2){
        return true;
    }else{
        return false;
    }
}