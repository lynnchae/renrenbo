var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";

jQuery(function($) {



    $("#bootbox-regular").on(ace.click_event, function() {
        alert("asdasd");
        bootbox.prompt("What is your name?", function(result) {
            if (result === null) {
                //Example.show("Prompt dismissed");
            } else {
                //Example.show("Hi <b>"+result+"</b>");
            }
        });
    });

    $( "#id-btn-dialog1" ).on('click', function(e) {
        e.preventDefault();
        var dialog = $("#dialog-message").removeClass('hide').dialog({
            modal: true,
            title: "添加主播",
            title_html: true,
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
                        $(this).dialog("close");
                    }
                }
            ]
        });
    });




        jQuery(grid_selector).jqGrid({
        //direction: "rtl",
        url:'getAuthorList.do',
            cellEdit : true,
            cellsubmit : 'remote',
            cellurl : 'editPrizePercent',
        //data: grid_data,
        datatype: "json",
        height: 350,
        cellLayout:4,
        colNames:['主键','主播头像', '主播昵称','主播标签','密点数' ,'主播状态','主播年龄',""],
        colModel:[
            {name:'id',width:"10",index:'id',hidden:true,align:'center'},
            {name:'picUrl',index:'picUrl',width:'65',align:'center',editable:true, sorttype:"date",formatter: getPicUrl},
            {name:'authorName',index:'authorName',align:'center',width:"40",editable: true,editoptions:{size:"20",maxlength:"30"}},
            {name:'authorRemark',index:'authorRemark',align:'center',width:"40", editable: true},
            {name:'denseNum',index:'ship',align:'center',width:"50",editable:true,edittype:"select",editoptions:{value:""}},
            {name:'status',index:'note',align:'center',width:"40", formatter:setStatus},
            {name:'age',index:'note',align:'center',width:"40"},
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
        caption: "主播列表",
        autowidth: true
    });

    //设置主播头像
    function getPicUrl(cellvalue,options,cell){
       return "<img  style='border-radius:100%;border:2px solid #5293c4;max-width:50px; '  " +
           "src='"+ cellvalue+"'/>";
    }

    //设置主播的状态
    function setStatus(cellvalue){
        if(cellvalue == '0'){
            return "未审核";
        }

        if(cellvalue == 1){
            return "审核通过";
        }

        if(cellvalue == 2){
            return "审核失败";
        }
    }

    //设置操作按钮
    function setOperationBut(cellvalue,options,cell){
        var str = "<div class='visible-md visible-lg hidden-sm hidden-xs action-buttons'>" +
            "<a class='green' href='#' onclick=\"check("+ cell.id +")\"><i class='icon-pencil bigger-130'></i></a>" +
            "<a class='red' href='#' onclick=\"del("+ cell.id +")\"><i class='icon-trash bigger-130'></i></a>" +
            "</div>";
        return str;
    }

    //navButtons
    jQuery(grid_selector).jqGrid('navGrid',pager_selector,
        {
            //修改
            edit: true,
            editicon : 'icon-pencil blue',

            //新增
            add: true,
            addicon : 'icon-plus-sign purple',

            //删除
            del: true,
            delicon : 'icon-trash red',
            delfunc:deleteFunc,

            //搜索
            search: true,
            searchicon : 'icon-search orange',

            //刷新
            refresh: true,
            refreshicon : 'icon-refresh green',

            //查看
            view: true,
            viewicon : 'icon-zoom-in grey'

        }
    )


    //删除指定行
    function deleteFunc(){
        var ids= jQuery(grid_selector).jqGrid("getGridParam","selarrrow");
        if(ids == null){
            alert("请选择要删除的行");
        }else{
            alert(ids);
        }
    }













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

    //var selr = jQuery(grid_selector).jqGrid('getGridParam','selrow');


});


//审核按钮
function check(id){
    alert("当前审核的数据：" + id);
}


//当前删除的按钮
function del(id){
    alert("当删除的数据：" + id);
}


function search(){
    var status = $("#status").val();
    var anchorName = $("#anchorName").val();

    $(grid_selector).jqGrid("setGridParam",
        {url:"getAuthorList.do?status="+status+"&anchorName="+anchorName, page:1, datatype:"json"}).trigger("reloadGrid");

}
