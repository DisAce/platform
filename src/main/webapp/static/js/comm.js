 
/**
 *实用工具类
 */

var WEB_UTIL; 

WEB_UTIL = {

    ltrim: function (text) {
        return text == null ? 
                "" :
                text.toString().replace(/^\s+/ ,"")
    }
	
	,site_path: function () {
	 
		var curWwwPath = window.document.location.href;
		
		var pathName = window.document.location.pathname;
		//
		var pos = curWwwPath.indexOf(pathName);
		
		var localhostPath = curWwwPath.substring(0, pos);
		
		var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
		//
		var RootPath = localhostPath + projectName + '/';
		//
		return RootPath;
	
	}

   ,rtrim: function (text) {
        return text == null ? 
                "" :
                text.toString().replace(/\s+$/ ,"")
   }

   ,trim: function (text) {
       return this.ltrim(this.rtrim(text));
   }

   ,msglen: function (text) { // 微博字数计算规则 汉字 1 英文 0.5 网址 11 除去首尾空白
        text = text.replace(new RegExp("((news|telnet|nttp|file|http|ftp|https)://){1}(([-A-Za-z0-9]+(\\.[-A-Za-z0-9]+)*(\\.[-A-Za-z]{2,5}))|([0-9]{1,3}(\\.[0-9]{1,3}){3}))(:[0-9]*)?(/[-A-Za-z0-9_\\$\\.\\+\\!\\*\\(\\),;:@&=\\?/~\\#\\%]*)*","gi"),'填充填充填充填充填充填');
        return Math.ceil(($.trim(text.replace(/[^\u0000-\u00ff]/g,"aa")).length)/2);
   }

   ,limit: function (text ,max ,suff) {
        if (text == null) {
            return "";
        }
      
        text = text.toString();
       
        suff = suff || "...";
        if (text.length <= max) {
            return text;
        } else {
            return text.substring(0,max) + suff;
        }
   }

   ,limitFileName: function (filename,max) {
        max = max || 10;
        var fileExt;
        var fileNameArr = filename.split(".");
        if (fileNameArr.length > 1) {
            fileExt = fileNameArr.splice(-1);
            return this.limit(fileNameArr.join("") ,max ,"") + "." + fileExt.join("");
        } else {
            return this.limit(fileNameArr[0] ,max ,"");
        }
   }

  

   // 向text holder插入指定的文本后高亮选中，若已有指定的文本则只高亮

   ,highlightOrInsert: function (text ,holder ,trailer ,suff) {
       trailer = trailer || "#";
       suff = suff || "";
       var range;
       var holderText = holder.value || "";
       var start = holderText.lastIndexOf(text);
       var end = start + text.length;
       var ht = [];
	   if(holder.caret>=0){
	   ht = [holderText.substring(0,holder.caret),holderText.substring(holder.caret)];	   
	   } else {
	   ht = [holderText,""];	   
	   }
       // 插入文字
       if (start < 0) {
           holder.value = [ht[0],trailer ,text ,trailer ,ht[1]," " ,suff].join("");
           start = holder.value.lastIndexOf(text);
           end = start + text.length;
       } else {
       	   holder.value = [holderText ," ",suff].join("");
       }
       	   
       // 高亮文字
       if (document.createRange) {
            holder.setSelectionRange( start, end );
            holder.focus();
       } else {
            range = holder.createTextRange();
            range.collapse(true);
            range.moveStart( 'character', start );
            range.moveEnd( 'character', end - start );
            range.select();
       }
   }
   
   // 输入框中指定位置插入文本
  
   ,insertText: function (text ,caret ,holder) {
	   
       var pre;
       var suff;
       var holderText;

       caret = caret || 0;

       if (holder.nodeName) {
           holder = $(holder);
       }

       holderText = holder.val();
       pre = holderText.substr(0,caret);
       suff = holderText.substr(caret);
       holderText = [pre,text,suff].join("");
       holder.val(holderText);
       holder.focus();
       holder.cursorPos([pre,text].join("").length);
   },
   
   isCardNo : function (card)  
   {  
      // 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X  
      var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  
      if(reg.test(card) === false)  
      {  
          return  false;  
      }  else {
   	   return  true;  
      }
   } ,
 //获取当前 Document 文档高度、宽度
   getPageSize: function(){
	   var winW, winH;
		if (window.innerHeight) {// all except IE
			winW = window.innerWidth;
			winH = window.innerHeight;
		} else if (document.documentElement
				&& document.documentElement.clientHeight) {// IE 6 Strict Mode
			winW = document.documentElement.clientWidth;
			winH = document.documentElement.clientHeight;
		} else if (document.body) { // other
			winW = document.body.clientWidth;
			winH = document.body.clientHeight;
		} // for small pages with total size less then the viewport
		return {
			WinW : winW,
			WinH : winH
		};
   },
   //小写转大写
   strToUpper: function (v){
	   //
	   return v.toUpperCase();
   }
   ,
   //大写转小写
   strToLower: function (v){
	   //
	   return v.toLowerCase();
   }
};
