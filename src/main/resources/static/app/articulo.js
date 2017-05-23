$(document).ready(function() {
	
	
	Alert.showMessageBox(Alert.messageType.warning, "test");
});

function constructUrl(methodName, params) {
	var url = "/shopspot/json/articulo/" + methodName;

	if(params){
		for(var i = 0; i < params.length; i++) {
			url += "/" + params[i];
		}
	}

	return url;
}


function filtarArticulos() {
	var criteria = $("#txtBuscar").val();


	if(!criteria) return;

	var filtarArticuloUrl = constructUrl("consulta");

	$.ajax(filtarArticuloUrl, {
		global: false,
		success: function(articuloData) {
			if(articuloData.length > 0) {
				articuloData[0].nombre;
			}
			else {
				alert('');
			}
		}
	});

}