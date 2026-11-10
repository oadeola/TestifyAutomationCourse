
package Task12;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class JQueryResizeTest {

    @Test
    public void resizeBoxTest() throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        try {
            // Maximize browser
            driver.manage().window().maximize();

            WebDriverWait wait = new WebDriverWait(
                    driver, Duration.ofSeconds(15));

            // ========================================
            // STEP 1: Navigate to jQuery UI
            // ========================================

            driver.get("https://jqueryui.com/");

            System.out.println("PASS: jQuery UI opened");
            Thread.sleep(2000);


            // ========================================
            // STEP 2: Click Resizable menu
            // ========================================

            WebElement resizeMenu = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.linkText("Resizable")
                    )
            );

            resizeMenu.click();

            wait.until(
                    ExpectedConditions.urlContains("/resizable/")
            );

            System.out.println("PASS: Resizable page opened");
            Thread.sleep(2000);


            // ========================================
            // STEP 3: Switch into iframe
            // ========================================

            wait.until(
                    ExpectedConditions.frameToBeAvailableAndSwitchToIt(
                            By.cssSelector(".demo-frame")
                    )
            );

            System.out.println("PASS: Switched to iframe");
            Thread.sleep(2000);


            // ========================================
            // STEP 4: Locate resizable box
            // ========================================

            WebElement resizeBox = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.id("resizable")
                    )
            );

            // Record initial dimensions
            int initialWidth = resizeBox.getSize().getWidth();
            int initialHeight = resizeBox.getSize().getHeight();

            System.out.println();
            System.out.println("BEFORE RESIZING:");
            System.out.println("Width: " + initialWidth);
            System.out.println("Height: " + initialHeight);

            Thread.sleep(2000);


            // ========================================
            // STEP 5: Locate bottom-right resize handle
            // ========================================

            WebElement resizeHandle = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.cssSelector(
                                    "#resizable .ui-resizable-se"
                            )
                    )
            );

            // ========================================
            // STEP 6: Drag handle to enlarge box
            // ========================================

            Actions actions = new Actions(driver);

            actions.clickAndHold(resizeHandle)
                    .pause(Duration.ofMillis(500))
                    .moveByOffset(150, 100)
                    .pause(Duration.ofMillis(800))
                    .release()
                    .perform();

            System.out.println("PASS: Resize action performed");
            Thread.sleep(2000);


            // ========================================
            // STEP 7: Verify the box became bigger
            // ========================================

            wait.until(d ->
                    resizeBox.getSize().getWidth() > initialWidth
                            && resizeBox.getSize().getHeight() > initialHeight
            );

            int finalWidth = resizeBox.getSize().getWidth();
            int finalHeight = resizeBox.getSize().getHeight();

            System.out.println();
            System.out.println("AFTER RESIZING:");
            System.out.println("Width: " + finalWidth);
            System.out.println("Height: " + finalHeight);

            assertTrue(
                    "Box width did not increase",
                    finalWidth > initialWidth
            );

            assertTrue(
                    "Box height did not increase",
                    finalHeight > initialHeight
            );

            System.out.println();
            System.out.println("PASS: Box resized successfully!");


            // ========================================
            // STEP 8: Return to main page
            // ========================================

            driver.switchTo().defaultContent();

            // Keep browser visible for 5 seconds
            Thread.sleep(5000);

        } finally {
            driver.quit();
        }
    }
}
