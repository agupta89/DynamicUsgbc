package com.dynamicUsgbc.testcases;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.dynamicUsgbc.ReusableMethods.ReusableMethodPayment;
import com.dynamicUsgbc.ReusableMethods.ReusableMethodsCommunity;
import com.dynamicUsgbc.driver.BaseClass;
import com.dynamicUsgbc.driver.CommonMethod;

public class CommunityRegistrationFlowByCheckTest extends BaseClass{

	
	@Test
	@Parameters({"rowNum" ,"CommunityRegistrationSheet","SignInSheet"})
	public void CommunityRegistrationFlowByCheck(int rowNum, String CommRegSheet, String signinSheet) throws IOException {
		
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		CommonMethod.ExtentReportConfig();
		CommonMethod.test = CommonMethod.extent.startTest("Community Registration ByCheck", "Verifies if Community Registration is done successfully").assignCategory("CheckCommunityRegistration");
		CommonMethod.setUrl(CommunityRegistrationUrl);
		
		ReusableMethodsCommunity reuse = new ReusableMethodsCommunity();
		ReusableMethodPayment reusePay = new ReusableMethodPayment();
		
		try {
			
			reuse.CommunityRegistration(rowNum, CommRegSheet);
			reuse.SignIn(rowNum, signinSheet);
			reusePay.verifyCommRegPaymentDetails(rowNum, CommRegSheet);
			reusePay.PaymentByCheck();
			reusePay.verifyPaymentSuccessful();
			reuse.VerifyReceiptCommunity(rowNum,CommRegSheet );
			
            } 
		
		catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			CommonMethod.testlogError( "<pre>" + e1.toString() + "</pre>");
			CommonMethod.takeScreenshot("CommunityRegistrationFlowByCheck");
			throw e1;
		}
	}

}
