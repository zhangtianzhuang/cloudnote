<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
    String path = request.getContextPath();
    String imgPath = path + "/images";
%>
<html>
<head>
    <title>首页</title>
    <title>链接版笔记--爱收藏</title>

    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <link rel="icon" href="images/header.png" type="image/png">
    <link rel="stylesheet" href="css/index.css" type="text/css" />
    <script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="js/main.js"></script>

    <style>
        * {
            font-family: 微软雅黑;
            margin: 0px;
            padding: 0px;
            list-style-type: none;
            color: #000;
            font-size: 16px
        }
        .navier-bg {
            opacity: 0
        }

        .recommend{width:1000px;height:250px;margin:20px auto}
        .recommend p{width:100%;height: 70px;color: #E22018;line-height: 70px;font-size:18px;margin-left:20px}
        #show-notes{width:1000px;margin:40px auto;min-height:300px}
        .note-item{float:left;margin-left:20px;margin-bottom:60px;box-shadow: 0px 10px 10px #e0e3ec;
            width: 225px;height: 140px;background: #f1f4f5;color: #000;	border-radius:10px;}
        .note-item:hover {box-shadow: 0px 0px 20px #ccc;position: relative;top: 5px}
        .note-title{font-size:18px;color:#1296db;line-height:40px}
        .note-content{line-height:30px;}
        .note-browser{line-height:30px;}
        .note-upload-time{line-height:30px;}
        .note-title,.note-content,.note-browser,.note-upload-time{margin-left:8px;}
    </style>
</head>

<body>
<div class="header-bg"></div>
<jsp:include page="navier.jsp"/>
<div class="main">
    <div class="main_backgound">
        <img src="<%=path%>/images/navier03.png" width="960" height="391" />
    </div>
    <div class="main_navier">
        <ul>
            <li><a href="#">Android笔记<span>&gt;</span></a></li>
            <hr />
            <li><a href="#">Android Studio<span>&gt;</span></a></li>
            <hr />
            <li><a href="#">C++程序设计<span>&gt;</span></a></li>
            <hr />
            <li><a href="#">Java程序设计<span>&gt;</span></a></li>
            <hr />
            <li><a href="#">计算机网络<span>&gt;</span></a></li>
            <hr />
            <li><a href="#">数据结构(Java)<span>&gt;</span></a></li>
            <hr />
            <li><a href="#">算法设计<span>&gt;</span></a></li>
        </ul>
    </div>
</div>

<div class="recommend">
    <p>今日推荐</p>
</div>

<div class="bodyer">
    <div class="card_view">
        <p>代码猿</p>

        <div class="single_card" style="background:#ffa">代码小篇</div>

        <div class="single_card single_card_margin"
             style="background:#ffd1a4">Java</div>

        <div class="single_card single_card_margin"
             style="background:#d9b3b3">C++</div>

        <div class="single_card single_card_margin"
             style="background:#cdcd9a">Object</div>
    </div>


    <div class="card_view">
        <p>生活写真</p>

        <div class="single_card" style="background:#e8ffc4">梦想篇</div>

        <div class="single_card single_card_margin"
             style="background:#b3d9d9">娱乐篇</div>

        <div class="single_card single_card_margin"
             style="background:#d8d8e8">经历篇</div>

        <div class="single_card single_card_margin"
             style="background:#ffbfff">爱情篇</div>
    </div>
</div>

<div id="show-notes">
</div>

<script>
    function ajaxLoadNote(url,which){
        $.ajax({
            type:"post",
            async:false,
            url:url,
            success:function(data){
                var jsonData = eval(data);
                $.each(jsonData,function(index,note){
                    var title = note.title ;
                    var content = note.content ;
                    if(title.length>6){
                        title = title.substring(0,6)+'..';
                    }
                    if(content.length>10){
                        content = content.substring(0,10)+'..';
                    }
                    var str = '<div class="note-item">'
                        + '<span>'
                        + '<a class="note-title" href="<%=path%>/note/selectAction?id='+ note.id + '">'
                        + '标题:' + title
                        + '</a>'
                        + '</span><br/>'

                        + '<span class="note-content">'
                        + '内容:' + content
                        + '</span><br/>'

                        + '<span class="note-browser">'
                        + '浏览量:' + note.browserCounts
                        + '</span><br/>'

                        + '<span class="note-upload-time">'
                        + '上传日期:' + new Date(note.uploadDate).toLocaleDateString()
                        + '</span>'
                        + '</div>' ;
                    which.append(str);
                });
            },
            error:function(data){
                alert('服务器异常，加载错误...');
            }
        });
    }

    function addBg(){
        $(".note-item:eq(0)").css({background:"#6E52B9"});
        $(".note-item:eq(1)").css({background:"#FF9E0E"});
        $(".note-item:eq(2)").css({background:"#FFEC00"});
        $(".note-item:eq(3)").css({background:"#2FB1FF"});

        $(".note-title:eq(0)").css({color:"#fff"});
        $(".note-title:eq(1)").css({color:"#fff"});
        $(".note-title:eq(2)").css({color:"#fff"});
        $(".note-title:eq(3)").css({color:"#fff"});
    }

    $(function(){
        var url = "<%=path%>/note/selectAction?id=";
        ajaxLoadNote(url+"-3", $(".recommend"));
        ajaxLoadNote(url+"-2",$("#show-notes"));
        addBg();
    });

</script>

<div class="content_boss">
    <div class="content_boss_content">
			<span class="title"><img src="<%=imgPath%>/Viewlist.png"
                                     class="img_head" />学习网站</span><br /> <br />
        <div style="background:#FFF;width:100%;height:1px"></div>
        <div class="study_web">
            <div class="study_web_item">
                <span class="title">Android开发</span>
                <ul>
                    <li><a href="http://www.xiufm.com/" target="_blank">秀源码</a></li>
                    <li><a
                            href="http://hukai.me/android-training-course-in-chinese/index.html"
                            target="_blank">Android官方培训</a></li>
                    <li><a href="http://blog.csdn.net/lmj623565791"
                           target="_blank">鸿洋(Android UI)</a></li>
                    <li><a href="https://developer.android.google.cn/"
                           target="_blank">AndroidDevelopers</a></li>
                </ul>
            </div>

            <div class="study_web_item">
                <span class="title">工具助手</span>

                <ul>
                    <li><a href="http://www.iconfont.cn/plus" target="_blank">在线LOGO</a></li>
                    <li><a href="http://www.androiddevtools.cn" target="_blank">AndroidDevTools</a></li>
                    <li><a href="http://www.baofengjihuo.com/" target="_blank">暴风激活</a></li>
                    <li><a href="http://bd.ushendu.tv/" target="_blank">一键U盘装系统</a></li>
                    <li><a href="http://www.ghost123.com/" target="_blank">系统之家</a></li>
                </ul>
            </div>

            <div class="study_web_item">
                <span class="title">在线教程</span>

                <ul>
                    <li><a href="http://www.runoob.com/" target="_blank">菜鸟教程</a></li>
                    <li><a href="http://www.mob.com/" target="_blank">mob移动开发者</a></li>
                    <li><a href="http://588ku.com/beijing/" target="_blank">千库网</a></li>
                    <li><a href="http://699pic.com/" target="_blank">摄图网</a></li>
                    <li><a href="http://www.cniao5.com/" target="_blank">菜鸟窝</a></li>

                </ul>
            </div>

            <div class="study_web_item">
                <span class="title">软件学院</span>

                <ul>
                    <li><a href="http://www.imooc.com/" target="_blank">慕课网</a></li>
                    <li><a href="http://www.zhizuobiao.com/" target="_blank">职坐标</a></li>
                    <li><a href="http://www.jikexueyuan.com/" target="_blank">极客学院</a></li>
                    <li><a href="http://sc.chinaz.com/tag_moban/bootstrap.html"
                           target="_blank">bootstrap</a></li>
                </ul>
            </div>

        </div>
    </div>
</div>

<div class="adv">
    <div class="adv_main">
        <span class="title">爱收藏，爱分享</span><br /> <br /> <span
            class="content">在线笔记，立即同步，为了打造一个不同的云笔记</span> <span class="register"
                                                                onselectstart="return false;"> <a
            href="<%=path%>/user_register.jsp">立即注册</a>
			</span>

        <div style="width:100%;height:1px;background:#FFF;margin:50px auto"></div>
        <div class="adv_main_div">
            <ul class="adv_main_block">
                <li><a href="#">CLASSPATH</a></li>
                <li><a href="#">C语言</a></li>
                <li><a href="#">脚本</a></li>
                <li><a href="#">Hello World</a></li>
                <li><a href="#">OJECTE</a></li>
            </ul>
        </div>
        <br /> <br /> <span class="content">为你珍藏，留下你的足迹，记录下你的点点滴滴<br /></span>
        <span class="content chengxuyuannote">————程序员笔记</span>
    </div>
</div>


<div class="content_boss">
    <div class="content_boss_content">
			<span class="title"><img src="<%=imgPath%>/Viewlist.png"
                                     class="img_head" />学习网站</span><br /> <br />
        <div style="background:#FFF;width:100%;height:1px"></div>
        <div class="study_web">
            <div class="study_web_item">
                <span class="title">算法设计</span>

                <ul>
                    <li><a href="https://www.nowcoder.com/7268569"
                           target="_blank">剑指offer</a></li>
                    <li><a href="http://acm.nyist.net/JudgeOnline/problemset.php"
                           target="_blank">南阳oj</a></li>
                    <li><a href="http://blog.csdn.net/lmj623565791"
                           target="_blank">鸿洋(Android UI)</a></li>
                    <li><a href="https://developer.android.google.cn/"
                           target="_blank">AndroidDevelopers</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>

<div class="footer">
    云笔记&nbsp;&nbsp;&nbsp;&nbsp;<a href="contact-us.jsp">联系我们</a>
    &nbsp;&nbsp;&nbsp;&nbsp;<a href="http://www.miitbeian.gov.cn/state/outPortal/loginPortal.action"> 豫ICP备17024501号</a>
</div>

</body>
</html>