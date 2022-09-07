<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="sitemesh"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
<link rel="stylesheet" href="${ctxStatic}/dist/css/AdminLTE.css">
<!-- AdminLTE Skins. We have chosen the skin-blue for this starter
          page. However, you can choose any other skin. Make sure you
          apply the skin class to the body tag so the changes take effect.
    -->
<link rel="stylesheet"
	href="${ctxStatic}/dist/css/skins/skin-blue.min.css">
<link href="${ctxStatic}/plugins/bootstrapvalidator/css/bootstrapValidator.min.css" rel="stylesheet" />
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
    <script src="${ctxStatic}/plugins/html5shiv/html5shiv.min.js"></script>
    <script src="${ctxStatic}/plugins/respond/respond.min.js"></script>
    <![endif]-->
<script src="${ctxStatic}/plugins/jquery1.12.4/jquery.min.js"></script>
<!-- Bootstrap 3.3.5 -->
<script src="${ctxStatic}/bootstrap/js/bootstrap.min.js"></script>
<script src="${ctxStatic}/plugins/bootstrapvalidator/js/bootstrapValidator.min.js"></script>
<!-- AdminLTE App -->
<script src="${ctxStatic}/dist/js/app.min.js"></script>
<script src="${ctxStatic}/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<script src="${ctxStatic}/plugins/layer-v3.1.1/src/layer.js"></script>
<sitemesh:head />
</head>
<body class="skin-blue">
	<section>
		<sitemesh:body />
	</section>
	<script type="text/javascript">
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