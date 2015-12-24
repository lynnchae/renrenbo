var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";
var withdrawalsOperation = this;
var subgrid_table_id = "";

$(function($) {

    //第一次加载数据
    jQuery(grid_selector).jqGrid({
        url: 'getPresentUserList',
        datatype: "json",
        height: 450,
        cellLayout: 4,
        colNames: ['主键','是否显示操作按钮','用户昵称', '真实姓名', '手机号','财富值','钻数','待提取金额（元）','已选金额（元）','操作'],
        colModel: [
            {name: 'id', width: "10", index: 'id', hidden: true, align: 'center'},
            {name: 'display', index: 'display',hidden: true, align: 'center', width: "20"},
            {name: 'nickName', index: 'nickName', align: 'center', width: "20"},
            {name: 'realName', index: 'realName', align: 'center', width: "20"},
            {name: 'mobile', index: 'mobile', align: 'center', width: "30"},
            {name: 'wealthVal', index: 'wealthVal', align: 'center', width: "15"},
            {name: 'diamond', index: 'diamond', align: 'center', width: "15"},
            {name: 'waitMoney', index: 'waitMoney', align: 'center', width: "15"},
            {name: 'selectedMoney', index: 'selectedMoney', align: 'center', width: "15"},
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
        loadComplete: function () {
            var table = this;
            setTimeout(function () {
                updatePagerIcons(table);
                enableTooltips(table);
            }, 0);

        },
        caption: "用户提现信息列表",
        autowidth: true,
        subGrid: true,
        subGridRowExpanded:function(subgrid_id, row_id){  //参数 tableID 行ID
            //子表格容器的id和需要展开子表格的行id，将传入此事件函数
            subgrid_table_id;
            subgrid_table_id = subgrid_id + "_t";//根据subgrid_id定义对应的子表格的table的id
            var subgrid_pager_id;
            subgrid_pager_id = subgrid_id + "_pgr"//根据subgrid_id定义对应的子表格的pager的id
            //动态添加子报表的table和pager
            $("#" + subgrid_id).html("<table id='"+subgrid_table_id+"' style='font-size: 12px;color: #1E90FF' class='scroll'></table><div id='"+subgrid_pager_id+"'class='scroll'></div>");
            //创建jqGrid对象
            $("#" + subgrid_table_id).jqGrid({
                url: "getPresentDetails?id="+row_id,
                datatype: "json",
                cellLayout: 4,
                colNames: ['父级菜单ID','主键','账户','账户类型','提现金额(单位：元)','交易状态','回执单号','创建时间','备注'],
                colModel: [
                    {name: 'rowID', width: "100", index: 'rowID', hidden: true, align: 'center',formatter: setRefresh},
                    {name: 'id', width: "100", index: 'id', hidden: true, align: 'center'},
                    {name: 'alipayAccount', index: 'alipayAccount', align: 'center', width: "200"},
                    {name: 'type', index: 'type', align: 'center', width: "150",formatter: setType,editType: "select", editOptions: {value: '1:支付宝;'}},
                    {name: 'cashNum', index: 'cashNum', align: 'center', width: "200"},
                    {
                        name: 'status',
                        index: 'status',
                        align: 'center',
                        width: "150",
                        formatter: setStatutsType,
                        editType: "select",
                        editOptions: {value: '1:待入账;2:入账失败;3:已入账;'}
                    },
                    {name: 'receiptNumber', index: 'receiptNumber', align: 'center', width: "220"},
                    {name: 'createTime', index: 'createTime', width: '150', align: 'center'},
                    {name: 'reason', index: 'reason', align: 'center', width: "400"},
                ],
                prmNames: {search: "search"},
                multiselect: true,
                multiboxonly: true,
                height: "100%",
                caption: "该用户提现信息列表",
                autowidth: true,
                //全选事件
                onSelectAll:function(rowid, status) { //rowid 数组,所有ID的集合,选中status为true，取消选中为false
                    var ids = jQuery("#"+subgrid_table_id).jqGrid('getDataIDs');
                    var money = 0;//已选金额
                    if(status){
                        for (var i=0; i<ids.length; i++) {
                            var cl = ids[i];
                            var model = jQuery("#"+subgrid_table_id).jqGrid('getRowData', cl);
                            money = money*1 + model.cashNum*1;
                            jQuery(grid_selector).setCell (row_id,"selectedMoney",money.toFixed(2),{color:'#2E8B57'});
                        }
                    }else{
                        jQuery(grid_selector).setCell (row_id,"selectedMoney",money.toFixed(2),{color:'#2E8B57'});
                    }
                },
                //选择单行事件
                onSelectRow: function (rowid, status) {//某行的ID 选中status为true，取消选中为false
                    //获取已选金额
                    var money = jQuery(grid_selector).getCell(row_id, "selectedMoney");
                    var model = jQuery("#"+subgrid_table_id).jqGrid('getRowData', rowid);
                    if(status){
                        money = money*1 + model.cashNum*1;
                    }else{
                        money = money*1 - model.cashNum*1;
                    }
                    jQuery(grid_selector).setCell (row_id,"selectedMoney",money.toFixed(2),{color:'#2E8B57'});
                },
            });
        }
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
        {
            //新增
            add: false,
            addicon : 'icon-plus-sign purple',
            //修改
            edit: false,
            editicon : 'icon-pencil blue',
            //删除
            del: false,
            delicon : 'icon-trash red',
            deltitle:'删除',
            //搜索
            search: false,
            searchicon : 'icon-search orange',
            //刷新
            refresh: true,
            refreshicon : 'icon-refresh green',
            refreshtitle:'刷新',
            //查看
            view: true,
            viewicon : 'icon-zoom-in grey',
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
            },
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
    );

});

//查询按钮
function search() {
    var nickName = $.trim($("#nickName").val());//用户昵称
    var realName = $.trim($("#realName").val());//真实姓名
    var mobile = $.trim($("#mobile").val());//手机号
    var curpagenum = $(grid_selector).jqGrid('getGridParam', 'page');   //当前页码
    $(grid_selector).jqGrid("setGridParam",
        {
            url: "getPresentUserList?nickName=" + nickName + "&realName=" + realName+"&mobile="+mobile,
            page: curpagenum,
            datatype: "json"
        }).trigger("reloadGrid");

}

//子列表刷新
function subListRefresh(id) {
    //var curpagenum = $(grid_selector).jqGrid('getGridParam', 'page');   //当前页码
    $("#"+subgrid_table_id).jqGrid("setGridParam",
        {
            url: "getPresentDetails?id=" + id,
           // page: curpagenum,
            datatype: "json"
        }).trigger("reloadGrid");

}
//刷新已选金额
function setRefresh(rowID){
    jQuery(grid_selector).setCell (rowID,"selectedMoney",0,{color:'#436EEE'});
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

//是否显示提现复选框
//function  showCheckbox(cellvalue, options, cell){
//    //var str = "<input type='checkbox' onclick=\"selectWithdrawals(" + cell.id+ ")\" />";
//    var str = "<input type='checkbox' />";
//    if(cell.status == 1 ){
//        return str;
//    }else{
//        return "";
//    }
//}

//设置操作按钮
function setOperationBut(cellvalue, options, cell) {
    var str = "<div class='visible-md visible-lg hidden-sm hidden-xs action-buttons'>" +
        "<a class='updateStatus' href='#' title='修改提现信息' onclick=\"updateStatus(" + cell.id+ ")\"><i class='icon-eye-open bigger-130'></i></a>" +
        "</div>";
    return str;
}

//存放子列表选中的提现信息ID
var ids = 0;

//弹出修改状态对话框
function updateStatus(id){
    ids = jQuery("#"+subgrid_table_id).jqGrid('getGridParam', 'selarrrow');
    if(ids == null){
        alert("请展开子列表信息，并选择需要操作的信息。");
        return;
    }
    if(ids == 0){
        alert("请在该用户子列表中选择需要提现的信息。");
        return;
    }
    //获取选中行的值
    var model = $(grid_selector).jqGrid('getRowData',id);
    $("#dialog_nickName").html(model.nickName);
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
                text: "信息提交",
                "class": "btn btn-primary btn-xs",
                click: function () {
                    //验证表单信息
                    var dialog = this;
                    withdrawalsOperation.submit(id,dialog);
                }
            }
        ]
    });
}

