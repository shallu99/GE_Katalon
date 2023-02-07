package giroPie

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

public class user {

	//Global variables
	//	String url = 'https://staging.peppertree.ai/'
	//	String username = 'uthukpnt@gmail.com'
	//	String password = 'Peppertree@123'

	@Keyword
	def lauchBrowser(url) {
		WebUI.openBrowser('')
		WebUI.navigateToUrl(url)
		WebUI.maximizeWindow()
	}

	@Keyword
	def userLogin(user_obj,username, pass_obj, password, submit_btn_obj) {
		WebUI.setText(findTestObject(user_obj), username)
		WebUI.setText(findTestObject(pass_obj),password)
		WebUI.click(findTestObject(submit_btn_obj))
	}

	@Keyword
	def userLogout() {
		WebUI.click(findTestObject('Object Repository/Page_NachPay/img_Invite_MuiAvatar-img css-1hy9t21'))
		WebUI.click(findTestObject('Object Repository/Page_NachPay/p_Sign Out'))
		WebUI.click(findTestObject('Object Repository/Page_NachPay/button_Yes'))
	}

	@Keyword
	def closeBrowser() {
		WebUI.closeBrowser()
	}

	@Keyword
	def LoginwithUsernamePwd() {

		//Local variables
		String url = 'https://staging.peppertree.ai/'
		String username = 'ajay@uth-uk.com'
		String password = 'Tree@2023'

		WebUI.callTestCase(findTestCase('Common_TestCases/TC0001_GP_PT_Login'), [('url') : url, ('username') : username, ('password') : password],
		FailureHandling.STOP_ON_FAILURE)
	}

	@Keyword
	def ClickonDeleteAndYes() {
		WebUI.click(findTestObject('Object Repository/DeleteBranch/svg_Branches_MuiSvgIcon-root MuiSvgIcon-fon_148d01'))
		WebUI.click(findTestObject('Object Repository/DeleteBranch/button_Yes'))
	}

	@Keyword
	def ClickonDeleteAndYesinBankPage() {
		WebUI.click(findTestObject('Object Repository/Delete_Trial/Page_GIROPie/svg_Create Physical-Mandate_MuiSvgIcon-root MuiSvgIcon-fontSizeMedium css-101fpco'))
		WebUI.click(findTestObject('Object Repository/Delete_Trial/Page_GIROPie/button_Yes'))
	}

	@Keyword
	def NavigatetoBankPage() {
		WebUI.click(findTestObject('Object Repository/Bank/svg_UTH_MuiSvgIcon-root MuiSvgIcon-fontSize_e12007'))
		WebUI.click(findTestObject('Object Repository/Bank/li_Bank Profile'))
	}

	@Keyword
	def NavigatetoCompanyProfilePage() {
		WebUI.click(findTestObject('Object Repository/DeleteBranch/div_UTH'))
		WebUI.click(findTestObject('Object Repository/DeleteBranch/div_UTH_1'))
	}


	@Keyword
	def NavigatetoManageSubscriptionPage() {
		WebUI.click(findTestObject('Object Repository/Page_Mng_Subscription/UTH_Account_name'))
		WebUI.click(findTestObject('Object Repository/Page_Mng_Subscription/Manage Subscriptions dropdown'))
	}

	@Keyword
	def ClickOnInviteButtonPresentOnHomePage() {
		WebUI.click(findTestObject('Object Repository/InviteSeller/button_Invite'))
		'Verfiy the text "Invite Individuals" below here which is not working as of now'
		//WebUI.verifyElementPresent(findTestObject('Object Repository/InviteSeller/button_Invite Individual'))
	}

	@Keyword
	def EnterBuyerDetails(String CompanyName='', String PAN_GSTIN='', String Email='', String Phone='') {
		WebUI.click(findTestObject('Object Repository/InviteSeller/input_Invite Bulk_PrivateSwitchBase-input c_7e24b0'))
		WebUI.setText(findTestObject('Object Repository/InviteSeller/input_As Seller_r5'), CompanyName)
		WebUI.setText(findTestObject('Object Repository/InviteSeller/input_As Seller_r6'), PAN_GSTIN)
		WebUI.setText(findTestObject('Object Repository/InviteSeller/input_As Seller_r7'), Email)
		WebUI.setText(findTestObject('Object Repository/InviteSeller/input_As Seller_r8'), Phone)
		//WebUI.click(findTestObject('Object Repository/InviteSeller/button_Invite_1'))

	}

	@Keyword
	def NavigatetoUserManagementPage() {
		WebUI.click(findTestObject('Object Repository/InviteUser/img_Invite_MuiAvatar-img css-1hy9t21'))
		WebUI.click(findTestObject('Object Repository/InviteUser/li_User Management'))
	}

	@Keyword
	def ClickOnInviteUserButtonPresentOnUserMgntPage() {
		WebUI.click(findTestObject('Object Repository/InviteUser/button_Invite User'))
		'Verfiy the text "Invite Individuals" below here which is not working as of now'
		//WebUI.verifyElementPresent(findTestObject('Object Repository/InviteUser/button_Invite User_1'))
	}

	@Keyword
	def EnterUserDetails() {
		WebUI.setText(findTestObject('Object Repository/InviteUser/input_Invite User Bulk_MuiInputBase-input M_bcd2f6'), 'Ztest@gmail.com')
		WebUI.click(findTestObject('Object Repository/InviteUser/li_Main Branch'))
		WebUI.setText(findTestObject('Object Repository/InviteUser/input__ra'), '8553399911')
		WebUI.setText(findTestObject('Object Repository/InviteUser/input_Invite User Bulk_MuiInputBase-input M_bcd2f6'), 'Tester')
		//WebUI.click(findTestObject('Object Repository/InviteUser/button_Invite User_1_2'))
	}

}
