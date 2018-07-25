
// 获取车牌区域
function getRegionData(success){
    if (!window.bizRegionData) {
        $.ajax({
            type:'GET',
            dataType: 'json',
            url: API_BASE + '/api.php/v1/tools/weizhang?token=SDUFLSKJSDF',
            data: {},
            success:function(json){
                console.log('API Data:', json);

                if (json.errorCode == 0) {
                    window.bizRegionData = json.data.region;
                    if (success) success(window.bizRegionData);
                }
            }
        });

    } else {
        if (success) success(window.bizRegionData);
    }
}

// 获取省份
function getProvinceData(success){
    if (!window.bizProvinceData) {
        $.ajax({
            type:'GET',
            dataType: 'json',
            url: API_BASE + '/api.php/v1/tools/region',
            success:function(json){
                console.log('API Data:', json);

                if (json.errorCode == 0) {
                    window.bizProvinceData = json.data;
                    if (success) success(window.bizProvinceData);
                }
            }
        });

    } else {
        if (success) success(window.bizProvinceData);
    }
}

// 获取某个省份的城市
function getCityData(parentid, success){

    $.ajax({
        type:'GET',
        dataType: 'json',
        url: API_BASE + '/api.php/v1/tools/region?parentid='+parentid,
        success:function(json){
            console.log('API Data:', json);

            if (json.errorCode == 0) {
                if (success) success(json.data);
            }
        }
    });
}

// 选择省份城市

function select_city_pop(tpl, data){
    var $box = $(this);

    getProvinceData(function(provinces){
        var html = Mustache.render(tpl, {provinces:provinces});
        $box.html(html);
        $box.initUI();

        var $provinceLink = $box.find('#select_city_provinceLink'),
            $cityLink = $box.find('#select_city_cityLink'),
            $provinceBox = $box.find('#select_city_provinceBox'),
            $cityBox = $box.find('#select_city_cityBox');

        var $scrollBox = $provinceBox.parentsUntil(function(){
            return dwz.hasClass(this, 'dwz-scroll');
        });

        $provinceLink.click(function(){
            $provinceBox.show();
            $cityBox.hide();
            $scrollBox.trigger(dwz.event.type.iScrollRefresh);
        });

        $cityLink.click(function(){
            $provinceBox.hide();
            $cityBox.show();
            $scrollBox.trigger(dwz.event.type.iScrollRefresh);
        });

        var $provinceItems = $provinceBox.find('a.list-item');
        $provinceItems.touchwipe({
            touch:function() {
                var $provinceItem = $(this);
                $provinceItems.removeClass('activated');
                $provinceItem.addClass('activated');
                $provinceLink.html($provinceItem.attr('data-name') + '<i class="icon icon-select-down"></i>');
                $cityLink.html('请选择<i class="icon icon-select-down"></i>');

                var regionData = {
                    provinceId: $provinceItem.attr('data-id'),
                    provinceName: $provinceItem.attr('data-name')
                }

                getCityData(regionData.provinceId, function (cities) {

                    var $cityId = $('#' + data.dwz_city_id),
                        $cityName = $('#' + data.dwz_city_name),
                        itemTpl = '{{#cities}}<a class="list-item" data-id="{{0}}" data-name="{{1}}">{{1}}</a>{{/cities}}';

                    var cityHtml = Mustache.render(itemTpl, {cities: cities});
                    $cityBox.html(cityHtml);

                    var $cityItems = $cityBox.find('a.list-item');
                    $cityItems.touchwipe({
                        touch: function (event) {
                            var $cityItem = $(this);
                            $cityItems.removeClass('activated');
                            $cityItem.addClass('activated');
                            $cityLink.html($cityItem.attr('data-name') + '<i class="icon icon-select-down"></i>');

                            regionData['cityId'] = $cityItem.attr('data-id');
                            regionData['cityName'] = $cityItem.attr('data-name');

                            if (data.dwz_province_id) {
                                var $provinceId = $('#'+data.dwz_province_id);
                                $provinceId.val(regionData.provinceId).trigger('change', regionData);
                            }
                            if (data.dwz_province_name) {
                                var $provinceName = $('#'+data.dwz_province_name);
                                $provinceName.val(regionData.provinceName).trigger('change', regionData);
                            }

                            $cityId.val(regionData.cityId).trigger('change', regionData);
                            $cityName.val(regionData.cityName).trigger('change', regionData);

                            event.preventDefault();
                            event.stopPropagation();
                        }
                    });

                });
            }
        });

    });
}

