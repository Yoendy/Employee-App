package com.Sistema_Empleados.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Repository;

import com.Sistema_Empleados.domain.Empleado;


@Repository
public class EmpleadoRepositoryImpl implements EmpleadoRepository 
{
	private List<Empleado> empleados;
	private Workbook workbook;
	private Sheet sheet;
	private Iterator<Row> iterator;
	private String excelFilePath;
	private FileInputStream inputStream;
	private int pagina = 2;
	Empleado empleado;
	
	public EmpleadoRepositoryImpl()
	{

		excelFilePath = "C:\\data\\BD.xls";//"WEB-INF/classes/BD.xls";//"src/main/resources/BD.xls";;//"C:\\Users\\Yoendy F. Maldonado\\Documents\\workspace\\SistemaEmpleados\\src\\main\\resources\\BD.xls";//"C:\\Users\\ymaldonado\\workspace\\SistemaEmpleados\\src\\main\\resources\\BD.xls";
		getData();
	}
	
	@Override
	public void openWorkBook()
	{
		  try{
		  inputStream = new FileInputStream(new File(excelFilePath));
		 
		  workbook = new HSSFWorkbook(inputStream);
		  }catch (IOException e) 
		   {

			e.printStackTrace();
		   }
		  sheet = workbook.getSheetAt(pagina);
		  empleados = new ArrayList<>();
		  iterator = sheet.iterator();
	}
	
	@Override
	public List<Empleado> findAll() {
	
		getData();
		return empleados;
	}
	
