package com.Sistema_Empleados.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

public class Conexion {
	 	
		private String excelFilePath;
		private FileInputStream inputStream;
		public Workbook workbook;
	    
	    //private constructor to avoid client applications to use constructor
	    public Conexion()
	    {

	    }
	    
	    public Workbook getWorkbook()
	    {
			try
			{
				excelFilePath = "C:\\data\\BD.xls";//"WEB-INF/classes/BD.xls";//"src/main/resources/BD.xls";//"C:\\Users\\Yoendy F. Maldonado\\Documents\\workspace\\SistemaEmpleados\\src\\main\\resources\\BD.xls";
				inputStream = new FileInputStream(new File(excelFilePath));			 
				workbook = new HSSFWorkbook(inputStream);
				inputStream.close();
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			
			return workbook;

	    }
	    
	    public void closeWorkbook()
	    {	 
	    	try
	    	{
		    	if(workbook != null)
					workbook.close();
	    	}
	    	catch (IOException e) 
	    	{
	    		e.printStackTrace();
	    	}
			
	    }
}
