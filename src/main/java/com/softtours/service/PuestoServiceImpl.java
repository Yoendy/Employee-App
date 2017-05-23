package com.softtours.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtours.domain.Puesto;
import com.softtours.repository.PuestoRepository;
import com.softtours.repository.PuestoRepositoryImpl;
import com.softtours.util.PuestoWriter;

@Service
public class PuestoServiceImpl implements PuestoService {
	
	Puesto puesto;
	
	@Autowired
	private PuestoRepository puestoRepository;
	
	public PuestoServiceImpl(){
		this.puestoRepository = new PuestoRepositoryImpl();
	}

	@Override
	public List<Puesto> findAll() {
		return puestoRepository.findAll();
	}

	@Override
	public void save(Puesto puesto) {
		puestoRepository.save(puesto);
	}

	@Override
	public void update(Puesto puesto) {
		puestoRepository.update(puesto);
		
	}

	@Override
	public Puesto findOne(int id) {
		return puestoRepository.findOne(id);
	}

	@Override
	public void delete(int id) {
		puestoRepository.delete(id);
		
	}

	@Override
	public boolean exist(int id) {
		return puestoRepository.exist(id);
	}

	@Override
	public List<Puesto> searchAll(String filtro) {
		return puestoRepository.searchAll(filtro);
	}

	@Override
	public void export(HttpServletResponse response) {

			PuestoWriter puestoWriter = new PuestoWriter();
			puestoWriter.generateWord(response, findAll());
	}
	


}
