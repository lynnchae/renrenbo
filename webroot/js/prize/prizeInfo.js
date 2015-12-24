var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";
var prize = this;

$(function($) {


    //判断编码是否已经存在
    /*$("#add_prizeCode" ).blur(function(e) {
        judgeCode();
    });*/

    //添加
    $( "#add-btn" ).on('click', function(e) {
        e.preventDefault();
        $(".name_message").html("");
        $(".code_message").html("");
        $(".img_message").html("");
        $(".description_message").html("");
        $(".collectWealthVal_message").html("");
        $(".giveWealthVal_message").html("");
        $(".dense_message").html("");
        var dialog = $("#dialog-message-add").removeClass('hide').dialog({
            modal: true,
            title: "添加礼物",
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
                        var url = "addPrizeInfo";
                        var dialog = this;
                        prize.editOrAdd(0,2,url,dialog);
                    }
                }
            ]
        });
    });

    //第一次加载数据
    jQuery(grid_selector).jqGrid({
        url:'getPrizeInfoList',
        datatype: "json",
        height: 450,
        cellLayout:4,
        colNames:['主键','商品名称','图片','描述','财富值（自己领取）','财富值（送主播）','创建时间',"操作"],
        colModel:[
            {name:'id',width:"10",index:'id',hidden:true,align:'center'},
            //{name:'prizeCode',index:'prizeCode',align:'center',width:"50"},
            {name:'name',index:'name',align:'center',width:"60",editable: true},
            {name:'icon',index:'icon',width:'45',align:'center',editable:true,formatter: getPicUrl,editType:"image"},
            {name:'description',index:'description',align:'center',width:"140", editable: true},
            {name:'collectWealthVal',index:'popularityVal',align:'center',width:"40", editable: true},
            {name:'giveWealthVal',index:'frequencyNum',align:'center',width:"50",editable: true},
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
        caption: "实体礼物列表",
        autowidth: true

    });

    //设置图片
    function getPicUrl(cellvalue,options,cell){
       return "<img  style='max-width:40px;height:40px; '  " +
           "src='"+ cellvalue+"'/>";
    }

    //设置操作按钮
    function setOperationBut(cellvalue,options,cell){
        var str = "<div class='visible-md visible-lg hidden-sm hidden-xs action-buttons'>" +
            "<a class='green' href='#' onclick=\"edit("+ cell.id +")\"><i class='icon-pencil bigger-130'></i></a>" +
            "<a class='red' href='#' onclick=\"del("+ cell.id +")\"><i class='icon-trash bigger-130'></i></a>" +
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
            addtitle:'新增礼物',
            addfunc:addPrize,

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

});

//新增
function addPrize(){
    $(".name_message").html("");
    //$(".code_message").html("");
    $(".img_message").html("");
    $(".description_message").html("");
    $(".collectWealthVal_message").html("");
    $(".giveWealthVal_message").html("");
    $(".dense_message").html("");
    //清空输入框之前的值
    $("#add_name").val("");
    $("#addFile").val("");
    $("#add_description").val("");
    $("#add_collectWealthVal").val("");
    $("#add_giveWealthVal").val("");
    //弹出模态框
    var dialog = $("#dialog-message-add").removeClass('hide').dialog({
        modal: true,
        title: "添加礼物",
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
                    var url = "addPrizeInfo";
                    var dialog = this;
                    prize.editOrAdd(0,2,url,dialog);
                }
            }
        ]
    });
}
//修改按钮
function edit(id){
    var model = $(grid_selector).jqGrid('getRowData',id);
    $("#dialog_name").val(model.name);
    var icon = model.icon;
    var src = icon.substring(icon.lastIndexOf("=")+2,icon.length-2);
    $("#dialog_pic").attr("src",src);
    var typeStr = model.type;
    $("#dialog_description").val(model.description);
    $("#dialog_collectWealthVal").val(model.collectWealthVal);
    $("#dialog_giveWealthVal").val(model.giveWealthVal);
    //$("#dialog_prizeCode").html(model.prizeCode);
    $("#dialog_createTime").val(model.createTime);
    var dialog = $("#dialog-message").removeClass('hide').dialog({
        modal: true,
        title: "修改奖品信息",
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
                    var url = "editPrizeInfo";
                    var dialog = this;
                    prize.editOrAdd(id,1,url,dialog);
                }
            }
        ]
    });
}

