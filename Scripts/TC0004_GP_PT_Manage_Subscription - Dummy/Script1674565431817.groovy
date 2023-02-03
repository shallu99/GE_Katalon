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
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory

CustomKeywords.'nachPay.user.LoginwithUsernamePwd'()

WebUI.click(findTestObject('Object Repository/Page_Mng_Subscription/UTH_Account_name'))

WebUI.click(findTestObject('Object Repository/Page_Mng_Subscription/Manage Subscriptions dropdown'))

System.out.println(WebUI.verifyElementPresent(findTestObject('Object Repository/Page_Mng_Subscription/text field with current version'), 
        3))

//WebDriver driver = DriverFactory.getWebDriver()
//String current_version = driver.findElement(By.xpath('//*[@id=":r5:"]')).getAttribute('textContent');
//System.out.println(current_version)
//String cur_version = driver.findElement(By.xpath('//*[@id="gsh0"]/div/div[3]/p')).getAttribute('textContent');
// 
//System.out.println(cur_version)
//
//String cur_version1 = WebUI.getText(findTestObject('Object Repository/Page_Mng_Subscription/Page_test/p_Current Plan'))
//
//System.out.println(cur_version1)
String cur_version2 = WebUI.getText(findTestObject('Object Repository/Page_Mng_Subscription/Version displayed on page'))

//System.out.println(cur_version2)
String exp_version = 'Beta Version 1.0'

if (cur_version2.equalsIgnoreCase(exp_version)) {
    System.out.println(cur_version2)
}

//System.out.println(WebUI.verifyElementPresent(findTestObject('Object Repository/Page_Mng_Subscription/Page_test/button_Invite User'), 3))
CustomKeywords.'nachPay.user.closeBrowser'()
