package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;

public class ProductInfoTest extends BaseTest{
	
	@BeforeClass
	public void accountPageSetUp() {
		accountPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	public void goToProductInfoPage(String productName) {
		// String productName = "iMac";
		accountPage.doSearch(productName);
		productInfoPage = accountPage.selectProductFromResults(productName);
	}
	@Test
	public void productInfoPageTitletest() {
		accountPage.doSearch("iMac");
		productInfoPage = accountPage.selectProductFromResults("iMac");
		Assert.assertEquals(productInfoPage.getProductInfoPageTitle("iMac"), "iMac");
	}

	@Test
	public void verifyProductInfotest() {
		String productName = "iMac";
		accountPage.doSearch("iMac");
		productInfoPage = accountPage.selectProductFromResults("iMac");
		Assert.assertTrue(productInfoPage.getProductImages()==3);
	}
	
	@Test
	public void verifyProductImageTest() {

		Assert.assertTrue(productInfoPage.getProductImages() == 3);
	}

	@Test
	public void verifyProductInfoTest() {
		String productName = "iMac";
		goToProductInfoPage(productName);
		Map<String, String> productInfoMap = productInfoPage.getProductInformation();

		System.out.println(productInfoMap);
		productInfoMap.forEach((k, v) -> System.out.println(k + " : " + v));

		/**
		 * Brand Apple 
		 * Availability Out Of Stock 
		 * price $100.00 
		 * name iMac 
		 * Product Code Product 14
		 * exTaxPrice $100.00
		 */
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(productInfoMap.get("name"), productName);
		softAssert.assertEquals(productInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(productInfoMap.get("price"), "$100.00");
		softAssert.assertEquals(productInfoMap.get("Product Code"), "Product 14");
		softAssert.assertAll();

	}
}
