
package Task9;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SauceDemoNavigation {

    @Test
    public void backAndForwardTest() throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        try {
            // Maximize browser window
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
            // STEP 2: Login with credentials
            // ==========================================

            WebElement username = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.id("user-name")
                    )
            );

            username.sendKeys("standard_user");
            Thread.sleep(2000);

            WebElement password = driver.findElement(
                    By.id("password")
            );

            password.sendKeys("secret_sauce");
            Thread.sleep(2000);

            WebElement loginButton = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.id("login-button")
                    )
            );

            loginButton.click();

            wait.until(
                    ExpectedConditions.urlContains("inventory.html")
            );

            System.out.println("PASS: Login successful");
            Thread.sleep(2000);


            // ==========================================
            // STEP 3: Navigate BACK to Login Page
            // ==========================================

            driver.navigate().back();

            WebElement backLoginButton = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.id("login-button")
                    )
            );

            System.out.println("PASS: Returned to login page");
            Thread.sleep(2000);


            // ==========================================
            // STEP 4: Print Login Button VALUE
            // ==========================================

            String loginValue = backLoginButton.getAttribute("value");

            System.out.println(
                    "Login button VALUE attribute: " + loginValue
            );

            assertEquals(
                    "Login",
                    loginValue
            );

            assertTrue(
                    "Login button is not displayed",
                    backLoginButton.isDisplayed()
            );

            System.out.println(
                    "PASS: Login screen verified successfully"
            );

            Thread.sleep(2000);


            // ==========================================
            // STEP 5: Navigate FORWARD to Homepage
            // ==========================================

            driver.navigate().forward();

            wait.until(
                    ExpectedConditions.urlContains("inventory.html")
            );

            WebElement productName = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.className("inventory_item_name")
                    )
            );

            String product = productName.getText();

            assertFalse(
                    "Product name is empty",
                    product.isEmpty()
            );

            System.out.println(
                    "Product found on homepage: " + product
            );

            System.out.println(
                    "PASS: Returned to homepage successfully"
            );

            // Keep browser visible for observation
            Thread.sleep(5000);

        } finally {
            driver.quit();
        }
    }
}
