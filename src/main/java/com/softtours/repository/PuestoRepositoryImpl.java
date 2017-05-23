package com.softtours.repository;

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

import com.softtours.domain.Departamento;
import com.softtours.domain.Puesto;

@Repository
public class PuestoRepositoryImpl implements PuestoRepository
{
	private List<Puesto> puestos;
	private Workbook workbook;
	private Sheet sheet;
	private Iterator<Row> iterator;
	private String excelFilePath;
	private FileInputStream inputStream;
	private int pagina = 1;
	Puesto puesto;
	
	public PuestoRepositoryImpl()
	{
		excelFilePath = "src/main/resources/BD.xls";//"C:\\Users\\Yoendy F. Maldonado\\Documents\\workspace\\SistemaEmpleados\\src\\main\\resources\\BD.xls";//"C:\\Users\\ymaldonado\\workspace\\SistemaEmpleados\\src\\main\\resources\\BD.xls";
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
		  puestos = new ArrayList<>();
		  iterator = sheet.iterator();
	}

	
	@Override
	public List<Puesto> findAll() {
		return puestos;
	}

	@Override
	public Puesto findOne(int id) {
		getData();
		return  puestos.stream().filter(x -> x.getId() == id).findAny().orElse(null);
	}

	@Override
	public void save(Puesto modelo) {
		
		int id = count()+1;
		modelo.setId(id);
		modelo.setEstado(true);
		insert(modelo,count());
	}

	@Override
	public void update(Puesto modelo) {
	
		getData();
		Puesto puesto = findOne(modelo.getId());
		int index = puestos.indexOf(puesto);
		update(modelo, index);

	}

	@Override
	public void delete(Puesto modelo) {
		 
		int index = puestos.indexOf(modelo);
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
		return puestos.size();
	}

	@Override
	public void getData() {
		openWorkBook();
		while (iterator.hasNext()) 
		{
		   Row nextRow = (Row) iterator.next();   
		   
		 
		   if(nextRow.getRowNum()==0)
		    continue;
		   
		   Puesto puesto = new Puesto();
		   Iterator<Cell> cellIterator = nextRow.cellIterator();
		   
		   while (cellIterator.hasNext()) 
		   {
			   Cell cell = cellIterator.next();
			   int columnIndex=cell.getColumnIndex();
		    
			   switch (columnIndex+1) 
			   {
			   		case 1:
			   			puesto.setId((int)cell.getNumericCellValue());
			   			break;
			   		case 2:
			   			puesto.setNombre(cell.getStringCellValue());
			   			break;
			   		case 3:
			   			puesto.setDescripcion(cell.getStringCellValue());
			   			break;
			   		case 4:
			   			puesto.setSalarioMinimo( new BigDecimal(cell.getNumericCellValue()));
			   			break;
			   		case 5:
			   			puesto.setSalarioMaximo( new BigDecimal(cell.getNumericCellValue()));
			   			break;
			   			
			   		case 6:
			   			puesto.setEstado(cell.getBooleanCellValue());
			   			break;
			   }
		    
		   	}
		   if(puesto.getId()!= 0)
			   puestos.add(puesto);
		}
		closeWorkBook();
	}

	@Override
	public void update(Puesto puesto, int index) {
			openWorkBook();
		   Row row = sheet.getRow(index+1);
		    
		   Cell cell = row.getCell(0);
	   
		   //Id
		   //cell.setCellValue(puesto.getId());
	   
		   //Nombre
		   cell = row.getCell(1);
		   cell.setCellValue(puesto.getNombre());
	   
		   //Descripcion
		   cell = row.getCell(2);
		   cell.setCellValue(puesto.getDescripcion());
	   
		   //Minimo
		   cell = row.getCell(3);
		   cell.setCellValue(puesto.getSalarioMinimo().doubleValue());
		   
		   //Maximo
		   cell = row.getCell(4);
		   cell.setCellValue(puesto.getSalarioMaximo().doubleValue());
		   
		   //Estado
		   cell = row.getCell(5);
		   cell.setCellValue(puesto.getEstado());
	   
	   
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
	public void closeWorkBook()
	{
		try
		{
		workbook.close();
		inputStream.close();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	
	@Override
	public List<Puesto> searchAll(String filtro) {
		getData();
		return  puestos.stream().filter(x -> x.getNombre().contains(filtro)).collect(Collectors.toList() );
	}

	@Override
	public void insert(Puesto departamento, int index) {
		openWorkBook();
		Row row = sheet.getRow(index+1);

		if(row == null)
		{
			row = sheet.createRow(index+1);
		}
		    
		   Cell cell = row.createCell(0);
	   
		   //Id
		   cell.setCellValue(departamento.getId());
	   
		   //Nombre
		   cell = row.createCell(1);
		   cell.setCellValue(departamento.getNombre());
	   
		   //Descripcion
		   cell = row.createCell(2);
		   cell.setCellValue(departamento.getDescripcion());
	   
		   //Minimo
		   cell = row.createCell(3);
		   cell.setCellValue(departamento.getSalarioMinimo().doubleValue());
		   
		   //Maximo
		   cell = row.createCell(4);
		   cell.setCellValue(departamento.getSalarioMaximo().doubleValue());
		   
		   //Estado
		   cell = row.createCell(5);
		   cell.setCellValue(departamento.getEstado());
	   
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
		
		Puesto puesto= findOne(id);
		puesto.setEstado(false);
		int index = puestos.indexOf(puesto);
		update(puesto, index);
		
	}

	@Override
	public List<Puesto> findAll(int id) {
		getData();
		return  puestos.stream().filter(x -> x.getId() == id).collect(Collectors.toList() );
	}
}
