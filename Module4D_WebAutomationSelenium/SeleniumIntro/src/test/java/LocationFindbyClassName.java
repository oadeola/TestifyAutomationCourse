import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static java.lang.Thread.sleep;

public class LocationFindbyClassName {
    static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver",  "C:\\git\\TestifyAutomationCourse\\Module4D_WebAutomationSelenium\\SeleniumIntro\\src\\chromedriver.exe");
        //launch browser
        WebDriver driver = new ChromeDriver();
        //maximize the browser
        driver.manage().window().maximize();
        //navigates to the url
        driver.get("https://www.google.com/");
        //find element by ID
        driver.findElement(By.name("q")).sendKeys("Selenium");
        sleep(5000);
    }
}
