/**
 * 通过工具类
 */
var Share = {
		 /** 退出系统 */
		 exit : function (url) {
			$.messager.confirm('系统提示', '确定要退出吗?', function(r) {
				if (r) {
					window.location.href = url+"?t=" + Math.random();
				}
			});
		 },
		
		 /** 清空选择的grid */
		 resetGrid : function (grid) {
		     // 清空选中的记录
		     grid.getSelectionModel().clearSelections();
		     grid.getEl().select('div.x-grid3-hd-checker').removeClass('x-grid3-hd-checker-on');
		 },
		 
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
		    ),
		    
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
		            str.push(this.enKey.charAt(ch1>>2), this.enKey.charAt(((ch1<<4)+(ch2>>4))&0x3f));
		            str.push(this.enKey.charAt(((ch2<<2)+(ch3>>6))&0x3f), this.enKey.charAt(ch3&0x3f));
		        }
		        //给剩下的字符进行编码。
		        if(pos<src.length){
		            ch1=src.charCodeAt(pos++);
		            str.push(this.enKey.charAt(ch1>>2));
		            if(pos<src.length){
		                ch2=src.charCodeAt(pos);
		                str.push(this.enKey.charAt(((ch1<<4)+(ch2>>4))&0x3f));
		                str.push(this.enKey.charAt(ch2<<2&0x3f), '=');
		            }else{
		                str.push(this.enKey.charAt(ch1<<4&0x3f), '==');
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
		            ch1=this.deKey[src.charCodeAt(pos++)];
		            ch2=this.deKey[src.charCodeAt(pos++)];
		            ch3=this.deKey[src.charCodeAt(pos++)];
		            ch4=this.deKey[src.charCodeAt(pos++)];
		            str.push(String.fromCharCode(
		                (ch1<<2&0xff)+(ch2>>4), (ch2<<4&0xff)+(ch3>>2), (ch3<<6&0xff)+ch4));
		        }
		        //给剩下的字符进行解码。
		        if(pos+1<src.length){
		            ch1=this.deKey[src.charCodeAt(pos++)];
		            ch2=this.deKey[src.charCodeAt(pos++)];
		            if(pos<src.length){
		                ch3=this.deKey[src.charCodeAt(pos)];
		                str.push(String.fromCharCode((ch1<<2&0xff)+(ch2>>4), (ch2<<4&0xff)+(ch3>>2)));
		            }else{
		                str.push(String.fromCharCode((ch1<<2&0xff)+(ch2>>4)));
		            }
		        }
		       //组合各解码后的字符，连成一个字符串。
		        return str.join('');
		    },
		    
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
		 		},
		    /** 问候 */
		    sayHello : function () {
		        var hour = new Date().getHours(),
		         hello = '';
		        if (hour < 6) {
		            hello = '凌晨好';
		        } else if (hour < 9) {
		            hello = '早上好';
		        } else if (hour < 12) {
		            hello = '上午好';
		        } else if (hour < 14) {
		            hello = '中午好';
		        } else if (hour < 17) {
		            hello = '下午好';
		        } else if (hour < 19) {
		            hello = '傍晚好';
		        } else if (hour < 22) {
		            hello = '晚上好';
		        } else {
		            hello = '夜里好';
		        }
		        return hello + '！';
		    },
		    
		    /** 菜单 */
		    showMenu : function () {
		    	$.ajax({
		    		url:'treeMenu',
		    		type : 'POST',
		    	    beforeSend: function(){  
		    	    },
		    	    complete: function(){  
		    	    },
		    	    success : function(data) { 
		    	    	var result=eval('('+data+')');
		    	    	var data = "";
		    	    	for(var i = 0 ;  i< result.length; i++ ){
		    	    		if(result[i].children.length>0){
		    	    		data +='<li class="" >';
		    	    			data += '<a href="#" class="dropdown-toggle">';
		    	    				data += '<i class="menu-icon fa fa-list"></i>';
		    	    				data += '<span class="menu-text"> '+result[i].name+' </span>';
		    						data += '<b class="arrow fa fa-angle-down"></b>';
		    					data += '</a>';
		    					data += '<b class="arrow"></b>';
		    					data += '<ul class="submenu">';
		    						for(var y = 0 ; y< result[i].children.length; y++){
		    							data += '<li class="">';
		    									_data = result[i].children[y].url+"?id="+result[i].children[y].id+"&"+result[i].children[y].name;
		    									data += '<a href="javascript:void(0);" action-data="'+Share.encode(Share.utf16to8(_data))+'" >';
		    									data += '<i class="menu-icon fa fa-caret-right"></i>';
		    								    data += result[i].children[y].name+'</a>';
		    								    data += '<b class="arrow"></b>';
		    						    data += '</li>';
		    						}
		    					data +='</ul>';
		    	    		}else{
		    	    			data += '<li class="">';
		    	    			_data = result[i].url+"?id="+result[i].id+"&"+result[i].name;
		    	    				data += '<a href="javascript:void(0);" action-data="'+Share.encode(Share.utf16to8(_data))+'" >';
		    							data += '<i class="menu-icon fa fa-home home-icon"></i>';
		    							data += '<span class="menu-text"> '+result[i].name+' </span>';
		    							data += '</a>';
		    							data += '<b class="arrow"></b>';
		    						data +='</ul>';
		    	    		}
		    	    	}
		    	    	$('ul.nav-list').append(data);
		    	    }
		    	});
		    },
		    /** 新增:获取汉字编码的hashCode */
		   hashCode : function (str) {
		        var h = 0;
		        var len = str.length;
		        var t = 2147483648;
		        for (var i = 0; i < len; i++) {
		            h = 31 * h + str.charCodeAt(i);
		            if(h > 2147483647) h %= t;//java int溢出则取模
		        }
		        return h;
		    }
		    
};




