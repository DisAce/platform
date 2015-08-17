<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	//Add and Edit
	function tbsMenuGridAddAndEdit(title, url, type) {
		if (type == 1) { //edit
			var rows = $('#tbsMenuGrid').treegrid('getSelections');
			if (rows.length != 1) {
				$.messager.alert('消息', '请钩择一行数据!', 'info');
				return;
			}
		}
		$('<div/>').dialog({
			href : 'module/baseDlg',
			modal : true,
			title : title,
			height:200,
			width : 500,
			resizable:true,
			buttons : [ {
				text : '确定',
				iconCls : 'icon-ok',
				handler : function() {
					tbsMenuGridSubmit(url);
					
				}
			}, {
				text : '取消',
				iconCls : 'icon-cancel',
				handler : function() {
					$(this).closest(".dialog-button").prev(".window-body").dialog('close');
				}
			} ],
			onClose : function() {
				$(this).dialog('destroy');
			},
			onLoad : function() {
				$('#tbsMenuComboxTree').combotree({multiple:false,url:'module/comboxTree?t='+ Math.random()});
				if (type == 0) {
					$('#tbsMenuFm').form('clear');
				} else {
					var rows = $('#tbsMenuGrid').treegrid('getSelections');
					if (rows.length == 1) {
						$('#tbsMenuFm').form('load', rows[0]);
					} else {
						$.messager.alert('消息', '请钩择一行数据!', 'info');
					}
				}
			}
		});
	}
	
	//Del
	function tbsMenuGridDel() {
		var rows = $('#tbsMenuGrid').treegrid('getSelections');
		if (rows.length > 0) {
		    var ids = '';
			for ( var i = 0; i < rows.length; i++) {
						ids += 'ids=' + rows[i].id + '&';
			}
			ids = ids.substring(0, ids.length - 1);
			var url = '${demoPath}admin/TbsMenu/del.html?' + ids;
			$.messager.confirm('Confirm','确定要删除选择的数据吗?', function(r) {
				if (r) {
				    $.get(url, function(result){
						if (result.success){ 
							$('#tbsMenuGrid').treegrid('reload');
							$('#tbsMenuGrid').treegrid('clearSelections');
						} else {  
						    $.messager.show({ title: 'Error', msg : result.msg }); 
						} 
				    }, 'json');
				}
			});
		} else {
			$.messager.alert('消息', '请选择要删除的数据!','info');
		}
	}
	
	//Reload
	function tbsMenuGridReload() {
		$('#tbsMenuGrid').treegrid('options').pageNumber=1;
		var url='module/data?gridName=treegrid';
		$('#tbsMenuGrid').treegrid('options').url=url;
		$('#tbsMenuGrid').treegrid('reload');
	}
	
	//tbsMenuGridSubmit  submit
	function tbsMenuGridSubmit(url) {
	    $('#tbsMenuFm').form('submit',{  
	        url: url,  
	        onSubmit: function(){  
	            return $(this).form('validate');  
	        },  
	        success: function(result){  
	            var result = eval('('+result+')');  
	            if (result.success){  
	                $.messager.alert('操作提示', result.msg, 'info');
	            	$('#tbsMenuGrid').treegrid('reload');
	            	$(this).closest(".dialog-button").prev(".window-body").dialog('destroy');
	            } else {  
	            	$.messager.alert('操作提示', result.msg, 'info');
	            }  
	        }  
	    });
	}
	
	//高级搜索 del row
	function tbsMenuSearchRemove(curr) {
		if ($(curr).closest('table').find('tr').size() > 2) {
			$(curr).closest('tr').remove();
		} else {
			alert('该行不允许删除');
		}
	}
	
	//高级查询
	function tbsMenuSearch() {
		$('<div/>').dialog({
			href : '${demoPath}admin/TbsMenu/searchDlg.html',
			modal : true,
			title : '高级查询',
			top : 120,
			width : 480,
			buttons : [ {
				text : '增加一行',
				iconCls : 'icon-add',
				handler : function() {
					var currObj = $(this).closest('.panel').find('table');
					currObj.find('tr:last').clone().appendTo(currObj);
					//currObj.find('tr:last td *[disabled]').removeAttr("disabled");
				}
			}, {
				text : '确定',
				iconCls : 'icon-ok',
				handler : function() {
					$('#tbsMenuGrid').treegrid('options').pageNumber=1;
					var args=serializeObjectEx($('#tbsMenuSearchFm'));
					args['searchType']=2; //添加JSON属性
					$('#tbsMenuGrid').treegrid({queryParams: args});
				}
			}, {
				text : '取消',
				iconCls : 'icon-cancel',
				handler : function() {
					$(this).closest('.window-body').dialog('destroy');
				}
			} ],
			onClose : function() {
				$(this).dialog('destroy');
			}
		});
	}
	
	//导出
	function tbsMenuGridExport(){
		window.location="${demoPath}admin/TbsMenu/export.html";
		//var panel=$('#tbcTempGrid').treegrid('getPanel');
		//var options=panel.panel('options');
		//alert(options.method);
		//console.dir(options);
	}
	
	//导入
	function tbsMenuGridImport(){
		$('<div/>').dialog({
			href : '${demoPath}admin/TbsMenu/importDlg.html',
			modal : true,
			title : '导入',
			top : '15%',
			left : '30%',
			width : 600,
			height: 300,
			resizable:true,
			buttons : [ {
				text : '确定',
				iconCls : 'icon-ok',
				handler : function() {
					//$(this).closest('.window-body').dialog('destroy');
				}
			}, {
				text : '取消',
				iconCls : 'icon-cancel',
				handler : function() {
					$(this).closest('.window-body').dialog('destroy');
				}
			} ],
			onClose : function() {
				$(this).dialog('destroy');
			},
			onLoad : function() {
				
			}
		});
	}
	
