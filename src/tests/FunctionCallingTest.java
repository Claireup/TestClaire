package tests;

import java.util.regex.Pattern;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import HotelApp_BusinessFunctions.HotelApp_BusinessFunctions;

public class FunctionCallingTest extends HotelApp_BusinessFunctions {
  //private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
 // public Properties prop;

  
  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://adactin.com";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    prop = new Properties();
	prop.load(new FileInputStream("./SharedUIMap/SharedUIMap.properties"));
  }

  @Test
  public void testMySecondWebDriver() throws Exception {
	driver.get(baseUrl + "/HotelApp/index.php");
	//driver.findElement(By.xpath(prop.getProperty("Txt_Login_Username"))).sendKeys("Claireup");
	//driver.findElement(By.id(prop.getProperty("Txt_Login_Password"))).sendKeys("Selenium16");
	//driver.findElement(By.name(prop.getProperty("Btn_Login_Login"))).click();
	//Comment- Call to Login Function
	 HA_BF_Login (driver, "Claireup","Selenium16");
	// HA_Search_Hotel();
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
