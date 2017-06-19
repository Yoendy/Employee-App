package com.Sistema_Empleados.util;

import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import com.Sistema_Empleados.dto.DepartamentoDTO;
import com.Sistema_Empleados.dto.EmpleadoDTO;

public class EmpleadoWriter {
	private XWPFDocument document;

	public void generateWord(HttpServletResponse response, List<EmpleadoDTO> empleados)
	{
		try
		{
			document = new XWPFDocument();
			
			//Write Document in servlet
			ServletOutputStream outputStream = response.getOutputStream();
			
			//Create table
			XWPFTable table = document.createTable();
			
			//Create header
			createHeader(table);
			
			//Create Body
			createBody(table, empleados);
			
			// 6. Set the response properties
			String fileName = "Empleados.doc";
			response.setHeader("Content-Disposition", "inline; filename=" + fileName);
			  
			// Make sure to set the correct content type
			response.setContentType("application/msword");
			
			//Return to Response
			document.write(outputStream);
			outputStream.flush();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	private void createHeader(XWPFTable table)
	{
		//Create header
		XWPFTableRow row = table.getRow(0);

		addCell(row.getCell(0), "Nombre Completo", true);
		addCell(row.addNewTableCell(), "Email", true);
		addCell(row.addNewTableCell(), "Posicion", true);
		addCell(row.addNewTableCell(), "Departamento", true);
		addCell(row.addNewTableCell(), "Supervisor", true);
		addCell(row.addNewTableCell(), "Salario", true);
	}
	
	private void createBody(XWPFTable table, List<EmpleadoDTO> empleados)
	{
		//Create body
		for(EmpleadoDTO empleado: empleados)
		{
			XWPFTableRow row = table.createRow();
			
			addCell(row.getCell(0),empleado.getNombreCompleto(),false);
			addCell(row.getCell(1),empleado.getEmail(),false);
			addCell(row.getCell(2),empleado.getPuesto(),false);
			addCell(row.getCell(3),empleado.getDepartamento(),false);
			addCell(row.getCell(4),empleado.getSupervisor(),false);
			addCell(row.getCell(5),empleado.getSalario().toString(),false);
		}
		
	}
	
	
	private void addCell(XWPFTableCell cell, String texto, Boolean mayuscula)
	{
		XWPFParagraph parrafo = cell.addParagraph();
		addRun(parrafo.createRun(), mayuscula, texto);
	}
	
	private void addRun(XWPFRun run, Boolean mayuscula, String texto)
	{
		run.setText(texto);
		run.setBold(mayuscula);
		run.setCapitalized(false);
		run.setFontFamily("Source Sans Pro");
		run.setFontSize(14);
	}
}
