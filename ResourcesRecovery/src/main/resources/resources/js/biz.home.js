/**
 * Created by rogerwu on 12/17/16.
 */

function home_render(tpl){
    var $box = $(this);

    $.ajax({
        type:'GET',
        dataType: 'json',
        url: API_BASE + '/api.php/v1/News/indexTopList',
        data: {},
        success:function(json){
            console.log('API Data:', json);
            // console.log(json.data.title)

            var config = {
                'key1':{
                    url:'service_appoin.html?dwz_callback=service_appoin',
                    rel:'service_appoin'
                },
                'key2':{
                    url:'service_violation.html?dwz_callback=service_violation',
                    rel:'service_violation'
                },
                'key3':{
                    url:'service_rescue.html?dwz_callback=service_rescue',
                    rel:'service_rescue'
                },
                'key4':{
                    url:'service-dealers.html?dwz_callback=service_dealers',
                    rel:'service-dealers'
                },
                'key5':{
                    url:'service_spared.html?dwz_callback=service_spared',
                    rel:'service_spared'
                },
                'key6':{
                    url:'service_traffic.html?dwz_callback=service_traffic',
                    rel:'service_traffic'
                },
                'key7':{
                    url:'service_insurance.html?dwz_callback=service_insurance',
                    rel:'service_insurance'
                },
                'key8':{
                    url:'service_financial.html?dwz_callback=exhibition_news_finance',
                    rel:'service_financial'
                },
                'key9':{
                    url:'service_journal.html?dwz_callback=service_journal',
                    rel:'service_journal'
                }
            };

            for (var index=0; index<json.data.button.length; index++) {
                json.data.button[index]['config'] = config['key'+json.data.button[index]['i']];
            }

            var html = Mustache.render(tpl, json);
            $box.html(html);
            $box.initUI();
        }
    });
}
