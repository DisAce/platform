<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../commons/head.jsp"%>
<body class="no-skin">
	<div id="main-container" class="main-container">
		<div class="col-xs-12">
			<div id="dv">
				<!--jqGrid开始-->
				<div class="widget-box transparent">
					<div class="widget-header widget-header-flat">
						<h4 class="lighter">
							<i class="fa fa-list-ol orange"></i> 模块管理列表
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
													<td id="search_grid-table"
														class="ui-pg-button ui-corner-all" title=""
														data-original-title="">
														<div class="ui-pg-div" title="查询">
															<span class="ui-icon ace-icon fa fa-search orange"
																style="width: 20px; font-size: 20px;"></span>
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
			$(grid_selector).setGridWidth($("#dv").width());
			//表头宽度不变
			$('.ui-jqgrid-hdiv').width($("#dv").width());
			$('.ui-jqgrid-bdiv').width($("#dv").width());
			//分页条宽度不变
			$(pager_selector).width($("#dv").width());
		});

		$('#icon-bureau-add').on( 'click', function() {
					$.layer({
						type : 2,
						title : 'iframe父子操作',
						maxmin : true,
						shadeClose : false, //开启点击遮罩关闭层
						area : [ '600px', '400px' ],
						btns : 2,
						btn : [ '确定', '取消' ],
						iframe : {
							src : 'admin/role/baseDlg'
						},
						close : function(index) {
							layer.msg(
									'您获得了子窗口标记：'
											+ layer.getChildFrame('#name',
													index).val(), 3, 1);
						}
					});
						
				});
		var _colNames = ['ID','菜单名称', '父节点','菜单类型', '加载方式','显示顺序','地址','按钮事件','状态','时间' ];
		var _colModel = [ {name : 'id', index : 'id', width : 60,hidden : true,key : true, sorttype : "text", editable : true }, 
						  {name : 'name',index : 'name',width : 100,sorttype : "text",editable : true}, 
						  {name : 'parent',index : 'parent',width : 90,editable : true,sorttype : "text"},
						  {name : 'leaf',index : 'leaf',width : 70,formatter: "select", editoptions:{value:"0:菜单;1:功能"}},
						  {name : 'type',index : 'type',width : 70,formatter: "select", editoptions:{value:"0:iframe;1:树形;2:href"}},
						  {name : 'displayIndex',index : 'displayIndex',width : 50,editable : true,edittype : "text"},
						  {name : 'url',index : 'url',width : 70,editable : true,edittype : "text"},
						  {name : 'button',index : 'button',width : 70,editable : true,edittype : "text"},
						  {name : 'isDisplay',index : 'isDisplay',width : 50,formatter: "select", editoptions:{value:"0:禁用;1:启用"}},
						  {name : 'createTime',index : 'createTime',width : 120,editable : true,edittype : "text"} ];
		T._initTreeGrid(grid_selector,pager_selector,"admin/module/data?gridName=treegrid",_colNames,_colModel);
	});
</script>
</body>