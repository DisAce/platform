
<form class="form-horizontal container" id="estype_add_form" action="YWRtaW4vc3VwcGxpZXJQcmljZS9zYXZl"
	novalidate="novalidate">
	<div class="row">
		<label for="obj_SupplierId" class="col-md-3 control-label no-padding-right"> 公司名称： </label>
		<div class="col-md-9">
		<select id="obj_SupplierId" class="col-xs-10"   name="supplierId" 
				placeholder="" datatype="*" nullmsg="请选择公司名称 ">
				<option value="">-请选择-</option>
			</select>
			 <span class="middle" style="color: red"> *</span>
			<div class="Validform_checktip" style="clear: both;"></div>
		</div>
	</div>
	<div class="row">
		<label for="obj_Price" class="col-md-3 control-label no-padding-right"> 单价： </label>
		<div class="col-md-9">
			<input id="obj_Price" name="price" type="text" data-rel="tooltip"
				class="col-md-10" placeholder="" datatype="*1-32"
				errormsg="名称 长度不能超过32位！" nullmsg="请输入单机信息"> <span
				class="middle" style="color: red"> 元/斤</span>
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
