/**
 * 将最新版本信息秀到页面
 */

$(function($) {
    getVersionFm();//获取Fm的最新版本信息
    getVersionRrb();//获取人人播的最新版本信息
});

/**
 * 查询FM最新版本信息
 */
function getVersionFm() {
    var appKey = $("#fmAppKey").val();//移动操作系统
    $.ajax({
        url: 'getVersionFm',
        type: 'post',
        dataType: 'json',
        data: {'appKey': appKey},
        success: function (data) {
            //将数据秀到界面上
            if (data != null) {
                $("#fmNumber").val(data.number);
                $("#fmVersionUrl").val(data.versionUrl);
                $("#fmRemark").val(data.remark);
                $("input[name='fmIsUpToDate'][value="+data.isUpToDate+"]").attr("checked","checked");
                $("input[name='fmIsForcedUpDate'][value="+data.isForcedUpDate+"]").attr("checked","checked");
            } else {
                alert("查询失败");
            }
        },
        timeout: function () {
            alert("等待超时");
        }
    });
}

/**
 * 查询人人播最新版本信息
 */
function getVersionRrb() {
    var appKey = $("#rrbAppKey").val();//移动操作系统
    $.ajax({
        url: 'getVersionRrb',
        type: 'post',
        dataType: 'json',
        data: {'appKey': appKey},
        success: function (data) {
            //将数据秀到界面上
            if (data != null) {
                $("#rrbNumber").val(data.number);
                $("#rrbVersionUrl").val(data.versionUrl);
                $("#rrbRemark").val(data.remark);
                $("input[name='rrbIsUpToDate'][value="+data.isUpToDate+"]").attr("checked","checked");
                $("input[name='rrbIsForcedUpDate'][value="+data.isForcedUpDate+"]").attr("checked","checked");
            } else {
                alert("查询失败");
            }
        },
        timeout: function () {
            alert("等待超时");
        }
    });
}


/**
 * $.ajax({
        url:'updateConfigInfo',
        type:'post',
        dataType:'json',
        data:{'slowPercent':slowPercent,'crowPercent':crowPercent},
        success:function(data){//更新成功，返回更新后的数据
            if(data != null && data.ERRORCODE == 0){
                $("#slowPercent").val(data.RESULT.slowPercent);
                $("#crowPercent").val(data.RESULT.crowPercent);
                alert("更新成功");
                return;
            }

            //更新失败，返回原来数据
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
 */


/**
 *修改版本信息
 */
function updateVersion(type){
    var params = validate(type);
    $.ajax({
        url:'updateVersion',
        type:'get',
        dataType:'json',
        cache: false,
        data:{'type':type,'number':params.number,'appKey':params.appKey,'versionUrl':params.versionUrl,'remark':params.remark,'isUpToDate':params.isUpToDate,'isForcedUpDate':params.isForcedUpDate},
        success:function(data){
            //更新成功，返回更新以后的数据
            if(data != null && data.ERRORCODE == 0){
                if(type == 'fm'){
                    $("#fmNumber").val(data.RESULT.number);
                    $("#fmVersionUrl").val(data.RESULT.versionUrl);
                    $("#fmRemark").val(data.RESULT.remark);
                    $("input[name='fmIsUpToDate'][value="+data.RESULT.isUpToDate+"]").attr("checked","checked");
                    $("input[name='fmIsForcedUpDate'][value="+data.RESULT.isForcedUpDate+"]").attr("checked","checked");
                    alert("更新成功");
                }
                if(type == 'rrb'){
                    $("#rrbNumber").val(data.RESULT.number);
                    $("#rrbVersionUrl").val(data.RESULT.versionUrl);
                    $("#rrbRemark").val(data.RESULT.remark);
                    $("input[name='rrbIsUpToDate'][value="+data.RESULT.isUpToDate+"]").attr("checked","checked");
                    $("input[name='rrbIsForcedUpDate'][value="+data.RESULT.isForcedUpDate+"]").attr("checked","checked");
                    alert("更新成功");
                }
                return;
            }

            //更新失败，返回原有数据
            if(type == 'fm'){
                $("#fmNumber").val(params.number);
                $("#fmVersionUrl").val(params.versionUrl);
                $("#fmRemark").val(params.remark);
                $("input[name='fmIsUpToDate'][value="+params.isUpToDate+"]").attr("checked","checked");
                $("input[name='fmIsForcedUpDate'][value="+params.isForcedUpDate+"]").attr("checked","checked");
            }
            if(type == 'rrb'){
                $("#rrbNumber").val(params.number);
                $("#rrbVersionUrl").val(params.versionUrl);
                $("#rrbRemark").val(params.remark);
                $("input[name='rrbIsUpToDate'][value="+params.isUpToDate+"]").attr("checked","checked");
                $("input[name='rrbIsForcedUpDate'][value="+params.isForcedUpDate+"]").attr("checked","checked");
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
    });
}

//验证表单信息
function validate(type) {
    var params = null;
    if (type == "fm") {
        var fmNumber = $("#fmNumber").val();//版本号
        var fmAppKey = $("#fmAppKey").val();//移动操作系统
        var fmVersionUrl = $("#fmVersionUrl").val();//版本地址
        var fmRemark = $("#fmRemark").val();//备注
        var fmIsUpToDate = $("input[name='fmIsUpToDate']:checked").val()//是否是最新
        var fmIsForcedUpDate = $("input[name='fmIsForcedUpDate']:checked").val()//是否强制更新
        if (fmNumber == "") {
            alert("请输入版本号");
            return;
        }
        if (fmAppKey == "") {
            alert("请选择移动操作系统");
            return;
        }
        if (fmVersionUrl == "") {
            alert("请输入版本地址");
        }
        if (fmRemark == "") {
            alert("请输入备注");
        }
        if (fmIsUpToDate == "") {
            alert("请选择是否是最新");
        }
        if (fmIsForcedUpDate == "") {
            alert("请选择是否强制更新");
        }

        //返回的参数
        params = {
            type: type,
            number: fmNumber,
            appKey: fmAppKey,
            versionUrl: fmVersionUrl,
            remark: fmRemark,
            isUpToDate: fmIsUpToDate,
            isForcedUpDate: fmIsForcedUpDate
        }
        return params;
    }

    if (type == "rrb") {
        var rrbNumber = $("#rrbNumber").val();//版本号
        var rrbAppKey = $("#rrbAppKey").val();//移动操作系统
        var rrbVersionUrl = $("#rrbVersionUrl").val();//版本地址
        var rrbRemark = $("#rrbRemark").val();//备注
        var rrbIsUpToDate = $("input[name='rrbIsUpToDate']:checked").val()//是否是最新
        var rrbIsForcedUpDate = $("input[name='rrbIsForcedUpDate']:checked").val()//是否强制更新
        if (rrbNumber == "") {
            alert("请输入版本号");
            return;
        }
        if (rrbAppKey == "") {
            alert("请选择移动操作系统");
            return;
        }
        if (rrbVersionUrl == "") {
            alert("请输入版本地址");
        }
        if (rrbRemark == "") {
            alert("请输入备注");
        }
        if (rrbIsUpToDate == "") {
            alert("请选择是否是最新");
        }
        if (rrbIsForcedUpDate == "") {
            alert("请选择是否强制更新");
        }
        //返回的参数
        params = {
            type: type,
            number: rrbNumber,
            appKey: rrbAppKey,
            versionUrl: rrbVersionUrl,
            remark: rrbRemark,
            isUpToDate: rrbIsUpToDate,
            isForcedUpDate: rrbIsForcedUpDate
        }
        return param
    }
}