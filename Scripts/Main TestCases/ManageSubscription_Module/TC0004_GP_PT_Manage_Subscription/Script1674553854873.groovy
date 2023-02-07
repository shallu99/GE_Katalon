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

import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.WebElement;


try {
CustomKeywords.'giroPie.user.LoginwithUsernamePwd'()

CustomKeywords.'giroPie.user.NavigatetoManageSubscriptionPage'()

//System.out.println(WebUI.verifyElementPresent(findTestObject('Object Repository/Page_Mng_Subscription/text field with current version'), 
//        3))

WebDriver driver = DriverFactory.getWebDriver()

String cur_version_EndofPage = driver.findElement(By.xpath("//*[@id='gsh0']/div/div[2]")).getText();
//System.out.println(cur_version_EndofPage)


WebElement searchTextBox = driver.findElement(By.xpath('//*[@value="'+cur_version_EndofPage+'"]'));
String titleValue = searchTextBox.getAttribute("value");
//System.out.println("Value of the attribute: " + titleValue);

if (cur_version_EndofPage.equalsIgnoreCase(titleValue)) {
    System.out.println("Current version of the application is "+titleValue)
}
}finally {
CustomKeywords.'giroPie.user.closeBrowser'()
}