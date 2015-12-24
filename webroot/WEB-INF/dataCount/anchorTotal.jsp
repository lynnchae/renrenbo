<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8" />
    <title>主播数据总统计</title>
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

    <!--引入主播数据总统计js-->
    <script src="<%=basePath%>js/dataCount/anchorTotal.js"></script>

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

        <div class="col-xs-12">
            <h3 class="header smaller lighter green">人人播所有主播数据统计</h3>

            <div class="infobox infobox-green  ">
                <div class="infobox-icon">
                    <i class="icon-comments"></i>
                </div>

                <div class="infobox-data">
                    <span class="infobox-data-number" id="sendMessage"></span>
                    <div class="infobox-content">发送消息</div>
                </div>
                <!--<div class="stat stat-success">8%</div>-->
            </div>

            <div class="infobox infobox-blue  ">
                <div class="infobox-icon">
                    <i class="icon-comments"></i>
                </div>

                <div class="infobox-data">
                    <span class="infobox-data-number" id="frowardMessage"></span>
                    <div class="infobox-content">转发消息</div>
                </div>
                <!--<div class="stat stat-success">8%</div>-->
            </div>

            <div class="infobox infobox-green  ">
                <div class="infobox-icon">
                    <i class="icon-twitter"></i>
                </div>

                <div class="infobox-data">
                    <span class="infobox-data-number" id="fansReply"></span>
                    <div class="infobox-content">接收粉丝回复数</div>
                </div>
                <!--<div class="stat stat-success">8%</div>-->
            </div>

            <!--<div class="infobox infobox-green  ">
                <div class="infobox-icon">
                    <i class="icon-twitter"></i>
                </div>

                <div class="infobox-data">
                    <span class="infobox-data-number" id="anchorActivity"></span>
                    <div class="infobox-content">主播活跃度</div>
                </div>
                <div class="stat stat-success">8%</div>
            </div>

            <div class="infobox infobox-green  ">
                <div class="infobox-icon">
                    <i class="icon-twitter"></i>
                </div>

                <div class="infobox-data">
                    <span class="infobox-data-number" id="fanActivity"></span>
                    <div class="infobox-content">粉丝活跃度</div>
                </div>
                <div class="stat stat-success">8%</div>
            </div>-->

            <div class="infobox infobox-blue  ">
                <div class="infobox-icon">
                    <i class="icon-user"></i>
                </div>

                <div class="infobox-data">
                    <span class="infobox-data-number" id="fansRetention"></span>
                    <div class="infobox-content">粉丝留存</div>
                </div>
                <!--<div class="stat stat-success">8%</div>-->
            </div>

            <div class="infobox infobox-green  ">
                <div class="infobox-icon">
                    <i class="icon-user"></i>
                </div>

                <div class="infobox-data">
                    <span class="infobox-data-number" id="coverPerson"></span>
                    <div class="infobox-content">覆盖人次</div>
                </div>
                <!--<div class="stat stat-success">8%</div>-->
            </div>

            <div class="infobox infobox-blue  ">
                <div class="infobox-icon">
                    <i class="icon-user"></i>
                </div>

                <div class="infobox-data">
                    <span class="infobox-data-number" id="broadcastTime"></span>
                    <div class="infobox-content">播出时长</div>
                </div>
                <!--<div class="stat stat-success">8%</div>-->
            </div>

            <div class="infobox infobox-green  ">
                <div class="infobox-icon">
                    <i class="icon-user"></i>
                </div>

                <div class="infobox-data">
                    <span class="infobox-data-number" id="populaCity"></span>
                    <div class="infobox-content">播报热门城市</div>
                </div>
                <!--<div class="stat stat-success">8%</div>-->
            </div>

        </div>

    </div>

</div>

</body>
</html>
