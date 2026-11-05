
package Task4;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class Guru99Security {

    @Test
    public void securityProjectTest() throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        try {
            // Step 1: Navigate to Guru99
            driver.manage().window().maximize();
            driver.get("http://demo.guru99.com/");

            WebDriverWait wait = new WebDriverWait(
                    driver, Duration.ofSeconds(15));

            // Step 2: Click Security Project
            WebElement securityProject = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.linkText("Security Project")
                    )
            );

            securityProject.click();

            // Step 3: Enter UserID
            WebElement userId = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.name("uid")
                    )
            );

            userId.sendKeys("adeola123");

            // Step 4: Enter Password
            WebElement password = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.name("password")
                    )
            );

            password.sendKeys("Test1111");

            // Verify entered text
            assertEquals("adeola123",
                    userId.getAttribute("value"));

            assertEquals("Test1111",
                    password.getAttribute("value"));

            System.out.println("UserID and Password entered successfully!");

        } finally {
            //driver.quit();
        }
    }
}
