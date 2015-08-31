jQuery(document).ready(function() {
	
		$('#btn-login-dark').on('click', function(e) {
			$('body').attr('class', 'login-layout');
			$('#id-text2').attr('class', 'white');
			$('#id-company-text').attr('class', 'blue');
			
			e.preventDefault();
		 });
		 $('#btn-login-light').on('click', function(e) {
			$('body').attr('class', 'login-layout light-login');
			$('#id-text2').attr('class', 'grey');
			$('#id-company-text').attr('class', 'blue');
			
			e.preventDefault();
		 });
		 $('#btn-login-blur').on('click', function(e) {
			$('body').attr('class', 'login-layout blur-login');
			$('#id-text2').attr('class', 'white');
			$('#id-company-text').attr('class', 'light-blue');
			
			e.preventDefault();
		 });
		 
		$("#resetUserPassword").on( 'click', function(e) {
			var form = $('#resetPasswordFrom');
			e.preventDefault();
			T.U.AjaxRequest({
			url : T.U.RewriteAjaxAction('resetpwd'),
			type:"POST",
			params:form.serialize(),
			callback : function(data) {
				   if (typeof (data)  != 'undefined') {
                    	if(data.success){
                    		window.location.href='main';
    						return;
    					}
                    	if(data.success){
                    		layer.msg('密码重置成功，请重新登录！', {icon: 1});
                    		window.location.href='login';
    						return;
    					}
    					if(!data.success){
    						if(data.resultCode == 340136){
    							layer.msg('修改密码失败！', {icon: 5});
        						return;
    						}
    						if(data.resultCode == 340096){
    							layer.msg('新密码不能为空！', {icon: 5});
        						return;
    						}
    						if(data.resultCode == 340106){
    							layer.msg('确认密码不能为空！', {icon: 5});
        						return;
    						}
    						if(data.resultCode == 340116){
    							layer.msg('两次输入的密码不一致！', {icon: 5});
        						return;
    						}
    					}
    					
                    }else {
                    	layer.msg('服务器出现致命错误、请于管理员联系！', {icon: 2});
						return;
                    }	
					},
					callbackError: function(data) {
						layer.msg('未知错误！', {icon: 2});
						return;
					}
				});
				return false;
			});	

});
