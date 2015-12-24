var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";
var account = this;

$(function($) {

    //第一次加载数据
    jQuery(grid_selector).jqGrid({
        url: 'getAccountInfoList',
        datatype: "json",
        height: 450,
        cellLayout:4,
        colNames: ['主键','用户ID', '昵称','真实姓名','主播头像','关注城市','身份证号', '用户手机号','用户的性别','标签','财富值','资产','人气数', '状态','是否允许播音','创建时间','操作'],
        colModel: [
            {name: 'id', width: "10", index: 'id', hidden: true, align: 'center'},
            {name: 'accountID', width: "10", index: 'accountID', hidden: true, align: 'center'},
            {name: 'nickName', index: 'nickName', align: 'center', width: "20"},
            {name: 'realName', index: 'realName', align: 'center', width: "20"},
            {name: 'headPic', index: 'headPic', align: 'center', width: "25",formatter: getPicUrl,editType:"image"},
            {name: 'cityName', index: 'cityName', align: 'center', width: "30"},
            {name: 'idCard', index: 'idCard', align: 'center', width: "40"},
            {name: 'mobile', index: 'mobile', align: 'center', width: "25"},
            {name: 'sex', index: 'sex', align: 'center', width: "20"},
            {name: 'remark', index: 'remark', align: 'center', width: "30"},
            {name: 'diamondWealthVal', index: 'diamondWealthVal', width: '20', align: 'center'},
            {name: 'diamond', index: 'diamond', width: '20', align: 'center'},
            {name: 'popularityVal', index: 'popularityVal', width: '20', align: 'center'},
            {name: 'status', index: 'status', align: 'center', width: "30",formatter:setType ,editType:"select",editOptions: {value:'0:不申请审核;1:申请审核;2:审核中;3:审核成功;4:审核失败;5:禁播;6:解禁'}},
            {name: 'isVoice', index: 'isVoice', align: 'center', width: "20",editable: true, formatter:setIsVoiceOptions,editType:"select",editOptions:{value:'0:不允许;1:允许'}},
            //受理人
            //{name: 'acceptPerson', index: 'acceptPerson', align: 'center', width: "20"},
            {name: 'createTime', index: 'createTime', width: '30', align: 'center'},
            {
                name: 'myac',
                index: '',
                align: 'center',
                width: "80",
                fixed: true,
                sortable: false,
                resize: false,
                formatter: setOperationBut
            }
        ],
        viewrecords: true,
        rowNum: 10,
        rowList: [10, 20, 30],
        pager: pager_selector,
        multiselect: true,
        multiboxonly: true,

        loadComplete : function() {
            var table = this;
            setTimeout(function(){
                updatePagerIcons(table);
                enableTooltips(table);
            }, 0);

        },
        caption: "主播信息列表",
        autowidth: true,
        //cellEdit : true,//是否允许单元格可编辑
        //cellurl : 'editIsVoice'//编辑是否允许播音

    });

    //主播头像
    function getPicUrl(cellvalue,options,cell){
        return "<img  style='border-radius:100%;border:1px solid #5293c4;max-width:30px; '  " +
            "src='"+ cellvalue+"'/>";
    }

    function updatePagerIcons(table) {
        var replacement =
        {
            'ui-icon-seek-first' : 'icon-double-angle-left bigger-140',
            'ui-icon-seek-prev' : 'icon-angle-left bigger-140',
            'ui-icon-seek-next' : 'icon-angle-right bigger-140',
            'ui-icon-seek-end' : 'icon-double-angle-right bigger-140'
        };
        $('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
            var icon = $(this);
            var $class = $.trim(icon.attr('class').replace('ui-icon', ''));

            if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
        })
    }

    //unlike navButtons icons, action icons in rows seem to be hard-coded
    //you can change them like this in here if you want
    function updateActionIcons(table) {
         var replacement =
         {
             'ui-icon-pencil' : 'icon-pencil blue',
             'ui-icon-trash' : 'icon-trash red',
             'ui-icon-disk' : 'icon-ok green',
             'ui-icon-cancel' : 'icon-remove red'
         };
         $(table).find('.ui-pg-div span.ui-icon').each(function(){
						var icon = $(this);
						var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
						if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
					})
    }

    //replace icons with FontAwesome icons like above
    function updatePagerIcons(table) {
        var replacement =
        {
            'ui-icon-seek-first' : 'icon-double-angle-left bigger-140',
            'ui-icon-seek-prev' : 'icon-angle-left bigger-140',
            'ui-icon-seek-next' : 'icon-angle-right bigger-140',
            'ui-icon-seek-end' : 'icon-double-angle-right bigger-140'
        };
        $('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
            var icon = $(this);
            var $class = $.trim(icon.attr('class').replace('ui-icon', ''));

            if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
        })
    }

    function enableTooltips(table) {
        $('.navtable .ui-pg-button').tooltip({container:'body'});
        $(table).find('.ui-pg-div').tooltip({container:'body'});
    }

    //navButtons
    jQuery(grid_selector).jqGrid('navGrid',pager_selector,
        { 	//navbar options
            edit: false,
            editicon : 'icon-pencil blue',//修改
            add: false,
            addicon : 'icon-plus-sign purple',//新增
            del: false,
            delicon : 'icon-trash red',//删除
            search: false,
            searchicon : 'icon-search orange',//搜索
            refresh: true,
            refreshicon : 'icon-refresh green',//刷新
            view: true,
            viewicon : 'icon-zoom-in grey',//查看
        },
        {
            //edit record form
            //closeAfterEdit: true,
            recreateForm: true,
            beforeShowForm : function(e) {
                var form = $(e[0]);
                form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
                style_edit_form(form);
            }
        },
        {
            //new record form
            closeAfterAdd: true,
            recreateForm: true,
            viewPagerButtons: false,
            beforeShowForm : function(e) {
                var form = $(e[0]);
                form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
                style_edit_form(form);
            }
        },
        {
            //delete record form
            recreateForm: true,
            beforeShowForm : function(e) {
                var form = $(e[0]);
                if(form.data('styled')) return false;

                form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
                style_delete_form(form);

                form.data('styled', true);
            },
            onClick : function(e) {
                alert(1);
            }
        },
        {
            //search form
            recreateForm: true,
            afterShowSearch: function(e){
                var form = $(e[0]);
                form.closest('.ui-jqdialog').find('.ui-jqdialog-title').wrap('<div class="widget-header" />')
                style_search_form(form);
            },
            afterRedraw: function(){
                style_search_filters($(this));
            }
            ,
            multipleSearch: true,
            /**
             multipleGroup:true,
             showQuery: true
             */
        },
        {
            //view record form
            recreateForm: true,
            beforeShowForm: function(e){
                var form = $(e[0]);
                form.closest('.ui-jqdialog').find('.ui-jqdialog-title').wrap('<div class="widget-header" />')
            }
        }
    )

});

