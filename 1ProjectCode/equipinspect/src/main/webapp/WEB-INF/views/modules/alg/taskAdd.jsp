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
<script type="text/javascript">
	//初始化bootstrap-table的内容
	function InitMainTable() {
		//记录页面bootstrap-table全局变量$table，方便应用
		var queryUrl = '${ctx}/alg/task/adddata';
		var options = {
				sidePagination : "client", //分页方式：client客户端分页，server服务端分页（*）
				url : queryUrl, //请求后台的URL（*）
				cardView : IsMobile(),
				striped : true,
				undefinedText : "",//字段是空的时候 默认显示 "-"  ,现在修改为""
				silent : true,
				dataType : "json", // 数据类型
				method : 'GET', //请求方式（*）
				cache : false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
				pagination : true, //是否显示分页（*）
				sortable : true,
				sortStable : true,
				pageNumber : 1, //初始化加载第一页，默认第一页,并记录
				pageSize : 1000,
				pageList : [ 1000 ],
				//得到查询的参数-提交到服务器端的参数
				/* queryParams : function(params) {
					//这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
					var temp = {
						pagesize : params.limit, //页面大小
						pageindex : (params.offset / params.limit) + 1
					//页码
					};
					return temp;
				}, */
				uniqueId : "id",
				columns : [
				           {
				        	   field:'ckstatus',
				        	   checkbox:true
				           },
						{sortable : true,
							field : 'code',
							title : '<spring:message code="position_no"/>',
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
							field : 'equip_operator',
							title : '<spring:message code="operator_people"/>',
							align : 'center',
							valign : 'middle',
							//formatter : actionFormatter
						}, {
							field : 'startdate',
							title : '<spring:message code="begin_date"/>',
							align : 'center',
							valign : 'middle'
						}, {
							field : 'enddate',
							title : '<spring:message code="end_date"/>',
							align : 'center',
							valign : 'middle'
						} ],

				responseHandler : function(res) {
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
				item.user_name = name;
			}
		})
		$('#tbContent').data('bootstrap.table').initBody(true);
	}

	$(function() {
		InitMainTable();
		$("#btnSearch").click(
				function() {
					var searchtext={};
					if ($('#txtregion_name').val()!='') {
						searchtext.region_name=$('#txtregion_name').val();
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
						searchtext.equip_operator=$('#txtoperator').val();
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
			layer.alert('<form id="alertform">'+
					'<div class="form-group">'+
					'                <label for="txtstartdate" class="control-label"><spring:message code="begin_date"/></label>'+
					'                <div class="input-group date form_date1" data-date="" data-date-format="yyyy-mm-dd" data-link-field="txtstartdate" data-link-format="yyyy-mm-dd">'+
					'                    <input name="qmstartdate" class="form-control" size="16" type="text" value="" readonly>'+
					'                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>'+
					'					<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>'+
					'                </div>'+
					'				<input name="startdate" id="txtstartdate" type="hidden" value="" /><br/>'+
					'            </div>'+
					'<div class="form-group">'+
					'                <label for="txtenddate" class=" control-label"><spring:message code="end_date"/></label>'+
					'                <div class="input-group date form_date2 " data-date="" data-date-format="yyyy-mm-dd" data-link-field="txtenddate" data-link-format="yyyy-mm-dd">'+
					'                    <input name="qmenddate" class="form-control" size="16" type="text" value="" readonly>'+
					'                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>'+
					'					<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>'+
					'                </div>'+
					'				<input type="hidden" id="txtenddate" name="enddate" value="" /><br/>'+
					'            </div>'+
					'</form>',
					{title:' ',success:function(){
						$('.form_date1').datetimepicker({
					        language:  'zh-CN',
					        weekStart: 1,
					        todayBtn:  1,
							autoclose: 1,
							todayHighlight: 1,
							startView: 2,
							minView: 2,
							forceParse: 0
					    }).on('hide',function(e) {  
			                $('#alertform').data('bootstrapValidator')  
		                    .updateStatus('qmstartdate', 'NOT_VALIDATED')  
		                    .validateField('qmstartdate');  
		            });
						$('.form_date2').datetimepicker({
					        language:  'zh-CN',
					        weekStart: 1,
					        todayBtn:  1,
							autoclose: 1,
							todayHighlight: 1,
							startView: 2,
							minView: 2,
							forceParse: 0
					    }).on('hide',function(e) {  
			                $('#alertform').data('bootstrapValidator')  
		                    .updateStatus('qmenddate', 'NOT_VALIDATED')  
		                    .validateField('qmenddate');  
		            });
						$('#alertform').bootstrapValidator({fields:{
							qmstartdate: {
								validators: {
									notEmpty: {
										message: '<spring:message code="msg_not_null" />'
									}
								}
							},
							qmenddate: {
								validators: {
									notEmpty: {
										message: '<spring:message code="msg_not_null" />'
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
							 console.log($('#txtstartdate').val())
							 $('#tbContent').data('bootstrap.table').data.forEach(function(item) {
									if(item.ckstatus==true){
										item.startdate = $('#txtstartdate').val();
										item.enddate = $('#txtenddate').val();
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
							url : '${ctx}/alg/task/add',
							data : JSON.stringify({data:$("#tbContent").bootstrapTable(
									'getData', false)}),
							contentType : 'application/json;charset=utf8',
							dataType : 'json',
							success : function(data) {
								layer.msg(data.text);
								
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
					<span class="input-group-addon"><spring:message code="position_no"/></span> <input
						id="txtcode" type="text" class="form-control">
				</div>
				<div class="input-group">
					<span class="input-group-addon"><spring:message code="barcode"/></span> <input
						id="txtbarcode" type="text" class="form-control">
				</div>
				<div class="input-group">
					<span class="input-group-addon"><spring:message code="operator_people"/></span> <input
						id="txtoperator" type="text" class="form-control">
				</div>
				<div class="input-group">
					<span class="input-group-addon"><spring:message code="region"/></span> <input
						id="txtregion_name" type="text" class="form-control">
				</div>
				<div class="input-group">
					<span class="input-group-addon"><spring:message code="position"/></span> <input
						id="txtoffice_name" type="text" class="form-control">
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
	<script
		src="${ctxStatic}/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
		<script
		src="${ctxStatic}/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
</body>
</html>