
function exhibition_vehicles_getBrand(tpl) {
    var $box = $(this);
            $.ajax({
                type: 'GET',
                url:API_BASE + "/api.php/v1/vehicles/getBrand",
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
}

function exhibition_vehicles_getBrandOne(tpl, data) {
    var $box = $(this);
    $.ajax({
        type: 'GET',
        url:API_BASE + "/api.php/v1/vehicles/getBrandOne?cid=" + data.id,
        data:{},
        dataType:"json",
        success: function(json) {
            console.log(json,'ppp')
            var html = Mustache.render(tpl, json.data);
            $box.html(html);
            $box.initUI();

            var $pic = $("#exhibitionbranddetail", $box);
            var $ips = $(".item-inner", $pic);
            $ips.eq(0).removeClass("cached").addClass("active");

            var $btns = $(".btn-color", $pic);
            $btns.eq(0).addClass("active");

            var $ipic = $(".item-picture", $pic);
            var $icol = $(".item-color", $pic);

            $.each($btns, function(idx){
                var $this = $btns.eq(idx);
                $this.click(function(){
                    $(".active", $ipic).removeClass("active").addClass("cached");
                    $(".active", $icol).removeClass("active");
                    $this.removeClass("cached").addClass("active");
                    $ips.eq(idx).removeClass("cached").addClass("active");
                });
            });

            // 暂时js设置宽度，以后改到css中
            var $scrollGrad = $('#exhibition-d-setting .scroll-grid');
            var gridColCount = $scrollGrad.find(':scope > .grid-content > .grid-col').size();
            if (gridColCount){
                $scrollGrad.css({'width':(gridColCount*100)+'px'});
            }
            var myScroll = new IScroll('#exhibition-d-setting .grid-table2', {
                scrollX: false,
                scrollY: true,
                lockDirection: true,
                probeType: 3,
            });
            //myScroll.enableStickyHeaders('.grid-row.title');
            var myScroll1 = new IScroll('#exhibition-d-setting .grid-table1 .grid-content-box', {
                scrollX: true,
                scrollY: false,
                lockDirection: true,
                probeType: 3,
            });
            var myScroll2 = new IScroll('#exhibition-d-setting .grid-table2 .grid-content-box', {
                scrollX: true,
                scrollY: false,
                probeType: 3,
                lockDirection: true,
            });
            myScroll1.on('scroll', function(){
                myScroll2.scrollTo(this.x, 0, 0, false);
            });
            myScroll2.on('scroll', function(){
                myScroll1.scrollTo(this.x, 0, 0, false);
            });
            $box.find('#car-detail-top-tab').on(dwz.event.type.iScrollRefresh, function(event){
                if (myScroll) myScroll.refresh(); // 处理tab页默认cached的页面滚动获取不到尺寸问题
                if (myScroll1) myScroll.refresh();
                if (myScroll2) myScroll.refresh();
            });
            $box.onPageClear(function(event){
                if (myScroll) myScroll.destroy();
                if (myScroll1) myScroll.destroy();
                if (myScroll2) myScroll.destroy();
                myScroll = null;
                myScroll1 = null;
                myScroll2 = null;
            });
        }
    });
}

// 促销活动
function exhibition_news_newsInformation(tpl, data){
    var $box = $(this);

    $.ajax({
        type:'GET',
        dataType: 'json',
        url: API_BASE + '/api.php/v1/News/activityCenter',
        data: {},
        success:function(json){
            console.log('API Data:', json);
            console.log("促销活动")
            var html = Mustache.render(tpl, json);
            $box.html(html);
            $box.initUI();
        }
    });
}


// 金融产品
function exhibition_news_finance(tpl, data){
    var $box = $(this);

    $.ajax({
        type:'GET',
        dataType: 'json',
        url: API_BASE + '/api.php/v1/news/serverfinance',
        data: {},
        success:function(json){
            console.log("金融产品1")
            console.log('API Data:', json);
	
            var html = Mustache.render(tpl, json);
            $box.html(html);
            $box.initUI();
				

			//车型选选择
            var $vehicleInput = $box.find('select[name=vehicle]'),
                $modelInput = $box.find('select[name=model]'),
                $fanganInput = $box.find('select[name=fangan]'),
                $daikuanqixianInput = $box.find('select[name=daikuanqixian]'),
                $soufukuan = $box.find('#service_financal_soufukuan'),
                $baojia = $('#service_financal_baojia'),
                $shichang = $('#service_financal_shichang'),
                $cankaojia = $('#service_financal_cankaojia');

            var vehicleData = json.data.vehicle;
            var vehicleTpl = '<option value="">车型：</option>{{#vehicle}}<option value="{{cid}}">{{name}}</option>{{/vehicle}}';
            var vehicleHtml = Mustache.render(vehicleTpl, json.data);
            $vehicleInput.html(vehicleHtml);

            //选择完车型后的报价
            $vehicleInput.on('change', function (event) {
                var val = $vehicleInput.val();

                if (val) {
                    $.each(vehicleData, function(index){
                        if (this.cid == val) {
                            var currentData = vehicleData[index];

                            var modelTpl = '{{#childs}}<option value="{{price}}">{{name}}</option>{{/childs}}';
                            var modelHtml = Mustache.render(modelTpl, currentData);
                            $modelInput.html(modelHtml);
                        }
                    });
                } else {
                    $modelInput.html('<option value="">型号：</option>');
                }

                $modelInput.trigger('change');
            });

            $modelInput.on('change', calculate);

            //选择贷款方案
            var $listBox = $('#service_financal_listBox1, #service_financal_listBox2');
            var $lis = $listBox.find('li');

            $lis.click(function(event){
                var $li = $(this);
                $lis.find('span').removeClass('back5');
                $li.find('span').addClass('back5');

                calculate();
            });


            //选择年限
            $daikuanqixianInput.on('change', calculate);

            //计算方案:
            function calculate(){
                var price = $modelInput.val() ? parseInt($modelInput.val()) : 0;

                $baojia.text(price/10000);
                $shichang.text(price/10000);

                var $checkedLi = $listBox.find('li span.back5');
                if ($checkedLi.size() > 0) {
                    var rate = $checkedLi.attr('data-val');
                    var soufukuan = $soufukuan.text();
                    var yuefu = parseInt((price-soufukuan)/rate*100)/100/10000;
                    $cankaojia.text(yuefu.formatCurrency());
                }

            }

        }
    });
}

// 新闻资讯
function exhibition_news_xinwen(tpl, data){
    var $box = $(this);

    $.ajax({
        type:'GET',
        dataType: 'json',
        url: API_BASE + '/api.php/v1/News/newsInformation',
        data: {},
        success:function(json){
            console.log("新闻资讯");
            console.log('API Data:', json);

            var html = Mustache.render(tpl, json);
            $box.html(html);
            $box.initUI();
        }
    });
}

// 新闻list
function exhibition_news_list(tpl, data){
    var $box = $(this);
    $.ajax({
        type:'GET',
        dataType: 'json',
        url: API_BASE + '/api.php/v1/News/archiveInfo?arcId='+data.id,
        data: {},
        success:function(json){
            console.log("新闻 list")
            console.log('API Data:', json);

            var html = Mustache.render(tpl, json);
            $box.html(html);
            $box.initUI();
        }
    });
}

// 轮播详情  
function exhibition_news_archiveInfo(tpl, data){
    var $box = $(this);

    $.ajax({
        type:'GET',
        dataType: 'json',
        url: API_BASE + '/api.php/v1/News/archiveInfo?arcId='+data.id,
        data: {},
        success:function(json){
            console.log('API Data:','info', json);

            if (json.data.url) {
                json.data.height = $box.first().offsetHeight-2 + 'px';
            }

            json.data.collect_url = API_BASE + '/api.php/v1/user/myColl?token=7ebb79884d5df49cc2659694c5abdbaf&id='+json.data.id+'&type=tuijian';
            var html = Mustache.render(tpl, json.data);
            $box.html(html);
            $box.initUI();

        }
    });
}

// ①我的 收藏 
function exhibition_news_myColl(tpl, data){
    var $box = $(this);

    $.ajax({
        type:'get',
        dataType: 'json',
        url: API_BASE + '/api.php/v1/news/myCollArtinfo?token=7ebb79884d5df49cc2659694c5abdbaf&id='+data.id,
        data: {
        },
        success:function(json){
            console.log('API Data:','info', json);

            if (json.data.url) {
                json.data.height = $box.first().offsetHeight-2 + 'px';
            }

            json.data.collect_url = API_BASE + '/api.php/v1/user/myColl?token=7ebb79884d5df49cc2659694c5abdbaf&id='+json.data.id;
            var html = Mustache.render(tpl, json.data);
            $box.html(html);
            $box.initUI();

        }
    });
}


//促销活动
function exhibition_cuxiaohuodong(tpl, data){
    var $box = $(this);

    $.ajax({
        type:'GET',
        dataType: 'json',
        url: API_BASE + '/api.php/v1/News/activityCenter',
        data: {},
        success:function(json){
            console.log("cuxiao")
            console.log('API Data:', json);

            var html = Mustache.render(tpl, json);
            $box.html(html);
            $box.initUI();
        }
    });
}

// function exhibition_news_archiveInfo(tpl, data){
//     var $box = $(this);

//     $.ajax({
//         type:'GET',
//         dataType: 'json',
//         url: API_BASE + '/api.php/v1/News/archiveInfo?arcId='+data.id,
//         data: {},
//         success:function(json){
//             console.log('API Data:', json);

//             var html = Mustache.render(tpl, json.data);
//             $box.html(html);
//             $box.initUI();
//         }
//     });
// }

// 车型福利
function exhibition_chezhufuli(tpl, data){
     var $box = $(this);

    $.ajax({
        type:'GET',
        dataType: 'json',
        url: API_BASE + '/api.php/v1/News/welfareList',
        data: {},
        success:function(json){
            console.log('fuli')
            console.log('API Data:', json);
            console.log($box)
            var html = Mustache.render(tpl, json);
            $box.html(html);
            $box.initUI();
        }
    });
}
//金融中心
function exhibition_jinrong(tpl, data){
     var $box = $(this);

    $.ajax({
        type:'GET',
        dataType: 'json',
        url: API_BASE + '/api.php/v1/News/financeList',
        data: {},
        success:function(json){
            console.log('金融产品')
            console.log('API Data:', json);
            var html = Mustache.render(tpl, json);
            $box.html(html);
            $box.initUI();
        }
    });
}

// 媒体声音
function exhibition_meitishengyin(tpl, data){
     var $box = $(this);

    $.ajax({
        type:'GET',
        dataType: 'json',
        url: API_BASE + '/api.php/v1/news/publicityList?cid='+data.id,
        data: {},
        success:function(json){
            console.log('API Data:', json);
            var html = Mustache.render(tpl, json);

            $box.html(html);
            $box.initUI();
        }
    });
}

// 试驾页面
function exhibition_test_drive(tpl, data) {
    var $box = $(this);

    $.ajax({
        type:'GET',
        dataType: 'json',
        url: API_BASE + '/api.php/v1/Vehicles/getClationOne?cid='+data.cid,
        data: {},
        success:function(json){
            console.log('API Data:', json);

            if (json.errorCode > 0) {
                $.alert.error(json.message);
                $box.find('.content').html(json.message);
                return;
            }

            json.data.form_url = API_BASE + '/api.php/v1/tools/formData?token=7ebb79884d5df49cc2659694c5abdbaf';
            var html = Mustache.render(tpl, json.data);
            $box.html(html);
            $box.initUI();

            var $dealersBox = $('#test_dirver_dealers_box');

            $box.find('#test_drive_city_name').touchwipe({
                touch:function(event){
                    $.dialog.open({url:'select_city_pop.html?dwz_callback=select_city_pop&dwz_city_id=test_drive_city_id&dwz_city_name=test_drive_city_name'});
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
                            $dealersBox.html('<p>'+json.message+'</p>');
                            return;
                        }

                        var tpl2 = '{{#data}}<div class="item-phone b-line">\
                                <a href="tel://{{&phone_service}}" class="item-phone-lin r-line" target="redirect">\
                                    <p class="item-phone-title">{{name}}</p>\
                                    <p class="item-phone-sub">客户服务热线{{&phone_service}}</p>\
                                </a>\
                                <a class="item-phone-map" href="view_map.html?dwz_callback=view_map&name={{name}}&phone={{&phone_service}}&address={{address}}&lng={{lng}}&lat={{lat}}" target="navView" rel="view_map">\
                                    <img src="images/icon-png/icon-location.png" class="item-phone-icon">\
                                    <p class="item-phone-head">查看地图</p>\
                                </a>\
                                </div>{{/data}}';
                        var html = Mustache.render(tpl2, json);
                        $dealersBox.html(html);
                        $dealersBox.initUI();

                        var $scrollBox = $dealersBox.parentsUntil(function(){
                            return dwz.hasClass(this, 'dwz-scroll');
                        });

                        if ($scrollBox.size() > 0) {
                            $scrollBox.trigger(dwz.event.type.iScrollRefresh);
                        }
                    }
                });
            });


        }
    });
}
