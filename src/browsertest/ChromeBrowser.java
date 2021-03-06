package browsertest;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;

public class ChromeBrowser {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    baseUrl = "http://adactin.com/";
//    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    
    //System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/Drivers/chromedriver.exe");
    //driver = new ChromeDriver();
    System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "/Drivers/IEDriverServer.exe");
    driver = new InternetExplorerDriver();
  }

  @Test
  public void testMySecondWebDriver() throws Exception {
    driver.get(baseUrl + "/HotelApp/index.php");
    driver.findElement(By.id("username")).clear();
    //driver.findElement(By.id("username")).sendKeys("Claireup");
    driver.findElement(By.cssSelector("#username")).sendKeys("Claireup");
    driver.findElement(By.id("password")).clear();
    //driver.findElement(By.id("password")).sendKeys("Selenium16");
    driver.findElement(By.xpath("//*[@id='password']")).sendKeys ("Selenium16");
    driver.findElement(By.id("login")).click();
    new Select(driver.findElement(By.id("location"))).selectByVisibleText("Melbourne");
    new Select(driver.findElement(By.id("room_type"))).selectByVisibleText("Standard");
    driver.findElement(By.id("Submit")).click();
    driver.findElement(By.id("cancel")).click();
    driver.findElement(By.linkText("Logout")).click();
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
