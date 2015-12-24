/**
 * 系统公告查询
 */


 //每个模块都是以ID都是以 notice开头
 // 如FM活动模块：notice1
var commonParentDir = "notice";

//同数据库主键一致
var eventRemarkID = 1;


$(function($) {
    //给FM活动设置校验信息
    setValidInfoForActivityFrom();

    //查询活动模块
    queryNoticeModuleInfo(eventRemarkID);

});


var setValidInfoForActivityFrom = function(){
    //给FM活动表单绑定验证信息
    $('#noticeActivityFrom').validate({
        errorElement: 'div',
        errorClass: 'help-block',
        focusInvalid: false,
        rules: {
            title:{
                required:true
            },
            url: {
                required: true,
                url: true
            },
            brief:{
                required: true
            }
        },
        messages: {

            title: {
                required: "*请输入标题名"
            },
            url: {
                required: "*输入url",
                url: "*请输入正确格式的url"
            },
            brief:{
                required:'*请输入活动简介'
            }

         }
    });

}



/**
 * 根据id 查询信息
 */
var queryNoticeModuleInfo = function(remark){
    $.ajax({
        url:'getNoticeByRemark',
        data:{ "remark":remark},
        dataType:'json',
        type:'post',
        success:function(data){
            initNoticeModuleInfo(data,remark);
        },
        timeout:function(){

        }
    })
};


/**
 * 初始化每个模块
 * @param data
 *         返回的json数据，包含公告中活动模块的数据
 * @param id
 *         每个模块的主键
 */
var initNoticeModuleInfo = function(data,remark){
    //查询成功
    if(data.ERRORCODE = '0' && data.RESULT != null){
        //获取图标
        $("#"+commonParentDir+remark + " img[name=icon]").attr("src",data.RESULT.icon);
        //设置标题
        $("#"+commonParentDir+remark + " input[name=title]").val(data.RESULT.title);
        //设置url
        $("#"+commonParentDir+remark + " input[name=url]").val(data.RESULT.url);
        //设置简介
        $("#"+commonParentDir+remark + " textarea[name=brief]").val(data.RESULT.brief);
    }else{
        alert(data.RESULT);
    }
}


/**
 * 更新模块的公告信息
 * @param id
 */
 function updateNoticeInfo(remark){
    //判断表单是否检验成功
    if(!$("#noticeActivityFrom").valid()){
        return;
    }

    var title =  $("#"+commonParentDir+remark + " input[name=title]").val();
    var url = $("#"+commonParentDir+remark + " input[name=url]").val();
    var brief =  $("#"+commonParentDir+remark + " textarea[name=brief]").val();

    //要传递的数据
    var data = {
        "remark":remark,
        "title":title,
        "url":url,
        "brief":brief
    };

    //上传文件
    $.ajaxFileUpload(
        {
            url: "setSysNoticeByMark", //用于文件上传的服务器端请求地址
            secureuri: false, //是否需要安全协议，一般设置为false
            fileElementId: 'icon1', //文件上传域的ID
            dataType: 'json', //返回值类型 一般设置为json
            data:data,
            success: function (data,status)  //服务器成功响应处理函数
            {
                initNoticeModuleInfo(data,remark);

            },
            error: function (data, status, e)//服务器响应失败处理函数
            {
                console.info(e);
                //initNoticeModuleInfo(data,remark);
            }
        }
    );
}















