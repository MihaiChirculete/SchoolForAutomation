import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class FirstSeleniumTest {

    private WebDriver wd;

    @BeforeTest
    public void init()
    {
        System.setProperty("webdriver.chrome.driver", "D:\\Programe\\chromedriver_win32\\chromedriver.exe");
        this.wd = new ChromeDriver();
        wd.manage().window().maximize();
    }

    @Test
    public void correctCredentials(){
        wd.get("http://school-for-automation.tech");
        WebElement userName = wd.findElement(By.id("username"));
        WebElement userPass = wd.findElement(By.id("password"));
        WebElement loginBtn = wd.findElement(By.id("submit"));

        userName.sendKeys("tester");
        userPass.sendKeys("test1234");
        loginBtn.click();

        Assert.assertTrue(wd.findElements(By.id("home")).size() > 0);
    }

    @Test
    public void wrongUserTest()
    {
        wd.get("http://school-for-automation.tech");
        WebElement userName = wd.findElement(By.id("username"));
        WebElement userPass = wd.findElement(By.id("password"));
        WebElement loginBtn = wd.findElement(By.id("submit"));

        userName.sendKeys("Mihai");
        userPass.sendKeys("test1234");
        loginBtn.click();

        Assert.assertTrue(wd.findElement(By.xpath("//*[@id=\"error\"]")).getAttribute("style").equalsIgnoreCase("display: block;"));
    }

    @Test
    public void wrongPasswordTest()
    {
        wd.get("http://school-for-automation.tech");
        WebElement userName = wd.findElement(By.id("username"));
        WebElement userPass = wd.findElement(By.id("password"));
        WebElement loginBtn = wd.findElement(By.id("submit"));

        userName.sendKeys("tester");
        userPass.sendKeys("Test12345");
        loginBtn.click();

        Assert.assertTrue(wd.findElement(By.xpath("//*[@id=\"error\"]")).getAttribute("style").equalsIgnoreCase("display: block;"));
    }

    @Test
    public void emptyCredentials()
    {
        wd.get("http://school-for-automation.tech");
        WebElement userName = wd.findElement(By.id("username"));
        WebElement userPass = wd.findElement(By.id("password"));
        WebElement loginBtn = wd.findElement(By.id("submit"));

        userName.sendKeys("");
        userPass.sendKeys("");
        loginBtn.click();

        Assert.assertTrue(wd.findElement(By.xpath("//*[@id=\"error\"]")).getAttribute("style").equalsIgnoreCase("display: block;"));
    }

    @Test
    public void navBarTest()
    {
        wd.get("http://school-for-automation.tech/home.html");
        WebElement navBtnHome = wd.findElement(By.xpath("//*[@id=\"nav\"]/ul/li[1]/a"));
        WebElement navBtnAbout = wd.findElement(By.xpath("//*[@id=\"nav\"]/ul/li[2]/a"));
        WebElement navBtnCourses = wd.findElement(By.xpath("//*[@id=\"nav\"]/ul/li[3]/a"));
        WebElement navBtnBlog = wd.findElement(By.xpath("//*[@id=\"nav\"]/ul/li[4]/a"));
        WebElement navBtnContact = wd.findElement(By.xpath("//*[@id=\"nav\"]/ul/li[5]/a"));

        Assert.assertTrue(navBtnHome.getText().equalsIgnoreCase("home"));
        Assert.assertTrue(navBtnAbout.getText().equalsIgnoreCase("about"));
        Assert.assertTrue(navBtnCourses.getText().equalsIgnoreCase("courses"));
        Assert.assertTrue(navBtnBlog.getText().equalsIgnoreCase("blog"));
        Assert.assertTrue(navBtnContact.getText().equalsIgnoreCase("contact"));
    }

    @AfterTest
    public void terminate()
    {
        wd.quit();
    }
}
