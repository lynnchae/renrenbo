
<%@ page contentType="text/html;charset=UTF-8" language="java" %>



<html>
<head>
  <meta charset="utf-8" />
  <title>用户提现操作</title>
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

  <!--引入提现操作js-->
  <script src="<%=basePath%>js/finance/withdrawalsOperation.js"></script>

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
    <!--修改状态对话框-->
    <div id="dialog-message-status" class="hide">
      <div class="col-xs-12">
        <form class="form-horizontal" role="form">
          <div class="form-group">
            <p class="col-sm-3 text-left">用户昵称：</p>
            <div class="col-sm-9 text-left">
              <span id="dialog_nickName"></span>
            </div>
          </div>
          <div class="form-group">
            <p class="col-sm-3 text-left"> 修改提现状态：</p>
            <div>
              <input class="col-sm-1" type="radio" value="2" name="dialog_status" id="dialog_status_fail" /><p class="col-sm-2 text-left">入账失败</p>
              <input class="col-sm-1" type="radio" value="3" name="dialog_status" id="dialog_status_already" /><p class="col-sm-2 text-left">已入账</p>
              <br><br>
            </div>
          </div>
          <div class="form-group">
            <p class="col-sm-3 text-left">回执单号：</p>
            <div class="col-sm-9">
              <input type="text" id="dialog_receipt_number" name="dialog_receipt_number" class="form-control" />
            </div>
          </div>
          <div class="form-group">
            <p class="col-sm-3 text-left">备注：</p>
            <div class="col-sm-9">
              <textarea id="dialog_reason" class="form-control" id="form-field-8" placeholder="备注信息"></textarea><br><br>
            </div>
          </div>
        </form>
      </div>
    </div>

    <div class="col-xs-12">
      <form class="form-inline">
        <label>昵称：</label>
        <input type="text" id="nickName"  name ="nickName" class="input-small" placeholder="昵称" />&nbsp;&nbsp;&nbsp;&nbsp;
        <label>真实姓名：</label>
        <input type="text" id="realName"  name ="realName" class="input-small" placeholder="真实姓名" />&nbsp;&nbsp;&nbsp;&nbsp;
        <label>手机号：</label>
        <input type="text" style="width: 150px;" id="mobile"  name ="mobile" class="input-small" placeholder="手机号" />&nbsp;&nbsp;&nbsp;&nbsp;
        <button type="button" class="btn btn-info btn-sm" onclick="search()">
          <i class="icon-key bigger-110"></i>
          查询
        </button>
      </form>

      <table id="grid-table" style="font-size: 12px;"></table>
      <div id="grid-pager"></div>

    </div>
  </div>

</div>
</body>
</html>
