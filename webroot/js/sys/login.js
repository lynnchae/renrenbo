
$(function($) {
    //登录表单信息验证
    setLoginFrom();
    //校验用户信息是否正确
    //login();
    if(window != top)
    top.location.href=location.href;

});

//登录表单进行验证
var setLoginFrom = function(){
    //给登录表单绑定验证信息
    $('#loginFrom').validate({
        errorElement: 'div',
        errorClass: 'help-block',
        focusInvalid: false,
        rules: {

            userName:{
                required:true
            },
            userPassword: {
                required: true
            }

        },
        messages: {

            userName: {
                required: "*请输入用户名"
            },
            userPassword: {
                required: "*请输入密码"
            }

        }
    });

}

//登录验证按钮
function login() {

    //判断表单是否检验成功
    if(!$("#loginFrom").valid()){
        return;
    }

    var userName = $("#userName").val();//用户姓名
    var userPassword = $("#userPassword").val();//用户密码

    var xmlhttp;
    if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp=new XMLHttpRequest();
    }
    else
    {// code for IE6, IE5
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {
            if(xmlhttp.responseText == 'true'){
                window.location.href = '../sys/toLogin';
            }
            if(xmlhttp.responseText == 'false'){
                alert("用户名或密码有误");
                //$("#errorMessage").html("用户名或密码有误");
            }
        }
    }
    xmlhttp.open("GET",'loginCheck?userName='+userName+"&userPassword="+userPassword,true);
    xmlhttp.send();

}