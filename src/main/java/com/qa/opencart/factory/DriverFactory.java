package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.apache.log4j.Logger;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author Ashwini
 *
 */
public class DriverFactory {
	
	private static final Logger LOGGER = Logger.getLogger(String.valueOf(DriverFactory.class));
	WebDriver driver;
	Properties prop;
	public static String highlight;
	public OptionsManager optionsManager;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

	public WebDriver init_driver(String browserName) {

		/*
		 * String browserName = prop.getProperty("browser"); highlight =
		 * prop.getProperty(highlight);
		 */
		System.out.println("browser name is " + browserName);
		LOGGER.info("browser name is "+browserName );
		optionsManager = new OptionsManager(prop);

		if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			LOGGER.info("chrome browser setup");
			// driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));

		} else if (browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			LOGGER.info("ff browser setup");
			// driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));

		} else if (browserName.equals("safari")) {
			driver = new SafariDriver();
		} else {
			System.out.println("please pass correct browser name" + browserName);
		}

		getDriver().manage().window().fullscreen();
		getDriver().manage().deleteAllCookies();

		return getDriver();

	}
	
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	public Properties init_prop() {
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream(".\\src\\test\\resources\\config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}
	
	/**
	 * take sceenshot Ashot
	 */
	public String getScreenshot() {
		String src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);
		File srcFile = new File(src);
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			//Logger.error("some exception is coming while creating the screenshot");
			e.printStackTrace();
		}
		return path;
	}
}
