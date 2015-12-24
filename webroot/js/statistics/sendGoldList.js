var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";
var rechargeOrder = this;

$(function($) {

        jQuery(grid_selector).jqGrid({
        url:'getSendGoldList',
        datatype: "json",
        height: 450,
        cellLayout:4,
        colNames:['主键','手机号','领取金币数','是否已经领取','创建时间'],
        colModel:[
            {name:'id',width:"10",index:'id',hidden:true,align:'center'},
            {name:'mobile',index:'mobile',align:'center',width:"40",editable: true},
            {name:'goldNum',index:'goldNum',width:'65',align:'center',editable:true},
            {name:'isCollect',index:'isCollect',align:'center',width:"40",editable: true,formatter:setIsCollect},
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
        caption: "赠送金币列表",
        autowidth: true

    });

    //设置状态
    function setIsCollect(cellvalue){
        if(cellvalue == 0){
            return "未领取";
        }else if(cellvalue == 1){
            return "已领取";
        }else {
            return "";
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
    var mobile = $("#mobile").val();
    var isCollect = $("#isCollect option:selected").val();
    var curpagenum = $(grid_selector).jqGrid('getGridParam', 'page');   //当前页码
    $(grid_selector).jqGrid("setGridParam",
        {url:"getSendGoldList?mobile="+mobile+"&isCollect="+isCollect, page:curpagenum, datatype:"json"}).trigger("reloadGrid");

}
//重新加载数据
function reloadData(){
    $(grid_selector).jqGrid("setGridParam",
        {url:"getSendGoldList?", page:1, datatype:"json"}).trigger("reloadGrid");

}


