package lib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataConfig 
{
	XSSFWorkbook wb;
	
	XSSFSheet sheet1;

	public ExcelDataConfig(String excelPath)
	{
        try {
			File src=new File(excelPath);//This is file class from java i/o package
			
			FileInputStream fis=new FileInputStream(src);//Load the file into file input stream

			wb=new XSSFWorkbook(fis);//load the workbook
			
					} 
        catch (IOException e)
        {
		System.out.println(e.getMessage());	
		}
	}
	
public String getData(int sheetNumber,int row,int column)
{
	sheet1=wb.getSheetAt(sheetNumber);//load the first sheet//index starts at 0

	String data=sheet1.getRow(row).getCell(column).getStringCellValue();
	return data;
	
}

public int getRowCount(int sheetIndex)
{
int row=wb.getSheetAt(sheetIndex).getLastRowNum();
row=row+1;
return row;
}
}