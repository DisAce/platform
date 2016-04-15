<%@ page language="java" import="java.util.*,java.util.HashMap" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xht1ml" >
<head>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>"/>
<c:set var="sysPath" value="${pageContext.request.contextPath}/" scope="application" />
<title>统一认证管理平台</title>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="统一认证管理平台,数据挖掘,数据分析" />
<meta http-equiv="description" content="控制面板" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
 %>
<!-- bootstrap & fontawesome -->
<link rel="stylesheet" type="text/css" href="${sysPath}static/assets/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="${sysPath}static/assets/font-awesome/css/font-awesome.min.css" />
<!-- page specific plugin styles -->
<link rel="stylesheet" type="text/css" href="${sysPath}static/assets/css/jquery-ui.min.css" />
<link rel="stylesheet" type="text/css" href="${sysPath}static/assets/css/bootstrap-multiselect.min.css" />
<link rel="stylesheet" type="text/css" href="${sysPath}static/assets/css/datepicker.css" />
<link rel="stylesheet" type="text/css" href="${sysPath}static/assets/css/ui.jqgrid.css" />
<!-- text fonts -->
<link rel="stylesheet" href="http://fonts.useso.com/css?family=Open+Sans:400,300" />
<!-- ace styles -->
<link rel="stylesheet" type="text/css" href="${sysPath}static/assets/css/ace.min-bak.css" id="main-ace-style" />
<!--[if lte IE 9]>
			<link rel="stylesheet" href="${sysPath}static/assets/css/ace-part2.min.css" />
<![endif]-->
<link rel="stylesheet" type="text/css" href="${sysPath}static/assets/css/ace-skins.min.css" />
<link rel="stylesheet" type="text/css" href="${sysPath}static/assets/css/ace-rtl.min.css" />
<!--[if lte IE 9]>
		  <link rel="stylesheet" href="${sysPath}static/assets/css/ace-ie.min.css" />
<![endif]-->
<link rel="shortcut icon" href="${sysPath}static/assets/images/favicon.ico" type="image/x-icon" />
<!-- ace settings handler -->
<script type="text/javascript" src="${sysPath}static/assets/js/ace-extra.min.js"></script>
<!--[if lte IE 8]>
		<script src="${sysPath}static/assets/js/html5shiv.min.js"></script>
		<script src="${sysPath}static/assets/js/respond.min.js"></script>
<![endif]-->
<link rel="icon" href="${sysPath}static/assets/images/favicon.ico" type="image/x-icon" />


</head>