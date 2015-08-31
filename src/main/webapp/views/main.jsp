<%@ page language="java" import="java.util.*,java.util.HashMap" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@ include file="path.jsp"%>
<title>统一认证管理平台</title>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="统一认证管理平台,数据挖掘,数据分析" />
<meta http-equiv="description" content="控制面板" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
String userName = (String)request.getSession().getAttribute("userName");
 %>
 
<link rel="stylesheet" type="text/css" href="${cdnPath}static/assets/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="${cdnPath}static/assets/font-awesome/css/font-awesome.min.css" />
<link rel="stylesheet" type="text/css" href="${cdnPath}static/assets/css/ace.min-bak.css" id="main-ace-style" />
<!--[if lte IE 9]>
<link rel="stylesheet" type="text/css" href="${cdnPath}static/assets/css/ace-part2.min.css" />
<![endif]-->
<link rel="stylesheet" type="text/css" href="${cdnPath}static/assets/css/ace-skins.min.css" />
<link rel="stylesheet" type="text/css" href="${cdnPath}static/assets/css/ace-rtl.min.css" />
<link rel="stylesheet" type="text/css" href="${sysPath}static/assets/css/jquery.gritter.css" />
<!--[if lte IE 9]>
		  <link rel="stylesheet" href="${cdnPath}static/assets/css/ace-ie.min.css" />
<![endif]-->

<script type="text/javascript" src="${cdnPath}static/assets/js/ace-extra.min.js"></script>
<!--[if lte IE 8]>
		<script src="${cdnPath}static/assets/js/html5shiv.min.js"></script>
		<script src="${cdnPath}static/assets/js/respond.min.js"></script>
