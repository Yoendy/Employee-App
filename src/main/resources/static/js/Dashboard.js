window.chartColors = {
	red: 'rgb(255, 99, 132)',
	orange: 'rgb(255, 159, 64)',
	yellow: 'rgb(255, 205, 86)',
	green: 'rgb(75, 192, 192)',
	blue: 'rgb(54, 162, 235)',
	purple: 'rgb(153, 102, 255)',
	grey: 'rgb(231,233,237)'
}; 

function getPuestos(item) {
	var puesto  = new Object();;
	puesto = item.nombre;
	return puesto;
}

function getSalariosMinimos(item) {
	var puesto  = new Object();;
	puesto = item.salarioMinimo;
	return puesto;
}

function getCantidadEmpleado(item) {
	var puesto  = new Object();;
	puesto = item.cantidadEmpleado;
	return puesto;
}

function getSalariosMaximos(item) {
	var puesto  = new Object();;
	puesto = item.salarioMaximo;
	return puesto;
}

$(function(){
	
	puestosSalarios();
	empleadosDepartamentos();
	empleadosPuesto();
})


function puestosSalarios()
{
	$.get("/puestos/dashboard", 
			function(response)
			{

			}).success(function(response)
			{
				
					var puestos = response.map(getPuestos);
					var salariosMinimos = response.map(getSalariosMinimos);
					var salariosMaximos = response.map(getSalariosMaximos);
					console.log(puestos);
					console.log(salariosMinimos);
					console.log(salariosMaximos);
			        
					var color = Chart.helpers.color;
			        var data = {
			            labels: puestos,
			            datasets: [{
			                label: 'Salario Minimo',
			                backgroundColor: color(window.chartColors.red).alpha(0.5).rgbString(),
			                borderColor: window.chartColors.red,
			                borderWidth: 1,
			                data: salariosMinimos
			            	}, 
			            	{
			                label: 'Salario Maximo',
			                backgroundColor: color(window.chartColors.blue).alpha(0.5).rgbString(),
			                borderColor: window.chartColors.blue,
			                borderWidth: 1,
			                data: salariosMaximos
			            }]

			        };

			        
			            var ctx = document.getElementById("canvas").getContext("2d");
			            window.myBar = new Chart(ctx, {
			                type: 'bar',
			                data: data,
			                options: {
			                    responsive: true,
			                    legend: {
			                        position: 'top',
			                    },
			                    title: {
			                        display: false,
			                        text: 'Salarios por Posicion'
			                    }
			                }
			            });			        
			        
	            });
}

function empleadosDepartamentos()
{

	$.get("/empleados/departamentos", 
			function(response)
			{

			}).success(function(response)
			{
				
				var departamentos = response.map(getPuestos);
				var cantidades = response.map(getCantidadEmpleado);
				console.log(departamentos);
				console.log(cantidades);

				var color = Chart.helpers.color;
			    var data = {
			            labels: departamentos,
			            datasets: [{
			                label: 'Cantidad de Empleados',
			                backgroundColor: color(window.chartColors.blue).alpha(0.5).rgbString(),
			                borderColor: window.chartColors.blue,
			                borderWidth: 1,
			                data: cantidades
			            	}]

			        };

			            var ctx1 = document.getElementById("canvas-empleados-departamento").getContext("2d");
			            window.EmpleadosPorDepartamentos = new Chart(ctx1, {
			                type: 'bar',
			                data: data,
			                options: {
			                    responsive: true,
			                    legend: {
			                        position: 'top',
			                    },
			                    title: {
			                        display: false,
			                        text: 'Salarios por Posicion'
			                    }
			                }
			            });

			     
	            });
}

function empleadosPuesto()
{
	$.get("/empleados/puestos", 
			function(response)
			{

			}).success(function(response)
			{
				
				var posiciones = response.map(getPuestos);
				var quantity = response.map(getCantidadEmpleado);
				console.log(posiciones);
				console.log(quantity);

				var color = Chart.helpers.color;
			    var data = {
			            labels: posiciones,
			            datasets: [{
			                label: 'Cantidad de Empleados',
			                backgroundColor: color(window.chartColors.green).alpha(0.5).rgbString(),
			                borderColor: window.chartColors.green,
			                borderWidth: 1,
			                data: quantity
			            	}]

			        };

			       

	            var ctx2 = document.getElementById("canvas-empleados-puestos").getContext("2d");
	            window.EmpleadosPorPuesto = new Chart(ctx2, {
	                type: 'bar',
	                data: data,
	                options: {
	                    responsive: true,
	                    scales : {
	                        yAxes : [{
	                            ticks : {
	                                beginAtZero : true
	                            }   
	                        }]
	                    },
	                    legend: {
	                        position: 'top',
	                    },
	                    title: {
	                        display: false,
	                        text: 'Salarios por Posicion'
	                    }
	                    
	                }
	            });
			     
	            });
}


var MONTHS = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];




