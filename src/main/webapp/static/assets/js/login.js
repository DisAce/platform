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
                    	if(data.o == "1"){
                    		$('#gritter-notice-wrapper').remove();
                    		T.Box.Tips('操作提示',data.msg,T.C.I.WarningClass,T.C.I.WarningIoc,T.C.I.style);
    						var url = 'Authentication/captchaImage.do?t=' + Math.random();
    						$('#verifyCode').attr('src', url);
    						return;
    					}
    					if(!data.success){
    						$('#gritter-notice-wrapper').remove();
    						T.Box.Tips('操作提示',data.msg,T.C.I.WarningClass,T.C.I.WarningIoc,T.C.I.style);
    						return;
    					}
    					if(!data.success){
    						$('#gritter-notice-wrapper').remove();
    						T.Box.Tips('操作提示',data.msg,T.C.I.WarningClass,T.C.I.WarningIoc,T.C.I.style);
    						return;
    					}
                    }else {
                    	$('#gritter-notice-wrapper').remove();
                    	T.Box.Tips('操作提示','服务器出现致命错误、请于管理员联系！',T.C.I.ErrorClass,T.C.I.ErrorIoc,T.C.I.style);
						return;
                    }	
					},
					callbackError: function(data) {
						T.Box.Tips('操作提示','未知错误！',T.C.I.ErrorClass,T.C.I.ErrorIoc,T.C.I.style);
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
			params:form.serialize(),
			callback : function(data) {
				   if (typeof (data)  != 'undefined') {
                    	if(data.success){
                    		$('#gritter-notice-wrapper').remove();
                    		T.Box.Tips('操作提示',data.msg,T.C.I.SuccessClass,T.C.I.SuccessIoc,T.C.I.style);
                    		$('.back-to-login-link').click();
    						return;
    					}
                    	if(data.o == "1"){
                    		$('#gritter-notice-wrapper').remove();
                    		T.Box.Tips('操作提示',data.msg,T.C.I.WarningClass,T.C.I.WarningIoc,T.C.I.style);
    						var url = 'Authentication/captchaImage.do?t=' + Math.random();
    						$('#verifyCode').attr('src', url);
    						return;
    					}
    					if(!data.success){
    						$('#gritter-notice-wrapper').remove();
    						T.Box.Tips('操作提示',data.msg,T.C.I.WarningClass,T.C.I.WarningIoc,T.C.I.style);
    						return;
    					}
    					if(!data.success){
    						$('#gritter-notice-wrapper').remove();
    						T.Box.Tips('操作提示',data.msg,T.C.I.WarningClass,T.C.I.WarningIoc,T.C.I.style);
    						return;
    					}
                    }else {
                    	$('#gritter-notice-wrapper').remove();
                    	T.Box.Tips('操作提示','服务器出现致命错误、请于管理员联系！',T.C.I.ErrorClass,T.C.I.ErrorIoc,T.C.I.style);
						return;
                    }	
					},
					callbackError: function(data) {
						T.Box.Tips('操作提示','未知错误！',T.C.I.ErrorClass,T.C.I.ErrorIoc,T.C.I.style);
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
