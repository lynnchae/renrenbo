//单个主播数据统计
var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";

var grid_table_present = "#grid-table-present";
var grid_pager_present = "#grid-pager-present";

var grid_table_ranking = "#grid-table-ranking";
var grid_pager_ranking = "#grid-pager-ranking";

var singAnchor = this;

$(function($) {

    //第一次加载数据
    jQuery(grid_selector).jqGrid({
        url: 'getAnchorSingleList',
        datatype: "json",
        height: 450,
        cellLayout:4,
        colNames: ['主键','accountID','排名','主播名','身份证号','手机号','粉丝数','人气数','总财富值','发送消息','转发消息', '回复','粉丝留存', '覆盖人次', '播出时长','财富贡献值排名','收到礼物'],//'粉丝活跃度',
        colModel: [
            {name: 'id', width: "10", index: 'id', hidden: true, align: 'center'},
            {name: 'accountID', width: "10", index: 'accountID', hidden: true, align: 'center'},
            {name: 'ranking', index: 'ranking', align: 'center', width: "10"},
            {name: 'anchorName', index: 'anchorName', align: 'center', width: "10"},
            {name: 'idCard', index: 'idCard', align: 'center', width: "20"},
            {name: 'mobile', index: 'mobile', align: 'center', width: "10"},
            {name: 'fanNum', index: 'fanNum', align: 'center', width: "10"},
            {name: 'popNum', index: 'popNum', align: 'center', width: "10"},
            {name: 'totalData', index: 'totalData', align: 'center', width: "10"},
            {name: 'sendMessageNum', index: 'sendMessageNum', align: 'center', width: "10"},
            {name: 'forwardMessageNum', index: 'forwardMessageNum', align: 'center', width: "10"},
            {name: 'replyNum', index: 'replyNum', width: '10', align: 'center'},
            //{name: 'fanActivity', index: 'fanActivity', width: '10', align: 'center'},
            {name: 'fansRetention', index: 'fansRetention', width: '10', align: 'center'},
            {name: 'coverPerson', index: 'coverPerson', width: '10', align: 'center'},
            {name: 'broadcastTime', index: 'broadcastTime', align: 'center', width: "10"},
            {
                name: 'wealthRanking',
                index: 'wealthRanking',
                align: 'center',
                width: "15",
                //fixed: true,
                sortable: false,
                resize: false,
                formatter: setWealthRankingBut
            },
            {
                name: 'receivedGift',
                index: 'receivedGift',
                align: 'center',
                width: "15",
                //fixed: true,
                sortable: false,
                resize: false,
                formatter: setReceivedGiftBut
            }
        ],
        viewrecords: true,
        rowNum: 10,
        rowList: [10, 20, 30],
        pager: pager_selector,
        multiselect: true,
        multiboxonly: true,

        loadComplete : function() {
            var table = this;
            setTimeout(function(){
                updatePagerIcons(table);
                enableTooltips(table);
            }, 0);

        },
        caption: "单个主播数据统计列表",
        autowidth: true,

    });

    //navButtons
    jQuery(grid_selector).jqGrid('navGrid',pager_selector,
        { 	//navbar options
            edit: false,
            editicon : 'icon-pencil blue',//修改
            add: false,
            addicon : 'icon-plus-sign purple',//新增
            del: false,
            delicon : 'icon-trash red',//删除
            search: false,
            searchicon : 'icon-search orange',//搜索
            refresh: true,
            refreshicon : 'icon-refresh green',//刷新
            view: true,
            viewicon : 'icon-zoom-in grey',//查看
        },
        {
            //edit record form
            //closeAfterEdit: true,
            recreateForm: true,
            beforeShowForm : function(e) {
                var form = $(e[0]);
                form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
                style_edit_form(form);
            }
        },
        {
            //new record form
            closeAfterAdd: true,
            recreateForm: true,
            viewPagerButtons: false,
            beforeShowForm : function(e) {
                var form = $(e[0]);
                form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
                style_edit_form(form);
            }
        },
        {
            //delete record form
            recreateForm: true,
            beforeShowForm : function(e) {
                var form = $(e[0]);
                if(form.data('styled')) return false;

                form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
                style_delete_form(form);

                form.data('styled', true);
            },
            onClick : function(e) {
                alert(1);
            }
        },
        {
            //search form
            recreateForm: true,
            afterShowSearch: function(e){
                var form = $(e[0]);
                form.closest('.ui-jqdialog').find('.ui-jqdialog-title').wrap('<div class="widget-header" />')
                style_search_form(form);
            },
            afterRedraw: function(){
                style_search_filters($(this));
            }
            ,
            multipleSearch: true,
            /**
             multipleGroup:true,
             showQuery: true
             */
        },
        {
            //view record form
            recreateForm: true,
            beforeShowForm: function(e){
                var form = $(e[0]);
                form.closest('.ui-jqdialog').find('.ui-jqdialog-title').wrap('<div class="widget-header" />')
            }
        }
    )

});

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

