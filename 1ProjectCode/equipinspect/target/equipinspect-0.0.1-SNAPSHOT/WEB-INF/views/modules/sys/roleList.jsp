<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet"
	href="${ctxStatic}/plugins/bootstrap-table/css/bootstrap-table.min.css">
<link rel="stylesheet" type="text/css"
	href="${ctxStatic }/plugins/ztree/css/bootstrapStyle/bootstrapStyle.css">
<script type="text/javascript"
	src="${ctxStatic }/plugins/ztree/js/jquery.ztree.core.js"></script>
<script type="text/javascript"
	src="${ctxStatic }/plugins/ztree/js/jquery.ztree.excheck.js"></script>
<script type="text/javascript"
	src="${ctxStatic }/plugins/ztree/js/jquery.ztree.exedit.js"></script>
<script>
	var curRoleId='';
	$(function() {
		InitMainTable();
		$("#btnSearch").click(function() {
			$('#tbContent').bootstrapTable('refresh',{pageNumber:1});
		});
		$('#btnAdd').click(function () {
			layer.open({
				title:' ',
				type:2,
				content:'${ctx}/sys/role/add',
				success : function(layero, index) {
					layer.iframeAuto(index);
				}
			});
		});
		$("#btnSave").click(
				function() {
					if (curRoleId=='') {
						layer.msg('<spring:message code="select_data"/>');
						return false;
					}
					layer.confirm('<spring:message code="msg_confirm_save"/>?',
							{title:' ',
						btn:['<spring:message code="ok"/>','<spring:message code="cancel"/>']},function(index){
						var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
						var nodes = treeObj.getCheckedNodes(true);
						var postdata=[];
						for (var i = 0; i < nodes.length; i++) {
							postdata.push({menuId:nodes[i].id});
						}
						$.ajax({
							type : 'post',
							url : '${ctx}/sys/role/menudata?roleid='+curRoleId,
							data : JSON.stringify(postdata),
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
		$(document).on('click','.btnEdit',function(){
			layer.open({
				title:' ',
				type:2,
				content:'${ctx}/sys/role/edit?id='+$(this).data('id'),
				success : function(layero, index) {
					layer.iframeAuto(index);
				}
			});
		}).on('click','.btnRemove',function(){
			var id = $(this).data('id');
			layer.confirm('<spring:message code="msg_confirm_delete"/>?',
					{title:' ',
				btn:['<spring:message code="ok"/>','<spring:message code="cancel"/>']}
			,function(){
				$.get('${ctx}/sys/role/delete?id='+id,function(data){
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
		var queryUrl = '${ctx}/sys/role/listdata';
		var options = {
				cardView : IsMobile(),
				url : queryUrl, //请求后台的URL（*）
				clickToSelect:true,
				singleSelect:true,
				columns : [
						{
							checkbox:true
						},
						{
							field : 'name',
							title : '<spring:message code="role"/>',
							align : 'center'
						}, {
							title : '<spring:message code="operator"/>',
							width:'100px',
							formatter : actionFormatter,
							align : 'center'
						} ],
				onCheck:function(row, $element){
					
					$('#lblCurRole').text('<spring:message code="msg_current_role"/>：'+row.name);
					$.get(
							'${ctx}/sys/role/menudata',{roleId:row.id},
							function(data){
								curRoleId = row.id;
								var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
								treeObj.checkAllNodes(false);
								
								//动态赋值选择状态，先关闭父子影响
								treeObj.setting.check.chkboxType={
										"Y" : "",
										"N" : ""
									};
								var nodes = treeObj.transformToArray(treeObj.getNodes());
								
								for (var i = 0; i < data.length; i++) {
									
									for (var j = 0; j < nodes.length; j++) {
										
										if (data[i].menuId==nodes[j].id) {
											treeObj.checkNode(nodes[j], true, true);
											break;
										}
										
									}
								}
								//动态赋值选择状态结束后，再开启父子影响，不影响好的用户体验
								treeObj.setting.check.chkboxType={
										"Y" : "ps",
										"N" : "ps"
									};
							});
				},
				
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
		var result = '<a class="btnEdit" style="cursor:pointer" title="<spring:message code="edit" />" data-id="'+row.id+'"><spring:message code="edit" /></a>'
		+' <a class="btnRemove" style="cursor:pointer" title="<spring:message code="delete" />" data-id="'+row.id+'"><spring:message code="delete" /></a>'
		return result;
	}
	
	
	$(function() {
		if ('<%=response.getLocale().getLanguage()%>'=='zh') {
			asysetting.data.key.name = 'name';
		}else if ('<%=response.getLocale().getLanguage()%>'=='en') {
			asysetting.data.key.name = 'ename';
		}
		$.fn.zTree.init($("#treeDemo"), asysetting);
		//此时扩展不起作用，因为数据节点还没有到,onAsyncSuccess中实现就行
		//var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		//treeObj.expandAll(true);
	});
	var asysetting = {
		view : {
			showLine : true,
			//zTree 不提供默认的增加按钮，如要实现需要利用自定义控件的方法 addHoverDom / removeHoverDom
			//addHoverDom : addHoverDom, //当鼠标移动到节点上时，显示用户自定义控件  
			//removeHoverDom : removeHoverDom, //离开节点时的操作  
		},
		check : {
			enable : true,
			chkStyle : "checkbox", //复选框
			chkboxType : {
				"Y" : "ps",
				"N" : "ps"
			}
		},
		edit : {
			enable : false, //单独设置为true时，可加载修改、删除图标  
			showRemoveBtn : true,
			removeTitle : "删除",
			showRenameBtn : true,
			renameTitle : "编辑",
		},
		async : {
			enable : true,
			url : "${ctx}/sys/menu/listdata",
			type : "post",
			autoParam : [ "id" ],
			contentType : "application/json;charset:utf8",
			dataType : "json",
		},
		data : {
			key:{name:"name"},
			simpleData : {
				enable : true,//如果使用简单节点，需要开启这个
				idKey : "id",
				pIdKey : "parentId",
				rootPId : ""
			}
		},
		callback : {
			/* beforeRemove : zTreeBeforeRemove,
			beforeEditName : zTreeBeforeEditName */
			onAsyncSuccess: onAsyncSuccess
		}
	};
	function onAsyncSuccess(event, treeId, treeNode, msg) {  
		//加载成功后再扩展
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		treeObj.expandAll(true);
    }  
</script>
</head>
<body>
	<div class="row">
		<div class="col-md-6">
			<div class="box">
				<div class="box-header with-border">
					<div class="form-inline" style="text-align: right;">
						<button id="btnSearch" class="btn btn-default">
						<span class="glyphicon glyphicon-search"></span> <spring:message code="search"/></button>
						<button id="btnAdd" class="btn btn-default">
						<span class="glyphicon glyphicon-plus"></span> <spring:message code="add"/></button>
						
					</div>
				</div>
				<div class="box-body">
					<table id="tbContent"></table>
				</div>

			</div>
		</div>
		<div class="col-md-6">
			<div class="box">
				<div class="box-header with-border">
					<div class="pull-left">
						<label id="lblCurRole"></label>
					</div>
					<div class="pull-right">
						<button id="btnSave" class="btn btn-default">
						<span class="fa fa-save"></span> <spring:message code="save"/></button>
					</div>
				</div>
				<div class="box-body">
					<ul id="treeDemo" class="ztree"></ul>
				</div>
			</div>			
		</div>
	</div>
	<script
		src="${ctxStatic}/plugins/bootstrap-table/js/bootstrap-table.min.js"></script>
	<script
		src="${ctxStatic}/plugins/bootstrap-table/js/bootstrap-table-zh-CN.min.js"></script>

</body>
</html>