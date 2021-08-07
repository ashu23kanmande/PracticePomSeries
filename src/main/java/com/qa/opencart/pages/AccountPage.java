package com.qa.opencart.pages;

import java.util.List;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountPage {

	private WebDriver driver;
	private ElementUtil elementUtil;
	private By header = By.cssSelector("div#logo a");
	private By actionHeaderList = By.cssSelector("div#content h2");
	private By searchText = By.cssSelector("div#search input[name='search']");
	private By searchButton = By.cssSelector("div#search button[type='button']");
	private By searchItemResult = By.cssSelector("div.product-layout div.product-thumb");
	private By resultsItems = By.cssSelector("div.product-thumb h4 a");

	public AccountPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}

	public String getAccountsPageTitle() {
		return elementUtil.waitForPageTitlePresent(Constants.ACCOUNT_PAGE_TITLE, 5);
	}

	public int getAccountsSectionsCount() {
		return elementUtil.getElements(actionHeaderList).size();

	}

	public String getHeader() {
		if (elementUtil.doIsDisplayed(header)) {
			return elementUtil.doGetText(header);
		}

		return null;

	}

	public List<String> getAccountSectionsList() {
		List<String> accList = new ArrayList<>();
		List<WebElement> accsectionList = elementUtil.getElements(actionHeaderList);

		for (WebElement e : accsectionList) {
			String section = e.getText();
			System.out.println(section);
			accList.add(section);
		}

		return accList;
	}

	public boolean doSearch(String productName) {
		elementUtil.doSendKeys(searchText, productName);
		elementUtil.doClick(searchButton);
		if (elementUtil.getElements(searchItemResult).size() > 0) {
			return true;
		}

		return false;
	}

	public ProductInfoPage selectProductFromResults(String productName) {
		List<WebElement> resultItemsList = elementUtil.getElements(resultsItems);
		System.out.println("total number of items displyed:" + resultItemsList.size());

		for (WebElement e : resultItemsList) {
			if (e.getText().equals(productName)) {
				e.click();
				break;
			}
		}
		
		return new ProductInfoPage(driver);
	}

}
