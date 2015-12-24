/**
 * 查询当前参数的信息
 */

$(function($) {
    initActivityName();
});

/**
 * 查询配置信息
 */
function initActivityName(){
    $("#activity_name").append("<option value='1'>注册</option>");
    $("#activity_name").append("<option value='2'>充值</option>");
    $("#activity_name").append("<option value='3'>消费</option>");
    $("#activity_name").append("<option value='4'>分享</option>");
    /*$.ajax({
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
    });*/
}

function selectActivityName(){
    if($("#activity_object option").size() > 1){
        $("#activity_object option[value='rrb']").remove();
        $("#activity_object option[value='fm']").remove();
    }
    $("#activity_object").append("<option value='fm'>听众</option>");
    var value = $("#activity_name").val();
    if(value == 1 || value == 4){//注册或分享
        $("#activity_object").append("<option value='rrb'>主播</option>");
    }
}

function selectActivityObject(){

}

function selectRewardType(){
    var value = $("#reward_type").val();
    if(value == 1){
        $("#send_diamond").css("display","none");
        $("#send_money").css("display","none");
        $("#send_gold").css("display","block");
    }else if (value == 2){
        $("#send_money").css("display","none");
        $("#send_gold").css("display","none");
        $("#send_diamond").css("display","block");
    }else if (value == 3){
        $("#send_diamond").css("display","none");
        $("#send_gold").css("display","none");
        $("#send_money").css("display","block");
    }
}