//查询按钮
function search(){
    var anchorName = $.trim($("#anchorName").val());//主播姓名
    var mobile = $.trim($("#mobile").val());//主播手机号
    var idCard = $.trim($("#idCard").val());//身份证号
    var curpagenum = $(grid_selector).jqGrid('getGridParam', 'page');   //当前页码
    $(grid_selector).jqGrid("setGridParam",
        {url:"getAnchorSingleList?anchorName="+anchorName+"&idCard="+idCard+"&mobile="+mobile, page:curpagenum, datatype:"json"}).trigger("reloadGrid");
}

//查看财富贡献值排名
function setWealthRankingBut(cellvalue, options, cell){

    var str = "<div class='visible-md visible-lg hidden-sm hidden-xs action-buttons'>" +
        "<a class='viewDetails' href='#' title='查看贡献值排名' onclick=\"viewRanking(" + cell.id+ ")\"><i class='icon-eye-open bigger-130'></i></a>" +
        "</div>";
    return str;
}

//查看收到的礼物
function setReceivedGiftBut(cellvalue, options, cell){
    var str = "<div class='visible-md visible-lg hidden-sm hidden-xs action-buttons'>" +
        "<a class='viewDetails' href='#' title='收到的礼物' onclick=\"viewPresent(" + cell.id+ ")\"><i class='icon-eye-open bigger-130'></i></a>" +
        "</div>";
    return str;
}

