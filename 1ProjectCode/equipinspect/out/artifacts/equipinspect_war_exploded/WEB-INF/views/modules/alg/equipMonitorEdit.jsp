<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript"
	src="${ctxStatic}/plugins/zonemarker/js/context/context.js"></script>
<script type="text/javascript"
	src="${ctxStatic}/plugins/zonemarker/js/drag.js"></script>
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/plugins/zonemarker/js/context/context.standalone.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/font-rafael/xiaofang/css/iconfont.css">
<style type="text/css">
#searchdiv {
	position: fixed;
	top: 50px;
	right: 0;
	padding: 5px;
	vertical-align: center; text-align : center;
	background: rgba(0, 0, 0, .3);
	z-index: 1000;
	text-align: center;
	
}
#canvas{
	position: relative;
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
		<input type="button" value="<spring:message code="add_equip"/>" class="btn btn-primary"
				id="btn_add" />
		<select class="form-control" id="stRegion">
				<option value="D2-2">D2-2<spring:message code="region"/></option>
				<option  value="D1-1">D1-1<spring:message code="region"/></option>
				<option  value="D1-3">D1-3<spring:message code="region"/></option>
			</select>
	</div>
	<div>
		<div id="canvas">
			<img id="imageFullScreen"  width="100%" height="0" src="${ctxStatic}/plugins/zonemarker/${posRegion }.jpg" />
		</div>
	</div>

	<script>
		//初始化计数器
		var num = 0;
		//区块锁定标识
		var lock = false;
		var bili = 1;
		var gbili = 1;
		var loadData = [];
		function createBox(data) {
			var color = data.equip_state;
			if (data.operator=='') {
				color = 'gray';
					
			} else {
				if (color == 'g') {
					color = 'red';
				}else if (color == 'r') {
					color = 'orange';
				}else{
					color='black';
				}
			}
			var fontsize=35 * gbili +'px';
			var posx = data.posx*bili || 0;
			var posy = data.posy*gbili || 0;
			//更新计数器并记录当前计数
			var curNum = num++;
			//console.log(data)
			//创建块
			var pos = $("#canvas").position();
			var box = $(
					'<div class="dragbox" rel="'+curNum+'" data-id="'+data.id+'"><i class="icon iconfont '+data.font+'" style="font-size:'+fontsize+';color:'+color+';cursor:pointer"></i></div>')
					.css(
							{
								top : posy > 0 ? posy : (pos.top > 0 ? 0
										: pos.top * -1),
								left : posx > 0 ? posx : (pos.left > 0 ? 0
										: pos.left * -1)
							}).appendTo("#canvas");

			//创建右键菜单
			context.attach('.dragbox[rel=' + curNum + ']',
							[{
								header : '<spring:message code="operator"/>'
							},
							{
								text : '<spring:message code="save_pos"/>',
								action : function(e) {
									e.preventDefault();
									var dragbox = $('.dragbox[rel='+ curNum + ']');
									var box = 'id='+$(dragbox).data('id')+'&posx='+Math.round($(dragbox).position().left/bili)
											+'&posy='+Math.round($(dragbox).position().top/gbili);
									$.post("${ctx}/alg/equip/edit",
													box,
													function(data) {
														layer.msg(data.text);
													}, "json");
										}
									},
							{
								text : '<spring:message code="edit"/>',
								action : function(e) {
									e.preventDefault();
									var dragbox = $('.dragbox[rel='+ curNum + ']');
									var dataid = $(dragbox).data('id');
									layer.open({
										title:' ',
										type:2,
										offset: '50px',
										content:'${ctx}/alg/equip/edit?id='+dataid,
										success : function(layero, index) {
													layer.iframeAuto(index);
												}
										});
								}
							},
							{
									text : '<spring:message code="delete"/>',
									action : function(e) {
									e.preventDefault();
									layer.confirm('<spring:message code="msg_confirm_delete"/>?',
											{title:' ',
										btn:['<spring:message code="ok"/>','<spring:message code="cancel"/>']},
										function() {
											var dragbox = $('.dragbox[rel='+ curNum+ ']');
											$.get("${ctx}/alg/equip/delete?id="
												+$(dragbox).data('id'),
												function(data) {
												if (data.code == "success") {
													$('.dragbox[rel='+ curNum+ ']').remove();
												}
												layer.msg(data.text);
											},"json");
										});
									}
									}, {
										divider : true
									}
							]);
		}
		
		function loaddatafunc(){
			if (IsMobile()) {
				if (window.rafael.isNetworkConnected() != 1) {
				} else {
					$.ajax({
						url: '${ctx}/alg/equip/monitordata?posRegion=${posRegion}',
						type: 'GET',
						success: function(data) {
							 data.forEach(function(item) {
								 item.region_office_name = item.region_name+" "+item.office_name;
							});
							 initlayout();
						} ,
						dataType:'json'
					});
				}

			}else {
				<c:forEach items="${equipPos}" var="equip" >
				loadData.push({
					id : "${equip.id}",
					code:"${equip.code}",
					type : "${equip.type}",
					equip_type_name:"${equip.equip_type_name}",
					posx : parseInt("${equip.posx}"),
					posy : parseInt("${equip.posy}"),
					pos_region : "${equip.pos_region}",
					equip_state : "${equip.equip_state}",
					operator:"${equip.operator}",
					user_name:"${equip.user_name}",
					opt_startdate:"${equip.opt_startdate}",
					opt_enddate:"${equip.opt_enddate}",
					font:"${equip.font}",
					task_detail_id:"${equip.task_detail_id}",
					region_office_name:"${equip.region_name}"+" "+"${equip.office_name}",
					inspectdate:"${equip.inspectdate}"
				})
				</c:forEach>
				initlayout();
			}
			
		}
		function initlayout(){
			console.log(loadData);
			$.each(loadData, function(i, row) {
				createBox(row);
			});
		}
		$(function() {
			if(IsMobile())
				$('#imageFullScreen').removeAttr('width');
			$("#imageFullScreen").height(document.body.clientHeight-120);
		 	var naturalWidth = document.getElementById('imageFullScreen').naturalWidth;
		 	var naturalHeight = document.getElementById('imageFullScreen').naturalHeight;
			$('#imageFullScreen').parent().parent().height(
					$('#imageFullScreen').height()+10);
			 bili = $('#imageFullScreen').width()/naturalWidth;
			 gbili = $('#imageFullScreen').height()/naturalHeight;

			$("#stRegion").val("${posRegion}");
			 loaddatafunc();
			 
			//右键菜单参数
			context.init({
				fadeSpeed : 100,
				filter : function($obj) {
				},
				above : 'auto',
				preventDoubleContext : true,
				compress : false
			});
			//添加
			$("#btn_add").click(function() {
								layer.open({
									title:'设施添加',
									type:2,
									offset: '50px',
									area:'500px',
									content:'${ctx}/alg/equip/add',
									success : function(layero, index) {
										layer.iframeAuto(index);
									}
								});
							});

			//创建拖拽方法
			$("#canvas").on('mousedown', '.dragbox', function(e) {
				if (lock)
					return;
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

			

			$("#stRegion")
					.change(
							function() {

								var regionVal = $("#stRegion").val();
								window.location.href = "${ctx}/alg/equip/monitoredit?posRegion="
										+ regionVal;
							});
		});
	</script>
</body>
</html>