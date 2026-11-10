package Task8;

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

public class CreateAccountTest {
    @Test
    public void createAccountButtonTest()
            throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        try {
            // Maximize browser
            driver.manage().window().maximize();

            WebDriverWait wait = new WebDriverWait(
                    driver, Duration.ofSeconds(15));

            // ========================================
            // STEP 1: Navigate to Registration Page
            // ========================================

            driver.get(
                    "https://idorenyinankoh.github.io/loginPage/"
            );

            System.out.println("PASS: Registration page opened");
            Thread.sleep(2000);


            // ========================================
            // STEP 2: Check Create Account Button
            // ========================================

            WebElement createAccount = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.id("create")
                    )
            );

            System.out.println(
                    "Initial button enabled: "
                            + createAccount.isEnabled()
            );

            // Trigger the website's form validation
            // without submitting the form
            driver.findElement(
                    By.cssSelector("h3.signUp")
            ).click();

            wait.until(
                    ExpectedConditions.not(
                            ExpectedConditions.elementToBeClickable(
                                    By.id("create")
                            )
                    )
            );

            assertFalse(
                    "FAIL: Button should be disabled " +
                            "when required fields are empty",
                    createAccount.isEnabled()
            );

            System.out.println(
                    "PASS: Create Account button is disabled"
            );

            Thread.sleep(2000);


            // ========================================
            // STEP 3: Fill All Fields
            // ========================================

            // First Name
            WebElement firstName = driver.findElement(
                    By.id("firstName")
            );

            firstName.sendKeys("Cyril");
            System.out.println("PASS: First Name entered");
            Thread.sleep(1500);


            // Last Name
            WebElement lastName = driver.findElement(
                    By.id("lastName")
            );

            lastName.sendKeys("Tester");
            System.out.println("PASS: Last Name entered");
            Thread.sleep(1500);


            // Email
            WebElement email = driver.findElement(
                    By.id("email")
            );

            email.sendKeys("cyril.tester@example.com");
            System.out.println("PASS: Email entered");
            Thread.sleep(1500);


            // Gender - Male
            WebElement male = driver.findElement(
                    By.id("male")
            );

            male.click();

            assertTrue(
                    "Gender checkbox was not selected",
                    male.isSelected()
            );

            System.out.println("PASS: Gender selected");
            Thread.sleep(1500);


            // Password
            WebElement password = driver.findElement(
                    By.id("password")
            );

            password.sendKeys("TestPassword123");
            System.out.println("PASS: Password entered");
            Thread.sleep(1500);


            // Confirm Password
            WebElement confirmPassword = driver.findElement(
                    By.id("confirmPass")
            );

            confirmPassword.sendKeys("TestPassword123");

            System.out.println(
                    "PASS: Confirm Password entered"
            );

            Thread.sleep(1500);


            // Tell us about you
            WebElement aboutYou = driver.findElement(
                    By.id("xpLevel")
            );

            aboutYou.sendKeys(
                    "I am learning Selenium automation."
            );

            System.out.println(
                    "PASS: About You field completed"
            );

            Thread.sleep(2000);


            // ========================================
            // STEP 4: Check Create Account Again
            // ========================================

            // Trigger the form's click-based validation
            driver.findElement(
                    By.cssSelector("h3.signUp")
            ).click();

            wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.id("create")
                    )
            );

            assertTrue(
                    "FAIL: Create Account button " +
                            "should be enabled after filling fields",
                    createAccount.isEnabled()
            );

            System.out.println();
            System.out.println(
                    "PASS: Create Account button is enabled!"
            );

            // Keep browser visible for observation
            Thread.sleep(5000);

        } finally {
            driver.quit();
        }
    }
}
