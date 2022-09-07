<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form  action="${ctx}/alg/equiptype/add" method="post">
		<div class="box box-body">
		<input type="hidden" id="inputId" name="id"
				value="${algEquipOffice.id}">
			<div class="row">
				<div class="col-md-6 form-group">
					<label for="inputName"><spring:message code="check_item" /></label> <input type="text"
						class="form-control" id="inputName"
						name="name" value="">
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<label for="inputRegionName"><spring:message code="equip_type" /></label>
					<select id="algEquipTypeId" name="algEquipTypeId" class="form-control">
					<c:forEach items="${equipType }" var="item">
						<option value="${item.id }">${item.name }</option>
					</c:forEach>
					</select>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<label><spring:message code="type"/></label>
					<select name="type" class="form-control">
						<option selected="selected" value="bool"><spring:message code="choose_type"/></option>
						<option value="text"><spring:message code="input_type"/></option>
					</select>
				</div>
			</div>
			<div class="box-footer">
				<button type="button" class="btn pull-right" onclick="parent.layer.closeAll()"><spring:message code="cancel"/></button>
				<button type="submit" class="btn btn-primary pull-right"><spring:message code="save"/></button>
			</div>

		</div>
	</form>
<script type = "text/javascript" > 
$(function() {
	$('form').bootstrapValidator({
		fields: {
			name: {
				validators: {
					notEmpty: {
						message: '<spring:message code="msg_not_null" />'
					}
				}
			}
		}
	}).on('success.form.bv', function(e) {
		//防止表单以老式的方法提交  
		  e.preventDefault();  
		  var $form=$(this);  
		  postMethod=$form.attr('method');
		  postURL=$form.attr('action');  
		  var postData=$form.serialize();
		  $.ajax({   
		    url: postURL,   
		    type: postMethod,  
		    data: postData,  
		    success: function(data){  
		    	layer.msg(data.text);
		    	if (data.code=='success') {
					setTimeout(function () {
						parent.$('#tbContent').bootstrapTable(('refresh'));
						var index = parent.layer.getFrameIndex(window.name); //获取窗口索引  
						parent.layer.close(index);//关闭弹出的子页面窗口 
					}, 500);
				}
		    	
		     } 
		  });  
	})
}) 
</script>
</body>
</html>