function viewPresent(id){
    jQuery(grid_table_present).jqGrid({
        url: "getPresentOfAccountID?id="+id,
        datatype: "json",
        height: 450,
        cellLayout:4,
        colNames: ['主键','名称','图片','数量'],
        colModel: [
            {name:'id',width:"10",index:'id',hidden:true,align:'center'},
            {name:'prizeName',index:'prizeName',width:"200",align:'center'},
            {name:'prizeIcon',index:'prizeIcon',width:"200",align:'center',formatter:getPicUrlMapping},
            {name:'prizeNum',index:'prizeNum',width:"200",align:'center'}
        ],
        viewrecords : true,
        rowNum:10,
        rowList:[10,20,30],
        pager : grid_pager_present,
        multiselect: true,
        multiboxonly: true,
        loadComplete : function() {
            var table = this;
            setTimeout(function(){
                updatePagerIcons(table);
                enableTooltips(table);
            }, 0);
        },
        caption: "礼物信息列表",
        //autowidth: true
    });

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

    function updateActionIcons(table) {
        var replacement =
        {
            'ui-icon-pencil' : 'icon-pencil blue',
            'ui-icon-trash' : 'icon-trash red',
            'ui-icon-disk' : 'icon-ok green',
            'ui-icon-cancel' : 'icon-remove red'
        };
        $(table).find('.ui-pg-div span.ui-icon').each(function(){
            var icon = $(this);
            var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
            if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
        })
    }

    function enableTooltips(table) {
        $('.navtable .ui-pg-button').tooltip({container:'body'});
        $(table).find('.ui-pg-div').tooltip({container:'body'});
    }

    //navButtons
    jQuery(grid_table_present).jqGrid('navGrid',grid_pager_present,
        { 	//navbar options
            edit: false,
            editicon : 'icon-pencil blue',//修改
            add: false,
            addicon : 'icon-plus-sign purple',//新增
            del: false,
            delicon : 'icon-trash red',//删除
            search: false,
            searchicon : 'icon-search orange',//搜索
            refresh: true,
            refreshicon : 'icon-refresh green',//刷新
            view: true,
            viewicon : 'icon-zoom-in grey',//查看
        },
        {
            //edit record form
            //closeAfterEdit: true,
            recreateForm: true,
            beforeShowForm : function(e) {
                var form = $(e[0]);
                form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
                style_edit_form(form);
            }
        },
        {
            //new record form
            closeAfterAdd: true,
            recreateForm: true,
            viewPagerButtons: false,
            beforeShowForm : function(e) {
                var form = $(e[0]);
                form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
                style_edit_form(form);
            }
        },
        {
            //delete record form
            recreateForm: true,
            beforeShowForm : function(e) {
                var form = $(e[0]);
                if(form.data('styled')) return false;

                form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
                style_delete_form(form);

                form.data('styled', true);
            },
            onClick : function(e) {
                alert(1);
            }
        },
        {
            //search form
            recreateForm: true,
            afterShowSearch: function(e){
                var form = $(e[0]);
                form.closest('.ui-jqdialog').find('.ui-jqdialog-title').wrap('<div class="widget-header" />')
                style_search_form(form);
            },
            afterRedraw: function(){
                style_search_filters($(this));
            }
            ,
            multipleSearch: true,
            /**
             multipleGroup:true,
             showQuery: true
             */
        },
        {
            //view record form
            recreateForm: true,
            beforeShowForm: function(e){
                var form = $(e[0]);
                form.closest('.ui-jqdialog').find('.ui-jqdialog-title').wrap('<div class="widget-header" />')
            }
        }
    )

    $(grid_table_present).jqGrid("setGridParam",
        {url:"getPresentOfAccountID?id="+id, page:1, datatype:"json"}).trigger("reloadGrid");
    var dialog = $("#dialog-message-present").removeClass('hide').dialog({
        modal: true,
        title: "礼物信息列表",
        title_html: true,
        width:680,
        height:680
    });
}

//礼物图片
function getPicUrlMapping(cellvalue,options,cell){
    return "<img  style='border-radius:100%;border:1px solid #5293c4;max-width:30px; '  " +
        "src='"+ cellvalue+"'/>";
}

function viewRanking(id){
    jQuery(grid_table_ranking).jqGrid({
        url: "getFansRanking?id="+id,
        datatype: "json",
        height: 450,
        cellLayout:4,
        colNames: ['主键','排名','昵称','头像','贡献值'],
        colModel: [
            {name:'id',width:"10",index:'id',hidden:true,align:'center'},
            {name:'ranking',index:'ranking',width:"50",align:'center'},
            {name:'nickName',index:'nickName',width:"150",align:'center'},
            {name:'headPic',index:'headPic',width:"150",align:'center',formatter:getPicUrlMapping},
            {name:'denseNum',index:'denseNum',width:"150",align:'center'}
        ],
        multiselect: true,
        multiboxonly: true,
        caption: "贡献信息列表（前十）",
    });

    $(grid_table_ranking).jqGrid("setGridParam",
        {url:"getFansRanking?id="+id, page:1, datatype:"json"}).trigger("reloadGrid");
    var dialog = $("#dialog-message-ranking").removeClass('hide').dialog({
        modal: true,
        title: "查看贡献信息排名",
        title_html: true,
        width:550,
        height:550
    });
}