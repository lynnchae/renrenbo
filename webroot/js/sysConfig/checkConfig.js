/**
 * 查询当前参数的信息
 */

$(function($) {
    getCheckConfigInfo();

});

/**
 * 查询配置信息
 */
function getCheckConfigInfo(){
    $.ajax({
        url:'getCkeckConfig',
        type:'post',
        dataType:'json',
        success:function(data){
            //将数据复制到界面上
            if(data != null){
                if(data.valOne == 1){
                    $("#checkConfig").attr("checked", true);
                }else{
                    $("#checkConfig").attr("checked", false);
                }
            }else{
                alert("查询失败");
            }
        },
        timeout:function(){
            alert("等待超时");
        }
    });
}

/**
 *修改配置信息
 */
function updateCheckConfig(){
     var check = $("#checkConfig").is(':checked');
    var isOnCheck = 1;
    if(!check){
        isOnCheck =0;
    }
    //判断当前的数值是否合法

    $.ajax({
        url:'updateCheckConfig',
        type:'post',
        dataType:'json',
        data:{'isOnCheck':isOnCheck},
        success:function(data){
            if(data != null && data.ERRORCODE == 0){
                $("#slowPercent").val(data.RESULT.slowPercent);
                $("#crowPercent").val(data.RESULT.crowPercent);
                if(data.RESULT.valOne == 1){
                    $("#checkConfig").attr("checked", true);
                }else{
                    $("#checkConfig").attr("checked", false);
                }
                alert("更新成功");
                return;
            }

            if(data != null && data.ERRORCODE == 1){
                alert(data.RESULT);
                return;
            }

            alert("更新失败");
        },
        timeout:function(){
            alert("等待超时");
        }
    })



}