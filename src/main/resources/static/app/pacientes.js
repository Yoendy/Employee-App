function addContacto(){
	
       $.get("/pacientes/contactos/add", $("#formPaciente").serialize()) //Serialize looks good name=textInNameInput&&telefon=textInPhoneInput---etc
       .done(function(data) {
       	alert(data);
       	$('#tableResul').append(data);
       });
       return false;
	
}


