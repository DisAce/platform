<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
<%@ include file="path.jsp"%>
<title>统一认证管理平台</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="酷社区,后台管理系统,coiol" />
<meta http-equiv="description" content="统一认证管理平台" />
<link rel="stylesheet" type="text/css" href="${sysPath}static/css/reset.css" />
<link rel="stylesheet" type="text/css" href="${sysPath}static/css/supersized.css" />
<link rel="stylesheet" type="text/css" href="${sysPath}static/css/style.css" />
<link rel="stylesheet" type="text/css" href="${sysPath}static/js/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="${sysPath}static/js/easyui/themes/icon.css" />
</head>
<body>
		<div class="page-container">
            <h1>统一认证管理平台</h1>
            <form id="login" method="post">
                <input type="text" name="username" class="username easyui-validatebox" data-options="required:true" missingMessage="用户名不能为空" placeholder="请输入您的用户名！" ></input>
                <input type="password" name="password" class="password easyui-validatebox" data-options="required:true" missingMessage="密码不能为空" placeholder="请输入您的密码！"></input>
                <input type="text" name="verifyCode" class="Captcha easyui-validatebox" placeholder="请输入验证码！"></input>
                <div style="width: 130px;float: left;margin-top:25px;margin-left: 10px;">
                	<span> 
						<a style="text-align: center;" id="verifyClick" title="看不清楚？点击获得新图片。" href="javascript:void(0);"> 
						<img style="width:130px;height:42px;" align="absmiddle" id="verifyCode" src="${sysPath}checkimage.jpg" /> </a>
					</span>
				</div>
                <a  id="submitForm" class="submit_button" href="javascript:void(0);">
                <span class="l-btn-left"><span class="l-btn-text">登 录</span></span></a>
                 <a  id="resetPassword" class="resetPassword" href="javascript:void(0)">
                <span class="l-resetPassword-right"><span class="l-resetPassword-text">忘记密码？</span></span></a>
            </form>
        </div>
	<script type="text/javascript" src="${sysPath}static/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${sysPath}static/js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${sysPath}static/js/easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${sysPath}static/js/supersized.3.2.7.min.js"></script>
	<script type="text/javascript" src="${sysPath}static/js/supersized-init.js"></script>
	<script type="text/javascript" src="${sysPath}static/js/scripts.js"></script>
</body>
</html>