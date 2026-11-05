import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LocationFindbyID {
    static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver",  "C:\\git\\TestifyAutomationCourse\\Module4D_WebAutomationSelenium\\SeleniumIntro\\src\\chromedriver.exe");
        //launch browser
        WebDriver driver = new ChromeDriver();
        //maximize the browser
        driver.manage().window().maximize();
        //navigates to the url
        driver.get("https://www.jumia.com.ng/");
        //findelementbyID
        driver.findElement(By.id("fi-q")).sendKeys("samsung galaxy");

    }
}
