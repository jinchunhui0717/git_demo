<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="${ctxStatic}/android/cordova.js"></script>
<script src="${ctxStatic}/android/zepto.min.js"></script>
<script src="${ctxStatic}/android/when.js"></script>
<script src="${ctxStatic}/android/upload_pic.js"></script>
<script src="${ctxStatic}/android/barcodescanner.js"></script>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<script type="text/javascript">
	$(function() {
		$("#camera").click(function() {
			var layer_msg_id;
			takePicture('<%=basePath%>/file/upload',function() {
										layer_msg_id = layer.msg(
																'正在上传图片...',
														{
															icon : -1,
															shade : 0,
															area : [ '150px',
																	'50px' ],//固定大小，解决大小忽闪的问题 
															time : false
														//取消自动关闭（默认2秒自动关闭）
														});
									}, function() {
										/* var la='#layui-layer'+layer_msg_id+' > .layui-layer-content';
										$(la).html('上传完成');
										setTimeout(function(){ layer.close(layer_msg_id); },300); */
										layer.close(layer_msg_id);
										layer.msg('上传完成');
									}, function() {
										layer.msg('上传失败,请重新拍照');
									});
						});
		$('#scanner').click(function() {
			window.plugins.barcodeScanner.scan(function(result) {
				layer.msg(result.text);
			}, function(error) {

			});
		});
	})
</script>
<style type="text/css">
div {
	height: 50px;
	line-height: 50px;
	vertical-align: center;
	text-align: center;
	text-align: center;
}
</style>
</head>
<body>
	<div>
		<a id="camera" class="btn btn-app"> <i class="fa fa-camera"></i>
			拍照
		</a> <a id="scanner" class="btn btn-app"> <i class="fa fa-barcode"></i>扫码
		</a>
	</div>
</body>
</html>