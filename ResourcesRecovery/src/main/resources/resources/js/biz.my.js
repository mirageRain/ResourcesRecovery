/**
 * Created by rogerwu on 12/17/16.
 */
//我的
function my_news_personal(tpl, data) {
	var $box = $(this);

	$.ajax({
		type: 'GET',
		dataType: 'json',
		url: API_BASE + '/api.php/v1/user/myinfo?token=7ebb79884d5df49cc2659694c5abdbaf',
		data: {},
		success: function(json) {
			console.log('API Data:', json);

			var html = Mustache.render(tpl, json.data);
			$box.html(html);
			$box.initUI();
		}
	});
}

function my_reserve(tpl) {
	var $box = $(this);

	$.ajax({
		type: 'GET',
		dataType: 'json',
		url: API_BASE + '/api.php/v1/user/myReser?token=7ebb79884d5df49cc2659694c5abdbaf',
		data: {},
		success: function(json) {
			console.log('API Data:', json);

			json.showStatus = function() {
				switch(this.status) {
					case '0':
						return '未处理';
					case '1':
						return '已经处理';
					case '2':
						return '已完成';
					case '3':
						return '取消';
					default:
						return '';
				}
			}
			var html = Mustache.render(tpl, json);
			$box.html(html);
			$box.initUI();
		}
	});
}
//我的订单
function my_order(tpl) {
	var $box = $(this);

	$.ajax({
		type: 'GET',
		dataType: 'json',
		url: API_BASE + '/api.php/v1/user/myOrder?token=7ebb79884d5df49cc2659694c5abdbaf',
		data: {},
		success: function(json) {
			console.log('API Data:', json);

			var html = Mustache.render(tpl, json);
			$box.html(html);
			$box.initUI();
		}
	});
}
//我的卡卷
function my_coupon(tpl) {
	var $box = $(this);

	$.ajax({
		type: 'GET',
		dataType: 'json',
		url: API_BASE + '/api.php/v1/user/myCard?token=7ebb79884d5df49cc2659694c5abdbaf',
		data: {},
		success: function(json) {
			console.log('API Data:', json);

			var html = Mustache.render(tpl, json);
			$box.html(html);
			$box.initUI();
		}
	});
}

// 我的收藏
function my_favorite(tpl) {
	var $box = $(this);
	$.ajax({
		type: 'GET',
		dataType: 'json',
		url: API_BASE + '/api.php/v1/user/myColl?token=7ebb79884d5df49cc2659694c5abdbaf',
		data: {},
		success: function(json) {
			console.log('API Data:', json);

	
			var html = Mustache.render(tpl, json);
			$box.html(html);
			$box.initUI();
		}
	});
}
// 我的爱车
function my_aiche(tpl) {
	console.log("aiche")
	var $box = $(this);

	$.ajax({
		type: 'GET',
		dataType: 'json',
		url: API_BASE + '/api.php/v1/user/myCar?token=7ebb79884d5df49cc2659694c5abdbaf',
		data: {},
		success: function(json) {
			console.log('API Data:', json);

			json.showStatus = function() {
				switch(this.status) {
					case '0':
						return '未处理';
					case '1':
						return '已经处理';
					case '2':
						return '已完成';
					case '3':
						return '取消';
					default:
						return '';
				}
			}
			var html = Mustache.render(tpl, json);
			$box.html(html);
			$box.initUI();
		}
	});
}

// 推荐购车
function my_gouche(tpl) {
	console.log("购车")
	var $box = $(this);

	$.ajax({
		type: 'GET',
		dataType: 'json',
		url: API_BASE + '/api.php/v1/user/myRecom?token=7ebb79884d5df49cc2659694c5abdbaf',
		data: {},
		success: function(json) {
			console.log('API Data:', json);

			json.showStatus = function() {
				switch(this.status) {
					case '0':
						return '未处理';
					case '1':
						return '已经处理';
					case '2':
						return '已完成';
					case '3':
						return '取消';
					default:
						return '';
				}
			}
			var html = Mustache.render(tpl, json);
			$box.html(html);
			$box.initUI();
		}
	});
}


