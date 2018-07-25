/**
 * Created by rogerwu on 12/17/16.
 */


function service_news_welfareList(tpl, data){
    var $box = $(this);

    $.ajax({
        type:'GET',
        dataType: 'json',
        url: API_BASE + '/api.php/v1/news/ownersClub',
        data: {},
        success:function(json){
            console.log('API Data:', json);
            console.log('API Data:', 1);

            var html = Mustache.render(tpl, json);
            $box.html(html);
            $box.initUI();

            var $menus = $box.find('.header .segment .segment-button'),
                $contents = $box.find('.content .segment-content');

            $menus.each(function(index){
                $(this).click(function(){
                    $menus.removeClass('checked').eq(index).addClass('checked');
                    $contents.removeClass('active').eq(index).addClass('active');
                }, index);
            });
        }
    });
}


//车主福利  
function service_news_archiveInfo(tpl, data){
    var $box = $(this);

    $.ajax({
        type:'GET',
        dataType: 'json',
        url: API_BASE + '/api.php/v1/News/archiveInfo?arcId='+data.id,
        data: {},
        success:function(json){
            console.log('API Data:', json);

            if (json.data.url) {
                json.data.height = $box.first().offsetHeight-2 + 'px';
            }

            json.data.collect_url = API_BASE + '/api.php/v1/user/myColl?token=7ebb79884d5df49cc2659694c5abdbaf&id='+json.data.id+'&type=article';
            var html = Mustache.render(tpl, json.data);
            $box.html(html);
            $box.initUI();

        }
    });
}