//判断编码
function judgeCode(){
    $.ajax({
        url:"judgeCodeIsExist",
        type: "get",
        data: {prizeCode:$("#add_prizeCode").val()},
        dataType: "json",
        success: function(data){
            if(data){
                $(".code_message").html("该编码已使用");
                $("#add_prizeCode").val("");
            }else{
                $(".code_message").html("");
            }
        }
    });
}
//修改或添加操作
/**
 *id 操作的ID，添加时传入0
 * opeType，操作的类型，1表示修改，2表示添加
 * url，请求的路径
 * dialog，弹框的对象
 **/
function editOrAdd(id,opeType,url,dialog){
    var params = prize.validate(id,opeType);
    if(params != null){
        var fileElementId = "editFile";
        if(opeType != 1){
            fileElementId = "addFile"
        }
        ajaxFileUpload(url,params,fileElementId,dialog,opeType,prize);
    }
}
//验证表单信息
function validate(id,opeType){
    var createTime = "";
    var icon = "";
    if(opeType == 1){//修改时
        var name = $("#dialog_name").val();
        var description = $("#dialog_description").val();
        var collectWealthVal = $("#dialog_collectWealthVal").val();
        var giveWealthVal = $("#dialog_giveWealthVal").val();
        //var prizeCode = $("#dialog_prizeCode").html();
        createTime = $("#dialog_createTime").val();
        icon = $("#dialog_pic").attr('src');
    }else{//添加时
        var name = $("#add_name").val();
        var description = $("#add_description").val();
        var collectWealthVal = $("#add_collectWealthVal").val();
        var giveWealthVal = $("#add_giveWealthVal").val();
        //var prizeCode = $("#add_prizeCode").val();
        var pic = $("#addFile").val();
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
        /*if(prizeCode == ""){
            $(".code_message").html("请输入奖品编码");
            return;
        }else{
            $(".code_message").html("");
        }*/
    }

    if(name == ""){
        $(".name_message").html("商品名不能为空");
        return;
    }else{
        $(".name_message").html("");
    }
    if(collectWealthVal == ""){
        $(".collectWealthVal_message").html("请输入自己领取的财富值");
        return;
    }else{
        $(".collectWealthVal_message").html("");
    }
    if(giveWealthVal == ""){
        $(".giveWealthVal_message").html("请输入送主播的财富值");
        return;
    }else{
        $(".giveWealthVal_message").html("");
    }
    if(description == ""){
        $(".description_message").html("请输入描述");
        return;
    }else{
        $(".description_message").html("");
    }
    var params={
            prizeID:id,
            prizeName:name,
            description:description,
            collectWealthVal:collectWealthVal,
            giveWealthVal:giveWealthVal,
            createTime:createTime,
            icon:icon
    }
    return params;
}


function search(){
    var name = $("#name").val();
    var curpagenum = $(grid_selector).jqGrid('getGridParam', 'page');   //当前页码
    $(grid_selector).jqGrid("setGridParam",
        {url:"getPrizeInfoList?name="+name, page:curpagenum, datatype:"json"}).trigger("reloadGrid");

}
//重新加载数据
function reloadData(){
    $(grid_selector).jqGrid("setGridParam",
        {url:"getPrizeInfoList?", page:1, datatype:"json"}).trigger("reloadGrid");

}
//批量删除事件
function deleteFunc(){
    var ids= jQuery(grid_selector).jqGrid("getGridParam","selarrrow");
    if(ids == null){
        alert("请选择要删除的行");
    }else{
        prize.del(ids);
    }
}
//当前删除的按钮
function del(id) {
    $.ajax({
        url: "deletePrizeInfo?prizeIDs="+id,// 跳转到 url
        type: 'get',
        cache: false,
        success: function (data) {
            if(data == 'true'){//删除成功
                //重新加载数据
                prize.search();
            }else if(data == 'false'){
                alert("删除失败");
            }else{
                alert("编码为"+data+"的奖品还在宝箱中，不能删除");
            }
        }
    });
}


