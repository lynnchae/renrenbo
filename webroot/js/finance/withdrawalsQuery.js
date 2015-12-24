var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";
var withdrawalsQuery = this;

$(function($) {
    //第一次加载数据
    jQuery(grid_selector).jqGrid({
        url: 'getWithdrawCashList',
        datatype: "json",
        height: 450,
        cellLayout: 4,
        colNames: ['主键', '用户昵称', '账户', '账户类型', '提现金额(单位：元)', '交易状态','回执单号', '创建时间','备注'],
        colModel: [
            {name: 'id', width: "10", index: 'id', hidden: true, align: 'center'},
            {name: 'nickName', index: 'nickName', align: 'center', width: "20"},
            {name: 'alipayAccount', index: 'alipayAccount', align: 'center', width: "45"},
            {name: 'type', index: 'type', align: 'center', width: "25",formatter: setType,editType: "select", editOptions: {value: '1:支付宝;'}},
            {name: 'cashNum', index: 'cashNum', align: 'center', width: "20"},
            {
                name: 'status',
                index: 'status',
                align: 'center',
                width: "15",
                formatter: setStatutsType,
                editType: "select",
                editOptions: {value: '1:待入账;2:入账失败;3:已入账;'}
            },
            {name: 'receiptNumber', index: 'receiptNumber', align: 'center', width: "20"},
            {name: 'createTime', index: 'createTime', width: '30', align: 'center'},
            {name: 'reason', index: 'reason', align: 'center', width: "55"},
            //{
            //    name: 'myac',
            //    index: '',
            //    align: 'center',
            //    width: "80",
            //    fixed: true,
            //    sortable: false,
            //    resize: false,
            //    formatter: setOperationBut
            //}
        ],
        viewrecords: true,
        rowNum: 10,
        rowList: [10, 20, 30],
        pager: pager_selector,
        multiselect: true,
        multiboxonly: true,

        loadComplete: function () {
            var table = this;
            setTimeout(function () {
                updatePagerIcons(table);
                enableTooltips(table);
            }, 0);

        },
        caption: "提现记录列表",
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
            viewicon : 'icon-zoom-in grey'//查看
        },
        {
            //edit record form
            //closeAfterEdit: true,
            recreateForm: true,
            beforeShowForm : function(e) {
                var form = $(e[0]);
                form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />');
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
                form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />');
                style_edit_form(form);
            }
        },
        {
            //delete record form
            recreateForm: true,
            beforeShowForm : function(e) {
                var form = $(e[0]);
                if(form.data('styled')) return false;

                form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />');
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
                form.closest('.ui-jqdialog').find('.ui-jqdialog-title').wrap('<div class="widget-header" />');
                style_search_form(form);
            },
            afterRedraw: function(){
                style_search_filters($(this));
            }
            ,
            multipleSearch: true
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
    var userName = $.trim($("#userName").val());
    var receiptNumber = $.trim($("#receiptNumber").val());
    var alipayAccount = $.trim($("#alipayAccount").val());
    var curpagenum = $(grid_selector).jqGrid('getGridParam', 'page');   //当前页码
    $(grid_selector).jqGrid("setGridParam",
        {
            url: "getWithdrawCashList?userName=" + userName + "&receiptNumber=" + receiptNumber+"&alipayAccount="+alipayAccount,
            page: curpagenum,
            datatype: "json"
        }).trigger("reloadGrid");

}

//设置账户类型
function setType(type){
    if(type == 1){
        return "支付宝";
    }
}
//设置交易状态
function setStatutsType(status){
    if(status == 1){
        return "待入账 ";
    }
    if(status == 2){
        return "入账失败 ";
    }
    if(status == 3){
        return "已入账 ";
    }
}

//设置操作按钮
function setOperationBut(cellvalue, options, cell) {
    var str = "<div class='visible-md visible-lg hidden-sm hidden-xs action-buttons'>" +
        "<a class='upStatus' href='#' title='修改交易状态' onclick=\"upStatus(" + cell.id+ ")\"><i class='icon-eye-open bigger-130'></i></a>" +
        "</div>";
    //只有当状态为待入账的时候，才显示按钮
    if(cell.status == 3 || cell.status ==2){
        return "";
    }else{
        return str;
    }
}

//弹出修改状态对话框
function upStatus(id){
    //获取选中行的值
    var model = $(grid_selector).jqGrid('getRowData',id);
    $("#dialog_nickName").html(model.nickName);
    $("#dialog_tradeNumber").html(model.tradeNumber);
    $("#dialog_alipayAccount").html(model.alipayAccount);
    $("#dialog_type").html(model.type);
    $("#dialog_cashNum").html(model.cashNum);
    var dialog = $("#dialog-message-status").removeClass('hide').dialog({
        modal: true,
        title: "提现信息",
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
                text: "提交修改",
                "class": "btn btn-primary btn-xs",
                click: function () {
                    //验证表单信息
                    var params = withdrawalsQuery.validate(id);
                    var dialog = this;
                    withdrawalsQuery.submit(id,params,dialog);
                }
            }
        ]
    });
}

//修改状态提交
function submit(id,params,dialog) {
    $.ajax({
        url: 'updatWithdrawCashOfStatus',
        type: 'post',
        cache: false,
        data:params,
        success: function (data){
            if(data == 'true'){
                alert("信息提交成功！");
                $("input:radio[name='dialog_status']").attr('checked',false);
                $("#dialog_receipt_number").val("");
                $("#dialog_reason").val("");
                withdrawalsQuery.search();
                $(dialog).dialog("close");
            }else{
                alert("信息提交失败！");
            }
        }
    });
}

//验证表单信息
function validate(id){
    var status = $("input[name='dialog_status']:checked").val();//交易状态
    var reason = $.trim($("#dialog_reason").val());//备注信息
    var receiptNumber = $.trim($("#dialog_receipt_number").val());//回执单号
    if(status == ""){
        alert("请选择交易状态");
        return;
    }
    //返回的参数
    var params={
        id:id,
        status:status,
        receiptNumber:receiptNumber,
        reason:reason
    };
    return params;
}