//修改状态提交
function submit(id,dialog) {
    //验证表单信息
    var status = $("input[name='dialog_status']:checked").val();//交易状态
    var reason = $.trim($("#dialog_reason").val());//备注信息
    var receiptNumber = $.trim($("#dialog_receipt_number").val());//回执单号
    if(status == ""){
        alert("请选择提现状态");
        return;
    }
    $.ajax({
        url: "updatWithdrawCashOfStatus?status="+status+"&reason="+reason+"&receiptNumber="+receiptNumber+"&ids="+ids+"&id="+id,
        type: 'get',
        cache: false,
        //data:params,
        success: function (data){
            if(data == 'true'){
                alert("信息提交成功！");
                $("input:radio[name='dialog_status']").attr('checked',false);
                $("#dialog_reason").attr("value","");
                $("#dialog_receipt_number").attr("value","");
                withdrawalsOperation.search();
                withdrawalsOperation.subListRefresh(id);
                $(dialog).dialog("close");
            }else{
                alert("信息提交失败！");
                $("input:radio[name='dialog_status']").attr('checked',false);
                $("#dialog_reason").attr("value","");
                $("#dialog_receipt_number").attr("value","");
                $(dialog).dialog("close");
            }
        }
    });
}



//jQuery("#listTable").jqGrid({
//    url: 'queryList.do',
//    datatype: 'json',
//    colNames: ['','编号','姓名'],
//    colModel: [
//        {
//            name: 'MY_ID',
//            index: 'MY_ID',
//            sortable: false,
//            width: '0%',
//            hidden:true
//        },
//        {
//            name: 'MY_NO',
//            index:'MY_NO',
//            sortable: false,
//            align:'center',
//            width:'10%'
//        },
//        {
//            name: 'NAME',
//            index:'NAME',
//            sortable: false,
//            align:'center',
//            width:'10%'
//        }
//    ],
//    page: 1,
//    rowNum: 10,
//    rowList: [10, 20, 30],
//    pager: '#listPage',
//    multiselect: true,
//    multiboxonly: true,
//    sortname: 'MY_NO',
//    viewrecords: true,
//    sortorder: "desc",
//    jsonReader: {
//        repeatitems: false
//    },
//    width: "100%",
//    height: '100%',
//    gridComplete: function() {
//        var rowIds = jQuery("#listTable").jqGrid('getDataIDs');
//        for(var k=0; k<rowIds.length; k++) {
//            var curRowData = jQuery("#listTable").jqGrid('getRowData', rowIds[k]);
//            if(curRowData.MY_NO == '123456'){
//                $("#listTable").find("input[id='jqg_" + rowIds[k] +
//                    "']").val(curRowData.MY_ID);
//                if(curRowData.NAME=='shihuan'){
//                    $("#listTable").find("input[id='jqg_" + rowIds[k] +
//                        "']").attr("checked", true);
//                }
//            }else{
//                $("#listTable").find("input[id='jqg_" + rowIds[k] +
//                    "']").val(curRowData.MY_ID);
//                $("#listTable").find("input[id='jqg_" + rowIds[k] + "']").attr("checked",
//                    true);
//                $("#listTable").find("input[id='jqg_" + rowIds[k] + "']").attr("disabled",
//                    true);
//            }
//        }
//    }
//    onSelectAll:function(rowid, status) { //rowid 数组
//        var ids = jQuery("#listTable").jqGrid('getDataIDs');
//        for (var i=0; i<ids.length; i++) {
//            var cl = ids[i];
//            var curRowData = jQuery("#listTable").jqGrid('getRowData', cl);
//            var ckt = $("#listTable").find("input[id='jqg_" + cl + "']").attr("disabled");
//            if(ckt){
//                $("#listTable").find("input[id='jqg_" + cl + "']").attr("checked", true);
//            }
//        }
//    },
//    beforeSelectRow:function(rowid, e){
//        //alert(rowid);   //rowid的值是checkbox的value值
//        return false;
//    }
//});