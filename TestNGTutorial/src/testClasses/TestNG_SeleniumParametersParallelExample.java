package testClasses;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestNG_SeleniumParametersParallelExample {
	
	WebDriver driver;
	String baseURL;
	
	@BeforeClass
	@Parameters({"browser"})
	public void setUp(String browser) {
		baseURL = "https://letskodeit.teachable.com";
		if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", 
					"D:\\selenium\\geckodriver-v0.29.0-win64\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "D:\\selenium\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(baseURL);
	}
	
	@Test
	public void testLogin() throws InterruptedException {
		WebElement loginLink = driver.findElement(By.xpath("//a[@href='/sign_in']"));
		loginLink.click();
		WebElement emailField = driver.findElement(By.id("user_email"));
		emailField.sendKeys("test@email.com");
		WebElement passwordField = driver.findElement(By.id("user_password"));
		passwordField.sendKeys("abcabc");
		WebElement loginButton = driver.findElement(By.name("commit"));
		loginButton.click();
	}
	
	@AfterClass
	public void cleanUp() {
		//driver.quit();
	}
}