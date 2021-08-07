package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest {

	@BeforeClass
	public void registerPageSetUp() {

		registerPage = loginPage.navigatetoRigisterPage();
	}

	@DataProvider
	public Object[][] getRegisterData() {
		Object data[][] = ExcelUtil.getTestData(Constants.Register_Sheet_Name);
		return data;
	}

	@Test(dataProvider="getRegisterData")
	public void userRegisetrationTest(String firstName,String lastName,String emailID,String phone,String password,String subscribe) {
	Assert.assertTrue(registerPage.accountRegistration(firstName, lastName, emailID, phone, password, subscribe));

	}
}
