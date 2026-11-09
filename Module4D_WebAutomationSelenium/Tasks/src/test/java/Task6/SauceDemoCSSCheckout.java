
package Task6;

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

public class SauceDemoCSSCheckout {

    @Test
    public void checkoutTest() throws InterruptedException {

        // Launch Chrome browser
        WebDriver driver = new ChromeDriver();

        try {
            // Maximize browser
            driver.manage().window().maximize();

            WebDriverWait wait = new WebDriverWait(
                    driver, Duration.ofSeconds(15));

            // ==========================================
            // STEP 1: Navigate to SauceDemo
            // ==========================================

            driver.get("https://www.saucedemo.com/");

            System.out.println("PASS: SauceDemo opened");
            Thread.sleep(2000);


            // ==========================================
            // STEP 2: Enter Username and Password
            // ==========================================

            // Locate username using CSS Selector
            WebElement username = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.cssSelector("#user-name")
                    )
            );

            username.sendKeys("standard_user");

            System.out.println("PASS: Username entered");
            Thread.sleep(2000);


            // Locate password using CSS Selector
            WebElement password = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.cssSelector("#password")
                    )
            );

            password.sendKeys("secret_sauce");

            System.out.println("PASS: Password entered");
            Thread.sleep(2000);


            // ==========================================
            // STEP 3: Click Login Button
            // ==========================================

            WebElement loginButton = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.cssSelector("#login-button")
                    )
            );

            loginButton.click();

            // Verify successful login
            wait.until(ExpectedConditions.urlContains(
                    "inventory.html"));

            System.out.println("PASS: Login successful");
            Thread.sleep(2000);


            // ==========================================
            // STEP 4: Add ONE product to cart
            // ==========================================

            // Product: Sauce Labs Backpack
            WebElement addToCart = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.cssSelector(
                                    "#add-to-cart-sauce-labs-backpack"
                            )
                    )
            );

            addToCart.click();

            // Verify cart badge shows 1
            WebElement cartBadge = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.cssSelector(".shopping_cart_badge")
                    )
            );

            assertEquals("1", cartBadge.getText());

            System.out.println("PASS: One product added to cart");
            Thread.sleep(2000);


            // ==========================================
            // STEP 5: Click Cart Icon
            // ==========================================

            WebElement cartIcon = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.cssSelector(".shopping_cart_link")
                    )
            );

            cartIcon.click();

            // Verify cart page opens
            wait.until(ExpectedConditions.urlContains(
                    "cart.html"));

            // Verify the correct product is in cart
            WebElement product = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.cssSelector(".inventory_item_name")
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
                            By.cssSelector("#checkout")
                    )
            );

            checkoutButton.click();

            // Verify checkout page
            wait.until(ExpectedConditions.urlContains(
                    "checkout-step-one.html"));

            WebElement checkoutTitle = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.cssSelector(".title")
                    )
            );

            assertTrue(checkoutTitle.getText()
                    .contains("Checkout: Your Information"));

            System.out.println("PASS: Checkout page opened");

            // Keep checkout page visible for 5 seconds
            Thread.sleep(5000);

        } finally {

            // Close browser
            driver.quit();
        }
    }
}
