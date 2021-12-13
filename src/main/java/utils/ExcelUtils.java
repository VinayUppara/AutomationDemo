package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	public static Object[][] getDataFromExcel(String dataFile,String sheetName, String key) throws IOException
	{ 
		FileInputStream fileInputStream = null; 
		HashMap<String, String> mapData = new HashMap<String,String>();
		try { 

			fileInputStream = new FileInputStream(dataFile);
			XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream); 
			XSSFSheet sheet = workbook.getSheet(sheetName); 
			Cell tableStart = getStartTable(key,sheet);

			if(null==tableStart) { 
				throw new Exception();

			} 
			int startRow = tableStart.getRowIndex(); 

			int startCol = tableStart.getColumnIndex();


			//tableStart.getRow().getRowNum();

			Cell tableEnd = getEndTabe(key, sheet);

			int endRow = tableEnd.getRowIndex();

			int endCol = tableEnd.getColumnIndex();

			int numRows = endRow - startRow; 

			int numCols = endCol - (startCol+1);

			HashMap<String,String> headersMap = getHeaders(key, sheet);

			for (int i = startRow+1; i <=endRow; i++) {
				// rowMap = new  HashMap<String,String>();
				Row row = sheet.getRow(i);
				for(int j =startCol+1;j<=endCol-1;j++) 
				{ 
					
				Cell cell = row.getCell(j);
				DataFormatter dataFormatter = new DataFormatter();
				String value = dataFormatter.formatCellValue(cell);
				mapData.put(headersMap.get(String.valueOf(j)), value); 

				} 

			}

		}


		catch (Exception e) 
		{ 
			e.printStackTrace(); 
		}

		finally 
		{ 
			try 
			{ 
				fileInputStream.close();
			}
			catch (Exception e2)
			{  

			}
		} 
		return new Object[][]{
			{mapData}}; 
	}

	private static HashMap<String, String> getHeaders(String key, XSSFSheet sheet)
	{ 
		int tableStartCol = getStartTable(key,sheet).getRow().getFirstCellNum();
		int tableEndCol = getStartTable(key,sheet).getRow().getLastCellNum();

		Row headers = getStartTable(key, sheet).getRow();
		HashMap<String,String> headersMap = new HashMap<String,String>();

		for (int i = tableStartCol+1; i < tableEndCol; i++) {
			Cell cell =headers.getCell(i); 
			DataFormatter dataFormatter = new DataFormatter(); 
			String value = dataFormatter.formatCellValue(cell);
			//System.out.println(String.valueOf(i)+" "+value);
			headersMap.put(String.valueOf(i), value);
		}

		return headersMap;


	}

	private static Cell getStartTable(String key, XSSFSheet sheet) {
		for(Row row:sheet) 
		{ 
			Iterator<Cell> cells = row.cellIterator();
			while(cells.hasNext())
			{ 
				Cell cell = cells.next(); 
				DataFormatter dataFormatter = new DataFormatter();
				String value = dataFormatter.formatCellValue(cell); 
				//System.out.println(value);
				if(key.equalsIgnoreCase(value)) {

					//System.out.println(value);
					return cell; 
				} 
			}

		} return null;

	}

	private static Cell getEndTabe(String key, XSSFSheet sheet) {

		int tableStartCol = getStartTable(key,sheet).getRow().getFirstCellNum(); 



		int tableEndCol = getStartTable(key, sheet).getRow().getLastCellNum();


		Iterator iterator = sheet.rowIterator();
		while (iterator.hasNext())
		{ 
			Row row = (Row)iterator.next();
			for(int i = tableStartCol+1;i<tableEndCol+1;i++)
			{
				Cell cell = row.getCell(i);
				DataFormatter formatter = new DataFormatter();
				String value = formatter.formatCellValue(cell); 

				if(key.equalsIgnoreCase(value))
				{ 
					//System.out.println(value);
					return cell;
				} 
			}
		}

		return null;

	}
}
