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
							<i class="fa fa-list-ol orange"></i> 进货单管理列表
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
														title="新增" >
														<div class="ui-pg-div" title="新增">
															<span id="icon-bureau-add"
																style="width: 20px; font-size: 20px;"
																class="fa fa-plus-circle ui-icon purple"></span>
														</div>
													</td>
													<td id="edit_grid-table" class="ui-pg-button ui-corner-all"
														title="" data-original-title="">
														<div class="ui-pg-div" title="修改">
															<span id="icon-bureau-edit" class="ui-icon ace-icon fa fa-pencil blue"
																style="width: 20px; font-size: 20px;"></span>
														</div>
													</td>
													<td id="del_grid-table" class="ui-pg-button ui-corner-all"
														title="" data-original-title="">
														<div class="ui-pg-div" title="删除">
															<span id="icon-bureau-delete" class="ui-icon fa-trash red"
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
	jQuery(document).ready(function() {
	//alert(T.M.encode(T.E.utf16to8('admin/material/save'))+"===="+T.E.utf8to16(T.M.decode('YWRtaW4vc3VwcGxpZXIvc2F2ZQ')));
		var grid_selector = "#grid-table";
		var pager_selector = "#grid-pager";
		$(window).on('resize.jqGrid', function () {
			//表格自适应宽度减去18px的滚动条宽度
			$(grid_selector).jqGrid( 'setGridWidth', $(".page-content").width() -2);
			//表头宽度不变
			$('.ui-jqgrid-hdiv').width($(".page-content").width()-2);
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
		
		$.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
					_title: function(title) {
						var $title = this.options.title || '&nbsp;'
						if( ("title_html" in this.options) && this.options.title_html == true )
							title.html($title);
						else title.text($title);
					}
				}));
		//新增
		$('#icon-bureau-add').on( 'click', function(e) {
			e.preventDefault();
			var title = "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='ace-icon fa fa-plus-square red'></i> 新增进货单信息 </h4></div>";
			T.Box.WSC_ShowDialog("admin/material/baseDlg", 660, title);
			T.U.Datepicker('#datepicker');
	    	T.C.S.Validform.resetForm();
	    	$("#obj_Price").html("");
	    	$("#obj_id").val('');
	    	T.U.AjaxRequest({
   					url : T.U.RewriteAjaxAction('admin/supplier/comboxList'),
   					type:"POST",
   					callback : function(json) {
   						    var json = eval('('+json+')');
			                var options_str = "";
			                $("#obj_Supplier").html("");
			                options_str += "<option value=\"\" >-请选择-</option>"
			                $.each(json,function(i, item){
			                        options_str += "<option value=\"" + item.id + "\" >" + item.name + "</option>";
			                });
			                $("#obj_Supplier").append(options_str);
   					},
   					callbackError : function(err) {
   					T.Box.Tips('警告','获取供应商列表信息失败！',T.C.I.WarningClass,T.C.I.WarningIoc,T.C.I.style);
					return false;
   					}
   					});
        	return false;
		
			});
			//编辑
		 $('#icon-bureau-edit').on( 'click', function(e) {
			e.preventDefault();
			var title = "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='ace-icon fa fa-pencil red'></i> 修改进货单信息 </h4></div>";
			id = jQuery(grid_selector).jqGrid('getGridParam','selarrrow');
			if(id.length <= 0){
			T.Box.Tips('操作提示','请选择要操作的记录！',T.C.I.InfoClass,T.C.I.InfoIoc,T.C.I.style);
			return false;
			}else if(id.length > 1){
			T.Box.Tips('操作提示','当前操作不允许选择多行记录！',T.C.I.InfoClass,T.C.I.InfoIoc,T.C.I.style);
			return false;
			}else{
			rowId = jQuery(grid_selector).jqGrid('getGridParam','selrow');
			var rowData = jQuery(grid_selector).jqGrid('getRowData',rowId);
			T.Box.WSC_ShowDialog("admin/material/baseDlg", 660,  title);
			if(T.C.S.i == 1){
			T.U.Datepicker('#datepicker');
	    	T.C.S.Validform.resetForm();
	    	$("#obj_Price").html("");
	    	T.U.AjaxRequest({
   					url : T.U.RewriteAjaxAction('admin/supplier/comboxList'),
   					type:"POST",
   					callback : function(json) {
   						    var json = eval('('+json+')');
			                var options_str = "";
			                $("#obj_Supplier").html("");
			                options_str += "<option value=\"\" >-请选择-</option>"
			                $.each(json,function(i, item){
			                        options_str += "<option value=\"" + item.id + "\" >" + item.name + "</option>";
			                });
			                $("#obj_Supplier").append(options_str);
   							},
   					callbackError : function(err) {
   					T.Box.Tips('警告','获取供应商列表信息失败！',T.C.I.WarningClass,T.C.I.WarningIoc,T.C.I.style);
					return false;
   						}
   					});
   			T.U.AjaxRequest({
   					url : T.U.RewriteAjaxAction('admin/supplierPrice/comboxList'),
   					type:"POST",
   					params:{supplierId:$("#obj_Supplier").val()},
   					callback : function(json) {
   						    var json = eval('('+json+')');
			                var options_str = "";
			                $("#obj_Price").html("");
			                options_str += "<option value=\"\" >-请选择-</option>"
			                $.each(json,function(i, item){
			                        options_str += "<option value=\"" + item.id + "\" >" + item.name + "</option>";
			                });
			                $("#obj_Price").append(options_str);
   					},
   					callbackError : function(err) {
   					T.Box.Tips('警告','获取供应商单价列表信息失败！',T.C.I.WarningClass,T.C.I.WarningIoc,T.C.I.style);
					return false;
   					}
   					});
	    	T.C.S.SKEY = rowData.id;
			$("#obj_id").val(rowData.id);
			$("#obj_Supplier").val(rowData.supplierId);
			$("#obj_Price").val(rowData.priceId);
			$("#obj_Total").val(rowData.total);
			$("#obj_Num").val(rowData.num);//
			$("#datepicker").val(rowData.date);//
			$("#obj_remarks").val(rowData.remarks);
			}
        	return false;
				}
			});
			//删除
		 $('#icon-bureau-delete').on( 'click', function(e) {
			e.preventDefault();
			id = jQuery(grid_selector).jqGrid('getGridParam','selarrrow');
			if(id.length <= 0){
			T.Box.Tips('操作提示','请选择要删除的记录！',T.C.I.InfoClass,T.C.I.InfoIoc,T.C.I.style);
			return false;
			}else if(id.length > 1){
			T.Box.Tips('操作提示','当前操作不允许选择多行记录！',T.C.I.InfoClass,T.C.I.InfoIoc,T.C.I.style);
			return false;
			}else{
			rowId = jQuery(grid_selector).jqGrid('getGridParam','selrow');
			var rowData = jQuery(grid_selector).jqGrid('getRowData',rowId);
			T.C.S.SKEY = rowData.id;
			T.Box.Tips('操作提示','确定要删除当前选中记录吗?',T.C.I.QuestionClass,T.C.I.QuestionIoc,T.C.I.style,true);
			return false;
			}
		
			});
		
		$("body").on( 'change', "#obj_Supplier",function(e) {
			e.preventDefault();
			T.U.AjaxRequest({
   					url : T.U.RewriteAjaxAction('admin/supplierPrice/comboxList'),
   					type:"POST",
   					params:{supplierId:$("#obj_Supplier").val()},
   					callback : function(json) {
   						    var json = eval('('+json+')');
			                var options_str = "";
			                $("#obj_Price").html("");
			                options_str += "<option value=\"\" >-请选择-</option>"
			                $.each(json,function(i, item){
			                        options_str += "<option value=\"" + item.id + "\" >" + item.name + "</option>";
			                });
			                $("#obj_Price").append(options_str);
   					},
   					callbackError : function(err) {
   					T.Box.Tips('警告','获取供应商单价列表信息失败！',T.C.I.WarningClass,T.C.I.WarningIoc,T.C.I.style);
					return false;
   					}
   					});
        	return false;
		
			});	
		
		$("body").on( 'click',"#btn-ico-confirmation", function(e) {
			e.preventDefault();
			T.U.AjaxRequest({
   					url : T.U.RewriteAjaxAction('admin/material/del/'+T.C.S.SKEY),type:"POST",
   					callback : function(data) {
   						   if (typeof (data)  != 'undefined') {
		                    	if(data.success){
		                    		jQuery(T.C.S.DridSelector).jqGrid('setGridParam', {
		            					url : T.U.RewriteAjaxAction('admin/material/data'+"?_t=" + new Date().getTime()),
		            					mtype:'POST'
		            				}).trigger("reloadGrid");
		            				$('#gritter-notice-wrapper').remove();
		            				T.Box.Tips('操作提示',data.msg,T.C.I.SuccessClass,T.C.I.SuccessIoc,T.C.I.style);
		                    	return;
		    					}
		    					if(!data.success){
		    					T.Box.Tips('操作提示',data.msg,T.C.I.WarningClass,T.C.I.WarningIoc,T.C.I.style);
		    						return;
		    					}
		                    }else {
		                    	T.Box.Tips('操作提示','未知错误！',T.C.I.ErrorClass,T.C.I.ErrorIoc,T.C.I.style);
	    						return;
		                    }	
   					}
   					});
	    			return false;
		
			});	
			
		var _colNames = ['ID', '进货日期','supplierId','供应商名称', 'priceId','单价（元/斤）','进货金额（元）','总重量（斤）','只数（只）','单只平均重量（斤）','单只平均价格（元）',  '备注', '入库时间'];
		var _colModel = [ {name : 'id', index : 'id', width : 60, sorttype : "text", hidden : true,key : true },
						  {name : 'date',index : 'date',width : 50,sorttype : "text"}, 
						  {name : 'supplierId',index : 'supplierId',width : 90,hidden : true}, 
						  {name : 'name',index : 'name',width : 90,sorttype : "text"},
						  {name : 'priceId',index : 'priceId',width : 90,hidden : true},  
						  {name : 'price',index : 'price',width : 60,sorttype : "text"},
						  {name : 'total',index : 'total',width : 60,sorttype : "text"},
						  {name : 'weight',index : 'weight',width : 50,sorttype : "text"},
						  {name : 'num',index : 'num',width : 40,sorttype : "text"},
						  {name : 'singleTotal',index : 'singleTotal',width : 70,sorttype : "text"},
						  {name : 'singlePrice',index : 'singlePrice',width : 70,sorttype : "text"},
						  {name : 'remarks', index : 'remarks', width : 150, sorttype : "text",hidden : true },
						  {name : 'createTime',index : 'createTime',width : 80,sorttype : "text"} ];
						  
		T.D._initDataGrid(grid_selector,pager_selector,"admin/material/data",_colNames,_colModel);
		
		function customFmatter(cellvalue, options, rowObject){  
      		
      				if(cellvalue==0){
      					return '<span class="label label-sm label-success arrowed arrowed-righ" style="width:70px;">正常</span>';
      				}else if(cellvalue==1){
      					return '<span class="label label-sm label-info arrowed arrowed-righ" style="width:70px;">正常</span>';
      				}else if(cellvalue==2){
      					return '<span class="label label-sm label-warning arrowed arrowed-righ" style="width:70px;">异常</span>';
      				}else{
      					return '<span class="label label-sm label-inverse arrowed-in" style="width:70px;">锁定</span>';
      				}
		}  
	});
</script>
</body>
</html>
