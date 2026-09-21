import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CourseraNavigate {
    static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver",  "C:\\git\\TestifyAutomationCourse\\Module4D_WebAutomationSelenium\\SeleniumIntro\\src\\chromedriver.exe");
        //launch browser
        WebDriver driver = new ChromeDriver();
        //maximize the browser
        driver.manage().window().maximize();
        //navigates to the url
        driver.get("https://www.coursera.org/");

    }
}
