
package Task15;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.testng.Assert.*;

public class GoogleMcDonaldsTest {

    WebDriver driver;
    WebDriverWait wait;

    // ==========================================
    // BEFORE EACH TEST: Launch Chrome
    // ==========================================

    @BeforeMethod
    public void setUp() throws InterruptedException {

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        wait = new WebDriverWait(
                driver, Duration.ofSeconds(20));

        System.out.println("\nChrome browser launched");

        Thread.sleep(2000);
    }


    // ==========================================
    // TEST 1: Google Search for Testify
    // ==========================================

    @Test(priority = 1)
    public void googleSearchTest()
            throws InterruptedException {

        // Step 1: Open Google
        driver.get("https://www.google.com/");

        System.out.println("PASS: Google opened");
        Thread.sleep(2000);

        // Step 2: Locate search field
        WebElement searchBox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.name("q")
                )
        );

        // Enter Testify
        searchBox.sendKeys("testify");

        System.out.println("PASS: Search text entered");
        Thread.sleep(2000);

        // Submit Google search
        searchBox.sendKeys(Keys.ENTER);

        // Verify search results page
        wait.until(
                ExpectedConditions.urlContains("search")
        );

        assertTrue(
                driver.getCurrentUrl().contains("search"),
                "Google search was not completed"
        );

        System.out.println("PASS: Google search completed");

        System.out.println(
                "Page title: " + driver.getTitle()
        );

        System.out.println(
                "TASK 15 - TEST 1 PASSED!"
        );

        Thread.sleep(3000);
    }


    // ==========================================
    // TEST 2: McDonald's Order Now Button Colour
    // ==========================================

    @Test(priority = 2)
    public void mcdonaldsButtonColourTest()
            throws InterruptedException {

        // Step 1: Open McDonald's website
        driver.get(
                "https://www.mcdonalds.com/us/en-us.html"
        );

        System.out.println("PASS: McDonald's opened");
        Thread.sleep(3000);

        // Step 2: Locate the Order Now button
        // Prefer the header button
        List<WebElement> headerButtons =
                driver.findElements(
                        By.xpath(
                                "//header//*[self::a or self::button]" +
                                        "[contains(normalize-space(.),'Order Now')]"
                        )
                );

        WebElement orderButton = null;

        for (WebElement button : headerButtons) {

            if (button.isDisplayed()) {
                orderButton = button;
                break;
            }
        }

        // Fallback: Look for any visible Order Now button
        if (orderButton == null) {

            List<WebElement> buttons = wait.until(
                    ExpectedConditions.presenceOfAllElementsLocatedBy(
                            By.xpath(
                                    "//*[self::a or self::button]" +
                                            "[contains(normalize-space(.),'Order Now')]"
                            )
                    )
            );

            for (WebElement button : buttons) {

                if (button.isDisplayed()) {
                    orderButton = button;
                    break;
                }
            }
        }

        assertNotNull(
                orderButton,
                "Order Now button was not found"
        );

        System.out.println("PASS: Order Now button found");

        Thread.sleep(2000);

        // Step 3: Read the CSS background colour
        String backgroundColor =
                orderButton.getCssValue("background-color");

        // Sometimes the background is applied
        // to the parent container
        if (backgroundColor.equals("transparent")
                || backgroundColor.equals("rgba(0, 0, 0, 0)")) {

            backgroundColor = orderButton
                    .findElement(By.xpath(".."))
                    .getCssValue("background-color");
        }

        assertNotNull(
                backgroundColor,
                "Button colour could not be retrieved"
        );

        assertFalse(
                backgroundColor.isEmpty(),
                "Button colour is empty"
        );

        // Step 4: Print colour
        System.out.println();
        System.out.println(
                "===================================="
        );

        System.out.println(
                "      MCDONALD'S BUTTON COLOUR"
        );

        System.out.println(
                "===================================="
        );

        System.out.println(
                "CSS Background Colour: " + backgroundColor
        );

        if (!backgroundColor.equals("transparent")
                && !backgroundColor.equals("rgba(0, 0, 0, 0)")) {

            String hexColor = Color
                    .fromString(backgroundColor)
                    .asHex();

            System.out.println(
                    "HEX Colour Code: " + hexColor
            );

        } else {

            System.out.println(
                    "The located element has a transparent " +
                            "background. Inspect its styled container."
            );
        }

        System.out.println(
                "===================================="
        );

        System.out.println(
                "TASK 15 - TEST 2 PASSED!"
        );

        Thread.sleep(5000);
    }


    // ==========================================
    // AFTER EACH TEST: Close Browser
    // ==========================================

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        if (driver != null) {

            driver.quit();

            driver = null;

            System.out.println(
                    "Chrome browser closed successfully"
            );
        }
    }
}
