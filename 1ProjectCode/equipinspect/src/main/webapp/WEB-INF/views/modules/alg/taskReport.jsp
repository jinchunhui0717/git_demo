<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet"
	href="${ctxStatic}/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css">

<script>
	
	$(function() {
		var date=new Date;
		var year=date.getFullYear(); 
		$('#datetimepicker').val(year);
		$('#datetimepicker').datetimepicker({
	        language:  'zh-CN',
	        weekStart: 1,
	        todayBtn:  1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 4,
			minView: 4,
			forceParse: 0
	    })
	  
		$("#btnSearch").click(function() {
			$.get('${ctx}/alg/task/reporthegedata?task_year='+$.trim($('#datetimepicker').val()),function(res){
				initHegeChart(res);
			});
			$.get('${ctx}/alg/task/reportwanchengedata?task_year='+$.trim($('#datetimepicker').val()),function(res){
				initWanchengChart(res);
			});
		});
		$("#btnExport").click(function() {
			window.location.href="${ctx}/alg/task/reportexport?task_year="+$.trim($('#datetimepicker').val());
		});
		$.get('${ctx}/alg/task/reporthegedata?task_year='+$.trim($('#datetimepicker').val()),function(res){
			initHegeChart(res);
		});
		$.get('${ctx}/alg/task/reportwanchengedata?task_year='+$.trim($('#datetimepicker').val()),function(res){
			initWanchengChart(res);
		});
	});
	
	
	
	
	
	var chartHege;
	var chartWancheng;
	function initHegeChart(res) {
		console.log(res);
		var D11data=[];
		var D13data=[];
		var D22data=[];
		var xdata =[];
		var titletext = '';
		if(res.length==0)
			{
			titletext = $('#datetimepicker').val()+'年消防器材点检合格率';
			for (var i = 1; i <= 12; i++) {
				xdata.push(i+'月');
				var tmpd11=0;
				var tmpd13=0;
				var tmpd22=0;
				res.forEach(function(item){
					if (item.task_month == i) {
						if (item.pos_region=='D1-1') {
							tmpd11=item.per;
						} else if (item.pos_region=='D1-3') {
							tmpd13=item.per;
						} else if (item.pos_region=='D2-2') {
							tmpd22=item.per;
						} 
					}
					
				});
				D11data.push(tmpd11);
				D13data.push(tmpd13);
				D22data.push(tmpd22);
			}
			}
		else{
			titletext = res[0].task_year+'年消防器材点检合格率';
			for (var i = res[0].task_month; i <= 12; i++) {
				xdata.push(i+'月');
				var tmpd11=0;
				var tmpd13=0;
				var tmpd22=0;
				res.forEach(function(item){
					if (item.task_month == i) {
						if (item.pos_region=='D1-1') {
							tmpd11=item.per;
						} else if (item.pos_region=='D1-3') {
							tmpd13=item.per;
						} else if (item.pos_region=='D2-2') {
							tmpd22=item.per;
						} 
					}
					
				});
				D11data.push(tmpd11);
				D13data.push(tmpd13);
				D22data.push(tmpd22);
			}
		}
		
		
		
		var optionHege = {
				color:['#5C9BD5','#404040','#A7A7A7'],
			    title : {
			        text: titletext
			    },
			    legend: {
			        data:['D1-1 点检合格率','D1-3 点检合格率','D2-2及外围 点检合格率'],
			        x:'center',
			        y:'bottom'
			    },
			    xAxis : [
			        {
			            type : 'category',
			            data : xdata,
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value',
			            axisLabel: {
			                  show: true,
			                  interval: 'auto',
			                  formatter: '{value}%'
			          		}
			        }
			    ],
			    series : [
			        {
			            name:'D1-1 点检合格率',
			            type:'bar',
			            data:D11data,
			            itemStyle : { normal: {label : {show: true}}},
			        },
			        {
			            name:'D1-3 点检合格率',
			            type:'bar',
			            data:D13data,
			            itemStyle : { normal: {label : {show: true}}},
			        },
			      
			        {
			            name:'D2-2及外围 点检合格率',
			            type:'bar',
			            data:D22data,
			            itemStyle : { normal: {label : {show: true}}},
			        }
			    ]
			};
			                    

	    // 为echarts对象加载数据
	    chartHege = echarts.init(document.getElementById('divHege'), 'macarons');
	    chartHege.setTheme()
	    chartHege.setOption(optionHege);
	    

	}
    
	function initWanchengChart(res) {

		var D11data=[];
		var D13data=[];
		var D22data=[];
		var xdata =[];
		var titletext = '';
		if(res.length==0)
			{
			titletext = $('#datetimepicker').val()+'年消防器材点检完成率';
			for (var i = 1; i <= 12; i++) {
				xdata.push(i+'月');
				var tmpd11=0;
				var tmpd13=0;
				var tmpd22=0;
				res.forEach(function(item){
					if (item.task_month == i) {
						if (item.pos_region=='D1-1') {
							tmpd11=item.per;
						} else if (item.pos_region=='D1-3') {
							tmpd13=item.per;
						} else if (item.pos_region=='D2-2') {
							tmpd22=item.per;
						} 
					}
					
				});
				D11data.push(tmpd11);
				D13data.push(tmpd13);
				D22data.push(tmpd22);
			}
			}
		else{
			titletext = res[0].task_year+'年消防器材点检完成率';
			for (var i = res[0].task_month; i <= 12; i++) {
				xdata.push(i+'月');
				var tmpd11=0;
				var tmpd13=0;
				var tmpd22=0;
				res.forEach(function(item){
					if (item.task_month == i) {
						if (item.pos_region=='D1-1') {
							tmpd11=item.per;
						} else if (item.pos_region=='D1-3') {
							tmpd13=item.per;
						} else if (item.pos_region=='D2-2') {
							tmpd22=item.per;
						} 
					}
					
				});
				D11data.push(tmpd11);
				D13data.push(tmpd13);
				D22data.push(tmpd22);
			}
		}
		
		
		
		var optionWancheng = {
				color:['#5C9BD5','#404040','#A7A7A7'],
			    title : {
			        text: titletext
			    },
			    legend: {
			        data:['D1-1 点检完成率','D1-3 点检完成率','D2-2及外围 点检完成率'],
			        x:'center',
			        y:'bottom'
			    },
			    xAxis : [
			        {
			            type : 'category',
			            data : xdata,
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value',
			            axisLabel: {
			                  show: true,
			                  interval: 'auto',
			                  formatter: '{value}%'
			          		}
			        }
			    ],
			    series : [
			        {
			            name:'D1-1 点检完成率',
			            type:'bar',
			            data:D11data,
			            itemStyle : { normal: {label : {show: true}}},
			        },
			        {
			            name:'D1-3 点检完成率',
			            type:'bar',
			            data:D13data,
			            itemStyle : { normal: {label : {show: true}}},
			        },
			      
			        {
			            name:'D2-2及外围 点检完成率',
			            type:'bar',
			            data:D22data,
			            itemStyle : { normal: {label : {show: true}}},
			        }
			    ]
			};
			       
			                    

		chartWancheng = echarts.init(document.getElementById('divWancheng'), 'macarons');


	    // 为echarts对象加载数据
	    chartWancheng.setTheme()
	    chartWancheng.setOption(optionWancheng);
	    

	}
    
    
    
</script>
</head>
<body>
			<div class="box">
				<div class="box-header with-border">
					<div class="form-inline" style="text-align: right;">
					<div class="input-group">
						<span class="input-group-addon">日期</span> 
						<input class="form-control"  type="text" id="datetimepicker" data-date-format="yyyy" >
								
					</div>
				
				<button id="btnSearch" class="btn btn-default">
				<span class="glyphicon glyphicon-search"></span> <spring:message code="search"/></button>
				<button id="btnExport" class="btn btn-default">
					<span class="glyphicon glyphicon-export"></span> <spring:message code="export"/></button>
			</div>
				</div>
				<div class="box-body">
					<div class="row">
						<div class="col-sm-6">
							<div id="divHege" class="box-body text-center" style="min-height: 350px">
									</div>
						</div>
						<div class="col-sm-6">
							<div id="divWancheng" class="box-body text-center" style="min-height: 350px">
									</div>
						</div>
					</div>
				</div>
			</div>
			
	<script src="${ctxStatic}/plugins/echarts/echarts-all.js"></script>
	<script
		src="${ctxStatic}/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
	<script
		src="${ctxStatic}/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>

</body>
</html>