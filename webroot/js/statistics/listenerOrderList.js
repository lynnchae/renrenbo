var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";
var rechargeOrder = this;

$(function($) {

        jQuery(grid_selector).jqGrid({
        url:'getListenerOrderList',
        datatype: "json",
        height: 450,
        cellLayout:4,
        colNames:['主键','听众','箱子','奖品','领取方式','主播', '状态','打赏时间','操作'],
        colModel:[
            {name:'id',width:"10",index:'id',hidden:true,align:'center'},
            {name:'listenerName',index:'listenerName',align:'center',width:"40",editable: true},
            {name:'boxName',index:'boxName',width:'65',align:'center',editable:true},
            {name:'prizeName',index:'prizeName',align:'center',width:"40",editable: true},
            {name:'cancelType',index:'cancelType',align:'center',width:"40", editable: true,formatter:setCancelType},
            {name:'anchorName',index:'anchorName',align:'center',width:"40",editable: true},
            {name:'status',index:'status',align:'center',width:"40",formatter:setStatus},
            {name:'createTime',index:'createTime',align:'center',width:"40"},
            {name:'shoppingAddressID',index:'shoppingAddressID',align:'center', width:"100", fixed:true, sortable:false, resize:false,formatter:setOperationBut},
        ],
        viewrecords : true,
        rowNum:10,
        rowList:[10,20,30],
        pager : pager_selector,
        //toppager: true, altRows: true,
        multiselect: true,
        //multikey: "ctrlKey",
        multiboxonly: true,

        loadComplete : function() {
            var table = this;
            setTimeout(function(){
                updatePagerIcons(table);
                enableTooltips(table);
            }, 0);

        },
        caption: "开箱记录",
        autowidth: true

    });


    //navButtons
    jQuery(grid_selector).jqGrid('navGrid',pager_selector,
        {
            //修改
            edit: false,
            editicon : 'icon-pencil blue',

            //新增
            add: false,
            addicon : 'icon-plus-sign purple',

            //删除
            del: false,
            delicon : 'icon-trash red',
            //delfunc:deleteFunc,

            //搜索
            search: true,
            searchicon : 'icon-search orange',

            //刷新
            refresh: true,
            refreshicon : 'icon-refresh green',

            //查看
            view: false,
            viewicon : 'icon-zoom-in grey'

        }
    )

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

    //设置状态
    function setStatus(cellvalue){
        if(cellvalue == 0){
            return "已生成订单";
        }else if(cellvalue == 1){
            return "订单已处理";
        }else{
            return "";
        }
    }

    //设置类型
    function setCancelType(cellvalue){
        if(cellvalue == 1){
            return "自己获取";
        }else if(cellvalue == 2){
            return "送主播";
        }else{
            return "";
        }
    }

    //设置操作按钮
    function setOperationBut(cellvalue, options, cell) {
        var str = "<div class='visible-md visible-lg hidden-sm hidden-xs action-buttons'>" +
            "<a title='订单详情' href=\"shoppingDetail?orderId="+ cell.id +"\" target='includePage'><i class='icon-eye-open bigger-130'></i></a>" +
            "</div>";
        console.log("111:"+cell.cancelType);
        if(cell.cancelType == 1){
            return str;
        }else{
            return "";
        }
    }
});

function search(){
    //var type = $("#type option:selected").val();
    var boxName = $("#boxName").val();
    var prizeName = $("#prizeName").val();
    var status = $("#status option:selected").val();
    var cancelType = $("#cancelType option:selected").val();
    var curpagenum = $(grid_selector).jqGrid('getGridParam', 'page');   //当前页码
    $(grid_selector).jqGrid("setGridParam",
        {url:"getListenerOrderList?boxName="+boxName+"&prizeName="+prizeName+"&status="+status+"&cancelType="+cancelType, page:curpagenum, datatype:"json"}).trigger("reloadGrid");

}
//重新加载数据
function reloadData(){
    $(grid_selector).jqGrid("setGridParam",
        {url:"getListenerOrderList?", page:1, datatype:"json"}).trigger("reloadGrid");

}