//服务
function service(tpl) {
	var $box = $(this);

	$.ajax({
		type: 'GET',
		dataType: 'json',
		url: API_BASE + '/api.php/v1/news/getServerfwelfare?token=7ebb79884d5df49cc2659694c5abdbaf',
		data: {},
		success: function(json) {
			console.log('API Data:', json);
			console.log('API Data:', 1);

			var html = Mustache.render(tpl, json);
			$box.html(html);
			$box.initUI();
		}
	});
}
//为你推荐
function weinituijian(tpl,data) {
	var $box = $(this);

	$.ajax({
		type: 'GET',
		dataType: 'json',
		url: API_BASE + '/api.php/v1/News/personalInfo?token=7ebb79884d5df49cc2659694c5abdbaf&arcId='+data.id,
		data: {},
		success: function(json) {
			console.log('API Data:', json);
			console.log('API Data:', 2);
 if (json.data.url) {
                json.data.height = $box.first().offsetHeight-2 + 'px';
            }

            json.data.collect_url = API_BASE + '/api.php/v1/user/myColl?token=7ebb79884d5df49cc2659694c5abdbaf&id='+json.data.id+'&type=article';
			var html = Mustache.render(tpl, json);
			$box.html(html);
			$box.initUI();
			
		}
	});
}

//车型展示

function car_guanwang(tpl) {
	var $box = $(this);

	$.ajax({
		type: 'GET',
		dataType: 'json',
		url: API_BASE + '/api.php/v1/News/archiveInfo?token=7ebb79884d5df49cc2659694c5abdbaf&id=' + data.id,
		data: {},
		success: function(json) {
			console.log('API Data:', json);
			console.log('API Data:', 1);

			var html = Mustache.render(tpl, json);
			$box.html(html);
			$box.initUI();
		}
	});
}

// 购物车页面
function shopping_car(tpl) {
	var $box = $(this);

	$.ajax({
		type: 'GET',
		dataType: 'json',
		url: API_BASE + '/api.php/v1/News/archiveInfo?token=7ebb79884d5df49cc2659694c5abdbaf',
		data: {},
		success: function(json) {
			console.log('API Data:', json);
			console.log('API Data:', 1);

			var html = Mustache.render(tpl, json);
			$box.html(html);
			$box.initUI();
			var item_click1 = $box.find(".item-click1");

			var item_click3 = $box.find(".item-click3");
			var $shopping_zhongjia = $box.find("#shopping_zhongjia");
			var baojia = $box.find("#shopping_baojia");
			$shopping_zhongjia.text("¥" + baojia.text() + "元");
			item_click1.click(function() {
				var item_click2 = $box.find(".item-click2").text();

				if(item_click2 < 2) {
					item_click2 = 1;
					$shopping_zhongjia.text("¥" + baojia.text() + "元");
				} else {
					$box.find(".item-click2").text(--item_click2);
					$shopping_zhongjia.text("¥" + baojia.text() * item_click2 + "元");
				}
			})
			item_click3.click(function() {
				var item_click2 = $box.find(".item-click2").text();
				$box.find(".item-click2").text(++item_click2);
				$shopping_zhongjia.text("¥" + baojia.text() * item_click2 + "元");

			})

		}
	});
}

function shopping_car(tpl, data) {
	var $box = $(this);

	$.ajax({
		type: 'GET',
		dataType: 'json',
		url: API_BASE + '/api.php/v1/user/myAddress?token=7ebb79884d5df49cc2659694c5abdbaf',
		data: {},
		success: function(json) {
			console.log('API Data:', json);

			var html = Mustache.render(tpl, json);
			$box.html(html);
			$box.initUI();

			$.shoppingCart.syncPage({refresh:true});
		}
	});
}
function shopping_car_address(tpl, data){
	var $box = $(this);
	var html = Mustache.render(tpl, {});
	$box.html(html);
	$box.initUI();
}

// 服务大厅
function service_list(tpl) {
	var $box = $(this);

	$.ajax({
		type: 'GET',
		dataType: 'json',
		url: API_BASE + '/api.php/v1/news/getServerfwelfare?token=7ebb79884d5df49cc2659694c5abdbaf',
		data: {},
		success: function(json) {
			console.log('API Data:', json);
			console.log('API Data:', "服务大厅");

			var html = Mustache.render(tpl, json);
			$box.html(html);
			$box.initUI();
		}
	});
}