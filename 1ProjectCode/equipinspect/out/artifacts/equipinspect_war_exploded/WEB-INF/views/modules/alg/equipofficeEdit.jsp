<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form  action="${ctx}/alg/equipoffice/edit" method="post">
		<div class="box box-body">

			<input type="hidden" id="inputId" name="id"
				value="${algEquipOffice.id}">
			<div class="row">
				<div class="col-md-6 form-group">
					<label for="inputName"><spring:message code="position" /></label> <input type="text"
						class="form-control" id="inputName"
						name="name" value="${algEquipOffice.name}">
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<label for="inputRegionName"><spring:message code="region" /></label>
					<select name="algEquipRegionId" class="form-control">
					<c:forEach items="${algEquipRegion }" var="item">
					<c:choose>
						<c:when test="${item.id eq algEquipOffice.algEquipRegionId }">
							<option selected="selected" value="${item.id }">${item.name }</option>
						</c:when>
						<c:otherwise>
							<option value="${item.id }">${item.name }</option>
						</c:otherwise>
					</c:choose>
					</c:forEach>
					</select>
				</div>
			</div>
			<div class="box-footer">
				<button type="button" class="btn pull-right" onclick="parent.layer.closeAll()"><spring:message code="cancel" /></button>
				<button type="submit" class="btn btn-primary pull-right"><spring:message code="save" /></button>
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