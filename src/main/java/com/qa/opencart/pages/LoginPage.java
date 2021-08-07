package com.qa.opencart.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {
	
	private static final Logger LOGGER = Logger.getLogger(String.valueOf(LoginPage.class));
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	//by locator/object repository 	
	
	private By userName = By.id("input-email");
	private By password = By.id("input-password");
	private By loginButton = By.cssSelector("input[value=Login]");
	private By forgotPasswordlink = By.cssSelector("div.form-group a");
	private By registerLink = By.linkText("Register");
	
	//constructor of page class
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil  = new ElementUtil(this.driver);
	}
	
	public String getLoginPageTitle() {
		return elementUtil.waitForPageTitlePresent(Constants.LOGIN_PAGE_TITLE, 5);
	}
	
	public boolean isForgotPwdLinkExists(){
		return elementUtil.doIsDisplayed(forgotPasswordlink);
	}
	
	public AccountPage doLogin(String un, String pwd) {
		System.out.println("login with" + un + "" + pwd );	
		LOGGER.info("login with " + un + ""+ pwd);
		elementUtil.doSendKeys(userName, un);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doClick(loginButton);
		
		return new AccountPage(driver);
	}
	
	public RegisterPage navigatetoRigisterPage() {
		System.out.println("navigate to register page");
		LOGGER.info("navigate to register page");
		elementUtil.doClick(registerLink);
		 return new RegisterPage(driver);
	}

}
