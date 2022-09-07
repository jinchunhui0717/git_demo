<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet"
	href="${ctxStatic}/plugins/bootstrap-table/css/bootstrap-table.min.css">

<script>

	$(function() {
		InitMainTable();
		$("#btnSearch").click(function() {
			$('#tbContent').bootstrapTable(('refresh'));
		});
		$(document).on('click','.btnEdit',function(){
			layer.open({
				type:2,
				area:['auto','90%'],
				content:'${ctx}/alg/task/taskdetailinspect?id='+$(this).data('taskdetailid')
				
			});
		})
	});
	
	//初始化bootstrap-table的内容
	function InitMainTable() {
		//记录页面bootstrap-table全局变量$table，方便应用
		var queryUrl = '${ctx}/alg/task/taskdetaillistdata';
		$('#tbContent')
				.bootstrapTable(
						{
							formatLoadingMessage : function() {
								return '';
							},
							cardView : IsMobile(),
							striped : true,
							undefinedText : "",//字段是空的时候 默认显示 "-"  ,现在修改为""
							silent : true,
							url : queryUrl, //请求后台的URL（*）
							toolbarAlign : 'right', // 工具栏对齐方式
							dataType : "json", // 数据类型
							method : 'GET', //请求方式（*）
							cache : false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
							pagination : true, //是否显示分页（*）
							sidePagination : "server", //分页方式：client客户端分页，server服务端分页（*）
							pageNumber : 1, //初始化加载第一页，默认第一页,并记录
							pageSize : 10,
							pageList : [ 10, 25, 50, 100 ],
							//得到查询的参数-提交到服务器端的参数
							queryParams : function(params) {
								//这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
								var temp = {
									limit : params.limit, //页面大小
									offset : params.offset,
									task_id:'${id}'
								//页码
								};
								return temp;
							},
							columns : [
									 {
							            title : '编号',
										field : 'code'
									}, {
							            title : '条码',
										field : 'barcode'
									},  {
							            title : '设备类型',
										field : 'equip_type_name'
									}, {
							            title : '办公',
										field : 'office_name'
									},  {
							            title : '地点',
										field : 'region_name'
									}, {
							            title : '监控X,Y坐标',
										field : 'posx',
										formatter:function(value,row,index){
											return row.posx+','+row.posy
										}
									}, {
							            title : '监控区域',
										field : 'pos_region'
									}, {
							            title : '巡检人',
										field : 'user_name'
									}, {
										align : 'center',
										valign : 'middle',
										field : 'imageurl',
										title : '图片',
										formatter : imgFormatter
									}, {
							            title : '状态',
							            formatter:function(value,row,index){
											if (row.equip_state=='g') {
												return '<span class="label label-success">已巡检</span>';
											} else {
												return '<span class="label label-warning">未巡检</span>';
											}
										}
									}, {
							            title : '检查项',
										formatter:function(value,row,index){
											if (row.equip_state=='g') {
											return '<button class="btn btn-default btnEdit" title="检查项" data-taskdetailid="'+row.task_detail_id+'"><span class="glyphicon glyphicon-th"></span></button>'
											}else{
												return '';
											}
										}
									}],

							responseHandler : function(res) {
								console.log(res.rows)
								return {
									total : res.total,
									rows : res.rows
								};
							}
						});
		
	}
	function imgFormatter(value, row, index) {
		if (row.equip_state==undefined || $.trim(row.equip_state)=='') {
			result = '<img  width="60" height="60" src="${ctxStatic}/img/noimg.png"></img>';
		}else{
			result = '<a href="javascript:bigimgfunc(\'${ctxStatic}/../upload/'+row.imageurl+'\')"><img class="photoimg" src="${ctxStatic}/../upload/thumb/'+row.imageurl+'"></img></a>';
		}
		
		return result;
	}
	function bigimgfunc(url){
		$('#bigimage').attr('src','');
		$('#bigimage').attr('src',url);
		var layer_msg_id;
		layer.open({
            type:1,
            title:'原图',
            offset: '50px',
            area:['100%','90%'],
            content:$('#bigimage'),
          	success:function(){
          		layer_msg_id = layer.msg('正在加载图片...', {
        			icon : -1,
        			shade : 0,
        			area : [ '150px', '50px' ],//固定大小，解决大小忽闪的问题
        			time : false
        		//取消自动关闭（默认2秒自动关闭）
        		});
          	}
        })
		$('#bigimage').load(function(){
			layer.close(layer_msg_id);
		})
        
    }

</script>
</head>
<body>
	<div class="box">
	<img id="bigimage" style="display: none" width="100%" src="" />
		<div class="box-header with-border">
			<button class="btn btn-default" onclick="javascript:window.history.go(-1)">返回</button>
			<div class="pull-right">
				<button id="btnSearch" class="btn btn-primary">查询</button>
			</div>
		</div>
		<div class="box-body">
			<table id="tbContent"></table>
		</div>

	</div>
	<script
		src="${ctxStatic}/plugins/bootstrap-table/js/bootstrap-table.min.js"></script>
	<script
		src="${ctxStatic}/plugins/bootstrap-table/js/bootstrap-table-zh-CN.min.js"></script>

</body>
</html>