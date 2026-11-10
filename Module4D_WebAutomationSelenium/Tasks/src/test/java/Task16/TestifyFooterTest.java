
package Task16;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class TestifyFooterTest {

    @Test
    public void verifyFooterContactDetails()
            throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        try {
            driver.manage().window().maximize();

            WebDriverWait wait = new WebDriverWait(
                    driver, Duration.ofSeconds(20));

            JavascriptExecutor js =
                    (JavascriptExecutor) driver;

            // =========================================
            // STEP 1: Navigate to Testify Academy
            // =========================================

            driver.get("https://academy.testifyltd.com/");

            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.tagName("body")
                    )
            );

            System.out.println("PASS: Testify Academy opened");
            System.out.println(
                    "Current URL: " + driver.getCurrentUrl()
            );

            Thread.sleep(3000);


            // =========================================
            // STEP 2: Scroll to the footer
            // =========================================

            WebElement footer = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.tagName("footer")
                    )
            );

            js.executeScript(
                    "arguments[0].scrollIntoView(" +
                            "{block:'center'});",
                    footer
            );

            System.out.println("PASS: Footer located");
            Thread.sleep(3000);


            // =========================================
            // STEP 3: Retrieve actual footer text
            // =========================================

            String footerText = footer.getText();

            // =========================================
            // STEP 4: Define expected contact details
            // =========================================

            String expectedLocation = "Nigeria";
            String expectedEmail = "info@testifyltd.co.uk";
            String expectedPhone = "(+234)904-882-0971";


            // =========================================
            // STEP 5: Verify each expected value
            // =========================================

            boolean locationCorrect =
                    footerText.toLowerCase()
                            .contains(expectedLocation.toLowerCase());

            boolean emailCorrect =
                    footerText.contains(expectedEmail);

            boolean phoneCorrect =
                    footerText.contains(expectedPhone);


            // =========================================
            // STEP 6: Print individual test results
            // =========================================

            System.out.println();
            System.out.println(
                    "======================================"
            );
            System.out.println(
                    "       CONTACT VERIFICATION"
            );
            System.out.println(
                    "======================================"
            );

            System.out.println(
                    "Location: "
                            + (locationCorrect ? "PASS" : "FAIL")
            );

            System.out.println(
                    "Email: "
                            + (emailCorrect ? "PASS" : "FAIL")
            );

            System.out.println(
                    "Phone: "
                            + (phoneCorrect ? "PASS" : "FAIL")
            );

            System.out.println(
                    "======================================"
            );


            // =========================================
            // STEP 7: JUnit Assertions
            // =========================================

            assertTrue(
                    "FAIL: Location Nigeria was not found",
                    locationCorrect
            );

            assertTrue(
                    "FAIL: Expected email not found: "
                            + expectedEmail,
                    emailCorrect
            );

            assertTrue(
                    "FAIL: Expected phone not found: "
                            + expectedPhone,
                    phoneCorrect
            );


            Thread.sleep(5000);

        } finally {

            driver.quit();
        }
    }
}
