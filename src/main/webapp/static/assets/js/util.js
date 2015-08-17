/**
 * 通过工具类
 */
var T = {
		
	    C: { // Config
	        I: { // Init
	            count: 0,
	            rowNum: 10,
	            PatternUsername: /^[0-9a-zA-Z\-_.]*$/,
	            HTTP20x30x: [200, 201, 202, 203, 204, 205, 206, 300, 301, 302, 303, 304, 305, 306, 307],
	            rowList: [10, 20, 30],
	            style: 'width:330px',
	            InfoIoc: 'ace-icon fa fa-info-circle bigger-300',
	            InfoClass: 'gritter-info gritter-center',
	            QuestionIoc: 'ace-icon fa fa-question-circle bigger-300',
	            QuestionClass: 'gritter-warning gritter-center',
	            WarningIoc: 'ace-icon fa fa-exclamation-triangle bigger-300',
	            WarningClass: 'gritter-error gritter-center',
	            SuccessIoc: 'ace-icon fa fa-info-circle bigger-300',
	            SuccessClass: 'gritter-success gritter-center',
	            ErrorIoc: 'ace-icon fa fa-bug bigger-300',
	            ErrorClass: 'gritter-error gritter-center'
	        },

	        S: { // Selector
	            Disabled: 'disabled',
	            Warning: 'warning',
	            Error: 'error',
	            Success: 'success',
	            DataUrl: '',
	            DridSelector: '',
	            i:0,
	            SKEY: '',
	            PRICE:'',
	            Validform:'',
	            Dialog: '#WSC_Dialog',
	            Processing: 'processing',
	            CommandResult: 'command_result',
	            /** * 此变量为编码的key，每个字符的下标相对应于它所代表的编码。*/
	    		enKey: 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/',
	    		
	    		/*** 此变量为解码的key，是一个数组，BASE64的字符的ASCII值做下标，所对应的就是该字符所代表的编码值。*/
	    		deKey: new Array(
	    		        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
	    		        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
	    		        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63,
	    		        52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1,
	    		        -1,  0,  1,  2,  3,  4,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14,
	    		        15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1,
	    		        -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
	    		        41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1
	    		    )
	        }
	    },
	    
	    D: {	//dataGrid
	    	
	    	_initDataGrid : function (grid_selector,pager_selector,_url,_colNames,_colModel){
	    		T.C.S.DridSelector = grid_selector;
	    		T.C.S.DataUrl = T.U.RewriteAjaxAction(_url);
				jQuery(grid_selector).jqGrid({
					url: _url,
					datatype: "json",
					mtype: "POST",
					height:351,
					colNames:_colNames,
					colModel:_colModel,
					viewrecords : true,
					rowNum:T.C.I.rowNum,
					rowList:T.C.I.rowList,
					pager : pager_selector,
					altRows: true,
					multiselect: true,
			        multiboxonly: true,
					loadComplete : function() {
						var table = this;
						setTimeout(function(){
							T.UI.updatePagerIcons(table);
							T.UI.enableTooltips(table);
						}, 0);
						T.UI.effectHandle();
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
			_initTreeGrid : function (grid_selector,pager_selector,_url,_colNames,_colModel){ 
				T.C.S.DridSelector = grid_selector;
	    		T.C.S.DataUrl = T.U.RewriteAjaxAction(_url);
				jQuery(grid_selector).jqGrid({
					url: _url,
					treedatatype: "xml",
					mtype: "POST",
					height:351,
					treeGrid : true,
					treeGridModel : "adjacency",
					ExpandColumn : "name",
					colNames:_colNames,
					colModel:_colModel,
					viewrecords : true,
					rowNum:T.C.I.rowNum,
					rowList:T.C.I.rowList,
					pager : pager_selector,
			        treeIcons:{
			        	plus:'fa-caret-right',
			        	minus:'fa-caret-down',
			        	leaf:'fa-meh-o'},
					loadComplete : function() {
						var table = this;
						setTimeout(function(){
							T.UI.updatePagerIcons(table);
							T.UI.enableTooltips(table);
						}, 0);
						T.UI.effectHandle();
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
			}
	    	
	    },
		UI: { // ui
			
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
			
		},
		
		 U: { // Utils
			 	ajaxUtil : function (url,target,v){  //select
				    $.ajax({
			            url:T.U.RewriteAjaxAction(url),
			            type:"POST",
			            async : false,
			            dataType:"json",
			            timeout:5000,
			            success:function(json){
			                var json = eval('('+json+')');
			                var options_str = "";
			                $(target).html("");
			                $.each(json,function(i, item){
			                        options_str += "<option value=\"" + item.id + "\" >" + item.name + "</option>";
			                });
			                $(target).append(options_str);
			                if(v){
			                T.U.Multiselect(target);
			                }
			            },
			            error:function(xhr, status){
			            	T.Box.Tips('警告','获取系统角色列表信息失败！',T.C.I.WarningClass,T.C.I.WarningIoc,T.C.I.style);
							return false;
			            }
				    })
				},
				 RewriteAjaxAction: function(url) {
			            // Quick hack: rewrite AJAX submits to HTTPS if they'd fail otherwise.
			            if (document.location.protocol == 'https:' && url.substr(0, 5) == 'http:') {
			                return url.replace(/^http:\/\/[^:\/]+/, 'https://' + document.location.host);
			            } else {
			                return url;
			            }
			        },
			        
			      FormXHR: function(form, onSuccess) {
			            $.ajax({
			                type: 'POST',
			                dataType: 'json',
			                url: T.U.RewriteAjaxAction(T.E.utf8to16(T.M.decode(form.attr('action')))),
			                data: form.serialize() ,
			                beforeSend: function(xhr) {
			                   $("#WSC_Dialog").next().find("button.btn-primary")
			                            .addClass(T.C.S.Disabled)
			                            .attr(T.C.S.Disabled, T.C.S.Disabled);
			                },
			                error: function (xhr, textStatus, errorThrown) {

			                    // Restore the form to original state.
			                    // Hopefully. :D
			                	$("#WSC_Dialog").next().find("button.btn-primary")
	                            .removeClass(T.C.S.Disabled)
	                            .removeAttr(T.C.S.Disabled, T.C.S.Disabled);
			                	T.Box.Tips('操作提示',xhr,T.C.I.ErrorClass,T.C.I.ErrorIoc,T.C.I.style);
	    						return;
			                },
			                success: function(data) {
			                	
			                	$("#WSC_Dialog").next().find("button.btn-primary")
	                            .removeClass(T.C.S.Disabled)
	                            .removeAttr(T.C.S.Disabled, T.C.S.Disabled);
			                	
			                    if (typeof (data)  != 'undefined') {
			                    	if(data.success){
			                    		jQuery(T.C.S.DridSelector).jqGrid('setGridParam', {
			            					url : T.C.S.DataUrl+"?_t=" + new Date().getTime(),
			            					mtype:'POST'
			            				}).trigger("reloadGrid");
			                    		T.C.S.Validform.resetForm();
			                    		$(T.C.S.Dialog).dialog( "close" );
			                    	T.Box.Tips('操作提示',data.msg,T.C.I.SuccessClass,T.C.I.SuccessIoc,T.C.I.style);
			    					}
			    					if(!data.success){
			    						T.Box.Tips('操作提示',data.msg,T.C.I.WarningClass,T.C.I.WarningIoc,T.C.I.style);
			    						return;
			    					}
			                        
			                    }
			                    
			                    else {
			                    	T.Box.Tips('操作提示','未知错误！',T.C.I.ErrorClass,T.C.I.ErrorIoc,T.C.I.style);
		    						return;
			                    }
			                }
			            });
			        },
			        /**
			         * Ajax请求方法
			         * 
			         * @param {Object}
			         *            settings.params 参数对象，必须
			         * @param {String}
			         *            settings.url 请求地址，必须
			         * @param {Function}
			         *            settings.callback 成功后回调方法，必须
			         * @param {boolean}
			         *            settings.showMsg 处理成功时，是否显示提示信息 true:显示 false:不显示
			         * @param {boolean}
			         *            settings.showWaiting 是否显示等待条 true:显示 false:不显示
			         * @param {Number}
			         *            settings.timeout 超时时间
			         * @param {String}
			         *            settings.successMsg 成功消息
			         * @param {String}
			         *            settings.failureMsg 失败消息
			         */
			        AjaxRequest : function (settings) {
			            // 参数对象
			            var params = settings.params === undefined ? {} : settings.params,
			             showWaiting = settings.showWaiting === undefined ? true : settings.showWaiting,
			             showMsg = settings.showMsg === undefined ? true : settings.showMsg,
			             timeout = settings.timeout === undefined ? 60 * 1000 : settings.timeout,
			             async = settings.async === undefined ? false : settings.timeout,
			             type = settings.type === undefined ? "GET" : settings.type,
			             dataType = settings.dataType === undefined ? "json" : settings.dataType,
			            // 发送请求
			             waiting = null;
			            if (showWaiting) {
			            	waiting = true;
			            	T.Box.Tips('操作提示','正在处理，请稍等...',T.C.I.InfoClass,T.C.I.InfoIoc,T.C.I.style);
			            	
			            }
			            $.ajax({
			                url: settings.url,
			                type:type,
			                dataType:dataType,
			                data: params,
			                async : false,
			                timeout: timeout,
			                success: function (response, options) {
			                    if (waiting != null) {
			                    	$.gritter.removeAll();
			                    }
			                        if (settings.callback) { // 回调方法
			                            settings.callback(response);
			                        }
			                    },
				            error:function(xhr, status){
				            	if (settings.callbackError) { // 回调方法
		                            settings.callbackError(xhr);
		                        }
				            	
								return false;
				            }
			            });
			        },
			        Multiselect : function (t){
			        	$(t).multiselect({
							 buttonClass: 'btn btn-white btn-primary',
							 templates: {
								button: '<button type="button" class="multiselect col-xs-10 dropdown-toggle" data-toggle="dropdown"></button>',
								ul: '<ul class="multiselect-container dropdown-menu"></ul>',
								filter: '<li class="multiselect-item filter"><div class="input-group"><span class="input-group-addon"><i class="fa fa-search"></i></span><input class="form-control multiselect-search" type="text"></div></li>',
								filterClearBtn: '<span class="input-group-btn"><button class="btn btn-default btn-white btn-grey multiselect-clear-filter" type="button"><i class="fa fa-times-circle red2"></i></button></span>',
								li: '<li><a href="javascript:void(0);"><label></label></a></li>',
								divider: '<li class="multiselect-item divider"></li>',
								liGroup: '<li class="multiselect-item group"><label class="multiselect-group"></label></li>'
								
							 },
							 maxHeight: 200
							});
			        },
			        Datepicker : function (date){
			        	$(date).datepicker({
							showOtherMonths: true,
							selectOtherMonths: false,
							dateFormat: 'yy-mm-dd',
							dayNamesMin: ['日', '一', '二', '三', '四', '五', '六'] 
							//isRTL:true,
					
							
							/*
							changeMonth: true,
							changeYear: true,
							
							showButtonPanel: true,
							beforeShow: function() {
								//change button colors
								var datepicker = $(this).datepicker( "widget" );
								setTimeout(function(){
									var buttons = datepicker.find('.ui-datepicker-buttonpane')
									.find('button');
									buttons.eq(0).addClass('btn btn-xs');
									buttons.eq(1).addClass('btn btn-xs btn-success');
									buttons.wrapInner('<span class="bigger-110" />');
								}, 0);
							}
					*/
						});
					
			        }
			        
		 },
	    Box: { //dialog
	    	
	    	WSC_ShowDialog: function (url, width, title) {
	    		   if (!title) {
	    			      title = "WSC";
	    			   }
	    		    $.ajax({  
	    	              type : "GET",  
	    	              url : T.U.RewriteAjaxAction(url),  
	    	              async : false,  
	    	              success : function(html){  
	    	       		   //容器
		    				   var id = "WSC_Dialog";
		    				   var container = $("#" + id);
		    				   if (container.size() == 0) { //不存在则创建

		    				      var loadingDiv = "<div id='WSC_JQueryDialogLoading' class='contentSectionWhite' style='display: block; background-color:White; width:92px; height:20px; margin:  10% auto auto 45%; position:absolute;'>";
		    				      loadingDiv = loadingDiv + "<div class=\"xubox_main\" style=\"z-index: 19891121; width: 60px; height: 24px;\"><span class=\"xubox_loading xubox_loading_0 show\"></span><span class=\"xubox_setwin\"></span><span class=\"xubox_botton\"></span></div></div>";
		    				      container = $("<div id='WSC_JQueryDialogAutoGenDiv' class='ui-helper-hidden' style='z-index:999990;'>" + loadingDiv + "</div>").attr("id", id)
		    				            .append(html) //内容容器
		    				            .appendTo($(document.body))
		    				            .dialog({
		    							   modal: true,
		    							   title: title,
		    							   title_html: true,
		    				               minWidth: 320,
		    				               minHeight: 220,
		    				               open: function (event, ui) {
		    				            	   T.Box.DelayToShowLoading();
		    				               },
		    				               buttons: [ 
		    											{
		    												html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确认",
		    												"class" : "btn btn-primary btn-xs",
		    												click: function() {
		    													var flag = T.C.S.Validform.check(false);
		    											      	if(!flag) return;
		    											      	var param = $('#estype_add_form');
		    											      	T.U.FormXHR(param); return false; 
		    													$( this ).dialog( "close" );
		    												}
		    											},
		    											{
		    												html: "<i class='ace-icon fa fa-times bigger-110'></i>&nbsp; 取消",
		    												"class" : "btn btn-xs",
		    												click: function() {
		    													T.C.S.Validform.resetForm();
		    													$( this ).dialog( "close" );
		    												}
		    											}
		    										]
		    				            });
		    				   }
		    				   $("#WSC_JQueryDialogLoading").css("display", "block");
		    				   //标题
		    				   container.dialog("option", "title", title);
		    				   //尺寸
		    				   container.dialog("option", "width", width);
		    				   //呈现
		    				   container.dialog("open");
		    				   T.C.S.i = 1;
		    				   T.C.S.Validform = $("#estype_add_form").Validform({"tiptype":3,ignoreHidden:true});
	    	              }  
	    	              });  
	    			 
	    			},
	    			DelayToShowLoading: function () {
	    			   T.C.I.count++;
	    			   if (T.C.I.count > 2) {
	    			      $("#WSC_JQueryDialogLoading").css("display", "none");
	    			      T.C.I.count = 0;
	    			      return true;
	    			   }
	    			   window.setTimeout("T.Box.DelayToShowLoading()", 300);
	    			},
	    			
	    			Tips : function (title ,text,className,ioc,style,showButton ){
	    				$.gritter.add({
							title: title,
							text: text,
							image:ioc,
							sticky: false,
							showButton: showButton,
							style:style,
							class_name: className,
							before_open: function(){
				                    if($('.gritter-item-wrapper').length == 2) {
				                        return false;
				                    }
							 }
						});
	    			},
	    			
	    			Close:function(){
	    				$.gritter.removeAll();
	    	        	return false;
	    			}
	    },
	    
	    M:{  //MD5
	    	
	    	/*** 编码 */
			encode: function(src){
			        //用一个数组来存放编码后的字符，效率比用字符串相加高很多。
			        var str=new Array();
			        var ch1, ch2, ch3;
			        var pos=0;
			       //每三个字符进行编码。
			        while(pos+3<=src.length){
			            ch1=src.charCodeAt(pos++);
			            ch2=src.charCodeAt(pos++);
			            ch3=src.charCodeAt(pos++);
			            str.push(T.C.S.enKey.charAt(ch1>>2), T.C.S.enKey.charAt(((ch1<<4)+(ch2>>4))&0x3f));
			            str.push(T.C.S.enKey.charAt(((ch2<<2)+(ch3>>6))&0x3f), T.C.S.enKey.charAt(ch3&0x3f));
			        }
			        //给剩下的字符进行编码。
			        if(pos<src.length){
			            ch1=src.charCodeAt(pos++);
			            str.push(T.C.S.enKey.charAt(ch1>>2));
			            if(pos<src.length){
			                ch2=src.charCodeAt(pos);
			                str.push(T.C.S.enKey.charAt(((ch1<<4)+(ch2>>4))&0x3f));
			                str.push(T.C.S.enKey.charAt(ch2<<2&0x3f), '=');
			            }else{
			                str.push(T.C.S.enKey.charAt(ch1<<4&0x3f), '==');
			            }
			        }
			       //组合各编码后的字符，连成一个字符串。
			        return str.join('');
			    },
			    
			/*** 解码。 */
			decode: function(src){
			        //用一个数组来存放解码后的字符。
			        var str=new Array();
			        var ch1, ch2, ch3, ch4;
			        var pos=0;
			       //过滤非法字符，并去掉'='。
			        src=src.replace(/[^A-Za-z0-9\+\/]/g, '');
			        //decode the source string in partition of per four characters.
			        while(pos+4<=src.length){
			            ch1=T.C.S.deKey[src.charCodeAt(pos++)];
			            ch2=T.C.S.deKey[src.charCodeAt(pos++)];
			            ch3=T.C.S.deKey[src.charCodeAt(pos++)];
			            ch4=T.C.S.deKey[src.charCodeAt(pos++)];
			            str.push(String.fromCharCode(
			                (ch1<<2&0xff)+(ch2>>4), (ch2<<4&0xff)+(ch3>>2), (ch3<<6&0xff)+ch4));
			        }
			        //给剩下的字符进行解码。
			        if(pos+1<src.length){
			            ch1=T.C.S.deKey[src.charCodeAt(pos++)];
			            ch2=T.C.S.deKey[src.charCodeAt(pos++)];
			            if(pos<src.length){
			                ch3=T.C.S.deKey[src.charCodeAt(pos)];
			                str.push(String.fromCharCode((ch1<<2&0xff)+(ch2>>4), (ch2<<4&0xff)+(ch3>>2)));
			            }else{
			                str.push(String.fromCharCode((ch1<<2&0xff)+(ch2>>4)));
			            }
			        }
			       //组合各解码后的字符，连成一个字符串。
			        return str.join('');
			    }
	    	
	    },
	    
	    E : {  //encoded
	    	 /** 转码*/
			 utf16to8:function(str) {
	              var out, i, len, c;  
	              out = "";  
	              len = str.length;  
	              for(i = 0; i < len; i++) {  
	               c = str.charCodeAt(i);  
	               if ((c >= 0x0001) && (c <= 0x007F)) {  
	                out += str.charAt(i);  
	               } else if (c > 0x07FF) {  
	                out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));  
	                out += String.fromCharCode(0x80 | ((c >> 6) & 0x3F));  
	                out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));  
	               } else {  
	                out += String.fromCharCode(0xC0 | ((c >> 6) & 0x1F));  
	                out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));  
	                    }  
	                }
	                return out;  
			      },
			 /** 转码*/
			 utf8to16:function(str) {  
	                var out, i, len, c;  
	                var char2, char3;  
	                out = "";  
	                len = str.length;  
	                i = 0;  
	                while(i < len) {  
	               c = str.charCodeAt(i++);  
	               switch(c >> 4)   {  
	                case 0: case 1: case 2: case 3: case 4: case 5: case 6: case 7:  
	                 // 0xxxxxxx  
	                 out += str.charAt(i-1);  
	                 break;  
	                case 12: case 13:  
	                 // 110x xxxx　 10xx xxxx  
	                    char2 = str.charCodeAt(i++);  
	                 out += String.fromCharCode(((c & 0x1F) << 6) | (char2 & 0x3F));  
	                 break;  
	                case 14:  
	                // 1110 xxxx　10xx xxxx　10xx xxxx  
	                    char2 = str.charCodeAt(i++);  
	                 char3 = str.charCodeAt(i++);  
	                out += String.fromCharCode(((c & 0x0F) << 12) |  
	                ((char2 & 0x3F) << 6) |  
	                ((char3 & 0x3F) << 0));  
	                 break;  
	                		}
	                	}
	                	return out;  
			 		}
	    	}
};





	