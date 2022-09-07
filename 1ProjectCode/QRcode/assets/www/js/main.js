$(document).ready(function(event) {
	//* 响应物理后退按钮
	document.addEventListener("backbutton", eventBackButton, false);
		
	function eventBackButton(){
		navigator.notification.confirm("是否退出万圣节更衣室", onConfirm, "退出程序", "确认,取消");
	}
		
	function onConfirm(button) {
		if(button == 1){
			navigator.app.exitApp();
		}
	}
	 
});
