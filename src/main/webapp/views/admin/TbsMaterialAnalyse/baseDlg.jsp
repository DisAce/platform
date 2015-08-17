<form class="form-horizontal container" id="estype_add_form" action="YWRtaW4vbWF0ZXJpYWwvc2F2ZQ"
	novalidate="novalidate">
	<div class="row">
		<label for="obj_Supplier" class="col-md-2 control-label no-padding-right">
			供应商名称： </label>
		<div class="col-md-4">
			<select id="obj_Supplier" class="col-xs-10"   name="supplierId" 
				placeholder="" datatype="*" nullmsg="请选择供应商名称 ">
				<option value="">-请选择-</option>
			</select> <span class="middle" style="color: red"> *</span>
			<div class="Validform_checktip" style="clear: both;"></div>
		</div>
		<label for="obj_Price" class="col-md-2 control-label no-padding-right"> 供应商单价： </label>
		<div class="col-md-4">
			<select id="obj_Price" class="col-xs-10"   name="priceId" 
				placeholder="" datatype="*" nullmsg="请选择供应商单价！">
				<option value="">-请选择-</option>
			</select> <span
				class="middle" style="color: red"> *</span>
			<div class="Validform_checktip" style="clear: both;"></div>
		</div>
	</div>
	<div class="row">
	<label for="obj_Total" class="col-md-2 control-label no-padding-right"> 总金额： </label>
		<div class="col-md-4">
			<input id="obj_Total" name="total" type="text" data-rel="tooltip"
				class="col-md-10" placeholder="" datatype="*1-16"
				errormsg="进货单总金额 长度不能超过16位，请重新填写！"  nullmsg="请输入此次进货单总金额！" />
			 <span class="middle" style="color: red"> 元</span>
			<div class="Validform_checktip" style="clear: both;"></div>
		</div>
		<label for="obj_Num" class="col-md-2 control-label no-padding-right"> 总只数： </label>
		<div class="col-md-4">
			<input id="obj_Num" name="num" type="text" data-rel="tooltip"
				class="col-md-10" placeholder="" datatype="*1-16"
				errormsg="进货单只数 长度不能超过16位，请重新填写！"  nullmsg="请输入此次进货单只数！" />
			 <span class="middle" style="color: red"> 只</span>
			<div class="Validform_checktip" style="clear: both;"></div>
		</div>
	</div>
	<div class="row">
	<label for="obj_date" class="col-md-2 control-label no-padding-right"> 进货日期： </label>
		<div class="col-md-4"><div class="input-group input-group-sm">
				<input type="text" id="datepicker" readonly="readonly" name="date" class="form-control" />
				<span class="input-group-addon">
					<i class="ace-icon fa fa-calendar"></i>
				</span>
			</div>
		</div>
		<label for="obj_remarks" class="col-md-2 control-label no-padding-right"> 备注： </label>
		<div class="col-md-4">
			<textarea id="obj_date" name="remarks" class="col-md-10 "
				style="overflow: hidden; word-wrap: break-word; resize: horizontal; height: 52px;resize: none;"
				maxlength="50"></textarea>
		</div>
	</div>
	<input id="obj_id" name="id" type="hidden">
</form>
