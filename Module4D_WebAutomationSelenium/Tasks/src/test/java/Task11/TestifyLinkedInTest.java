
package Task11;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestifyLinkedInTest {

    @Test
    public void testifyLinkedInTest()
            throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        try {
            driver.manage().window().maximize();

            WebDriverWait wait = new WebDriverWait(
                    driver, Duration.ofSeconds(20));

            JavascriptExecutor js =
                    (JavascriptExecutor) driver;

            // =====================================
            // STEP 1: Navigate to Google
            // =====================================

            driver.get("https://www.google.com/");

            System.out.println("PASS: Google opened");
            Thread.sleep(2000);


            // =====================================
            // STEP 2: Search for Testify Academy
            // =====================================

            WebElement searchBox = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.name("q")
                    )
            );

            searchBox.sendKeys("Testify Academy");
            Thread.sleep(2000);

            searchBox.sendKeys(Keys.ENTER);

            System.out.println("PASS: Search completed");
            Thread.sleep(2000);


            // =====================================
            // STEP 3: Open Testify Academy
            // =====================================

            WebElement academyLink = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath(
                                    "//a[.//h3 and " +
                                            "contains(@href,'academy.testifyltd.com')]"
                            )
                    )
            );

            academyLink.click();

            wait.until(
                    ExpectedConditions.urlContains(
                            "academy.testifyltd.com"
                    )
            );

            System.out.println("PASS: Testify Academy opened");
            System.out.println(
                    "Website: " + driver.getCurrentUrl()
            );

            Thread.sleep(3000);


        } finally {
            driver.quit();
        }
    }
}
