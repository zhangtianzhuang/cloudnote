$(function(){
    $(".list li").hover(function(){
        $(this).find(".down").stop().slideDown()
    },function(){
        $(this).find(".down").stop().slideUp()
    })
})

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
                if(title.trim().length>6){
                    title = title.substring(0,6)+'..';
                }
                if(content.trim().length>10){
                    content = content.substring(0,10)+'..';
                }
                var str = '<div class="note-item">'
                    + '<span>'
                    + '<a class="note-title" href="/note/selectAction?id='+ note.id + '">'
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