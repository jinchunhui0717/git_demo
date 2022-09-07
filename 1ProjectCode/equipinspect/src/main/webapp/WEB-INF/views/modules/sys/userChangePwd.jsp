<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="${ctx}/sys/user/changepwd" method="post">
	<div class="box">
		<div class="box-body">
			<input type="hidden" id="inputId" name="id" value="${sysUser.id}">
			<div class="form-group">
				<label for="inputLogin_name"><spring:message code="account" /></label>
				<input type="text" class="form-control" id="inputLogin_name"
					name="loginName" value="${sysUser.login_name}" disabled="disabled">
			</div>
			<div class="form-group">
				<label for="inputName"><spring:message code="name" /></label> <input
					type="text" class="form-control" id="inputName" name="name"
					value="${sysUser.name}"  disabled="disabled">
			</div>
			<div class="form-group">
				<label for="inputPassword"><spring:message code="password" /></label>
				<input type="password" class="form-control" id="inputPassword"
					name="password" value="${sysUser.password}">
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
	<script type="text/javascript">
		$(function() {
			//$('[name="loginLocked"]').val('${sysUser.login_locked }');
			$('form')
					.bootstrapValidator(
							{
								fields : {
									password : {
										validators : {
											notEmpty : {
												message : '<spring:message code="msg_passwordnotempty"></spring:message>'
											},
											stringLength : {
												min : 6,
												max : 10,
												message : '<spring:message code="msg_length" arguments="6,10" />'
											},
										}

									}
								}
							})
					.on(
							'success.form.bv',
							function(e) {
								//防止表单以老式的方法提交  
								e.preventDefault();
								var $form = $(this);
								postMethod = $form.attr('method');
								postURL = $form.attr('action');
								var postData = $form.serialize();
								$
										.ajax({
											url : postURL,
											type : postMethod,
											data : postData,
											success : function(data) {
												layer.msg(data.text);
												if (data.code == 'success') {
													setTimeout(
															function() {
																parent
																		.$(
																				'#tbContent')
																		.bootstrapTable(
																				('refresh'));
																var index = parent.layer
																		.getFrameIndex(window.name); //获取窗口索引  
																parent.layer
																		.close(index);//关闭弹出的子页面窗口 
															}, 500);
												}
											}
										});
							});
		})
	</script>
</body>
</html>