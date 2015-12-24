<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<%
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	    %>
		<meta charset="utf-8" />
		<title>FM，人人播后台管理系统</title>
	
		<!-- basic styles -->

		<link href="<%=basePath%>assets/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="<%=basePath%>assets/css/font-awesome.min.css" />

		<!--[if IE 7]>
		  <link rel="stylesheet" href="<%=basePath%>assets/css/font-awesome-ie7.min.css" />
		<![endif]-->

		<!-- page specific plugin styles -->

		<!-- fonts -->

		<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" />

		<!-- ace styles -->

		<link rel="stylesheet" href="<%=basePath%>assets/css/ace.min.css" />
		<link rel="stylesheet" href="<%=basePath%>assets/css/ace-rtl.min.css" />
		<link rel="stylesheet" href="<%=basePath%>assets/css/ace-skins.min.css" />

		<!--[if lte IE 8]>
		  <link rel="stylesheet" href="<%=basePath%>assets/css/ace-ie.min.css" />
		<![endif]-->

		<!-- inline styles related to this page -->

		<script src='<%=basePath%>js/index.js'></script>

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

		<!-- page specific plugin scripts -->

		<!-- ace scripts -->

		<script src="<%=basePath%>assets/js/ace-elements.min.js"></script>
		<script src="<%=basePath%>assets/js/ace.min.js"></script>

		<!-- inline scripts related to this page -->


		<style>
			.center {
			text-align:center;
		   }
		  .center [class*="col-"] {
			margin-top:2px;
			margin-bottom:2px;
			padding-top:4px;
			padding-bottom:4px;

			position:relative;
			text-overflow:ellipsis;
		  } 
		.center [class*="col-"]  span{
		  position:relative;
		  z-index:2;
			
		  display: inline-block;
		  overflow: hidden;
		  text-overflow: ellipsis;
		  white-space: nowrap;

		  width: 100%;
		}
		.center [class*="col-"]:before {
			position:absolute;
			top:0; bottom:0;
			left:2px; right:2px;
			content:"";
			display:block;
			border:1px solid #DDD;
			z-index: 1;
		}

		.center [class*="col-"]:hover:before {
			background-color:#FCE6A6;
			border-color:#EFD27A;
		}
		</style>

		<!-- ace settings handler -->

		<script src="<%=basePath%>assets/js/ace-extra.min.js"></script>

		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
		<script src="<%=basePath%>assets/js/html5shiv.js"></script>
		<script src="<%=basePath%>assets/js/respond.min.js"></script>
		<![endif]-->

		<!--引入初始化js-->
		<script src="http://localhost:8080/renrenfm/js/index.js"></script>

	</head>

	<body>
		<div class="navbar navbar-default" id="navbar">
			<script type="text/javascript">
				try{ace.settings.check('navbar' , 'fixed')}catch(e){}
			</script>

			<div class="navbar-container" id="navbar-container">
				<div class="navbar-header pull-left">
					<a href="#" class="navbar-brand">
						<small>
							<i class="icon-leaf"></i>
							FM，人人播后台管理系统
						</small>
					</a><!-- /.brand -->
				</div><!-- /.navbar-header -->

				<div class="navbar-header pull-right" role="navigation">
					<ul class="nav ace-nav">
						
						<li class="light-blue">
							<a data-toggle="dropdown" href="#" class="dropdown-toggle">
								<img class="nav-user-photo" src="<%=basePath%>assets/avatars/user.jpg" alt="Jason's Photo" />
								<span class="user-info">
									<small>欢迎,${sessionScope.user.userName}</small>
								</span>
								<i class="icon-caret-down"></i>
							</a>

							<ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">

								<li>
									<a href="<%=basePath%>sys/login" >
										<i class="icon-off"></i>
										退出
									</a>
								</li>
							</ul>
						</li>
					</ul><!-- /.ace-nav -->
				</div><!-- /.navbar-header -->
			</div><!-- /.container -->
		</div>



		
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>

			<div class="main-container-inner">
				<a class="menu-toggler" id="menu-toggler" href="#">
					<span class="menu-text"></span>
				</a>

				<div class="sidebar" id="sidebar">
					<script type="text/javascript">
						try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
					</script>

					<ul class="nav nav-list">
						<li>
							<a href="#" class="dropdown-toggle">
								<i class="icon-desktop"></i>
								<span class="menu-text">主播管理</span>

								<b class="arrow icon-angle-down"></b>
							</a>

							<ul class="submenu">
								<li>
									<%--<a href="<%=basePath%>author/allAuthor.do?"+ Math.random(), target="includePage">
										<i class="icon-double-angle-right"></i>
										查询所有主播
									</a>--%>

									<a href="<%=basePath%>accountInfo/accountIndex?"+ Math.random(), target="includePage" onclick="setNavigationBar('主播管理',this)">
										<i class="icon-double-angle-right"></i>
										主播认证
									</a>
									<a href="<%=basePath%>accountInfo/accusationIndex?"+ Math.random(), target="includePage" onclick="setNavigationBar('主播管理',this)">
										<i class="icon-double-angle-right"></i>
										举报管理
									</a>
									<a href="<%=basePath%>accountInfo/appealIndex?"+ Math.random(), target="includePage" onclick="setNavigationBar('主播管理',this)">
											<i class="icon-double-angle-right"></i>
										申诉管理
									</a>
								</li>
							</ul>
						</li>

						<li>
							<a href="#" class="dropdown-toggle">
								<i class="icon-desktop"></i>
								<span class="menu-text">听众管理</span>

								<b class="arrow icon-angle-down"></b>
							</a>

							<ul class="submenu">
								<li>
									<a href="<%=basePath%>listener/listenerAccountIndex?"+ Math.random(), target="includePage" onclick="setNavigationBar('听众管理',this)">
										<i class="icon-double-angle-right"></i>
										听众列表
									</a>
								</li>
							</ul>
						</li>

						<li>
							<a href="#" class="dropdown-toggle">
								<i class="icon-desktop"></i>
								<span class="menu-text">礼物管理</span>

								<b class="arrow icon-angle-down"></b>
							</a>

							<ul class="submenu">
								<li>
									<a href="<%=basePath%>box/boxIndex?"+ Math.random(), target="includePage" onclick="setNavigationBar('礼物管理',this)">
										<i class="icon-double-angle-right"></i>
										宝箱管理
									</a>

									<a href="<%=basePath%>prize/prizeIndex?"+ Math.random(), target="includePage" onclick="setNavigationBar('礼物管理',this)">
										<i class="icon-double-angle-right"></i>
										实体礼物管理
									</a>

									<a href="<%=basePath%>present/presentIndex?"+ Math.random(), target="includePage" onclick="setNavigationBar('礼物管理',this)">
										<i class="icon-double-angle-right"></i>
										虚拟礼物管理
									</a>
								</li>
							</ul>
						</li>


						<li>
							<a href="#" class="dropdown-toggle" onclick="setNavigationBar('系统管理',null)">
								<i class="icon-desktop"></i>
								<span class="menu-text">系统管理</span>
								<b class="arrow icon-angle-down"></b>
							</a>

							<ul class="submenu">
								<li>
									<a href="<%=basePath%>roadConditionPercent/getIndex?"+ Math.random(), target="includePage" onclick="setNavigationBar('系统管理',this)">
										<i class="icon-double-angle-right"></i>
										路况拥堵指标
									</a>
								</li>

								<li>
									<%--<a href="<%=basePath%>#"+ Math.random(), target="includePage">
										<i class="icon-double-angle-right"></i>
										系统参数配置
									</a>--%>
								</li>

								<li>
									<a href="<%=basePath%>sysNotice/getIndex"+ Math.random(), target="includePage" onclick="setNavigationBar('系统管理',this)">
										<i class="icon-double-angle-right"></i>
										系统公告
									</a>
								</li>

								<li>
									<%--<a href="<%=basePath%>sys/getVersion"+ Math.random(), target="includePage">
										<i class="icon-double-angle-right"></i>
										版本更新
									</a>--%>
								</li>

								<li>
									<a href="<%=basePath%>sysConfig/getIndex?"+ Math.random(), target="includePage" onclick="setNavigationBar('系统管理',this)">
										<i class="icon-double-angle-right"></i>
										APP审核配置
									</a>
								</li>

							</ul>

						</li>

						<li>
							<a href="#" class="dropdown-toggle">
								<i class="icon-desktop"></i>
								<span class="menu-text">财务管理</span>

								<b class="arrow icon-angle-down"></b>
							</a>

							<ul class="submenu">
								<li>
									<a href="<%=basePath%>finance/withdrawalsQueryIndex?"+ Math.random(), target="includePage" onclick="setNavigationBar('财务管理',this)">
										<i class="icon-double-angle-right"></i>
										提现记录查询
									</a>
									<a href="<%=basePath%>finance/withdrawalsOperationIndex?"+ Math.random(), target="includePage" onclick="setNavigationBar('财务管理',this)">
										<i class="icon-double-angle-right"></i>
										提现记录操作
									</a>
									<a href="<%=basePath%>recharge/rechargeIndex?"+ Math.random(), target="includePage" onclick="setNavigationBar('数据统计',this)">
										<i class="icon-double-angle-right"></i>
										充值列表
									</a>
									<a href="<%=basePath%>recharge/payCallBackIndex?"+ Math.random(), target="includePage" onclick="setNavigationBar('数据统计',this)">
										<i class="icon-double-angle-right"></i>
										充值回调记录
									</a>
									<a href="<%=basePath%>reward/rewardIndex?"+ Math.random(), target="includePage" onclick="setNavigationBar('数据统计',this)">
										<i class="icon-double-angle-right"></i>
										打赏记录
									</a>
									<a href="<%=basePath%>gold/sendGoldIndex?"+ Math.random(), target="includePage" onclick="setNavigationBar('数据统计',this)">
										<i class="icon-double-angle-right"></i>
										活动领取金币
									</a>
								</li>
							</ul>
						</li>

						<li>
							<a href="#" class="dropdown-toggle">
								<i class="icon-desktop"></i>
								<span class="menu-text">活动管理</span>

								<b class="arrow icon-angle-down"></b>
							</a>

							<ul class="submenu">
								<li>
									<a href="<%=basePath%>weme/wemeCoinsIndex?"+ Math.random(), target="includePage" onclick="setNavigationBar('活动管理',this)">
										<i class="icon-double-angle-right"></i>
										微密活动
									</a>
								</li>
								<li>
									<a href="<%=basePath%>activity/getIndex?"+ Math.random(), target="includePage" onclick="setNavigationBar('活动管理',this)">
										<i class="icon-double-angle-right"></i>
										运营活动管理
									</a>
								</li>
							</ul>

						</li>

						<li>
							<a href="#" class="dropdown-toggle">
								<i class="icon-desktop"></i>
								<span class="menu-text">数据统计</span>

								<b class="arrow icon-angle-down"></b>
							</a>

							<ul class="submenu">
								<li>
									<a href="<%=basePath%>dataCount/userChangeIndex?"+ Math.random(), target="includePage" onclick="setNavigationBar('数据统计',this)">
										<i class="icon-double-angle-right"></i>
										用户数据分析
									</a>
									<a href="<%=basePath%>dataCount/anchorTotalIndex?"+ Math.random(), target="includePage" onclick="setNavigationBar('数据统计',this)">
										<i class="icon-double-angle-right"></i>
										主播数据总统计
									</a>
									<a href="<%=basePath%>dataCount/singleAnchorIndex?"+ Math.random(), target="includePage" onclick="setNavigationBar('数据统计',this)">
										<i class="icon-double-angle-right"></i>
										单个主播数据统计
									</a>
									<a href="<%=basePath%>dataCount/listenerTotalIndex?"+ Math.random(), target="includePage" onclick="setNavigationBar('主播管理',this)">
										<i class="icon-double-angle-right"></i>
										听众数据总统计
									</a>
									<a href="<%=basePath%>dataCount/singleFansIndex?"+ Math.random(), target="includePage" onclick="setNavigationBar('数据统计',this)">
										<i class="icon-double-angle-right"></i>
										单个粉丝数据统计
									</a>
									<a href="<%=basePath%>dataCount/openBoxDataCountIndex?"+ Math.random(), target="includePage" onclick="setNavigationBar('数据统计',this)">
										<i class="icon-double-angle-right"></i>
										开箱数据统计
									</a>
									<a href="<%=basePath%>dataCount/presentDataCountIndex?"+ Math.random(), target="includePage" onclick="setNavigationBar('数据统计',this)">
										<i class="icon-double-angle-right"></i>
										礼物数据统计
									</a>
									<a href="<%=basePath%>dataCount/presentReceiveCountIndex?"+ Math.random(), target="includePage" onclick="setNavigationBar('数据统计',this)">
										<i class="icon-double-angle-right"></i>
										礼品领取数据统计
									</a>
									<a href="<%=basePath%>dataCount/openBoxReceiveCountIndex?"+ Math.random(), target="includePage" onclick="setNavigationBar('数据统计',this)">
										<i class="icon-double-angle-right"></i>
										开箱领取数据统计
									</a>
									<a href="<%=basePath%>listenerOrder/listenerOrderIndex?"+ Math.random(), target="includePage" onclick="setNavigationBar('数据统计',this)">
										<i class="icon-double-angle-right"></i>
										开箱记录
									</a>
								</li>
							</ul>
						</li>

					</ul><!-- /.nav-list -->


					<div class="sidebar-collapse" id="sidebar-collapse">
						<i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
					</div>

					<script type="text/javascript">
						try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
					</script>
				</div>

				<div class="main-content">

					<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
						</script>

						<ul class="breadcrumb">
							<li>
								<i class="icon-home home-icon"></i>
								<a name="homePage" id="homePage" href="#">主页</a>
							</li>
							<li>
								<a name="twoLevel" id="twoLevel" href="#"></a>
							</li>
							<li name="threeLevel" id="threeLevel" class="active"></li>
						</ul>
					</div>

					<div class="page-content"><!-- border:0 -->
						<iframe name="includePage" frameborder="0px"   noresize="noresize" src="<%=basePath%>sys/calendar"
								scrolling="no" style="border: 0px solid red;background: white" width="100%;" height="1000px"></iframe>
					</div>

				</div>

				<!-- /.main-content -->
			</div><!-- /.main-container-inner -->
		</div><!-- /.main-container -->

	
</body>
</html>
