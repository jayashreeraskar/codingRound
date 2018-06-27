import com.sun.javafx.PlatformUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class FlightBookingTest {

	WebDriver driver;
	DesiredCapabilities dc;
	SignInTest test =new SignInTest();
	@Test
	public void testThatResultsAppearForAOneWayJourney() {

		dc = test.getDriver();
		test.setDriverPath();
		driver = new ChromeDriver(dc);
		driver.get("https://www.cleartrip.com/");
		driver.manage().window().maximize();

		test.waitFor(2000);
		driver.findElement(By.id("OneWay")).click();

		driver.findElement(By.id("FromTag")).clear();
		driver.findElement(By.id("FromTag")).sendKeys("Bangalore");

		// wait for the auto complete options to appear for the origin

		test.waitFor(2000);
		List<WebElement> originOptions = driver.findElement(By.id("ui-id-1"))
				.findElements(By.tagName("li"));
		originOptions.get(0).click();
		driver.findElement(By.xpath("//input[@id='To']//preceding::input[@id='ToTag']")).clear();
		driver.findElement(By.xpath("//input[@id='To']//preceding::input[@id='ToTag']")).sendKeys("Delhi");

		// wait for the auto complete options to appear for the destination

		test.waitFor(2000);
		// select the first item from the destination auto complete list
		List<WebElement> destinationOptions = driver.findElement(
				By.id("ui-id-2")).findElements(By.tagName("li"));
		destinationOptions.get(0).click();

		driver.findElement(
				By.xpath("//div[@class='monthBlock first']/table/tbody/tr[5]/td[5]/a"))
				.click();

		// all fields filled in. Now click on search
		driver.findElement(By.id("SearchBtn")).click();

		test.waitFor(5000);
		// verify that result appears for the provided journey search
		Assert.assertTrue(isElementPresent(By.className("searchSummary")));

		// close the browser
		driver.quit();

	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

}
