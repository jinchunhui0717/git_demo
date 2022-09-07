<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>设备点检</title>
<script type="text/javascript"
	src="${ctxStatic}/plugins/zonemarker/js/context/context.js"></script>
<script type="text/javascript"
	src="${ctxStatic}/plugins/zonemarker/js/drag.js"></script>
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/plugins/zonemarker/js/context/context.standalone.css">
<style type="text/css">
#canvas {
	position: absolute;
	z-index: 9;
	border: 2px dashed #ccc;
}

#searchdiv {
	position: fixed;
	top: 50px;
	right: 0;
	width: auto;
	padding-left: 10px;
	padding-right: 10px;
	height: 50px;
	line-height: 50px;
	vertical-align: center;
	text-align: center;
	background: rgba(0, 0, 0, .3);
	z-index: 1000;
	text-align: center;
}

.dragbox {
	cursor: move;
	position: absolute;
	z-index: 99;
}

.dragbox .bg {
	width: 100%;
	height: 100%;
	background-color: orange;
}

.dragbox .coor {
	width: 10px;
	height: 10px;
	overflow: hidden;
	cursor: se-resize;
	position: absolute;
	right: 0;
	bottom: 0;
	background-color: red;
}

.dragbox .content {
	position: absolute;
	left: 50%;
	top: 50%;
	z-index: 99;
	text-align: center;
	font: bold 14px/1.5em simsun;
}
.dragbox img{
	cursor: pointer;
}
</style>
</head>
<body>
	<div id="searchdiv" class="form-inline">
		<select class="form-control" id="stRegion">
			<option value="D2-2">D2-2区域</option>
			<option  value="D1-1">D1-1区域</option>
			<option  value="D1-3">D1-3区域</option>
		</select>
	</div>
	<div>
		<div id="canvas">
			<img id="imageFullScreen"
				src="${ctxStatic}/plugins/zonemarker/${posRegion }.jpg" />
		</div>
	</div>
	<script>
		//初始化计数器
		var num = 0;
		function createBox(data) {
			var dataId = data.id || '';
			var value = data.type || '';
			var color = data.equipState || '';
			if (color == 'r' || color == '') {
				color = 'red';
			} else if (color == 'o') {
				color = 'orange';
			} else if (color == 'g') {
				color = 'green';
			}
			var height = 26;
			var width = 26;
			var pageX = data.pageX || 0;
			var pageY = data.pageY || 0;
			//更新计数器并记录当前计数
			var curNum = num++;
			//console.log(data)
			//创建块
			var pos = $("#canvas").position();
			var box = $(
					'<div class="dragbox" rel="'+curNum+'" dataId="'+dataId+'"><img width="'+width+'" height="'+height+'" src="${ctxStatic}/plugins/zonemarker/'+value+'-'+color+'.svg"/></div>')
					.css(
							{
								top : pageY > 0 ? pageY : (pos.top > 0 ? 0
										: pos.top * -1),
								left : pageX > 0 ? pageX : (pos.left > 0 ? 0
										: pos.left * -1)
							}).appendTo("#canvas");

		}
		$(function() {
			$('#imageFullScreen').parent().parent().height(
					$('#imageFullScreen').height());
			$("#stRegion").val("${posRegion}")
			//加载layer拓展
			layer.config({
				extend : 'extend/layer.ext.js'
			});
			$(document).on('click', '.dragbox img', function() {
				layer.open({
					type : 2,
					title:'拍照扫码',
					content: '${ctx}/file/uploadview',
					success : function(layero, index) {
						layer.iframeAuto(index);
					}
				});
			});
			//创建拖拽方法
			$("#canvas").mousedown(function(e) {
				var canvas = $(this);
				e.preventDefault();
				var pos = $(this).position();
				this.posix = {
					'x' : e.pageX - pos.left,
					'y' : e.pageY - pos.top
				};
				$.extend(document, {
					'move' : true,
					'move_target' : this,
					'call_down' : function(e, posix) {
						canvas.css({
							'cursor' : 'move',
							'top' : e.pageY - posix.y,
							'left' : e.pageX - posix.x
						});
					},
					'call_up' : function() {
						canvas.css('cursor', 'default');
					}
				});
			}).on('mousedown', '.dragbox', function(e) {
				var pos = $(this).position();
				this.posix = {
					'x' : e.pageX - pos.left,
					'y' : e.pageY - pos.top
				};
				$.extend(document, {
					'move' : true,
					'move_target' : this
				});
				e.stopPropagation();
			}).on(
					'mousedown',
					'.dragbox .coor',
					function(e) {
						var $box = $(this).parent();
						var posix = {
							'w' : $box.width(),
							'h' : $box.height(),
							'x' : e.pageX,
							'y' : e.pageY
						};
						$.extend(document, {
							'move' : true,
							'call_down' : function(e) {
								$box.css({
									'width' : Math.max(30, e.pageX - posix.x
											+ posix.w),
									'height' : Math.max(30, e.pageY - posix.y
											+ posix.h)
								});
							}
						});
						e.stopPropagation();
					});
			var loadData = [];
			<c:forEach items="${equipPos}" var="equip">
			loadData.push({
				id : "${equip.id}",
				type : "${equip.type}",
				pageX : parseInt("${equip.posx}"),
				pageY : parseInt("${equip.posy}"),
				posRegion : "${equip.posRegion}",
				equipState : "${equip.equipState}"
			})
			</c:forEach>
			$.each(loadData, function(i, row) {
				createBox(row);
			});
			
			$("#stRegion").on(
					"change",
					function() {

						var regionVal = $("#stRegion").val();
						window.location.href = "${ctx}/alg/equip/check?posRegion="
								+ regionVal;
					});
		});
	</script>
</body>
</html>