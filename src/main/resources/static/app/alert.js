var Alert = {};

Alert.messageType = {
	success: 1,
	warning: 2,
	danger: 3,
	info: 4
}

Alert.showLoading = function() {

    var loadingFrame= $("<div></div>").addClass("page-loading-frame").addClass("v2");
    
    var loadingFrameContent = '<div class="page-loading-loader"><div class="dot1"></div><div class="dot2"></div></div>';
    
    $("body").append(loadingFrame.html(loadingFrameContent));
}

Alert.hideLoading = function() {
	 if($(".page-loading-frame").length > 0) {
	 	$(".page-loading-frame").remove();
     }
}

Alert.showMessageBox = function (messageType, messageText, messageTitle) {
	$("#message-box-generic").removeClass("message-box-success")
							 .removeClass("message-box-warning")
							 .removeClass("message-box-info")
							 .removeClass("message-box-danger");
	
	$("#message-box-generic .fa").removeClass("fa-globe")
								.removeClass("fa-check")
								.removeClass("fa-warning")
								.removeClass("fa-times")
								.removeClass("fa-info");
	
	switch(messageType) {
		case Alert.messageType.success:
			messageTitle = messageTitle || "Operación exitosa";
			$("#message-box-generic").addClass("message-box-success");
			$("#message-box-generic .fa").addClass("fa-check");
		break;
		case Alert.messageType.warning:
			messageTitle = messageTitle || "Advertencia";
			$("#message-box-generic").addClass("message-box-warning");
			$("#message-box-generic .fa").addClass("fa-warning");
		break;
		case Alert.messageType.danger:
			messageTitle = messageTitle || "Error";
			$("#message-box-generic").addClass("message-box-danger");
			$("#message-box-generic .fa").addClass("fa-times");
		break;
		case Alert.messageType.info:
			messageTitle = messageTitle || "Información";
			$("#message-box-generic").addClass("message-box-info");
			$("#message-box-generic .fa").addClass("fa-info");
		break;
		default:
			messageTitle = messageTitle || "Mensaje";
			$("#message-box-generic .fa").addClass("fa-globe");
		break;
	}
	
	$("#message-box-generic .mb-title-text").text(messageTitle);
	$("#message-box-generic .mb-content p").text(messageText);
	$("#message-box-generic").addClass("open");
	$("#message-box-generic button").focus();
	
}


Alert.showNotificationMessage = function(message,messageType, timeout){
	var messageClass;
	switch(messageType) {
		case Alert.messageType.success:
			messageClass =	"success";
			break;
		case Alert.messageType.warning:
			messageClass = "warning";
			break;
		case Alert.messageType.danger:
			messageClass = "error";
			break;
		case Alert.messageType.info:
			messageClass = "information";
			break;
		default:
			messageClass = "";
			break;
	}
    var option = {
        text: message,
        layout: 'topRight',
        type: messageClass,
        timeout: timeout || 10000
    };
    console.log(option);

	jQuery.notyRenderer.init(option);
}