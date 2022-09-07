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
			$('#tbContent').bootstrapTable('refresh',{pageNumber:1});
		});
		$('#btnAdd').click(function () {
			layer.open({
				title:' ',
				type:2,
				content:'${ctx}/sys/user/add',
				success : function(layero, index) {
					layer.iframeAuto(index);
				}
			});
		});
		$(document).on('click','.btnEdit',function(){
			layer.open({
				title:' ',
				type:2,
				content:'${ctx}/sys/user/edit?id='+$(this).data('id'),
				success : function(layero, index) {
					layer.iframeAuto(index);
				}
			});
		}).on('click','.btnRemove',function(){
			var id = $(this).data('id');
			layer.confirm('<spring:message code="msg_confirm_delete"/>?',
					{title:' ',
				btn:['<spring:message code="ok"/>','<spring:message code="cancel"/>']},
					function(){
						$.get('${ctx}/sys/user/delete?id='+id,function(data){
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
		//记录页面bootstrap-table全局变量$table，方便应用
		var queryUrl = '${ctx}/sys/user/listdata';
		var options = {
				cardView : IsMobile(),
				url : queryUrl, //请求后台的URL（*）
				queryParams : function(params) {
					params.login_name = $.trim($('#txtLoginName').val());
					params.name = $.trim($('#txtName').val());
					params.role_name = $.trim($('#txtRole').val());
					return params;
				},
				columns : [
						 {
							 sortable:true,
							field : 'login_name',
							title : '<spring:message code="account"/>',
							align : 'center'
						}, {sortable:true,
							field : 'name',
							title : '<spring:message code="name"/>',
							align : 'center'
						}, {sortable:true,
							field : 'role_name',
							title : '<spring:message code="role"/>',
							align : 'center'
						}, {sortable:true,
							field : 'login_locked',
							title : '<spring:message code="state"/>',
							align : 'center',
							formatter:function(value,row,index){
									if (row.login_locked=='1') {
										return '<span class="label label-danger"><spring:message code="disable"/></span>';
									} else if (row.login_locked=='0') {
										return '<span class="label label-success"><spring:message code="enable"/></span>';
									}
								}
						}, {
							title : '<spring:message code="operator"/>',
							width:'100px',
							formatter : actionFormatter,
							align : 'center'
						} ]
			}
		if ('<%=response.getLocale().getLanguage()%>'=='zh') {
			options.locale = 'zh-CN';
		}else if ('<%=response.getLocale().getLanguage()%>'=='en') {
			options.locale = 'en-US';
		}
		$('#tbContent')
				.bootstrapTable(options
						);
	}

	//操作栏的格式化
	function actionFormatter(value, row, index) {
		var result = '<a class="btnEdit" style="cursor:pointer" title="<spring:message code="edit" />" data-id="'+row.id+'"><spring:message code="edit" /></a>'
		+' <a class="btnRemove" style="cursor:pointer" title="<spring:message code="delete" />" data-id="'+row.id+'"><spring:message code="delete" /></a>'
		return result;
	}
</script>
</head>
<body>
 
	<div class="box">
		<div class="box-header with-border">
			<div class="form-inline" style="text-align: right;">
				<div class="input-group">
					<span class="input-group-addon"><spring:message code="account"></spring:message></span> <input
						id="txtLoginName" type="text" class="form-control">
				</div>
				<div class="input-group">
					<span class="input-group-addon"><spring:message code="name" /></span> <input
						id="txtName" type="text" class="form-control">
				</div>
				<div class="input-group">
					<span class="input-group-addon"><spring:message code="role" /></span> 
					<select id="txtRole" class="form-control">
						<option value=""></option>
						<c:forEach items="${sysrole}" var="item">
							<option value="${item.name }">${item.name }</option>
						</c:forEach>
					</select>
				</div>
				<button id="btnSearch" class="btn btn-default" >
				<span class="glyphicon glyphicon-search" ></span> <spring:message code="search" /></button>
				
				<button id="btnAdd" class="btn btn-default">
				<span class="glyphicon glyphicon-plus"></span> <spring:message code="add" /></button>
				
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