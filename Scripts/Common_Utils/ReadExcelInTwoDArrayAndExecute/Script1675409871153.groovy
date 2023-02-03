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
import org.junit.Test as Test
import org.openqa.selenium.Keys as Keys
import org.testng.annotations.DataProvider as DataProvider
import excelExportAndFileIO.Excel as Excel
import groovy.ui.SystemOutputInterceptor as SystemOutputInterceptor

String filepath = "C:\\Users\\Lenovo UTH-UK\\Desktop";
String filename = "Input_Data.xlsx"
String sheetname = "Sheet1"
String testCaseName = "Test7A"

//@DataProvider(name="Test6A")
String[][] arra= CustomKeywords.'excelExportAndFileIO.Excel.readExcel'(filepath, filename, sheetname, testCaseName)

//for (int i = 0; i < arra.length; i++) {
//        for (int j = 1; j < arra[i].length-1; j++) {
// System.out.println();
//            System.out.print(arra[0][j] + " ");
// System.out.println();

System.out.println();
System.out.println();
System.out.println();
System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
System.out.println(arra);
System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
System.out.println();
System.out.println();
System.out.println();

String username = arra[0][2]
String password = arra[0][3]
System.out.println(username + password)

WebUI.openBrowser(arra[0][1])

WebUI.setText(findTestObject('Object Repository/LoginPage_GIROPie/input_TextField_Username'), username)

WebUI.setText(findTestObject('Object Repository/LoginPage_GIROPie/input_TextField_password'),
	password)

WebUI.click(findTestObject('Object Repository/LoginPage_GIROPie/Continue_SignUpButton'))

WebUI.closeBrowser()

//        }
//        System.out.println();
//    }