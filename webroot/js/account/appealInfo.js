var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";
var apppeal = this;

$(function($) {

    //第一次加载数据
    jQuery(grid_selector).jqGrid({
        url: 'getAppealInfoList',
        datatype: "json",
        height: 450,
        cellLayout:4,
        colNames: ['主键','申诉人ID','申诉人','身份证号', '申诉原因','创建时间','操作'],
        colModel: [
            {name: 'id', width: "10", index: 'id', hidden: true, align: 'center'},
            {name: 'accountID', width: "10", index: 'accountID', hidden: true, align: 'center'},
            {name: 'complainantName', width: "10", index: 'complainantName', align: 'center'},
            {name: 'idCard', width: "30", index: 'idCard', align: 'center'},
            {name: 'reason', index: 'reason', align: 'center', width: "30"},
            {name: 'createTime', index: 'createTime', align: 'center', width: "30"},
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
        caption: "申诉信息列表",
        autowidth: true

    });
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
    var appealName = $.trim($("#appealName").val());//申诉人
    var idCard = $.trim($("#idCard").val());//身份证号
    var curpagenum = $(grid_selector).jqGrid('getGridParam', 'page');   //当前页码
    $(grid_selector).jqGrid("setGridParam",
        {url:"getAppealInfoList?appealName="+appealName+"&idCard="+idCard, page:curpagenum, datatype:"json"}).trigger("reloadGrid");

}

//设置按钮操作
function setOperationBut(cellvalue, options, cell) {
    var str = "<div class='visible-md visible-lg hidden-sm hidden-xs action-buttons'>" +
        "<a class='relievePlay' href='#' title='解禁操作' onclick=\"relievePlay(" + cell.id+ ")\"><i class='icon-eye-open bigger-130'></i></a>" +
        "</div>";
    if(cell.status == 0 ){
        return "";
    }else{
        return str;
    }
}


//解禁操作
function relievePlay(id){
    //获取选中行的值
    var model = $(grid_selector).jqGrid('getRowData',id);
    $("#dialog_name").html(model.complainantName);
    $("#dialog_idCard").html(model.idCard);
    var dialog = $("#dialog-message-appeal").removeClass('hide').dialog({
        modal: true,
        title: "解禁设置",
        title_html: true,
        width:300,
        buttons: [
            {
                text: "关闭",
                "class": "btn btn-xs",
                click: function () {
                    $(this).dialog("close");
                }
            },
            {
                text: "提交信息",
                "class": "btn btn-primary btn-xs",
                click: function () {
                    //验证表单信息
                    var params = apppeal.validate(id);
                    var dialog = this;
                    apppeal.submit(id,params,dialog);
                }
            }
        ]
    });
}

//修改状态提交
function submit(id,params,dialog) {
    $.ajax({
        url: 'addRelievePlay',
        type: 'post',
        cache: false,
        data:params,
        success: function (data){
            if(data == 'true'){
                alert("信息提交成功！");
                apppeal.search();
                $(dialog).dialog("close");
            }else{
                alert("信息提交失败！");
            }
        }
    });
}

//验证表单信息
function validate(id){
    //获取选中行的值
    var model = $(grid_selector).jqGrid('getRowData',id);
    var accountID = model.accountID;
    var status = $("input[name='dialog_status']:checked").val();//解禁
    var reason = $.trim($("#dialog_reason").val());//备注信息
    if(status == ""){
        alert("请选择解禁");
        return;
    }
    //返回的参数
    var params={
        status:status,
        reason:reason,
        accountID:accountID
    };
    return params;
}