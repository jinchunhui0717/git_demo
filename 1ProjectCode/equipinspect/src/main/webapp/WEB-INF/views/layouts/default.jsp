<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="sitemesh"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<!DOCTYPE html>
<c:choose>
	<c:when test="${fns:isMobileDevice() }">
		<html manifest="${ctxStatic}/manifest/rafael.manifest">
	</c:when>
	<c:otherwise>
		<html>
	</c:otherwise>
</c:choose>
<meta charset="UTF-8">
<%-- <title><sitemesh:title /></title> --%>
<title><spring:message code="appname"/></title>
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

<style  type="text/css">
table td{word-break: keep-all;white-space:nowrap;}
table thead tr th{background-color:#E8E8E8;}
</style>
</head>
<body class="skin-blue layout-top-nav fixed">
	<div class="wrapper">
		<!-- 顶部导航栏 -->
		<header class="main-header">
			<nav class="navbar navbar-static-top">
				<div class="container-fluid">
					<div class="navbar-header">
						<a href="javascript:void(0)" class="navbar-brand"><b><spring:message code="appname"></spring:message></b></a>
						<button type="button" class="navbar-toggle collapsed"
							data-toggle="collapse" data-target="#navbar-collapse">
							<i class="fa fa-bars"></i>
						</button>
					</div>
					
					<!-- 内容导航 -->
					<div class="collapse navbar-collapse" id="navbar-collapse">
						<ul class="nav navbar-nav">
							<c:forEach items="${fns:getMenuList() }" var="menu"
								varStatus="status">
								<c:choose>
									<c:when test="${empty menu.parentId}">
										<c:choose>
											<c:when test="${status.last && status.first}">
												<li><a href="#">${menu.name }</a></li>
											</c:when>
											<c:when test="${status.first}">
												<li class="dropdown"><a href="#"
													class="dropdown-toggle" data-toggle="dropdown">${menu.name }
														<span class="caret"></span>
												</a>
													<ul class="dropdown-menu" role="menu">
											</c:when>
											<c:when test="${status.last}">
						</ul>
						</li>
						<li><a href="#">${menu.name }</a></li>
						</c:when>
						<c:otherwise>
							</ul>
							</li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown">${menu.name } <span class="caret"></span>
							</a>
								<ul class="dropdown-menu" role="menu">
						</c:otherwise>
						</c:choose>
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${empty menu.href}">
									<c:choose>
										<c:when test="${status.last}">
											<li>${menu.name }</li>
											</ul>
											</li>
										</c:when>
										<c:otherwise>
											<li>${menu.name }</li>
											<li class="divider"></li>
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${status.last}">

											<li><a href="${ctx }${menu.href}">${menu.name }</a></li>
											</ul>
											</li>
										</c:when>
										<c:otherwise>
											<li><a href="${ctx }${menu.href}">${menu.name }</a></li>
										</c:otherwise>
									</c:choose>
								</c:otherwise>
							</c:choose>
						</c:otherwise>
						</c:choose>
						</c:forEach>
						<%-- <li class="active"><a href="">分类1<span class="sr-only">(current)</span></a></li>
							<li><a href="#">分类2</a></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown">分类3 <span class="caret"></span></a>
								<ul class="dropdown-menu" role="menu">
									<li><a href="${ctx }/user/">下拉1</a></li>
									<li><a href="#">下拉2</a></li>
									<li class="divider"></li>
									<li><a href="#">下拉3</a></li>
									<li class="divider"></li>
									<li><a href="#">下拉4</a></li>
								</ul></li> --%>
						</ul>
						<!-- 登陆导航 -->
						<ul class="nav navbar-nav navbar-right">
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown">${fns:getSysUser().name} <span
									class="caret"></span></a>
								<ul class="dropdown-menu" role="menu">
								<li><a onclick="javascript:changepwd();"><spring:message code="changepwd"></spring:message></a></li>
									<li><a onclick="javascript:logout();"><spring:message code="signout"></spring:message></a></li>
									
								</ul></li>
						</ul>
					</div>
					<!-- /.navbar-collapse -->
				</div>
				<!-- /.container-fluid -->
			</nav>
		</header>
		<!-- 左侧菜单栏 -->
		<!--<aside class="main-sidebar">
    </aside>-->
		<!-- 中间内容 -->
		<div class="content-wrapper">
			<div style="padding-left: 15px; padding-right: 15px; padding-bottom: 1px;">
	          <ol class="breadcrumb" style="left: 10px;margin-bottom: 0;background: transparent;">
	          	<li><i class="fa fa-dashboard"></i></li>
	          	<c:forEach items="${fns:getCurNavigation() }" var="item">
	          		<li>${item }</li>
	          	</c:forEach>
	          </ol>
        	</div>
			
			<div class="content body"
				style="padding-left: 15px; padding-right: 15px; padding-bottom: 1px;">
				<!-- Main content -->
				<section>
					<sitemesh:body />
				</section>
			</div>
		</div>
		<!-- 底部标注 -->
		<footer class="main-footer" style="padding: 5px;">

			<strong>Copyright &copy; 2018-2025 <a href="#">org.rafael</a>.
			</strong> All rights reserved.
		</footer>
	</div>
	<script type="text/javascript">
		function logout() {
			if (IsMobile()) {
				if (window.rafael.isNetworkConnected() != true) {
					layer.msg('<spring:message code="msg_no_connect_server"/>', {
						
						offset : 'b'
					});
					
				}else {
					window.location.href="${ctx }/logout";
				}
			}
			else {
				window.location.href="${ctx }/logout";
			}
			return false;
		}
		
		function changepwd() {
			if (IsMobile()) {
				if (window.rafael.isNetworkConnected() != true) {
					layer.msg('<spring:message code="msg_no_connect_server"/>', {
						
						offset : 'b'
					});
					
				}else {
					layer.open({
						title:' ',
						type:2,
						content:'${ctx}/sys/user/changepwd?id=${fns:getSysUser().id}',
						success : function(layero, index) {
							layer.iframeAuto(index);
						}
					});
				}
			}
			else {
				layer.open({
					title:' ',
					type:2,
					content:'${ctx}/sys/user/changepwd?id=${fns:getSysUser().id}',
					success : function(layero, index) {
						layer.iframeAuto(index);
					}
				});
			}
			return false;
		}
		var global_layer_msg_id;
		$(document).ajaxSend(function(evt, request, settings) {
			var shade;
			if (settings.type=='GET') {
				shade=false;
			} else {
				shade=0.01;
			}
			if (IsMobile()) {
				if (window.rafael.isNetworkConnected() != 1) {
					layer.msg('<spring:message code="msg_no_connect_server"/>', {
						offset : 'b'
					});
					request.abort();
				} else {
					global_layer_msg_id = layer.msg('<spring:message code="msg_wait"/>', {
						icon : -1,
						shade : shade,
						anim : -1,//关闭动画，否则也会导致大小忽闪的问题
						area : [ '150px', '50px' ],//固定大小，解决大小忽闪的问题
						time : false
					//取消自动关闭（默认2秒自动关闭）
					});
				}

			} else {
				global_layer_msg_id = layer.msg('<spring:message code="msg_wait"/>', {
					icon : -1,
					shade : shade,
					anim : -1,//关闭动画，否则也会导致大小忽闪的问题
					area : [ '150px', '50px' ],//固定大小，解决大小忽闪的问题
					time : false
				//取消自动关闭（默认2秒自动关闭）
				});
			}

		});
		$(document).ajaxComplete(function(event, request, settings) {
			layer.close(global_layer_msg_id);
		});
		$.ajaxSetup({
			//解决ajax 302 ,一般都是session超时引起的
			complete : function(XMLHttpRequest, textStatus) {
				layer.close(global_layer_msg_id);
				if (textStatus == "parsererror") {
					window.location.href = '${ctx}/login';
				} else if (textStatus == "error") {
					layer.msg('<spring:message code="msg_timeout"/>');
				}
			}
		});

		function IsMobile() {
			if (window.rafael==undefined) {
				return false;
			}else{
				return true;
			}
		}
		/* window.applicationCache.onchecking = function (e) {
			console.info('1检查更新中');
		    //console.info(e);
		}
		window.applicationCache.oncached = function(e) {
			console.info('4.1缓存成功');
			console.info(e);
		window.applicationCache.onnoupdate = function (e) {
			console.info('2.1不更新');
		} */
		var progresslayer;
		window.applicationCache.ondownloading = function(e) {
			progresslayer = layer
					.open({
						type : 1,
						title : false,
						offset:'b',
						area:['100%','20px'],
						closeBtn : 0,
						shade : 0.01,//遮罩层的透明度
						content : '<div class="progress progress-striped active" >'
								+ '  <div id="myprogressbar" class="progress-bar" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 100%">'
								+ '    <span><spring:message code="msg_lixian"/></span>'
								+ '  </div>' + '</div>'
					});

		}
		window.applicationCache.addEventListener('progress', function(e) {
			$('#myprogressbar').css('width',
					Math.round((e.loaded / e.total) * 100) + '%')
		}, false);
		window.applicationCache.oncached=function(e) {
			layer.close(progresslayer);
		};
		window.applicationCache
				.addEventListener(
						'updateready',
						function(e) {
							if (window.applicationCache.status == window.applicationCache.UPDATEREADY) {
								window.applicationCache.swapCache();
								window.location.reload();
								layer.close(progresslayer);
							}
						}, false);
		window.applicationCache.onerror = function (e) {
			layer.close(progresslayer);
			
		}
		
		
	</script>
</body>
</html>