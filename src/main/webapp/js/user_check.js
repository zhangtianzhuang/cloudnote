/**
 * Created by zhangtianzhuang on 2018/2/7.
 * @param email
 */
/**
 * 检查邮箱是否合法
 * @param email 用户邮箱
 */
function checkEmail(email){
    // 邮箱检查正则表达式
    var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
    if(!myreg.test(email)){
        return false;
    }else{
        return true;
    }
}

/**
 * 密码验证
 * @param password 密码
 */
function checkPassword(password) {
    var myreg = /^[a-zA-Z0-9]{6,15}$/;
    if(!myreg.test(password)){
        return false;
    }else{
        return true;
    }
}
/**
 * 检查两次密码是否相同
 * @param password
 * @param password2
 */
function checkAgainPassword(password,password2){
    if (checkPassword(password) && checkPassword(password2) && password==password2){
        return true;
    }else{
        return false;
    }
}

$(function () {
    $('input[name="user.username"]').blur(function () {
        if (checkEmail($("#email").val()))
            $("#checkEmail").text('棒棒哒').css({color: "#05D400"});
        else
            $("#checkEmail").text('嘤嘤嘤').css({color: "#f00"});
    });

    // 密码验证
    $('input[name="user.password"]').blur(function () {
        if (checkPassword($("#password").val()))
            $("#checkPassword").text('棒棒哒').css({color: "#05D400"})
        else
            $("#checkPassword").text('嘤嘤嘤').css({color: "#f00"})
    });

    //确认密码验证
    $('input[name="passwordagain"]').blur(function () {
        if (checkAgainPassword($("#password").val(), $("#password2").val()))
            $("#checkPassword2").text('666').css({color: "#05D400"});
        else
            $("#checkPassword2").text('令人窒息的操作').css({color: "#f00"});
    });
});