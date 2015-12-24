var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";
var wemeCoins = this;

$(function($) {

    //第一次加载数据
    jQuery(grid_selector).jqGrid({
        url: 'getWemeInfoList',
        datatype: "json",
        height: 450,
        cellLayout:4,
        colNames: ['主键','手机号','领取人发送的语音条数','是否已返回密点','创建时间','备注','操作'],
        colModel: [
            {name: 'id', width: "10", index: 'id', hidden: true, align: 'center'},
            {name: 'mobile', width: "15", index: 'mobile', align: 'center'},
            {name: 'vioceNum', index: 'voiceNum', align: 'center', width: "15"},
            {name: 'denseStatus', index: 'denseStatus', align: 'center', width: "15",editable: true,formatter:setDenseStatus},
            {name: 'createTime', index: 'createTime', align: 'center', width: "15"},
            {name: 'reason', index: 'reason', align: 'center', width: "30"},
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
        caption: "微密活动信息列表",
        autowidth: true,
        //cellEdit : true,//是否允许单元格可编辑
        //cellurl : 'editIsVoice'//编辑是否允许播音

    });

    //设置返回微密用户微密的状态
    function setDenseStatus(cellvalue){
        if(cellvalue == 0){
            return "未返回";
        }else if(cellvalue == 1){
            return "已返回";
        }else {
            return "";
        }
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
function search() {
    var denseStatus = $("#denseStatus option:selected").val();//返回微密用户密点数状态
    var mobile = $.trim($("#mobile").val());//手机号
    var curpagenum = $(grid_selector).jqGrid('getGridParam', 'page');//当前页码
    $(grid_selector).jqGrid("setGridParam",
        {url: "getWemeInfoList?denseStatus="+denseStatus+"&mobile="+mobile, page: curpagenum, datatype: "json"}).trigger("reloadGrid");
}

//设置操作按钮
function setOperationBut(cellvalue, options, cell) {
    var str = "<div class='visible-md visible-lg hidden-sm hidden-xs action-buttons'>" +
        "<a class='updateStatus' href='#' title='修改返回微密用户密点数的状态' onclick=\"updateStatus(" + cell.id+ ")\"><i class='icon-eye-open bigger-130'></i></a>" +
        "</div>";
    if(cell.denseStatus == 0 || cell.denseStatus == 1){
        return "";
    }else{
        return str;
    }
}

function updateStatus(id){
    //获取选中行的值
    var model = $(grid_selector).jqGrid('getRowData',id);
    $("#dialog_mobile").html(model.mobile);
    var dialog = $("#dialog-message-status").removeClass('hide').dialog({
        modal: true,
        title: "微密活动信息",
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
                text: "信息提交",
                "class": "btn btn-primary btn-xs",
                click: function () {
                    //验证表单信息
                    var params = wemeCoins.validate(id);
                    var dialog = this;
                    wemeCoins.submit(id,params,dialog);
                }
            }
        ]
    });
}

//信息提交
function  submit(id,params,dialog){
    $.ajax({
        url: 'updateWemeStatus',
        type: 'post',
        cache: false,
        data:params,
        success: function (data){
            if(data == 'true'){
                alert("信息提交成功！");
                $("input:radio[name='dialog_denseStatus']").attr('checked',false);
                $("#dialog_reason").val("");
                wemeCoins.search();
                $(dialog).dialog("close");
            }else{
                alert("信息提交失败！");
            }
        }
    });
}

//验证表单信息
function validate(id){
    var denseStatus = $("input[name='dialog_denseStatus']:checked").val();//返回微密用户密点数状态
    var reason = $.trim($("#dialog_reason").val());//备注信息
    if(denseStatus == ""){
        alert("请选择状态");
        return;
    }
    //返回的参数
    var params={
        id:id,
        denseStatus:denseStatus,
        reason:reason
    };
    return params;
}