<![endif]-->
</head>
<body class="no-skin">
	<div id="navbar" class="navbar navbar-default">
			<script type="text/javascript">
			try{ace.settings.check('navbar' , 'fixed')}catch(e){}
			</script>

			<div class="navbar-container" id="navbar-container">
				<button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler">
					<span class="sr-only">Toggle sidebar</span>

					<span class="icon-bar"></span>

					<span class="icon-bar"></span>

					<span class="icon-bar"></span>
				</button>

				<div class="navbar-header pull-left">
					<a href="main" class="navbar-brand">
						<small>
							<i class="fa fa-leaf"></i>
							统一认证管理平台
						</small>
					</a>
				</div>
				
				<div class="navbar-buttons navbar-header pull-right" role="navigation">
					<ul class="nav ace-nav">
						<li class="grey">
							<a data-toggle="dropdown" class="dropdown-toggle" href="#">
								<i class="ace-icon fa fa-tasks"></i>
								<span class="badge badge-grey">4</span>
							</a>

							<ul class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
								<li class="dropdown-header">
									<i class="ace-icon fa fa-check"></i>
									4 Tasks to complete
								</li>

								<li>
									<a href="#">
										<div class="clearfix">
											<span class="pull-left">Software Update</span>
											<span class="pull-right">65%</span>
										</div>

										<div class="progress progress-mini">
											<div style="width:65%" class="progress-bar"></div>
										</div>
									</a>
								</li>

								<li>
									<a href="#">
										<div class="clearfix">
											<span class="pull-left">Hardware Upgrade</span>
											<span class="pull-right">35%</span>
										</div>

										<div class="progress progress-mini">
											<div style="width:35%" class="progress-bar progress-bar-danger"></div>
										</div>
									</a>
								</li>

								<li>
									<a href="#">
										<div class="clearfix">
											<span class="pull-left">Unit Testing</span>
											<span class="pull-right">15%</span>
										</div>

										<div class="progress progress-mini">
											<div style="width:15%" class="progress-bar progress-bar-warning"></div>
										</div>
									</a>
								</li>

								<li>
									<a href="#">
										<div class="clearfix">
											<span class="pull-left">Bug Fixes</span>
											<span class="pull-right">90%</span>
										</div>

										<div class="progress progress-mini progress-striped active">
											<div style="width:90%" class="progress-bar progress-bar-success"></div>
										</div>
									</a>
								</li>

								<li class="dropdown-footer">
									<a href="#">
										See tasks with details
										<i class="ace-icon fa fa-arrow-right"></i>
									</a>
								</li>
							</ul>
						</li>

						<li class="purple">
							<a data-toggle="dropdown" class="dropdown-toggle" href="#">
								<i class="ace-icon fa fa-bell icon-animated-bell"></i>
								<span class="badge badge-important">8</span>
							</a>

							<ul class="dropdown-menu-right dropdown-navbar navbar-pink dropdown-menu dropdown-caret dropdown-close">
								<li class="dropdown-header">
									<i class="ace-icon fa fa-exclamation-triangle"></i>
									8 Notifications
								</li>

								<li>
									<a href="#">
										<div class="clearfix">
											<span class="pull-left">
												<i class="btn btn-xs no-hover btn-pink fa fa-comment"></i>
												New Comments
											</span>
											<span class="pull-right badge badge-info">+12</span>
										</div>
									</a>
								</li>

								<li>
									<a href="#">
										<i class="btn btn-xs btn-primary fa fa-user"></i>
										Bob just signed up as an editor ...
									</a>
								</li>

								<li>
									<a href="#">
										<div class="clearfix">
											<span class="pull-left">
												<i class="btn btn-xs no-hover btn-success fa fa-shopping-cart"></i>
												New Orders
											</span>
											<span class="pull-right badge badge-success">+8</span>
										</div>
									</a>
								</li>

								<li>
									<a href="#">
										<div class="clearfix">
											<span class="pull-left">
												<i class="btn btn-xs no-hover btn-info fa fa-twitter"></i>
												Followers
											</span>
											<span class="pull-right badge badge-info">+11</span>
										</div>
									</a>
								</li>

								<li class="dropdown-footer">
									<a href="#">
										See all notifications
										<i class="ace-icon fa fa-arrow-right"></i>
									</a>
								</li>
							</ul>
						</li>

						<li class="green">
							<a data-toggle="dropdown" class="dropdown-toggle" href="#">
								<i class="ace-icon fa fa-envelope icon-animated-vertical"></i>
								<span class="badge badge-success">5</span>
							</a>

							<ul class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
								<li class="dropdown-header">
									<i class="ace-icon fa fa-envelope-o"></i>
									5 Messages
								</li>

								<li class="dropdown-content">
									<ul class="dropdown-menu dropdown-navbar">
										<li>
											<a href="#">
												<img src="static/assets/avatars/avatar.png" class="msg-photo" alt="Alex's Avatar" />
												<span class="msg-body">
													<span class="msg-title">
														<span class="blue">Alex:</span>
														Ciao sociis natoque penatibus et auctor ...
													</span>

													<span class="msg-time">
														<i class="ace-icon fa fa-clock-o"></i>
														<span>a moment ago</span>
													</span>
												</span>
											</a>
										</li>

										<li>
											<a href="#">
												<img src="static/assets/avatars/avatar3.png" class="msg-photo" alt="Susan's Avatar" />
												<span class="msg-body">
													<span class="msg-title">
														<span class="blue">Susan:</span>
														Vestibulum id ligula porta felis euismod ...
													</span>

													<span class="msg-time">
														<i class="ace-icon fa fa-clock-o"></i>
														<span>20 minutes ago</span>
													</span>
												</span>
											</a>
										</li>

										<li>
											<a href="#">
												<img src="static/assets/avatars/avatar4.png" class="msg-photo" alt="Bob's Avatar" />
												<span class="msg-body">
													<span class="msg-title">
														<span class="blue">Bob:</span>
														Nullam quis risus eget urna mollis ornare ...
													</span>

													<span class="msg-time">
														<i class="ace-icon fa fa-clock-o"></i>
														<span>3:15 pm</span>
													</span>
												</span>
											</a>
										</li>

										<li>
											<a href="#">
												<img src="static/assets/avatars/avatar2.png" class="msg-photo" alt="Kate's Avatar" />
												<span class="msg-body">
													<span class="msg-title">
														<span class="blue">Kate:</span>
														Ciao sociis natoque eget urna mollis ornare ...
													</span>

													<span class="msg-time">
														<i class="ace-icon fa fa-clock-o"></i>
														<span>1:33 pm</span>
													</span>
												</span>
											</a>
										</li>

										<li>
											<a href="#">
												<img src="static/assets/avatars/avatar5.png" class="msg-photo" alt="Fred's Avatar" />
												<span class="msg-body">
													<span class="msg-title">
														<span class="blue">Fred:</span>
														Vestibulum id penatibus et auctor  ...
													</span>

													<span class="msg-time">
														<i class="ace-icon fa fa-clock-o"></i>
														<span>10:09 am</span>
													</span>
												</span>
											</a>
										</li>
									</ul>
								</li>

								<li class="dropdown-footer">
									<a href="inbox.html">
										See all messages
										<i class="ace-icon fa fa-arrow-right"></i>
									</a>
								</li>
							</ul>
						</li>

						<li class="light-blue">
							<a data-toggle="dropdown" href="#" class="dropdown-toggle">
								<img class="nav-user-photo" src="static/assets/avatars/user.jpg" alt="${user.realName} Photo" />
								<span class="user-info">
									<small>欢迎,</small>
									${user.realName}
								</span>

								<i class="ace-icon fa fa-caret-down"></i>
							</a>

							<ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
								<li>
									<a href="#">
										<i class="ace-icon fa fa-cog"></i>
										设置
									</a>
								</li>

								<li>
									<a href="profile.html">
										<i class="ace-icon fa fa-user"></i>
										个人资料
									</a>
								</li>

								<li class="divider"></li>

								<li>
									<a id="ext-sys-power-off" href="javascript:void(0)">
										<i class="ace-icon fa fa-power-off"></i>
										退出登录
									</a>
								</li>
							</ul>
						</li>
					</ul>
				</div>
			</div><!-- /.navbar-container -->
		</div>

		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>
			<table id="grid-table"></table>
			<div id="grid-pager"></div>
			<div id="sidebar" class="sidebar                  responsive">
				<script type="text/javascript">
					try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
				</script>
				

				<ul  id="treeSidebar" class="nav nav-list">
					
				</ul><!-- /.nav-list -->

				<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
					<i class="ace-icon fa fa-angle-double-left" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
				</div>

				<script type="text/javascript">
					try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
				</script>
			</div>

			<div class="main-content">
				<div class="page-content">
				
					<div class="col-xs-12 clear_LeftRightPadding" id="FrameTabs">
					  <div class="tabbable">
                        <ul  id="myTab"  class="nav nav-tabs padding-10 tab-color-blue background-blue"  >
                      		 <li style="text-align:center;" menuname="xxxx" sel="tab_xxxx" id="li_xxx" class="hide home template">
											<a data-toggle="tab" href="#tab_xxx">
												<i class="green icon-home bigger-110"></i>
											</a>
							 </li>
					 	 	 <li style="text-align:center;" menuname="xxxx" sel="tab_xxxx" id="li_xxxx" class="hide template">
											<a data-toggle="tab" href="#tab_xxxx">
												<button onclick="frameTab.removeTab('xxx',true)" class="close smaller-90 position-absolute red tr2px" type="button">
													<i class="fa fa-remove"></i>
												</button>
											</a>
							 </li>
                        	 <li class="hide" id = "leftArrow" onclick="frameTab.showPrev();" style="width:20px;text-align:center;">
											<i class="fa fa-angle-double-left" style="height:34px;line-height:30px;font-size:18px;cursor:pointer;width:100%;"></i>
							 </li>
							 <li class="hide" id = "rightArrow" onclick="" style="width:20px;text-align:center;">
											<i class="fa fa-angle-double-right" style="height:34px;line-height:30px;font-size:18px;cursor:pointer;width:100%;"></i>
							 </li>
                            <li id="li_1257887" class="active" style="text-align: center; width: 89px;" sel="tab_1257887" menuname="1257887" outerwidth="89"><a href="#tab_1257887" data-toggle="tab"><i class="green ace-icon fa fa-home bigger-120"></i><span id="frameTabTitle">首页</span></a>
                            </li>
                        </ul>
                        <div id="myTabContext" class="tab-content">
                        <div id="tab_MainTemplate" class="hide tab-pane">
						<iframe frameborder="0" style="width: 100%;" id="mainFrame_titleXXX" name="ifm" src="" scrolling="no" onload="javascript:void(0);"></iframe>
						</div>
						<div id="tab_1257887" class="tab-pane active">				 
                         <iframe id="main_right" name="main_right" frameborder="0" scrolling="no" style="width: 100%;height: 100%; " src="index" tabid="1" onload="frameTab.adjustIFramesHeightOnLoad(this)"></iframe>
                         </div>
                        </div>
                    </div>
				</div>
				</div><!-- /.page-content -->
			</div><!-- /.main-content -->
			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->
		<!-- basic scripts -->
		
		<!--[if !IE]> -->
		<script type="text/javascript" src="${cdnPath}static/assets/js/jquery/jquery-2.1.1.min.js"></script>
		<!-- <![endif]-->

		<!--[if IE]>
		<script type="text/javascript" src="${cdnPath}static/assets/js/jquery/jquery-1.11.1.min.js"></script>
		<![endif]-->
		
		<!--[if !IE]> -->
		<script type="text/javascript">
			window.jQuery || document.write("<script src='${cdnPath}static/assets/js/jquery.min.js'>"+"<"+"/script>");
		</script>
		<!-- <![endif]-->
		
		<!--[if IE]>
		<script type="text/javascript">
		 window.jQuery || document.write("<script src='${cdnPath}static/assets/js/jquery1x.min.js'>"+"<"+"/script>");
		</script>
		<![endif]-->
		
		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='${cdnPath}static/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		
		<script src="${cdnPath}static/assets/bootstrap/3.2.0/js/bootstrap.min.js"></script>

		<!-- page specific plugin scripts -->

		<!--[if lte IE 8]>
		<script src="${cdnPath}static/assets/js/excanvas.min.js"></script>
		<![endif]-->
		<script type="text/javascript" src="${cdnPath}static/assets/js/jquery-ui.custom.min.js"></script>
		<script type="text/javascript" src="${cdnPath}static/assets/js/jquery.ui.touch-punch.min.js"></script>
		<script type="text/javascript" src="${cdnPath}static/assets/js/jquery.easypiechart.min.js"></script>
		<script type="text/javascript" src="${cdnPath}static/assets/js/jquery.sparkline.min.js"></script>
		<script type="text/javascript" src="${cdnPath}static/assets/js/flot/jquery.flot.min.js"></script>
		<script type="text/javascript" src="${cdnPath}static/assets/js/flot/jquery.flot.pie.min.js"></script>
		<script type="text/javascript" src="${cdnPath}static/assets/js/flot/jquery.flot.resize.min.js"></script>
		<script type="text/javascript" src="${sysPath}static/assets/js/jquery.gritter.min.js"></script>
		<script type="text/javascript" src="${cdnPath}static/js/FrameTab.js"></script>
		<!-- common-->
		<script type="text/javascript" src="${cdnPath}static/assets/js/layer/layer.js"></script>
		<script type="text/javascript" src="${cdnPath}static/js/util.js"></script>
		<script type="text/javascript" src="${cdnPath}static/assets/js/util.js"></script>
		<!-- ace scripts -->
		<script type="text/javascript" src="${cdnPath}static/assets/js/ace-elements.min.js"></script>
		<script type="text/javascript" src="${cdnPath}static/assets/js/ace.src.js"></script>
		<script type="text/javascript">
		jQuery(document).ready(function() {
			Share.showMenu();
			$("#ext-sys-power-off").on('click', function(e) {
			T.Box.Tips('操作提示','你确定要退出当前用户吗?',T.C.I.QuestionClass,T.C.I.QuestionIoc,T.C.I.style,true);
			e.preventDefault();
		 });
		 	$("body").on( 'click',"#btn-ico-confirmation", function(e) {
			e.preventDefault();
			window.location.href = "logout?t=" + Math.random();
	    	return false;
			});	
		});
		</script>
		
</body>
</html>