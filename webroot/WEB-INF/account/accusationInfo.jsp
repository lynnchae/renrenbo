
<%@ page contentType="text/html;charset=UTF-8" language="java" %>



<html>
<head>
  <meta charset="utf-8" />
  <title>举报管理</title>
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

  <!--引入举报列表js-->
  <script src="<%=basePath%>js/account/accusationInfo.js"></script>

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
    <!--禁播设置对话框-->
    <div id="dialog-message-noPlay" class="hide">
      <div class="col-xs-12">
        <form class="form-horizontal" role="form">
          <div class="form-group">
            <p class="col-sm-3 text-left">举报人：</p>
            <div class="col-sm-9 text-left">
              <span id="dialog_reportName"></span>
            </div>
          </div>
          <div class="form-group">
            <p class="col-sm-3 text-left">举报理由：</p>
            <div class="col-sm-9">
              <p id="dialog_accusation_reason"></p>
            </div>
          </div>
          <div class="form-group">
            <p class="col-sm-3 text-left">被举报人：</p>
            <div class="col-sm-9 text-left">
              <span id="dialog_beReportName"></span>
            </div>
          </div>
          <div class="form-group">
            <p class="col-sm-4 text-left">被举报人身份证号：</p>
            <div class="col-sm-8 text-left">
              <span id="dialog_beReportIdcard"></span>
            </div>
          </div>
          <div class="form-group">
            <p class="col-sm-3 text-left">禁播设置：</p>
            <div>
              <input class="col-sm-1" type="radio" value="0" name="dialog_status" id="dialog_status" /><p class="col-sm-4 text-left">禁播</p>
            </div>
          </div>
          <div class="form-group">
            <p class="col-sm-3 text-left">禁播时长：</p>
            <div>
              <select class="select7-choices" name="dialog_duration" id="dialog_duration">
                <option value="3">三天</option>
                <option value="2">二天</option>
                <option value="1">一天</option>
              </select>
            </div>
          </div>
          <div class="form-group">
            <p class="col-sm-3 text-left">禁播原因：</p>
            <div class="col-sm-9">
              <textarea id="dialog_noplay_reason" class="form-control" placeholder="禁播原因"></textarea><br><br>
            </div>
          </div>
        </form>
      </div>
    </div>

    <div class="col-xs-12">
      <form class="form-inline">
        <label>举报人：</label>
        <input type="text" id="reportName"  name ="reportName" class="input-small" placeholder="举报人" />&nbsp;&nbsp;&nbsp;&nbsp;
        <label>被举报人：</label>
        <input type="text" id="beReportName"  name ="beReportName" class="input-small" placeholder="被举报人" />&nbsp;&nbsp;&nbsp;&nbsp;
        <label>被举报人身份证号：</label>
        <input type="text" id="idCard"  name ="idCard" class="input-small" style="width: 150px;"  placeholder="身份证号" />&nbsp;&nbsp;&nbsp;&nbsp;
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
