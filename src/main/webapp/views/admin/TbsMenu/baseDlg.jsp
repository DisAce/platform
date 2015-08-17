<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 弹出框 tbsMenuDlg -->
<div id="tbsMenuDlg">
	<form id="tbsMenuFm" method="post">
		<table>
			<tr>
				<td><label> 模块名称: </label></td>
    			<td >
				<input name="name" class="easyui-validatebox" type="text"/>
				</td>
				<td><label> 模块URL: </label></td>
    			<td >
				<input name="moduleUrl" class="easyui-validatebox" type="text" />
				</td>
	 		</tr>
	        <tr>
			<td><label> 父模块: </label></td>
    			<td >
				<input name="parentId" class="easyui-validatebox" type="text" id="tbsMenuComboxTree"/>
				</td>
				<td><label> 模块类型: </label></td>
    			<td >
					<select name="leaf">
					<option value="0"  > 菜单 </option>
					<option value="1"  > 功能 </option>
					</select>
				</td>
	 		</tr>
	        <tr>

				<td><label> 加载方式: </label></td>
    			<td >
					<select name="type">
					<option value="0"  > href </option>
					<option value="1"  > 树形 </option>
					<option value="2"  > iframe </option>
					</select>
				</td>
				<td><label> 排序: </label></td>
    			<td >
				<input name="displayIndex" class="easyui-validatebox" type="text" />
				</td>
	 		</tr>
	        <tr>
				<td><label> 按钮事件: </label></td>
    			<td >
				<input name="button" class="easyui-validatebox" type="text"/>
				</td>
				<td><label> 状态: </label></td>
    			<td >
					<select name="isDisplay">
					<option value="1"  > 启用 </option>
					<option value="0"  > 禁用 </option>
					</select>
				</td>

	 		</tr>
		</table>
	</form>
</div>