//会员俱乐部
function huiyuan_iframe(tpl, data){
    var $box = $(this);

    $.ajax({
        type:'GET',
        dataType: 'json',
        url: API_BASE + '/api.php/v1/News/archiveInfo?arcId='+data.id,
        data: {},
        success:function(json){
            console.log('API Data:', json);

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



// 保险产品
function service_insurance(tpl, data){
    var $box = $(this);

    $.ajax({
        type:'GET',
        dataType: 'json',
        url: API_BASE + '/api.php/v1/news/insurance',
        data: {},
        success:function(json){
            console.log('API Data:', json.data.vehicle);
            var html = Mustache.render(tpl, json);
            $box.html(html);
            $box.initUI();

            //页面装载之后将节点缓存
            var $select_f = $box.find('#select_first'),
                $select_s = $box.find('#select_second'),
                $baojia = $box.find('#baojia'),
                $insurance_type = $box.find('.insurance_type'),
                $single = $box.find('.single_choose li'),
                $third = $box.find('#third_D'),
                $abmt = $box.find('#abatement_D'),
                $glass = $box.find('#glass'),
                $scratch = $box.find('#scratch_D'),
                $cankaojia = $box.find('#cankaojia'),
                priceOfCar = 0;
            console.log($insurance_type);
            var dataLen = json.data.vehicle.length,i;

            var str = '';

            for (i = 0;i < dataLen;i++){
                str = '<option value="'+i+'">'+json.data.vehicle[i].name+'</option>';
                $select_f.append(str);
            }

            $select_f.on('change', function () {
                $select_s.html("<option >具体型号：</option>");
                var $index = $(this).val(),i = 0,len = json.data.vehicle[$index].childs.length;
                var result = json.data.vehicle[$index];
                var vStr = '';
                for(i;i < len;i++){
                    vStr = '<option value="'+result.childs[i].price+'">'+result.childs[i].name+'</option>';
                    $select_s.append(vStr);
                }
            });

            //当选择具体某款车的同时将价格绑定到变量中
            $select_s.on('change', function () {
                console.log($(this).val());
                priceOfCar = Number($(this).val());
                $baojia.text(priceOfCar / 10000);
                $box.find('.count').each(function () {
                    if ($(this).attr('data-rate').length >= 8){
                        console.log('这是司乘险');
                        var price = parseInt($(this).attr("data-rate").split(",")[0] * priceOfCar) + parseInt($(this).attr("data-rate").split(",")[1] * priceOfCar);
                        this.parentNode.nextElementSibling.children[0].innerHTML = price;
                    }else {
                        var m = parseInt($(this).attr("data-value")) + parseInt($(this).attr("data-rate") * priceOfCar);
                        this.parentNode.nextElementSibling.children[0].innerHTML = m;
                    }
                });

                //不计免赔  (商业第三者险 + 车辆损失险) * 15%
                var abmt = parseInt($box.find('#T').text()) + parseInt($box.find('#L').text());
                $abmt.parentNode().nextElementSibling.children[0].innerHTML = parseInt(abmt * 0.15);
                counter()
            });

            $single.on('click', function () {

               $(this).find('span').addClass('back5');

               if ($(this).parentNode().id == 'glass'){
                    console.log('这是玻璃险');
                    var num = parseInt($(this).attr('data-rate') * priceOfCar);
                   try {
                       this.nextElementSibling.children[0].className = '';
                       this.parentNode.parentNode.nextElementSibling.children[0].innerHTML = num;
                   }catch (e){
                       this.previousElementSibling.children[0].className = '';
                       this.parentNode.parentNode.nextElementSibling.children[0].innerHTML = num;
                   }
               }else if ($(this).find('span').hasClass('back5')) {
                   try {
                        this.nextElementSibling.children[0].className = '';
                        this.parentNode.parentNode.nextElementSibling.children[0].innerHTML = $(this).attr('data-value');
                   }catch (e){
                        this.previousElementSibling.children[0].className = '';
                        this.parentNode.parentNode.nextElementSibling.children[0].innerHTML = $(this).attr('data-value');
                   }
               }
            });

            //保险种类单选效果
            $insurance_type.on('click', function () {
                if ($(this).find('span').hasClass('back2')){
                    $(this).find('span').removeClass('back2');
                    this.parentNode.nextElementSibling.children[0].innerHTML = '0';
                }else {
                    if (this.id == 'third_D') {
                        //这是第三者

                        $(this).find('span').addClass('back2');
                        $box.find('#third_wrap li').each(function () {
                            if ($(this).find('span').hasClass('back5')){
                                var p = $(this).find('span').attr('data-value');
                                console.log(p);
                                console.log(this.parentNode.parentNode.nextElementSibling.children[0]);
                                this.parentNode.parentNode.nextElementSibling.children[0].innerHTML = p;
                            }
                        })
                    }else if (this.id == 'scratch') {
                        //'这是车身划痕险';

                        $(this).find('span').addClass('back2');
                        $box.find('#scratch_D li').each(function () {
                            if ($(this).find('span').hasClass('back5')){
                                var o = $(this).find('span').attr('data-value');
                                this.parentNode.parentNode.nextElementSibling.children[0].innerHTML = o;
                            }
                        })
                    }else if(this.id == 'abatement_D'){
                        //'这是不计免赔';

                        $(this).find('span').addClass('back2');
                        var abmt = parseInt($box.find('#T').text()) + parseInt($box.find('#L').text());
                        $abmt.parentNode().nextElementSibling.children[0].innerHTML = parseInt(abmt * 0.15);
                    }else if(this.id == 'driver'){
                        //'这是司乘险';

                        $(this).find('span').addClass('back2');
                        var price = parseInt($(this).attr("data-rate").split(",")[0] * priceOfCar) +
                                    parseInt($(this).attr("data-rate").split(",")[1] * priceOfCar);
                        this.parentNode.nextElementSibling.children[0].innerHTML = price;
                    }else {
                        $(this).find('span').addClass('back2');
                        var single = parseInt($(this).attr('data-value')) +
                                     parseInt($(this).attr('data-rate') * priceOfCar);
                        $(this).parentNode().nextElementSibling.children[0].innerHTML = single;
                    }
                }
                counter()
            });

            //第三者责任险金额单选
            var third_li = document.getElementById('third_wrap').children,
                len = third_li.length,i,j,index = 0;
            for (i = 0;i < len;i++) {
                third_li[i].index = i;
                third_li[i].onclick = function () {
                    index = this.index;
                    for (j = 0;j < len;j++){
                        third_li[j].children[0].className = '';
                    }
                    third_li[index].children[0].className = 'back5';
                    var p = third_li[index].children[0].getAttribute('data-value');
                    document.getElementById('T').innerHTML = p;
                    this.parentNode.previousElementSibling.children[0].className = 'back2';
                    counter()
                }
            }

            //车身划痕险
            var scratch_li = document.getElementById('scratch_D').children,
                sLen = scratch_li.length;
            for (i = 0;i < sLen;i++) {
                scratch_li[i].index = i;
                scratch_li[i].onclick = function () {
                    index = this.index;
                    for (j = 0;j < sLen;j++){
                        scratch_li[j].children[0].className = '';
                    }
                    scratch_li[index].children[0].className = 'back5';
                    var k = scratch_li[index].children[0].getAttribute('data-value');
                    document.getElementById('S').innerHTML = k;
                    this.parentNode.previousElementSibling.children[0].className = 'back2';
                    counter()
                }
            }

            function counter(){
                var totalNum = 0;
                $box.find('.val').each(function () {
                    totalNum += Math.ceil(this.innerHTML);
                });

                $box.find('#cankaojia').text(totalNum);
                console.log(totalNum);
            }
            counter();
        }
    });
}

// 预约保养
function service_appoin(tpl, data){
    var $box = $(this);

    var json = {form_url:API_BASE + '/api.php/v1/tools/formData?token=7ebb79884d5df49cc2659694c5abdbaf'};
    var html = Mustache.render(tpl, json);
    $box.html(html);
    $box.initUI();

    $box.find(':radio').on('change', function(event){
        var $radio = $(this);
        $('.icon-radio', $radio.parentNode()).addClass('checked');

        var $label = $radio.parentsUntil(function(){
            return $(this).is('label');
        });

        var $group = $radio.parentsUntil(function(){
            return $(this).is('div.item');
        });

        $group.find('.icon-radio').removeClass('checked');
        $label.find('.icon-radio').addClass('checked');
    });

    $box.find('#service_appoin1_city_name').touchwipe({
        touch:function(event){
            $.dialog.open({url:'select_city_pop.html?dwz_callback=select_city_pop&dwz_city_id=service_appoin1_city_id&dwz_city_name=service_appoin1_city_name&dwz_province_id=service_appoin1_province_id'});
            event.preventDefault();
            event.stopPropagation();
        }
    }).change(function(event){
        $.ajax({
            type:'GET',
            dataType: 'json',
            url: API_BASE + '/api.php/v1/tools/getAutoshopList?province='+this.data.provinceName+'&city='+this.data.cityName,
            success:function(json){
                console.log('API Data:', json);

                if (json.errorCode > 0) {
                    return;
                }

                var tpl2 = '{{#data}}<option value="">{{name}}</option>{{/data}}';
                var html = Mustache.render(tpl2, json);
                $('#service_appoin1_dealer').html(html);
            }
        });
    });

    $box.find('#service_appoin2_city_name').touchwipe({
        touch:function(event){
            $.dialog.open({url:'select_city_pop.html?dwz_callback=select_city_pop&dwz_city_id=service_appoin2_city_id&dwz_city_name=service_appoin2_city_name&dwz_province_id=service_appoin2_province_id'});
            event.preventDefault();
            event.stopPropagation();
        }
    }).change(function(event){
        $.ajax({
            type:'GET',
            dataType: 'json',
            url: API_BASE + '/api.php/v1/tools/getAutoshopList?province='+this.data.provinceName+'&city='+this.data.cityName,
            success:function(json){
                console.log('API Data:', json);

                if (json.errorCode > 0) {
                    return;
                }

                var tpl2 = '{{#data}}<option value="">{{name}}</option>{{/data}}';
                var html = Mustache.render(tpl2, json);
                $('#service_appoin2_dealer').html(html);
            }
        });
    });
}

// 经销商查询
function service_dealers(tpl){
    var $box = $(this);

    //获取gps位置
    var geolocation = new BMap.Geolocation();
    geolocation.getCurrentPosition(function(gpsPos){

        if(this.getStatus() == BMAP_STATUS_SUCCESS){

            $.ajax({
                type:'GET',
                dataType: 'json',
                url: API_BASE + '/api.php/v1/tools/getAutoshopList?lng='+gpsPos.point.lng+'&lat='+gpsPos.point.lat,
                success:function(json){
                    console.log('API Data:', json);

                    if (json.errorCode > 0) {
                        $.alert.error(json.message);
                        $box.find('div.content').html(json.message);
                        return;
                    }

                    var html = Mustache.render(tpl, json);
                    $box.html(html);
                    $box.initUI();

                    var $mapBox = $('#service-dealers-bmap');
                    $mapBox.css({'height': document.documentElement.clientHeight + 'px'});

                    var map = new BMap.Map($mapBox.attr('id'));

                    map.centerAndZoom(gpsPos.point, 12);
                    map.enableScrollWheelZoom();

                    for (var index=0; index<json.data.length; index++) {
                        var item = json.data[index];

                        var point = new BMap.Point(item.lng, item.lat);
                        dwzMap.addMarker({map:map, point:point, iconWidth:23, iconHeight:25, imageOffsetLeft: 0, imageOffsetTop: -275}, function(marker){
                            var content = '<div class="InfoWindow">'
                                + '<p>地址：'+item.address+'</p>'
                                + '<p>经度：'+item.lng+' &nbsp;&nbsp; 纬度：'+item.lat+'</p>'
                                + '</div>';

                            var infoOpt = { title: '<h2 class="InfoWindowTitle">名称：'+item.name+'</h2>'};
                            var infoWindow = new BMap.InfoWindow("<div class='InfoWindow'>" + content + "</div>", infoOpt);
                            marker.addEventListener("click", function () {
                                this.openInfoWindow(infoWindow);
                            });
                        });
                    }

                }
            });

        } else {

            $box.find('div.content').html('<p>获取GPS位置信息失败,请先开启GPS.</p>');
        }
    },{enableHighAccuracy: true});
}

// 紧急救援
function service_rescue(tpl){
    var $box = $(this);
  $.ajax({
        type: 'GET',
        url:API_BASE + "/api.php/v1/vehicles/getBrand?token=7ebb79884d5df49cc2659694c5abdbaf",
        data:{},
        dataType:"json",
        success: function(json) {
            var data = json.data;
            $.each(data)
            var html = Mustache.render(tpl, json.data);
            $box.html(html);
            $box.initUI();
        }
    });



    //获取gps位置
    var geolocation = new BMap.Geolocation();
    geolocation.getCurrentPosition(function(gpsPos){

        if(this.getStatus() == BMAP_STATUS_SUCCESS){

            $.ajax({
                type:'POST',
                dataType: 'json',
                url: API_BASE + '/api.php/v1/tools/getAutoshopList?lng='+gpsPos.point.lng+'&lat='+gpsPos.point.lat,
                success:function(json){
                    console.log('API Data:', json);

                    var html = Mustache.render(tpl, json);
                    $box.html(html);
                    $box.initUI();

                    var $mapBox = $('#service-dealers-sescue');
                    var map = new BMap.Map($mapBox.attr('id'));

                    map.centerAndZoom(gpsPos.point, 12);
                    map.enableScrollWheelZoom();

                    for (var index=0; index<json.data.length; index++) {
                        var item = json.data[index];

                        var point = new BMap.Point(item.lng, item.lat);
                        dwzMap.addMarker({map:map, point:point, iconWidth:23, iconHeight:25, imageOffsetLeft: 0, imageOffsetTop: -275}, function(marker){
                            var content = '<div class="InfoWindow">'
                                + '<p>地址：'+item.address+'</p>'
                                + '<p>经度：'+item.lng+' &nbsp;&nbsp; 纬度：'+item.lat+'</p>'
                                + '</div>';

                            var infoOpt = { title: '<h2 class="InfoWindowTitle">名称：'+item.name+'</h2>'};
                            var infoWindow = new BMap.InfoWindow("<div class='InfoWindow'>" + content + "</div>", infoOpt);
                            marker.addEventListener("click", function () {
                                this.openInfoWindow(infoWindow);
                            });
                        });
                    }

                }
            });

        } else {

            $box.find('div.content').html('<p>获取GPS位置信息失败,请先开启GPS.</p>');
        }
    },{enableHighAccuracy: true});
}

// 违章查询页面
function service_violation(tpl){
    var $box = $(this);

    var form_url = API_BASE + '/api.php/v1/tools/weizhang?token=SDUFLSKJSDF'

    getRegionData(function(regionList){
        var html = Mustache.render(tpl, {form_url:form_url, regionList:regionList});
        $box.html(html);
        $box.initUI();
    });

}
function service_violation_pop_region(tpl){
    var $box = $(this);

    getRegionData(function(regionList){
        var html = Mustache.render(tpl, {regionList:regionList});
        $box.html(html);
        $box.initUI();

        var $items = $box.find('div.city-list div.list-item');

        $items.click(function(event){
            var $this = $(this);
            $items.removeClass('activated');
            $this.addClass('activated');

            $('#service_violation_region_plate').text($this.attr('data-region-plate'));
            $('#service_violation_region_number').val($this.attr('data-region-number'));

        });

    });
}

// 违章查询测试：冀F920NV G483805 LFMAP86C7F0135529
function service_violation_done(json){
    console.log(json);

    if (json.errorCode != 0) {
        $.alert.error(json.message);
        return;
    }

    var $list = $('#service_violation_list'),
        tpl = '{{#data}}{{#records}}\
            <li>\
            <p>{{address}}</p>\
            <p>{{department}}</p>\
            <p>{{reason}}</p>\
            <p>{{time}}</p>\
            </li>\
            {{/records}}{{/data}}';

    var html = Mustache.render(tpl, json);
    $list.html(html);
}

// 应用管理页面
function service_diy(tpl, data) {
    var $box = $(this);

    $.ajax({
        type:'GET',
        dataType: 'json',
        url: API_BASE + '/api.php/v1/tools/reldiyManage?token=7ebb79884d5df49cc2659694c5abdbaf',
        data: {},
        success:function(json){
            console.log('API Data:', json);

            var html = Mustache.render(tpl, {form_url:API_BASE + '/api.php/v1/tools/reldiy?token=7ebb79884d5df49cc2659694c5abdbaf', data:json.data});
            $box.html(html);
            $box.initUI();

            var frag = {
                boxAdd: '<a class="col diy-icon box-add"><i class="icon-add"></i></a>',
                boxLine: '<a class="col diy-icon box-line" data-diy-key="{{key}}">\
                    <input type="hidden" name="i[]" value="{{key}}">\
                    <span class="img-icon"><img class="img-icon-home" src="images/icon-png/icon-s-{{key}}.png"></span>\
                    <span class="img-icon-name">{{name}}</span>\
                    <i class="icon-delete"></i>\
                </a>'
            };

            var $addBox = $box.find('div.diy-add-box'),
                $content = $box.find('div.grids-contant'),
                $diyItems = $content.find(':scope > .grids-grid');

            function initAddBox(){
                var $selectIcons = $content.find('.icon-right');
                var html = '';

                $selectIcons.each(function(){
                    var $selectItem = $(this).parentsUntil(function(){
                        return $(this).is('.grids-grid');
                    });
                    html += Mustache.render(frag.boxLine, {key: $selectItem.attr('data-diy-key'), name: $selectItem.attr('data-diy-name')});
                });

                for (var index=$selectIcons.size(); index<4; index++) {
                    html += frag.boxAdd;
                }

                $addBox.html(html);

                // 删除应用
                $addBox.find('.box-line').click(function(event){
                    var $this = $(this);

                    var key = $this.attr('data-diy-key');

                    $diyItems.each(function(){
                        if ($(this).attr('data-diy-key') == key) {
                            $(this).find('i').addClass('icon-add').removeClass('icon-right');
                        }
                    });

                    initAddBox();

                });
            }

            $diyItems.click(function(event){
                var $this = $(this),
                    $icon = $this.find('i'),
                    $selectIcons = $content.find('.icon-right');

                if ($icon.hasClass('icon-add') && $selectIcons.size() < 4) {

                    $icon.removeClass('icon-add').addClass('icon-right');

                    initAddBox();
                }
            });

            initAddBox();
        }
    });


}

// 应用管理
function service_diy_done(json){
    console.log(json);

    if (json.errorCode != 0) {
        $.alert.error(json.message);
        return;
    }

    $.navView.close();
    $.navTab.open({tabid:'home', url: 'home.html?dwz_callback=home_render'});
}

// 备件查询页面
function service_spared(tpl){
    var $box = $(this);

    var html = Mustache.render(tpl, {});
    $box.html(html);
    $box.initUI();
}

// 限行查询
function service_traffic(tpl){
    var $box = $(this);
	
    $.ajax({
        type:'GET',
        dataType: 'json',
        url: API_BASE + '/api.php/v1/tools/restrictions?token=7ebb79884d5df49cc2659694c5abdbaf',
        data: {},
        success:function(json){
            console.log('API Data:', json);
             console.log('API Data:', 1);

            var html = Mustache.render(tpl, json);
            $box.html(html);
            $box.initUI();
      
    }
    })

}

// 产品手册
function service_manual(tpl){
    var $box = $(this);

    var html = Mustache.render(tpl, {});
    $box.html(html);
    $box.initUI();
}

// 指示灯手册
function service_indicator(tpl){
    var $box = $(this);

    var html = Mustache.render(tpl, {});
    $box.html(html)
    $box.initUI();
}

// 爱车讲堂
function service_lecture(tpl){
    var $box = $(this);

    var height = $box.first().offsetHeight-70 + 'px';

    var html = Mustache.render(tpl, {height:height});
    $box.html(html);
    $box.initUI();
}

// 精彩杂志
function service_journal(tpl){
    var $box = $(this);

    var height = $box.first().offsetHeight-70 + 'px';

    var html = Mustache.render(tpl, {height:height});
    $box.html(html);
    $box.initUI();
}