
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8" />
  <title>充值列表</title>
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


  <link rel="stylesheet" href="<%=basePath%>assets/css/ace-ie.min.css" />

    <link rel="stylesheet" href="<%=basePath%>css/dialog.css" />

  <script src="<%=basePath%>assets/js/ace-extra.min.js"></script>



  <script src="<%=basePath%>assets/js/html5shiv.js"></script>
  <script src="<%=basePath%>assets/js/respond.min.js"></script>





    <script src="<%=basePath%>assets/js/jquery-2.0.3.min.js"></script>


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


    <!--引入充值列表界面的js-->
    <script src="<%=basePath%>js/statistics/rechargeList.js"></script>

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

      <form class="form-inline">
          <label>用户昵称：</label>
          <input type="text" id="listenerName"  name ="listenerName" class="input-small" placeholder="昵称" />&nbsp;&nbsp;&nbsp;&nbsp;
          <%--<label>账号：</label>
          <input type="text" id="rechargeNum"  style="width:150px" name ="rechargeNum" class="input-small" placeholder="账号"/>&nbsp;&nbsp;&nbsp;&nbsp;--%>
          <label>手机号码：</label>
          <input type="text" id="mobile"   style="width:150px" name ="mobile" class="input-small" placeholder="手机号码"/>&nbsp;&nbsp;&nbsp;&nbsp;
          <label>状态：</label>
          <select class="select2-choices" name="status" id="status">
            <option value="-1">-类型-</option>
            <option value="0">未支付</option>
            <option value="1">支付中</option>
            <option value="2">已完成</option>
          </select>&nbsp;&nbsp;&nbsp;&nbsp;
          <button type="button" class="btn btn-info btn-sm" onclick="search()">
              <i class="icon-key bigger-110"></i>
              查询
          </button>
      </form>

      <table id="grid-table" style="font-size: 12px"></table>
      <div id="grid-pager"></div>

  </div>
</div><!-- /.main-container -->


</div>
</body>
</html>
