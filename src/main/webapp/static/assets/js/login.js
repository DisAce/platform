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
		 
		$('#verifyClick').on('click', function() {
			var url = 'Authentication/captchaImage.do?t=' + Math.random();
			$('#verifyCode').attr('src', url);
			return false; 
		 });
	
		$("#loginAction").on( 'click', function(e) {
			var form = $('#login');
			e.preventDefault();
			T.U.AjaxRequest({
			url : T.U.RewriteAjaxAction('login?t='+Math.random()+'&userAgent=')+navigator.userAgent,
			type:"POST",
			params:form.serialize(),
			callback : function(data) {
				   if (typeof (data)  != 'undefined') {
                    	if(data.success){
                    		window.location.href='main';
    						return;
    					}
    					if(!data.success){
    						if(data.resultCode == 20000){
    							layer.msg(data.o, {icon: 2});
        						return;
    						}
    						if(data.resultCode == 22022){
    							layer.msg('用户名不能为空，请重新输入！', {icon: 5});
        						return;
    						}
    						if(data.resultCode == 340086){
    							layer.msg('密码不能为空，请重新输入！', {icon: 5});
        						return;
    						}
    						if(data.resultCode == 600025){
    							layer.msg('验证码不能为空，请重新输入！', {icon: 5});
        						return;
    						}
    						if(data.resultCode == 600036){
    							layer.msg('验证码不正确，请重新输入！', {icon: 5});
        						return;
    						}
    						if(data.resultCode == 350086){
    							layer.msg('用户名或密码错误，请重新输入！', {icon: 5});
        						return;
    						}
    						if(data.resultCode == 600026){
    							layer.msg('验证码失效，请重新输入！', {icon: 5});
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
		//忘记密码
		
		$("#resetPassword").on( 'click', function(e) {
			var form = $('#forgetPasswordForm');
			e.preventDefault();
			T.U.AjaxRequest({
			url : T.U.RewriteAjaxAction('findPassword'),
			type:"POST",
			dataType:"json",
			params:form.serialize(),
			callback : function(data) {
				   if (typeof (data)  != 'undefined') {
                    	if(data.success){
                    		if(data.resultCode == 25340){
    							layer.msg('邮件发送成功，请查收！', {icon: 1});
    							$('.back-to-login-link').click();
        						return;
    						}
    					}
                    
    					if(!data.success){
    						if(data.resultCode == 22022){
    							layer.msg('用户名不能为空，请重新输入！', {icon: 5});
        						return;
    						}
    						if(data.resultCode == 20001){
    							layer.msg('验证邮箱不能为空，请重新输入！', {icon: 5});
        						return;
    						}
    						if(data.resultCode == 600025){
    							layer.msg('验证码不能为空，请重新输入！', {icon: 5});
        						return;
    						}
    						if(data.resultCode == 600036){
    							layer.msg('验证码不正确，请重新输入！', {icon: 5});
        						return;
    						}
    						if(data.resultCode == 600026){
    							layer.msg('验证码失效，请重新输入！', {icon: 5});
        						return;
    						}
    						if(data.resultCode == 20000){
    							layer.msg('请输入正确的帐号和其注册邮箱！', {icon: 5});
        						return;
    						}
    						if(data.resultCode == 25341){
    							layer.msg('邮件发送失败，请稍后重试！', {icon: 5});
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
		
		$('#findVerifyClick').on('click', function() {
			var url = 'Authentication/captchaImage.do?t=' + Math.random();
			$('#findVerifyCode').attr('src', url);
			return false; 
		 });

});
