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
	<script type="text/javascript"
	src="${ctxStatic}/util/DateUtil.js"></script>
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
#statusdiv {
	position: fixed;
	bottom:0;
	right: 0;
	padding: 5px;
	margin-bottom:70px;
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
		<select class="form-control" id="stRegion">
				<option value="D2-2">D2-2<spring:message code="region"/></option>
				<option  value="D1-1">D1-1<spring:message code="region"/></option>
				<option  value="D1-3">D1-3<spring:message code="region"/></option>
			</select>
	</div>
	<div id="statusdiv">
		<label class="form-control" id="lblnoCount" style="cursor: pointer;"></label>
		<label class="form-control" id="lblyesCount" style="cursor: pointer;color: red"></label>
		<label class="form-control" id="lblerrorCount" style="cursor: pointer;color: orange;"></label>
	</div>
	<div>
		<div id="canvas" >
			<img id="imageFullScreen" width="100%" height="0"/>
		</div>
	</div>
	<script>
		
		//初始化计数器
		var num = 0;
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

			//创建块
			var pos = $("#canvas").position();
			var box = $(
					'<div class="dragbox" rel="'+curNum+'"  data-id="'+data.id+'"><i class="icon iconfont '+data.font+'" style="font-size:'+fontsize+';color:'+color+';cursor:pointer"></i></div>')
					.css(
							{
								top : posy > 0 ? posy : (pos.top > 0 ? 0
										: pos.top * -1),
								left : posx > 0 ? posx : (pos.left > 0 ? 0
										: pos.left * -1)
							}).appendTo("#canvas");

		}
		function loaddatafunc(){
			$("#imageFullScreen").height(document.body.clientHeight-120);
		 	var naturalWidth = document.getElementById('imageFullScreen').naturalWidth;
		 	var naturalHeight = document.getElementById('imageFullScreen').naturalHeight;
			$('#imageFullScreen').parent().parent().height(
					$('#imageFullScreen').height()+10);
			 bili = $('#imageFullScreen').width()/naturalWidth;
			 gbili = $('#imageFullScreen').height()/naturalHeight;
			$("#stRegion").val("${posRegion}");
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
			var xj_no =0;//未巡检
			var xj_yes=0;//合格
			var xj_error=0;//未合格
			var xj_type = [];
			var bl = false;
			$.each(loadData, function(i, row) {
				createBox(row);
				if (row.equip_state=='g') {
					xj_yes++;
				}else if (row.equip_state=='r') {
					xj_error++;
				}else{
					xj_no++;
				}
				bl = false;
				for (var i = 0; i < xj_type.length; i++) {
					var iterable_element = xj_type[i];
					if (iterable_element.type==row.equip_type_name && iterable_element.state==row.equip_state) {
						bl=true;
						iterable_element.count++;
						break;
					}
				}
				
				if(bl==false){
					xj_type.push({type:row.equip_type_name,state:row.equip_state,count:1});
				}
			});
			xj_type.sort(function(x,y) {
				return (x.type < y.type) ? 1 : -1
			})
			$('#lblnoCount').text('<spring:message code="inspect_no"/>:'+xj_no).click(lblcount);
			$('#lblyesCount').text('<spring:message code="inspect_pass"/>:'+xj_yes).click(lblcount);
			$('#lblerrorCount').text('<spring:message code="inspect_nopass"/>:'+xj_error)
			.click(lblcount);
			function lblcount(){
				var msgDetail = '<table class="table table-bordered"><thead><tr><th>项目清单</th><th>数量</th></tr></thead><tbody>';
				for (var i = 0; i < xj_type.length; i++) {
					msgDetail+='<tr>';
					if (xj_type[i].state=='g') {
						msgDetail +='<td>'+xj_type[i].type+'<spring:message code="inspect_pass"/></td><td>'+xj_type[i].count+'</td>';
					}else if (xj_type[i].state=='r') {
						msgDetail +='<td>'+xj_type[i].type+'<spring:message code="inspect_nopass"/></td><td>'+xj_type[i].count+'</td>';
					}else{
						msgDetail +='<td>'+xj_type[i].type+'<spring:message code="inspect_no"/></td><td>'+xj_type[i].count+'</td>';
					}
					msgDetail+='</tr>';
				}
				msgDetail+='</tbody></table>';
				layer.alert(msgDetail,{title:'设备状态明细',closeBtn: 0,shadeClose: true,btn:['<spring:message code="ok"/>']});
			}
			$("#stRegion").on(
					"change",
					function() {
						var regionVal = $("#stRegion").val();
						window.location.href = "${ctx}/alg/equip/monitor?posRegion="
								+ regionVal;
					});
			$('.dragbox i').click(function () {		
				var that = $(this);
				var id = $(this).parent().data('id');
				var rows = loadData.filter(function(data) {
					return data.id == id;
				})
				var row;
				if (rows.length>0) {
					row = rows[0];
				}
				var url='';
				var data;
				if (row.operator=='') {
					layer.msg('该设施未分配');
					return false;
				}
				if (row.equip_state=='g'||row.equip_state=='r') {
					//有检测项了
					url='${ctx}/alg/task/taskdetailinspectdata';
					data={taskDetailId:row.task_detail_id};
				}
				else {
					url='${ctx}/alg/equiptype/listdata';
					data={alg_equip_type_id:row.type};
				}
				$.get(url,
						data,function(data){
						
							var txt = '<table class="table table-bordered"><thead><tr><th>项目清单</th><th>结果</th></tr></thead><tbody>';
							txt +='<tr><td><spring:message code="code"/></td><td>'+row.code+'</td></tr>'+
							'<tr><td><spring:message code="location"/></td><td>'+row.region_office_name+'</td></tr>'+
							'<tr><td><spring:message code="operator_people"/></td><td>'+row.user_name+'</td></tr>'+
							'<tr><td><spring:message code="begin_date"/></td><td>'
							+(row.opt_startdate==''?'':new Date(row.opt_startdate).Format('yyyy-MM-dd'))+'</td></tr><tr><td><spring:message code="end_date"/></td><td>'+
							(row.opt_enddate==''?'':new Date(row.opt_enddate).Format('yyyy-MM-dd'))
							+'</td></tr><tr><td><spring:message code="inspectdate"/></td><td>'+(row.inspectdate==''?'':new Date(row.inspectdate.substring(0,10)).Format('yyyy-MM-dd'))+'</td></tr>';
							var hege;var hegeflag = false;
							var beizhu;var beizhuflag = false;
							if ('<%=response.getLocale().getLanguage()%>'=='zh') {
								for (var i = 0; i < data.rows.length; i++) {
									if(data.rows[i].value=='true')
										data.rows[i].value = "是";
									else if(data.rows[i].value=='false')
										data.rows[i].value = "否";
								}
								
								for (var i = 0; i < data.rows.length; i++) {
									if (data.rows[i].name=='是否合格') {
										hege={name:data.rows[i].name,value:data.rows[i].value};
										hegeflag = true;
									}else if (data.rows[i].name=='备注') {
										beizhu={name:data.rows[i].name,value:data.rows[i].value};
										beizhuflag = true;
									}
									else {
										txt +='<tr><td>'+data.rows[i].name+'</td><td>'+(data.rows[i].value==undefined?'<spring:message code="inspect_no"/>':data.rows[i].value)+'</td></tr>';
										
									}
								}
								if (hegeflag) {
									txt +='<tr><td>'+hege.name+'</td><td>'+(hege.value==undefined?'<spring:message code="inspect_no"/>':hege.value)+'</td></tr>';
									
								}
								if (beizhuflag) {
									txt +='<tr><td>'+beizhu.name+'</td><td>'+(beizhu.value==undefined?'<spring:message code="inspect_no"/>':beizhu.value)+'</td></tr>';
									
								}
							}else if ('<%=response.getLocale().getLanguage()%>'=='en') {
								for (var i = 0; i < data.rows.length; i++) {
									if (data.rows[i].name=='是否合格') {
										hege={name:data.rows[i].name,value:data.rows[i].value};
										hegeflag = true;
									}else if (data.rows[i].name=='备注') {
										beizhu={name:data.rows[i].name,value:data.rows[i].value};
										beizhuflag = true;
									}
									else {
										txt +='<tr><td>'+data.rows[i].name+'</td><td>'+(data.rows[i].value==undefined?'<spring:message code="inspect_no"/>':data.rows[i].value)+'</td></tr>';
										
									}
								}
								if (hegeflag) {
									txt +='<tr><td>'+hege.name+'</td><td>'+(hege.value==undefined?'<spring:message code="inspect_no"/>':hege.value)+'</td></tr>';
									
								}
								if (beizhuflag) {
									txt +='<tr><td>'+beizhu.name+'</td><td>'+(beizhu.value==undefined?'<spring:message code="inspect_no"/>':beizhu.value)+'</td></tr>';
									
								}
							}
							
							
							txt += '</tbody></table>';;
							layer.alert(txt,{closeBtn: 0,shadeClose: true,
								title:' ',
								offset:'50px',
								area:['auto','90%'],
								btn:['<spring:message code="ok"/>'],
								});
						});
				
			})
		}
		$(function() {
			$('#imageFullScreen').attr('src','${ctxStatic}/plugins/zonemarker/${posRegion }.jpg');
			$('#imageFullScreen').attr('onload','loaddatafunc()');
			if(IsMobile())
				$('#imageFullScreen').removeAttr('width');
			
			
		});
		
	</script>
</body>
</html>