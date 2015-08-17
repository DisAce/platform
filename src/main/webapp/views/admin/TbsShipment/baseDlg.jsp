<form class="form-horizontal container" id="estype_add_form" action="YWRtaW4vc2hpcG1lbnQvc2F2ZQ"
	novalidate="novalidate">
	<div class="row">
		<label for="obj_name" class="col-md-3 control-label no-padding-right">
			店面名称： </label>
		<div class="col-md-9">
			<input id="obj_name" name="name" type="text" data-rel="tooltip"
				class="col-md-10" placeholder="" datatype="*1-100"
				errormsg="名称至少6个字符,最多100个字符！" nullmsg="请输入店面名称"> <span
				class="middle" style="color: red"> *</span>
			<div class="Validform_checktip" style="clear: both;"></div>
		</div>
	</div>
	<div class="row">
	<label for="obj_address" class="col-md-3 control-label no-padding-right"> 店面地址： </label>
		<div class="col-md-9">
			<input id="obj_address" name="address" type="text" data-rel="tooltip"
				class="col-md-10" placeholder="" datatype="*1-100"
				errormsg="地址 长度不能超过100位！" nullmsg="请输入店面地址"> <span
				class="middle" style="color: red"> *</span>
			<div class="Validform_checktip" style="clear: both;"></div>
		</div>
	</div>
	<div class="row">
		<label for="obj_telephone" class="col-md-3 control-label no-padding-right"> 联系方式： </label>
		<div class="col-md-9">
			<input id="obj_telephone" name="telephone" type="text" data-rel="tooltip"
				class="col-md-10" placeholder="" datatype="*8-16"
				errormsg="联系方式格式不正确，请重新填写！"  nullmsg="请输入联系方式！" />
			 <span class="middle" style="color: red"> *</span>
			<div class="Validform_checktip" style="clear: both;"></div>
		</div>
	</div>
	<div class="row">
	<label for="obj_remarks" class="col-md-3 control-label no-padding-right"> 备注： </label>
		<div class="col-md-9">
			<textarea id="obj_remarks" name="remarks" class="col-md-10 "
				style="overflow: hidden; word-wrap: break-word; resize: horizontal; height: 52px;resize: none;"
				maxlength="50"></textarea>
		</div>
	</div>
	<input id="obj_id" name="id" type="hidden">
</form>
