package com.softtours.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softtours.domain.Empleado;
import com.softtours.domain.Puesto;
import com.softtours.dto.DepartamentoDTO;
import com.softtours.dto.EmpleadoDTO;
import com.softtours.dto.PuestoDTO;
import com.softtours.service.DepartamentoService;
import com.softtours.service.EmpleadoService;
import com.softtours.service.PuestoService;
import com.softtours.validation.DepartamentoValidator;
import com.softtours.validation.EmpleadoValidator;

import scala.Console;

@RequestMapping("/empleados")
@Controller
public class EmpleadoController {
	
	private EmpleadoService empleadoService;
	private PuestoService puestoService;
	private DepartamentoService departamentoService;
	private EmpleadoValidator empleadoValidator;
	
	@Autowired
	public EmpleadoController(EmpleadoService empleadoService,PuestoService puestoService, DepartamentoService departamentoService, EmpleadoValidator empleadoValidator)
	{
		this.empleadoService = empleadoService;
		this.puestoService = puestoService;
		this.departamentoService = departamentoService;
		this.empleadoValidator = empleadoValidator;
	}
	
	@RequestMapping("")
	public String list(Model model) {
		model.addAttribute("empleados", empleadoService.findAll());
		model.addAttribute("empleadoDTO", new EmpleadoDTO());

		return "empleados/Consulta";
	}

	@RequestMapping("crear")
	public String crear(Model model) {
		model.addAttribute("empleado", new Empleado());
		model.addAttribute("puestos", puestoService.findAll());
		model.addAttribute("departamentos", departamentoService.findAll());
		model.addAttribute("empleados", empleadoService.findAll());
		return "empleados/Formulario";
	}

	@RequestMapping(value = "guardar", method = RequestMethod.POST)
	public String guardar(@Valid Empleado empleado, BindingResult result,Model model) throws IOException {
		model.addAttribute("puestos", puestoService.findAll());
		model.addAttribute("departamentos", departamentoService.findAll());
		model.addAttribute("empleados", empleadoService.findAll());
		empleadoValidator.validate(empleado, result);
		
		 if (result.hasErrors()) 
		 {
	            return "empleados/Formulario";
	     }
		
		if(empleadoService.exist(empleado.getId()))
			empleadoService.update(empleado);
		else
			empleadoService.save(empleado);

		return "redirect:/empleados/";
	}

	@RequestMapping("editar/{id}")
	public String editar(@PathVariable int id, Model model) {
		model.addAttribute("puestos", puestoService.findAll());
		model.addAttribute("departamentos", departamentoService.findAll());
		model.addAttribute("empleados", empleadoService.findAll());
		Empleado empleado = empleadoService.findOne(id);
		model.addAttribute("empleado", empleado);
		return "empleados/Formulario";
	}
	
	@RequestMapping(value="/buscar",method = RequestMethod.POST)
	public String buscar(EmpleadoDTO empleadoDTO,Model model){
		List<Empleado> empleados = new ArrayList<>();
		try{
			empleados =	empleadoService.searchAll(empleadoDTO.getNombre());
			model.addAttribute("empleados",empleados );
			
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return "empleados/Consulta";
	}
	
	@RequestMapping(value="/departamentos",method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<DepartamentoDTO> Departamentos()
	{
		return empleadoService.findAllEmpleadosByDepartamento();
	}
	
	@RequestMapping(value="/puestos",method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<PuestoDTO> Puestos()
	{
		return empleadoService.findAllEmpleadosByPuesto();
	}
	
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void export(HttpServletResponse response) 
    {
    	empleadoService.export(response);
    }
}
