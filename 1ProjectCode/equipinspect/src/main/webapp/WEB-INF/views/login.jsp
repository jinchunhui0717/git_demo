<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<!-- ie渲染模式兼容性 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<!-- 缩放的相关设置 -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<!-- Bootstrap 3.3.5 -->
<link rel="stylesheet"
	href="${ctxStatic}/bootstrap/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="${ctxStatic}/font-awesome-4.7.0/css/font-awesome.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="${ctxStatic}/dist/css/AdminLTE.min.css">
<!-- iCheck -->
<link rel="stylesheet"
	href="${ctxStatic}/plugins/iCheck/square/blue.css">
    <link href="${ctxStatic}/plugins/bootstrapvalidator/css/bootstrapValidator.min.css" rel="stylesheet" />
<!-- AdminLTE Skins. We have chosen the skin-blue for this starter
          page. However, you can choose any other skin. Make sure you
          apply the skin class to the body tag so the changes take effect.
    -->
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
    <script src="${ctxStatic}/plugins/html5shiv/html5shiv.min.js"></script>
    <script src="${ctxStatic}/plugins/respond/respond.min.js"></script>
    <![endif]-->
</head>
<body class="hold-transition login-page" style="background-color: rgb(96,175,236);">

	<div class="login-box">
		<div class="login-logo">
			<%-- <img alt=" " src="${ctxStatic }/img/logo.jpg" width="35px"> --%>
			<a href="javascript:void(0)" style="color: #ffffff"><b><spring:message code="appname"></spring:message></b></a>
		</div>
		<!-- /.login-logo -->
		<div class="login-box-body">
			
			<p class="login-box-msg"></p>
			<form action="${ctx }/login" method="post">
				<div class="form-group has-feedback">
					<input type="text" id="username" name="username" class="form-control"
						placeholder='<spring:message code="username"></spring:message>' value="<shiro:principal/>"> <span
						class="glyphicon glyphicon-user form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<input type="password" name="password" class="form-control"
						placeholder="<spring:message code="password"></spring:message>"> <span
						class="glyphicon glyphicon-lock form-control-feedback"></span>
					<input type="hidden" name="mobileflag" id="mobileflag">
				</div>
				<div class="form-group has-feedback">
					<select id="selLanguage" class="form-control">
					<option value="zh_CN">中文</option>
					<option value="en_US">English</option>
					</select>
				</div>
				<div class="row">
					<div class="col-xs-8">
						<div class="checkbox icheck">
							<label> <input type="checkbox" name="rememberMe">
								<spring:message code="rememberMe"></spring:message>
							</label>
						</div>
					</div>
					<div class="col-xs-4">
						<button id="btnLogin" type="submit" class="btn btn-primary btn-block btn-flat">
							<spring:message code="login"></spring:message></button>
					</div>
				</div>
				<div class="row">
					<div id="divMessage" class="col-xs-12">${error}</div>
				</div>
			</form>


			<!-- <a href="#">忘记密码?</a><br> 
			<a href="register.html"
				class="text-center">账户注册</a> -->

		</div>
		<!-- /.login-box-body -->
	</div>
	<!-- /.login-box -->

	<!-- jQuery 2.1.4 -->
	<script src="${ctxStatic}/plugins/jquery1.12.4/jquery.min.js"></script>
	<!-- Bootstrap 3.3.5 -->
	<script src="${ctxStatic}/bootstrap/js/bootstrap.min.js"></script>
	<!-- iCheck -->
	<script src="${ctxStatic}/plugins/iCheck/icheck.min.js"></script>
	<script src="${ctxStatic}/plugins/bootstrapvalidator/js/bootstrapValidator.min.js"></script>
	<script src="${ctxStatic}/plugins/cookie/jquery.cookie.js"></script>
	<script>
		$(function() {
			if (IsMobile()) {
				
			$('#btnLogin').click(function() {
				if ($.cookie('loginname')==$('#username').val() && $.cookie('lang')==$('#selLanguage').val()) {

				}else{
					$.ajax({
						url:'${ctx}/file/updatemanifest',
						type:'GET',
						async:false
					}); 
				}
				$.cookie('loginname', $('#username').val(), { expires: 360, path: '/' });
				$.cookie('lang', $('#selLanguage').val(), { expires: 360, path: '/' });
			})
			
			}
			
			$('#selLanguage').val('${pageContext.response.locale }');
			$('#selLanguage').change(function() {
				window.location.href='?locale='+$('#selLanguage').val();
			});
			$('input').iCheck({
				checkboxClass : 'icheckbox_square-blue',
				radioClass : 'iradio_square-blue',
				increaseArea : '20%' // optional
			});
			if (IsMobile()) {
				$('#mobileflag').val('yes');
			}
			$('form').bootstrapValidator({
				fields:{
					username:{
						 validators: {
		                        notEmpty: {
		                            message: '<spring:message code="msg_accountnotempty"></spring:message>'
		                        }
		                    }
					},
					password:{
						 validators: {
		                        notEmpty: {
		                            message: '<spring:message code="msg_passwordnotempty"></spring:message>'
		                        }
		                    }
					}
				}
			});
		});
		function IsMobile() {
			if (window.rafael==undefined) {
				return false;
			}else{
				return true;
			}
		}
	</script>
</body>
</html>
