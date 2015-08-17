<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="path.jsp"%>
<title>统一认证管理平台</title>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
<meta http-equiv="description" content="统一认证管理平台" />
<!-- easyui-->
<script type="text/javascript" src="${cdnPath}static/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${cdnPath}static/js/easyui/jquery.easyui.min.js"></script>
<link id="easyuiTheme" rel="stylesheet" type="text/css" href="${cdnPath}static/js/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="${cdnPath}static/js/easyui/themes/icon.css" />
</head>
<body>
	<div class="easyui-dialog" 
		data-options="
			modal : true,
			title : '未授权',
			width : 400,
			cache: false,
			resizable:true,
			buttons : [ {
				text : '登录',
				iconCls : 'icon-ok',
				handler : function() {
				   window.location.href='${cdnPath}';
				   $(this).closest('.window-body').dialog('destroy');
				}
			}, {
				text : '关闭',
				iconCls : 'icon-cancel',
				handler : function() {
				    $(this).closest('.window-body').dialog('destroy');
				}
			} ]
		">
	    <div style="padding: 10px;">无访问权限 -[ ${url} ]-请联系管理员或重新登录!<br/>1、可能长时间未操作，导致超时所致。<br/>2、请确认你的帐号有无相关操作权限。<br/>3、若以上问题仍未解决，请联系<a href="mailto:master@coiol.com">系统管理员</a>。</div>
	</div>
</body>
</html>