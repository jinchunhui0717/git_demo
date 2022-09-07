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
		
	});
	
	//初始化bootstrap-table的内容
	function InitMainTable() {
		//记录页面bootstrap-table全局变量$table，方便应用
		var queryUrl = '${ctx}/alg/task/taskdetailinspectdata';
		var options = {
				ajaxOptions:{async:false},
				cardView : IsMobile(),
				url : queryUrl, //请求后台的URL（*）
				toolbarAlign : 'right', // 工具栏对齐方式
				dataType : "json", // 数据类型
				method : 'GET', //请求方式（*）
				cache : false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
				pagination : true, //是否显示分页（*）
				sidePagination : "server", //分页方式：client客户端分页，server服务端分页（*）
				pageNumber : 1, //初始化加载第一页，默认第一页,并记录
				pageSize : 1000,
				pageList : [ 10, 25, 50, 100 ],
				//得到查询的参数-提交到服务器端的参数
				queryParams : function(params) {
					//这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
					var temp = {
						limit : params.limit, //页面大小
						offset : params.offset,
						taskDetailId:'${id}'
					//页码
					};
					return temp;
				},
				columns : [
				           {
				            title : '<spring:message code="check_item"/>',
							field : 'name'
						},{
				            title : '<spring:message code="result"/>',
							field : 'value'
						}],

				responseHandler : function(res) {
					var hege;var hegeflag = false;
					var beizhu;var beizhuflag = false;
					if ('<%=response.getLocale().getLanguage()%>'=='zh') {
						for (var i = 0; i < res.rows.length; i++) {
							if(res.rows[i].value=='true')
								res.rows[i].value = "是";
							else if(res.rows[i].value=='false')
								res.rows[i].value = "否";
						}
					}
					var rows = [];
					for (var i = 0; i < res.rows.length; i++) {
						if (res.rows[i].name=='是否合格') {
							hege=res.rows[i];
							hegeflag = true;
						}else if (res.rows[i].name=='备注') {
							beizhu=res.rows[i];
							beizhuflag = true;
						}
						else {
							rows.push(res.rows[i]);
						}
					}
					if (hegeflag) {
						rows.push(hege);
					}
					if (beizhuflag) {
						rows.push(beizhu);
					}
					
					return {
						total : res.total,
						rows :rows
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
</script>
</head>
<body>
	<div class="box">
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