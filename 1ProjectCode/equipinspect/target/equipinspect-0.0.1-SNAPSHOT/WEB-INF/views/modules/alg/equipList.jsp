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
		$('#txtRegion').change(function() {
			$.get('${ctx}/alg/equip/getofficebyregion',{regionid:$('#txtRegion').val()},function(data){
				$('#txtOffice').empty();
				$('#txtOffice').append('<option value=""></option>');
				data.forEach(function(item) {
					$('#txtOffice').append('<option value="'+item.name+'">'+item.name+'</option>');
				})
			})
		});
		$("#btnSearch").click(function() {
			$('#tbContent').bootstrapTable('refresh',{pageNumber:1});
		});
		$("#btnExport").click(function() {
			
			var tmpoption = $('#tbContent').bootstrapTable('getOptions');	
			var params = {};
			params = tmpoption.queryParams(params);
			params.sort = tmpoption.sortName;
			params.order = tmpoption.sortOrder;
			window.location.href="${ctx}/alg/equip/export?"+$.param(params);
		});
		$('#btnAdd').click(function () {
			layer.open({
				title:' ',
				type:2,
				area:'500px',
				content:'${ctx}/alg/equip/add',
				success : function(layero, index) {
					layer.iframeAuto(index);
				}
			});
		});
		$(document).on('click','.btnEdit',function(){
			layer.open({
				title:' ',
				type:2,
				area:'500px',
				content:'${ctx}/alg/equip/edit?id='+$(this).data('id'),
				success : function(layero, index) {
					layer.iframeAuto(index);
				}
			});
		}).on('click','.btnRemove',function(){
			var id = $(this).data('id');
			layer.confirm(
					'<spring:message code="msg_confirm_delete"/>?',
					{title:' ',
				btn:['<spring:message code="ok"/>','<spring:message code="cancel"/>']},
				function(){
				$.get('${ctx}/alg/equip/delete?id='+id,function(data){
					layer.msg(data.text);
					if (data.code=='success') {
						$('#tbContent').bootstrapTable(('refresh'));
					}
				},'json');
			});
		});
	});
	
	//初始化bootstrap-table的内容
	function InitMainTable() {
		console.log('123')
		//记录页面bootstrap-table全局变量$table，方便应用
		var queryUrl = '${ctx}/alg/equip/listdata';
		var options = {
				cardView : IsMobile(),
				url : queryUrl, //请求后台的URL（*）
				queryParams : function(params) {
					params.code = $.trim($('#txtCode').val());
					params.barcode = $.trim($('#txtBarcode').val());
					params.equip_type_name = $.trim($('#txtType').val());
					params.office_name = $.trim($('#txtOffice').val());
					params.region_name = $.trim($('#txtRegion').find('option:selected').text());
					params.extend5 = 'all';
					return params;
				},
				columns : [
				           {
				        	sortable:true,
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
							field : 'office_name',
							width:300,
						},  {sortable:true,
				            title : '<spring:message code="region"/>',
							field : 'region_name'
						}, {sortable:true,
				            title : '<spring:message code="coordinate"/>',
							field : 'posx',
							formatter:function(value,row,index){
								return row.posx+','+row.posy
							}
						}, {sortable:true,
				            title : '<spring:message code="monitor_region"/>',
							field : 'pos_region'
						}, {sortable:true,
				            title : '<spring:message code="purchase_date"/>',
							field : 'extend1'
						}, {sortable:true,
				            title : '<spring:message code="manufacturer"/>',
							field : 'extend2'
						}, {sortable:true,
				            title : '<spring:message code="maintenance_period"/>',
							field : 'extend3'
						}, {sortable:true,
				            title : '<spring:message code="certificate_number"/>',
							field : 'extend4'
						}, {sortable:true,
				            title : '<spring:message code="category"/>',
							field : 'extend6'
						}, {sortable:true,
				            title : '<spring:message code="specifications"/>',
							field : 'extend7'
						}, {sortable:true,
				            title : '<spring:message code="notes"/>',
							field : 'extend8'
						}, {
							align : 'center',
							valign : 'middle',
							field : 'image',
							title : '<spring:message code="picture"/>',
							formatter : imgFormatter
						}
						, {sortable:true,
				            title : '<spring:message code="state"/>',
							field : 'extend5',
							formatter:function(value,row,index){
								if (row.extend5=='1') {
									return '<spring:message code="in_use"/>';
								} else if (row.extend5=='0') {
									return '<spring:message code="abandoned"/>';
								}
							}
						}, {
							title : '<spring:message code="operator"/>',
							width:'100px',
							formatter : actionFormatter
						} ]
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

	function actionFormatter(value, row, index) {
		var result = '<a class="btnEdit" style="cursor:pointer" title="<spring:message code="edit" />" data-id="'+row.id+'"><spring:message code="edit" /></a>'
		+' <a class="btnRemove" style="cursor:pointer" title="<spring:message code="delete" />" data-id="'+row.id+'"><spring:message code="delete" /></a>'
		return result;
	}
	
	function imgFormatter(value, row, index) {
		if (row.image==undefined || $.trim(row.image)=='') {
			result = '<img  width="20" height="20" src="${ctxStatic}/img/noimg.png"></img>';
		}else{
			result = '<a href="javascript:bigimgfunc(\'${ctxStatic}/../upload/'+row.image+'\')"><img  width="20" height="20" class="photoimg" src="${ctxStatic}/../upload/thumb/'+row.image+'"></img></a>';
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
					<span class="input-group-addon"><spring:message code="sscode"></spring:message></span> <input
						id="txtCode" type="text" class="form-control">
				</div>
				<div class="input-group">
					<span class="input-group-addon"><spring:message code="barcode" /></span> <input
						id="txtBarcode" type="text" class="form-control">
				</div>
				<div class="input-group">
					<span class="input-group-addon"><spring:message code="equip_type" /></span> 
					<select id="txtType"  class="form-control">
					<option value=""></option>
					<c:forEach items="${equiptype }" var="item">
						<option value="${item.name }">${item.name }</option>
					</c:forEach>
					</select>
				</div>
				<div class="input-group">
					<span class="input-group-addon"><spring:message code="region" /></span> 
					<select id="txtRegion"  class="form-control">
					<option value=""></option>
					<c:forEach items="${equipregion }" var="item">
						<option value="${item.id }">${item.name }</option>
					</c:forEach>
					</select>
				</div>
				<div class="input-group">
					<span class="input-group-addon"><spring:message code="position" /></span> 
					<select id="txtOffice"  class="form-control" style="width: 200px;">
					<option value=""></option>
					<%-- <c:forEach items="${equipoffice }" var="item">
						<option value="${item.name }">${item.name }</option>
					</c:forEach> --%>
					</select>
				</div>
				<button id="btnSearch" class="btn btn-default">
				<span class="glyphicon glyphicon-search"></span> <spring:message code="search"/></button>
				
				<button id="btnAdd" class="btn btn-default">
				<span class="glyphicon glyphicon-plus"></span> <spring:message code="add"/></button>
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

</body>
</html>