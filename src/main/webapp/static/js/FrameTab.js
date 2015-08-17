/**
 * 通过工具类
 */
var frameTab = {
	// 添加 tab 页
		addTab:function (data,isReload) {
			var url , title ,splits ;
			splits = Share.utf8to16(Share.decode(data)).split("&");
			url = splits[0];title = splits[1];
			url = url || "about:blank"; //判断url
			title = title || "无标题"; 
			frameTab.inactiveAll();
		if(isReload){
			frameTab.removeTab(title);
			var myTabPanel = $('#myTab');
			var myTabContext = $('#myTabContext');
			var li = frameTab.createTitle(title);
			myTabPanel.append(li);
			var context = frameTab.createContext(title, url);
			myTabContext.append(context);
			frameTab.activeTab(title);
			return;
		}
		if (frameTab.findTab(title)) {
			frameTab.activeTab(title);	
			return;
		}
		var myTabPanel = $('#myTab');
		var myTabContext = $('#myTabContext');
		var li = frameTab.createTitle(title);
		myTabPanel.append(li);
		var context = frameTab.createContext(title, url);
		myTabContext.append(context);
		frameTab.activeTab(title);
	},

	/*
	* 将所有tab标题li元素宽度恢复成原始值
	*/
	recoverLiWidth : function (){
		var $liArr = $('#myTab').children('li[outerwidth]');
		$.each($liArr,function(){
			$(this).width($(this).attr('outerwidth'));
		});
	},

	setOuterWidth : function (){
		var $activeLi = $('#myTab').children('li.active');
		$activeLi.attr('outerwidth',$activeLi.outerWidth());
	},

	showPrev : function (){
		frameTab.adjustTab('leftArrow');
	},
	

	showNext : function (){
		frameTab.adjustTab('rightArrow');
	},

	getAllVisibleTabLi : function (){
		var $leftArrow = $('#leftArrow');
		var $allVisibleTabLi = $leftArrow.nextAll(':not(#rightArrow)').not('.hide');
		return  $allVisibleTabLi;

	},

	getTotalVisibleTabLiWidth : function (){
		var $allVisibleTabLi = frameTab.getAllVisibleTabLi();
		var allVisibleTabLiWidth = 0 ;
		$.each($allVisibleTabLi,function(){
			allVisibleTabLiWidth += $(this).outerWidth();
		});

		return allVisibleTabLiWidth;
	},

	getTotalVisibleTabLiOuterWidth : function (){
		var $allVisibleTabLi = frameTab.getAllVisibleTabLi();
		var allVisibleTabLiWidth = 0 ;
		$.each($allVisibleTabLi,function(){
			allVisibleTabLiWidth += parseFloat($(this).attr('outerWidth'));
		});

		return allVisibleTabLiWidth;
	},


	adjustTab : function (markName){
		frameTab.recoverLiWidth();
			var $leftArrow = $('#leftArrow');
			var $rightArrow = $('#rightArrow');
			var $myTab = $('#myTab');
			var maxVisibleTabWidth = $myTab.width();//tab最大可显示的长度
			var allVisibleTabLiWidth = 0 ;//所有可视化tab li总宽度
			var $allVisibleTabLi;
			
			//---------------若所有tab（包括隐藏的）的总长度小于或等于最大可视化长度则左右箭头隐藏
			var $allTabLi = $leftArrow.nextAll('li').not('#rightArrow');
			var allTabLiWidth = 0 ;
			$.each($allTabLi,function(){
				allTabLiWidth += parseFloat($(this).attr('outerwidth'));
			});

			if(maxVisibleTabWidth >= allTabLiWidth){
				$leftArrow.nextAll(':not(#rightArrow)').removeClass('hide');
				$rightArrow.addClass('hide');
				$leftArrow.addClass('hide');
				return ;
			}	
		    //-------------------------------------------------------------------------------------

		    //------------------------------------------------------如果是左右箭头点击事件
		    if('leftArrow' == markName){
		    	$myTab.children('li[id!=leftArrow]').not('.hide').first().prev().removeClass('hide'); // 显示
				$myTab.children('li[id!=rightArrow]').not('.hide').last().addClass('hide'); // 隐藏
		    }


		    if('rightArrow' == markName){
		    	$myTab.children('li[id!=rightArrow]').not('.hide').last().next().removeClass('hide'); //显示
				$myTab.children('li[id!=leftArrow]').not('.hide').first().addClass('hide'); //隐藏
		    }
		    //---------------------------------------------------------------------------------------


		    //-------------------------若所有可视的tab总长度大于最大可视化长度，则隐藏距离左键头最近的一个tab
		    $allVisibleTabLi = frameTab.getAllVisibleTabLi();
			allVisibleTabLiWidth = frameTab.getTotalVisibleTabLiWidth();


			if(maxVisibleTabWidth < allVisibleTabLiWidth || $myTab.height() > 35){
				$leftArrow.removeClass('hide'); //显示做左箭头
				$myTab.append($rightArrow.removeClass('hide')); //显示右箭头

				if(!markName){
					//隐藏距离左键头最近的一个非隐藏的tab
					$myTab.children('li').not('#leftArrow').not('.hide').first().addClass('hide');
				}
				
				$allVisibleTabLi = frameTab.getAllVisibleTabLi();
				allVisibleTabLiWidth = frameTab.getTotalVisibleTabLiWidth() ;
				allVisibleTabLiWidth += $('#leftArrow').width();
				allVisibleTabLiWidth += $('#rightArrow').width();
				if(maxVisibleTabWidth < allVisibleTabLiWidth || $myTab.height() > 35){
					adjustTab();
				}
			}
			//-----------------------------------------------------------------------------------------------------
						
			$myTab.append($rightArrow.removeClass('hide'));

			allVisibleTabLiWidth = frameTab.getTotalVisibleTabLiWidth();
			allVisibleTabLiWidth += $('#leftArrow').outerWidth();
			allVisibleTabLiWidth += $('#rightArrow').outerWidth();
			$allVisibleTabLi = frameTab.getAllVisibleTabLi();
			var amountWidth = parseInt((maxVisibleTabWidth - allVisibleTabLiWidth)/($allVisibleTabLi.length));
			$.each($allVisibleTabLi,function(){
				$(this).width($(this).width() + amountWidth);
			});


			if($rightArrow.prev().hasClass('hide')){
				$rightArrow.attr('onclick','frameTab.showNext();');
			}else{
				$rightArrow.removeAttr('onclick');
			}

			if($leftArrow.next().hasClass('hide')){
				 $leftArrow.attr('onclick','frameTab.showPrev();');
			}else{
				 $leftArrow.removeAttr('onclick');
			}
	},


	createTitle : function (title) {
		var _title = Share.hashCode(title);
		_title = $.trim(_title);
		if('1257887' == _title){
			var $tabLi = $('#myTab').children('li.home').first().clone();
			$tabLi.removeClass('hide').attr({'id' : 'li_' + _title,'sel' : 'tab_' + _title,'menuname' : _title});
			$tabLi.children('a').attr('href','#tab_' + _title).append(title);
			return $tabLi;
		}else{
			var $tabLi = $('#myTab').children('li:not(.home)').first().clone();
			$tabLi.removeClass('hide').attr({'id' : 'li_' + _title,'sel' : 'tab_' + _title,'menuname' : _title});
			$tabLi.children('a').attr('href','#tab_' + _title);
			$tabLi.find('button').before(title);
			$tabLi.find('button').attr('onclick','frameTab.removeTab("' + _title + '",true)');
			return $tabLi;
		}
		
	},

	createContext : function (title, url) {
		title = Share.hashCode(title);
		title = $.trim(title);
		url = $.trim(url);
		var $tabContext = $('#myTabContext').children('div').first().clone();
		$tabContext.removeClass('hide').attr('id','tab_' + title);
		$tabContext.children('iframe').attr({'src' : url , 'id' : 'mainFrame_' + title,'onload':'frameTab.adjustIFramesHeightOnLoad(this);'});
		return $tabContext;
	},

	// 禁用全部 tab页
	inactiveAll : function () {
		var $allTabLi = $('#myTab li');
		$allTabLi.removeClass('active');
		var $allTabContent = $('#myTabContext div');
		$allTabContent.removeClass('active');
	},

	// 查找是否已经存在
	findTab : function (title) {
		title = Share.hashCode(title);
		var $tab = $('#li_' + title);
		var $context = $('#tab_' + title);
		return $tab.size() > 0  && $context.size() > 0;
	},
	
	// 激活Tab
	activeTab : function (title,i) {
		if(!i){
			title = Share.hashCode(title);
		}
		var li = $('#li_' + title);
		li.siblings().removeClass('active');
		li.attr('class', 'active');
		var context = $('#tab_' + title);
		context.attr('class', 'tab-pane active');
		frameTab.setOuterWidth();
		frameTab.adjustTab();
	},
	
	
	
	// 关闭 tab页
	// 模仿浏览器的标签页关闭行为
	removeTab : function (title,isShowNextTab) {
		var $li = $('#li_' + title);
		var $context = $('#tab_' + title);
		var $nextLi = $li.next().not('.hide').not('#rightArrow');
		var $prevLi = $li.prev().not('.hide').not('#leftArrow');
		var menuName = $nextLi.length > 0 ? $nextLi.attr('menuname') : $prevLi.attr('menuname');
		$li.remove();
		$context.remove();
		if(isShowNextTab){
			if($li.hasClass('active')){
				frameTab.activeTab(menuName,1);
			}
			frameTab.showFirstHideLi();
		}
	},


	showFirstHideLi : function  (){
		var $leftArrow = $('#leftArrow');
		var $rightArrow = $('#rightArrow');

		if(!$rightArrow.hasClass('hide')){
			var $lastNotHideLi = $('#myTab').children('li').not('.hide').not('#leftArrow').not('#rightArrow').not('.template').last();
			var $nextHideLi = $lastNotHideLi.next('.hide');
			if($nextHideLi.length > 0){
				$nextHideLi.removeClass('hide');

				var maxVisibleTabWidth = $('#myTab').width();//tab最大可显示的长度
				var allVisibleTabLiWidth = frameTab.getTotalVisibleTabLiOuterWidth();
				
				allVisibleTabLiWidth += $('#leftArrow').outerWidth();
				allVisibleTabLiWidth += $('#rightArrow').outerWidth();

				if(allVisibleTabLiWidth > maxVisibleTabWidth){
					$nextHideLi.addClass('hide');
					allVisibleTabLiWidth = frameTab.getTotalVisibleTabLiOuterWidth();
					allVisibleTabLiWidth += $('#leftArrow').outerWidth();
					allVisibleTabLiWidth += $('#rightArrow').outerWidth();
				}

				$allVisibleTabLi = getAllVisibleTabLi();
				var amountWidth = parseInt((maxVisibleTabWidth - allVisibleTabLiWidth)/($allVisibleTabLi.length));
				$.each($allVisibleTabLi,function(){
					$(this).width(parseFloat($(this).attr('outerwidth')) + amountWidth);
				});
				return ;
			}	
		}



		if(!$leftArrow.hasClass('hide')){
			var $firstNotHideLi = $('#myTab').children('li').not('.hide').not('#leftArrow').not('#rightArrow').not('.template').first();
			var $prevHideLi = $firstNotHideLi.prev('.hide');

			if($prevHideLi.length > 0){
				$prevHideLi.removeClass('hide');

				var maxVisibleTabWidth = $('#myTab').width();//tab最大可显示的长度
				var allVisibleTabLiWidth = frameTab.getTotalVisibleTabLiOuterWidth();
				allVisibleTabLiWidth += $('#leftArrow').outerWidth();
				allVisibleTabLiWidth += $('#rightArrow').outerWidth();

				if(allVisibleTabLiWidth > maxVisibleTabWidth){
					$nextHideLi.addClass('hide');
					allVisibleTabLiWidth = getTotalVisibleTabLiOuterWidth();
					allVisibleTabLiWidth += $('#leftArrow').outerWidth();
					allVisibleTabLiWidth += $('#rightArrow').outerWidth();
				}

				$allVisibleTabLi = getAllVisibleTabLi();
				var amountWidth = parseInt((maxVisibleTabWidth - allVisibleTabLiWidth)/($allVisibleTabLi.length));
				$.each($allVisibleTabLi,function(){
					$(this).width(parseFloat($(this).attr('outerwidth')) + amountWidth);
				});
				return;
			}
		}
	},


	//iframe 自适应
	adjustIFramesHeightOnLoad : function (iframe) { 
		var pWindowHeight = $(window).height();
		var amountHeight = pWindowHeight - $('#navbar').outerHeight() - $('#myTab').outerHeight() -9 ;
		 
		var ifrmDocumentHeight = $(iframe.contentWindow.window.document).height();
		ifrmDocumentHeight = Math.max(ifrmDocumentHeight,amountHeight);	
		$(iframe).height(ifrmDocumentHeight); 
	}
};