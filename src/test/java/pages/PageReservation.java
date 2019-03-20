package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class PageReservation {

	private By passengersDrop;
	private By fromDrop;
	private By toDrop;
	private WebDriver driver;
	private By titleText;

	public PageReservation(WebDriver driver) {
		this.driver = driver;
		titleText = By.xpath(
						"/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[3]/td/font");
		passengersDrop = By.name("passCount");
		fromDrop = By.name("fromPort");
		toDrop = By.name("toPort");
	}

	public void assertPage() {
		Assert.assertTrue(driver.findElement(titleText).getText().contains("Flight Finder to search"));
	}

	public void selectPassenger(int cant) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement cantidadPasajeros = wait.until(ExpectedConditions.presenceOfElementLocated(passengersDrop));
		Select selectPasajeros = new Select(cantidadPasajeros);
		selectPasajeros.selectByVisibleText(Integer.toString(cant));
	}

	public void selectFromPort(int index) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement origenVuelo = wait.until(ExpectedConditions.presenceOfElementLocated(fromDrop));
		Select selectFrom = new Select(origenVuelo);
		selectFrom.selectByIndex(index);
	}

	public void selectToPort(String city) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement destinoVuelo = wait.until(ExpectedConditions.presenceOfElementLocated(toDrop));
		Select selectTo = new Select(destinoVuelo);
		selectTo.selectByValue(city);
	}
}
