
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8" />
  <title>实体礼物管理</title>
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
    <link rel="stylesheet" href="<%=basePath%>css/dialog.css" />

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


    <!--引入礼物界面的js-->
    <script src="<%=basePath%>js/present/presentInfo.js"></script>
    <!--引入图片上传的js-->
    <script src="<%=basePath%>js/uploadImg/uploadImg.js"></script>
    <!--引入验证的js-->
    <script src="<%=basePath%>js/util/validate.js"></script>
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

    <div id="dialog-message" class="row hide">
        <div class="col-xs-12">
            <form class="form-horizontal" role="form">
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="dialog_name"> 礼物名：</label>
                    <div class="col-sm-9">
                        <input type="hidden" id="dialog_createTime"/>
                        <input type="text" id="dialog_name" placeholder="礼物名" class="col-xs-10 col-sm-5" autocomplete="off" />
                        <div class="help-block col-xs-12 col-sm-reset inline name_message" style="color:red;"></div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="dialog_pic"> 图片：</label>
                    <div class="col-sm-9">
                        <img id="dialog_pic"  style='max-width:80px;' src=""/>
                        <div class="help-block col-xs-12 col-sm-reset inline name_message" style="color:red;"></div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="dialog_pic">更换图片：</label>
                    <div class="col-sm-9">
                        <input type="file" id="editFile" name="file" value="请选择文件" style="width:180px;display:inline"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="dialog_type">类型：</label>
                    <div class="col-sm-9">
                        <select id="dialog_type" class="dialog_type">
                            <option value="0">免费领取</option>
                            <option value="1">花钱购买</option>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="dialog_denseNum">价格（金币）：</label>
                    <div class="col-sm-9">
                        <input id="dialog_denseNum" type="text" class="denseNum"
                               onkeypress = "return vaildIntegerNumber(event) "
                               onpaste = "return !clipboardData.getData('text').match(/\D/) "
                               ondragenter = "return false "
                               style = "ime-mode:Disabled "
                               placeholder="请输入数字"/>
                        <span class="denseNum_span" >0</span>
                        <div class="help-block col-xs-12 col-sm-reset inline dense_message" style="color:red;"></div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="dialog_diamond">钻数：</label>
                    <div class="col-sm-9">
                        <input id="dialog_diamond" type="text" class="diamond"
                               onkeypress = "return vaildIntegerNumber(event) "
                               onpaste = "return !clipboardData.getData('text').match(/\D/) "
                               ondragenter = "return false "
                               style = "ime-mode:Disabled "
                               placeholder="请输入数字"/>
                        <span class="diamond_span" >0</span>
                        <div class="help-block col-xs-12 col-sm-reset inline diamond_message" style="color:red;"></div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="dialog_popularityVal">人气值：</label>
                    <div class="col-sm-9">
                        <input id="dialog_popularityVal" type="text" class="popularityVal"
                               onkeypress = "return vaildIntegerNumber(event) "
                               onpaste = "return !clipboardData.getData('text').match(/\D/) "
                               ondragenter = "return false "
                               style = "ime-mode:Disabled; "
                               placeholder="请输入数字"/>
                        <span class="popularityVal_span" >0</span>
                        <div class="help-block col-xs-12 col-sm-reset inline popularityVal_message" style="color:red;"></div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="dialog_frequencyNum">频率：</label>
                    <div class="col-sm-9">
                        <input id="dialog_frequencyNum" type="text" class="frequencyNum"
                               onkeypress = "return vaildIntegerNumber(event) "
                               onpaste = "return !clipboardData.getData('text').match(/\D/) "
                               ondragenter = "return false "
                               style = "ime-mode:Disabled; "
                               placeholder="请输入数字"/>
                        <span class="frequencyNum_span">0</span>
                        <div class="help-block col-xs-12 col-sm-reset inline frequencyNum_message" style="color:red;"></div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="dialog_frequencyNum">呈现方式：</label>
                    <div class="col-sm-9">
                        <select id="dialog_appearType" >
                            <option value="0">没有效果</option>
                            <option value="1">放大</option>
                            <option value="2">平移跳动</option>
                            <option value="3">旋转</option>
                            <option value="4">翻转</option>
                            <option value="5">曲线运动</option>
                        </select>
                    </div>
                </div>

            </form>
        </div>
    </div><!-- #dialog-message -->

    <div class="row hide" id="dialog-message-add">
        <div class="col-xs-12">
            <form class="form-horizontal" role="form">

                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="add_name">礼物名：</label>
                        <div class="col-sm-9">
                            <input type="text" id="add_name" name="add_name" placeholder="礼品名" class="col-xs-10 col-sm-5" />
                            <div class="help-block col-xs-12 col-sm-reset inline name_message" style="color:red;"></div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="addFile">图片：</label>
                        <div class="col-sm-9">
                            <input type="file" id="addFile" name="file" style="width:180px;display:inline" class="col-xs-10 col-sm-5"/>
                            <div class="help-block col-xs-12 col-sm-reset inline img_message" style="color:red;"></div>
                        </div>
                     </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="add_type">类型：</label>
                    <div class="col-sm-9">
                        <select id="add_type">
                            <option value="0">免费领取</option>
                            <option value="1">花钱购买</option>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="add_denseNum">价格(金币)：</label>
                    <div class="col-sm-9">
                        <input id="add_denseNum" name="add_denseNum" type="text" class="denseNum"
                               onkeypress = "return vaildIntegerNumber(event) "
                               onpaste = "return !clipboardData.getData('text').match(/\D/) "
                               ondragenter = "return false "
                               style = "ime-mode:Disabled "
                               placeholder="请输入数字"/>
                        <span class="denseNum_span" >0</span>
                        <div class="help-block col-xs-12 col-sm-reset inline dense_message" style="color:red;"></div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="add_denseNum">钻数：</label>
                    <div class="col-sm-9">
                        <input id="add_diamond" name="add_diamond" type="text" class="diamond"
                               onkeypress = "return vaildIntegerNumber(event) "
                               onpaste = "return !clipboardData.getData('text').match(/\D/) "
                               ondragenter = "return false "
                               style = "ime-mode:Disabled "
                               placeholder="请输入数字"/>
                        <span class="diamond_span" >0</span>
                        <div class="help-block col-xs-12 col-sm-reset inline diamond_message" style="color:red;"></div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="add_popularityVal">人气值：</label>
                    <div class="col-sm-9">
                        <input id="add_popularityVal" type="text" class="popularityVal"
                               onkeypress = "return vaildIntegerNumber(event) "
                               onpaste = "return !clipboardData.getData('text').match(/\D/) "
                               ondragenter = "return false "
                               style = "ime-mode:Disabled "
                               placeholder="请输入数字"/>
                        <span class="popularityVal_span" >0</span>
                        <div class="help-block col-xs-12 col-sm-reset inline popularityVal_message" style="color:red;"></div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="add_frequencyNum">领取频率：</label>
                    <div class="col-sm-9">
                        <input id="add_frequencyNum" type="text" class="frequencyNum"
                               onkeypress = "return vaildIntegerNumber(event) "
                               onpaste = "return !clipboardData.getData('text').match(/\D/) "
                               ondragenter = "return false "
                               style = "ime-mode:Disabled "
                               placeholder="请输入数字"/>
                        <span class="frequencyNum_span" >0</span>
                        <div class="help-block col-xs-12 col-sm-reset inline frequencyNum_message" style="color:red;"></div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="add_frequencyNum">呈现方式：</label>
                    <div class="col-sm-9">
                        <select id="add_appearType">
                            <option value="0">没有效果</option>
                            <option value="1">放大</option>
                            <option value="2">平移跳动</option>
                            <option value="3">旋转</option>
                            <option value="4">翻转</option>
                            <option value="5">曲线运动</option>
                        </select>
                    </div>
                </div>

            </form>
        </div>
    </div>

    <div class="col-xs-12">

      <form class="form-inline">
          <label>礼物名称：</label> <input type="text" id="name"  name ="name" class="input-medium" placeholder="name" />
          <label style="margin-left: 10px;">礼物类型：</label>
          <select class="select2-choice" name="type" id="type">
            <option value="-1">---类型---</option>
            <option value="0">免费领取</option>
            <option value="1">花钱购买</option>
          </select>
          <label style="margin-left: 10px;">呈现类型：</label>
          <select class="select2-choice" name="appearType" id="appearType">
              <option value="-1">---类型---</option>
              <option value="0">没有效果</option>
              <option value="1">放大</option>
              <option value="2">平移跳动</option>
              <option value="3">旋转</option>
              <option value="4">翻转</option>
              <option value="5">曲线运动</option>
          </select>
          <%--<label>呈现方式：</label> <input type="appearType" id="appearType" name="appearType" class="input-small" placeholder="appearType" />--%>

          <button type="button"  class="btn btn-purple btn-sm" onclick="search()">
              Search
              <i class="icon-search icon-on-right bigger-110"></i>
          </button>

      </form>

      <table id="grid-table" style="font-size: 12px"></table>
      <div id="grid-pager"></div>

  </div>
</div><!-- /.main-container -->




</div>
</body>
</html>
