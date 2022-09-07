<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet"
	href="${ctxStatic}/plugins/bootstrap-table/css/bootstrap-table.min.css">
<link rel="stylesheet"
	href="${ctxStatic}/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css">
<script>

	$(function() {
		$('#datetimepickerstart,#datetimepickerend').datetimepicker({
	        language:  'zh-CN',
	        weekStart: 1,
	        todayBtn:  1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 3,
			minView: 3,
			forceParse: 0
	    })
		
		InitMainTable();
		$("#btnSearch").click(function() {
			$('#tbContent').bootstrapTable(('refresh'));
		});
		$("#btnExport").click(function() {
			var tmpoption = $('#tbContent').bootstrapTable('getOptions');	
			var params = {};
			params = tmpoption.queryParams(params);
			params.sort = tmpoption.sortName;
			params.order = tmpoption.sortOrder;

			window.location.href="${ctx}/alg/task/export?"+$.param(params);
		});
		$(document).on('click','.btnEdit',function(){
			layer.open({
				title:' ',
				type:2,
				area:['600px','90%'],
				content:'${ctx}/alg/task/taskdetailinspect?id='+$(this).data('taskdetailid')
				
			});
		})
	});
	
	//初始化bootstrap-table的内容
	function InitMainTable() {
		//记录页面bootstrap-table全局变量$table，方便应用
		var queryUrl = '${ctx}/alg/task/taskresultdata';
		var options = {
				cardView : IsMobile(),
				url : queryUrl, //请求后台的URL（*）
				queryParams : function(params) {
					params.task_create_date_start=$.trim($('#datetimepickerstart').val());
					params.task_create_date_end = $.trim($('#datetimepickerend').val());
					params.code=$.trim($('#txtCode').val());
					params.barcode=$.trim($('#txtBarcode').val());
					params.equip_type_name=$.trim($('#txtType').val());
					params.region_name=$.trim($('#txtRegion').val());
					
					return params;
				},
				columns : [
						 {  sortable:true,
				            title : '<spring:message code="task_cycle"/>',
							field : 'task_create_date',
							formatter:function(value,row,index){
								return value.substring(0,7);
							}
						},{  sortable:true,
				            title : '<spring:message code="sscode"/>',
							field : 'code'
						}, {sortable:true,
				            title : '<spring:message code="barcode"/>',
							field : 'barcode'
						},  {sortable:true,
				            title : '<spring:message code="equip_type"/>',
							field : 'equip_type_name'
						}, {sortable:true,
				            title : '<spring:message code="position"/>',
							field : 'office_name'
						},  {sortable:true,
				            title : '<spring:message code="region"/>',
							field : 'region_name'
						}, {sortable:true,
				            title : '<spring:message code="monitor_region"/>',
							field : 'pos_region'
						}, {sortable:true,
				            title : '<spring:message code="operator_people"/>',
							field : 'user_name'
						}, {sortable:true,
				            title : '<spring:message code="inspectdate"/>',
							field : 'opt_date'
						}, {
							align : 'center',
							valign : 'middle',
							field : 'imageurl',
							title : '<spring:message code="picture"/>',
							formatter : imgFormatter
						}, {
							sortable:true,
							field:'equip_state',
				            title : '<spring:message code="state"/>',
				            formatter:function(value,row,index){
								if (row.equip_state=='g') {
									return '<span class="label label-danger"><spring:message code="inspect_pass"/></span>';
								} else if (row.equip_state=='r') {
									return '<span class="label label-warning"><spring:message code="inspect_nopass"/></span>';
								} else {
									return '<span class="label label-default"><spring:message code="inspect_no"/></span>';
								}
							}
						}, {
				            title : '<spring:message code="check_item"/>',
				            formatter:function(value,row,index){
								if (row.equip_state=='g'||row.equip_state=='r') {
								return '<a class=" btnEdit"  style="cursor:pointer" title="<spring:message code="check_item"/>" data-taskdetailid="'+row.task_detail_id+'"><spring:message code="check_item"/></a>'
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
			};
		if ('<%=response.getLocale().getLanguage()%>'=='zh') {
			options.locale = 'zh-CN';
		}else if ('<%=response.getLocale().getLanguage()%>'=='en') {
			options.locale = 'en-US';
		}
		$('#tbContent')
				.bootstrapTable(options
						);
		
	}
	function imgFormatter(value, row, index) {
		if (row.equip_state==undefined || $.trim(row.equip_state)=='') {
			result = '<img  width="20" height="20" src="${ctxStatic}/img/noimg.png"></img>';
		}else{
			result = '<a href="javascript:bigimgfunc(\'${ctxStatic}/../upload/'+row.imageurl+'\')"><img  width="20" height="20" class="photoimg" src="${ctxStatic}/../upload/thumb/'+row.imageurl+'"></img></a>';
		}
		
		return result;
	}
	function bigimgfunc(url){
		var width = 'auto';
		if (IsMobile()) {
			$('#bigimage').attr('width', '100%');
			width='100%';
		}
		$('#bigimage').attr('src', '');
		$('#bigimage').attr('src', url);
		var layer_msg_id;
		layer.open({
			type: 1,
			title: ' ',
			offset: '50px',
			area:[width,'90%'],
			content: $('#bigimage'),
			success: function() {
          		//layer.iframeAuto(index);
          		layer_msg_id = layer.msg('<spring:message code="msg_wait"/>', {
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
		<img id="bigimage" style="display: none" width="600px" src="" />
		<div class="box-header with-border">
			<div class="form-inline" style="text-align: right;">
					<div class="input-group">
						<span class="input-group-addon"><spring:message code="task_cycle"></spring:message></span> 
						<input class="form-control"  type="text" id="datetimepickerstart" data-date-format="yyyy-mm">
						<span class="input-group-addon">-</span> 
						<input class="form-control"  type="text" id="datetimepickerend" data-date-format="yyyy-mm">
					
					</div>
					<div class="input-group">
						<span class="input-group-addon"><spring:message code="sscode"></spring:message></span> <input
							id="txtCode" type="text" class="form-control">
					</div>
					<div class="input-group">
						<span class="input-group-addon"><spring:message code="barcode" /></span> <input
							id="txtBarcode" type="text" class="form-control">
					</div>
					<div class="input-group">
						<span class="input-group-addon"><spring:message code="equip_type" /></span> 
						
							<select id="txtType" class="form-control">
								<option></option>
								<c:forEach items="${equiptype }" var="item">
									<option value="${item.name }">${item.name }</option>
								</c:forEach>
							</select>
					</div>
					<div class="input-group">
						<span class="input-group-addon"><spring:message code="region" /></span>
							<select id="txtRegion" class="form-control">
							<option></option>
							<c:forEach items="${region }" var="item">
							<option value="${item.name }">${item.name}</option>
							</c:forEach>
							</select>
					</div>
					<button id="btnSearch" class="btn btn-default">
					<span class="glyphicon glyphicon-search"></span> <spring:message code="search"/></button>
					<button id="btnExport" class="btn btn-default">
					<span class="glyphicon glyphicon-export"></span> <spring:message code="export"/></button>
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
	<script
		src="${ctxStatic}/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
	<script
		src="${ctxStatic}/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
</body>
</html>