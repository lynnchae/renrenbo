/**
 * 统计人人播主播总数据
 */
$(function($) {
    getAnchorTotal();
});

function getAnchorTotal(){
    $.ajax({
        url: 'getAnchorTotal',
        type: 'post',
        dataType: 'json',
        success: function (data) {
            //将数据秀到界面上
            if (data != null) {
                $("#sendMessage").html(data.sendMessageNum);
                $("#frowardMessage").html(data.frowardMessageNum);
                $("#fansReply").html(data.fansReplyNum);
                $("#fansRetention").html(data.fansRetentionNum);
                $("#coverPerson").html(data.coverPeopleNum);
                $("#broadcastTime").html(data.broadcastTimeNum);
                $("#populaCity").html(data.populaCityNum);
            } else {
                alert("查询失败");
            }
        },
        timeout: function () {
            alert("等待超时");
        }
    });
}