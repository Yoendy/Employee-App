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
import org.springframework.web.bind.annotation.ResponseBody;

import com.Sistema_Empleados.domain.Puesto;
import com.Sistema_Empleados.dto.PuestoDTO;
import com.Sistema_Empleados.service.PuestoService;
import com.Sistema_Empleados.validation.PuestoValidator;

import scala.Console;

@RequestMapping("/puestos")
@Controller
public class PuestoController {

	private PuestoService puestoService;
	private PuestoValidator puestoValidator;

	@Autowired
	public PuestoController(PuestoService puestoService,PuestoValidator puestoValidator){
		
		this.puestoService= puestoService;
		this.puestoValidator = puestoValidator;
	}
	
	@RequestMapping("")
	public String list(Model model) {
		model.addAttribute("puestos", puestoService.findAll());
		model.addAttribute("puestoDTO", new PuestoDTO());
		return "puestos/Consulta";
	}

	@RequestMapping("crear")
	public String crear(Model model) {
		model.addAttribute("puesto", new Puesto());
		return "puestos/Formulario";
	}

	@RequestMapping(value = "guardar", method = RequestMethod.POST)
	public String guardar(@Valid Puesto puesto, BindingResult result) throws IOException {
		
		puestoValidator.validate(puesto, result);
		
		 if (result.hasErrors()) 
		 {
	            return "puestos/Formulario";
	     }
		
		if(puestoService.exist(puesto.getId()))
			puestoService.update(puesto);
		else
			puestoService.save(puesto);

		return "redirect:/puestos/";
	}

	@RequestMapping("editar/{id}")
	public String editar(@PathVariable int id, Model model) {
		Puesto puesto = puestoService.findOne(id);
		model.addAttribute("puesto", puesto);
		return "puestos/Formulario";
	}
	
	@RequestMapping(value="/buscar",method = RequestMethod.POST)
	public String buscar(PuestoDTO puestoDTO,Model model){
		List<Puesto> puestos = new ArrayList<>();
		try{
			puestos =	puestoService.searchAll(puestoDTO.getNombre());
			model.addAttribute("puestos", puestos );
			
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return "puestos/Consulta";
	}
	
	@RequestMapping(value="/dashboard",method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Puesto> Dashboard()
	{
		List<Puesto> puestos = new ArrayList<>();
		try
		{
			puestos =	puestoService.findAll();
			
			
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return puestos;
	}
	
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void export(HttpServletResponse response) 
    {
    	puestoService.export(response);
    }
}



