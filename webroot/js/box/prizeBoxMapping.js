var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";
var grid_selector_add = "#grid-table-add";
var pager_selector_add = "#grid-pager-add";
var boxPrizeMapping = this;
var boxId = 0;
var boxCode = null;

$(function($) {
    boxPrizeMapping.boxId = $("#form-boxID").attr("boxID");
    boxCode = $("#form-boxID").attr("boxCode");
    //添加
    $("#add-btn").on('click', function(e) {
        e.preventDefault();
        boxPrizeMapping.addPrize();
    });

    jQuery(grid_selector).jqGrid({
        url: "getPrizeInBox?boxId="+boxId,
        datatype: "json",
        cellLayout:4,
        colNames: ['主键','名称','图片','百分比(%)','操作'],
        colModel: [
            {name:'id',width:"10",index:'id',hidden:true,align:'center'},
            //{name:"prizeCode",index:"prizeCode",width:300,align:'center'},
            {name:"prizeName",index:"prizeName",width:300,align:'center'},
            {name:"prizeIcon",index:"prizeIcon",width:400,align:"center",formatter:getPicUrlMapping},

            {name:"percent",index:"percent",width:200,align:"center",editable:true},
            {name:'myac',index:'',align:'center', width:"100", fixed:true, sortable:false, resize:false,formatter:setOperationButMapping},
        ],
        viewrecords : true,
        rowNum:30,
        //rowList:[10,20,30],
        pager : pager_selector,
        multiselect: true,
        multiboxonly: true,
        loadComplete : function() {
            var table = this;
            setTimeout(function(){
                updatePagerIconsMapping(table);
                enableTooltipsMapping(table);
            }, 0);

        },
        height: 350,
        caption: "奖品列表",
        autowidth: true,
        cellEdit : true,
        cellsubmit : 'remote',
        cellurl : 'editPrizePercent'
    });
    //navButtons，设置最下面操作栏按钮
    jQuery(grid_selector).jqGrid('navGrid',pager_selector,
        {
            //修改
            edit: false,
            editicon : 'icon-pencil blue',

            //新增
            add: false,
            addicon : 'icon-plus-sign purple',

            //删除
            del: true,
            delicon : 'icon-trash red',
            delfunc:deleteFuncMapping,

            //搜索
            search: false,
            searchicon : 'icon-search orange',

            //刷新
            refresh: true,
            refreshicon : 'icon-refresh green',

            //查看
            view: true,
            viewicon : 'icon-zoom-in grey'

        }
    );




});


//设置图片
function getPicUrlMapping(cellvalue,options,cell){
    return "<img  style='max-width:40px;height:40px;'  " +
        "src='"+ cellvalue+"'/>";
}

//设置操作按钮
function setOperationButMapping(cellvalue,options,cell){
    var str = "<div class='visible-md visible-lg hidden-sm hidden-xs action-buttons'>" +
        /*"<a class='green' href='#' title='修改' onclick=\"edit("+ cell.id +")\"><i class='icon-pencil bigger-130'></i></a>" +*/
        "<a class='red' href='#' title='删除' onclick=\"del("+ cell.id +")\"><i class='icon-trash bigger-130'></i></a>" +
        "</div>";
    return str;
}

//replace icons with FontAwesome icons like above
function updatePagerIconsMapping(table) {
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

function enableTooltipsMapping(table) {
    $('.navtable .ui-pg-button').tooltip({container:'body'});
    $(table).find('.ui-pg-div').tooltip({container:'body'});
}


//重新加载数据
function reloadDataMapping(){
    $(grid_selector).jqGrid("setGridParam",
        {url:"getPrizeInBox?boxId="+boxPrizeMapping.boxId, page:1, datatype:"json"}).trigger("reloadGrid");

}

//批量删除
function deleteFuncMapping(){
    var ids= jQuery(grid_selector).jqGrid("getGridParam","selarrrow");
    if(ids == null){
        alert("请选择要删除的行");
    }else{
        boxPrizeMapping.del(ids);
    }
}

//当前删除的按钮
function del(id) {
    //检查是否为新添加的
    $.ajax({
        url: "deletePrizeInBox?mappingIds="+id,// 跳转到 url
        type: 'get',
        cache: false,
        success: function (data) {
            if(data == 'true'){//删除成功
                //重新加载数据
                boxPrizeMapping.reloadDataMapping();
            }else if(data == 'false'){
                alert("删除失败");
            }else{
                alert("编码为"+data+"的箱子里还有奖品，不能删除");
            }
        }
    });
}

//按添加按钮
function addPrize(){
    jQuery(grid_selector_add).jqGrid({
        url: "getPrizeNotInBox?boxCode="+boxCode,
        datatype: "json",
        height: 250,
        colNames: ['主键','名称','图片'],
        colModel: [
            {name:'id',width:"10",index:'id',hidden:true,align:'center'},
            {name:"prizeName",index:"internalNo",width:250,align:'center'},
            {name:"prizeIcon",index:"name",width:350,align:"center",formatter:getPicUrlMapping}
        ],
        viewrecords : true,
        rowNum:10,
        rowList:[10,20,30],
        //pager : pager_selector,
        multiselect: true,
        multiboxonly: true,
        loadComplete : function() {
            var table = this;
            setTimeout(function(){
                updatePagerIconsMapping(table);
                enableTooltipsMapping(table);
            }, 0);

        },
        caption: "奖品列表",
        autowidth: true
    });
    //navButtons，设置最下面操作栏按钮
    jQuery(grid_selector_add).jqGrid('navGrid',pager_selector_add,
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
            //delfunc:deleteFuncMapping,

            //搜索
            search: false,
            searchicon : 'icon-search orange',

            //刷新
            refresh: false,
            refreshicon : 'icon-refresh green',

            //查看
            view: true,
            viewicon : 'icon-zoom-in grey'

        }
    );
    $(grid_selector_add).jqGrid("setGridParam",
        {url:"getPrizeNotInBox?boxCode="+boxCode, page:1, datatype:"json"}).trigger("reloadGrid");
    var dialog = $("#dialog-message-add").removeClass('hide').dialog({
        modal: true,
        title: "添加奖品到箱子",
        title_html: true,
        width:650,
        height:500,
            buttons: [
            {
                text: "Cancel",
                "class": "btn btn-xs",
                click: function () {
                    $(this).dialog("close");
                    $(grid_selector_add).clearGridData();
                }
            },
            {
                text: "OK",
                "class": "btn btn-primary btn-xs",
                click: function () {
                    //var ids= jQuery(grid_selector).jqGrid("getGridParam","selarrrow");
                    var dialog = this;
                    addPrizeToBox(dialog);
                }
            }
        ]
    });
}


// 给箱子中添加奖品
function addPrizeToBox(dialog){
    var ids= jQuery(grid_selector_add).jqGrid("getGridParam","selarrrow");
    $.ajax({
        url: "addPrize2Box?prizeIDs="+ids+"&boxCode="+boxCode,// 跳转到 url
        type: 'POST',
        cache: false,
        success: function (data) {
            if(data == 'true'){//删除成功
                $(dialog).dialog("close");
                $(grid_selector_add).clearGridData();
                //重新加载数据
                boxPrizeMapping.reloadDataMapping();
            }else if(data == 'false'){
                alert("添加失败");
            }
        }
    });
}

