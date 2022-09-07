<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜单设置</title>
<link rel="stylesheet" type="text/css"
	href="${ctxStatic }/plugins/ztree/css/bootstrapStyle/bootstrapStyle.css">
<script type="text/javascript"
	src="${ctxStatic }/plugins/ztree/js/jquery.ztree.core.js"></script>
<script type="text/javascript"
	src="${ctxStatic }/plugins/ztree/js/jquery.ztree.excheck.js"></script>
<script type="text/javascript"
	src="${ctxStatic }/plugins/ztree/js/jquery.ztree.exedit.js"></script>
<script>
	$(document).ready(function() {
		$.fn.zTree.init($("#treeDemo"), asysetting);
	});
	var asysetting = {
		view : {
			showLine : true,
			//zTree 不提供默认的增加按钮，如要实现需要利用自定义控件的方法 addHoverDom / removeHoverDom
			addHoverDom : addHoverDom, //当鼠标移动到节点上时，显示用户自定义控件  
			removeHoverDom : removeHoverDom, //离开节点时的操作  
		},
		check : {
			enable : false,
			chkStyle : "checkbox", //复选框
			chkboxType : {
				"Y" : "s",
				"N" : "ps"
			}
		},
		edit : {
			enable : true, //单独设置为true时，可加载修改、删除图标  
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
			simpleData : {
				enable : true,//如果使用简单节点，需要开启这个
				idKey : "id",
				pIdKey : "parentId",
				rootPId : ""
			}
		},
		callback : {
			beforeRemove : zTreeBeforeRemove,
			beforeEditName : zTreeBeforeEditName
		}
	};
	function addHoverDom(treeId, treeNode) {
		var sObj = $("#" + treeNode.tId + "_span"); //获取节点信息  
		if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0)
			return;

		var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
				+ "' title='新增下级节点' onfocus='this.blur();'></span>"; //定义添加按钮  
		sObj.after(addStr); //加载添加按钮  
		var btn = $("#addBtn_" + treeNode.tId);

		//绑定添加事件，并定义添加操作  
		if (btn)
			btn.bind("click", function() {
				//将新节点添加到数据库中  
				//初始化文本框
				$('#inputMenuName').val('');
				$('#inputMenuHref').val('');
				layer.open({
					type : 1,
					content : $('#div-layer-none'),
					btn : [ '确定' ],
					yes : function(index, layero) {
						var name = $('#inputMenuName').val();
						var href = $('#inputMenuHref').val();
						var zTree = $.fn.zTree.getZTreeObj("treeDemo");
						$.ajaxSetup({
							contentType : 'application/json;charset:utf8'
						});
						$.post('${ctx}/sys/menu/adddata', JSON.stringify({
							parentId : treeNode.id,
							name : name,
							isShow : 1,
							href : href,
						}), function(data) {
							layer.msg(data.text);
							if (data.code == 'success') {
								var newID = data.extend; //获取新添加的节点Id  
								zTree.addNodes(treeNode, {
									id : newID,
									parentId : treeNode.id,
									name : name
								}); //页面上添加节点  
								var node = zTree.getNodeByParam("id", newID); //根据新的id找到新添加的节点  
								zTree.selectNode(node); //让新添加的节点处于选中状态  
								layer.close(index);
							}
							
						}, 'json');
					}
				});

			});
	}
	function removeHoverDom(treeId, treeNode) {
		$("#addBtn_" + treeNode.tId).unbind().remove();
	}
	function zTreeBeforeRemove(treeId, treeNode) {
		layer.confirm('将会删除本菜单及其子菜单,是否删除?', function(index) {
			$.ajax({
				url : '${ctx}/sys/menu/delete?id=' + treeNode.id,
				type:'GET',
				async:false,
				success : function(data) {
					layer.msg(data.text);
					if (data.code == 'success') {
						var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
						treeObj.removeNode(treeNode,false);
						layer.close(index);
					} 

				},
				dataType : 'json'
			});

		});
		return false;
	}
	function zTreeBeforeEditName(treeId, treeNode) {
		//初始化文本框
		$('#inputMenuName').val(treeNode.name);
		$('#inputMenuHref').val(treeNode.href);
		layer.open({
			type : 1,
			content : $('#div-layer-none'),
			btn : [ '确定' ],
			yes : function(index, layero) {
				var name = $('#inputMenuName').val();
				var href = $('#inputMenuHref').val();
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				$.ajaxSetup({
					contentType : 'application/json;charset:utf8'
				});
				$.post('${ctx}/sys/menu/update', JSON.stringify({
					id : treeNode.id,
					name : name,
					href : href,
				}), function(data) {
					layer.msg(data.text);
					if (data.code == 'success') {
						treeNode.name = name;
						treeNode.href = href;
						zTree.updateNode(treeNode);
						layer.close(index);
					}
				}, 'json');
			}
		});

		return false;
	}
</script>
</head>
<body>
	<div class="box">
		<div class="box-body">
			<ul id="treeDemo" class="ztree"></ul>
		</div>
	</div>
	<div id="div-layer-none" style="display: none" class="box-body">
		<div class="form-group">
			<label for="inputMenuName">菜单名称</label> <input type="text"
				class="form-control" id="inputMenuName">
		</div>
		<div class="form-group">
			<label for="inputMenuHref">菜单地址</label> <input type="text"
				class="form-control" id="inputMenuHref">
		</div>
	</div>
</body>

</html>