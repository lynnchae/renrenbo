/**
 * 统计FM听众总数据
 */
$(function($) {
    getListenerTotal();
});

function getListenerTotal(){
    $.ajax({
        url: 'getListenerTotal',
        type: 'post',
        dataType: 'json',
        success: function (data) {
            //将数据秀到界面上
            if (data != null) {
                $("#replyNum").html(data.replyNum);
                $("#rewardMoney").html(data.rewardMoney);
            } else {
                alert("查询失败");
            }
        },
        timeout: function () {
            alert("等待超时");
        }
    });
}