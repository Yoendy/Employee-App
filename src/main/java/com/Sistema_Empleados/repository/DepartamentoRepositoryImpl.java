package com.Sistema_Empleados.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Repository;

import com.Sistema_Empleados.config.Conexion;
import com.Sistema_Empleados.domain.Departamento;

@Repository
public class DepartamentoRepositoryImpl implements DepartamentoRepository
{
	private List<Departamento> departamentos;
	private Workbook workbook;
	private Sheet sheet;
	private Iterator<Row> iterator;
	private String excelFilePath;
	private FileInputStream inputStream;
	Departamento departamento;
	int pagina = 0;
	
	public DepartamentoRepositoryImpl() 
	{ 
		excelFilePath = "C:\\data\\BD.xls";//"src/main/resources/BD.xls";
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
		  departamentos = new ArrayList<>();
		  iterator = sheet.iterator();
	}
	
	@Override
	public void getData()
	{  	
		openWorkBook();
		while (iterator.hasNext()) 
		{
		   Row nextRow = (Row) iterator.next();   
		   
		   // Not creating country object for header
		   if(nextRow.getRowNum()==0)
		    continue;
		   
		   Departamento departamento = new Departamento();
		   Iterator<Cell> cellIterator = nextRow.cellIterator();
		   
		   while (cellIterator.hasNext()) 
		   {
			   Cell cell = cellIterator.next();
			   int columnIndex=cell.getColumnIndex();
		    
			   switch (columnIndex+1) 
			   {
			   		case 1:
			   			departamento.setId((int)cell.getNumericCellValue());
			   			break;
			   		case 2:
			   			departamento.setNombre(cell.getStringCellValue());
			   			break;
			   		case 3:
			   			departamento.setDescripcion(cell.getStringCellValue());
			   			break;
			   		case 4:
			   			departamento.setEncargadoId((int)(cell.getNumericCellValue()));
			   			break;
			   		case 5:
			   			departamento.setEstado((cell.getBooleanCellValue()));
			   			break;
			   }
		    
		   	}
		   if(departamento.getId()!= 0)
			   departamentos.add(departamento);
		}
		closeWorkBook();
	}
	
	@Override
	public void insert(Departamento departamento, int index)
	{
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

		//EncargadoId
		cell = row.createCell(3);
		cell.setCellValue(departamento.getEncargadoId());
		
		//Estado
		cell = row.createCell(4);
		cell.setCellValue(departamento.getEstado());

		generateData();
		getData();


	}
	
	@Override
	public void update(Departamento departamento, int index)
	{
		openWorkBook();
		   Row row = sheet.getRow(index+1);
		    
		   Cell cell = row.getCell(0);
		   
		   //Id
		   //cell.setCellValue(departamento.getId());
		   
		   //Nombre
		   cell = row.getCell(1);
		   cell.setCellValue(departamento.getNombre());
		   
		   //Descripcion
		   cell = row.getCell(2);
		   cell.setCellValue(departamento.getDescripcion());
		   
		   //EncargadoId
		   cell = row.getCell(3);
		   cell.setCellValue(departamento.getEncargadoId());
		   
			//Estado
			cell = row.getCell(4);
			cell.setCellValue(departamento.getEstado());
		   
		   generateData();
		   getData();

	}
	@Override
	public void generateData() {
		try{
			FileOutputStream out = new FileOutputStream(new File(excelFilePath));
			workbook.write(out);
			out.close();
			workbook.close();

		}catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	

	
	/* (non-Javadoc)
	 * @see com.softtours.repository.GenericRepository#findAll()
	 */
	@Override
	public List<Departamento> findAll() {
		getData();
		return departamentos;
	}

	/* (non-Javadoc)
	 * @see com.softtours.repository.GenericRepository#findOne(int)
	 */
	@Override
	public Departamento findOne(int id) {
		getData();
		return  departamentos.stream().filter(x -> x.getId() == id).findAny().orElse(null);
	}

	/* (non-Javadoc)
	 * @see com.softtours.repository.GenericRepository#save(com.softtours.domain.Departamento)
	 */
	@Override
	public void save(Departamento modelo){
		int id = count()+1;
		modelo.setId(id);
		insert(modelo,count());
	}

	/* (non-Javadoc)
	 * @see com.softtours.repository.GenericRepository#update(com.softtours.domain.Departamento)
	 */
	@Override
	public void update(Departamento modelo) {
		Departamento departamento = findOne(modelo.getId());
		int index = departamentos.indexOf(departamento);
		update(modelo, index);
	}

	/* (non-Javadoc)
	 * @see com.softtours.repository.GenericRepository#delete(com.softtours.domain.Departamento)
	 */
	@Override
	public void delete(Departamento modelo){
		openWorkBook();
		 int index = departamentos.indexOf(modelo);
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

	/* (non-Javadoc)
	 * @see com.softtours.repository.GenericRepository#exist(int)
	 */
	@Override
	public Boolean exist(int id) {
		return null != findOne(id);
	}

	/* (non-Javadoc)
	 * @see com.softtours.repository.GenericRepository#count()
	 */
	@Override
	public int count() {
		getData();
		return departamentos.size();
	}
	@Override
	public List<Departamento> searchAll(String filtro) {
		getData();
		return  departamentos.stream().filter(x -> x.getNombre().contains(filtro)).collect(Collectors.toList() );
	}
	
	
	@Override
	public boolean isRowEmpty(Row row) 
	{
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
		
		Departamento departamento = findOne(id);
		departamento.setEstado(false);
		int index = departamentos.indexOf(departamento);
		update(departamento, index);
		
	}

	@Override
	public List<Departamento> findAll(int id) {
		getData();
		return  departamentos.stream().filter(x -> x.getId() == id).collect(Collectors.toList() );
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
		
}
