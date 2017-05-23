package com.softtours.util;

import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import com.softtours.domain.Puesto;

public class PuestoWriter {

	private XWPFDocument document;

	public void generateWord(HttpServletResponse response, List<Puesto> puestos)
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
			createBody(table, puestos);
			
			  // 6. Set the response properties
			  String fileName = "Puestos.doc";
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

		addCell(row.getCell(0), "Nombre", true);
		addCell(row.addNewTableCell(),"Descripcion", true);
		addCell(row.addNewTableCell(),"Salario Minimo", true);
		addCell(row.addNewTableCell(),"Salario Maximo", true);
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
	
	private void createBody(XWPFTable table, List<Puesto> puestos)
	{
		//Create header
		for(Puesto puesto : puestos)
		{
			XWPFTableRow row = table.createRow();
		
			addCell(row.getCell(0), puesto.getNombre(), false);
			addCell(row.getCell(1), puesto.getDescripcion(), false);
			addCell(row.getCell(2), puesto.getSalarioMinimo().toString(), false);
			addCell(row.getCell(3), puesto.getSalarioMaximo().toString(), false);
		}
		
	}

}
