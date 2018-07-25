
$(function(){
	layui.use(['layer', 'form'], function(){
	  var layer = layui.layer
	  ,form = layui.form();
	});

	$('#importData' ).change(function(){  
		var rABS = false;
		var file = $("#importData");
		var fileName = file.val();
		var fileObj = file[0];
		var fileName = $("#importData").val();
		var point = fileName.lastIndexOf(".");
		var type = fileName.substr(point);
		var arr = new Array();
		arr = fileName.split("\\");
		//文件非空且为excel文件
		if(!fileObj||(type!='.xls'&&type!='.xlsx')) {
			 layer.msg("您上传的文件格式不符合要求", {icon: 5, anim: 6});
			return;}
		var str12 = "您要上传的文件名称为：" + arr[arr.length - 1] + "，是否确认上传？";
		 layer.confirm(str12, {icon: 1, title:'确认导入信息', anim: 1}, function (index) {
			 var f = fileObj.files[0];
				var reader = new FileReader();
				reader.onload = function(e) {	//定义生成事件
					var data = e.target.result;
					if(rABS) {
						wb = XLSX.read(btoa(fixdata(data)), {//手动转化
							type: 'base64'
						});
					} else {
						wb = XLSX.read(data, {
							type: 'binary'
						});
					}
					var jsonData= XLSX.utils.sheet_to_json(wb.Sheets[wb.SheetNames[0]]) ;
					var data = JSON.stringify(jsonData);
					$.ajax({	//发起请求
					   "url":"score_input",
					   "data":data,
					   "type":"post",
						"contentType": "application/json;charset=utf-8",
					   "error":function(){
						   alert("服务器未正常响应，请重试");
					   },
					  "success":function(response){
						   if(response == 1) {
							   layer.msg('保存成功', {icon: 6,time: 700}); 
							   setTimeout("location.reload()", 800);
						   } else {
							   layer.confirm(response, {icon: 3, title:'未成功导入的数据', anim: 6});
						   }
					   }
					});
				};
				if(rABS) {	//读取文件
					reader.readAsArrayBuffer(f);
				} else {
					reader.readAsBinaryString(f);
				}
		 }, function (index) {
			 $(".layui-upload-file").val("");
		 });
		
	});
	
	function fixdata(data) { //文件流转BinaryString
		var o = "",
			l = 0,
			w = 10240;
		for(; l < data.byteLength / w; ++l) o += String.fromCharCode.apply(null, new Uint8Array(data.slice(l * w, l * w + w)));
		o += String.fromCharCode.apply(null, new Uint8Array(data.slice(l * w)));
		return o;
	}
	
	
	var table = $('#layui-table').DataTable({
	   "ajax": {
		   "url": "get_teachers",
		   "dataSrc": "data",//默认为data
		   "type": "post",
		   "error":function(){alert("服务器未正常响应，请重试");}
	   },
	   "columns": [
			{ "data": "collegeName", "title":"学院","defaultContent":""},
			{ "data": "phone", "title":"学院电话","defaultContent":""},
			{ "data": "jobNumber", "title":"工号","defaultContent":""},
			{ "data": "name", "title":"姓名","defaultContent":""},
			{ "data": "position", "title":"职称","defaultContent":""},
		   { "data": "studentScore", "title":"学生评教成绩","defaultContent":""},
		   { "data": null, "title":"操作","defaultContent": "<button class='edit-btn layui-btn layui-btn-normal' type='button'>编辑</button> "}
	   ],
	   "language": {
		   "sProcessing": "处理中...",
		   "sLengthMenu": "显示 _MENU_ 项结果",
		   "sZeroRecords": "没有匹配结果",
		   "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
		   "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
		   "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
		   "sInfoPostFix": "",
		   "sSearch": "搜索:",
		   "sUrl": "",
		   "sEmptyTable": "表中数据为空",
		   "sLoadingRecords": "载入中...",
		   "sInfoThousands": ",",
		   "oPaginate": {
			   "sFirst": "首页",
			   "sPrevious": "上页",
			   "sNext": "下页",
			   "sLast": "末页"
		   },
		   "oAria": {
			   "sSortAscending": ": 以升序排列此列",
			   "sSortDescending": ": 以降序排列此列"
		   }
	   }
   });
	//点击编辑按钮
   $("#layui-table tbody").on("click",".edit-btn",function(){
		var preBrother = $(this).parent().prev();
		var txt =  preBrother.text().trim();
		if(txt=='')txt = 0;
		var html="<input type='text' value='"+txt+"'>";
		preBrother.html(html);

		$(this).html("保存");
		$(this).toggleClass("edit-btn layui-btn layui-btn-normal");
		$(this).toggleClass("save-btn layui-btn ");
		$('#layui-table tbody input').addClass("form-control");
   });
   
	//点击保存按钮
   $("#layui-table tbody").on("click",".save-btn",function(){
	   var thisObj=$(this);
	   var row=table.row($(this).parents("tr"));
	   var tds=$(this).parents("tr").children("td");
	   var jobNumber = tds.eq(2).text().trim();
	   var strRateScore = tds.eq(5).children("input").val();
	  
	   
	   /*$.each(tds, function(i,val){
		   var jqob=$(val);
		   //把input变为字符串
		   if(!jqob.has('button').length){
			   var txt=jqob.children("input").val();
			   jqob.html(txt);
			   table.cell(jqob).data(txt);//修改DataTables对象的数据
		   }
	   });*/
	   //var data=row.data();
	   var jsonData={"jobNumber":jobNumber,"studentScore":strRateScore};
	   $.ajax({
		   "url":"save_student_score",
		   "data":jsonData,
		   "type":"post",
		   "error":function(){
        		   var preBrother = $(this).parent().prev();
        			var txt =  preBrother.text().trim();
        			if(txt=='')txt = 0;
        			var html="<input type='text' value='"+txt+"'>";
        			preBrother.html(html);

        	   layer.msg("您的输入有误，请认真核对", {icon: 5, anim: 0});
           },
           "success":function(response){
               if(response=="1") {
					
            	   tds.eq(5).html(strRateScore);
            	   table.cell(tds.eq(5)).data(strRateScore);
            	   thisObj.html("编辑");
            	   thisObj.toggleClass("edit-btn layui-btn layui-btn-normal");
            	   thisObj.toggleClass("save-btn layui-btn ");
					layer.msg('保存成功', {icon: 6,time: 700}); 
			   }
			   else {
				   var preBrother = $(this).parent().prev();
					var txt =  preBrother.text().trim();
					if(txt=='')txt = 0;
					var html="<input type='text' value='"+txt+"'>";
					preBrother.html(html);
				   layer.msg(response, {icon: 5, anim: 0});
			   }
           }
	   });
   });

   //批量点击编辑按钮
   $("#batch-edit-btn").click(function(){
	   $(".edit-btn").click();
   });
   $("#batch-save-btn").click(function(){
	   $(".save-btn").click();
   });
   
   //计算期末成绩按钮
   $("#batch-compute-btn").click(function () {
	   $.ajax({
		   "url":"compute_semester",
		   "type":"get",
		   "error":function(){
	    	   layer.msg("系统繁忙，请稍后再试", {icon: 5, anim: 0});
	       },
	       "success":function(data){
	    	   if(data == 1) {
				   layer.msg('计算完成', {icon: 6,time: 700});
			   } else {
				   layer.msg(data, {icon: 5, anim: 0});
			   }
	       }
	   });
   });
});  





