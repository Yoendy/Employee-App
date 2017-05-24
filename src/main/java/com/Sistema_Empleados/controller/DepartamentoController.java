package com.Sistema_Empleados.controller;

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

import com.Sistema_Empleados.domain.Departamento;
import com.Sistema_Empleados.dto.DepartamentoDTO;
import com.Sistema_Empleados.service.DepartamentoService;
import com.Sistema_Empleados.service.EmpleadoService;
import com.Sistema_Empleados.validation.DepartamentoValidator;

import scala.Console;

@RequestMapping("/departamentos")
@Controller
public class DepartamentoController {

	private DepartamentoService departamentoService;
	private EmpleadoService empleadoService;
	private DepartamentoValidator departamentoValidator;

	@Autowired
	public DepartamentoController(DepartamentoService departamentoService,EmpleadoService empleadoService,DepartamentoValidator departamentoValidator){
		
		this.departamentoService = departamentoService;
		this.empleadoService = empleadoService;
		this.departamentoValidator = departamentoValidator;
	}
	
	@RequestMapping("")
	public String list(Model model) {
		model.addAttribute("departamentos", departamentoService.findAll());
		model.addAttribute("departamentoDTO", new DepartamentoDTO());
		return "Departamentos/Consulta";
	}

	@RequestMapping("crear")
	public String crear(Model model) {
		model.addAttribute("departamento", new Departamento());
		model.addAttribute("empleados", empleadoService.findAll());
		return "Departamentos/Formulario";
	}

	@RequestMapping(value = "guardar", method = RequestMethod.POST)
	public String guardar(@Valid Departamento departamento, BindingResult result,Model model) throws IOException {
		model.addAttribute("empleados", empleadoService.findAll());
		departamentoValidator.validate(departamento, result);
		
		System.out.println(result);
		 if (result.hasErrors()) 
		 {
	        return "Departamentos/Formulario";
	     }
		
		if(departamentoService.exist(departamento.getId()))
			departamentoService.update(departamento);
		else
			departamentoService.save(departamento);
		Console.println("Id:");
		Console.println(departamento.getId());
		return "redirect:/departamentos/";
	}

	@RequestMapping("editar/{id}")
	public String editar(@PathVariable int id, Model model) {
		Departamento departamento = departamentoService.findOne(id);
		model.addAttribute("empleados", empleadoService.findAll());
		model.addAttribute("departamento", departamento);
		return "Departamentos/Formulario";
	}
	
	@RequestMapping(value="/buscar",method = RequestMethod.POST)
	public String buscar(DepartamentoDTO departamentoDTO,Model model){
		List<Departamento> departamentos = new ArrayList<>();
		try{
			departamentos =	departamentoService.searchAll(departamentoDTO.getNombre());
			model.addAttribute("departamentos",departamentos );
			
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return "Departamentos/Consulta";
	}
	
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void export(HttpServletResponse response) 
    {
    	departamentoService.export(response);
    }
}
