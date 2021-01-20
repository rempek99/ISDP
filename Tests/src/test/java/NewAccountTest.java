import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.TestInstance;

import java.util.HashMap;
import java.util.Map;
public class NewAccountTest {
  private WebDriver driver;
  @Before
  public void setUp() {
    System.setProperty("webdriver.gecko.driver", "/home/student/geckodriver");
    FirefoxBinary firefoxBinary = new FirefoxBinary();
    FirefoxOptions options = new FirefoxOptions();
    options.setBinary(firefoxBinary);
    options.setHeadless(true);
    driver = new FirefoxDriver(options);
  }

  @Test
  public void test1() {
 
    driver.get("https://localhost:8181/faces/main/index.xhtml");
 
    // Logowanie jako Admin
    driver.findElement(By.linkText("Logowanie")).click();
    driver.findElement(By.name("j_username")).click();
    driver.findElement(By.name("j_username")).sendKeys("DMitchell");
    driver.findElement(By.name("j_password")).click();
    driver.findElement(By.name("j_password")).sendKeys("P@ssw0rd");
    driver.findElement(By.cssSelector("input:nth-child(2)")).click();
 
    // Rejestracja Nowego użytkownika
    driver.get("https://localhost:8181/faces/common/registerAccount.xhtml");
    driver.findElement(By.id("RegisterForm:name")).click();
    driver.findElement(By.id("RegisterForm:name")).sendKeys("Test");
    driver.findElement(By.id("RegisterForm:surname")).sendKeys("Testowe");
    driver.findElement(By.id("RegisterForm:email")).sendKeys("test1@test.com");
    driver.findElement(By.id("RegisterForm:login")).sendKeys("TTestowe");
    driver.findElement(By.id("RegisterForm:password")).sendKeys("Test1234@");
    driver.findElement(By.id("RegisterForm:passwordRepeat")).sendKeys("Test1234@");
    driver.findElement(By.id("RegisterForm:question")).sendKeys("Are you tester?");
    driver.findElement(By.id("RegisterForm:answer")).sendKeys("yes");
    driver.findElement(By.name("RegisterForm:j_idt36")).click();
    driver.findElement(By.linkText("Konto użytkownika")).click();
    driver.findElement(By.linkText("Lista nowych kont")).click();
    Assert.assertTrue(driver.getPageSource().contains("TTestowe"));

// Usuwanie Testowego Konta
    var table = driver.findElement(By.className("table"));
    var rows = table.findElements(By.tagName("tr"));
    driver.findElement(By.xpath("/html/body/div/div[3]/div/form/table/tbody/tr[" + String.valueOf(rows.size()-1) +"]/td[5]/input[2]")).click();
    System.out.println(rows.size());
    driver.findElement(By.xpath("/html/body/div/div[3]/div/form/input[2]")).click();

    Assert.assertFalse(driver.getPageSource().contains("TTestowe"));
    driver.findElement(By.linkText("Konto użytkownika")).click();
    driver.findElement(By.linkText("Lista kont użytkowników")).click();
    //Assert that User is created
    Assert.assertFalse(driver.getPageSource().contains("TTestowe"));
    driver.findElement(By.linkText("Wylogowanie")).click();
    driver.findElement(By.name("j_idt26:j_idt30")).click();
    Assert.assertTrue(driver.getPageSource().contains("Uwierzytelniony użytkownik: brak autoryzacji"));
  }
  @After
  public void tearDown() {
    driver.quit();
  }
}
