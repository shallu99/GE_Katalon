package excelExportAndFileIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.kms.katalon.core.annotation.Keyword
import com.sun.org.apache.bcel.internal.generic.RETURN

import groovy.inspect.swingui.ObjectBrowser


public class Excel {

	int Testcasenamerow;
	int rowCount;
	int k = 0;
	String[][] matchingRowValues;
	List<Row> matchingRows ;
	List<Row> matchingRow;
	Sheet UTHSheet;
	String sheetnames;
	String TestScript;
	int Totalarraycount


	@Keyword
	public Object[][] readExcel(String filePath, String fileName, String sheetName, String testcasename) throws IOException {

		//Create an object of File class to open xlsx file
		File file = new File(filePath + "\\" + fileName);

		//Create an object of FileInputStream class to read excel file
		FileInputStream inputStream = new FileInputStream(file);
		Workbook UTHWorkbook = null;

		//Find the file extension by splitting file name in substring  and getting only extension name
		String fileExtensionName = fileName.substring(fileName.indexOf("."));

		//Check condition if the file is xlsx file
		if (fileExtensionName.equals(".xlsx")) {

			//If it is xlsx file then create object of XSSFWorkbook class
			UTHWorkbook = new XSSFWorkbook(inputStream);
		}

		//Check condition if the file is xls file
		else if (fileExtensionName.equals(".xls")) {

			//If it is xls file then create object of HSSFWorkbook class
			UTHWorkbook = new HSSFWorkbook(inputStream);
		}

		//Read sheet inside the workbook by its name
		UTHSheet = UTHWorkbook.getSheet(sheetName);
		sheetnames = UTHSheet.getSheetName();
		System.out.println("Name of the sheet in given excel file : "+UTHSheet.getSheetName());

		//Find number of rows in excel file
		rowCount = UTHSheet.getLastRowNum() - UTHSheet.getFirstRowNum();

		matchingRows = new ArrayList<>();
		for (Row row : UTHSheet) {
			for (Cell cell : row) {


				if (cell.getStringCellValue().equals(testcasename)) {

					TestScript =cell.getStringCellValue();
					System.out.println("TestCaseName found in row number: " + (row.getRowNum() + 1));
					Testcasenamerow = row.getRowNum();
					matchingRows.add(row);
					break;
				}
			}
		}

		System.out.println("Matching Row execute yes: ");
		System.out.println("Execute testcase: ");
		String Execute = "Yes";
		matchingRow = new ArrayList<>();
		for (Row row : matchingRows) {
			for (Cell cell : row) {
				if (cell.getStringCellValue().equals(Execute)) {
					System.out.print(cell.getStringCellValue() + "\t");
					System.out.println("execute Found in row: " + (row.getRowNum() + 1));
					matchingRow.add(row);
				}
			}
			System.out.println();
		}
		// return matchingRow;
		//        }

		//            public Object[][] getTestData () {
		Totalarraycount = matchingRow.size()
		for (int i = 0; i < matchingRow.size(); i++) {
			Row row = matchingRow.get(i);
			matchingRowValues = new String[matchingRows.size()][row.getLastCellNum()];
			System.out.println(row.getLastCellNum());
			for (int j = 0; j < row.getLastCellNum(); j++) {
				Cell cell = row.getCell(j);
				matchingRowValues[i][j] = cell.getStringCellValue();
				k++;
			}
		}
		return matchingRowValues;
	}


	public void print () {
		System.out.println("Testdata to execute testcase : " + "Sheetname " + sheetnames + " Testcasename " + TestScript);
		for (String[] rowValues : matchingRowValues) {
			for (String value : rowValues) {
				System.out.print(value + "\t");
			}
			System.out.println();
		}


	}

	//Main function is calling readExcel function to read data from excel file
	public static void main (String...strings) throws IOException {
		//Create an object of ReadGuru99ExcelFile class
		Excel objExcelFile = new Excel();
		objExcelFile.readExcel("C:\\Users\\adars\\Excel", "invitebulk.xlsx", "Mytest", "Test1A");
	}

}