package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.testng.Assert.fail;

/**
 * Created by student on 5/5/2016.
 */
public class Tests {
    WebDriver driver;

    @BeforeClass
    public void setup(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("http://the-internet.herokuapp.com/");
    }

    @Test
    public void testOne() throws IOException {
        driver.findElement(By.partialLinkText("Form Authentication")).click();
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.cssSelector(".radius")).click();
        Assert.fail();
    }

    @Test
    public void testHovering() throws InterruptedException {
        driver.get("http://the-internet.herokuapp.com/hovers");

        Actions actions = new Actions(driver);
        List<WebElement> avatars = driver.findElements(By.cssSelector("img[alt='User Avatar']"));
        actions.moveToElement(avatars.get(1)).build().perform();

        Assert.assertTrue(driver.findElement(By.xpath("//div[@class=\"figure\"][2]"))
                .findElement(By.cssSelector("div a")).isDisplayed());
    }

    @Test
    public void simpleTest() throws Exception {
        Assert.assertEquals(2, is(2));
    }

    @Step
    public void checkThat2is2() {
        Assert.assertEquals(2, is(2));
    }

    @Test
    public void simpleTestWithSteps() throws Exception {
        checkThat2is2();
    }

    @Attachment
    public String makeAttach() {
        return "yeah, 2 is 2";
    }

    @Test
    public void simpleTestWithAttachments() throws Exception {
        Assert.assertEquals(2, is(2));
        makeAttach();
    }

    @Test
    public void failedTest() {
        fail("This test should be failed");
    }

    @AfterClass
    public void tearDown(){
        driver.close();
    }
}
