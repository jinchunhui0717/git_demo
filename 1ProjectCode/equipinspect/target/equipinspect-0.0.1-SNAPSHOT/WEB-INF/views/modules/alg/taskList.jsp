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
		InitMainTable();
		$('#datetimepicker').datetimepicker({
	        language:  'zh-CN',
	        weekStart: 1,
	        todayBtn:  1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 4,
			minView: 4,
			forceParse: 0
	    })
		$("#btnSearch").click(function() {
			$('#tbContent').bootstrapTable(('refresh'));
		});
	});
	
	//初始化bootstrap-table的内容
	function InitMainTable() {
		//记录页面bootstrap-table全局变量$table，方便应用
		var queryUrl = '${ctx}/alg/task/tasklistdata';
		var options = {
				
				cardView : IsMobile(),
				
				url : queryUrl, //请求后台的URL（*）
				
				//得到查询的参数-提交到服务器端的参数
				queryParams : function(params) {
					params.createdate = $.trim($('#datetimepicker').val());
					return params;
				},
				columns : [
						{
							field : 'number',
							title : '<spring:message code="no"/>',
							width : 5,
							align : 'center',
							switchable : false,
							formatter : function(value, row, index) {
								//return index+1; //序号正序排序从1开始
								var pageSize = $('#tbContent')
										.bootstrapTable(
												'getOptions').pageSize;//通过表的#id 可以得到每页多少条
								var pageNumber = $('#tbContent')
										.bootstrapTable(
												'getOptions').pageNumber;//通过表的#id 可以得到当前第几页
								return pageSize * (pageNumber - 1)
										+ index + 1; //返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
							}
						}, {
							field : 'createDate',
							title : '任务周期',
							align : 'center',
							formatter : function(value, row, index) {
								return row.createDate.substring(0,7)
							}
						}, {
							title : '<spring:message code="operator"/>',
							formatter : actionFormatter,
							align : 'center'
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

	//操作栏的格式化
	function actionFormatter(value, row, index) {
		var result = "<a   style='cursor:pointer' href='${ctx}/alg/task/taskdetaillist?id="
				+ row.id
				+ "'><spring:message code='detail'/></a>";
		return result;
	}
</script>
</head>
<body>
	<div class="box">
		<div class="box-header with-border">
			<div class="form-inline" style="text-align: right;">
			<div class="input-group">
						<span class="input-group-addon"><spring:message code="task_cycle"></spring:message></span> 
						<input class="form-control"  type="text" id="datetimepicker" data-date-format="yyyy">
								
					</div>
				<button id="btnSearch" class="btn btn-default">
				<span class="glyphicon glyphicon-search"></span> <spring:message code="search"/></button>
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