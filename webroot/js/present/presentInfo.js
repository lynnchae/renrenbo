var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";
var present = this;

$(function($) {
        jQuery(grid_selector).jqGrid({
        //direction: "rtl",
        url:'getPresentInfoList',
        //data: grid_data,
        datatype: "json",
        height: 550,
        cellLayout:4,
        colNames:['主键','商品名称','图片','类型', '价格（金币）','钻','人气值','领取频率' ,'呈现效果方式','创建时间',"操作"],
        colModel:[
            {name:'id',width:"10",index:'id',hidden:true,align:'center'},
            {name:'name',index:'name',align:'center',width:"40",editable: true},
            {name:'icon',index:'icon',width:'65',align:'center',editable:true,formatter: getPicUrl,editType:"image",editoptions:{src:getPicUrl}},
            {name:'type',index:'type',align:'center',width:"40",editable: true, formatter:setType,edittype:"select",editoptions: {value:'0:免费领取;1:花钱购买'}},
            {name:'denseNum',index:'denseNum',align:'center',width:"40",editable: true,editoptions:{size:"20",maxlength:"30"}},
            {name:'diamond',index:'diamond',align:'center',width:"40",editable: true,editoptions:{size:"20",maxlength:"30"}},
            {name:'popularityVal',index:'popularityVal',align:'center',width:"40", editable: true},
            {name:'frequencyNum',index:'frequencyNum',align:'center',width:"50",editable: true},
            {name:'appearType',index:'appearType',align:'center',width:"40",editable: true,formatter:setAppearType,edittype:"select"},
            {name:'createTime',index:'createTime',align:'center',width:"40"},
            {name:'myac',index:'',align:'center', width:"100", fixed:true, sortable:false, resize:false,formatter:setOperationBut},
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
        caption: "虚拟礼物列表",
        autowidth: true

    });

    //设置主播头像
    function getPicUrl(cellvalue,options,cell){
       return "<img  style='max-width:40px;height:40px; '  " +
           "src='"+ cellvalue+"'/>";
    }

    //设置呈现方式
    function setAppearType(cellvalue){
        if(cellvalue == 0){
            return "没有效果";
        }
        if(cellvalue == 1){
            return "放大";
        }
        if(cellvalue == 2){
            return "平移跳动";
        }
        if(cellvalue == 3){
            return "旋转";
        }
        if(cellvalue == 4){
            return "翻转";
        }
        if(cellvalue == 5){
            return "曲线运动";
        }
    }

    //设置状态
    function setType(cellvalue){
        if(cellvalue == 0){
            return "免费领取";
        }

        if(cellvalue == 1){
            return "花钱购买";
        }
    }

    //设置操作按钮
    function setOperationBut(cellvalue,options,cell){
        var str = "<div class='visible-md visible-lg hidden-sm hidden-xs action-buttons'>" +
            "<a class='green' href='#' onclick=\"editPresentInfo("+ cell.id +")\"><i class='icon-pencil bigger-130'></i></a>" +
            "<a class='red' href='#' onclick=\"del("+ cell.id +")\"><i class='icon-trash bigger-130'></i></a>" +
            "</div>";
        return str;
    }

    //navButtons
    jQuery(grid_selector).jqGrid('navGrid',pager_selector,
        {
            //新增
            add: true,
            addicon : 'icon-plus-sign purple',
            addtitle:'新增礼物',
            addfunc:addPresent,
            //修改
            edit: false,
            editicon : 'icon-pencil blue',
            //删除
            del: true,
            delicon : 'icon-trash red',
            delfunc:deleteFunc,
            deltitle:'删除',
            //搜索
            search: false,
            searchicon : 'icon-search orange',
            //刷新
            refresh: true,
            refreshicon : 'icon-refresh green',
            refreshtitle:'刷新',

            //查看
            view: false,
            viewicon : 'icon-zoom-in grey',

            alertcap:'警告',
            alerttext:'请选择指定的行记录'
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

    //添加时触发类型选择的事件
    $("#dialog_type").change(function(){
        var type = $("#dialog_type  option:selected").val();
        setShowView(type);
    });
    //修改时触发类型选择的事件
    $("#add_type").change(function(){
        var type = $("#add_type  option:selected").val();
        setShowView(type);
    });

});

function setShowView(type){
    if(type == 0){//免费领取
        $(".denseNum").hide();
        $(".diamond").hide();
        $(".popularityVal_span").hide();
        $(".frequencyNum_span").hide();
        $(".denseNum_span").show();
        $(".diamond_span").show();
        $(".popularityVal").show();
        $(".frequencyNum").show();
    }else{//购买
        $(".denseNum_span").hide();
        $(".diamond_span").hide();
        $(".popularityVal").hide();
        $(".frequencyNum").hide();
        $(".denseNum").show();
        $(".diamond").show();
        $(".popularityVal_span").show();
        $(".frequencyNum_span").show();
    }
}
/***新增礼物*/
function addPresent(){
    var type = $("#add_type  option:selected").val();
    setShowView(type);
    //清空提示的值
    $(".name_message").html("");
    $(".dense_message").html("");
    $(".diamond_message").html("");
    $(".img_message").html("");
    $(".popularityVal_message").html("");
    $(".frequencyNum_message").html("");
    //清空输入框的值
    $("#add_name").val("");
    $("#add_denseNum").val("");
    $("#add_diamond").val("");
    $("#add_popularityVal").val("");
    $("#add_frequencyNum").val("");
    //展示模态框
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
                    var url = "addPresentInfo";
                    var dialog = this;
                    present.editOrAdd(0,2,url,dialog);
                }
            }
        ]
    });
}

