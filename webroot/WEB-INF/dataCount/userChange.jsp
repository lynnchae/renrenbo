<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <meta charset="utf-8" />
  <title>用户数据分析</title>
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

  <!--引入数据用户分析js-->
  <script src="<%=basePath%>js/dataCount/userChange.js"></script>

</head>

<body>
<div class="navbar navbar-default" id="navbar">
  <script type="text/javascript">
    try{ace.settings.check('navbar' , 'fixed')}catch(e){}
  </script>


  <div class="main-container" id="main-container">


      <div class="tabbable">
              <ul class="nav nav-tabs padding-12 tab-color-blue background-blue" id="userAnalysisTab">
                <li class="active">
                  <a data-toggle="tab" href="#day">一天</a>
                </li>

                <li>
                  <a data-toggle="tab" href="#week">一周</a>
                </li>

                <li>
                  <a data-toggle="tab" href="#month">一月</a>
                </li>
              </ul>
              <div class="tab-content" style="height:380px;">
                <div id="day" name="day" class="tab-pane in active">
                  <div class="col-sm-12 form-group">
                    <h3 class="header smaller lighter green">人人播 ：</h3>
                    <div class="infobox infobox-green  ">
                      <div class="infobox-icon">
                        <i class="icon-user"></i>
                      </div>

                      <div class="infobox-data">
                        <span class="infobox-data-number" id="dayNumberRrb"></span>
                        <div class="infobox-content">新增用户</div>
                      </div>
                      <!--<div class="stat stat-success">8%</div>-->
                    </div>

                    <div class="infobox infobox-blue  ">
                      <div class="infobox-icon">
                        <i class="icon-apple"></i>
                      </div>

                      <div class="infobox-data">
                        <span class="infobox-data-number" id="dayRetentionRrb"></span>
                        <div class="infobox-content">留存率</div>
                      </div>

                      <!--<div class="badge badge-success">
                        +32%
                        <i class="icon-arrow-up"></i>
                      </div>-->
                    </div>

                    <div class="infobox infobox-pink  ">
                      <div class="infobox-icon">
                        <i class="icon-user"></i>
                      </div>

                      <div class="infobox-data">
                        <span class="infobox-data-number" id="dayActiveRrb"></span>
                        <div class="infobox-content">活跃用户</div>
                      </div>
                      <!--<div class="stat stat-success">8%</div>-->
                    </div>

                    <div class="infobox infobox-red  ">
                      <div class="infobox-icon">
                        <i class="icon-user"></i>
                      </div>

                      <div class="infobox-data">
                        <span class="infobox-data-number" id="dayOverallRrb"></span>
                        <div class="infobox-content">总用户</div>
                      </div>

                      <!--<div class="badge badge-success">
                        +32%
                        <i class="icon-arrow-up"></i>
                      </div>-->
                    </div><br><br>

                    <h3 class="header smaller lighter green">FM ：</h3>
                    <div class="infobox infobox-green  ">
                      <div class="infobox-icon">
                        <i class="icon-user"></i>
                      </div>

                      <div class="infobox-data">
                        <span class="infobox-data-number" id="dayNumberFm"></span>
                        <div class="infobox-content">新增用户</div>
                      </div>
                      <!--<div class="stat stat-success">8%</div>-->
                    </div>

                    <div class="infobox infobox-blue  ">
                      <div class="infobox-icon">
                        <i class="icon-apple"></i>
                      </div>

                      <div class="infobox-data">
                        <span class="infobox-data-number" id="dayRetentionFm"></span>
                        <div class="infobox-content">留存率</div>
                      </div>

                      <!--<div class="badge badge-success">
                        +32%
                        <i class="icon-arrow-up"></i>
                      </div>-->
                    </div>
                    <div class="infobox infobox-pink  ">
                      <div class="infobox-icon">
                        <i class="icon-user"></i>
                      </div>

                      <div class="infobox-data">
                        <span class="infobox-data-number" id="dayActiveFm"></span>
                        <div class="infobox-content">活跃用户</div>
                      </div>
                      <!--<div class="stat stat-success">8%</div>-->
                    </div>

                    <div class="infobox infobox-red  ">
                      <div class="infobox-icon">
                        <i class="icon-user"></i>
                      </div>

                      <div class="infobox-data">
                        <span class="infobox-data-number" id="dayOverallFm"></span>
                        <div class="infobox-content">总用户</div>
                      </div>

                      <!--<div class="badge badge-success">
                        +32%
                        <i class="icon-arrow-up"></i>
                      </div>-->
                    </div>

                    </div>
                  </div>


                  <div id="week" name="week" class="tab-pane">
                    <div class="col-sm-12 form-group">
                      <h3 class="header smaller lighter green">人人播 ：</h3>
                      <div class="infobox infobox-green  ">
                        <div class="infobox-icon">
                          <i class="icon-user"></i>
                        </div>

                        <div class="infobox-data">
                          <span class="infobox-data-number" id="weekNumberRrb"></span>
                          <div class="infobox-content">新增用户</div>
                        </div>
                        <!--<div class="stat stat-success">8%</div>-->
                      </div>

                      <div class="infobox infobox-blue  ">
                        <div class="infobox-icon">
                          <i class="icon-apple"></i>
                        </div>

                        <div class="infobox-data">
                          <span class="infobox-data-number" id="weekRetentionRrb"></span>
                          <div class="infobox-content">留存率</div>
                        </div>

                        <!--<div class="badge badge-success">
                          +32%
                          <i class="icon-arrow-up"></i>
                        </div>-->
                      </div>

                      <div class="infobox infobox-pink  ">
                        <div class="infobox-icon">
                          <i class="icon-user"></i>
                        </div>

                        <div class="infobox-data">
                          <span class="infobox-data-number" id="weekActiveRrb"></span>
                          <div class="infobox-content">活跃用户</div>
                        </div>
                        <!--<div class="stat stat-success">8%</div>-->
                      </div>

                      <div class="infobox infobox-red  ">
                        <div class="infobox-icon">
                          <i class="icon-user"></i>
                        </div>

                        <div class="infobox-data">
                          <span class="infobox-data-number" id="weekOverallRrb"></span>
                          <div class="infobox-content">总用户</div>
                        </div>

                        <!--<div class="badge badge-success">
                          +32%
                          <i class="icon-arrow-up"></i>
                        </div>-->
                      </div><br><br>

                      <h3 class="header smaller lighter green">FM ：</h3>
                      <div class="infobox infobox-green  ">
                        <div class="infobox-icon">
                          <i class="icon-user"></i>
                        </div>

                        <div class="infobox-data">
                          <span class="infobox-data-number" id="weekNumberFm"></span>
                          <div class="infobox-content">新增用户</div>
                        </div>
                        <!--<div class="stat stat-success">8%</div>-->
                      </div>

                      <div class="infobox infobox-blue  ">
                        <div class="infobox-icon">
                          <i class="icon-apple"></i>
                        </div>

                        <div class="infobox-data">
                          <span class="infobox-data-number" id="weekRetentionFm"></span>
                          <div class="infobox-content">留存率</div>
                        </div>

                        <!--<div class="badge badge-success">
                          +32%
                          <i class="icon-arrow-up"></i>
                        </div>-->
                      </div>
                      <div class="infobox infobox-pink  ">
                        <div class="infobox-icon">
                          <i class="icon-user"></i>
                        </div>

                        <div class="infobox-data">
                          <span class="infobox-data-number" id="weekActiveFm"></span>
                          <div class="infobox-content">活跃用户</div>
                        </div>
                        <!--<div class="stat stat-success">8%</div>-->
                      </div>

                      <div class="infobox infobox-red  ">
                        <div class="infobox-icon">
                          <i class="icon-user"></i>
                        </div>

                        <div class="infobox-data">
                          <span class="infobox-data-number" id="weekOverallFm"></span>
                          <div class="infobox-content">总用户</div>
                        </div>

                        <!--<div class="badge badge-success">
                          +32%
                          <i class="icon-arrow-up"></i>
                        </div>-->
                      </div>

                    </div>
                  </div>

                  <div id="month" name="month" class="tab-pane">
                    <div class="col-sm-12 form-group">
                      <h3 class="header smaller lighter green">人人播 ：</h3>
                      <div class="infobox infobox-green  ">
                        <div class="infobox-icon">
                          <i class="icon-user"></i>
                        </div>

                        <div class="infobox-data">
                          <span class="infobox-data-number" id="monthNumberRrb"></span>
                          <div class="infobox-content">新增用户</div>
                        </div>
                        <!--<div class="stat stat-success">8%</div>-->
                      </div>

                      <div class="infobox infobox-blue  ">
                        <div class="infobox-icon">
                          <i class="icon-apple"></i>
                        </div>

                        <div class="infobox-data">
                          <span class="infobox-data-number" id="monthRetentionRrb"></span>
                          <div class="infobox-content">留存率</div>
                        </div>

                        <!--<div class="badge badge-success">
                          +32%
                          <i class="icon-arrow-up"></i>
                        </div>-->
                      </div>

                      <div class="infobox infobox-pink  ">
                        <div class="infobox-icon">
                          <i class="icon-user"></i>
                        </div>

                        <div class="infobox-data">
                          <span class="infobox-data-number" id="monthActiveRrb"></span>
                          <div class="infobox-content">活跃用户</div>
                        </div>
                        <!--<div class="stat stat-success">8%</div>-->
                      </div>

                      <div class="infobox infobox-red  ">
                        <div class="infobox-icon">
                          <i class="icon-user"></i>
                        </div>

                        <div class="infobox-data">
                          <span class="infobox-data-number" id="monthOverallRrb"></span>
                          <div class="infobox-content">总用户</div>
                        </div>

                        <!--<div class="badge badge-success">
                          +32%
                          <i class="icon-arrow-up"></i>
                        </div>-->
                      </div><br><br>

                      <h3 class="header smaller lighter green">FM ：</h3>
                      <div class="infobox infobox-green  ">
                        <div class="infobox-icon">
                          <i class="icon-user"></i>
                        </div>

                        <div class="infobox-data">
                          <span class="infobox-data-number" id="monthNumberFm"></span>
                          <div class="infobox-content">新增用户</div>
                        </div>
                        <!--<div class="stat stat-success">8%</div>-->
                      </div>

                      <div class="infobox infobox-blue  ">
                        <div class="infobox-icon">
                          <i class="icon-apple"></i>
                        </div>

                        <div class="infobox-data">
                          <span class="infobox-data-number" id="monthRetentionFm"></span>
                          <div class="infobox-content">留存率</div>
                        </div>

                        <!--<div class="badge badge-success">
                          +32%
                          <i class="icon-arrow-up"></i>
                        </div>-->
                      </div>
                      <div class="infobox infobox-pink  ">
                        <div class="infobox-icon">
                          <i class="icon-user"></i>
                        </div>

                        <div class="infobox-data">
                          <span class="infobox-data-number" id="monthActiveFm"></span>
                          <div class="infobox-content">活跃用户</div>
                        </div>
                        <!--<div class="stat stat-success">8%</div>-->
                      </div>

                      <div class="infobox infobox-red  ">
                        <div class="infobox-icon">
                          <i class="icon-user"></i>
                        </div>

                        <div class="infobox-data">
                          <span class="infobox-data-number" id="monthOverallFm"></span>
                          <div class="infobox-content">总用户</div>
                        </div>

                        <!--<div class="badge badge-success">
                          +32%
                          <i class="icon-arrow-up"></i>
                        </div>-->
                      </div>

                    </div>
                  </div>
              </div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>
