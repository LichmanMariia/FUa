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
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FUaTestList {
	private static WebDriver driver;
	private static WebDriverWait driverWait;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//System.setProperty("webdriver.opera.driver", "D:\\QA\\Selenium\\operadriver_win64\\operadriver.exe");
		//OperaOptions options = new OperaOptions(); 
		//options.setBinary("C:\\Users\\Lenovo\\AppData\\Local\\Programs\\Opera\\54.0.2952.60");
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
			driver.findElement(By.id("user_auth_form")).findElement(By.name("email")).sendKeys("spamformari@gmail.com");
			driver.findElement(By.name("password")).sendKeys("qwe123qwe123");
			driver.findElement(By.xpath("//form[@id='user_auth_form']/div[3]/input")).click();
						
			driver.switchTo().window(parentWindow);
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ga-master-id")))); // ожидания загрузки главной страницы с авторизацией
						
			driver.findElement(By.linkText("Телефоны, ноуты, планшеты")).click();
			
			driver.findElement(By.linkText("Мобильные телефоны")).click();
			driver.findElement(By.linkText("Смартфон SAMSUNG Galaxy J7 J710F/DS")).click();
			driver.findElement(By.xpath("//li[@class='compare']/a")).click(); // сравнение
			
			driver.findElement(By.xpath("//div[@class='buy_button']/a[@class='btn btn-large btn-green with-icon add2cart_4726511 btn-green']")).click(); // добавление в корзину
			String parentWindow3 = driver.getWindowHandle();
			Set<String> allPopupWindows3 = driver.getWindowHandles();
			for (String popupWindow3 : allPopupWindows3) {
				driver.switchTo().window(popupWindow3);
		       driver.findElement(By.linkText("продолжить покупки")).click();
		    driver.switchTo().window(parentWindow3);
		    			
			driver.findElement(By.xpath("//input[@type='search'][@name='qr']")).sendKeys("Samsung galaxy S9");
			WebDriverWait wait11 = new WebDriverWait(driver, 10);
			wait11.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@class='btn btn-right'][@type='submit']"))));
			
			driver.findElement(By.id("head_search_form")).findElement(By.tagName("input")).click();
			driver.findElement(By.linkText("Мобильные телефоны")).click();
			driver.findElement(By.linkText("Смартфон SAMSUNG Galaxy S9 G960F/DS")).click(); 
			
			driver.findElement(By.xpath("//li[@class='compare']/a")).click(); // добавление для сравнения
			WebDriverWait wait1 = new WebDriverWait(driver, 5);
			wait1.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ga-master-id"))));
			
			driver.findElement(By.linkText("В закладки")).click(); // добавление в закладки
			String parentWindow1 = driver.getWindowHandle();
			Set<String> allPopupWindows1 = driver.getWindowHandles();
			for (String popupWindow1 : allPopupWindows1) {
				driver.switchTo().window(popupWindow1);
			driver.findElement(By.name("new_group_first")).sendKeys("phone");
			driver.findElement(By.xpath("//form[@id='add2wishlist_form']/div[1]/input[2]")).click();
			driver.switchTo().window(parentWindow1);
				
			WebDriverWait wait2 = new WebDriverWait(driver, 10);
			wait2.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ga-master-id"))));
			
			WebElement main = driver.findElement(By.id("ga-master-id"));
			main.click();
			//driver.findElement(By.xpath("//div[@class='title']")).findElement(By.xpath("//div[@class='compare_list']")).findElement(By.xpath("//a[@class='category']")).click(); //переход к сравнению
			driver.get("https://f.ua/compare/45/");
			WebElement compare1 = driver.findElement(By.xpath("//td[@class='lborder tborder cp-title pid-4726511']"));
			WebElement compare11 = compare1.findElement(By.linkText("Samsung Galaxy J7 J710F/DS Black"));
			String text1 = compare11.getText();
			assertEquals("Samsung Galaxy J7 J710F/DS Black", text1);
			
			WebElement compare2 = driver.findElement(By.xpath("//td[@class='lborder tborder cp-title pid-5375656']"));
			WebElement compare22 = compare2.findElement(By.linkText("Samsung Galaxy S9 G960F/DS Titanium Gray"));
			String text2 = compare22.getText();
			assertEquals("Samsung Galaxy S9 G960F/DS Titanium Gray", text2);
			
			driver.get("https://f.ua/basket/");
				WebElement card = driver.findElement(By.id("cart_container"));
				WebElement card1 = card.findElement(By.xpath("//div[@class='cart_title']"));
				String text = card1.getText();
				assertEquals("Вы добавили 1 товар на сумму " + " " + "грн", text);
				
			}
			}
		}
	}
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		driver.findElement(By.xpath("//div[@class='compare_block']")).findElement(By.xpath("//a[@class='delete']")).click();
				WebElement classElement  = driver.findElement(By.xpath("//div[@class='user_block']")); 
				WebElement aTag = driver.findElement(By.tagName("a"));
				String a = aTag.getAttribute("href");
				driver.get(a);
				driver.get("https://f.ua/user/info/");
				driver.findElement(By.className("wishlist")).click();
				driver.findElement(By.xpath("//div[@class='lk_wishlist_detail_head']/span")).click();
				
				String parentWindow2 = driver.getWindowHandle();
				Set<String> allPopupWindows2 = driver.getWindowHandles();
				for (String popupWindow2 : allPopupWindows2) {
					driver.switchTo().window(popupWindow2); 
					driver.findElement(By.id("popup_content")).findElement(By.xpath("//div[@class='callback_form']")).findElement(By.xpath("//div[@style='text-align: center;']")).findElement(By.linkText("Удалить")).click();
						}
				driver.switchTo().window(parentWindow2);
				WebDriverWait wait2 = new WebDriverWait(driver, 5);
				wait2.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ga-master-id"))));
				driver.findElement(By.xpath("//div[@class='user_lk_right_block user_lk_right_block_personal']")).findElement(By.xpath("//ul[@class='ul user_params']")).findElement(By.xpath("//div[@class='exit_button']")).findElement(By.linkText("Выйти из кабинета")).click();
				driver.get("https://f.ua/basket/");
				driver.findElement(By.xpath("//td[@class='delete']")).findElement(By.tagName("span")).click();
				driver.findElement(By.cssSelector("div.compare_block > div.compare_list > a.delete")).click();
				
				driver.quit();
			}
	}


