
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8" />
  <title>宝箱管理</title>
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
    <script src="<%=basePath%>js/box/prizeBox.js"></script>
    <!--引入图片上传的js-->
    <script src="<%=basePath%>js/uploadImg/uploadImg.js"></script>
    <!--引入验证的js-->
    <script src="<%=basePath%>js/util/validate.js"></script>
    <!--箱子与奖品映射的js-->
    <%--<script src="<%=basePath%>js/box/prizeBoxMapping.js"></script>--%>
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

    <!--修改时的弹框-->
    <div id="dialog-message" class="hide">
        <div class="col-xs-12">
            <form class="form-horizontal" role="form">
                <%--<div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="dialog_name"> 编码：</label>
                    <div class="col-sm-9">
                        <span id="dialog_boxCode"></span>
                    </div>
                </div>--%>
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="dialog_name"> 箱子名：</label>
                    <div class="col-sm-9">
                        <input type="text" id="dialog_name" placeholder="编码" class="col-xs-10 col-sm-5" autocomplete="off" />
                        <div class="help-block col-xs-12 col-sm-reset inline name_message" style="color:red;"></div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="dialog_pic"> 图片：</label>
                    <div class="col-sm-9">
                        <img id="dialog_pic"  style='max-width:80px;' src=""/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="editFile"> 图片：</label>
                    <div class="col-sm-9">
                        <input type="file" id="editFile" name="file" value="请选择文件" style="width:180px;display:inline"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="editFile">价格（金币）：</label>
                    <div class="col-sm-9">
                        <input id="dialog_denseNum" type="text"
                               style="ime-mode:disabled;"
                               ondragenter="return false"
                               onkeypress="return vailFloatNumberPerfect(event,this)"
                               onpaste="return !clipboardData.getData('text').match(/\D/)"
                               placeholder="请输入数字"/>
                        <div class="help-block col-xs-12 col-sm-reset inline dense_message" style="color:red;"></div>
                    </div>
                </div>
                <input type="hidden" id="dialog_createTime">
            </form>
        </div>
    </div><!-- #dialog-message -->

    <!--添加时的弹框-->
    <div id="dialog-message-add" class="row hide">
        <div class="col-xs-12">
            <form class="form-horizontal" role="form">
                <%--<div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="add_boxCode"> 编码：</label>
                    <div class="col-sm-9">
                        <input type="text" id="add_boxCode" placeholder="编码" class="col-xs-10 col-sm-5" autocomplete="off" />
                        <div class="help-block col-xs-12 col-sm-reset inline code_message" style="color:red;"></div>
                    </div>
                </div>--%>
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="add_name"> 箱子名：</label>
                    <div class="col-sm-9">
                        <input type="text" id="add_name" placeholder="箱子名" class="col-xs-10 col-sm-5" autocomplete="off" />
                        <div class="help-block col-xs-12 col-sm-reset inline name_message" style="color:red;"></div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="addFile"> 图片：</label>
                    <div class="col-sm-9">
                        <input type="file" id="addFile" name="file" style="width:180px;display:inline"/>
                        <div class="help-block col-xs-12 col-sm-reset inline img_message" style="color:red;"></div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="addFile"> 价格（金币）：</label>
                    <div class="col-sm-9">
                        <input id="add_denseNum" type="text"
                               style="ime-mode:disabled;"
                               ondragenter="return false"
                               onkeypress="return vailFloatNumberPerfect(event,this)"
                               onpaste="return !clipboardData.getData('text').match(/\D/)"
                               placeholder="请输入数字"/>
                        <div class="help-block col-xs-12 col-sm-reset inline dense_message" style="color:red;"></div>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <div class="col-xs-12">
      <form class="form-inline">
          <label>箱子名称：</label> <input type="text" id="name"  name ="name" class="input-small" placeholder="name" />
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
