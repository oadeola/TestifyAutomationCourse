
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class GoogleNavigate {

    @Test
    public void navigateToGoogle() {

        // Launch Chrome using Selenium Manager
        WebDriver driver = new ChromeDriver();

        try {
            // Maximize browser window
            driver.manage().window().maximize();

            // Navigate to Google
            driver.get("https://www.google.com/");

            // Print page title
            System.out.println("Page title: " + driver.getTitle());

        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
