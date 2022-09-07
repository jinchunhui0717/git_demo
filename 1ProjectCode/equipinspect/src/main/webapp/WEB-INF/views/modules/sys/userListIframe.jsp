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
	//初始化bootstrap-table的内容
	function InitMainTable() {
		//记录页面bootstrap-table全局变量$table，方便应用
		var queryUrl = '${ctx}/sys/user/listdata';
		var options = {
				ajaxOptions:{async:false},
				url : queryUrl, //请求后台的URL（*）
				queryParams : function(params) {
					return params;
				},
				columns : [
						 {
							field : 'login_name',
							title : '<spring:message code="account"/>',
							align : 'center'
						}, {
							field : 'name',
							title : '<spring:message code="name"/>',
							align : 'center'
						},{
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
		var result = "<a class='editable editable-click editable-empty' href='javascript:void(0)' onclick=\"closeIframe('"
			+ row.login_name
			+ "','"+row.name+"')\"><spring:message code='choose'/></a>";
		return result;
	}

	function closeIframe(login_name,name){
		parent.beforeCloseIframe(login_name,name);
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引  
		parent.layer.close(index);//关闭弹出的子页面窗口 
	}

	$(function() {
		InitMainTable();
		$("#btnSearch").click(function() {
			$('#tbContent').bootstrapTable(('refresh'));
		});
	});
</script>
</head>
<body>
	<div class="box box-body">
			<table id="tbContent"></table>
		</div>
	
	<script
		src="${ctxStatic}/plugins/bootstrap-table/js/bootstrap-table.min.js"></script>
	<script
		src="${ctxStatic}/plugins/bootstrap-table/js/bootstrap-table-zh-CN.min.js"></script>
		
</body>
</html>