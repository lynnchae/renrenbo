<%--

  Created by IntelliJ IDEA.
  User: zhaoym
  Date: 2015/8/11
  Time: 17:12
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

  <!--引入版本信息js-->
  <script src="<%=basePath%>js/versionUpdate/versionUpdate.js"></script>
</head>

<body>
<div class="navbar navbar-default" id="navbar">
  <script type="text/javascript">
    try{ace.settings.check('navbar' , 'fixed')}catch(e){}
  </script>


  <div class="main-container" id="main-container">
    <!--FM版本信息-->
    <div class="col-xs-12 col-sm-6" >
      <div class="widget-box">
        <div class="widget-header">
          <h4>FM版本更新</h4>
                    <span class="widget-toolbar">
						<a href="#" data-action="reload" onclick="">
                          <i class="icon-refresh"></i>
                        </a>
						<a href="#" data-action="collapse">
                          <i class="icon-chevron-up"></i>
                        </a>
					</span>
        </div>

        <div class="widget-body">
          <div class="widget-main">

            <div>
              <label for="form-field-mask-1">
                版本号
              </label>
              <div>
                <input class="input-medium input-mask-eyescript" type="text" id="fmNumber" />
              </div>
            </div>

            <div style="margin-top: 50px;">
              <label for="form-field-mask-2">
                移动操作系统
              </label>
              <div>
                <select class="select-choices" name="fmAppKey" id="fmAppKey">
                  <option value="0" selected>IOS</option>
                  <option value="1">Android</option>
                </select>
              </div>
            </div>

            <div style="margin-top: 50px;">
              <label for="form-field-mask-3">
                版本地址
              </label>
              <div>
                <input class="input-medium input-mask-eyescript" type="text" id="fmVersionUrl" />
              </div>
            </div>

            <div style="margin-top: 50px;">
              <label for="form-field-mask-4">
                备注
              </label>
              <div>
                <textarea id="fmRemark" class="form-control" id="form-field-12" placeholder=""></textarea>
              </div>
            </div>

            <div style="margin-top: 50px;">
              <label for="form-field-mask-5">
                是否是最新
              </label>
              <div>
                  <input type="radio" value="1" name="fmIsUpToDate" id="fmIsUpToDateYes" />是
                  <input type="radio" value="0" name="fmIsUpToDate" id="fmIsUpToDateNo" />否
              </div>
            </div>


            <div style="margin-top: 50px;">
              <label for="form-field-mask-6">
                是否强制更新
              </label>
              <div>
                <input type="radio" value="1" name="fmIsForcedUpDate" id="fmIsForcedUpDateYes" />是
                <input type="radio" value="0" name="fmIsForcedUpDate" id="fmIsForcedUpDateNo" />否
              </div>
            </div>

            <div class="form-actions left" style="margin-top:50px;background-color: white">
              <button type="button" class="btn btn-sm btn-success" onclick="updateVersion('fm')">
                提交
                <i class="icon-arrow-right icon-on-right bigger-110"></i>
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!--人人播版本信息-->
    <div class="col-xs-12 col-sm-6" >
      <div class="widget-box">
        <div class="widget-header">
          <h4>人人播版本更新</h4>
                    <span class="widget-toolbar">
						<a href="#" data-action="reload" onclick="">
                          <i class="icon-refresh"></i>
                        </a>
						<a href="#" data-action="collapse">
                          <i class="icon-chevron-up"></i>
                        </a>
					</span>
        </div>

        <div class="widget-body">
          <div class="widget-main">

            <div>
              <label for="form-field-mask-1">
                版本号
              </label>
              <div>
                <input class="input-medium input-mask-eyescript" type="text" id="rrbNumber" />
              </div>
            </div>

            <div style="margin-top: 50px;">
              <label for="form-field-mask-2">
                移动操作系统
              </label>
              <div>
                <select class="select7-choices" name="rrbAppKey" id="rrbAppKey">
                  <option value="0" selected>IOS</option>
                  <option value="1">Android</option>
                </select>
              </div>
            </div>

            <div style="margin-top: 50px;">
              <label for="form-field-mask-3">
                版本地址
              </label>
              <div>
                <input class="input-medium input-mask-eyescript" type="text" id="rrbVersionUrl" />
              </div>
            </div>

            <div style="margin-top: 50px;">
              <label for="form-field-mask-4">
                备注
              </label>
              <div>
                <textarea id="rrbRemark" class="form-control" id="form-field-13" placeholder=""></textarea>
              </div>
            </div>

            <div style="margin-top: 50px;">
              <label for="form-field-mask-5">
                是否是最新
              </label>
              <div>
                <input type="radio" value="1" name="rrbIsUpToDate" id="rrbIsUpToDateYes" />是
                <input type="radio" value="0" name="rrbIsUpToDate" id="rrbIsUpToDateNo" />否
              </div>
            </div>


            <div style="margin-top: 50px;">
              <label for="form-field-mask-6">
                是否强制更新
              </label>
              <div>
                <input type="radio" value="1" name="rrbIsForcedUpDate" id="rrbIsForcedUpDateYes" />是
                <input type="radio" value="0" name="rrbIsForcedUpDate" id="rrbIsForcedUpDateNo" />否
              </div>
            </div>

            <div class="form-actions left" style="margin-top:50px;background-color: white">
              <button type="button" class="btn btn-sm btn-success" onclick="updateVersion('rrb')">
                提交
                <i class="icon-arrow-right icon-on-right bigger-110"></i>
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>


  </div>

</div>
</body>
</html>
