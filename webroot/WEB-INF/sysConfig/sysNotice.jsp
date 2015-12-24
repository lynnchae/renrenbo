<%--

  Created by IntelliJ IDEA.
  User: zhuosh
  Date: 2015/8/2
  Time: 12:49
  To change this template use File | Settings | File Templates.
--%>
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


    <!--[if lte IE 8]>
    <link rel="stylesheet" href="<%=basePath%>assets/css/ace-ie.min.css" />
    <![endif]-->

    <!-- ace styles -->
    <link rel="stylesheet" href="<%=basePath%>assets/css/ace.min.css" />
    <link rel="stylesheet" href="<%=basePath%>assets/css/ace-rtl.min.css" />
    <link rel="stylesheet" href="<%=basePath%>assets/css/ace-skins.min.css" />

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



    <!-- ace scripts -->

    <script src="<%=basePath%>assets/js/ace-elements.min.js"></script>
    <script src="<%=basePath%>assets/js/ace.min.js"></script>
    <script src="<%=basePath%>assets/js/bootbox.min.js"></script>

    <script src='<%=basePath%>assets/js/index.js' language='JavaScript' charset='utf-8'>

    </script>
    <script src="<%=basePath%>assets/js/jquery-ui-1.10.3.full.min.js"></script>

    <!--jquery.validate-->
    <script src="<%=basePath%>assets/js/jquery.validate.min.js"></script>



    <!--引入主播界面的js-->
    <script src="<%=basePath%>js/sysConfig/sysNotice.js"></script>
    <script src="<%=basePath%>assets/js/ajaxfileupload.js"></script>
    <script src="<%=basePath%>assets/js/jquery-ui-1.10.3.full.min.js"></script>

</head>

<body>
<div class="navbar navbar-default" id="navbar">
    <script type="text/javascript">
        try{ace.settings.check('navbar' , 'fixed')}catch(e){}
    </script>


    <div class="main-container" id="main-container">


        <div class="col-xs-12 col-sm-4" style="width:700px;height:300px;">
            <div class="widget-box">
                <div class="widget-header">
                    <h4>FM活动</h4>
                    <span class="widget-toolbar">
						<a href="#" data-action="reload" onclick="queryNoticeModuleInfo(1)">
                                   <i class="icon-refresh"></i>
                        </a>

						<a href="#" data-action="collapse">
                                    <i class="icon-chevron-up"></i>
                        </a>


					</span>
                </div>

                <div class="widget-body" id="notice1">
                    <div class="widget-main">
                        <form id="noticeActivityFrom">
                        <input type="hidden" id="id" value="1">
                            <div style="position: relative;height:210px;">

                                <div style="float: left;margin-left:20px; margin-top: 2px;">
                                    <img src="" style="height: 100px;width: 100px;" name="icon"/>

                                    <input class="input-icon" type="file" style="margin-top: 10px;" id="icon1" name="uploadImg" >
                                </div>


                                <div style="float: left;margin-left: 50px;width: 300px;">
                                    <div>
                                        <label >
                                            标题:
                                        </label>

                                        <div>
                                            <input class="input-xxlarge input-mask-eyescript" type="text" name="title" />
                                        </div>
                                    </div>

                                    <div style="margin-top: 20px;">
                                        <label>
                                            跳转的url:
                                            <small class="text-info">(http://或者https://)</small>
                                        </label>

                                        <div>
                                            <input class="input-xxlarge input-mask-eyescript" type="text" name="url" value="http://">
                                        </div>
                                    </div>

                                </div>


                            </div>

                        <div style="margin-top:52px;">
                            <label for="form-field-8">简介:</label>
                            <textarea class="form-control" name="brief" placeholder="请输入简介内容" style="height: 70px;"></textarea>
                        </div>


                        <div class="form-actions left" style="margin-top:70px;background-color: white">
                            <button type="button" class="btn btn-sm btn-success"  onclick="updateNoticeInfo('1')">
                                提交
                                <i class="icon-arrow-right icon-on-right bigger-110" ></i>
                            </button>
                        </div>
                    </div>
                    </div>
                </div>
            </div>
        </div><!-- /span -->

    </div>


</div>
</body>
</html>
