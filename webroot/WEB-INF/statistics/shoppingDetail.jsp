
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="utf-8" />
    <title>订单详情</title>
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


    <!--引入箱子界面的js-->
    <script src="<%=basePath%>js/statistics/shoppingDetail.js"></script>
    <!--获取跟路径的js-->
    <script src="<%=basePath%>js/util/rootPath.js"></script>

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

        <!--添加时的弹框-->
        <div id="dialog-message-add" class="hide">
            <table id="grid-table-add" style="font-size: 12px"></table>
            <div id="grid-pager-add"></div>
        </div>

        <br>

        <div class="col-xs-12">
            <div class="col-xs-12 col-sm-4" style="width:60%;height:350px;">
                <div class="widget-box">
                    <div class="widget-header">
                        <h4>商品信息</h4>
                        <span class="widget-toolbar">
                            <a href="#" data-action="collapse">
                                <i class="icon-chevron-up"></i>
                            </a>
                        </span>
                    </div>

                    <div class="widget-body">
                        <div class="widget-main">

                            <div style="position: relative;height:210px;">

                                <div style="float: left;margin-left:20px; margin-top: 2px;">
                                    <img src="${result.icon}" style="height: 200px;width: 200px;" name="icon"/>
                                </div>

                                <div style="float: left;margin-left: 50px;width: 300px;width:60%">
                                    <div>
                                        <label>
                                            商品名:
                                        </label>
                                        <div style="display: inline;font-size: medium">
                                            ${result.name}
                                        </div>

                                        <label style="margin-left: 30px">
                                            物流状态:
                                        </label>
                                        <div style="display: inline;font-size: medium">
                                            <c:choose>
                                                <c:when test="${result.sendStatus == 0}">
                                                    未发货
                                                </c:when>
                                                <c:when test="${result.sendStatus == 1}">
                                                    已发货
                                                </c:when>
                                                <c:when test="${result.sendStatus == 2}">
                                                    已验收
                                                </c:when>
                                                <c:when test="${result.sendStatus == 3}">
                                                    拒收
                                                </c:when>
                                            </c:choose>
                                        </div>
                                    </div>

                                    <div style="margin-top: 20px;">
                                        <label>
                                            收件人:
                                        </label>
                                        <div style="display: inline;font-size: medium">
                                            ${result.recipientsName}
                                        </div>
                                        <label style="margin-left: 30px">
                                            收件人电话:
                                        </label>
                                        <div style="display: inline;font-size: medium">
                                            ${result.recipientsMobile}
                                        </div>
                                        <label style="margin-left: 30px">
                                            邮编:
                                        </label>
                                        <div style="display: inline;font-size: medium">
                                            ${result.postcode}
                                        </div>
                                    </div>
                                    <div style="margin-top: 30px;">
                                        <label>
                                            地址：
                                        </label>
                                        <div style="display: inline;font-size: medium">
                                            <c:if test="${result.shoppingAddressID > 0}">
                                                ${result.provinceName},${result.cityName},${result.countyName},${result.detailAddress}
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>

            <div class="col-xs-12 col-sm-4" style="width:60%;height:300px;">
                <div class="widget-box">
                    <div class="widget-header">
                        <h4>物流信息</h4>
                        <span class="widget-toolbar">
                            <a href="#" data-action="collapse">
                                <i class="icon-chevron-up"></i>
                            </a>
                        </span>
                    </div>

                    <div class="widget-body">
                        <form id="sendMessageForm">
                        <div class="widget-main">
                            <div>
                                <label>
                                    快递公司：
                                </label>
                                <div style="display: inline;font-size: medium">
                                <c:choose>
                                    <c:when test="${result.sendStatus == 0}">
                                        <span id="orderId" style="display: none;">${result.id}</span>
                                        <input class="input-medium input-mask-eyescript" type="text" id="company" name="company" style="width: 200px" />
                                    </c:when>
                                    <c:otherwise>
                                        ${result.sendCompany}
                                    </c:otherwise>
                                </c:choose>
                                </div>
                            </div>

                            <div style="margin-top: 50px;">
                                <label>
                                    运单号：
                                </label>
                                <div style="display: inline;font-size: medium">
                                <c:choose>
                                    <c:when test="${result.sendStatus == 0}">
                                        <input class="input-medium input-mask-eyescript" type="text" id="number" name="number" style="width: 200px"/>
                                    </c:when>
                                    <c:otherwise>
                                        ${result.sendNumber}
                                    </c:otherwise>
                                </c:choose>
                                </div>
                            </div>
                            <c:if test="${result.sendStatus == 0}">
                                <div class="form-actions left" style="margin-top:50px;background-color: white">
                                    <button type="button" class="btn btn-sm btn-success" onclick="saveSendMessage()">
                                        提交
                                        <i class="icon-arrow-right icon-on-right bigger-110"></i>
                                    </button>
                                </div>
                            </c:if>
                        </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div><!-- /.main-container -->


</div>
</body>
</html>
