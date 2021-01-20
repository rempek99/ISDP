// Generated by Selenium IDE

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
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
    driver = new FirefoxDriver();
  }

  @Test
  public void test1() {
    // Test name: Test1
    // Step # | name | target | value
    // 1 | open | /faces/main/index.xhtml |
    driver.get("https://localhost:8181/faces/main/index.xhtml");
    // 3 | click | linkText=Logowanie |

    // Logowanie jako Admin
    driver.findElement(By.linkText("Logowanie")).click();
    // 4 | click | name=j_username |
    driver.findElement(By.name("j_username")).click();
    // 7 | type | name=j_username | DMitchell
    driver.findElement(By.name("j_username")).sendKeys("DMitchell");
    // 8 | click | name=j_password |
    driver.findElement(By.name("j_password")).click();
    // 9 | type | name=j_password | P@ssw0rd
    driver.findElement(By.name("j_password")).sendKeys("P@ssw0rd");
    // 10 | click | css=input:nth-child(2) |
    driver.findElement(By.cssSelector("input:nth-child(2)")).click();
    // 11 | click | linkText=Rejestracja konta |

    // Rejestracja Nowego użytkownika
    driver.findElement(By.linkText("Rejestracja konta")).click();
    // 12 | click | id=RegisterForm:name |
    driver.findElement(By.id("RegisterForm:name")).click();
    // 13 | type | id=RegisterForm:name | Test
    driver.findElement(By.id("RegisterForm:name")).sendKeys("Test");
    // 14 | type | id=RegisterForm:surname | Testowy
    driver.findElement(By.id("RegisterForm:surname")).sendKeys("Testowy");
    // 15 | type | id=RegisterForm:email | test@test.com
    driver.findElement(By.id("RegisterForm:email")).sendKeys("test@test.com");
    // 16 | type | id=RegisterForm:login | TTestowy
    driver.findElement(By.id("RegisterForm:login")).sendKeys("TTestowy");
    // 17 | type | id=RegisterForm:password | Test1234!
    driver.findElement(By.id("RegisterForm:password")).sendKeys("Test1234!");
    // 18 | type | id=RegisterForm:passwordRepeat | Test1234!
    driver.findElement(By.id("RegisterForm:passwordRepeat")).sendKeys("Test1234!");
    // 19 | type | id=RegisterForm:question | Are you tester?
    driver.findElement(By.id("RegisterForm:question")).sendKeys("Are you tester?");
    // 20 | type | id=RegisterForm:answer | yes
    driver.findElement(By.id("RegisterForm:answer")).sendKeys("yes");
    // 21 | click | name=RegisterForm:j_idt36 |
    driver.findElement(By.name("RegisterForm:j_idt36")).click();
    // 22 | click | linkText=Konto użytkownika |
    driver.findElement(By.linkText("Konto użytkownika")).click();
    // 23 | click | linkText=Lista nowych kont |
    driver.findElement(By.linkText("Lista nowych kont")).click();
    // 24 | click | name=j_idt26:j_idt27:5:j_idt37 |
    Assert.assertTrue(driver.getPageSource().contains("TTestowy"));
    driver.findElement(By.name("j_idt26:j_idt27:5:j_idt37")).click();
    // 25 | select | name=j_idt26:j_idt27:5:j_idt37 | label=Biuro
    {
      WebElement dropdown = driver.findElement(By.name("j_idt26:j_idt27:5:j_idt37"));
      dropdown.findElement(By.xpath("//option[. = 'Biuro']")).click();
    }
    // 26 | click | css=tr:nth-child(6) option:nth-child(2) |
    driver.findElement(By.cssSelector("tr:nth-child(6) option:nth-child(2)")).click();
    // 27 | click | name=j_idt26:j_idt27:5:j_idt40 |
    driver.findElement(By.name("j_idt26:j_idt27:5:j_idt40")).click();
    // 28 | click | linkText=Konto użytkownika |
    driver.findElement(By.linkText("Konto użytkownika")).click();
    // 29 | click | linkText=Lista kont użytkowników |
    driver.findElement(By.linkText("Lista kont użytkowników")).click();
    //Assert that User is created
    Assert.assertTrue(driver.getPageSource().contains("TTestowy"));
    // 30 | click | linkText=Wylogowanie |
    driver.findElement(By.linkText("Wylogowanie")).click();
    // 31 | click | name=j_idt26:j_idt30 |
    driver.findElement(By.name("j_idt26:j_idt30")).click();

    Assert.assertTrue(driver.getPageSource().contains("Uwierzytelniony użytkownik: brak autoryzacji"));
  }
  @After
  public void tearDown() {
    // driver.quit();
  }
}
