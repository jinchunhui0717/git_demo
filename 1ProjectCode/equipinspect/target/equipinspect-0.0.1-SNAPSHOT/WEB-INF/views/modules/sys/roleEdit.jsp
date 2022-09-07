<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form  action="${ctx}/sys/role/edit" method="post">
		<div class="box box-body">

			<input type="hidden" id="inputId" name="id"
				value="${sysRole.id}">
			<div class="row">
				<div class="col-md-6 form-group">
					<label ><spring:message code="role"/></label> <input type="text"
						class="form-control" 
						name="name" value="${sysRole.name}">
				</div>
				<div class="col-md-6 form-group">
					<label ><spring:message code="indexurl"/></label> 
					<select name="indexurl" class="form-control">
					
						<c:forEach items="${sysmenu}" var="item">
							<option value="${item.href }">${item.name }</option>
							
						</c:forEach>
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
	$('[name="indexurl"]').val('${sysRole.indexurl}');
	$('form').bootstrapValidator({
		fields: {
			name: {
				validators: {
					notEmpty: {
						message: '<spring:message code="role" /><spring:message code="msg_not_null" />'
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