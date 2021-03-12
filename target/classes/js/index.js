
function changePage(id) {
    var page=parseInt($("#"+id).text());
    $("#page1").attr("class","page-item");
    $("#page2").attr("class","page-item");
    $("#page3").attr("class","page-item");
    $("#page4").attr("class","page-item");
    $("#page5").attr("class","page-item");
    var pageId = "#p"+id;
    $(pageId).attr("class","page-item active");
    returnPage(page);
}
function tellPage() {
    var page=1;
    var re=0;
    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "http://localhost:8080/friendLooking/returnPages" ,//url
        xhrFields: { withCredentials: true },
        data:{page:page},
        crossDomain: true,
        async :false,
        success: function (result) {
            console.log("tell:"+result.pages);
            re=result.pages;
        },
        error : function() {
            console.log("fail");
            alert("异常！");
        }
    });
    return re;
}
function bafPage(id) {
    if(id=="pageback"){
        if($("#page1").attr('class')=="page-item active"){
            if(parseInt($("#age1").text())!=1){
                $("#age5").text(parseInt($("#age5").text())-1);
                $("#age4").text(parseInt($("#age4").text())-1);
                $("#age3").text(parseInt($("#age3").text())-1);
                $("#age2").text(parseInt($("#age2").text())-1);
                $("#age1").text(parseInt($("#age1").text())-1);
                changePage("age1");
            }else{
                /*alert("这是第一页啦！");*/
                alertCon("这已经是第一页啦！");
            }
        }
        if($("#page2").attr('class')=="page-item active"){
            $("#page2").attr("class","page-item");
            $("#page1").attr("class","page-item active");
            returnPage(parseInt($("#page1").text()));
        }
        if($("#page3").attr('class')=="page-item active"){
            $("#page3").attr("class","page-item");
            $("#page2").attr("class","page-item active");
            returnPage(parseInt($("#page2").text()));
        }
        if($("#page4").attr('class')=="page-item active"){
            $("#page4").attr("class","page-item");
            $("#page3").attr("class","page-item active");
            returnPage(parseInt($("#page3").text()));
        }
        if($("#page5").attr('class')=="page-item active"){
            $("#page5").attr("class","page-item");
            $("#page4").attr("class","page-item active");
            returnPage(parseInt($("#page4").text()));
        }
    }
    if(id=="pageforward"){
        if($("#page5").attr('class')=="page-item active"){
            var allPages=tellPage();
            /*alert(allPages);*/
            if(parseInt($("#age5").text())!=allPages){
                $("#age5").text(parseInt($("#age5").text())+1);
                $("#age4").text(parseInt($("#age4").text())+1);
                $("#age3").text(parseInt($("#age3").text())+1);
                $("#age2").text(parseInt($("#age2").text())+1);
                $("#age1").text(parseInt($("#age1").text())+1);
                changePage("age5");
            }else{
                alertCon("这是最后一页啦！");
            }
        }
        if($("#page4").attr('class')=="page-item active"){
            if($("#page5").css("display")=='none'){
                alertCon("这是最后一页啦！");
            }else{
                $("#page4").attr("class","page-item");
                $("#page5").attr("class","page-item active");
                returnPage(parseInt($("#page5").text()));
            }
        }
        if($("#page3").attr('class')=="page-item active"){
            if($("#page4").css("display")=='none'){
                alertCon("这是最后一页啦！");
            }else{
                $("#page3").attr("class","page-item");
                $("#page4").attr("class","page-item active");
                returnPage(parseInt($("#page4").text()));
            }
        }
        if($("#page2").attr('class')=="page-item active"){
            if($("#page3").css("display")=='none'){
                alertCon("这是最后一页啦！");
            }else{
                $("#page2").attr("class","page-item");
                $("#page3").attr("class","page-item active");
                returnPage(parseInt($("#page3").text()));
            }
        }
        if($("#page1").attr('class')=="page-item active"){
            if($("#page2").css("display")=='none'){
                alertCon("这是最后一页啦！");
            }else{
                $("#page1").attr("class","page-item");
                $("#page2").attr("class","page-item active");
                returnPage(parseInt($("#page2").text()));
            }
        }
    }
}
function returnPage(page) {
    var num;
    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "http://localhost:8080/friendLooking/returnPages" ,//url
        xhrFields: { withCredentials: true },
        data:{page:page},
        crossDomain: true,
        success: function (result) {
            console.log(result);
            if(result.Logname1!=null) {
                $("#info1").css('display','block');
                if(result.Pic1!=null){
                    $("#Pic1").attr("src",result.Pic1);
                }else{
                    $("#Pic1").attr("src","../images/头像.png");
                }
                $("#Logname1").text(result.Logname1);
                $("#Logname1").append('<small id="Emali1">'+result.Email1+'</small>');
                if(result.Message1!=null) {
                    $("#Message1").text(result.Message1);
                }else{
                    $("#Message1").text("暂无");
                }
            }
            if(result.Logname2!=null) {
                $("#info2").css('display','block');
                if(result.Pic2!=null){
                    $("#Pic2").attr("src",result.Pic2);
                }else{
                    $("#Pic2").attr("src","../images/头像.png");
                }
                $("#Logname2").text(result.Logname2);
                $("#Logname2").append('<small id="Emali2">'+result.Email2+'</small>');
                if(result.Message2!=null){
                    $("#Message2").text(result.Message2);
                }else{
                    $("#Message2").text("暂无");
                }
            }
            if(result.Logname3!=null) {
                $("#info3").css('display','block');
                if(result.Pic3!=null){
                    $("#Pic3").attr("src",result.Pic3);
                }else{
                    $("#Pic3").attr("src","../images/头像.png");
                }
                $("#Logname3").text(result.Logname3);
                $("#Logname3").append('<small id="Emali3">'+result.Email3+'</small>');
                if(result.Message3!=null) {
                    $("#Message3").text(result.Message3);
                }else{
                    $("#Message3").text("暂无");
                }
            }
            if(result.Logname1==null) {
                $("#info1").css('display','none');
            }
            if(result.Logname2==null) {
                $("#info2").css('display','none');
            }
            if(result.Logname3==null) {
                $("#info3").css('display','none');
            }
            if(result.pages<5){
                num=result.pages +1;
                while (num<=5){
                    $("#page"+num).css('display','none');
                    num++;
                }
            }
            num=1;
            while(num<=result.pages){
                $("#page"+num).css('display','block');
                num++;
            }
            ensurefollow(page);
        },
        error : function() {
            console.log("fail");
            alert("异常！");
        }
    });
}
function returnSearchage(page) {
    var num;
    var name=$("#search").val();
    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "http://localhost:8080/friendLooking/returnSearch" ,//url
        xhrFields: { withCredentials: true },
        data:{"page":page,"name":name},
        crossDomain: true,
        success: function (result) {
            console.log(result);
            if(result.Logname1!=null) {
                $("#info1").css('display','block');
                if(result.Pic1!=null){
                    $("#Pic1").attr("src",result.Pic1);
                }else{
                    $("#Pic1").attr("src","../images/头像.png");
                }
                $("#Logname1").text(result.Logname1);
                $("#Logname1").append('<small id="Emali1">'+result.Email1+'</small>');
                if(result.Message1!=null) {
                    $("#Message1").text(result.Message1);
                }else{
                    $("#Message1").text("暂无");
                }
            }
            if(result.Logname2!=null) {
                $("#info2").css('display','block');
                if(result.Pic2!=null){
                    $("#Pic2").attr("src",result.Pic2);
                }else{
                    $("#Pic2").attr("src","../images/头像.png");
                }
                $("#Logname2").text(result.Logname2);
                $("#Logname2").append('<small id="Emali2">'+result.Email2+'</small>');
                if(result.Message2!=null){
                    $("#Message2").text(result.Message2);
                }else{
                    $("#Message2").text("暂无");
                }
            }
            if(result.Logname3!=null) {
                $("#info3").css('display','block');
                if(result.Pic3!=null){
                    $("#Pic3").attr("src",result.Pic3);
                }else{
                    $("#Pic3").attr("src","../images/头像.png");
                }
                $("#Logname3").text(result.Logname3);
                $("#Logname3").append('<small id="Emali3">'+result.Email3+'</small>');
                if(result.Message3!=null) {
                    $("#Message3").text(result.Message3);
                }else{
                    $("#Message3").text("暂无");
                }
            }
            if(result.Logname1==null) {
                $("#info1").css('display','none');
            }
            if(result.Logname2==null) {
                $("#info2").css('display','none');
            }
            if(result.Logname3==null) {
                $("#info3").css('display','none');
            }
            if(result.pages<5){
                num=result.pages +1;
                while (num<=5){
                    $("#spage"+num).css('display','none');
                    num++;
                }
            }
            num=1;
            while(num<=result.pages){
                $("#spage"+num).css('display','block');
                num++;
            }
        },
        error : function() {
            console.log("fail");
            alert("异常！");
        }
    });
}
function changeSeachpage(id) {
    var page=parseInt($("#"+id).text());
    $("#spage1").attr("class","page-item");
    $("#spage2").attr("class","page-item");
    $("#spage3").attr("class","page-item");
    $("#spage4").attr("class","page-item");
    $("#spage5").attr("class","page-item");
    var pageId = "#spage"+parseInt(id.substring(1).substring(1).substring(1).substring(1));
    $(pageId).attr("class","page-item active");
    returnSearchage(page);
}
function bafSearchPage(id) {
    if(id=="spageback"){
        if($("#spage1").attr('class')=="page-item active"){
            if(parseInt($("#sage1").text())!=1){
                $("#sage5").text(parseInt($("#sage5").text())-1);
                $("#sage4").text(parseInt($("#sage4").text())-1);
                $("#sage3").text(parseInt($("#sage3").text())-1);
                $("#sage2").text(parseInt($("#sage2").text())-1);
                $("#sage1").text(parseInt($("#sage1").text())-1);
                changeSeachpage("sage1");
            }else{
                /*alert("这是第一页啦！");*/
                alertCon("这已经是第一页啦！");
            }
        }
        if($("#spage2").attr('class')=="page-item active"){
            $("#spage2").attr("class","page-item");
            $("#spage1").attr("class","page-item active");
            returnSearchage(parseInt($("#spage1").text()));
        }
        if($("#spage3").attr('class')=="page-item active"){
            $("#spage3").attr("class","page-item");
            $("#spage2").attr("class","page-item active");
            returnSearchage(parseInt($("#spage2").text()));
        }
        if($("#spage4").attr('class')=="page-item active"){
            $("#spage4").attr("class","page-item");
            $("#spage3").attr("class","page-item active");
            returnSearchage(parseInt($("#spage3").text()));
        }
        if($("#spage5").attr('class')=="page-item active"){
            $("#spage5").attr("class","page-item");
            $("#spage4").attr("class","page-item active");
            returnSearchage(parseInt($("#spage4").text()));
        }
    }
    if(id=="spageforward"){
        if($("#spage5").attr('class')=="page-item active"){
            var allPages=tellSearchpage();
            /*alert(allPages);*/
            if(parseInt($("#sage5").text())!=allPages){
                $("#sage5").text(parseInt($("#sage5").text())+1);
                $("#sage4").text(parseInt($("#sage4").text())+1);
                $("#sage3").text(parseInt($("#sage3").text())+1);
                $("#sage2").text(parseInt($("#sage2").text())+1);
                $("#sage1").text(parseInt($("#sage1").text())+1);
                changeSeachpage("sage5");
            }else{
                alertCon("这是最后一页啦！");
            }
        }
        if($("#spage4").attr('class')=="page-item active"){
            if($("#spage5").css("display")=='none'){
                alertCon("这是最后一页啦！");
            }else{
                $("#spage4").attr("class","page-item");
                $("#spage5").attr("class","page-item active");
                returnSearchage(parseInt($("#spage5").text()));
            }
        }
        if($("#spage3").attr('class')=="page-item active"){
            if($("#spage4").css("display")=='none'){
                alertCon("这是最后一页啦！");
            }else{
                $("#spage3").attr("class","page-item");
                $("#spage4").attr("class","page-item active");
                returnSearchage(parseInt($("#spage4").text()));
            }
        }
        if($("#spage2").attr('class')=="page-item active"){
            if($("#spage3").css("display")=='none'){
                alertCon("这是最后一页啦！");
            }else{
                $("#spage2").attr("class","page-item");
                $("#spage3").attr("class","page-item active");
                returnSearchage(parseInt($("#spage3").text()));
            }
        }
        if($("#spage1").attr('class')=="page-item active"){
            if($("#spage2").css("display")=='none'){
                alertCon("这是最后一页啦！");
            }else{
                $("#spage1").attr("class","page-item");
                $("#spage2").attr("class","page-item active");
                returnSearchage(parseInt($("#spage2").text()));
            }
        }
    }
}
function tellSearchpage() {
    var page=1;
    var name=$("#search").val();
    var re=0;
    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "http://localhost:8080/friendLooking/returnSearch" ,//url
        xhrFields: { withCredentials: true },
        data:{"page":page,"name":name},
        crossDomain: true,
        async :false,
        success: function (result) {
            console.log("tell:"+result.pages);
            re=result.pages;
        },
        error : function() {
            console.log("fail");
            alert("异常！");
        }
    });
    return re;
}
function openImg(id){
    $("#imgmyModal").css('display','block');
    $("#img").attr("src",$("#"+id).attr('src'));
}
function followOne(id){
    var num=parseInt(id.replace(/[^0-9]/ig,""));
    if($("#page1").attr('class')=="page-item active"){
        var name=returnbefollower(parseInt($("#page1").text()),num);
        follow(parseInt($("#page1").text()),name);
    }
    if($("#page2").attr('class')=="page-item active"){
        var name=returnbefollower(parseInt($("#page2").text()),num);
        follow(parseInt($("#page2").text()),name);
    }
    if($("#page3").attr('class')=="page-item active"){
        var name=returnbefollower(parseInt($("#page3").text()),num);
        follow(parseInt($("#page3").text()),name);
    }
    if($("#page4").attr('class')=="page-item active"){
        var name=returnbefollower(parseInt($("#page4").text()),num);
        follow(parseInt($("#page4").text()),name);
    }
    if($("#page5").attr('class')=="page-item active"){
        var name=returnbefollower(parseInt($("#page5").text()),num);
        follow(parseInt($("#page5").text()),name);
    }
}
function returnbefollower(page,num) {
    var re;
    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "http://localhost:8080/friendLooking/returnPages" ,//url
        xhrFields: { withCredentials: true },
        data:{page:page},
        crossDomain: true,
        async:false,
        success: function (result) {
            console.log(result["Logname"+num]);
            re=result["Logname"+num];
        },
        error : function() {
            console.log("fail");
            alert("异常！");
        }
    });
    return re;
}
function follow(page,name) {
    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "http://localhost:8080/friendLooking/followOne" ,//url
        xhrFields: { withCredentials: true },
        data:{"name":name},
        crossDomain: true,
        async :false,
        success: function (result) {
            console.log(result);
            if(result.status==0){
                alert("已取消关注");
                returnPage(page);
            }else{
                alert("关注成功");
                returnPage(page);
            }
        },
        error : function() {
            console.log("fail");
            alert("异常！");
        }
    });
}
function ensurefollow(page) {
    $("#folower1").val("关注");
    $("#folower2").val("关注");
    $("#folower3").val("关注");
    if(ensurefollowFromdatabase(returnbefollower(page,1))==1){
        $("#folower1").val("已关注");
    }
    if(ensurefollowFromdatabase(returnbefollower(page,2))==1){
        $("#folower2").val("已关注");
    }
    if(ensurefollowFromdatabase(returnbefollower(page,3))==1){
        $("#folower3").val("已关注");
    }
}
function ensurefollowFromdatabase(name){
    var re;
    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "http://localhost:8080/friendLooking/ensurefollow" ,//url
        xhrFields: { withCredentials: true },
        data:{"name":name},
        crossDomain: true,
        async :false,
        success: function (result) {
            console.log(result);
            if(result.status==0){
                re=0;
            }else{
                re=1;
            }
        },
        error : function() {
            console.log("fail");
            alert("异常！");
        }
    });
    return re;
}
