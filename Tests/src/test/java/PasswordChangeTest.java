import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Keys;

public class PasswordChangeTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private String host, signInPage, logoutPage;
    private String userName;
    private String userPassword, newPasswordForJDoe, newPasswordForDMitchell;

    @Before
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "/home/student/geckodriver");
        FirefoxBinary firefoxBinary = new FirefoxBinary();
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary(firefoxBinary);
        options.setHeadless(true);
        driver = new FirefoxDriver(options);
        host = "https://localhost:8181";
        signInPage = host + "/faces/common/signIn.xhtml";
        logoutPage = host + "/faces/common/logout.xhtml";
        userName = "JDoe";
        userPassword = "P@ssw0rd";
        newPasswordForJDoe = "P@ss123";
        newPasswordForDMitchell = "NEWP@ss123";
        wait = new WebDriverWait(driver, 10); //Webdriver wait object used to wait for certain interactions to be possible

    }
    @Test
    public void SignedInPassChangeTest() {

        driver.get(signInPage);

        Assert.assertTrue(driver.getPageSource().contains("Uwierzytelniony użytkownik: brak autoryzacji"));

        //logging in
        driver.findElement(By.linkText("Logowanie")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("j_username")));
        driver.findElement(By.name("j_username")).click();
        driver.findElement(By.name("j_username")).sendKeys(userName);
        driver.findElement(By.name("j_password")).sendKeys(userPassword);
        driver.findElement(By.name("j_password")).sendKeys(Keys.ENTER);

        //waiting for logged in user field to be visible
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div[4]/div")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div[4]/div")));
        Assert.assertTrue(driver.getPageSource().contains("Uwierzytelniony użytkownik: JDoe"));

        // waiting for settings to be clickable
        wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//*[@id=\"myNavbar\"]/ul[1]/li[2]/a"))));

        //clicking on settings
        driver.findElement(By.xpath("//*[@id=\"myNavbar\"]/ul[1]/li[2]/a")).click();

        //waiting for password change option to be clickable
        wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//*[@id=\"myNavbar\"]/ul[1]/li[2]/ul/li/a"))));

        //clicking on password change option
        driver.findElement(By.xpath("//*[@id=\"myNavbar\"]/ul[1]/li[2]/ul/li/a")).click();

        //Asserting page has proper password change form
        Assert.assertTrue(driver.getPageSource().contains("<form id=\"ChangeMyPasswordForm\" name=\"ChangeMyPasswordForm\" method=\"post\" action=\"/faces/common/changeMyPassword.xhtml\" class=\"content\" enctype=\"application/x-www-form-urlencoded\">\n"));

        //Password change sequance
        driver.findElement(By.id("ChangeMyPasswordForm:oldPassword")).click();
        driver.findElement(By.id("ChangeMyPasswordForm:oldPassword")).sendKeys(userPassword);
        driver.findElement(By.id("ChangeMyPasswordForm:newPassword")).sendKeys(newPasswordForJDoe);
        driver.findElement(By.id("ChangeMyPasswordForm:newPasswordRepeat")).sendKeys(newPasswordForJDoe);
        driver.findElement(By.name("ChangeMyPasswordForm:j_idt32")).click();

        //Logging out
        driver.findElement(By.linkText("Wylogowanie")).click();
        driver.findElement(By.name("j_idt26:j_idt30")).click();

        //Logging in with new password
        driver.findElement(By.linkText("Logowanie")).click();
        driver.findElement(By.name("j_username")).click();
        driver.findElement(By.name("j_username")).sendKeys(userName);
        driver.findElement(By.name("j_password")).sendKeys(newPasswordForJDoe);
        driver.findElement(By.cssSelector("input:nth-child(2)")).click();

        //waiting for logout to be visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"myNavbar\"]/ul[2]/li/a")));

        //logging out
        driver.findElement(By.xpath("//*[@id=\"myNavbar\"]/ul[2]/li/a")).click();
        driver.findElement(By.name("j_idt26:j_idt30")).click();
    }

    @Test
    public void notSingedInPassChangeTest() {
        driver.get(signInPage);

        Assert.assertTrue(driver.getPageSource().contains("Uwierzytelniony użytkownik: brak autoryzacji"));

        //waiting for password change to be visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Resetowanie hasła")));
        driver.findElement(By.linkText("Resetowanie hasła")).click();

        //Asserting page has proper password change form
        Assert.assertTrue(driver.getPageSource().contains("<form id=\"ResetPasswordForm\" name=\"ResetPasswordForm\" method=\"post\" action=\"/faces/common/resetPassword.xhtml\" class=\"content\" enctype=\"application/x-www-form-urlencoded\">\n"));

        //password change sequence
        driver.findElement(By.id("ResetPasswordForm:login")).click();
        driver.findElement(By.id("ResetPasswordForm:login")).sendKeys("DMitchell");
        driver.findElement(By.name("ResetPasswordForm:j_idt29")).click();
        driver.findElement(By.id("ResetPasswordForm:answer")).click();
        driver.findElement(By.id("ResetPasswordForm:answer")).sendKeys("Eva");
        driver.findElement(By.id("ResetPasswordForm:newPassword")).sendKeys(newPasswordForDMitchell);
        driver.findElement(By.id("ResetPasswordForm:newPasswordRepeat")).sendKeys(newPasswordForDMitchell);
        driver.findElement(By.name("ResetPasswordForm:j_idt33")).click();


        //logging in with new password
        driver.findElement(By.linkText("Logowanie")).click();
        driver.findElement(By.name("j_username")).click();
        driver.findElement(By.name("j_username")).sendKeys("DMitchell");
        driver.findElement(By.name("j_password")).sendKeys(newPasswordForDMitchell);
        driver.findElement(By.cssSelector("input:nth-child(2)")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div[4]/div/h4")));
        Assert.assertTrue(driver.getPageSource().contains("Uwierzytelniony użytkownik: DMitchell"));

        //waiting for logout to be visible
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"myNavbar\"]/ul[2]/li/a")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"myNavbar\"]/ul[2]/li/a")));

        //logging out
        driver.findElement(By.xpath("//*[@id=\"myNavbar\"]/ul[2]/li/a")).click();
        driver.findElement(By.name("j_idt26:j_idt30")).click();
    }


    @After
    public void tearDown() {
        driver.quit();
    }
}
