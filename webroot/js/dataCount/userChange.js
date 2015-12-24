/**
 *人人播和FM用户数据分析（分为天、周、月）
 */
$(function($) {
    getUserChange();
});

function getUserChange(){
    $.ajax({
        url: 'getUserChange',
        type: 'post',
        dataType: 'json',
        success: function (data) {
            //将数据秀到界面上
            if (data != null) {
                //统计新增用户（天、周、月）
                $("#dayNumberRrb").html(data.dayNumberRrb);
                $("#weekNumberRrb").html(data.weekNumberRrb);
                $("#monthNumberRrb").html(data.monthNumberRrb);
                $("#dayNumberFm").html(data.dayNumberFm);
                $("#weekNumberFm").html(data.weekNumberFm);
                $("#monthNumberFm").html(data.monthNumberFm);
                //统计留存率（天、周、月）
                $("#dayRetentionRrb").html(data.dayRetentionRrb);
                $("#weekRetentionRrb").html(data.weekRetentionRrb);
                $("#monthRetentionRrb").html(data.monthRetentionRrb);
                $("#dayRetentionFm").html(data.dayRetentionFm);
                $("#weekRetentionFm").html(data.weekRetentionFm);
                $("#monthRetentionFm").html(data.monthRetentionFm);
                //统计活跃用户（天、周、月）
                $("#dayActiveRrb").html(data.dayActiveRrb);
                $("#weekActiveRrb").html(data.weekActiveRrb);
                $("#monthActiveRrb").html(data.monthActiveRrb);
                $("#dayActiveFm").html(data.dayActiveFm);
                $("#weekActiveFm").html(data.weekActiveFm);
                $("#monthActiveFm").html(data.monthActiveFm);
                //统计总用户
                $("#dayOverallRrb").html(data.overallRrb);
                $("#dayOverallFm").html(data.overallFm);
                $("#weekOverallRrb").html(data.overallRrb);
                $("#weekOverallFm").html(data.overallFm);
                $("#monthOverallRrb").html(data.overallRrb);
                $("#monthOverallFm").html(data.overallFm);
            } else {
                alert("查询失败");
            }
        },
        timeout: function () {
            alert("等待超时");
        }
    });
}
