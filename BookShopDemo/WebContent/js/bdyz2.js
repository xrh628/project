//封装document.getElementById方法
function $(id){
    return document.getElementById(id);
}
function checkName(){
    var name = $("userName").value;
    var msg = $("userMsg");
    if(name==""){
        msg.innerHTML = "用户名不能为空!";
        msg.className = "errStyle";
        return false;
    }
    //定义正则表达式
    var reg = /^[a-z0-9_]+$/;
    //验证字符串内容是否匹配正则表达式
    var result = reg.test(name);
    if(result==false){
        msg.innerHTML = "用户名格式不正确!";
        msg.className = "errStyle";
        return false;
    }
    msg.innerHTML = "√";
    msg.className = "okStyle";
    return true;
}

function checkPsw(){
    var psw = $("psw").value;
    var msg = $("pswMsg");
    if(psw==""){
        msg.innerHTML = "密码不能为空!";
        msg.className = "errStyle";
        return false;
    }
    if(psw.length<6){
        msg.innerHTML = "密码不能少于6位字符!";
        msg.className = "errStyle";
        return false;
    }
    msg.innerHTML = "√";
    msg.className = "okStyle";
    return true;
}

function checkPsw2(){
    var psw = $("psw").value;
    var psw2 = $("psw2").value;
    var msg = $("psw2Msg");
    if(psw!=psw2){
        msg.innerHTML = "两次密码不一致!";
        msg.className = "errStyle";
        return false;
    }
    msg.innerHTML = "√";
    msg.className = "okStyle";
    return true;
}

function checkEmail(){
    var email = $("email").value;
    var msg = $("emailMsg");
    if(email==""){
        msg.innerHTML = "邮箱不为空!";
        msg.className = "errStyle";
        return false;
    }

    var reg = /^\w+@\w+(\.[a-zA-Z]{2,3}){1,2}$/;
    var result = reg.test(email);
    if(result==false){
        msg.innerHTML = "邮箱格式不正确!";
        msg.className = "errStyle";
        return false;
    }

    msg.innerHTML = "√";
    msg.className = "okStyle";
    return true;
}















