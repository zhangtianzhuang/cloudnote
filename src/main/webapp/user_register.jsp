<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String imgPath = path + "/images";
%>

<html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>注册</title>

    <link href="css/register.css" type="text/css" rel="stylesheet"/>
    <style>
        * {
            font-family: 微软雅黑
        }

        body.bodyer {
            background-image: url(images/bg2-find-note.png);
            background-size: cover;
        }

        .boss-box {
            width: 600px;
            height: 370px;
            margin: 10% auto;
            background: #fff;
            border-radius: 5px;
        }

        .main {
            height: 100px;
            padding: 20px;
            margin-left: 110px;
        }

        .main tr td:first-child img {
            width: 30px;
            height: 30px
        }

        #checkEmail, #checkPassword, #checkPassword2 {
            width: 20px;
            height: 20px
        }

        .main tr td input[type='text'], input[type='password'] {
            width: 260px;
            height: 36px;
            padding-left: 5px;
        }

        .main tr td input[type='submit'], input[type='button'] {
            width: 100%;
            height: 35px;
            margin-top: 10px;
            border-radius: 6px;
            background: #666E8A;
            color: #fff;
            border: 1px solid #666E8A;
            cursor: pointer
        }

        #sendVetifyCode {
            position: relative;
            bottom: 6px;
        }

        .vetifyCode {
            width: 90px;
            height: 28px
        }
    </style>

    <!-- 发送验证码 -->
    <script type="text/javascript">
        var clock = '';
        var nums = 120;
        var btn;
        function sendCode(thisBtn) {
            btn = thisBtn;
            btn.disabled = true; //将按钮置为不可点击
            btn.value = nums + '秒后可重新获取';
            clock = setInterval(doLoop, 1000); //一秒执行一次
        }
        function doLoop() {
            nums--;
            if (nums > 0) {
                btn.value = nums + '秒后可重新获取';
            } else {
                clearInterval(clock); //清除js定时器
                btn.disabled = false;
                btn.value = '发送验证码';
                nums = 120; //重置时间
            }
        }
    </script>

    <!-- ajax对验证码进行验证 -->
    <script type="text/javascript" src="js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="js/user_check.js"></script>
    <script type="text/javascript">
        function checkVertifyCode(input,url){
            var flag = true ;
            $.ajax({
                type:"post",
                url:url,
                async:false,
                data:{
                    code:input,
                },
                dataType:"text",
                success:function(data){
                    var result = data ;
//                    console.log(result);
                    if(result=='code-success'){
//                        console.log('1.验证码正确');
                        flag =  true;
                    }else{
//                        console.log('2.验证码错误');
                        flag = false;
                    }
                },
                error:function(data){
                    alert('验证出现异常，稍后重试');
                }
            });
//            console.log("flag:"+flag)
            return flag ;
        }

        $(function () {
            $("#vetifyCode").blur(function () {
                if (checkVertifyCode($("#vetifyCode").val(), "checkCodeAction.action")) {
                    console.log('ddddddddddd');
                    $("#checkVetifyCode").text("YES").css({color: "#05D400"});
                } else {
                    console.log('eeeeeeeeeeee');
                    $("#checkVetifyCode").text("NO").css({color: "#f00"});
                    $(".vetifyCode").attr('src', 'createImageAction.action?' + Math.random());
                }
            });

            $("#register").click(function () {
                if (checkEmail($("#email").val()) && checkPassword($("#password").val())
                    && checkAgainPassword($("#password").val(), $("#password2").val())
                    && checkVertifyCode($("#vetifyCode").val(), "checkCodeAction.action")) {
                    $("#myform").submit();
                } else {
                    alert('恁填完嘞，嘛溜嘞');
                }
            });
        });
    </script>
</head>
<body class="bodyer">
<div class="boss-box">


    <form name="register" action="<%=path%>/user/register.action" method="post" id="myform">
        <div style="width:100%;height:70px;text-align:center;line-height:100px">
            <h3>注册</h3>
        </div>

        <table class="main">
            <tr>
                <td><img src="images/logo-user.png"/></td>
                <td><input type="text" name="user.username" placeholder="邮箱" id="email"/></td>
                <td><span id="checkEmail"></span></td>
            </tr>
            <tr>
                <td><img src="images/logo-password.png"/></td>
                <td><input type="password" name="user.password" placeholder="密码" id="password"/></td>
                <td><span id="checkPassword"></span></td>
            </tr>

            <tr>
                <td><img src="images/logo-password.png"/></td>
                <td><input type="password" name="passwordagain" placeholder="确认密码" id="password2"/></td>
                <td><span id="checkPassword2"></span></td>
            </tr>

            <tr>
                <td></td>
                <td><input type="text" id="vetifyCode" placeholder="验证码"/></td>
                <td><img src="createImageAction.action" class="vetifyCode"
                         onclick="this.src='createImageAction.action?'+Math.random();"/></td>
                <td><span id="checkVetifyCode"></span></td>
            </tr>

            <tr>
                <td colspan="2"><input type="button" id="register" value="注册"/></td>
            </tr>
        </table>
    </form>
    <span style="float:right;margin-right:10px;margin-bottom:20px;font-size:14px">已有账号，立即
			<a href="user_login.jsp" style="color:#666E8A">登录</a></span>
</div>

<!--

 -->
</body>
</html>