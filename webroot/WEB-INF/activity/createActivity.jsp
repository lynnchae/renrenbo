
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8" />
    <title>jquery网格插件 - Bootstrap后台管理系统模版Ace下载</title>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

    %>

    <!-- basic styles -->
    <link href="<%=basePath%>assets/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="<%=basePath%>assets/css/font-awesome.min.css" />
    <!--[if IE 7]>
    <link rel="stylesheet" href="<%=basePath%>assets/css/font-awesome-ie7.min.css" />
    <![endif]-->
    <!-- page specific plugin styles -->
    <link rel="stylesheet" href="<%=basePath%>assets/css/jquery-ui-1.10.3.full.min.css" />
    <link rel="stylesheet" href="<%=basePath%>assets/css/datepicker.css" />
    <link rel="stylesheet" href="<%=basePath%>assets/css/ui.jqgrid.css" />
    <!-- fonts -->
    <link rel="stylesheet" href="<%=basePath%>assets/css/index.css" />
    <!-- ace styles -->
    <link rel="stylesheet" href="<%=basePath%>assets/css/ace.min.css" />
    <link rel="stylesheet" href="<%=basePath%>assets/css/ace-rtl.min.css" />
    <link rel="stylesheet" href="<%=basePath%>assets/css/ace-skins.min.css" />

    <!--[if lte IE 8]>
    <link rel="stylesheet" href="<%=basePath%>assets/css/ace-ie.min.css" />
    <![endif]-->

    <!-- inline styles related to this page -->

    <!-- ace settings handler -->

    <script src="<%=basePath%>assets/js/ace-extra.min.js"></script>

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

    <!--[if lt IE 9]>
    <script src="<%=basePath%>assets/js/html5shiv.js"></script>
    <script src="<%=basePath%>assets/js/respond.min.js"></script>
    <![endif]-->



    <!--[if !IE]> -->
    <script src="<%=basePath%>assets/js/jquery-2.0.3.min.js"></script>
    <!-- <![endif]-->

    <!--[if IE]>

    <![endif]-->

    <!--[if !IE]> -->
    <script type="text/javascript">
        window.jQuery || document.write("<script src='<%=basePath%>assets/js/jquery-2.0.3.min.js'>"+"<"+"/script>");
    </script>
    <!-- <![endif]-->

    <!--[if IE]>
    <script type="text/javascript">
        window.jQuery || document.write("<script src='<%=basePath%>assets/js/jquery-1.10.2.min.js'>"+"<"+"/script>");
    </script>
    <![endif]-->

    <script type="text/javascript">
        if("ontouchend" in document) document.write("<script src='<%=basePath%>assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
    </script>
    <script src="<%=basePath%>assets/js/bootstrap.min.js"></script>
    <script src="<%=basePath%>assets/js/typeahead-bs2.min.js"></script>
    <script src="<%=basePath%>assets/js/jquery.ui.touch-punch.min.js"></script>
    <!-- page specific plugin scripts -->
    <script src="<%=basePath%>assets/js/date-time/bootstrap-datepicker.min.js"></script>
    <script src="<%=basePath%>assets/js/jqGrid/jquery.jqGrid.min.js"></script>
    <script src="<%=basePath%>assets/js/jqGrid/i18n/grid.locale-en.js"></script>


    <!-- ace scripts -->

    <script src="<%=basePath%>assets/js/ace-elements.min.js"></script>
    <script src="<%=basePath%>assets/js/ace.min.js"></script>
    <script src="<%=basePath%>assets/js/bootbox.min.js"></script>

    <script src='<%=basePath%>assets/js/index.js' language='JavaScript' charset='utf-8'>

    </script>
    <script src="<%=basePath%>assets/js/jquery-ui-1.10.3.full.min.js"></script>


    <!--引入主播界面的js-->
    <script src="<%=basePath%>js/activity/createActivity.js"></script>
</head>

<body>
<div class="navbar navbar-default" id="navbar">
    <script type="text/javascript">
        try{ace.settings.check('navbar' , 'fixed')}catch(e){}
    </script>

    <div class="main-container" id="main-container">
        <div class="col-xs-12 col-sm-4" style="width:60%;height:300px;">
            <div class="widget-box">
                <div class="widget-header">
                    <h4>运营活动管理</h4>
                    <span class="widget-toolbar">
						<a href="#" data-action="collapse">
                            <i class="icon-chevron-up"></i>
                        </a>


					</span>
                </div>

                <div class="widget-body">
                    <div class="widget-main">

                        <div>
                            <label>
                                活动名称：
                            </label>

                            <select id="activity_name" onchange="selectActivityName()">
                                <option>--请选择--</option>
                            </select>
                        </div>

                        <div style="margin-top: 10px;">
                            <label>
                                活动对象：
                            </label>

                            <select id="activity_object" onchange="selectActivityObject()">
                                <option value="">--请选择--</option>
                            </select>
                        </div>

                        <div style="margin-top: 10px;">
                            <label>
                                活动说明：
                            </label>

                            <textarea class="input-mask-eyescript" type="text" cols="80" rows="5"></textarea>
                        </div>

                        <div style="margin-top: 10px;">
                            <label>
                                奖励方式：
                            </label>

                            <select id="reward_type" onchange="selectRewardType()">
                                <option value="">--请选择--</option>
                                <option value="1">送钻</option>
                                <option value="2">送金币</option>
                                <option value="3">送商品</option>
                            </select>
                        </div>

                        <div id="send_type" style="margin-top: 10px;">
                            <div id="send_gold" style="display: none"><label>送</label><input type="text"/><label>钻</label></div>
                            <div id="send_diamond" style="display: none"><label>送</label><input type="text"/><label>金币</label></div>
                            <div id="send_money" style="display: none"><label>充</label><input type="text"/><label>送</label><input type="text"/></div>
                        </div>

                        <div style="margin-top: 10px;">
                            <label>
                                领取条件：
                            </label>

                            <input class="input-medium input-mask-eyescript" type="text" />
                        </div>

                        <div style="margin-top: 10px;">
                            <label>
                                每人领取：
                            </label>

                            <input class="input-medium input-mask-eyescript" type="text" />
                        </div>

                        <div style="margin-top: 10px;">
                            <label>
                                生效时间：
                            </label>

                            <input class="input-medium input-mask-eyescript" type="text" />
                        </div>

                        <div style="margin-top: 10px;">
                            <label>
                                过期时间：
                            </label>

                            <input class="input-medium input-mask-eyescript" type="text" />
                        </div>

                        <div style="margin-top: 10px;">
                            <label>
                                活动资金：
                            </label>

                            <input class="input-medium input-mask-eyescript" type="text" />&nbsp;元
                        </div>

                        <div style="margin-top: 10px;">
                            <label>
                                过期提醒：
                            </label>

                            <input class="input-mask-eyescript" type="checkbox" />&nbsp;&nbsp;过期前3天提醒一次
                        </div>

                        <div style="margin-top: 10px;">
                            <label>
                                推广设置：
                            </label>
                            <input class="input-mask-eyescript" type="checkbox" />&nbsp;&nbsp;分享领取连接
                        </div>

                        <div style="margin-top: 10px;">
                            <label>
                                分享标题：
                            </label>
                            <input type="range">
                            <input class="input-medium input-mask-eyescript" type="text"/>
                        </div>

                        <div class="form-actions left" style="margin-top:50px;background-color: white">
                            <button type="button" class="btn btn-sm btn-success" onclick="updateConfigInfo()">
                                提交
                                <i class="icon-arrow-right icon-on-right bigger-110"></i>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div><!-- /span -->

    </div>

</div>
</body>
</html>
