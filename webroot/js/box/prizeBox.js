var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";
var box = this;

$(function($) {

    //添加
    $( "#add-btn" ).on('click', function(e) {
        e.preventDefault();
        $(".name_message").html("");
        //$(".code_message").html("");
        $(".img_message").html("");
        $(".dense_message").html("");
        var dialog = $("#dialog-message-add").removeClass('hide').dialog({
            modal: true,
            title: "添加宝箱",
            title_html: true,
            width:500,
            buttons: [
                {
                    text: "Cancel",
                    "class": "btn btn-xs",
                    click: function () {
                        $(this).dialog("close");
                    }
                },
                {
                    text: "OK",
                    "class": "btn btn-primary btn-xs",
                    click: function () {
                        var url = "addBoxInfo";
                        var dialog = this;
                        box.editOrAdd(0,2,url,dialog);
                    }
                }
            ]
        });
    });

    //第一次加载数据
    jQuery(grid_selector).jqGrid({
        url:'getBoxList',
        datatype: "json",
        height: 350,
        cellLayout:4,
        colNames:['主键','名称','图片', '价格（金币）','创建时间',"操作"],
        colModel:[
            {name:'id',width:"10",index:'id',hidden:true,align:'center'},
            //{name:'boxCode',index:'boxCode',align:'center',width:"50"},
            {name:'boxName',index:'boxName',align:'center',width:"40",editable: true},
            {name:'boxImg',index:'boxImg',width:'65',align:'center',editable:true,formatter: getPicUrl,editType:"image"},
            {name:'denseNum',index:'denseNum',align:'center',width:"40",editable: true,editoptions:{size:"20",maxlength:"30"}},
            {name:'createTime',index:'createTime',align:'center',width:"40"},
            {name:'myac',index:'',align:'center', width:"100", fixed:true, sortable:false, resize:false,formatter:setOperationBut},
        ],
        viewrecords : true,
        rowNum:10,
        rowList:[10,20,30],
        pager : pager_selector,
        multiselect: true,
        multiboxonly: true,
        loadComplete : function() {
            var table = this;
            setTimeout(function(){
                updatePagerIcons(table);
                enableTooltips(table);
            }, 0);

        },
        caption: "宝箱列表",
        autowidth: true,
        subGrid: true,  // (1)开启子表格支持
        subGridRowExpanded:function(subgrid_id, row_id){
        //子表格容器的id和需要展开子表格的行id，将传入此事件函数
        var subgrid_table_id;
        subgrid_table_id = subgrid_id + "_t";//根据subgrid_id定义对应的子表格的table的id
        var subgrid_pager_id;
        subgrid_pager_id = subgrid_id + "_pgr"//根据subgrid_id定义对应的子表格的pager的id
        $("#" + subgrid_id).html("<table id='"+subgrid_table_id+"'class='scroll'></table><div id='"+subgrid_pager_id+"'class='scroll'></div>");
        //创建jqGrid对象
        $("#" + subgrid_table_id).jqGrid({
            url: "getPrizeInBox?boxId="+row_id,
            datatype: "json",
            colNames: ['主键','名称','图片','价格（金币）','百分比(%)'],
            colModel: [
                {name:'id',width:"10",index:'id',hidden:true,align:'center'},
                //{name:"prizeCode",index:"id",width:300,align:'center'},
                {name:"prizeName",index:"prizeName",width:300,align:'center'},
                {name:"prizeIcon",index:"prizeIcon",width:400,align:"center",formatter:getPicUrl},
                {name:"prizeDenseNum",index:"prizeDenseNum",width:300,align:'center'},
                {name:"percent",index:"percent",width:200,align:"center"}
            ],
            prmNames: {search: "search"},
            //pager: subgrid_pager_id,
            viewrecords: true,
            height: "100%"
        });
    }
    });


    //设置图片
    function getPicUrl(cellvalue,options,cell){
       return "<img  style='max-width:40px;height:40px; '  " +
           "src='"+ cellvalue+"'/>";
    }

    //设置操作按钮
    function setOperationBut(cellvalue,options,cell){
        var str = "<div class='visible-md visible-lg hidden-sm hidden-xs action-buttons'>" +
            "<a class='green' href='#' title='修改箱子' onclick=\"edit("+ cell.id +")\"><i class='icon-pencil bigger-130'></i></a>" +
            "<a class='red' href='#' title='删除' onclick=\"del("+ cell.id +")\"><i class='icon-trash bigger-130'></i></a>" +
            "<a class='btn btn-xs btn-info' href=\"prizeBoxMappingIndex?boxId="+ cell.id +"\" target='includePage' title='修改礼物'><i class='icon-edit bigger-120'></i></a>" +
            "</div>";
        return str;
    }

    //navButtons，设置最下面操作栏按钮
    jQuery(grid_selector).jqGrid('navGrid',pager_selector,
        {
            //修改
            edit: false,
            editicon : 'icon-pencil blue',

            //新增
            add: true,
            addicon : 'icon-plus-sign purple',
            addtitle:'新增箱子',
            addfunc:addBox,

            //删除
            del: true,
            delicon : 'icon-trash red',
            delfunc:deleteFunc,

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

    //判断编码是否已经存在
    /*$("#add_boxCode").blur(function(e) {
        judgeCode();
    });*/

});

//新增
function addBox(){
    //清楚提示信息
    $(".name_message").html("");
    //$(".code_message").html("");
    $(".img_message").html("");
    $(".dense_message").html("");
    //清空之前输入框的值
    $("#add_name").val("");
    $("#addFile").val("");
    $("#add_denseNum").val("");
    //弹出模态框
    var dialog = $("#dialog-message-add").removeClass('hide').dialog({
        modal: true,
        title: "添加宝箱",
        title_html: true,
        width:500,
        buttons: [
            {
                text: "Cancel",
                "class": "btn btn-xs",
                click: function () {
                    $(this).dialog("close");
                }
            },
            {
                text: "OK",
                "class": "btn btn-primary btn-xs",
                click: function () {
                    var url = "addBoxInfo";
                    var dialog = this;
                    box.editOrAdd(0,2,url,dialog);
                }
            }
        ]
    });
}
//修改按钮
function edit(id){
    var model = $(grid_selector).jqGrid('getRowData',id);
    $("#dialog_name").val(model.boxName);
    var icon = model.boxImg;
    console.log(icon);
    var src = icon.substring(icon.lastIndexOf("=")+2,icon.length-2);
    $("#dialog_pic").attr("src",src);
    var typeStr = model.type;
    $("#dialog_denseNum").val(model.denseNum);
    //$("#dialog_boxCode").html(model.boxCode);
    $("#dialog_createTime").val(model.createTime);
    var dialog = $("#dialog-message").removeClass('hide').dialog({
        modal: true,
        title: "修改箱子信息",
        title_html: true,
        width:500,
        buttons: [
            {
                text: "Cancel",
                "class": "btn btn-xs",
                click: function () {
                    $(this).dialog("close");
                }
            },
            {
                text: "OK",
                "class": "btn btn-primary btn-xs",
                click: function () {
                    var url = "editBoxInfo";
                    var dialog = this;
                    box.editOrAdd(id,1,url,dialog);
                }
            }
        ]
    });
}

//判断编码
function judgeCode(){
    var boxCode = $("#add_boxCode").val();
    if(boxCode != ""){
        $.ajax({
            url:"judgeBoxCodeIsExist",
            type: "get",
            data: {boxCode:boxCode},
            dataType: "json",
            success: function(data){
                if(data){
                    //alert("该编码已使用");
                    $("#add_boxCode").val("");
                    $(".code_message").html("该编码已经使用");
                }else{
                    $(".code_message").html("");
                }
            }
        });
    }
}
//修改或添加操作
/**
 *id 操作的ID，添加时传入0
 * opeType，操作的类型，1表示修改，2表示添加
 * url，请求的路径
 * dialog，弹框的对象
 **/
function editOrAdd(id,opeType,url,dialog){
    var params = box.validate(id,opeType);
    if(params != null){
        var fileElementId = "editFile";
        if(opeType != 1){
            fileElementId = "addFile"
        }
        ajaxFileUpload(url,params,fileElementId,dialog,opeType,box);
    }
}
//验证表单信息
function validate(id,opeType){
    var createTime = "";
    var icon = "";
    if(opeType == 1){
        var name = $("#dialog_name").val();
        var denseNum = $("#dialog_denseNum").val();
        //var boxCode = $("#dialog_boxCode").html();
        createTime = $("#dialog_createTime").val();
        icon = $("#dialog_pic").attr('src');
    }else{
        var name = $("#add_name").val();
        var denseNum = $("#add_denseNum").val();
        //var boxCode = $("#add_boxCode").val();
        var pic = $("#addFile").val();
        /*if(boxCode == ""){
            $(".code_message").html("请输入编码");
            return;
        }else{
            $(".code_message").html("");
        }*/
        if(pic == ""){
            $(".img_message").html("请选择图片");
            return;
        }else{
            //验证是否为图片
            var isPic = isPicture(pic);
            if(!isPic){
                $(".img_message").html("文件类型不合法,只能是 jpg、gif、bmp、png、jpeg 类型！");
                return;
            }else{
                $(".img_message").html("");
            }
        }
    }

    if(name == ""){
        $(".name_message").html("箱子名不能为空");
        return;
    }else{
        $(".name_message").html("");
    }
    if(denseNum == ""){
        $(".dense_message").html("请输入价格");
        return;
    }else{
        $(".dense_message").html("");
    }

    var params={
            boxID:id,
            boxName:name,
            money:denseNum,
            createTime:createTime,
            icon:icon
    }
    return params;
}


function search(){
    var name = $("#name").val();
    var curpagenum = $(grid_selector).jqGrid('getGridParam', 'page');   //当前页码
    $(grid_selector).jqGrid("setGridParam",
        {url:"getBoxList?name="+name, page:curpagenum, datatype:"json"}).trigger("reloadGrid");

}
//重新加载数据
function reloadData(){
    //$("#add_boxCode").val("");
    $("#add_name").val("");
    $("#add_denseNum").val("");
    $(grid_selector).jqGrid("setGridParam",
        {url:"getBoxList?", page:1, datatype:"json"}).trigger("reloadGrid");

}
//批量删除事件
function deleteFunc(){
    var ids= jQuery(grid_selector).jqGrid("getGridParam","selarrrow");
    if(ids == null){
        alert("请选择要删除的行");
    }else{
        box.del(ids);
    }
}
//当前删除的按钮
function del(id) {
    $.ajax({
        url: "deleteBox?boxIDs="+id,// 跳转到 url
        type: 'get',
        cache: false,
        success: function (data) {
          if(data == 'true'){//删除成功
              //重新加载数据
              box.search();
          }else if(data == 'false'){
              alert("删除失败");
          }else{
              alert("编码为"+data+"的箱子里还有奖品，不能删除");
          }
        }
    });
}


