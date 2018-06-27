import com.sun.javafx.PlatformUtil;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class HotelBookingTest {

	WebDriver driver;
	DesiredCapabilities dc;
	SignInTest test=new SignInTest();

	@FindBy(xpath = "//a[text()='Hotels']")
	private WebElement hotelLink;

	@FindBy(id = "Tags")
	private WebElement localityTextBox;

	@FindBy(id = "SearchHotelsButton")
	private WebElement searchButton;

	@FindBy(id = "travellersOnhome")
	private WebElement travellerSelection;

	@SuppressWarnings("deprecation")
	@Test
	public void shouldBeAbleToSearchForHotels() {
		dc = test.getDriver();
		test.setDriverPath();
		driver = new ChromeDriver(dc);
		PageFactory.initElements(driver,this);

		driver.get("https://www.cleartrip.com/");
		driver.manage().window().maximize();

		hotelLink.click();

		localityTextBox.sendKeys("Indiranagar, Bangalore");

		new Select(travellerSelection).selectByVisibleText("1 room, 2 adults");
		searchButton.click();

		driver.quit();

	}

}
