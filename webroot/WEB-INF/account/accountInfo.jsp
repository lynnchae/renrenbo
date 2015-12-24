
<%@ page contentType="text/html;charset=UTF-8" language="java" %>



<html>
<head>
    <meta charset="utf-8" />
    <title>主播认证</title>
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




    <script src="<%=basePath%>assets/js/jquery-2.0.3.min.js"></script>


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
    <script src="<%=basePath%>assets/js/jquery.validate.min.js"></script>
    <script src="<%=basePath%>assets/js/ajaxfileupload.js"></script>

    <!--引入主播认证js-->
    <script src="<%=basePath%>js/account/accountInfo.js"></script>
    <!--数据提交审核js-->
    <script src="<%=basePath%>js/uploadImg/uploadImg.js"></script>

</head>

<body>
<div class="navbar navbar-default" id="navbar">
    <script type="text/javascript">
        try{ace.settings.check('navbar' , 'fixed')}catch(e){}
    </script>

    <div class="main-container" id="main-container">
        <script type="text/javascript">
            try{ace.settings.check('main-container' , 'fixed')}catch(e){}
        </script>
        <!--审核对话框-->
        <div id="dialog-message-verify" class="hide">
            <div class="col-xs-12">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <div class="col-sm-4">
                            <img id="dialog_headPic"  style='max-width:80px;' src=""/><br><br>
                        </div>
                        <p class="col-sm-2 text-left">昵称：</p>
                        <div class="col-sm-6 text-left">
                            <span id="dialog_nickName"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <p class="col-sm-2 text-left">姓名：</p>
                        <div class="col-sm-4 text-left">
                            <span id="dialog_name"></span>
                        </div>
                        <p class="col-sm-2 text-left">性别：</p>
                        <div class="col-sm-4 text-left">
                            <span id="dialog_sex"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <p class="col-sm-3 text-left">主播标签：</p>
                        <div class="col-sm-9 text-left">
                            <span id="dialog_remark"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <p class="col-sm-3 text-left">手机号码：</p>
                        <div class="col-sm-9 text-left">
                            <span id="dialog_mobile"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <p class="col-sm-3 text-left"> 身份证号：</p>
                        <div class="col-sm-9 text-left">
                            <span id="dialog_idCard"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <p class="col-sm-3 text-left"> 审核状态：</p>
                        <div>
                            <input class="col-sm-1" type="radio" value="4" name="dialog_status" id="dialog_status_no" /><p class="col-sm-4 text-left">不通过</p>
                            <input class="col-sm-1" type="radio" value="3" name="dialog_status" id="dialog_status_yes" /><p class="col-sm-3 text-left">通过</p>
                            <br><br>
                        </div>
                    </div>
                    <div class="form-group">
                        <p class="col-sm-2 text-left">备注：</p>
                        <div class="col-sm-10">
                            <textarea id="dialog_reason" class="form-control" id="form-field-8" placeholder="请输入审核理由"></textarea><br><br>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <!--禁播设置对话框-->
        <div id="dialog-message-noPlay" class="hide">
            <div class="col-xs-12">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <div class="form-group">
                            <p class="col-sm-3 text-left">昵称：</p>
                            <div class="col-sm-9 text-left">
                                <span id="dialog_noplay_nickName"></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <p class="col-sm-3 text-left">投诉人员：</p>
                        <div class="col-sm-9 text-left">
                            <select class="select7-choices" name="dialog_complaint_name" id="dialog_complaint_name">
                                <option>-请选择-</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <p class="col-sm-3 text-left">投诉理由：</p>
                        <div class="col-sm-9">
                            <textarea id="dialog_complaint_reason" class="form-control" placeholder="请输入投诉原因"></textarea><br><br>
                        </div>
                    </div>
                    <div class="form-group">
                        <p class="col-sm-3 text-left">申诉人员：</p>
                        <div class="col-sm-9 text-left">
                            <select class="select7-choices" name="dialog_complaint_name" id="dialog_appeal_name">
                                <option>-请选择-</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <p class="col-sm-3 text-left">申诉理由：</p>
                        <div class="col-sm-9">
                            <textarea id="dialog_appeal_reason" class="form-control" placeholder="请输入申诉原因"></textarea><br><br>
                        </div>
                    </div>
                    <div class="form-group">
                        <p class="col-sm-3 text-left">禁播设置：</p>
                        <div>
                            <input class="col-sm-1" type="radio" value="0" name="dialog_ban" id="dialog_ban" /><p class="col-sm-4 text-left">禁播</p>
                            <input class="col-sm-1" type="radio" value="1" name="dialog_ban" id="dialog_no_ban" /><p class="col-sm-3 text-left">解禁</p>
                            <br><br>
                        </div>
                    </div>
                    <div class="form-group">
                        <p class="col-sm-3 text-left">禁播原因：</p>
                        <div class="col-sm-9">
                            <textarea id="dialog_noplay_reason" class="form-control" placeholder="请输入禁播原因"></textarea><br><br>
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <div class="col-xs-12">
            <form class="form-inline">
                <label>主播状态：</label>
                <select class="select7-choices" name="anchorStatus" id="anchorStatus">
                    <option value="-1">-状态-</option>
                    <option value="0">不申请审核</option>
                    <option value="1">申请审核</option>
                    <option value="3">审核成功</option>
                    <option value="4">审核失败</option>
                    <option value="5">禁播</option>
                    <option value="6">解禁</option>
                </select>&nbsp;&nbsp;&nbsp;&nbsp;
                <label>所属城市：</label>
                <input type="text" id="cityName"  name ="cityName" class="input-small" placeholder="城市" />&nbsp;&nbsp;&nbsp;&nbsp;
                <label>手机号：</label>
                <input type="text" id="mobile"  name ="mobile" class="input-small" placeholder="手机号" />&nbsp;&nbsp;&nbsp;&nbsp;
                <button type="button" class="btn btn-info btn-sm" onclick="search()">
                    <i class="icon-key bigger-110"></i>
                    查询
                </button>
            </form>

            <table id="grid-table" style="font-size: 12px"></table>
            <div id="grid-pager"></div>

        </div>
    </div>

</div>
</body>
</html>
