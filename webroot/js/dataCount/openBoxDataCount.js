/**
 * 礼物数据统计
 */
$(function($) {
    openBoxDataCount();
});

function openBoxDataCount(){
    $.ajax({
        url: 'openBoxDataCount',
        type: 'post',
        dataType: 'json',
        success: function (data) {
            //将数据秀到界面上
            if (data != null) {
                $("#outOfBoxNum").html(data.outOfBoxNum);//开箱数
                $("#sendAnchorNum").html(data.sendAnchorNum);//送主播数
                $("#deliveryOrderNum").html(data.deliveryOrderNum);//发货订单数
            } else {
                alert("查询失败");
            }
        },
        timeout: function () {
            alert("等待超时");
        }
    });
}