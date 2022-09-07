<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="${ctx}/sys/user/add" method="post">
		<div class="box">
			<div class="box-body">
				<div class="form-group">
					<label for="inputLogin_name"><spring:message code="account" /></label>
					<input type="text" class="form-control" id="inputLogin_name"
						name="loginName" value="">
				</div>
				<div class="form-group">
					<label for="inputPassword"><spring:message code="password" /></label>
					<input type="password" class="form-control" id="inputPassword"
						name="password" value="">
				</div>
				<div class="form-group">
					<label for="inputName"><spring:message code="name" /></label> <input
						type="text" class="form-control" id="inputName" name="name"
						value="">
				</div>
				<div class="form-group">
					<label for="selectRole"><spring:message code="role" /></label> <select
						class="form-control" name="newroleid">
						<c:forEach items="${sysRole }" var="role">
							<option value="${role.id }">${role.name }</option>
						</c:forEach>
					</select>
				</div>
				<div class="form-group">
				<label ><spring:message code="state" /></label> <select
					class="form-control" name="loginLocked">
					<option value="0"><spring:message code="enable" /></option>
					<option value="1"><spring:message code="disable" /></option>
				</select>
			</div>
			</div>
			<div class="box-footer">
				<button type="button" class="btn pull-right"
					onclick="parent.layer.closeAll()">
					<spring:message code="cancel" />
				</button>
				<button type="submit" class="btn btn-primary pull-right">
					<spring:message code="save" />
				</button>
			</div>
		</div>
	</form>
<script type = "text/javascript" > 
$(function() {
	$('form').bootstrapValidator({
		fields: {
			loginName: {
				validators: {
					notEmpty: {
						message: '<spring:message code="msg_accountnotempty"></spring:message>'
					}
				}
			},
			password: {
				validators: {
					notEmpty: {
						message: '<spring:message code="msg_passwordnotempty"></spring:message>'
					},
					stringLength: {
						min: 6,
						max: 10,
						message: '<spring:message code="msg_length" arguments="6,10" />'
					},
				}
			}
		}
	}).on('success.form.bv', function(e) {
		e.preventDefault();
		var $form = $(this);
		postMethod = $form.attr('method');
		postURL = $form.attr('action');
		var postData = $form.serialize();
		$.ajax({
			url: postURL,
			type: postMethod,
			data: postData,
			success: function(data) {
				layer.msg(data.text);
				if (data.code == 'success') {
					setTimeout(function() {
						parent.$('#tbContent').bootstrapTable(('refresh'));
						var index = parent.layer.getFrameIndex(window.name);
						parent.layer.close(index)
					}, 500)
				}
			}
		})
	})
}) 
</script>
</body>
</html>