package com.Sistema_Empleados.bootstrap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

	private Logger log = Logger.getLogger(DataLoader.class);

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
//		Cliente p1 = new Cliente();
//
//		p1.setNombre("Pedro Pablo");
//		p1.setApellido("Leon Jaramillo");
//		p1.setDni("El Capo");
//		p1.setDireccion("dad");
//		p1.setEstado(1);
//		p1.setTipoDni(1L);
//
//		Contacto c = new Contacto();
//		c.setNombre("ppleon@yahoo.com");
//		c.setTipoContacto("email");
//
//		Contacto c2 = new Contacto();
//		c2.setNombre("809-908-5555");
//		c2.setTipoContacto("celular");
//		p1.getContactos().add(c);
//		p1.getContactos().add(c2);
//
//		clienteRepository.save(p1);
//
//		log.info("Paciente - Id " + p1.getId());
//
//		// -----------------------------
//
//		Proveedor proveedor = new Proveedor();
//		proveedor.setNombre("Hard Rock Hotel");
//		proveedor.setTipoDni(1L);
//		proveedor.setDni("1-050-5045");
//		proveedor.setDireccion("Blue Mall");
//		proveedor.setEstado(1);
//
//		Contacto c3 = new Contacto();
//		c3.setNombre("809-555-6677");
//		c3.setTipoContacto("telefono");
//
//		Contacto c4 = new Contacto();
//		c4.setNombre("info@hardrockhotel.com");
//		c4.setTipoContacto("email");
//
//		proveedor.getContactos().add(c3);
//		proveedor.getContactos().add(c4);
//
//		proveedorRepository.save(proveedor);
//		log.info("Proveedor id - " + proveedor.getId());
	}

}
