<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8" />
    <title>单个主播数据统计</title>
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

    <!--引入单个主播数据统计js-->
    <script src="<%=basePath%>js/dataCount/singleAnchor.js"></script>

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

        <!--查看礼物详情时的弹框-->
        <div id="dialog-message-present" class="hide">
            <table id="grid-table-present" style="font-size: 12px"></table>
            <div id="grid-pager-present"></div>
        </div>

        <br>

        <!--查看贡献排名的弹框-->
        <div id="dialog-message-ranking" class="hide">
            <table id="grid-table-ranking" style="font-size: 12px"></table>
            <div id="grid-pager-ranking"></div>
        </div>

        <br>

        <!--财富值贡献对话框-->
        <%--<div id="dialog-message-wealth" class="hide">--%>
            <%--<div class="col-xs-12">--%>
                <%--<form class="form-horizontal" role="form">--%>
                    <%--<table id="sample-table-1" class="table table-striped table-bordered table-hover">--%>
                        <%--<tr>--%>
                            <%--<td class="col-sm-4 text-center">排名</td>--%>
                            <%--<td class="col-sm-4 text-center">姓名</td>--%>
                            <%--<td class="col-sm-4 text-center">财富值</td>--%>
                        <%--</tr>--%>
                        <%--<c:forEach items="${rankingMaps}" var="rankingMap">--%>
                            <%--<tr>--%>
                                <%--<td class="col-sm-4 text-center">${rankingMap.ranking}</td>--%>
                                <%--<td class="col-sm-4 text-center">${rankingMap.nickName}</td>--%>
                                <%--<td class="col-sm-4 text-center">${rankingMap.denseNum}</td>--%>
                            <%--</tr>--%>
                        <%--</c:forEach>--%>
                        <%--&lt;%&ndash;<s:iterator value="studentMap" id="column">--%>
                            <%--<tr>--%>
                                <%--<td><s:property value="#column"/></td>--%>
                                <%--<td><s:property value="value.ranking"/></td>--%>
                                <%--<td><s:property value="value.nickName"/></td>--%>
                                <%--<td><s:property value="value.denseNum"/></td>--%>
                            <%--</tr>--%>
                        <%--</s:iterator>&ndash;%&gt;--%>
                    <%--</table>--%>
            <%--</div>--%>
        <%--</div>--%>

        <div class="col-xs-12">
            <form class="form-inline">
                <label>主播姓名：</label>
                <input type="text" id="anchorName"  name ="anchorName" class="input-small" placeholder="姓名" />&nbsp;&nbsp;&nbsp;&nbsp;
                <label>手机号：</label>
                <input type="text" id="mobile"  name ="mobile" class="input-small" style="width: 150px;" placeholder="手机号" />&nbsp;&nbsp;&nbsp;&nbsp;
                <label>身份证号：</label>
                <input type="text" id="idCard"  name ="idCard" class="input-small" style="width: 150px;" placeholder="身份证号" />&nbsp;&nbsp;&nbsp;&nbsp;
                <button type="button" class="btn btn-info btn-sm" onclick="search()">
                    <i class="icon-key bigger-110"></i>
                    查询
                </button>
               <%-- <table id="sample-table-1" class="table table-striped table-bordered table-hover">
                     <div style="display: none">
                         <span id="form-curpagenum" curpagenum="${result.curpagenum}"></span>
                         <span id="form-mobile">${result.mobile}</span>
                         <span id="form-anchorName">${result.anchorName}</span>
                         <span id="form-idCard">${result.idCard}</span>
                     </div>
                </table>--%>
            </form>
            <table id="grid-table" style="font-size: 12px"></table>
            <div id="grid-pager"></div>

        </div>
    </div>

</div>
</body>
</html>
