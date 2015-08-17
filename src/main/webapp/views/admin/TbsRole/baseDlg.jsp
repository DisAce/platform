
  <form id="tbsRoleFm" class="form-horizontal" method="post">
	 
		<table>
			<tr>
				<td><label> 主键: </label></td>
    			<td >
					<input name="roleId" type="text" />
				</td>


				<td><label> 角色: </label></td>
    			<td >
				<input name="roleName" type="text" />
				</td>

	 		</tr>
	        <tr>

				<td><label> 所有权限: </label></td>
    			<td >
				<input name="roleDesc" type="text"  />
				</td>


				<td><label> 时间: </label></td>
    			<td >
				<input name="createTime"  type="text" />
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
