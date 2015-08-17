/**
 * 通过工具类
 */
var dataPackage = {
	
		_initDataGrid : function (grid_selector,pager_selector,_url,_colNames,_colModel){ 
			
			jQuery(grid_selector).jqGrid({

				url: _url,
				datatype: "json",
				mtype: "POST",
				height:351,
				colNames:_colNames,
				colModel:_colModel,
				viewrecords : true,
				rowNum:10,
				rowList:[10,20,30],
				pager : pager_selector,
				altRows: true,
				multiselect: true,
		        multiboxonly: true,
				loadComplete : function() {
					var table = this;
					setTimeout(function(){
						dataPackage.updatePagerIcons(table);
						dataPackage.enableTooltips(table);
					}, 0);
					dataPackage.effectHandle();
				},
				autowidth: true
			});
			$(window).triggerHandler('resize.jqGrid');//trigger window resize to make the grid get the correct size
			jQuery(grid_selector).jqGrid('navGrid',pager_selector,
					{ 	
						edit: false,
						add: false,
						del: false,
						search: false,
						refresh: true,
						refreshicon : 'ace-icon fa fa-refresh green',
						view: false
					}
				)
		},
		
		effectHandle : function () {
			$('.ui-paging-info').css('margin-right', '50px');
			$('.ui-jqgrid-bdiv').css({'overflow-y' : 'scroll' });
			$('.ui-jqgrid-bdiv').css({'overflow-x' : 'hidden' });
		},
		
		updatePagerIcons : function (table) {
			var replacement = 
			{
				'ui-icon-seek-first' : 'ace-icon fa fa-angle-double-left bigger-140',
				'ui-icon-seek-prev' : 'ace-icon fa fa-angle-left bigger-140',
				'ui-icon-seek-next' : 'ace-icon fa fa-angle-right bigger-140',
				'ui-icon-seek-end' : 'ace-icon fa fa-angle-double-right bigger-140'
			};
			$('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
				var icon = $(this);
				var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
				
				if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
			})
		},
		
		enableTooltips : function (table) {
			$('.navtable .ui-pg-button').tooltip({container:'body'});
			$(table).find('.ui-pg-div').tooltip({container:'body'});
		}

};
	