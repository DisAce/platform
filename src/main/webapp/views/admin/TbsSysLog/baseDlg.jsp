<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<c:set var="sysPath" value="${pageContext.request.contextPath}/" scope="application" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
	<!-- bootstrap & fontawesome -->
	<link rel="stylesheet" type="text/css" href="${sysPath}static/assets/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="${sysPath}static/assets/font-awesome/css/font-awesome.min.css" />
	<!-- page specific plugin styles -->
	<!-- text fonts -->
	<link rel="stylesheet" href="http://fonts.useso.com/css?family=Open+Sans:400,300" />
	<!-- ace styles -->
	<link rel="stylesheet" type="text/css" href="${sysPath}static/assets/css/ace.min.css" id="main-ace-style" />
	<!--[if lte IE 9]>
				<link rel="stylesheet" href="${sysPath}static/assets/css/ace-part2.min.css" />
	<![endif]-->
	<!--[if lte IE 9]>
			  <link rel="stylesheet" href="${sysPath}static/assets/css/ace-ie.min.css" />
	<![endif]-->
  </head>
  
  <body>
  <form id="tbsRoleFm" class="form-horizontal" method="post">
	 
		<table>
			<tr>
				<td><label> 主键: </label></td>
    			<td >
					<input name="roleId" class="easyui-validatebox"  readonly="readonly" 
										/>
				</td>


				<td><label> 角色: </label></td>
    			<td >
				<input name="roleName" class="easyui-validatebox" type="text"
			   					 />
				</td>

	 		</tr>
	        <tr>

				<td><label> 所有权限: </label></td>
    			<td >
				<input name="roleDesc" class="easyui-validatebox" type="text"
			   					 />
				</td>


				<td><label> 时间: </label></td>
    			<td >
				<input name="createTime"  class="easyui-datetimebox"
													 							/>
				</td>


			<tr>
				<td><label> 授权设置: </label></td>
				<td colspan="3"><input id="roleAuthTree" name="moduleIds" size="57"/></td>
			</tr>
		</table>
		<!--  
		<div class="form-group">
				<label for="roleId" class="col-sm-3 control-label no-padding-right">
					用电分项名称  
				</label>
				<div class="col-sm-9">
					<input id="roleId" name="roleId" type="text" data-rel="tooltip" 
					 class="col-xs-9" placeholder="" datatype="*1-50" title="用电分项名称长度不能超过50位！" errormsg="用电分项名称长度不能超过50位！" nullmsg="用电分项名称不能为空！">
					<span class="middle" style="color: red"> *</span>
				<div class="Validform_checktip" style="clear: both;"></div>
				</div>
			</div>
			
			<div class="space-4"></div>
			
			<div class="form-group">
				<label for="roleName" class="col-sm-3 control-label no-padding-right">
					用电分项编码 
				</label>

				<div class="col-sm-9">  
					<input id="roleName" name="roleName" type="text" data-rel="tooltip"  maxlength="50" title="用电分项编码长度不能超过50位！"  class="col-xs-9" placeholder="" >
					
				</div>
			</div>		
			
			<div class="space-4"></div>
			
			<div class="form-group">
				<label  class="col-sm-3 control-label no-padding-right">
					上级用电分项
				</label>

				<div class="col-sm-9">
					<input id="roleDesc"  type="text" data-rel="tooltip" readonly="readonly"   class="col-xs-9" >
					
				</div>
			</div>		
			
			
			<div class="space-4"></div>
			
			<div class="form-group">
				<label for="createTime"  class="col-sm-3 control-label no-padding-right">
					备注
				</label>

				<div class="col-sm-9">
					<textarea id="createTime" name="REMARK" class="col-xs-9" 
						 title="备注不能超过200位任意字符！"
						style="resize: none" 
					  maxlength="200" multiline="true"  ></textarea>
				</div>
			</div>
			<input id="obj_pid" name="PID"  type="hidden"/>
			<input id="obj_id" name="ID" type="hidden" />
			-->
	</form>
  </body>
</html>
