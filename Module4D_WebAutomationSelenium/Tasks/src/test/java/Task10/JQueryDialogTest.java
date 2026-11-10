
package Task10;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class JQueryDialogTest {

    @Test
    public void closeDialogTest() throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        try {
            // Maximize browser
            driver.manage().window().maximize();

            WebDriverWait wait = new WebDriverWait(
                    driver, Duration.ofSeconds(15));

            // ==========================================
            // STEP 1: Navigate to jQuery UI
            // ==========================================

            driver.get("https://jqueryui.com/");

            System.out.println("PASS: jQuery UI website opened");
            Thread.sleep(2000);


            // ==========================================
            // STEP 2: Click Dialog from Left Menu
            // ==========================================

            WebElement dialogMenu = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.linkText("Dialog")
                    )
            );

            dialogMenu.click();

            wait.until(
                    ExpectedConditions.urlContains("/dialog/")
            );

            System.out.println("PASS: Dialog page opened");
            Thread.sleep(2000);


            // ==========================================
            // STEP 3: Switch to Dialog iframe
            // ==========================================

            // Dialog is inside an iframe
            wait.until(
                    ExpectedConditions.frameToBeAvailableAndSwitchToIt(
                            By.cssSelector(".demo-frame")
                    )
            );

            System.out.println("PASS: Switched to iframe");
            Thread.sleep(2000);


            // ==========================================
            // STEP 4: Verify Dialog is Displayed
            // ==========================================

            WebElement dialog = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.cssSelector(".ui-dialog")
                    )
            );

            assertTrue(
                    "FAIL: Dialog is not displayed",
                    dialog.isDisplayed()
            );

            System.out.println("PASS: Dialog is displayed");
            Thread.sleep(2000);


            // ==========================================
            // STEP 5: Click Close (X) Icon
            // ==========================================

            WebElement closeButton = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.cssSelector(".ui-dialog-titlebar-close")
                    )
            );

            closeButton.click();

            System.out.println("PASS: Close button clicked");
            Thread.sleep(2000);


            // ==========================================
            // STEP 6: Verify Dialog is Closed
            // ==========================================

            wait.until(
                    ExpectedConditions.invisibilityOf(dialog)
            );

            assertFalse(
                    "FAIL: Dialog is still displayed",
                    dialog.isDisplayed()
            );

            System.out.println("PASS: Dialog closed successfully");


            // ==========================================
            // STEP 7: Switch Back to Main Page
            // ==========================================

            driver.switchTo().defaultContent();

            System.out.println(
                    "PASS: Returned to main page"
            );

            // Keep browser visible
            Thread.sleep(5000);

        } finally {
            driver.quit();
        }
    }
}
