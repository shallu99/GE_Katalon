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

WebUI.openBrowser('')

WebUI.navigateToUrl('https://staging.peppertree.ai/')

WebUI.setText(findTestObject('Object Repository/AddBranch/input_Sign Up_r0'), 'uthukpnt@gmail.com')

WebUI.setEncryptedText(findTestObject('Object Repository/AddBranch/input__MuiInputBase-input MuiOutlinedInput-_047092'), 
    'QWaBvTLiu9ZovX8NOeYSWg==')

WebUI.click(findTestObject('Object Repository/AddBranch/div_Continue'))

WebUI.click(findTestObject('Object Repository/AddBranch/svg_UTH_MuiSvgIcon-root MuiSvgIcon-fontSize_e12007'))

WebUI.click(findTestObject('Object Repository/AddBranch/div_UTH'))

WebUI.click(findTestObject('Object Repository/AddBranch/p_Add Branch'))

WebUI.setText(findTestObject('Object Repository/AddBranch/input_Branches_branchName'), ' Sub')

WebUI.setText(findTestObject('Object Repository/AddBranch/input__gstin'), '29AABCU9603R1ZJ')

WebUI.setText(findTestObject('Object Repository/AddBranch/input__email'), 'uth@gmail.com')

WebUI.setText(findTestObject('Object Repository/AddBranch/input__contactName'), 'AA')

WebUI.setText(findTestObject('Object Repository/AddBranch/input__address'), 'ABC')

WebUI.setText(findTestObject('Object Repository/AddBranch/input__pin_code'), '560040')

WebUI.click(findTestObject('Object Repository/AddBranch/button_Submit'))

WebUI.click(findTestObject('Object Repository/AddBranch/p_1'))

WebUI.closeBrowser()

