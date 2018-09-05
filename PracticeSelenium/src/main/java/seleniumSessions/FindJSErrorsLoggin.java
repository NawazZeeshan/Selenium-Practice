package seleniumSessions;

import java.util.Date;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FindJSErrorsLoggin {
	
	public static WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		//DesiredCapabilities capabilites = new DesiredCapabilities();
		//one can write the above way and we are going to pass chrome directly without using new Desiredcapabilities
		  DesiredCapabilities capabilities =  DesiredCapabilities.chrome();
		  LoggingPreferences logginPreferences = new LoggingPreferences();
		  //enable loggin prefer to have browser specific log types with all level
		  logginPreferences.enable(LogType.BROWSER, Level.ALL);
		  capabilities.setCapability(CapabilityType.LOGGING_PREFS, logginPreferences);
		  
		  System.setProperty("webdriver.chrome.driver", "E:\\Program Files\\eclipse-workspace\\PracticeSelenium\\src\\main\\resources\\chromedriver.exe");
		  driver = new ChromeDriver(capabilities);
		  
	}
	
	public void extractJSLogsInfo() {
		LogEntries logEntries =driver.manage().logs().get(LogType.BROWSER);
		for(LogEntry entry:logEntries) {
			System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
			
		}
	}
	
	@Test
	public void testMethod() {
		driver.get("https://ezeepayservices.in:8447/backoffice/Authentication");
		driver.findElement(By.name("firstname")).sendKeys("admin1");
		driver.findElement(By.name("password")).sendKeys("ezeepay");
		driver.findElement(By.xpath("//input[contains(@value,'Sign in')]")).click();
		System.out.println("******************************************");
		driver.navigate().to("https://ezeepayservices.in:8447/backoffice/listtransaction");
		System.out.println("******************************************");
		driver.findElement(By.xpath("//i[contains(@class,'fa fa-money')]")).click();
		driver.findElement(By.xpath("//a[contains(@id,'createtxn')]")).click();
		System.out.println("******************************************");
		driver.navigate().refresh();
		driver.navigate().to("https://ezeepayservices.in:8447/backoffice/Ezeepay-CreateTxn");
		System.out.println("******************************************");
		extractJSLogsInfo();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
}