//修改按钮
function editPresentInfo(id){
    //获取对象信息，填充模式框的值
    var model = $(grid_selector).jqGrid('getRowData',id);
    $("#dialog_name").val(model.name);
    $("#dialog_createTime").val(model.createTime);
    //$("#dialog_icon").html(model.icon);
    var icon = model.icon;
    var src = icon.substring(icon.lastIndexOf("=")+2,icon.length-2);
    $("#dialog_pic").attr("src",src);
    var typeStr = model.type;
    $("#dialog_type  option[value='"+getType(typeStr)+"'] ").attr("selected",true);
    $("#dialog_denseNum").val(model.denseNum);
    $("#dialog_diamond").val(model.diamond);
    $("#dialog_popularityVal").val(model.popularityVal);
    $("#dialog_frequencyNum").val(model.frequencyNum);
    var appearTypeStr = model.appearType;
    $("#dialog_appearType  option[value='"+getAppearType(appearTypeStr)+"'] ").attr("selected",true);
    //根据状态设置金额、人气值、频率的显示问题
    var type = $("#dialog_type  option:selected").val();
    setShowView(type);
    //清空提示的值
    $(".name_message").html("");
    $(".dense_message").html("");
    $(".diamond_message").html("");
    $(".img_message").html("");
    $(".popularityVal_message").html("");
    $(".frequencyNum_message").html("");
    var dialog = $("#dialog-message").removeClass('hide').dialog({
        modal: true,
        title: "修改礼物信息",
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
                    var url = "editPresentInfo";
                    var dialog = this;
                    present.editOrAdd(id,1,url,dialog);
                }
            }
        ]
    });
}
//修改或添加操作
function editOrAdd(id,opeType,url,dialog){
    var params = present.validate(id,opeType);
    if(params != null){
        var fileElementId = "editFile";
        if(opeType != 1){
            fileElementId = "addFile"
        }
        ajaxFileUpload(url,params,fileElementId,dialog,opeType,present);
    }
}
//验证表单信息
function validate(id,opeType){
    var createTime = "";
    var icon = "";
    if(opeType == 1){
        var name = $("#dialog_name").val();
        var type = $("#dialog_type  option:selected").val();
        var denseNum = $("#dialog_denseNum").val();
        var diamond = $("#dialog_diamond").val();
        var popularityVal = $("#dialog_popularityVal").val();
        var frequencyNum = $("#dialog_frequencyNum").val();
        var appearType = $("#dialog_appearType  option:selected").val();
        createTime = $("#dialog_createTime").val();
        icon = $("#dialog_pic").attr('src');
    }else{
        var name = $("#add_name").val();
        var type = $("#add_type  option:selected").val();
        var denseNum = $("#add_denseNum").val();
        var diamond = $("#add_diamond").val();
        var popularityVal = $("#add_popularityVal").val();
        var frequencyNum = $("#add_frequencyNum").val();
        var appearType = $("#add_appearType  option:selected").val();
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

    }

    if(name == ""){
        $(".name_message").html("礼物名不能为空");
        return;
    }
    if(denseNum == ""){
        denseNum = 0;
    }
    if(diamond == ""){
        diamond = 0;
    }
    if(popularityVal == ""){
        popularityVal = 0;
    }
    if(frequencyNum == ""){
        frequencyNum = 0;
    }
    if(type == 0){//免费领取
        /*if(parseInt(denseNum) > 0){
            $(".dense_message").html("该礼物是免费领取的");
            return;
        }*/
        denseNum = 0;
        diamond = 0;
        if(popularityVal==""){
            $(".popularityVal_message").html("免费领取时，人气值必填");
            return;
        }
        if(frequencyNum==""){
            $(".frequencyNum_message").html("免费领取时，领取频率必填");
            return;
        }
    }else if(type == 1){//花钱购买
        /*if(parseInt(popularityVal) > 0 ){
            $(".popularityVal_message").html("花钱购买时，人气值为0");
            return;
        }
        if(parseInt(frequencyNum) > 0){
            $(".frequencyNum_message").html("花钱购买时，领取频率为0");
            return;
        }*/
        popularityVal = 0;
        frequencyNum = 0;
        if(denseNum == ""){
            $(".dense_message").html("花钱购买时，请填写金币数");
            return;
        }
    }else{
        $(".type_message").html("请输入礼物类型");
        return;
    }
    var params={
            presentID:id,
            presentName:name,
            type:type,
            denseNum:denseNum,
            popularityVal:popularityVal,
            frequencyNum:frequencyNum,
            appearType:appearType,
            diamond:diamond,
            createTime:createTime,
            icon:icon
    }
    return params;
}
//获取状态
function getType(value){
    if(value == "免费领取"){
        return 0;
    }else if(value == "花钱购买"){
        return 1;
    }
}

