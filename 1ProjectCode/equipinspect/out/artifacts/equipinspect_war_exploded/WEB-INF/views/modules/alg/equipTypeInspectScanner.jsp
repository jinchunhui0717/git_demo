<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet"
	href="${ctxStatic}/plugins/bootstrap-table/css/bootstrap-table.min.css">
<script type = "text/javascript">

function addEle(equipList) {
	var hege;var hegeflag = false;
	var beizhu;var beizhuflag = false;
	equipList.forEach(function(item) {
		if (item.name=='是否合格') {
			hege={name:item.name,id:item.id,type:item.type};
			hegeflag = true;
		}else if (item.name=='备注') {
			beizhu={name:item.name,id:item.id,type:item.type};
			beizhuflag = true;
		}
		else {
			if (item.type == 'bool') {
				var radiovar  = '<div class="row form-group">'+
				'                            <label class="col-xs-8 control-label">'+item.name+'</label>'+
				'                            <div class="col-xs-4">'+
				'                                        <input data-bv-notempty data-bv-notempty-message="<spring:message code="msg_not_null" />" type="radio" name="'+item.id+'" value="true" /> 是'+
				'                                        <input  data-bv-notempty data-bv-notempty-message="<spring:message code="msg_not_null" />"  type="radio" name="'+item.id+'" value="false" /> 否'+
				'                            </div>'+
				'                        </div>';
				$('.box-body').append(radiovar);
			} else {
				$('.box-body').append('<div class="row form-group"><div class="col-xs-12 "><label>' + item.name + '</label>' + '		<input type="text" class="form-control"  name="' + item.id + '" value="" />' + '	</div>' + '</div>');
			}
		}
		
	});
	if (hegeflag) {
		if (hege.type == 'bool') {
			var radiovar  = '<div class="row form-group">'+
			'                            <label class="col-xs-8 control-label">'+hege.name+'</label>'+
			'                            <div class="col-xs-4">'+
			'                                        <input data-bv-notempty data-bv-notempty-message="<spring:message code="msg_not_null" />" type="radio" name="'+hege.id+'" value="true" /> 是'+
			'                                        <input  data-bv-notempty data-bv-notempty-message="<spring:message code="msg_not_null" />"  type="radio" name="'+hege.id+'" value="false" /> 否'+
			'                            </div>'+
			'                        </div>';
			$('.box-body').append(radiovar);
		} else {
			$('.box-body').append('<div class="row form-group"><div class="col-xs-12 "><label>' + hege.name + '</label>' + '		<input type="text" class="form-control"  name="' + hege.id + '" value="" />' + '	</div>' + '</div>');
		}
	}
	if (beizhuflag) {
		if (beizhu.type == 'bool') {
			var radiovar  = '<div class="row form-group">'+
			'                            <label class="col-xs-8 control-label">'+beizhu.name+'</label>'+
			'                            <div class="col-xs-4">'+
			'                                        <input data-bv-notempty data-bv-notempty-message="<spring:message code="msg_not_null" />" type="radio" name="'+beizhu.id+'" value="true" /> 是'+
			'                                        <input  data-bv-notempty data-bv-notempty-message="<spring:message code="msg_not_null" />"  type="radio" name="'+beizhu.id+'" value="false" /> 否'+
			'                            </div>'+
			'                        </div>';
			$('.box-body').append(radiovar);
		} else {
			$('.box-body').append('<div class="row form-group"><div class="col-xs-12 "><label>' + beizhu.name + '</label>' + '		<input type="text" class="form-control"  name="' + beizhu.id + '" value="" />' + '	</div>' + '</div>');
		}
	}
	$('form').bootstrapValidator();
	
}
var equipList = [];
$(function() {
	var algequiptypeid = $.cookie('algequiptypeid');
	var taskdetailid = $.cookie('taskdetailid');
	var id = $.cookie('id');
	var store = localforage.createInstance({
		name: "equipType"
	});
	//var equipList = [];
	store.iterate(function(value, key, iterationNumber) {
		// 此回调函数将对所有 key/value 键值对运行
		if (value.alg_equip_type_id == algequiptypeid) {
			equipList.push(value)
		}
	},function() {

		if (equipList.length > 0) {
			console.log('离线');
			//离线
			addEle(equipList);
		} else {
			//zaixian
			console.log('zaixian');
			$.get('${ctx}/alg/equiptype/scannerdata?algequiptypeid=' + algequiptypeid,
			function(data) {
				equipList =data;
				addEle(data);
			});
		}
	});
	//劫持表单的提交事件  
	$('form').submit(function(e) {
		
		//防止表单以老式的方法提交  
		e.preventDefault();
		var bootstrapValidator = $("form").data('bootstrapValidator');
		 //执行校验
		// bootstrapValidator.validate();
		 //判断校验结果
		 if(!bootstrapValidator.isValid()){
		return false;
			  }

		var $form = $(this);
		postMethod = $form.attr('method');
		postURL = $form.attr('action');
		x=$form.serializeArray();
		var postData ={};
	    $.each(x, function(i, field){
	    	$(equipList).each(function(index,item) {
				if (item.id==field.name) {
					
					postData[item.name]=field.value;
					return false;
				}
			});
	    	
	    });

		var storePost = localforage.createInstance({
			name: "equipTypePost"
		});
		storePost.setItem(taskdetailid, postData,function(value) {
			// 当值被存储后，可执行其他操作
			setTimeout(function() {
				parent.updateTableState(id);
				var index = parent.layer.getFrameIndex(window.name); //获取窗口索引  
				parent.layer.close(index); //关闭弹出的子页面窗口 
			},
			300);
			
		});
		/* storePost.removeItem(taskdetailid,function(){
			storePost.setItem(taskdetailid, postData,function(value) {
				// 当值被存储后，可执行其他操作
				setTimeout(function() {
					parent.updateTableState(id);
					var index = parent.layer.getFrameIndex(window.name); //获取窗口索引  
					parent.layer.close(index); //关闭弹出的子页面窗口 
				},
				300);
				
			});
		}); */
		
	})
}); 
</script>
</head>
<body>
	<form
		action="${ctx}/alg/equiptype/scanner?taskdetailid=${taskdetailid}"
		method="post">
		<div class="box-body"></div>
		<div class="box-footer">
			<button type="button" class="btn pull-right"
				onclick="parent.layer.closeAll()">取消</button>
			<button type="submit" class="btn btn-primary pull-right">保存</button>
		</div>
	</form>
	<script src="${ctxStatic}/plugins/localForage/localforage.min.js"></script>
	<script src="${ctxStatic}/plugins/cookie/jquery.cookie.js"></script>
	<script src="${ctxStatic}/plugins/jqueryjson/jquery.serializejson.min.js"></script>
	<script src="${ctxStatic}/plugins/layer-v3.1.1/src/layer.js"></script>
</body>
</html>