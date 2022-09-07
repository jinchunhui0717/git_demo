<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>任务修改</title>
<link rel="stylesheet"
	href="${ctxStatic}/plugins/bootstrap-table/css/bootstrap-table.min.css">
<script>
	//初始化bootstrap-table的内容
	function InitMainTable() {
		//记录页面bootstrap-table全局变量$table，方便应用
		var queryUrl = '${ctx}/alg/task/adddata2';
		var options = {
				sidePagination : "client", //分页方式：client客户端分页，server服务端分页（*）
				url : queryUrl, //请求后台的URL（*）
				cardView : IsMobile(),
				pagination : false, //是否显示分页（*）
				columns : [
				           {
				        	   field:'ckstatus',
				        	   checkbox:true
				           },
						{sortable : true,
							field : 'code',
							title : '<spring:message code="sscode"/>',
							align : 'center'
						}, {sortable : true,
							field : 'barcode',
							title : '<spring:message code="barcode"/>',
							align : 'center'
						}, {
							field : 'region_name',
							title : '<spring:message code="region"/>',
							align : 'center',
							sortable : true
						}, {
							field : 'office_name',
							title : '<spring:message code="position"/>',
							align : 'center',
							sortable : true
						}, {sortable : true,
							field : 'equip_user_name',
							title : '<spring:message code="operator_people"/>',
							align : 'center',
							valign : 'middle',
							//formatter : actionFormatter
						}, {
							field : 'equip_opt_startdate',
							title : '<spring:message code="begin_date"/>',
							align : 'center',
							valign : 'middle',
							formatter:function(value,row,index){
								return '<spring:message code="permonth"/>'+row.equip_opt_startdate+'<spring:message code="perday"/>';
							}
						}, {
							field : 'equip_opt_enddate',
							title : '<spring:message code="end_date"/>',
							align : 'center',
							valign : 'middle',
							formatter:function(value,row,index){
								return '<spring:message code="permonth"/>'+row.equip_opt_enddate+'<spring:message code="perday"/>';
							}
						} ],

				responseHandler : function(res) {
					console.log(res.rows)
					return res.rows;
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

	
	function openIframe() {
		layer.open({
			title:' ',
			type : 2,
			content : '${ctx}/sys/user/listiframe',
			success : function(layero, index) {
				layer.iframeAuto(index);
			}
		});
	}

	function beforeCloseIframe(loginName,name) {
		//批量更新
		$('#tbContent').data('bootstrap.table').data.forEach(function(item) {
			if(item.ckstatus==true){
				item.equip_operator = loginName;
				item.equip_user_name = name;
			}
		})
		$('#tbContent').data('bootstrap.table').initBody(true);
	}

	$(function() {
		InitMainTable();
		
		$('#txtregion_name').change(function() {
			$.get('${ctx}/alg/equip/getofficebyregion',{regionid:$('#txtregion_name').val()},function(data){
				$('#txtoffice_name').empty();
				$('#txtoffice_name').append('<option value=""></option>');
				data.forEach(function(item) {
					$('#txtoffice_name').append('<option value="'+item.name+'">'+item.name+'</option>');
				})
			})
		});
		
		$("#btnSearch").click(
				function() {
					var searchtext={};
					if ($('#txtregion_name').val()!='') {
						searchtext.region_name=$('#txtregion_name').find('option:selected').text();
					}
					if ($('#txtoffice_name').val()!='') {
						searchtext.office_name=$('#txtoffice_name').val();
					}
					if ($('#txtcode').val()!='') {
						searchtext.code=$('#txtcode').val();
					}
					if ($('#txtbarcode').val()!='') {
						searchtext.barcode=$('#txtbarcode').val();
					}
					if ($('#txtoperator').val()!='') {
						searchtext.user_name=$('#txtoperator').val();
					}
					$('#tbContent').bootstrapTable('filterBy',searchtext);
				});
		$('#btnEdit').click(function() {
			var rows = $('#tbContent').bootstrapTable('getSelections');
			if (rows.length==0) {
				layer.msg('<spring:message code="msg_nochoose"/>');return false;
			}
			openIframe();
		});
		$('#btnEditDate').click(function() {
			var rows = $('#tbContent').bootstrapTable('getSelections');
			if (rows.length==0) {
				layer.msg('<spring:message code="msg_nochoose"/>');return false;
			}
			layer.alert('<form id="alertform"><div class="form-group"><spring:message code="begin_date"/> <spring:message code="permonth"/>X<spring:message code="perday"/><input name="startdate" id="txtstartdate" type="text" class="form-control"></div>'+
					'<div class="form-group"><spring:message code="end_date"/> <spring:message code="permonth"/>X<spring:message code="perday"/><input id="txtenddate" name="enddate" type="text" class="form-control"></div></form>',
					{title:' ',success:function(){
						$('#alertform').bootstrapValidator({fields:{
							startdate: {
								validators: {
									notEmpty: {
										message: '<spring:message code="msg_not_null" />'
									},
									digits: {
										message: '<spring:message code="msg_digit" />'
									}
								}
							},
							enddate: {
								validators: {
									notEmpty: {
										message: '<spring:message code="msg_not_null" />'
									},
									digits: {
										message: '<spring:message code="msg_digit" />'
									}
								}
							}
						}});
					}},
					function(index){
						//定义一个校验器
						 var bootstrapValidator = $("#alertform").data('bootstrapValidator');
						 //执行校验
						 bootstrapValidator.validate();
						 //判断校验结果
						 if(bootstrapValidator.isValid()){
							 $('#tbContent').data('bootstrap.table').data.forEach(function(item) {
									if(item.ckstatus==true){
										item.equip_opt_startdate = $('#txtstartdate').val();
										item.equip_opt_enddate = $('#txtenddate').val();
									}
								})
								$('#tbContent').data('bootstrap.table').initBody(true);
								layer.close(index);
						 }
						
						
					}
			);
		});
		
		$("#btnCommit").click(
				
				function() {
					layer.confirm('<spring:message code="msg_confirm_commit"/>?',
							{title:' ',
						btn:['<spring:message code="ok"/>','<spring:message code="cancel"/>']},
						function(index){
						$.ajax({
							type : 'post',
							url : '${ctx}/alg/task/add2',
							data : JSON.stringify({data:$("#tbContent").bootstrapTable(
									'getData', false)}),
							contentType : 'application/json;charset=utf8',
							dataType : 'json',
							success : function(data) {
								layer.msg(data.text);
								if (data.code=='success') {
									setTimeout(function() {
										layer.close(index);
									}, 300);
								}
							}
						});
						
					});
					
				});
	});
</script>
</head>
<body>
	<div class="box">
		<div class="box-header with-border">
			<div class="form-inline" style="text-align: right;">
				<div class="input-group">
					<span class="input-group-addon"><spring:message code="sscode"/></span> <input
						id="txtcode" type="text" class="form-control">
				</div>
				<div class="input-group">
					<span class="input-group-addon"><spring:message code="barcode"/></span> <input
						id="txtbarcode" type="text" class="form-control">
				</div>
				<div class="input-group">
					<span class="input-group-addon"><spring:message code="operator_people"/></span>
					 <!-- <input
						id="txtoperator" type="text" class="form-control"> -->
						<select id="txtoperator" class="form-control">
							<option></option>
							<c:forEach items="${role }" var="item">
								<option value="${item.name }">${item.name }</option>
							</c:forEach>
						</select>
				</div>
				<div class="input-group">
					<span class="input-group-addon"><spring:message code="region"/></span> <!-- <input
						id="txtregion_name" type="text" class="form-control"> -->
						<select id="txtregion_name" class="form-control">
							<option></option>
							<c:forEach items="${region }" var="item">
								<option value="${item.id }">${item.name }</option>
							</c:forEach>
						</select>
				</div>
				<div class="input-group">
					<span class="input-group-addon"><spring:message code="position"/></span><!--  <input
						id="txtoffice_name" type="text" class="form-control"> -->
						<select id="txtoffice_name" class="form-control" style="width: 200px;">
							<option></option>
							
						</select>
				</div>
				<button id="btnSearch" type="button" class="btn btn-default">
					<span class="glyphicon glyphicon-search" ></span> <spring:message code="search"/>
				</button>
				<button id="btnEdit" type="button" class="btn btn-default">
					<span class="glyphicon glyphicon-edit"></span> <spring:message code="batch_operator"/>
				</button>
				<button id="btnEditDate" type="button" class="btn btn-default">
					<span class="glyphicon glyphicon-edit"></span> <spring:message code="barch_update_date"/>
				</button>
				<button id="btnCommit" type="button" class="btn btn-default">
					<span class="glyphicon glyphicon-open"></span> <spring:message code="commit"/>
				</button>
			</div>
		</div>
		<div class="box-body">
			<table id="tbContent"></table>
		</div>
	</div>
	<script
		src="${ctxStatic}/plugins/bootstrap-table/js/bootstrap-table.js"></script>
	<script
		src="${ctxStatic}/plugins/bootstrap-table/js/bootstrap-table-zh-CN.min.js"></script>
	<script src="${ctxStatic}/util/DateUtil.js"></script>
</body>
</html>