// 查看经销商地图
function view_map(tpl, item){
    var $box = $(this);

    // var html = Mustache.render(tpl, json);
    $box.html(tpl);
    $box.initUI();

    var $mapBox = $('#view_map_bmap');
    $mapBox.css({'height': document.documentElement.clientHeight + 'px'});

    var point = new BMap.Point(item.lng, item.lat);
    var map = new BMap.Map($mapBox.attr('id'));

    map.centerAndZoom(point, 12);
    map.enableScrollWheelZoom();

    dwzMap.addMarker({map:map, point:point, iconWidth:23, iconHeight:25, imageOffsetLeft: 0, imageOffsetTop: -275}, function(marker){
        var content = '<div class="InfoWindow">'
            + '<p>地址：'+item.address+'</p>'
            + '<p>电话：'+item.phone+'</p>'
            + '<p>经度：'+item.lng+' &nbsp;&nbsp; 纬度：'+item.lat+'</p>'
            + '</div>';

        var infoOpt = { title: '<h2 class="InfoWindowTitle">名称：'+item.name+'</h2>'};
        var infoWindow = new BMap.InfoWindow("<div class='InfoWindow'>" + content + "</div>", infoOpt);
        marker.addEventListener("click", function () {
            this.openInfoWindow(infoWindow);
        });
    });


    //获取gps位置
    var geolocation = new BMap.Geolocation();
    geolocation.getCurrentPosition(function(gpsPos){

        if(this.getStatus() == BMAP_STATUS_SUCCESS){
            dwzMap.addMarker({map:map, point:gpsPos.point, iconWidth:23, iconHeight:25, imageOffsetLeft: 0, imageOffsetTop: -300});
        }
    },{enableHighAccuracy: true});
}

//pop查看大图
function car_pic_pop(tpl, data){
    var $box = $(this);

    var html = Mustache.render(tpl, data);
    $box.html(html);
    $box.initUI();
}

$.fn.extend({
    gotoCart: function(){
        return this.each(function(){
            $(this).touchwipe({
                touch:function(event){
                    var $link = $(this), url = $link.attr('data-href');
                    var price = $link.attr('data-price');
                    if (price) price = price.replaceAll('￥', '');
                    var item = {
                        sn: $link.attr('data-sn'),
                        title: $link.attr('data-title'),
                        price: price,
                        info: $link.attr('data-info'),
                        picUrl: $link.attr('data-pic-url')
                    }

                    $.shoppingCart.addItem(item);

                    var data = $.extend(url.getParams(), item);
                    $.navView.open({
                        url: url,
                        rel: $link.attr('rel')||'_blank',
                        data: data
                    });
                    event.stopPropagation();
                }
            });
        });
    },
    addToCart: function(options){
        var op = $.extend({
            frag: {
                addToCart:'<div class="item-click " style="border: 1px solid #ccc;" id="#productBoxId#" data-sn="#sn#" data-title="#title#" data-price="#price#" data-info="#info#" data-pic-url="#picUrl#">\
                    <div class="dwz-cart-del item-click1 r-line"></div>\
                    <div class="dwz-cart-num item-click2 r-line">#count#</div>\
                    <div class="dwz-cart-add item-click3"></div>\
                </div>'
            },
            add$:'.dwz-cart-add', del$:'.dwz-cart-del', num$:'.dwz-cart-num'
        }, options);

        return this.each(function(){
            var $input = $(this),
                count = $input.val(),
                sn = $input.attr('data-sn'),
                title = $input.attr('data-title'),
                price = $input.attr('data-price'),
                info = $input.attr('data-info'),
                picUrl = $input.attr('data-pic-url');

            var html = op.frag.addToCart.replaceAll('#productBoxId#', 'product-'+sn)
                .replaceAll('#sn#', sn).replaceAll('#title#', title)
                .replaceAll('#count#', count).replaceAll('#price#', price)
                .replaceAll('#info#', info).replaceAll('#picUrl#', picUrl);

            var $box = $($.parseHTML(html));
            $box.afterTo(this);
            $input.remove();

            var $add = $box.find(op.add$),
                $del = $box.find(op.del$);

            $add.touchwipe({
                touch:function(event){
                    var offest = $box.offset();

                    $.shoppingFly({title:title, x:offest.left+offest.width-120, y:offest.top});
                    $.shoppingCart.addItem({sn:sn, title:title, price:price, picUrl:picUrl});

                    event.preventDefault();
                    event.stopPropagation();
                }
            });

            $del.touchwipe({
                touch:function(event){
                    var offest = $box.offset();

                    $.shoppingFly({title:title, x:offest.left+offest.width-120, y:offest.top, flyIn:false});
                    $.shoppingCart.removeItem(sn);

                    event.preventDefault();
                    event.stopPropagation();
                }
            });
        });
    }
});

