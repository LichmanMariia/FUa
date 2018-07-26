package org.itstep;

import static org.junit.Assert.*;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FUaRegistratonTest {

	private static WebDriver driver;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//System.setProperty("webdriver.opera.driver", "D:\\QA\\Selenium\\operadriver_win64\\operadriver.exe");
				//OperaOptions options = new OperaOptions(); 
				//options.setBinary("C:\\\\Users\\\\Lenovo\\\\AppData\\\\Local\\\\Programs\\\\Opera\\\\54.0.2952.41\\\\opera.exe"); // указать путь установки Оперы
				//driver = new OperaDriver(options); 
				
				//System.setProperty("webdriver.gecko.driver", "D:\\QA\\Selenium\\geckodriver.exe"); 
				//DesiredCapabilities capabilities = DesiredCapabilities.firefox(); 
				//capabilities.setCapability("marionette", true); 
				//FirefoxOptions option = new FirefoxOptions(); 
				//option.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
				//driver = new FirefoxDriver(option);
				
				//System.setProperty("webdriver.edge.driver","D:\\QA\\Selenium\\MicrosoftWebDriver.exe"); 
				//driver = new EdgeDriver();
		
		System.setProperty("webdriver.chrome.driver", "D:\\QA\\Selenium\\chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://f.ua/");
	}

	@Test
	public void test() {
		driver.findElement(By.xpath("//a[@class='title not_auth']")).click();	
		String parentWindow = driver.getWindowHandle();
		Set<String> allPopupWindows = driver.getWindowHandles();
		for (String popupWindow : allPopupWindows) {
			driver.switchTo().window(popupWindow);
						
			 driver.findElement(By.linkText("Создать новый профиль")).click();
		}
			
			driver.switchTo().window(parentWindow);
			String parentwindow1 = driver.getWindowHandle();
			Set<String> allPopupWindows1 = driver.getWindowHandles();
			for (String popupWindow : allPopupWindows1) {
				driver.switchTo().window(popupWindow); 
									
				driver.findElement(By.xpath("//form[@id='register_form']/div[1]/div[2]/input")).sendKeys("Mari");
				driver.findElement(By.xpath("//form[@id='register_form']/div[2]/div[2]/input")).sendKeys("spamformari@gmail.com");
				
				driver.findElement(By.name("password")).sendKeys("qwe123qwe123");
				driver.findElement(By.name("password2")).sendKeys("qwe123qwe123");
				driver.findElement(By.id("register_form")).findElement(By.name("save")).click();
				
				
				

		}
	}
	
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		driver.findElement(By.id("ga-master-id")).click();
		
		driver.quit();
	}

}