//查询按钮
function search(){

    var anchorStatus = $("#anchorStatus option:selected").val();
    var cityName = $("#cityName").val();
    var mobile = $("#mobile").val();
    var curpagenum = $(grid_selector).jqGrid('getGridParam', 'page');   //当前页码
    $(grid_selector).jqGrid("setGridParam",
        {url:"getAccountInfoList?anchorStatus="+anchorStatus+"&cityName="+cityName+"&mobile="+mobile, page:curpagenum, datatype:"json"}).trigger("reloadGrid");

}

//设置操作按钮
function setOperationBut(cellvalue, options, cell) {
    var str = "<div class='visible-md visible-lg hidden-sm hidden-xs action-buttons'>" +
        "<a class='viewDetails' href='#' title='审核提交' onclick=\"viewDetails(" + cell.id+ ")\"><i class='icon-eye-open bigger-130'></i></a>" +
        //"<a class='noPlaySetting' href='#' title='禁播设置' onclick=\"noPlaySetting(" +cell.id+")\"><i class=' icon-edit bigger-130'></i></a>" +
        "</div>";
    if(cell.status == 0 ){
        return "";
    }else{
        return str;
    }
}

//设置审核状态
function setType(status){
    if(status == 0){
        return "不申请审核 ";
    }
    if(status == 1){
        return "申请审核";
    }
    if(status == 3){
        return "审核成功";
    }
    if(status == 4){
        return "审核失败";
    }
    if(status == 5){
        return "禁播";
    }
    if(status == 6){
        return "解禁";
    }
}

//设置是否允许播音
function setIsVoiceOptions(isVoice) {
    if (isVoice == 0) {
        return "不允许";
    }
    if (isVoice == 1) {
        return "允许";
    }
}

//查看详情按钮
function viewDetails(id){
    //获取选中行的值
    var model = $(grid_selector).jqGrid('getRowData',id);
    var pic = model.headPic;
    var src = pic.substring(pic.lastIndexOf("=")+2,pic.length-2);
    $("#dialog_headPic").attr("src",src);
    $("#dialog_nickName").html(model.nickName);
    $("#dialog_name").html(model.realName);
    $("#dialog_sex").html(model.sex);
    $("#dialog_remark").html(model.remark);
    $("#dialog_mobile").html(model.mobile);
    $("#dialog_idCard").html(model.idCard);
    var dialog = $("#dialog-message-verify").removeClass('hide').dialog({
        modal: true,
        title: "主播详情",
        title_html: true,
        width:500,
        buttons: [
            {
                text: "关闭",
                "class": "btn btn-xs",
                click: function () {
                    $(this).dialog("close");
                }
            },
            {
                text: "审核提交",
                "class": "btn btn-primary btn-xs",
                click: function () {
                    //验证表单信息
                    var params = account.validate(id);
                    var dialog = this;
                    account.submit(id,params,dialog);
                }
            }
        ]
    });
}

