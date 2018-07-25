//补0
		function getzf(num) {
			var temp = (num + "").length;
			for (var i = 0; i < 5 - temp; i++) {
				num = '0' + num;
			}
			return num;
		}

		$(function(){
			
			// 计算内容上下padding
			reContPadding({main:"#main",header:"#header",footer:"#footer"});
			function reContPadding(o){
				var main = o.main || "#main",
					header = o.header || null,
					footer = o.footer || null;
				var cont_pt = $(header).outerHeight(true),
					cont_pb = $(footer).outerHeight(true);
				$(main).css({paddingTop:cont_pt,paddingBottom:cont_pb});
			}
		});
		
		// 返回的是对象形式的参数
		function getUrlArgObject() {
			var args = new Object();
			var query = location.search.substring(1);// 获取查询串
			var pairs = query.split(",");// 在逗号处断开
			for (var i = 0; i < pairs.length; i++) {
				var pos = pairs[i].indexOf('=');// 查找name=value
				if (pos == -1) {// 如果没有找到就跳过
					continue;
				}
				var argname = pairs[i].substring(0, pos);// 提取name
				var value = pairs[i].substring(pos + 1);// 提取value
				args[argname] = unescape(value);// 存为属性
			}
			return args;// 返回对象
		}
		
		
		function getMyDate(str) {
			var oDate = new Date(str), oYear = oDate.getFullYear(), oMonth = oDate
					.getMonth() + 1, oDay = oDate.getDate(), oHour = oDate
					.getHours(), oMin = oDate.getMinutes(), oSen = oDate
					.getSeconds(), oTime = oYear + '年' + getzf(oMonth) + '月'
					+ getzf(oDay) + '日   ' + getzf(oHour) + ':' + getzf(oMin) + ':'
					+ getzf(oSen);// 最后拼接时间
			return oTime;
		};
		
		function getNowDate() {
			var oDate = new Date(), oYear = oDate.getFullYear(), oMonth = oDate
					.getMonth() + 1, oDay = oDate.getDate(), oHour = oDate
					.getHours(), oMin = oDate.getMinutes(), oSen = oDate
					.getSeconds(), oTime = oYear + '-' + getzf(oMonth) + '-'
					+ getzf(oDay) + ' ' + getzf(oHour) + ':' + getzf(oMin)+ ':'
					+ getzf(oSen); // 最后拼接时间
			return oTime;
		};
		
		// 补0
		function getzf(num) {
			if (parseInt(num) < 10) {
				num = '0' + num;
			}
			return num;
		}
		
		
		// 返回的是字符串形式的参数

		　　function getUrlArgStr(){     

		　　var url = location.search; //获取url中"?"符后的字串   
		   var theRequest = new Object();   
		   if (url.indexOf("?") != -1) {   
		      var str = url.substr(1);   
		      strs = str.split("&");   
		      for(var i = 0; i < strs.length; i ++) {   
		         theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);   
		      }   
		   }   
		   return theRequest;
		　　} 
		function StringBuffer() {
		    this.__strings__ = new Array;
		}

		StringBuffer.prototype.append = function (str) {
		    this.__strings__.push(str);
		};

		StringBuffer.prototype.toString = function () {
		    return this.__strings__.join("");
		};
		function getAddrJson(){
			var json;
			$.ajax({
				url: "http://passer-by.com/data_location/list.json",
				type: 'GET',
				crossDomain: true,
				async: false,
				dataType: 'json',
				success: function (data) {
					json=data;
				}
				
			});
			return json;
		}
		
		function getAddrByCode(code,json){
			var addr="";
			code=code.toString();
			
			var towncode=code ;                                           //街道乡镇编码
			var provinceCode=code.substring(0, 2) + "0000";               //省份编码
			var cityCode=code.substring(0, 4) + "00";                     //城市编码
			var areaCode=code.substring(0, 6);                            //地区编码
			var data;
			if(json==null)
				data=getAddrJson();
			else
				data=json;
			addr+=data[provinceCode];
			if(provinceCode!=cityCode&&data[cityCode]!=null)
				addr+=data[cityCode];	
			addr+=data[areaCode];
					//获取街道数据
					$.ajax({
						url: 'http://passer-by.com/data_location/town/' + areaCode + '.json',
						dataType: 'json',
						async: false,
						success: function(town) {
						addr+=town[code];
						}
					});
			return addr;
				
			}
		//通过地址ID删除地址	
		function deleteAddrByAddressId(addressId){
			$.ajax({
				"url" : "/user/collect_address/"+addressId,								
				"contentType": "application/json",
				"type" : "Delete",
				"error" : function() {
					alert("服务器繁忙");
				},
				"success" : function(returnData) {
					if (returnData.success) {
						window.location.reload();
						//alert("删除成功");
					}else{
						alert("删除失败");
					}
				}
			});
				
		}
		