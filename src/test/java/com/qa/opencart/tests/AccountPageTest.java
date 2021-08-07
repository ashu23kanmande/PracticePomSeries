package com.qa.opencart.tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountPageTest extends BaseTest{
	
	@BeforeClass
	public void accountPageSetUp() {
		accountPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority = 1)
	public void accountsPageTitleTest() {
		String title = accountPage.getAccountsPageTitle();
		System.out.println("acc page title is:" + title);
		Assert.assertEquals(title, Constants.ACCOUNT_PAGE_TITLE);
	}

	
	@Test(priority = 2)
	public void verifyAccountPageHeaderTest() {
		
		String header = accountPage.getHeader();
		System.out.println("header :" + header);
		Assert.assertEquals(header, Constants.ACCOUNT_PAGE_HEADER);
	}
	
	@Test(priority = 3)
	public void verifyAccountSectionsCountTest() {
		
		Assert.assertTrue(accountPage.getAccountsSectionsCount()==4);
	}
	

	@Test(priority = 4)
	public void verifyAccountsPageSectionsListTest() {
		
		List<String> accSectionList = accountPage.getAccountSectionsList();
		System.out.println(accSectionList);
		Assert.assertEquals(accSectionList, Constants.getAccSectionsList());
		
	}
	
	@Test(priority = 5)
	public void searchTest_iMac() {
		Assert.assertTrue(accountPage.doSearch("iMac"));
	}
	
	@Test(priority = 6)
	public void searchTest_MacBook() {
		Assert.assertTrue(accountPage.doSearch("MacBook Air"));
	}
	
	@Test(priority = 6)
	public void selectProducResultsTest() {
		accountPage.doSearch("iMac");
		accountPage.selectProductFromResults("iMac");
	}
}