//禁播设置按钮
function noPlaySetting(id){
    var model = $(grid_selector).jqGrid('getRowData',id);
    //Ajax 查询投诉主播的信息
    //获取举报人的所有ID
    //$.ajax({
    //    url: 'getBeAccounts',
    //    type: 'post',
    //    dataType: 'json',
    //    data:{'accountID':model.accountID},
    //    error: function() { alert('查询不出数据!'); },
    //    success: function(msg) {
    //        alert("含有数据的");
    //        //清空下拉列表中的数据
    //        for(var i=0 ; i<msg.length ; i++){
    //            var str = data[i];
    //            alert(str.ky);
    //            alert(str.val);
    //        }
    //        $("#dialog_complaint_name").empty();
    //        $.each(msg.toString(), function(key, val) {
    //            $("<option value='" +msg[key]+ "'>" + msg[val] + "</option>").appendTo($("#dialog_complaint_name"));
    //        });
    //        //  loadCity($("#province").val());
    //    }
    //});
    //获取选中行的值
    var model = $(grid_selector).jqGrid('getRowData',id);
    //$("#dialog_noplay_nickName").html(model.nickName);
    var dialog = $("#dialog-message-noPlay").removeClass('hide').dialog({
        modal: true,
        title: "禁播设置",
        title_html: true,
        width:500,
        buttons: [
            {
                text: "关闭",
                "class": "btn btn-xs",
                click: function () {
                    $(this).dialog("close");
                }
            },
            {
                text: "设置提交",
                "class": "btn btn-primary btn-xs",
                click: function () {
                    //验证表单信息
                    var params = account.validate(id);
                    var dialog = this;
                    account.noPlaySub(id,null,dialog);
                }
            }
        ]
    });
}

//审核提交
function submit(id,params,dialog) {
    $.ajax({
        url: 'submitAuditAccountInfo',
        type: 'get',
        cache: false,
        data:params,
        success: function (data){
            if(data == 'true'){
                alert("审核成功！");
                account.search();
                $(dialog).dialog("close");
            }else{
                alert("审核失败！");
            }
        }
    });
}

//禁播设置提交
function noPlaySub(id,params,dialog){
    //获取选中行的值
    var model = $(grid_selector).jqGrid('getRowData',id);
    var accountID = model.accountID;//禁播人员
    var status = model.status;//禁播前状态
    $("#dialog_nickName").html(model.nickName);
    $("#dialog_name").html(model.realName);
    //获取禁播设置
    var status = $("input[name='dialog_ban']:checked").val();
    //选择禁播操作
    if(status == 0){
        //禁播原因
        var reason = $("#dialog_noplay_reason").val();
        subNoPlay(accountID,status,reason);
    }
    //选择解禁操作
    if(status == 1){
        subRelievePlay(accountID);
    }
}

//禁播
function subNoPlay(accountID,status,reason){
    $.ajax({
        url: 'addNoPlay',
        type: 'get',
        cache: false,
        data:{'accountID':accountID,'status':status,'reson':reson},
        success: function (data){
            if(data == 'true'){
                alert("禁播成功！");
                account.search();
                $(dialog).dialog("close");
            }else{
                alert("禁播失败！");
            }
        }
    });
}

//解禁
function subRelievePlay(accountID){
    var type = 0;//人工解禁
    $.ajax({
        url: 'addRelievePlay',
        type: 'get',
        cache: false,
        data:{'accountID':accountID,'type':type},
        success: function (data){
            if(data == 'true'){
                alert("解禁成功！");
                account.search();
                $(dialog).dialog("close");
            }else{
                alert("解禁失败！");
            }
        }
    });
}

//验证表单信息
function validate(id){
    var status = $("input[name='dialog_status']:checked").val()//审核状态
    var reason = $("#dialog_reason").val();//备注
    alert(status);
    if(status == ""){
        alert("请选择审核状态");
        return;
    }
    if(reason == ""){
        alert("请输入备注");
        return;
    }
    //返回的参数
    var params={
        id:id,
        status:status,
        reason:reason
    }
    return params;
}