	@Override
	public void closeWorkBook()
	{
		try
		{
		workbook.close();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public Empleado findOne(int id) {

	
		getData();
		return  empleados.stream().filter(x -> x.getId() == id).findAny().orElse(null);
	}
	
	@Override
	public void save(Empleado modelo) {
		
		int id = count()+1;
		modelo.setId(id);
		modelo.setEstado(true);
		insert(modelo,count());
	}

	@Override
	public void update(Empleado modelo) {
		getData();
		Empleado puesto = findOne(modelo.getId());
		int index = empleados.indexOf(puesto);
		update(modelo, index);
	}

	@Override
	public void delete(Empleado modelo) {
		openWorkBook();
		int index = empleados.indexOf(modelo);
		 Row row = sheet.getRow(index +1);
	     
	     int lastRowNum=sheet.getLastRowNum();
	     if(index>=0 && index<lastRowNum)
	     {
	         sheet.shiftRows(index+2,lastRowNum, -1);
	     }
	     
	     if(index==lastRowNum)
	     {

	         if(row!=null)
	         {
	    		 row.setZeroHeight(true);
	    	     sheet.removeRow(row);
	         }
	     }
	     
	     generateData();
	     getData();
		
	}
	
	@Override
	public Boolean exist(int id) {
		getData();
		return null != findOne(id);
	}
	

	@Override
	public int count() {
		getData();
		return empleados.size();
	}
	
	@Override
	public void getData() {
		openWorkBook();
		while (iterator.hasNext()) 
		{
		   Row nextRow = (Row) iterator.next();   
		   
		 
		   if(nextRow.getRowNum()==0)
		    continue;
		   
		   Empleado empleado = new Empleado();
		   Iterator<Cell> cellIterator = nextRow.cellIterator();
		   
		   while (cellIterator.hasNext()) 
		   {
			   Cell cell = cellIterator.next();
			   int columnIndex=cell.getColumnIndex();
		    
			   switch (columnIndex+1) 
			   {
			   		case 1:
			   			empleado.setId((int)cell.getNumericCellValue());
			   			break;
			   		case 2:
			   			empleado.setNombre(cell.getStringCellValue());
			   			break;
			   		case 3:
			   			empleado.setApellido(cell.getStringCellValue());
			   			break;
			   		case 4:
			   			empleado.setEmail(cell.getStringCellValue());
			   			break;
			   		case 5:
			   			empleado.setDireccion(cell.getStringCellValue());
			   			break;
			   		case 6:
			   			empleado.setCelular(cell.getStringCellValue());
			   			break;
			   		case 7:
			   			empleado.setFechaIngreso(cell.getDateCellValue());
			   			break;
			   		case 8:
			   			empleado.setFechaCumpleano(cell.getDateCellValue());
			   			break;
			   		case 9:
			   			empleado.setSalario(new BigDecimal(cell.getNumericCellValue()));
			   			break;
			   		case 10:
			   			empleado.setSupervisor((int)cell.getNumericCellValue());
			   			break;
			   		case 11:
			   			empleado.setPuestoId((int)cell.getNumericCellValue());
			   			break;
			   		case 12:
			   			empleado.setDepartamentoId((int)cell.getNumericCellValue());
			   			break;
			   		case 13:
			   			empleado.setEstado(cell.getBooleanCellValue());
			   			break;
			   }
		    
		   	}
		   if(empleado.getId()!= 0)
			   empleados.add(empleado);
		}
		closeWorkBook();
	}
	
	@Override
	public void update(Empleado modelo, int index) {
			openWorkBook();
		   Row row = sheet.getRow(index+1);
		    
		   Cell cell = row.getCell(0);
	   
		   //Id
		   //cell.setCellValue(modelo.getId());
	   
		   //Nombre
		   cell = row.getCell(1);
		   cell.setCellValue(modelo.getNombre());
	   
		   //Apellido
		   cell = row.getCell(2);
		   cell.setCellValue(modelo.getApellido());
	   
		   //Email
		   cell = row.getCell(3);
		   cell.setCellValue(modelo.getEmail());
		   
		   //Direccion
		   cell = row.getCell(4);
		   cell.setCellValue(modelo.getDireccion());
		   
		   //Celular
		   cell = row.getCell(5);
		   cell.setCellValue(modelo.getCelular());
		   
		   //FechaIngreso
		   cell = row.getCell(6);
		   cell.setCellValue(modelo.getFechaIngreso());
		   
		   //FechaCumpleano
		   cell = row.getCell(7);
		   cell.setCellValue(modelo.getFechaCumpleano());
		   
		   //Salario
		   cell = row.getCell(8);
		   cell.setCellValue(modelo.getSalario().doubleValue());
		   
		   //Supervisor
		   cell = row.getCell(9);
		   cell.setCellValue(modelo.getSupervisor());
		   
		   //PuestoId
		   cell = row.getCell(10);
		   cell.setCellValue(modelo.getPuestoId());
		   
		   //DepartamentoId
		   cell = row.getCell(11);
		   cell.setCellValue(modelo.getDepartamentoId());
		   
		   //Estado
		   cell = row.getCell(12);
		   cell.setCellValue(modelo.getEstado());
	   
	   
		   generateData();
		   getData();
		
	}
	
	@Override
	public void generateData() {
		try
		{
			FileOutputStream out = new FileOutputStream(new File(excelFilePath));
			workbook.write(out);
			out.close();
			workbook.close();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Empleado> searchAll(String filtro) {


		getData();
		return  empleados.stream().filter(x -> x.getNombre().contains(filtro)).collect(Collectors.toList() );
	}
	
	@Override
	public void insert(Empleado modelo, int index) {
		openWorkBook();   
		Row row = sheet.getRow(index+1);

		if(row == null)
		{
			row = sheet.createRow(index+1);
		}
		    
		   Cell cell = row.createCell(0);
	   
		   //Id
		   cell.setCellValue(modelo.getId());
	   
		   //Nombre
		   cell = row.createCell(1);
		   cell.setCellValue(modelo.getNombre());
	   
		   //Apellido
		   cell = row.createCell(2);
		   cell.setCellValue(modelo.getApellido());
	   
		   //Email
		   cell = row.createCell(3);
		   cell.setCellValue(modelo.getEmail());
		   
		   //Direccion
		   cell = row.createCell(4);
		   cell.setCellValue(modelo.getDireccion());
		   
		   //Celular
		   cell = row.createCell(5);
		   cell.setCellValue(modelo.getCelular());
		   
		   //FechaIngreso
		   cell = row.createCell(6);
		   cell.setCellValue(modelo.getFechaIngreso());
		   
		   //FechaCumpleano
		   cell = row.createCell(7);
		   cell.setCellValue(modelo.getFechaCumpleano());
		   
		   //Salario
		   cell = row.createCell(8);
		   cell.setCellValue(modelo.getSalario().doubleValue());
		   
		   //Supervisor
		   cell = row.createCell(9);
		   cell.setCellValue(modelo.getSupervisor());
		   
		   //PuestoId
		   cell = row.createCell(10);
		   cell.setCellValue(modelo.getPuestoId());
		   
		   //DepartamentoId
		   cell = row.createCell(11);
		   cell.setCellValue(modelo.getDepartamentoId());
		   
		   //Estado
		   cell = row.createCell(12);
		   cell.setCellValue(modelo.getEstado());
	   
	   
		   generateData();
		   getData();
		
	}
	
	@Override
	public boolean isRowEmpty(Row row) {
	    for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) 
	    {
	        Cell cell = row.getCell(c);
	        if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK)
	            return false;
	    }
	    return true;
	}
	
	@Override
	public void delete(int id) {
		getData();
		Empleado empleado = findOne(id);
		empleado.setEstado(false);
		int index = empleados.indexOf(empleado);
		update(empleado, index);
		
	}

	@Override
	public List<Empleado> findAll(int id) {
		getData();
		return  empleados.stream().filter(x -> x.getId() == id).collect(Collectors.toList() );
	}

	@Override
	public int countAllByDepartamento(int id) {
		//getData();
		return  (int) empleados.stream().filter(x -> x.getDepartamentoId() == id).count();
	}

	@Override
	public int countAllByPuesto(int id) {
		//getData();
		return  (int) empleados.stream().filter(x -> x.getPuestoId() == id).count();
	}
}