//设置呈现方式
function getAppearType(value){
    if(value == "没有效果"){
        return 0;
    }
    if(value == "放大"){
        return 1;
    }
    if(value == "平移跳动"){
        return 2;
    }
    if(value == "旋转"){
        return 3;
    }
    if(value == "翻转"){
        return 4;
    }
    if(value == "曲线运动"){
        return 5;
    }
}
function search(){
    var type = $("#type option:selected").val();
    var name = $("#name").val();
    var appearType = $("#appearType option:selected").val();
    var curpagenum = $(grid_selector).jqGrid('getGridParam', 'page');   //当前页码
    $(grid_selector).jqGrid("setGridParam",
        {url:"getPresentInfoList?type="+type+"&name="+name+"&appearType="+appearType, page:curpagenum, datatype:"json"}).trigger("reloadGrid");

}
//重新加载数据
function reloadData(){
    $(grid_selector).jqGrid("setGridParam",
        {url:"getPresentInfoList?", page:1, datatype:"json"}).trigger("reloadGrid");

}
//批量删除事件
function deleteFunc(){
    var ids= jQuery(grid_selector).jqGrid("getGridParam","selarrrow");
    if(ids == null){
        alert("请选择要删除的行");
    }else{
        //del(ids);
        present.del(ids);
    }
}
//当前删除的按钮
function del(id) {
    $.ajax({
        url: "deletePresentInfo?presentIDs="+id,// 跳转到 url
        type: 'get',
        cache: false,
        success: function (data) {
          if(data == 'true'){
              //重新加载数据
              present.search();
          }else{
              alert("删除失败");
          }
        }
    });
}

