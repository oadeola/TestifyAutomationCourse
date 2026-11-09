
package Task5;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SauceDemoCheckout {

    @Test
    public void checkoutTest() throws InterruptedException {

        // Launch Chrome browser
        WebDriver driver = new ChromeDriver();

        try {
            // Maximize browser window
            driver.manage().window().maximize();

            // Explicit wait for page elements
            WebDriverWait wait = new WebDriverWait(
                    driver, Duration.ofSeconds(15));

            // ==========================================
            // STEP 1: Navigate to SauceDemo
            // ==========================================

            driver.get("https://www.saucedemo.com/");

            System.out.println("PASS: SauceDemo opened");
            Thread.sleep(2000);


            // ==========================================
            // STEP 2: Enter username and password
            // ==========================================

            WebElement username = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//input[@id='user-name']")
                    )
            );

            username.sendKeys("standard_user");

            System.out.println("PASS: Username entered");
            Thread.sleep(2000);


            WebElement password = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//input[@id='password']")
                    )
            );

            password.sendKeys("secret_sauce");

            System.out.println("PASS: Password entered");
            Thread.sleep(2000);


            // ==========================================
            // STEP 3: Click Login button
            // ==========================================

            WebElement loginButton = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//input[@id='login-button']")
                    )
            );

            loginButton.click();

            wait.until(ExpectedConditions.urlContains(
                    "inventory.html"));

            System.out.println("PASS: Login successful");
            Thread.sleep(2000);


            // ==========================================
            // STEP 4: Add one product to cart
            // ==========================================

            // Product: Sauce Labs Backpack
            WebElement addToCart = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath(
                                    "//button[@id='add-to-cart-sauce-labs-backpack']"
                            )
                    )
            );

            addToCart.click();

            // Verify cart badge displays 1
            WebElement cartBadge = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath(
                                    "//span[@class='shopping_cart_badge']"
                            )
                    )
            );

            assertEquals("1", cartBadge.getText());

            System.out.println("PASS: One product added to cart");
            Thread.sleep(2000);


            // ==========================================
            // STEP 5: Click cart icon
            // ==========================================

            WebElement cartIcon = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath(
                                    "//a[contains(@class,'shopping_cart_link')]"
                            )
                    )
            );

            cartIcon.click();

            wait.until(ExpectedConditions.urlContains(
                    "cart.html"));

            // Verify the correct product is in the cart
            WebElement product = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath(
                                    "//div[@class='inventory_item_name']"
                            )
                    )
            );

            assertEquals("Sauce Labs Backpack",
                    product.getText());

            System.out.println("PASS: Cart opened with Backpack");
            Thread.sleep(2000);


            // ==========================================
            // STEP 6: Click Checkout
            // ==========================================

            WebElement checkoutButton = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@id='checkout']")
                    )
            );

            checkoutButton.click();

            wait.until(ExpectedConditions.urlContains(
                    "checkout-step-one.html"));

            // Verify checkout information page
            WebElement checkoutTitle = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//span[@class='title']")
                    )
            );

            assertTrue(checkoutTitle.getText()
                    .contains("Checkout: Your Information"));

            System.out.println("PASS: Checkout page opened");

            // Keep checkout page visible for 5 seconds
            Thread.sleep(5000);

        } finally {

            // Close browser after test
            driver.quit();
        }
    }
}
