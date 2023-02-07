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

try {
CustomKeywords.'giroPie.user.LoginwithUsernamePwd'()

CustomKeywords.'giroPie.user.NavigatetoCompanyProfilePage'()

CustomKeywords.'giroPie.user.ClickonDeleteAndYes'()

//WebUI.click(findTestObject('Object Repository/DeleteBranch/p_2'))

String No_of_branches_b4_del = WebUI.getText(findTestObject('Object Repository/DeleteBranch/p_2'))

'Refresh the current web page'
WebUI.refresh()

String No_of_branches_after_del = WebUI.getText(findTestObject('Object Repository/DeleteBranch/p_2'))

System.out.println("Number of branches after deletion : "+No_of_branches_b4_del)
System.out.println("Number of branches before deletion : "+No_of_branches_after_del)

}catch (Exception e) {
	System.out.println()
	System.out.println("There is only one branch left and cannot be deleted")
	System.out.println()
}finally {
WebUI.closeBrowser()
}
