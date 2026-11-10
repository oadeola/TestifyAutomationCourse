
package Task14;

import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class ToolsQAPopupTest {

    @Test
    public void closePopupAndOpenTutorials()
            throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        try {
            driver.manage().window().maximize();

            WebDriverWait wait = new WebDriverWait(
                    driver, Duration.ofSeconds(20));

            JavascriptExecutor js =
                    (JavascriptExecutor) driver;

            // ======================================
            // STEP 1: Open ToolsQA
            // ======================================

            driver.get("https://www.toolsqa.com/");

            System.out.println("PASS: ToolsQA opened");

            // Allow time for delayed popup
            Thread.sleep(5000);


            // ======================================
            // STEP 2: Handle Popup
            // ======================================

            boolean popupClosed = false;

            // First check for JavaScript alert
            try {
                WebDriverWait alertWait =
                        new WebDriverWait(
                                driver,
                                Duration.ofSeconds(3)
                        );

                Alert alert = alertWait.until(
                        ExpectedConditions.alertIsPresent()
                );

                alert.dismiss();
                popupClosed = true;

                System.out.println(
                        "PASS: JavaScript alert dismissed"
                );

            } catch (TimeoutException e) {

                System.out.println(
                        "No JavaScript alert detected"
                );
            }


            // Check for HTML popup or cookie dialog
            if (!popupClosed) {

                String[] closeSelectors = {
                        ".pum-close",
                        ".modal .close",
                        ".modal .btn-close",
                        ".popup-close",
                        ".cookie-notice-container " +
                                ".cookie-notice-dismiss",
                        "#cookie-notice .cn-set-cookie",
                        ".cky-btn-accept",
                        ".cky-btn-close"
                };

                for (String selector : closeSelectors) {

                    List<WebElement> buttons =
                            driver.findElements(
                                    By.cssSelector(selector)
                            );

                    for (WebElement button : buttons) {

                        if (button.isDisplayed()
                                && button.isEnabled()) {

                            button.click();
                            popupClosed = true;

                            System.out.println(
                                    "PASS: Popup closed using "
                                            + selector
                            );

                            break;
                        }
                    }

                    if (popupClosed) {
                        break;
                    }
                }
            }


            // Report actual popup result
            if (!popupClosed) {

                System.out.println(
                        "WARNING: No recognized popup appeared."
                );

                System.out.println(
                        "Popup closing step was not verified."
                );
            }

            Thread.sleep(2000);


            // ======================================
            // STEP 3: Find Tutorials Link
            // ======================================

            WebElement tutorials = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath(
                                    "//a[normalize-space()='Tutorials']"
                            )
                    )
            );

            // Scroll until Tutorials is visible
            js.executeScript(
                    "arguments[0].scrollIntoView(" +
                            "{block:'center'});",
                    tutorials
            );

            System.out.println(
                    "PASS: Tutorials link located"
            );

            Thread.sleep(2000);


            // ======================================
            // STEP 4: Click Tutorials
            // ======================================

            wait.until(
                    ExpectedConditions.elementToBeClickable(
                            tutorials
                    )
            ).click();

            System.out.println(
                    "PASS: Tutorials clicked"
            );

            Thread.sleep(2000);


            // ======================================
            // STEP 5: Verify Navigation
            // ======================================

            wait.until(
                    ExpectedConditions.not(
                            ExpectedConditions.urlToBe(
                                    "https://www.toolsqa.com/"
                            )
                    )
            );

            String currentUrl =
                    driver.getCurrentUrl();

            System.out.println(
                    "Current URL: " + currentUrl
            );

            assertTrue(
                    "Tutorials navigation failed",
                    currentUrl.contains("toolsqa.com")
            );

            Thread.sleep(5000);

        } finally {
            driver.quit();
        }
    }
}
