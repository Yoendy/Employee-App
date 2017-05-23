$(document).ready(function(){
    $('#chkconComprobanteFiscal').click(
        function(){
            if ( $(this).is(':checked') )
             obtenerComprobanteFiscalValue();

            else
                $('#txtComprobanteFiscal').val();
                $('#txtComprobanteFiscal').hide();
        }
    );




});

function obtenerComprobanteFiscalValue(){
    $.ajax("/comprobantefiscal/obtenerComprobanteFiscal",{
        success: function(data){

            $('#txtComprobanteFiscal').val(data);
            $('#txtComprobanteFiscal').show();
        },error: function(){
            alert('Error Obteniendo Comprobante Fiscal!');
        }


    });
}
function editarContacto(formId, formAction){
    document.getElementById(formId).action = formAction;
    document.getElementById(formId).submit();
}

function editarDetalleFactura(formId, formAction){
    document.getElementById(formId).action = formAction;
    document.getElementById(formId).submit();
}


function editarMetodoPago(formId, formAction){
    document.getElementById(formId).action = formAction;
    document.getElementById(formId).submit();
}

function mostrarColumnas(tipoFactura){

    switch(tipoFactura){
        case "1":
            columnasFacturaAerea()
            break;
        case "2":

            columnasFacturaTransporte()
            break;
        case "3":
            columnasFacturaExcursionGeneral()
            break;
        case "4":
            columnasFacturaHotel()
            break;
        case "5":
            columnasFacturaParapente()
            break;

    }

}

function ocultarColumnas(){
    alert('OcultarColumnas');
}
function columnasFacturaAerea(){
    $("#thfechaRetorno").show();
    $("#tdfechaRetorno").show();
    $("#tdfechaSalida").show();
    $("#thfechaSalida").show();

    $("#tddestino").show();
    $("#thdestino").show();

    $("#tdlineaAerea").show();
    $("#thlineAerea").show();

    $("#tdnumeroBoletos").show();
    $("#thnumeroBoletos").show();

    $("#tdmonto").show();
    $("#thmonto").show();
}


function columnasFacturaTransporte(){
    $("#thfechaRetorno").show();
    $("#tdfechaRetorno").show();
    $("#tdfechaSalida").show();
    $("#thfechaSalida").show();

    $("#tddestino").show();
    $("#thdestino").show();

    $("#tdtipoAutobus").show();
    $("#thtipoAutobus").show();

    $("#tdtipoAlquiler").show();
    $("#thtipoAlquiler").show();

    $("#tdmonto").show();
    $("#thmonto").show();
}
function columnasFacturaExcursionGeneral(){
    $("#thfechaRetorno").show();
    $("#tdfechaRetorno").show();
    $("#tdfechaSalida").show();
    $("#thfechaSalida").show();

    $("#tddescripcion").show();
    $("#thdescripcion").show();

    $("#tdprecioPorPersona").show();
    $("#thprecioPorPersona").show();

    $("#tdcupos").show();
    $("#thcupos").show();

    $("#tdmonto").show();
    $("#thmonto").show();
}


function columnasFacturaHotel(){
    $("#thfechaRetorno").show();
    $("#tdfechaRetorno").show();
    $("#tdfechaSalida").show();
    $("#thfechaSalida").show();

    $("#tdtipohabitacion").show();
    $("#thtipoHabitacion").show();

    $("#tdtarifaPorPersona").show();
    $("#thtarifaporpersona").show();

    $("#tdpax").show();
    $("#thpax").show();

    $("#tdhabitaciones").show();
    $("#tdhabitaciones").show();

    $("#tdnoches").show();
    $("#thnoches").show();



    $("#tdmonto").show();
    $("#thmonto").show();
}


function columnasFacturaParapente(){
    $("#thfechaRetorno").show();
    $("#tdfechaRetorno").show();
    $("#tdfechaSalida").show();
    $("#thfechaSalida").show();

    $("#tddescripcion").show();
    $("#thdescripcion").show();

    $("#tdprecioPorPersona").show();
    $("#thprecioPorPersona").show();

    $("#tdcupos").show();
    $("#thcupos").show();


    $("#tdmonto").show();
    $("#thmonto").show();
}