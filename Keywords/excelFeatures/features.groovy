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

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.security.MessageDigest;

import java.util.Base64;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;



class features {
	/**
	 * Refresh browser
	 */
	@Keyword
	def refreshBrowser() {
		KeywordUtil.logInfo("Refreshing")
		WebDriver webDriver = DriverFactory.getWebDriver()
		webDriver.navigate().refresh()
		KeywordUtil.markPassed("Refresh successfully")
	}

	/**
	 * Click element
	 * @param to Katalon test object
	 */
	@Keyword
	def clickElement(TestObject to) {
		try {
			WebElement element = WebUiBuiltInKeywords.findWebElement(to);
			KeywordUtil.logInfo("Clicking element")
			element.click()
			KeywordUtil.markPassed("Element has been clicked")
		} catch (WebElementNotFoundException e) {
			KeywordUtil.markFailed("Element not found")
		} catch (Exception e) {
			KeywordUtil.markFailed("Fail to click on element")
		}
	}

	/**
	 * Get all rows of HTML table
	 * @param table Katalon test object represent for HTML table
	 * @param outerTagName outer tag name of TR tag, usually is TBODY
	 * @return All rows inside HTML table
	 */
	@Keyword
	def List<WebElement> getHtmlTableRows(TestObject table, String outerTagName) {
		WebElement mailList = WebUiBuiltInKeywords.findWebElement(table)
		List<WebElement> selectedRows = mailList.findElements(By.xpath("./" + outerTagName + "/tr"))
		return selectedRows
	}



	def ExcelLib() {
		this.workbook = initTestDataFile();
		this.worksheet = workbook.getSheet("Sheet1");
		//		this.worksheet = workbook.getSheet(workSheetName);
		//		this.testCaseName = testCaseName;
		this.rows = getRowCount();
		this.testCaseStartRow = getTestCaseStartRow();
		this.testCaseEndRow = getTestCaseEndRow();
		this.usedColumnsCount = getUsedColumnsCount();
		this.iterationCount = getIterationCount();
	}

	//	private XSSFWorkbook initTestDataFile() {
	public XSSFWorkbook initTestDataFile() {
		FileInputStream fis = new FileInputStream(new File("C:\\Users\\Lenovo UTH-UK\\Desktop\\Input_Data.xlsx"));
		XSSFWorkbook workbook = new XSSFWorkbook (fis);
		//		ClassLoader classLoader = getClass().getClassLoader();
		//		File file = new File(classLoader.getResource("Input_Data.xlsx").getFile());
		//		XSSFWorkbook workbook;
		try {
			workbook = new XSSFWorkbook(file);
			//		} catch (InvalidFormatException e) {
			////			log.error("Error in loadTestData :" ,e);
			//			System.out.println("Error in loadTestData :" ,e);
			//			throw new RuntimeException(e);
		} catch (IOException e) {
			//			log.error("Error in loadTestData :" ,e);
			System.out.println("Error in loadTestData :" ,e);
			throw new RuntimeException(e);
		}
		return workbook;
	}

	public int getIterationCount(){
		try {
			System.out.println("Sart Row :"  + testCaseStartRow + " End Row :" + testCaseEndRow);
			for(int i =testCaseStartRow; i <= testCaseEndRow; i++){
				if(getCellData(usedColumnsCount,i).equalsIgnoreCase("Yes")){
					iterationCount++;
				}
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

	//	/*Get TestCase Start Row*/
	public int getTestCaseStartRow(){
		try {
			for(int i = 0; i <= rows; i++){
				Row row = worksheet.getRow(i);
				if(row != null) {
					Cell cell = row.getCell(0);
					if(cell != null) {
						String columnName = cell.getStringCellValue();
						if(columnName.equals(testCaseName.trim())){
							testCaseStartRow = i;
							break;
						}
					}
				}
			}
		} catch (Exception e) {
			//			log.error("Error in getTestCaseStartRow",e);
			System.out.println("Error in getTestCaseStartRow",e);
			throw new RuntimeException(e);
		}
		return testCaseStartRow;
	}
	//
	//	/*Get Test Case End Row*/
	public int getTestCaseEndRow(){
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
		} catch (Exception e) {
			//			log.error("Error in getTestCaseStartRow",e);
			System.out.println("Error in getTestCaseStartRow",e);
			throw new RuntimeException(e);
		}
		return testCaseEndRow;
	}

	//	/*Get the Columns Count for the referenced test case*/
	public int getUsedColumnsCount(){
		try {
			int count = 0;
			Row row = worksheet.getRow(testCaseStartRow-1);
			int cellNum  = row.getLastCellNum();
			for(int i=0 ; i < cellNum ; i++) {
				Cell cell = row.getCell(i);
				usedColumnsCount = count++;
				if(cell != null) {
					String cellVal = cell.getStringCellValue();
					if("Execute".equals(cellVal)) {
						break;
					}
				}
			}
		} catch (Exception e) {
			//			log.error("Error in getTestCaseStartRow",e);
			System.out.println("Error in getTestCaseStartRow",e);
			throw new RuntimeException(e);
		}
		return usedColumnsCount;
	}

	//	/*Gets the total number of row count in the excel sheet*/
	public int getRowCount() {
		return worksheet.getLastRowNum();
	}

	@Keyword
	def XL(String... strings) {
		//		ExcelLib xl = new ExcelLib("Policy", "TC174CreateNewPolicyNone");
		ExcelLib();
	}
}