package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;

public class LoginPageTest extends BaseTest {

	@Test
	public void loginPageTitle() {
		String title = loginPage.getLoginPageTitle();
		
		System.out.println("login page title is : " + title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}

	@Test
	public void forgotpwdLinkExist() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExists());
	}
	
	@Test
	public void loginTest() {
		accountPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accountPage.getAccountsPageTitle() ,Constants.ACCOUNT_PAGE_TITLE);

	}

}