$.extend({
    shoppingFly:function(options){
        var op = $.extend({
            frag: {
                shoppingFly:'<span id="#boxId#" class="shopping-fly #class#" style="top:#top#px;left:#left#px;">#title#</span>'
            },
            title:'Product Name', x:0, y:0, flyIn:true
        }, options);

        var $cart = $('#pub-cart'),
            $shoppingCount = $cart.find('.item-submit-car b'),
            offest = $cart.offset();

        var startX = op.flyIn ? op.x : offest.left,
            startY = op.flyIn ? op.y : offest.top,
            endX = !op.flyIn ? op.x : offest.left,
            endY = !op.flyIn ? op.y : offest.top,
            boxId = 'shopping-fly-' + (new Date().getTime()),
            html = op.frag.shoppingFly.replaceAll('#left#', startX).replaceAll('#top#', startY)
                .replaceAll('#boxId#', boxId).replaceAll('#title#', op.title).replaceAll('#class#', op.flyIn ? '' : 'out');

        $('body').append(html);
        var $box = $('#'+boxId);

        setTimeout(function(){
            $box.css({left:endX + 'px', top:endY + 'px'}).addClass('erect');
            $shoppingCount.addClass('erect');
        }, 10);

        setTimeout(function(){
            $box.remove();
            $shoppingCount.removeClass('erect');
        }, 600);
    },
    shoppingCart: {
        config: {
            APP_CART_ITEMS:'APP_CART_ITEMS',
            itemFrag:'{{#items}}\
					<div class="panel-cont-finance item-ware-list dwz-cart-item">\
						<div class="item-choice item-cu"></div>\
						<div class="panel-cont-left item-ma-l"><img src="{{picUrl}}"></div>\
						<div class="panel-cont-right">\
							<h2>{{title}}</h2>\
							<h3>{{info}}</h3>\
							<p>￥<em>{{price}}</em>元</p>\
							<input type="number" value="{{count}}" min="0" class="add-to-cart" data-sn="{{sn}}" data-title="{{title}}" data-price="{{price}}" data-info="{{info}}" data-pic-url="{{picUrl}}">\
						</div>\
					</div>\
					{{/items}}',
            listBox$:'#dwz-shopping-cart',
            countBox$:'#pub-cart .item-submit-car b',
            infoBox$:'#pub-cart em',
            infoFrag:'#totalPrice#'
        },
        data:{opend: false, items:[]},

        getListBox: function(){
            return $(this.config.listBox$);
        },
        getCountBox: function(){
            return $(this.config.countBox$);
        },
        getInfoBox: function(){
            return $(this.config.infoBox$);
        },
        init: function(options){
            var me = this, op = $.extend(this.config, options);

            // 清理购物车中超时商品
            if (window.localStorage) {
                var strCartItems = localStorage.getItem(me.config.APP_CART_ITEMS);
                console.log("strCartItems: ", strCartItems);
                if (strCartItems) {
                    var items = JSON.parse(strCartItems),
                        now = new Date().getTime();

                    for (var i=0; i<items.length; i++) {
                        var item = items[i];
                        if (item && item.time && item.time > now - 1000 * 60 * 60 * 24) {
                            me.data.items.push(item);
                        }
                    }

                    localStorage.setItem(me.config.APP_CART_ITEMS, JSON.stringify(me.data.items));
                }
            }

            dwz.regPlugins.push(function($p){

                $('input.add-to-cart', $p).addToCart();
                $('a[target=gotoCart]', $p).gotoCart();

            });

        },
        syncPage: function(options){
            var op = $.extend({sn:'', refresh:false}, options);

            var me = $.shoppingCart,
                $countBox = this.getCountBox(),
                $infoBox = this.getInfoBox();

            var info = me.getTotalInfo(),
                infoHtml = me.config.infoFrag.replaceAll('#totalPrice#', info.price);
            if ($countBox.size() > 0) $countBox.text(info.count < 1000 ? info.count : '...');
            if ($infoBox.size() > 0) $infoBox.html(infoHtml);

            if (op.sn) {
                var $numBox = $('#product-'+op.sn),
                    itemData = me.getItemData(op.sn),
                    num = itemData.count || 0;

                if ($numBox.size() > 0) {

                    $numBox.each(function(){
                        var $this = $(this);
                        $this.find('.dwz-cart-num').text(num);

                        if (num <= 0) {
                            var $cartItemBox = $this.parentsUntil(function(){
                                return $(this).is('div.dwz-cart-item');
                            });
                            if ($cartItemBox.size()>0){
                                $cartItemBox.remove();
                            }
                        }
                    });
                }

            }

            // 初始化购物车商品列表
            var $listBox = me.getListBox();
            if (op.refresh && $listBox.size()>0) {

                var html = Mustache.render(me.config.itemFrag, me.data);
                $listBox.html(html);
                $listBox.initUI();

                // 重新初始化iscroll
                $listBox.parentsUnitBox().find('div.dwz-scroll').trigger(dwz.event.type.iScrollRefresh);
            }

        },
        addItem: function(params){
            var me = $.shoppingCart;
            var op = $.extend({sn:'', title:'', price:0, info:'', picUrl:'', count:1}, params),
                now = new Date().getTime();

            me.addItemData({
                sn: op.sn,
                count: op.count,
                price: op.price,
                title: op.title,
                info: op.info,
                picUrl: op.picUrl,
                time: now
            });

            me.syncPage({sn:op.sn});
        },
        removeItem: function(sn){
            var me = $.shoppingCart;

            me.removeItemData(sn);

            me.syncPage({sn:sn});
        },
        /**
         * 清空购物车
         */
        clean:function(){
            var me = $.shoppingCart,
                $countBox = this.getCountBox(),
                $infoBox = this.getInfoBox();

            var infoHtml = me.config.infoFrag.replaceAll('#totalPrice#', '0.0');
            if ($countBox.size() > 0) $countBox.text('0');
            if ($infoBox.size() > 0) $infoBox.html(infoHtml);

            // 清理数据
            me.data.items = [];
            if (window.localStorage) {
                localStorage.setItem(me.config.APP_CART_ITEMS, JSON.stringify(me.data.items));
            }
        },
        getTotalInfo:function(){
            var me = $.shoppingCart,
                info = {count:0, price:0};
            for (var i=0; i<me.data.items.length; i++) {
                var item = me.data.items[i];

                info.count += item.count;
                info.price += item.price * item.count;
            }

            info.price = info.price.parseCurrency();
            return info;
        },
        getItemData:function(sn) {
            var me = $.shoppingCart;
            for (var i=0; i<me.data.items.length; i++) {
                if (me.data.items[i].sn == sn) {
                    return me.data.items[i];
                }
            }
            return {};
        },
        addItemData: function(itemData){
            var me = $.shoppingCart,
                duplicate = false;

            // 根据编号查找 $.shoppingCart.data.items 中商品，进行修改
            for (var i=0; i<me.data.items.length; i++) {
                var item = me.data.items[i];
                if (item.sn == itemData.sn) {
                    me.data.items[i] = {
                        sn: itemData.sn,
                        count: item.count + itemData.count,
                        price: itemData.price,
                        title: itemData.title,
                        info: itemData.info,
                        picUrl: itemData.picUrl,
                        time: itemData.time
                    };

                    duplicate = true;
                    break;
                }
            }
            // 插入新商品到 $.shoppingCart.data.items
            if (!duplicate) {
                me.data.items.push({
                    sn: itemData.sn,
                    count: 1,
                    price: itemData.price,
                    title: itemData.title,
                    info: itemData.info,
                    picUrl: itemData.picUrl,
                    time: itemData.time
                });
            }

            if (window.localStorage) {
                localStorage.setItem(me.config.APP_CART_ITEMS, JSON.stringify(me.data.items));
            }
        },
        removeItemData:function(sn) {
            var me = $.shoppingCart;

            for (var i=0; i<me.data.items.length; i++) {
                var item = me.data.items[i];
                if (item.sn == sn) {
                    item.count = --item.count;

                    if (item.count <= 0){
                        me.data.items.splice(i, 1);
                    }
                }
            }

            if (window.localStorage) {
                localStorage.setItem(me.config.APP_CART_ITEMS, JSON.stringify(me.data.items));
            }
        }
    }
});