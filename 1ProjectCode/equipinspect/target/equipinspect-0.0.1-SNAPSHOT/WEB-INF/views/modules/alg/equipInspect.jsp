<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html manifest="${ctxStatic}/menifest/rafael.manifest">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" href="${ctxStatic}/plugins/bootstrap-table/css/bootstrap-table.min.css">
<%
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort()
				+ path;
	%>
<script> 
$(function() {
	function initData() {
		if (IsMobile()) {
			document.addEventListener("deviceready", onDeviceReady, false)
		}
		var allEquipList = [];
		var idList = [];
		var newidList = [];
		localforage.iterate(function(value, key, iterationNumber) {	
				allEquipList.push(value);
				idList.push(value.id);
		}, function() {
			 $.ajax({
				url: '${ctx}/alg/equip/inspectdata',
				type: 'GET',
				success: function(data) {
					$.get('${ctx}/alg/equiptype/scannerdata?algequiptypeid=' + '' + '&taskdetailid=' + '',
							function(data) {
							var equipTypeStore = localforage.createInstance({
								name: "equipType"
							});
							equipTypeStore.clear(function() {
								data.forEach(function(item) {
									equipTypeStore.setItem(item.id, item)
								});
							})
						});
					data.rows.forEach(function(item) {
						if (item.equip_state==undefined || $.trim(item.equip_state)=='') {
							 item.equip_state = 'init';
						}
						if ($.inArray(item.id,idList)==-1) {
							//不存在的直接插入
							localforage.setItem(item.id, item);
							allEquipList.push(item);
						} else {
							//存在并且状态不一样，则更新为最新状态（未提交除外）
							localforage.removeItem(item.id,function(){
								localforage.setItem(item.id, item);
								for (var i = 0; i < allEquipList.length; i++) {
									if (item.id==allEquipList[i].id) {
										allEquipList.splice(i,1);
										break;
									}
								}
								allEquipList.push(item);
							});
						}
						var newinold = false;
						allEquipList.forEach(function(itemOld) {
							if (itemOld.id == item.id) {
								newinold = true;
								if (itemOld.equip_state != item.equip_state && itemOld.equip_state!='uncomitted') {
									localforage.removeItem(item.id,function(){
										localforage.setItem(item.id, item);
										allEquipList.push(item);
									});
								}
							}
						})		
						if (newinold==false) {
							
						}
						newidList.push(item.id);
					});
					 idList.forEach(function(item) {
							if ($.inArray(item, newidList) == -1) {
								localforage.removeItem(item);
								for (var i = 0; i < allEquipList.length; i++) {
									if (item==allEquipList[i].id) {
										allEquipList.splice(i,1);
										break;
									}
								}
							}
						});
					 InitMainTable(allEquipList);
					 
				} ,
				error: function (XMLHttpRequest, textStatus, errorThrown) {
					console.log(textStatus+errorThrown);
					InitMainTable(allEquipList);
				},
				dataType:'json'
			});
		});
	}
	initData();
	$("#btnSearch").click(
			function() {
				var searchtext={};
				if ($('#txtoffice_name').val()!='') {
					searchtext.office_name=$('#txtoffice_name').val();
				}
				if ($('#selstate').val()!='all') {
					searchtext.equip_state=$('#selstate').val();
				}
				if ($('#txtbarcode').val()!='') {
					searchtext.barcode=$('#txtbarcode').val();
				}
				$('#tbContent').bootstrapTable('filterBy',searchtext);
				initData();
			});
    
	/* $('#btnClean').click(function() {
		layer.confirm('<spring:message code="clean_offline"/>',{title:' ',
			btn:['<spring:message code="ok"/>','<spring:message code="cancel"/>']},
			function(index) {
			localforage.clear(function() {
				var equipTypeStore = localforage.createInstance({
					name: "equipType"
				});
				equipTypeStore.clear(function() {
					var storePost = localforage.createInstance({
						name: "equipTypePost"
					});
					storePost.clear(function() {
						layer.close(index);
						layer.msg('<spring:message code="clean_ok"/>');
						setTimeout(function() {
							window.location.reload();
						}, 300);
					})
				})
				
			});
			
			
		})
	}); */
	$('#btnCommit').click(function() {
		$.ajax({
			url: '${ctx}/alg/equip/inspectdata',
			type: 'GET',
			success: function(data) {
				var commitidarray = [];
				 data.rows.forEach(function(item) {
					commitidarray.push(item.id);
				});
				 //提交
				 var postequip =[];
					var posttype=[];
					var flaguncomitted=[];
					localforage.iterate(function(value, key, iterationNumber) {	
						if (value.equip_state == 'uncomitted') {
							flaguncomitted.push(value);
							if ($.inArray(value.id, commitidarray) > -1) {
								postequip.push(value);
							}
						}
					}, 
					function() {
						if (flaguncomitted.length==0) {
							layer.msg('<spring:message code="commit_nodata"/>');
							return false;
						}
						if (postequip.length==0) {
							window.location.reload();
							return false;
						}
						var equipTypePost = localforage.createInstance({
							name: "equipTypePost"
						});
						equipTypePost.iterate(function(value, key, iterationNumber) {	
							posttype.push({key:key,value:value});
						}, function() {
							$.ajax({
								type : 'post',
								url : '${ctx}/alg/equip/inspectpost',
								data : JSON.stringify({equip:postequip,type:posttype}),
								contentType : 'application/json;charset=utf8',
								dataType : 'json',
								success : function(data) {
									layer.msg(data.text);
									if (data.code == 'success') {
										localforage.clear(function() {
											var equipTypeStore = localforage.createInstance({
												name: "equipType"
											});
											equipTypeStore.clear(function() {
												var storePost = localforage.createInstance({
													name: "equipTypePost"
												});
												storePost.clear(function() {
													window.location.reload();
												})
											})
										});
									}
									
								}
							});
						});
					});
			} ,
			error: function (XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus+errorThrown);
				
			},
			dataType:'json'
		});
	});

});

