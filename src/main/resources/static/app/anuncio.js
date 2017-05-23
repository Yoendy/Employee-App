
$(document).ready(function() {
	//Load Async fragment
	   $("#tableResul").load("/shopspot/anuncio/json/Consulta");
});

$("#formSearch").submit(function(event) {
	 //Ajax request with form complete
	 event.preventDefault();
        $.get("/customerweb/save", $("#formSearch").serialize()) //Serialize looks good name=textInNameInput&&telefon=textInPhoneInput---etc
        .done(function(data) {
        	alert(data);
        	$('#tableResul').append(data);
        });
        return false;
    });

