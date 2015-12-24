//单个粉丝数据统计
var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";

var grid_table_present = "#grid-table-present";
var grid_pager_present = "#grid-pager-present";

var grid_table_ranking = "#grid-table-ranking";
var grid_pager_ranking = "#grid-pager-ranking";

var singFans = this;


$(function($) {
    //第一次加载数据
    jQuery(grid_selector).jqGrid({
        url: 'getFansSingleList',
        datatype: "json",
        height: 450,
        cellLayout:4,
        colNames: ['主键','主播的accountID','昵称','手机号','收听主播语音条数','收听系统语音条数','回复数','打赏总金额','打赏的礼品','金额排名'],
        colModel: [
            {name: 'id', index: 'id', hidden: true,align: 'center', width: "10"},
            {name: 'accountID', index: 'accountID',hidden: true, align: 'center', width: "20"},
            {name: 'nickName', index: 'nickName', align: 'center', width: "20"},
            {name: 'mobile', index: 'mobile', align: 'center', width: "20"},
            {name: 'voiceAnchorNum', index: 'voiceAnchorNum', align: 'center', width: "15"},
            {name: 'voiceSystemNum', index: 'voiceSystemNum', align: 'center', width: "15"},
            {name: 'replyNum', index: 'replyNum', align: 'center', width: "15"},
            //{name: 'rewardNum', index: 'rewardNum', align: 'center', width: "15"},
            {name: 'moneyNum', index: 'moneyNum', align: 'center', width: "15"},
            //{name: 'moneyRanking', index: 'moneyRanking', align: 'center', width: "15"},
            {
                name: 'present',
                index: '',
                align: 'center',
                width: "80",
                fixed: true,
                sortable: false,
                resize: false,
                formatter: queryPresent
            },
            {
                name: 'ranking',
                index: '',
                align: 'center',
                width: "80",
                fixed: true,
                sortable: false,
                resize: false,
                formatter: queryRanking
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
        caption: "粉丝数据统计列表",
        autowidth: true,
        //cellEdit : true,//是否允许单元格可编辑
        //cellurl : 'editIsVoice'//编辑是否允许播音

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

    //unlike navButtons icons, action icons in rows seem to be hard-coded
    //you can change them like this in here if you want
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

//查询按钮
function search(){
    var nickName = $.trim($("#nickName").val());//昵称
    var mobile = $.trim($("#mobile").val());//手机号
    var curpagenum = $(grid_selector).jqGrid('getGridParam', 'page');   //当前页码
    $(grid_selector).jqGrid("setGridParam",
        {url:"getFansSingleList?nickName="+nickName+"&mobile="+mobile, page:curpagenum, datatype:"json"}).trigger("reloadGrid");
}

//查看打赏的礼物信息
function queryPresent(cellvalue, options, cell){
    var str = "<div class='visible-md visible-lg hidden-sm hidden-xs action-buttons'>" +
        "<a class='viewDetails' href='#' title='查看打赏的礼物信息' onclick=\"viewPresent(" + cell.id+ ")\"><i class='icon-eye-open bigger-130'></i></a>" +
        "</div>";
    return str;
}

function  viewPresent(id){
    jQuery(grid_table_present).jqGrid({
        url: "rewardPresentOfAccountID?id="+id,
        datatype: "json",
        height: 450,
        cellLayout:4,
        colNames: ['主键','名称','图片','数量','总金额'],
        colModel: [
            {name:'id',width:"10",index:'id',hidden:true,align:'center'},
            {name:'prizeName',index:'prizeName',width:"150",align:'center'},
            {name:'prizeIcon',index:'prizeIcon',width:"150",align:'center',formatter:getPicUrlMapping},
            {name:'prizeNum',index:'prizeNum',width:"150",align:'center'},
            {name:'moneyNum',index:'moneyNum',width:"150",align:'center'}
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
        caption: "粉丝打赏的礼物列表",
        autowidth: true,
        //cellEdit : true
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
        {url:"rewardPresentOfAccountID?id="+id, page:1, datatype:"json"}).trigger("reloadGrid");
    var dialog = $("#dialog-message-present").removeClass('hide').dialog({
        modal: true,
        title: "打赏礼物信息列表",
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

//查看打赏金额的排名信息
function queryRanking(cellvalue, options, cell){
    var str = "<div class='visible-md visible-lg hidden-sm hidden-xs action-buttons'>" +
        "<a class='viewDetails' href='#' title='查看打赏金额排名的信息' onclick=\"viewRanking(" + cell.id+ ")\"><i class='icon-eye-open bigger-130'></i></a>" +
        "</div>";
    return str;
}

function viewRanking(id){
    jQuery(grid_table_ranking).jqGrid({
        url: "rewardRankingOfAccountID?id="+id,
        datatype: "json",
        height: 450,
        cellLayout:4,
        colNames: ['主键','排名','主播昵称','主播头像','被打赏金额'],
        colModel: [
            {name:'id',width:"10",index:'id',hidden:true,align:'center'},
            {name:'ranking',index:'ranking',width:"50",align:'center'},
            {name:'nickName',index:'nickName',width:"150",align:'center'},
            {name:'headPic',index:'headPic',width:"150",align:'center',formatter:getPicUrlMapping},
            {name:'denseNum',index:'denseNum',width:"150",align:'center'}
        ],
        viewrecords : true,
        multiselect: true,
        multiboxonly: true,
        caption: "打赏金额排名信息列表",
        autowidth: true
    });

    $(grid_table_ranking).jqGrid("setGridParam",
        {url:"rewardRankingOfAccountID?id="+id, page:1, datatype:"json"}).trigger("reloadGrid");
    var dialog = $("#dialog-message-ranking").removeClass('hide').dialog({
        modal: true,
        title: "查看打赏金额排名信息（前十）",
        title_html: true,
        width:550,
        height:600
    });
}