var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";
var rechargeOrder = this;

$(function($) {

        jQuery(grid_selector).jqGrid({
        url:'getRewardRecoard',
        datatype: "json",
        height: 450,
        cellLayout:4,
        colNames:['主键','主播昵称','主播真实姓名','听众昵称', '礼物','金币数','打赏时间'],
        colModel:[
            {name:'id',width:"10",index:'id',hidden:true,align:'center'},
            {name:'anchorNickName',index:'anchorNickName',align:'center',width:"40",editable: true},
            {name:'anchorRealName',index:'anchorRealName',width:'65',align:'center',editable:true},
            {name:'listenerName',index:'listenerName',align:'center',width:"40",editable: true},
            {name:'presentName',index:'presentName',align:'center',width:"40",editable: true,editoptions:{size:"20",maxlength:"30"}},
            {name:'denseNum',index:'denseNum',align:'center',width:"40", editable: true},
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
        caption: "打赏记录",
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
});

function search(){
    //var type = $("#type option:selected").val();
    var listenerName = $("#listenerName").val();
    var anchorName = $("#anchorName").val();
    var presentName = $("#presentName").val();
    var curpagenum = $(grid_selector).jqGrid('getGridParam', 'page');   //当前页码
    $(grid_selector).jqGrid("setGridParam",
        {url:"getRewardRecoard?listenerName="+listenerName+"&anchorName="+anchorName+"&presentName="+presentName, page:curpagenum, datatype:"json"}).trigger("reloadGrid");

}
//重新加载数据
function reloadData(){
    $(grid_selector).jqGrid("setGridParam",
        {url:"getRewardRecoard?", page:1, datatype:"json"}).trigger("reloadGrid");

}


