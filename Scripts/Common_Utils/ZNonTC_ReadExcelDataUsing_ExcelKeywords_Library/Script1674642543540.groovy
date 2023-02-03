import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys


import com.kms.katalon.keyword.excel.ExcelKeywords

//Source
'https://www.linkedin.com/pulse/katalon-simple-script-get-value-data-from-excel-ahmad-faris'
'https://mundrisoft.com/tech-bytes/read-data-from-excel-sheet-using-katalon-studio/'

String excelLoginAndSignUp = 'C:/Users/Lenovo UTH-UK/Desktop/Input_Data.xlsx'
workBookLoginAndSignUp = ExcelKeywords.getWorkbook(excelLoginAndSignUp)
SheetOMS = ExcelKeywords.getExcelSheet(workBookLoginAndSignUp, 'Sheet1')
NameLogin = ExcelKeywords.getCellValueByAddress (SheetOMS, 'A1')
PasswordLogin = ExcelKeywords.getCellValueByAddress (SheetOMS, 'B1')

System.out.println(NameLogin)
System.out.println(PasswordLogin)

NameLogin = ExcelKeywords.getCellValueByAddress (SheetOMS, 'A2')
PasswordLogin = ExcelKeywords.getCellValueByAddress (SheetOMS, 'B2')
String No=ExcelKeywords.getSheetNames(workBookLoginAndSignUp)
System.out.println()
System.out.println(No)
System.out.println()
System.out.println(NameLogin)
System.out.println(PasswordLogin)
System.out.println()
