/**
 * 查询当前参数的信息
 */

$(function($) {
    getConfigInfo();
});

/**
 * 查询配置信息
 */
function getConfigInfo(){
    $.ajax({
        url:'getConfigInfo',
        type:'post',
        dataType:'json',
        success:function(data){
            //将数据复制到界面上
            if(data != null){
                $("#slowPercent").val(data.slowPercent);
                $("#crowPercent").val(data.crowPercent);
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
function updateConfigInfo(){
    //查询缓行指标
    var slowPercent = $("#slowPercent").val();
    var crowPercent = $("#crowPercent").val();

    //判断当前的数值是否合法

    $.ajax({
        url:'updateConfigInfo',
        type:'post',
        dataType:'json',
        data:{'slowPercent':slowPercent,'crowPercent':crowPercent},
        success:function(data){
            if(data != null && data.ERRORCODE == 0){
                $("#slowPercent").val(data.RESULT.slowPercent);
                $("#crowPercent").val(data.RESULT.crowPercent);
                alert("更新成功");
                return;
            }

            $("#slowPercent").val(slowPercent);
            $("#crowPercent").val(crowPercent);

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