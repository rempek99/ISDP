import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TestLogin {

    private WebDriver wd;
    private String url;
    private String userName;
    private String userPassword;


    @Before
    public void setUp(){
        System.setProperty("webdriver.gecko.driver", "geckodriver");
        wd = new FirefoxDriver();
//        wd = new ChromeDriver();
        url = "https://localhost:8181/faces/common/signIn.xhtml";
//        url = "https://wp.pl";
        userName = "JDoe";
        userPassword = "P@ssw0rd";
    }
    @Test
    public void createLocation(){
        wd.get(url);
        WebElement loginVar= wd.findElement(By.name("j_username"));
        loginVar.clear();
        loginVar.sendKeys(userName);
        WebElement loginVar2= wd.findElement(By.name("j_password"));
        loginVar2.clear();
        loginVar2.sendKeys(userPassword);
        wd.findElement(By.xpath("//input[@value='Zaloguj']")).click();
        wd.get("https://localhost:8181/faces/location/listLocations.xhtml");
        
        Assert.assertTrue(wd.getPageSource().contains("JDoe"));
        Assert.assertFalse(wd.getPageSource().contains("AX-20-30-00"));
        wd.get("https://localhost:8181/faces/location/createNewLocation.xhtml");
        
//        wd.findElement(By.xpath("/html/body/div/div[3]/div/form/table/tbody/tr[1]/td[5]/input[1]")).click();
        WebElement editlocation = wd.findElement(By.xpath("//*[@id=\"CreateLocationForm:locationSymbol\"]"));
        editlocation.clear();
        editlocation.sendKeys("AX-20-30-00");
        Select dropdown = new Select(wd.findElement(By.id("CreateLocationForm:locationType")));
        dropdown.selectByIndex(1);
        wd.findElement(By.xpath("/html/body/div/div[3]/div/form/input[2]")).click();
        wd.get("https://localhost:8181/faces/location/listLocations.xhtml");
        Assert.assertTrue(wd.getPageSource().contains("AX-20-30-00"));

        
        wd.get("https://localhost:8181/faces/common/logout.xhtml");
        wd.findElement(By.xpath("//input[@value='Wyloguj']")).click();
        Assert.assertTrue(wd.getPageSource().contains("Uwierzytelniony użytkownik: brak autoryzacji"));
        
        
    }
    
    @Test
    public void deleteLocation(){
        wd.get(url);
        WebElement loginVar= wd.findElement(By.name("j_username"));
        loginVar.clear();
        loginVar.sendKeys(userName);
        WebElement loginVar2= wd.findElement(By.name("j_password"));
        loginVar2.clear();
        loginVar2.sendKeys(userPassword);
        wd.findElement(By.xpath("//input[@value='Zaloguj']")).click();
        wd.get("https://localhost:8181/faces/location/listLocations.xhtml");
        wd.findElement(By.xpath("/html/body/div/div[3]/div/form/table/tbody/tr[11]/td[5]/input[2]")).click();
        wd.findElement(By.xpath("/html/body/div/div[3]/div/form/input[2]")).click();
    }
    @After
    public void shutDown(){
        wd.quit();
    }

}
