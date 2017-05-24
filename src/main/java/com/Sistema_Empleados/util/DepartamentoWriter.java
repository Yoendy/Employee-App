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

public class DepartamentoWriter {
	private XWPFDocument document;

	public void generateWord(HttpServletResponse response, List<DepartamentoDTO> departamentos)
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
			createBody(table, departamentos);
			
			  // 6. Set the response properties
			  String fileName = "Departamentos.doc";
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
		addCell(row.getCell(0),"Nombre", true);
		addCell(row.addNewTableCell(),"Descripcion", true);
		addCell(row.addNewTableCell(),"Encargado", true);
	}
	
	private void createBody(XWPFTable table, List<DepartamentoDTO> departamentos)
	{
		//Create header
		for(DepartamentoDTO departamento : departamentos)
		{
			XWPFTableRow row = table.createRow();
			addCell(row.getCell(0),departamento.getNombre(), false);
			addCell(row.getCell(1),departamento.getDescripcion(), false);
			addCell(row.getCell(2),departamento.getEncargado(), false);
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
