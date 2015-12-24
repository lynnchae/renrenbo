
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8" />
  <title>开箱记录</title>
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
    <script src="<%=basePath%>js/statistics/listenerOrderList.js"></script>

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
          <label>箱子：</label> <input type="text" id="boxName"  name ="boxName" class="input-small" placeholder="箱子名称" style="width: 150px" />
          <label>奖品：</label> <input type="text" id="prizeName"  name ="prizeName" class="input-small" placeholder="奖品名称" style="width: 150px"/>
        <label>礼物类型：</label>
        <select class="select2-choices" name="status" id="status">
          <option value="-1">-类型-</option>
          <option value="0">已生成订单</option>
          <option value="1">订单已处理</option>
        </select>
        <label>领取方式：</label>
        <select class="select2-choices" name="cancelType" id="cancelType">
          <option value="-1">-领取方式-</option>
          <option value="1">自己领取</option>
          <option value="2">送主播</option>
        </select>
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
