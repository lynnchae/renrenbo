var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";
var rechargeOrder = this;

$(function($) {

        jQuery(grid_selector).jqGrid({
        url:'getAllRechargeOrder',
        datatype: "json",
        height: 450,
        cellLayout:4,//'充值账号',
        colNames:['主键','用户ID','昵称','手机号','充值订单号','金币数','金额（元）','订单状态' ,'充值方式','创建时间'],
        colModel:[
            {name:'id',width:"10",index:'id',hidden:true,align:'center'},
            {name:'accountID',index:'accountID',align:'center',width:"40",editable: true},
            {name:'nickName',index:'nickName',width:'20',align:'center',editable:true},
            {name:'mobile',index:'mobile',width:'50',align:'center',editable:true},
            {name:'orderNo',index:'orderNo',align:'center',width:"60",editable: true},
            //{name:'rechargeNum',index:'rechargeNum',align:'center',width:"60",editable: true},
            {name:'denseNum',index:'denseNum',align:'center',width:"40",editable: true,editoptions:{size:"20",maxlength:"30"}},
            {name:'totalMoney',index:'totalMoney',align:'center',width:"40", editable: true},
            {name:'orderStatus',index:'orderStatus',align:'center',width:"50",editable: true, formatter:setStatus},
            {name:'type',index:'type',align:'center',width:"40",editable: true,formatter:setType},
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
        caption: "充值记录列表",
        autowidth: true

    });

    //设置状态
    function setStatus(cellvalue){
        if(cellvalue == 0){
            return "未支付";
        }else if(cellvalue == 1){
            return "支付中";
        }else if(cellvalue == 2){
            return "已完成";
        }
    }

    //设置状态
    function setType(cellvalue){
        if(cellvalue == 1){
            return "支付宝";
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
    var listenerName = $.trim($("#listenerName").val());
    var status =  $.trim($("#status option:selected").val());
    //var rechargeNum = $.trim($("#rechargeNum").val());
    var mobile = $.trim($("#mobile").val());
    var curpagenum = $(grid_selector).jqGrid('getGridParam', 'page');   //当前页码
    $(grid_selector).jqGrid("setGridParam",
        {url:"getAllRechargeOrder?listenerName="+listenerName+"&orderStatus="+status+"&rechargeNum="+rechargeNum+"&mobile="+mobile, page:curpagenum, datatype:"json"}).trigger("reloadGrid");

}
//重新加载数据
function reloadData(){
    $(grid_selector).jqGrid("setGridParam",
        {url:"getAllRechargeOrder?", page:1, datatype:"json"}).trigger("reloadGrid");

}


