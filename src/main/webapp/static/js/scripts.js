
jQuery(document).ready(function() {
	//登录界面JS事件
	$("#submitForm").bind('click', function() {
			$.messager.progress();
			$('#login').form('submit', {
			    url : 'login?t='+Math.random()+'&userAgent='+navigator.userAgent,
				onSubmit: function(){
					var isValid = $(this).form('validate');
					if (!isValid){
						$.messager.progress('close');	
					}
					return isValid;	
				},
				success : function(data) {
					$.messager.progress('close');
					var result=eval('('+data+')');
					if(result.success){
						window.location.href='main';
						return;
					}
					
					if(result.o == "1"){
						$.messager.alert('操作提示', result.msg, 'info');
						var url = 'checkimage.jpg?t=' + Math.random();
						$('#verifyCode').attr('src', url);
						return;
					}
					
					if(!result.success){
						$.messager.alert('操作提示', result.msg, 'info');
						return;
					}
					
				}
			});
		});
    
		//登录验证码
	
		$("#verifyClick").on("click", function() {
			var url = 'checkimage.jpg?t=' + Math.random();
			$('#verifyCode').attr('src', url);
		});
		
		
		$("#rollBack").bind('click',function(){
			$.ajax({
				url:'${sysPath}admin/rollBack.html',
				dataType :'json',
			    beforeSend: function(){  
			        $.messager.progress();
			    },
			    complete: function(){  
			        $.messager.progress('close'); 
			    },
			    success : function(data) { 
			    	$.messager.alert("操作提示", data.msg);
			    }
			});
    });
		
		//忘记密码
		$("#resetPassword").bind('click', function() {
			$('<div/>').dialog({
				href : 'forgotPassword',
				modal : true,
				title : '系统将发送重置密码链接到你的注册邮箱！',
				width : 600,
				height: 300,
				resizable:true,
				buttons : [ {
					text : '发送',
					iconCls : 'icon-ok',
					handler : function() {
						$.messager.progress();
						$('#forgetPasswordForm').form('submit', {
						    url : 'findPassword',
							onSubmit: function(){
								var isValid = $(this).form('validate');
								if (!isValid){
									$.messager.progress('close');	
								}
								return isValid;	
							},
							success : function(data) {
								$.messager.progress('close');
								var result=eval('('+data+')');
								if(result.success){
									$.messager.alert('操作提示', result.msg, 'info');
									return;
								}
								
								if(result.o == "1"){
									$.messager.alert('操作提示', result.msg, 'info');
									var url = 'checkimage.jpg?t=' + Math.random();
									$('#forgetVerifyCode').attr('src', url);
									return;
								}
								
								if(!result.success){
									$.messager.alert('操作提示', result.msg, 'info');
									return;
								}
							}
						});
					}
				}, {
					text : '取消',
					iconCls : 'icon-cancel',
					handler : function() {
					$(this).dialog('destroy');					}
				} ],
				onClose : function() {
					$(this).dialog('destroy');
				},
				
			});
		});

});
