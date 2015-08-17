
<form class="form-horizontal container" id="estype_add_form" action="YWRtaW4vdXNlci9zYXZl"
	novalidate="novalidate">
	<div class="row">
		<label for="obj_account" class="col-md-2 control-label no-padding-right">
			帐号： </label>
		<div class="col-md-4">
			<input id="obj_account" name="account" type="text" data-rel="tooltip"
				class="col-md-10" placeholder="" datatype="*6-32"
				errormsg="帐号至少6个字符,最多32个字符！" nullmsg="请输入用户帐号"> <span
				class="middle" style="color: red"> *</span>
			<div class="Validform_checktip" style="clear: both;"></div>
		</div>

		<label for="obj_realName"
			class="col-md-2 control-label no-padding-right"> 名称： </label>

		<div class="col-md-4">
			<input id="obj_realName" name="realName" type="text" data-rel="tooltip"
				class="col-md-10" placeholder="" datatype="*1-32"
				errormsg="名称 长度不能超过32位！" nullmsg="请输入用户名称"> <span
				class="middle" style="color: red"> *</span>
			<div class="Validform_checktip" style="clear: both;"></div>
		</div>
	</div>
	<div class="row">

		<label for="obj_password"
			class="col-md-2 control-label no-padding-right"> 密码： </label>
		<div class="col-md-4">
			<input id="obj_password" name="password" type="password" data-rel="tooltip"
				class="col-md-10" placeholder="Password" datatype="*6-16"
				errormsg="密码范围在6~16位之间！"  nullmsg="请输入密码！" />
			 <span class="middle" style="color: red"> *</span>
			<div class="Validform_checktip" style="clear: both;"></div>
		</div>


		<label for="obj_userpassword2"
			class="col-md-2 control-label no-padding-right"> 确认密码： </label>
		<div class="col-md-4">
			<input id="obj_userpassword2" name="userpassword2" recheck="password" type="password" data-rel="tooltip"
				class="col-md-10" placeholder="" datatype="*"
				errormsg="您两次输入的账号密码不一致！" nullmsg="请再输入一次密码！"> <span
				class="middle" style="color: red"> *</span>
			<div class="Validform_checktip" style="clear: both;"></div>
		</div>
	</div>
	<div class="row">

		<label for="obj_sex"
			class="col-md-2 control-label no-padding-right"> 性别： </label>
		<div class="col-md-4">
			<select id="obj_sex" class="col-xs-10" id="obj_sex" name="sex" placeholder=""
				datatype="*" nullmsg="请选择用户性别 ">
				<option value="">-请选择-</option>
				<option value=0>男</option>
				<option value=1>女</option>
				<option value=2>未知</option>
			</select> <span class="middle" style="color: red"> *</span>
			<div class="Validform_checktip" style="clear: both;"></div>
		</div>


		<label for="obj_email"
			class="col-md-2 control-label no-padding-right"> 邮箱： </label>
		<div class="col-md-4">
			<input id="obj_email" name="email" type="text" data-rel="tooltip"
				class="col-md-10" placeholder="" datatype="e"
				errormsg="邮箱格式不正确，请重新填写！" nullmsg="请输入用户邮箱 "> <span
				class="middle" style="color: red"> *</span>
			<div class="Validform_checktip" style="clear: both;"></div>
		</div>
	</div>
	<div class="row">

		<label for="obj_officePhone"
			class="col-md-2 control-label no-padding-right"> 电话： </label>
		<div class="col-md-4">
			<input id="obj_officePhone" name="officePhone" type="text"
				data-rel="tooltip" class="col-md-10" placeholder="" datatype="*0-12"
				errormsg="联系方式格式不正确，请重新填写！">
			<div class="Validform_checktip" style="clear: both;"></div>
		</div>

		<label for="obj_mobile"
			class="col-md-2 control-label no-padding-right"> 手机： </label>

		<div class="col-md-4">
			<input id="obj_mobile" name="mobile" type="text"
				data-rel="tooltip" class="col-md-10" placeholder="" datatype="m"
				errormsg="手机号码格式不正确！" nullmsg="请输入用户手机号码 "> <span
				class="middle" style="color: red"> *</span>
			<div class="Validform_checktip" style="clear: both;"></div>
		</div>
	</div>
	<div class="row">
		<label for="obj_roleIds"
			class="col-md-2 control-label no-padding-right"> 角色： </label>

		<div class="col-md-4 btn-group">
			<select id="obj_roleIds" class="col-xs-10"  multiple="" name="roleIds" 
				placeholder="" datatype="*" nullmsg="请选择角色列表 ">
				<option value="">-请选择-</option>
			</select> <span class="middle" style="color: red"> *</span>
			<div class="Validform_checktip" style="clear: both;"></div>
		</div>
		<label for="obj_remark" class="col-md-2 control-label no-padding-right">
			备注： </label>

		<div class="col-md-4">
			<textarea id="obj_remark" name="remark" class="col-md-10 "
				style="overflow: hidden; word-wrap: break-word; resize: horizontal; height: 52px;resize: none;"
				maxlength="50"></textarea>
		</div>
	</div>
	<input id="obj_userId" name="userId" type="hidden">
</form>
