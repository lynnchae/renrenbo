var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";
var rechargeOrder = this;

$(function($) {

        jQuery(grid_selector).jqGrid({
        url:'getAllPayBackRecord',
        datatype: "json",
        height: 450,
        cellLayout:4,
        colNames:['主键','订单编号','交易编号','交易状态','创建时间'],
        colModel:[
            {name:'id',width:"10",index:'id',hidden:true,align:'center'},
            {name:'orderNo',index:'orderNo',align:'center',width:"40"},
            {name:'tradeNo',index:'tradeNo',width:'65',align:'center'},
            {name:'tradeStatus',index:'tradeStatus',align:'center',width:"40",formatter:setStatus},
            {name:'createTime',index:'createTime',align:'center',width:"40"}
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
        caption: "支付回调记录列表",
        autowidth: true

    });

    //设置状态
    function setStatus(cellvalue){
        if(cellvalue == "WAIT_BUYER_PAY"){
            return "等待支付";
        }else if(cellvalue == "TRADE_FINISHED"){
            return "支付完成";
        }else if(cellvalue == "TRADE_SUCCESS"){
            return "支付成功";
        }
    }

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
});

function search(){
    //var type = $("#type option:selected").val();
    var orderNo = $("#orderNo").val();
    var tradeStatus = $("#tradeStatus option:selected").val();
    var curpagenum = $(grid_selector).jqGrid('getGridParam', 'page');   //当前页码
    $(grid_selector).jqGrid("setGridParam",
        {url:"getAllPayBackRecord?orderNo="+orderNo+"&tradeStatus="+tradeStatus, page:curpagenum, datatype:"json"}).trigger("reloadGrid");

}
//重新加载数据
function reloadData(){
    $(grid_selector).jqGrid("setGridParam",
        {url:"getAllPayBackRecord?", page:1, datatype:"json"}).trigger("reloadGrid");

}


