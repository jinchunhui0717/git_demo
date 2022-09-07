// 打卡摄像头拍照
function takePicture(url,uploadStart,success,error) {
	var destinationType = navigator.camera.DestinationType;
	var options = {
		quality : 30,
		destinationType : destinationType.FILE_URI
	};
	navigator.camera.getPicture(function(data) {
		var params = uploadStart();
		uploadPicture(data,params,url,success,error);
	}, null, options);
}
// 上传图片到服务器 - 暂时未处理上传失败的情况
function uploadPicture(data,params, url,success,error) {
	var options = new FileUploadOptions();
	options.fileKey = "file";
	options.mimeType = "image/jpeg";
	options.fileName = data.substr(data.lastIndexOf('/') + 1);
	//自定义参数
    options.params = params;
	
	var ft = new FileTransfer();
	//ft.onprogress = showUploadingProgress;
	//navigator.notification.progressStart("", "当前上传进度");
	ft.upload(data, url, 
			success
			//function() {
		//navigator.notification.progressStop();
	//}
, error, options);
}
// 显示上传进度
function showUploadingProgress(progressEvt) {
	if (progressEvt.lengthComputable) {
		$('#myprogressbar').css('width',Math.round((progressEvt.loaded / (progressEvt.total)) * 100)+'%')
		//navigator.notification.progressValue(Math.round((progressEvt.loaded / (progressEvt.total)) * 100));
	}
}