</script>
    

	<!-- 中  treegrid-->
    <div data-options="region:'center',border : false">
		<!-- treegrid toolbar -->
		<table id="tbsMenuGrid"  class="easyui-treegrid"  data-options="	
			url:'module/data?gridName=treegrid',
			/*onLoadSuccess : function(row,data){
			     $(this).treegrid('collapseAll');
			},*/
			columns:[ [  

			{field:'id',title:'唯一',hidden:false,width:'135',halign:'center',align:'center',sortable:'true', formatter: function(value,row,index){
			    return value;
			}},			

			{field:'parentId',title:'父节点',hidden:false,width:'135',halign:'center',align:'center',sortable:'true', formatter: function(value,row,index){
			    return value;
			}},			

			{field:'name',title:'名称',hidden:false,width:'200',halign:'left',align:'left',sortable:'true', formatter: function(value,row,index){
			    return value;
			}},			

			{field:'leaf',title:'菜单类型',hidden:false,width:'135',halign:'center',align:'center',sortable:'true', formatter: function(value,row,index){
				if(value=='0'){
			    	value='菜单';
			    }
				if(value=='1'){
			    	value='功能';
			    }
			    return value;
			}},			

			{field:'type',title:'加载方式',hidden:false,width:'135',halign:'center',align:'center',sortable:'true', formatter: function(value,row,index){
				if(value=='0'){
			    	value='href';
			    }
				if(value=='1'){
			    	value='树形';
			    }
				if(value=='2'){
			    	value='iframe';
			    }
			    return value;
			}},			

			{field:'displayIndex',title:'排序',hidden:false,width:'135',halign:'center',align:'center',sortable:'true', formatter: function(value,row,index){
			    return value;
			}},			

			{field:'moduleUrl',title:'地址',hidden:false,width:'135',halign:'center',align:'center',sortable:'true', formatter: function(value,row,index){
			    return value;
			}},			

			{field:'button',title:'按钮',hidden:true,width:'135',halign:'center',align:'center',sortable:'true', formatter: function(value,row,index){
			    return value;
			}},			

			{field:'isDisplay',title:'状态',hidden:false,width:'135',halign:'center',align:'center',sortable:'true', formatter: function(value,row,index){
				if(value=='1'){
			    	value='启用';
			    }
				if(value=='0'){
			    	value='禁用';
			    }
			    return value;
			}},			

			{field:'createTime',title:'时间',hidden:true,width:'135',halign:'center',align:'center',sortable:'true', formatter: function(value,row,index){
			    return value;
			}}			
			] ],
			toolbar:'#tbsMenuGridToolbar'
		"/>
		
		<!-- treegrid toolbar -->
		<div id="tbsMenuGridToolbar">
			<div style="margin-bottom:5px">
				<c:forEach items="${buttons}" var="button">
			         ${button}
			    </c:forEach>
				
				<!-- tbsMenuGridToolbarSearch -->
				<input class="easyui-searchbox" data-options="
					menu :'#tbsMenuGridToolbarSearch',
					prompt :'模糊查询',
					searcher : function(value,name){
						var str='{searchType:1,'+name+':\''+value+'\'}';
				        var obj = eval('('+str+')');
				        $('#tbsMenuGrid').treegrid('options').pageNumber=1;
				        $('#tbsMenuGrid').treegrid({queryParams: obj});
					}
				"/>
				<div id="tbsMenuGridToolbarSearch">
					<div name="id">唯一</div>
					<div name="parentId">父节点</div>
					<div name="name">名称</div>
					<div name="isMenu">菜单类型</div>
					<div name="type">加载方式</div>
					<div name="sortNumber">排序</div>
					<div name="url">地址</div>
					<div name="button">按钮</div>
					<div name="status">状态</div>
					<div name="createTime">时间</div>
				</div>
				<a href="javascript:void(0)" onclick="javascript:tbsMenuSearch()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'">高级查询</a>
			</div>
		</div>
	</div>
<!--  <div>-->