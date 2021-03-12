function onload() {
    document.getElementById("onload").click();
}
function infoReturn() {
    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "http://localhost:8080/friendLooking/infoReturn" ,//url
        xhrFields: { withCredentials: true },
        data:null,
        crossDomain: true,
        async:false,
        cache:false,
        processData:false,
        contentType:false,
        success: function (result) {
            console.log(result);
            if(result.status==0){}
            if(result.status==1){
                if(result.logname!=null){
                    $("#info-name").html(result.logname);
                }
                if(result.email!=null){
                    $("#info-email").val(result.email);
                }
                if(result.message!=null){
                    $("#info-message").val(result.message);
                }
                if(result.phone!=null){
                    $("#info-phone").val(result.phone);
                }
                if(result.pic!=null){
                    $("#pic").attr('src',result.pic);
                }
                if(result.Idcardforward!=null){
                    $("#idf").attr('src',result.Idcardforward);
                }
                if(result.Idcardbackward!=null){
                    $("#idb").attr('src',result.Idcardbackward);
                }
            }
        },
        error : function() {
            console.log("fail");
            alert("异常！");
        }
    });
}

function infoOnload(id) {
    var formData = new FormData($("#info")[0]);
    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "http://localhost:8080/friendLooking/onloadinfo" ,//url
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
                    /*alertCon("保存成功！");
                    infoReturn();*/

                    location.reload();
                    alert("保存成功！");
                } else {
                    alertCon("保存失败！");
                }
            }
        },
        error : function() {
            console.log("fail");
            alert("异常！");
        }
    });
}
function turnToinfotab() {
    $("#passwordtab").css('display','none');
    $("#info-tab").css('display','block');
    $("#ocRtab").css('display','none');
    $("#deletetab").css('display','none');
}
function turnTopasswordtab() {
    $("#info-tab").css('display','none');
    $("#passwordtab").css('display','block');
    $("#ocRtab").css('display','none');
    $("#deletetab").css('display','none');
}
function turnToocrtab() {
    $("#info-tab").css('display','none');
    $("#passwordtab").css('display','none');
    $("#ocRtab").css('display','block');
    $("#deletetab").css('display','none');
}
function turnTodeletetab(){
    $("#info-tab").css('display','none');
    $("#passwordtab").css('display','none');
    $("#ocRtab").css('display','none');
    $("#deletetab").css('display','block');
}
function passwordOnload() {
    var formData = new FormData($("#info-password")[0]);
    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "http://localhost:8080/friendLooking/onloadPassword" ,//url
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
                    alertCon("前后新密码不一致!");
                    $("#ensure-newpassword").val("");
                    $("#new-password").val("");
                    /*window.location.reload();*/
                } else if(result.status == 2){
                    alertCon("输入现有密码和原密码不一致!");
                    $("#now-password").val("");
                }else{
                    alert("修改成功!需要你重新登录!");
                    location.reload();
                    $("#ensure-newpassword").val("");
                    $("#new-password").val("");
                    $("#now-password").val("");
                }
            }
        },
        error : function() {
            console.log("fail");
            alert("异常！");
        }
    });
}
function IdOnload() {
    var formData = new FormData($("#info-OCR")[0]);
    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "http://localhost:8080/friendLooking/ocr" ,//url
        xhrFields: { withCredentials: true },
        data:formData,
        crossDomain: true,
        async:false,
        cache:false,
        processData:false,
        contentType:false,
        success: function (result) {
            var con;
            console.log(result);
            for(var k in result){
                con=con+k+"："+result[k]+"\n";
            }
            alert(con);
            location.reload();
        },
        error : function() {
            console.log("fail");
            alert("异常！");
        }
    });
}
function Idcardfonload() {
    document.getElementById("Idpicf").click();
}
function Idcardbonload() {
    document.getElementById("Idpicb").click();
}
function ensuredelete(){
    var msg = "点击确定即可注销";
    if (confirm(msg)==true){
        deleteMyinfo();
    }else{
    }
}
function deleteMyinfo() {
    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "http://localhost:8080/friendLooking/deleteMyinfo" ,//url
        xhrFields: { withCredentials: true },
        crossDomain: true,
        async:false,
        cache:false,
        processData:false,
        contentType:false,
        success: function (result) {
            console.log(result);
            alert("注销成功！");
            location.reload();
        },
        error : function() {
            console.log("fail");
            alert("异常！");
        }
    });
}