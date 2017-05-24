package com.Sistema_Empleados.service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Sistema_Empleados.config.Conexion;

@Transactional
@Service
public class DownloadService 
{
	 private static Logger logger = Logger.getLogger("service");
	public void downloadXLS(HttpServletResponse response) throws ClassNotFoundException {
		  logger.debug("Downloading Excel report");
		  
		// 1. Create new workbook
		  HSSFWorkbook workbook = (HSSFWorkbook) new Conexion().getWorkbook();
		  
		  // 2. Create new worksheet
		  HSSFSheet worksheet = workbook.getSheetAt(0);
		  
		  // 6. Set the response properties
		  String fileName = "SistemaEmpleado.xls";
		  response.setHeader("Content-Disposition", "inline; filename=" + fileName);
		  // Make sure to set the correct content type
		  response.setContentType("application/vnd.ms-excel");
		  
		  //7. Write to the output stream
		  write(response, worksheet);
	}
	
	 public static void write(HttpServletResponse response, HSSFSheet worksheet) {
		  
		  logger.debug("Writing report to the stream");
		  try 
		  {
			  ServletOutputStream outputStream = response.getOutputStream();
			  worksheet.getWorkbook().write(outputStream);
			  outputStream.flush();
		  } 
		  catch (Exception e) 
		  {
			  logger.error("Unable to write report to the output stream");
		  }
	}
}
