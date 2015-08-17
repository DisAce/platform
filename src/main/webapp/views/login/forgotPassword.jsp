<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
		$("#forgetVerifyClick").on("click", function() {
			var url = 'checkimage.jpg?t=' + Math.random();
			$('#forgetVerifyCode').attr('src', url);
		});
</script>
<!-- 弹出框 tbcConTempDlg -->
<div id="tbcConTempDlg" class="forgetPassword-div">
	<form id="forgetPasswordForm" method="post">
		<table>
			<tr>
				<td><input type="text" name="account"
					class="username easyui-validatebox" data-options="required:true"
					missingMessage="用户名不能为空" placeholder="请输入您的用户名！"></input></td>
			</tr>
			<tr>
				<td><input type="text" name="email"
					class="password easyui-validatebox" data-options="required:true"
					missingMessage="邮箱不能为空" placeholder="请输入您的邮箱！"></input></td>
			</tr>
			<tr>
				<td><input type="text" name="captcha"
					class="Captcha easyui-validatebox" placeholder="请输入验证码！"></input>
					<div
						style="width: 130px;float: left;margin-top:25px;margin-left: 10px;">
						<span> <a style="text-align: center;" id="forgetVerifyClick"
							title="看不清楚？点击获得新图片。" href="javascript:void(0);"> <img
								style="width:130px;height:42px;" align="absmiddle"
								id="forgetVerifyCode" src="${sysPath}checkimage.jpg" />
						</a>
						</span>
					</div></td>
			</tr>
		</table>
	</form>
</div>
