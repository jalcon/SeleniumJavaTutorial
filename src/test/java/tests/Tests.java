
package tests;

import java.util.ArrayList;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import helpers.Screenshooter;
import helpers.WebDriverManager;
import pages.PageLogin;
import pages.PageLogon;
import pages.PageReservation;

public class Tests {

	private WebDriver driver;
	ArrayList<String> tabs;

	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
		driver = new ChromeDriver();

		//
		// for (int i = 0; i <= 800; i++) {
		// driver.manage().window().setPosition(new Point(i, i));
		// }
		driver.navigate().to("http://newtours.demoaut.com/");
		JavascriptExecutor javaScriptExecutor = (JavascriptExecutor) driver;
		String googleWindow = "window.open('http://www.google.es')";
		javaScriptExecutor.executeScript(googleWindow);
		tabs = new ArrayList<String>(driver.getWindowHandles());

	}

	@Test
	public void loginIncorrecto() {
		WebDriverManager.setWindowSize(driver, "maximized");
		driver.switchTo().window(tabs.get(1)).navigate().to("http://www.youtube.com/user/Draculinio");
		driver.switchTo().window(tabs.get(0));
		PageLogin pageLogin = new PageLogin(driver);
		PageLogon pageLogon = new PageLogon(driver);
		pageLogin.login("user", "user");
		pageLogon.assertLogonPage();

	}

	@Test
	public void login() {
		WebDriverManager.setWindowSize(driver, "fullscreen");
		PageLogin pageLogin = new PageLogin(driver);
		PageReservation pageReservation = new PageReservation(driver);
		pageLogin.login("mercury", "mercury");
		pageReservation.assertPage();
	}

	@Test
	public void pruebaTres() {
		WebDriverManager.setWindowSize(driver, 400, 400);
		PageLogin pageLogin = new PageLogin(driver);
		PageReservation pageReservation = new PageReservation(driver);
		pageLogin.login("mercury", "mercury");
		pageReservation.selectPassenger(2);
		pageReservation.selectFromPort(3);
		pageReservation.selectToPort("London");
	}

	@Test
	public void pruebaCantidadDeCampos() {
		PageLogin pageLogin = new PageLogin(driver);
		pageLogin.verifyFields();
	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		if (!result.isSuccess()) {
			Screenshooter.takeScreenshot("Error", driver);
		}
		driver.switchTo().window(tabs.get(1)).close();
		driver.switchTo().window(tabs.get(0)).close();
	}
}
