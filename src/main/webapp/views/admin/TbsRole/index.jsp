<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../commons/head.jsp"%>
<body class="no-skin">
<div id="navbar" class="navbar navbar-default" style="display:none">
			<script type="text/javascript">
				//try{ace.settings.check('navbar' , 'fixed')}catch(e){}
			</script>
</div>
	<div id="main-container" class="main-container">
	<script type="text/javascript">
	try{ace.settings.check('main-container' , 'fixed')}catch(e){}
	</script>
		<div class="col-xs-12">
			<div class="page-content">
				<!--jqGrid开始-->
				<div class="widget-box transparent">
					<div class="widget-header widget-header-flat">
						<h4 class="lighter">
							<i class="fa fa-list-ol orange"></i> 角色管理列表
						</h4>
						<div class="widget-toolbar"></div>
					</div>
					<div class="widget-body">
						<div class="widget-body-inner" style="display: block;">
							<div class="widget-box">
								<div class="widget-body">
									<div id="grid-pager_left"
										style="height: 35px; background-color: #EFF3F8; padding: 5px">
										<table cellspacing="0" cellpadding="0" border="0"
											style="float: left; table-layout: auto;"
											class="ui-pg-table navtable">
											<tbody>
												<tr>
													<td id="add_grid-table" class="ui-pg-button ui-corner-all"
														title="新增" data-original-title="">
														<div class="ui-pg-div" title="新增">
															<span id="icon-bureau-add"
																style="width: 20px; font-size: 20px;"
																class="fa fa-plus-circle ui-icon purple"></span>
														</div>
													</td>
													<td id="edit_grid-table" class="ui-pg-button ui-corner-all"
														title="" data-original-title="">
														<div class="ui-pg-div" title="修改">
															<span class="ui-icon ace-icon fa fa-pencil blue"
																style="width: 20px; font-size: 20px;"></span>
														</div>
													</td>
													<td id="del_grid-table" class="ui-pg-button ui-corner-all"
														title="" data-original-title="">
														<div class="ui-pg-div" title="删除">
															<span class="ui-icon fa-trash red"
																style="width: 20px; font-size: 20px;"></span>
														</div>
													</td>
													<td id="imp_grid-table" class="ui-pg-button ui-corner-all"
														title="" data-original-title="">
														<div class="ui-pg-div" title="导入">
															<span class="ui-icon fa fa-arrow-circle-o-up red"
																style="width: 20px; font-size: 20px;"></span>
														</div>
													</td>
													<td id="exp_grid-table" class="ui-pg-button ui-corner-all">
														<div class="ui-pg-div">
															<span id="icon_export" title="Excel导出"
																style="width:20px; font-size: 20px;"
																class="ui-icon fa fa-arrow-circle-o-down purple"></span>
														</div>
													</td>
												</tr>
											</tbody>
										</table>
									</div>
									<table id="grid-table"></table>
									<div id="grid-pager"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
<%@ include file="../../commons/foot.jsp"%>
<script type="text/javascript">
	jQuery(function($) {
		var grid_selector = "#grid-table";
		var pager_selector = "#grid-pager";
		$(window).resize(function() {
			//表格自适应宽度减去18px的滚动条宽度
			$(grid_selector).setGridWidth($(".page-content").width() -2);
			//表头宽度不变
			$('.ui-jqgrid-hdiv').width($(".page-content").width() -2);
			$('.ui-jqgrid-bdiv').width($(".page-content").width() -2);
			//分页条宽度不变
			$(pager_selector).width($(".page-content").width() -2);
		});
		
		//resize on sidebar collapse/expand
		var parent_column = $(grid_selector).closest('[class*="col-"]');
		$(document).on('settings.ace.jqGrid' , function(ev, event_name, collapsed) {
			if( event_name === 'sidebar_collapsed' || event_name === 'main_container_fixed' ) {
				//setTimeout is for webkit only to give time for DOM changes and then redraw!!!
				setTimeout(function() {
					$(grid_selector).jqGrid( 'setGridWidth', parent_column.width() );
				}, 0);
			}
	    })

		$('#icon-bureau-add').on( 'click', function() {
				
		});
		var _colNames = ['角色ID', '角色名称', '角色描述', '创建时间' ];
		var _colModel = [ {name : 'roleId', index : 'roleId', width : 60, sorttype : "text", editable : true }, 
						  {name : 'roleName',index : 'roleName',width : 90,editable : true,sorttype : "text"}, 
						  {name : 'roleDesc',index : 'roleDesc',width : 150,sorttype : "text",editable : true}, 
						  {name : 'createTime',index : 'createTime',width : 70,editable : true,edittype : "text"} ];
		T.D._initDataGrid(grid_selector,pager_selector,"admin/role/data",_colNames,_colModel);
	});
</script>
	
</body>

