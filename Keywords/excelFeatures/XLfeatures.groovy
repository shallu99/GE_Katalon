package excelFeatures

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords
import internal.GlobalVariable
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class XLfeatures {

	@Keyword
	public void demoKey(String filePath, String SheetName, String TCName) throws IOException{

		//		String SheetName = "Sheet1"
		//		String TCName = "Katalon TestCloud";
		//		String filePath = "C:/Users/Lenovo UTH-UK/Desktop/Input_Data.xlsx"

		XSSFWorkbook workbook = initTestDataFile(filePath);
		XSSFSheet worksheet = getSheetwithName(workbook, SheetName);
		int rows = getRowCount(worksheet);
		System.out.println()
		System.out.println()
		//		System.out.println("Total number of rows in excel :  "+(rows+1))
		String testCaseName = TCName
		int testCaseStartRow = getTestCaseStartRow(worksheet, rows, testCaseName);
		//		System.out.println("TestCase StartRow : "+testCaseStartRow)
		int testCaseEndRow = getTestCaseEndRow(worksheet, rows, testCaseName);
		//		System.out.println("TestCase EndRow : "+testCaseEndRow)
		int usedColumnsCount = getUsedColumnsCount(worksheet, testCaseStartRow);
		//		System.out.println("Total No of Columns with Data : "+usedColumnsCount)
		int iterationCount = getIterationCount(testCaseStartRow, testCaseEndRow, usedColumnsCount);
		//		System.out.println("Total No of times TestCase to run :  "+iterationCount)
		System.out.println()
		System.out.println()
		Object[][] TestData = getTestdata(iterationCount, usedColumnsCount, testCaseStartRow, testCaseEndRow, worksheet);
		System.out.println()
		System.out.println()
		System.out.println("Data")
		System.out.println(TestData)
		System.out.println()
		System.out.println()

	}

	/*Return Two Dimensional Array to DataProvider*/
	public Object[][] getTestdata(int iterationCount, int usedColumnsCount, int testCaseStartRow, int testCaseEndRow, XSSFSheet worksheet){
		int row = 0;
		int col = 0;
		String[][] data = new String[iterationCount][usedColumnsCount-1];
		//Get the Test Data
		for(int i =testCaseStartRow; i <= testCaseEndRow; i++){
			col = 0;
			boolean flag = false;
			String cellData = getCellData(usedColumnsCount,i, worksheet);
			//			if(cellData.equalsIgnoreCase("Yes")){
			//				flag = true;
			for(int j = 1; j < usedColumnsCount; j++){
				data[row][col] = getCellData(j, i, worksheet);
				col++;
				//				}
			}
			if(flag){
				row++;
			}
		}
		return data;
	}

	/*Get Cell Data*/
	private String getCellData(int col, int row, XSSFSheet worksheet) {
		String value = "";
		Row rowObj = worksheet.getRow(row);
		Cell cellObj = rowObj.getCell(col);
		String cellValue = cellObj.getStringCellValue();
		if (cellObj != null) {
			value = cellValue;
			try {
				if (cellValue.contains(encryptionPrefix)) {
					value = decrypt(cellValue);
				}
			}catch (Exception e) {
				//			log.error("Error in getIterationCount",e);
				System.out.println("Error in getIterationCount",e);
				throw new RuntimeException(e);
			}
		}
		return value;
	}

	public int getIterationCount(int testCaseStartRow, int testCaseEndRow, int usedColumnsCount){
		int iterationCount = 0;
		try {
			System.out.println("Sart Row :"  + testCaseStartRow + " End Row :" + testCaseEndRow);
			for(int i =testCaseStartRow; i <= testCaseEndRow; i++){
				//				if(getCellData(usedColumnsCount,i).equalsIgnoreCase("Yes")){
				iterationCount++;
				//				}
			}
		} catch (Exception e) {
			//			log.error("Error in getIterationCount",e);
			System.out.println("Error in getIterationCount",e);
			throw new RuntimeException(e);
		}
		if(iterationCount > 0){
			//			log.debug("*************************************************************************************");
			//			log.debug("Total number of iterations selected for test script: '"+testCaseName+"' is"+" "+iterationCount);
			//			log.debug("*************************************************************************************");
			System.out.println("*************************************************************************************");
			System.out.println("Total number of iterations selected for test script is"+" "+iterationCount);
			System.out.println("*************************************************************************************");
		}else{
			//			log.debug("*************************************************************************************");
			//			log.debug("Total number of iterations selected is 0. Please check execute column in TestData.xls file");
			//			log.debug("*************************************************************************************");
			System.out.println("*************************************************************************************");
			System.out.println("Total number of iterations selected is 0. Please check execute column in TestData.xls file");
			System.out.println("*************************************************************************************");
		}
		return iterationCount;
	}

	//	/*Get the Columns Count for the referenced test case*/
	public int getUsedColumnsCount(XSSFSheet worksheet, int testCaseStartRow){
		int usedColumnsCount  = 0;
		try {
			int count = 0;
			Row row = worksheet.getRow(testCaseStartRow-1);
			usedColumnsCount  = row.getLastCellNum();
		}catch (IOException e) {
			//			log.error("Error in getUsedColumnsCount",e);
			System.out.println("Error in getUsedColumnsCount",e);
			throw new RuntimeException(e);
		}
		return usedColumnsCount;
	}

	//	/*Get Test Case End Row*/
	public int getTestCaseEndRow(XSSFSheet worksheet, int rows, String testCaseName){
		int testCaseEndRow = 0;
		try {
			for(int i = 0; i <= rows; i++){
				Row row = worksheet.getRow(i);
				if(row != null) {
					Cell cell = row.getCell(0);
					if(cell != null) {
						String columnValue = cell.getStringCellValue();
						if(columnValue.equals(testCaseName.trim())){
							testCaseEndRow = i;
						}
					}
				}
			}
		}catch (IOException e) {
			//			log.error("Error in getTestCaseEndRow",e);
			System.out.println("Error in getTestCaseEndRow",e);
			throw new RuntimeException(e);
		}
		return testCaseEndRow;
	}

	//	/*Get TestCase Start Row*/
	public int getTestCaseStartRow(XSSFSheet worksheet, int rows, String testCaseName){
		int testCaseStartRow=0
		try {
			for(int i = 0; i <= rows; i++){
				Row row = worksheet.getRow(i);
				if(row != null) {
					Cell cell = row.getCell(0);
					//				System.out.println("Cell Type "+cell.getCellTypeEnum())
					if(cell != null) {
						String columnName = cell.getStringCellValue();
						if(columnName.equals(testCaseName.trim())){
							testCaseStartRow = i;
							break;
						}
					}
				}
			}
		}catch (IOException e) {
			//			log.error("Error in getTestCaseStartRow",e);
			System.out.println("Error in getTestCaseStartRow",e);
			throw new RuntimeException(e);
		}
		return testCaseStartRow;
	}

	public XSSFWorkbook initTestDataFile(String filePath) {
		try {
			FileInputStream fis = new FileInputStream(filePath);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			return workbook;
		}catch (IOException e) {
			//			log.error("Error in loadTestData :" ,e);
			System.out.println("Error in loadTestData :" ,e);
			throw new RuntimeException(e);
		}
	}

	//	/*Gets the total number of row count in the excel sheet*/
	public int getRowCount(XSSFSheet worksheet) {
		int rowCount = worksheet.getLastRowNum();
		return rowCount;
	}

	//	/*Gets the total number of row count in the excel sheet*/
	public XSSFSheet getSheetwithName(XSSFWorkbook workbook, String SheetName) {
		XSSFSheet worksheet = workbook.getSheet(SheetName);
		return worksheet;
	}
}