function InitMainTable(data) {
	
	var options = {
		sidePagination: "client",
		cardView: IsMobile(),
		data: data,		
		uniqueId: "id",
		sortName:"code",
		columns: [{
			field: 'code',
			title: '<spring:message code="code"/>',
			align: 'center',
			valign: 'middle'
		}, {
			field: 'barcode',
			title: '<spring:message code="barcode"/>',
			align: 'center',
			valign: 'middle'
		}, {
			field: 'equip_type_name',
			title: '<spring:message code="type"/>',
			align: 'center',
			valign: 'middle'
		}, {
			field: 'office_name',
			title: '<spring:message code="position"/>',
			align: 'center',
			valign: 'middle'
		}, {
			field: 'user_name',
			title: '<spring:message code="operator_people"/>',
			align: 'center',
			valign: 'middle'
		}, {
			field: 'opt_startdate',
			title: '<spring:message code="begin_date"/>',
			align: 'center',
			valign: 'middle'
		}, {
			field: 'opt_enddate',
			title: '<spring:message code="end_date"/>',
			align: 'center',
			valign: 'middle'
		}, {
			title: '<spring:message code="state"/>',
			valign: 'middle',
			formatter: function(value, row, index) {
				if (row.equip_state == 'g') {
					return '<span class="label label-danger"><spring:message code="inspect_pass"/></span>'
				} else if (row.equip_state == 'r') {
					return '<span class="label label-warning"><spring:message code="inspect_nopass"/></span>'
				} else if (row.equip_state == 'uncomitted') {
					return '<span class="label label-info"><spring:message code="commit_no"/></span>'
				} else {
					return '<span class="label label-default"><spring:message code="inspect_no"/></span>'
				}
			}
		}, {
			title: '<spring:message code="operator"/>',
			align: 'center',
			valign: 'middle',
			formatter: actionFormatter
		}, {
			align: 'center',
			valign: 'middle',
			field: 'imageurl',
			title: '<spring:message code="picture"/>',
			formatter: imgFormatter
		}],
		responseHandler: function(res) {
			
			return res.rows
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
function actionFormatter(value, row, index) {
	var result = '<a style="margin:5px 5px 5px 0px" class="btn btn-app camera" data-id="' + row.id + '" data-taskdetailid="' + row.task_detail_id + '"> <i class="fa fa-camera"></i><spring:message code="take_pictures"/></a>' + '<a  style="margin:5px 5px 5px 0px" class="btn btn-app scanner"  data-id="' + row.id + '" data-type="' + row.type + '" data-taskdetailid="' + row.task_detail_id + '" data-barcode="' + row.barcode + '"> <i class="fa fa-barcode"></i><spring:message code="scanner"/></a>';
	return result
}
function imgFormatter(value, row, index) {
	if (row.equip_state == undefined || row.equip_state == 'init'||$.trim(row.equip_state) == '') {
		result = '<img  width="60" height="60" src="${ctxStatic}/img/noimg.png"></img>';
	} else if (row.equip_state == 'uncomitted'||row.equip_state == 'uncomitted-image') {
		result = '<a href="javascript:bigimgfunc(\'data:image/jpeg;base64,' + row.image + '\')"><img alt="<spring:message code="picture_no"/>" width="60" height="60"  src="data:image/jpeg;base64,' + row.imageurl + '"></img></a>';
	} else {
		result = '<a href="javascript:bigimgfunc(\'${ctxStatic}/../upload/' + row.imageurl + '\')"><img alt="<spring:message code="picture_no"/>" width="60" height="60" src="${ctxStatic}/../upload/thumb/' + row.imageurl + '"></img></a>';
	}
	return result
}
function bigimgfunc(url) {
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
			layer_msg_id = layer.msg('<spring:message code="msg_wait"/>', {
				icon: -1,
				shade: 0,
				area: ['150px', '50px'],
				time: false
			})
		}
	});
	$('#bigimage').load(function() {
		layer.close(layer_msg_id)
	})
}
//扫码后的回掉函数
function updateTableState(id) {
	localforage.getItem(id, function(err, value) {
		value.equip_state = 'uncomitted';
		localforage.setItem(id, value)
	});
	var row = $('#tbContent').bootstrapTable('getRowByUniqueId', id);
	row.equip_state = 'uncomitted';
	$('#tbContent').bootstrapTable('updateByUniqueId', {
		id: id,
		row: row
	})
}
function onDeviceReady() {
$(document).on('click','.camera', function() {
	var layer_msg_id;
	var id = $(this).data('id');
	var row = $('#tbContent').bootstrapTable('getRowByUniqueId', id);
	var params = new Object();
	params.taskDetailId = $(this).data('taskdetailid');
	takePictureNoUpload(function(data) {
		var data2 = window.rafael.GetThumbnailBase64(data);
		updateTable(id, data2,data);
		row.image=data;
		row.imageurl = data2;
		row.equip_state = 'uncomitted-image';
		localforage.setItem(row.id, row);
		layer.msg('<spring:message code="success"/>');
		
	}, function() {
		layer.msg('<spring:message code="take_pictures_reply"/>')
	}); 
	<%--takePicture('<%=basePath%>/file/upload', function() {
		layer_msg_id = layer.msg('正在上传图片...', {
			icon: -1,
			shade: 0,
			area: ['150px', '50px'],
			time: false
		});
		return params
	}, function(result) {
		var la = '#layui-layer' + layer_msg_id + ' > .layui-layer-content';
		$(la).html('上传完成');
		setTimeout(function() {
			layer.close(layer_msg_id)
		}, 300);
		var tmp = $.parseJSON(result.response);
		updateTable(id, tmp.extend)
	}, function() {
		layer.msg('上传失败,请重新拍照')
	});
	--%>
});
$(document).on('click', '.scanner', function() {
	var row = $('#tbContent').bootstrapTable('getRowByUniqueId', $(this).data('id'));
	if (row.equip_state == 'init' ||row.equip_state == '' || row.equip_state == undefined) {
		layer.msg('<spring:message code="take_pictures_first"/>');
		return false
	}
	var code = $(this).data('barcode');
	var taskdetailid = $(this).data('taskdetailid');
	var id = $(this).data('id');
	var algequiptypeid = $(this).data('type');
	
	
	window.plugins.barcodeScanner.scan(function(result) {
		if (result.cancelled == true) {
			return false
		}
		if (result.text == code) {
			$.cookie('taskdetailid', taskdetailid, {
				path: '/'
			});
			$.cookie('algequiptypeid', algequiptypeid, {
				path: '/'
			});
			$.cookie('id', id, {
				path: '/'
			});
			layer.open({
				title:' ',
				type: 2,
				offset: '50px',
				area: ['auto', '90%'],
				content: '${ctx}/alg/equiptype/scanner',
			})
		} else {
			layer.msg('<spring:message code="scanner_reply"/>')
		}
	}, function(error) {})
});
//拍照后的回调函数
function updateTable(id, imageurl,image) {
	$('#tbContent').bootstrapTable('updateByUniqueId', {
		id: id,
		row: {
			imageurl: imageurl,
			image:image,
			equip_state : 'uncomitted-image'
		}
	})
}
$('#btnScanner').click(function() {
	window.plugins.barcodeScanner.scan(function(result) {
		if (result.cancelled == true) {
			return false
		}
		$('#txtbarcode').val(result.text);
		$("#btnSearch").click();
	})
})
} </script>
</head>
<body>
	<div class="box">
		<div class="box-header with-border">
			<div class="form-inline" style="text-align: right;">
				<div class="input-group">
					<span class="input-group-addon"><spring:message code="barcode"/></span> <input
						id="txtbarcode" type="text" class="form-control">
				</div>
				<div class="input-group">
					<span class="input-group-addon"><spring:message code="position"/></span> <input
						id="txtoffice_name" type="text" class="form-control">
				</div>
				<div class="input-group">
					<span class="input-group-addon"><spring:message code="state">
					</spring:message></span>
					<select class="form-control" id = "selstate">
						<option value="all"></option>
						<option value="g"><spring:message code="inspect_pass"/></option>
						<option value="r"><spring:message code="inspect_nopass"/></option>
						<option value="uncomitted"><spring:message code="commit_no"/></option>
						<option value="init"><spring:message code="inspect_no"/></option>
					</select>
				</div>
				<button id="btnSearch" class="btn btn-default">
				<span class="glyphicon glyphicon-search"></span> <spring:message code="search"/></button>
				<%-- <button id="btnClean" class="btn btn-default">
				<span class="glyphicon glyphicon-trash"></span> <spring:message code="clean"/></button> --%>
				<button id="btnCommit" class="btn btn-default">
				<span class="glyphicon glyphicon-open"></span> <spring:message code="commit"/></button>
				<button id="btnScanner" class="btn btn-default">
				<span class="glyphicon glyphicon-barcode"></span> <spring:message code="recognition"/></button>
			</div>
		</div>
		<div class="box-body">
			<table id="tbContent"></table>
		</div>
	</div>
	<img id="bigimage" style="display: none" width="600px" src="" />
	<img alt="" style="display: none" id="displayImg" src="" />
	<script src="${ctxStatic}/android/cordova.js"></script>
	<script src="${ctxStatic}/android/upload_pic.js"></script>
	<script src="${ctxStatic}/android/barcodescanner.js"></script>
	<script src="${ctxStatic}/plugins/bootstrap-table/js/bootstrap-table.min.js"></script>
	<script src="${ctxStatic}/plugins/bootstrap-table/js/bootstrap-table-zh-CN.min.js"></script>
	<script src="${ctxStatic}/util/DateUtil.js"></script>
	<script src="${ctxStatic}/plugins/localForage/localforage.min.js"></script>
	<script src="${ctxStatic}/plugins/cookie/jquery.cookie.js"></script>
</body>
</html>