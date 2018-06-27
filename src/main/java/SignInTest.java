import java.util.Set;

import com.sun.javafx.PlatformUtil;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignInTest 
{

	WebDriver driver;
DesiredCapabilities dc;

    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {
        dc=getDriver();
         setDriverPath();
         driver=new ChromeDriver(dc);
         driver.get("https://www.cleartrip.com/");
         driver.manage().window().maximize();

        waitFor(2000);

        driver.findElement(By.linkText("Your trips")).click();
        
        String parent=driver.getWindowHandle();
       driver.findElement(By.id("SignIn")).click();
       
       waitFor(2000);

    	   driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='modal_window']")));
           WebDriverWait wait = new WebDriverWait(driver, 10);
           WebElement push_to_create = wait.until(ExpectedConditions
                   .elementToBeClickable(By
                           .id("signInButton")));

           driver.findElement(By.id("signInButton")).click();
        String errors1 = driver.findElement(By.id("errors1")).getText();
        Assert.assertTrue(errors1.contains("There were errors in your submission"));
        driver.quit();
    }

    void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @SuppressWarnings("restriction")
	public void setDriverPath() {
        if (PlatformUtil.isMac()) {
            System.setProperty("webdriver.chrome.driver", "chromewebdriver");
        }
        if (PlatformUtil.isWindows()) {
            System.setProperty("webdriver.chrome.driver", "webchromedriver.exe");
        }
        if (PlatformUtil.isLinux()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
        }
    }

    public  DesiredCapabilities getDriver()
    {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-popup-blocking");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

		return capabilities;


    }

}
