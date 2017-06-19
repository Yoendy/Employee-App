package com.Sistema_Empleados.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.Sistema_Empleados.config.Conexion;

public class ExcelBuilder extends AbstractExcelView {

@Override
protected void buildExcelDocument(Map<String, Object> arg0, HSSFWorkbook workbook, HttpServletRequest arg2,
		HttpServletResponse arg3) throws Exception 
{
	workbook = (HSSFWorkbook) new Conexion().getWorkbook();
	
}

}

