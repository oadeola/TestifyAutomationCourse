
package com.parabank;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WindowType;


import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.UUID;
import java.nio.file.Files;
import java.nio.file.Path;

public class ParaBankTest {

    // ==========================================
    // GLOBAL VARIABLES
    // ==========================================

    WebDriver driver;
    WebDriverWait wait;

    // Customer details
    String firstName = "Test";
    String lastName = "Customer";
    String address = "454 Test Street";
    String city = "Valletta";
    String state = "Malta";
    String zipCode = "12345";
    String phoneNumber = "7771234567";
    String ssn = "123456789";

    // Generate a unique username for every run
    String username = "qa" +
            UUID.randomUUID()
                    .toString()
                    .replace("-", "")
                    .substring(0, 10);

    String password = "Test12345";

    // Account information
    String newSavingsAccountId;
    String fundingAccountId;


    // ==========================================
    // SETUP - LAUNCH BROWSER
    // ==========================================

    @BeforeClass
    public void setup() {

        System.out.println("Launching ParaBank...");

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        // Explicit wait - maximum 30 seconds
        wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(30)
        );

        driver.get(
                "https://parabank.parasoft.com/parabank/index.htm"
        );

        // Wait for homepage to load
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.linkText("Register")
                )
        );

        System.out.println(
                "PASS: ParaBank launched successfully!"
        );
    }


    // ==========================================
    // HELPER METHOD - PAUSE AUTOMATION
    // ==========================================

    public void pause(int seconds) {

        try {

            System.out.println(
                    "Pausing for " + seconds + " seconds..."
            );

            Thread.sleep(seconds * 1000L);

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
        }
    }

    // ==========================================
    // TEST 1 - REGISTER NEW CUSTOMER
    // ==========================================

    @Test(priority = 1)
    public void registerNewCustomer() {

        System.out.println("\nStarting registration...");

        // STEP 1: Click Register
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.linkText("Register")
                )
        ).click();

        // STEP 2: Wait for registration form
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("customer.username")
                )
        );

        System.out.println(
                "PASS: Registration form loaded!"
        );

        // STEP 3: Enter personal information
        driver.findElement(By.id("customer.firstName"))
                .sendKeys(firstName);

        driver.findElement(By.id("customer.lastName"))
                .sendKeys(lastName);

        driver.findElement(By.id("customer.address.street"))
                .sendKeys(address);

        driver.findElement(By.id("customer.address.city"))
                .sendKeys(city);

        driver.findElement(By.id("customer.address.state"))
                .sendKeys(state);

        driver.findElement(By.id("customer.address.zipCode"))
                .sendKeys(zipCode);

        driver.findElement(By.id("customer.phoneNumber"))
                .sendKeys(phoneNumber);

        driver.findElement(By.id("customer.ssn"))
                .sendKeys(ssn);

        System.out.println(
                "PASS: Personal information entered!"
        );

        // STEP 4: Enter login credentials
        driver.findElement(By.id("customer.username"))
                .sendKeys(username);

        driver.findElement(By.id("customer.password"))
                .sendKeys(password);

        driver.findElement(By.id("repeatedPassword"))
                .sendKeys(password);

        System.out.println(
                "PASS: Login credentials entered!"
        );

        // STEP 5: Verify username before submission
        String enteredUsername = driver.findElement(
                By.id("customer.username")
        ).getDomProperty("value");

        System.out.println(
                "Generated username: " + username
        );

        System.out.println(
                "Entered username: " + enteredUsername
        );

        Assert.assertEquals(
                enteredUsername,
                username,
                "FAIL: Entered username does not match generated username!"
        );

        System.out.println(
                "PASS: Username verification successful!"
        );

        // STEP 6: Pause for visual inspection
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // STEP 7: Click Register once
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("input[value='Register']")
                )
        ).click();

        System.out.println(
                "Registration submitted!"
        );

        // STEP 8: Wait for registration result

        By welcomeHeading = By.xpath(
                "//div[@id='rightPanel']//h1[starts-with(normalize-space(.),'Welcome ')]"
        );

        By registrationErrors = By.cssSelector(
                "#rightPanel span.error"
        );

        wait.until(d -> {

            boolean success =
                    d.findElements(welcomeHeading)
                            .stream()
                            .anyMatch(WebElement::isDisplayed);

            boolean error =
                    d.findElements(registrationErrors)
                            .stream()
                            .anyMatch(e ->
                                    e.isDisplayed()
                                            && !e.getText().trim().isEmpty()
                            );

            return success || error;
        });

        // STEP 9: Check for registration errors

        List<WebElement> errors =
                driver.findElements(registrationErrors);

        StringBuilder errorMessages =
                new StringBuilder();

        for (WebElement error : errors) {

            if (error.isDisplayed()
                    && !error.getText().trim().isEmpty()) {

                errorMessages.append(
                        error.getText()
                ).append("\n");
            }
        }

        if (errorMessages.length() > 0) {

            System.out.println(
                    "Registration errors:\n" + errorMessages
            );
        }

        Assert.assertEquals(
                errorMessages.length(),
                0,
                "FAIL: Registration returned validation errors!"
        );

        // STEP 10: Verify welcome message

        String actualWelcome = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        welcomeHeading
                )
        ).getText().trim();

        String expectedWelcome =
                "Welcome " + username;

        Assert.assertEquals(
                actualWelcome,
                expectedWelcome,
                "FAIL: Incorrect welcome message!"
        );

        System.out.println(
                "PASS: Welcome message verified!"
        );

        // STEP 11: Verify account creation message

        String pageText = driver.findElement(
                By.id("rightPanel")
        ).getText();

        Assert.assertTrue(
                pageText.contains(
                        "Your account was created successfully"
                ),
                "FAIL: Account creation message not displayed!"
        );

        Assert.assertTrue(
                pageText.contains(
                        "You are now logged in"
                ),
                "FAIL: User is not logged in!"
        );

        System.out.println(
                "PASS: Customer registered successfully!"
        );

        System.out.println(
                "PASS: Account creation verified!"
        );

        System.out.println(
                "\nRegistration test completed successfully!"
        );
    }

    // ==========================================
    // TEST 2 - LOG OUT AND LOG IN
    // ==========================================

    @Test(
            priority = 2,
            dependsOnMethods = "registerNewCustomer"
    )
    public void loginCustomer() {

        System.out.println("\nStarting Login Test...");

        // STEP 1: Log out after registration
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.linkText("Log Out")
                )
        ).click();

        System.out.println("PASS: User logged out successfully!");

        // STEP 2: Wait for login form
        WebElement usernameField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.name("username")
                )
        );

        // STEP 3: Enter registered username
        usernameField.sendKeys(username);

        // STEP 4: Enter registered password
        driver.findElement(
                By.name("password")
        ).sendKeys(password);

        System.out.println("Login credentials entered!");

        pause(5);

        // STEP 5: Click Log In
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("input[value='Log In']")
                )
        ).click();

        System.out.println("Login submitted!");

        // STEP 6: Wait for Accounts Overview
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.linkText("Accounts Overview")
                )
        );

        // STEP 7: Verify welcome message
        String welcomeMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("#leftPanel p.smallText")
                )
        ).getText().trim();

        System.out.println(
                "Welcome message: " + welcomeMessage
        );

        Assert.assertTrue(
                welcomeMessage.contains(firstName),
                "FAIL: First name not displayed after login!"
        );

        Assert.assertTrue(
                welcomeMessage.contains(lastName),
                "FAIL: Last name not displayed after login!"
        );

        // STEP 8: Verify login success
        Assert.assertTrue(
                driver.findElements(
                        By.linkText("Log Out")
                ).stream().anyMatch(WebElement::isDisplayed),
                "FAIL: Log Out link not displayed after login!"
        );

        System.out.println("PASS: Customer login successful!");
        System.out.println("PASS: Welcome message verified!");
        System.out.println("PASS: User authentication verified!");
    }

    // ==========================================
    // TEST 3 - OPEN NEW SAVINGS ACCOUNT
    // ==========================================

    @Test(
            priority = 3,
            dependsOnMethods = "loginCustomer"
    )
    public void openNewSavingsAccount() {

        System.out.println("\nStarting Test 3: Open Savings Account...");

        // STEP 1: Click Open New Account
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.linkText("Open New Account")
                )
        ).click();

        System.out.println("PASS: Open New Account page opened!");

        // STEP 2: Wait for account type dropdown
        WebElement accountTypeDropdown = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("type")
                )
        );
        pause(5);

        // STEP 3: Select Savings account
        Select accountType = new Select(accountTypeDropdown);

        accountType.selectByVisibleText("SAVINGS");

        System.out.println("PASS: Savings account selected!");

        // STEP 4: Wait for funding account dropdown
        wait.until(d -> {
            WebElement element = d.findElement(
                    By.id("fromAccountId")
            );
            Select dropdown = new Select(element);
            return !dropdown.getOptions().isEmpty()
                    && !dropdown.getFirstSelectedOption()
                    .getDomProperty("value").isEmpty();
        });

        WebElement fundingDropdown = driver.findElement(
                By.id("fromAccountId")
        );

        Select fundingAccount = new Select(fundingDropdown);

        // Use first available account
        fundingAccount.selectByIndex(0);

        fundingAccountId = fundingAccount
                .getFirstSelectedOption()
                .getDomProperty("value");

        System.out.println(
                "Funding Account ID: " + fundingAccountId
        );

        Assert.assertFalse(
                fundingAccountId.isBlank(),
                "FAIL: No funding account was selected!"
        );
        pause(5);

        // STEP 5: Click Open New Account button
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("input[value='Open New Account']")
                )
        ).click();

        System.out.println("Account creation submitted!");

        // STEP 6: Verify success heading
        WebElement successHeading = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath(
                                "//div[@id='rightPanel']//h1[contains(.,'Account Opened!')]"
                        )
                )
        );

        Assert.assertTrue(
                successHeading.getText().contains("Account Opened!"),
                "FAIL: Account creation confirmation missing!"
        );

        System.out.println("PASS: Account creation confirmed!");

        // STEP 7: Capture new account ID
        WebElement accountIdElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("newAccountId")
                )
        );

        wait.until(d ->
                !d.findElement(By.id("newAccountId"))
                        .getText().trim().isEmpty()
        );

        newSavingsAccountId =
                accountIdElement.getText().trim();

        System.out.println(
                "New Savings Account ID: " + newSavingsAccountId
        );

        // STEP 8: Verify account ID was generated
        Assert.assertFalse(
                newSavingsAccountId.isBlank(),
                "FAIL: Savings account ID was not generated!"
        );

        Assert.assertTrue(
                newSavingsAccountId.matches("\\d+"),
                "FAIL: Generated account ID is not numeric!"
        );

        Assert.assertNotEquals(
                newSavingsAccountId,
                fundingAccountId,
                "FAIL: New account ID matches funding account ID!"
        );

        System.out.println(
                "PASS: New Savings Account ID verified!"
        );

        System.out.println(
                "\nTEST 3 PASSED: Savings account created successfully!"
        );
    }

    // ==========================================
    // TEST 4 - TRANSFER $100 TO SAVINGS ACCOUNT
    // ==========================================

    @Test(
            priority = 4,
            dependsOnMethods = "openNewSavingsAccount"
    )
    public void transferFunds() {

        System.out.println("\nStarting Test 4: Transfer Funds...");

        // STEP 1: Click Transfer Funds
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.linkText("Transfer Funds")
                )
        ).click();

        System.out.println("PASS: Transfer Funds page opened!");

        // STEP 2: Wait for amount field
        WebElement amountField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("amount")
                )
        );

        // STEP 3: Enter transfer amount
        amountField.clear();
        amountField.sendKeys("100");

        pause(5);

        System.out.println("Transfer amount: $100");

        // STEP 4: Wait for account dropdowns to load
        wait.until(d -> {

            Select fromDropdown = new Select(
                    d.findElement(By.id("fromAccountId"))
            );

            Select toDropdown = new Select(
                    d.findElement(By.id("toAccountId"))
            );

            boolean fromAvailable = fromDropdown
                    .getOptions()
                    .stream()
                    .anyMatch(option ->
                            fundingAccountId.equals(
                                    option.getDomProperty("value")
                            )
                    );

            boolean toAvailable = toDropdown
                    .getOptions()
                    .stream()
                    .anyMatch(option ->
                            newSavingsAccountId.equals(
                                    option.getDomProperty("value")
                            )
                    );

            return fromAvailable && toAvailable;
        });

        // STEP 5: Select original funding account
        Select fromAccount = new Select(
                driver.findElement(By.id("fromAccountId"))
        );

        fromAccount.selectByValue(fundingAccountId);

        System.out.println(
                "From Account: " + fundingAccountId
        );

        // STEP 6: Select new savings account
        Select toAccount = new Select(
                driver.findElement(By.id("toAccountId"))
        );

        toAccount.selectByValue(newSavingsAccountId);

        pause(5);

        System.out.println(
                "To Account: " + newSavingsAccountId
        );

        // STEP 7: Verify selections before transfer
        Assert.assertEquals(
                fromAccount.getFirstSelectedOption()
                        .getDomProperty("value"),
                fundingAccountId,
                "FAIL: Incorrect source account!"
        );

        Assert.assertEquals(
                toAccount.getFirstSelectedOption()
                        .getDomProperty("value"),
                newSavingsAccountId,
                "FAIL: Incorrect destination account!"
        );

        Assert.assertNotEquals(
                fundingAccountId,
                newSavingsAccountId,
                "FAIL: Source and destination accounts are the same!"
        );

        System.out.println(
                "PASS: Transfer details verified!"
        );

        pause(5);

        // STEP 8: Click Transfer
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("input[value='Transfer']")
                )
        ).click();

        System.out.println("Transfer submitted!");

        // STEP 9: Wait for confirmation
        WebElement confirmationHeading = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath(
                                "//div[@id='rightPanel']//h1[contains(.,'Transfer Complete!')]"
                        )
                )
        );

        Assert.assertTrue(
                confirmationHeading.getText()
                        .contains("Transfer Complete!"),
                "FAIL: Transfer confirmation not displayed!"
        );

        // STEP 10: Verify transfer confirmation
        String confirmationText = driver.findElement(
                By.id("rightPanel")
        ).getText();

        System.out.println(
                "Transfer confirmation:\n" + confirmationText
        );

        Assert.assertTrue(
                confirmationText.contains("$100"),
                "FAIL: Transfer amount not confirmed!"
        );

        Assert.assertTrue(
                confirmationText.contains(fundingAccountId),
                "FAIL: Source account not confirmed!"
        );

        Assert.assertTrue(
                confirmationText.contains(newSavingsAccountId),
                "FAIL: Destination account not confirmed!"
        );

        System.out.println(
                "PASS: Transfer amount verified!"
        );

        System.out.println(
                "PASS: Source account verified!"
        );

        System.out.println(
                "PASS: Destination account verified!"
        );

        System.out.println(
                "\nTEST 4 PASSED: $100 transferred successfully!"
        );
    }

    // ==========================================
    // TEST 5 - BILL PAYMENT
    // ==========================================

    @Test(
            priority = 5,
            dependsOnMethods = "transferFunds"
    )
    public void payBill() {

        System.out.println("\nStarting Test 5: Bill Payment...");

        // Test bill payment details
        String payeeName = "Test Water Company";
        String payeeAddress = "235 Power Street";
        String payeeCity = "Valletta";
        String payeeState = "Malta";
        String payeeZipCode = "12345";
        String payeePhone = "7771234567";
        String payeeAccountNumber = "987654321";
        String billAmount = "25.00";

        // STEP 1: Click Bill Pay
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.linkText("Bill Pay")
                )
        ).click();

        System.out.println("PASS: Bill Pay page opened!");

        // STEP 2: Wait for Bill Payment form
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.name("payee.name")
                )
        );

        System.out.println("PASS: Bill Payment form loaded!");

        pause(5);

        // STEP 3: Enter payee information
        driver.findElement(By.name("payee.name"))
                .sendKeys(payeeName);

        driver.findElement(By.name("payee.address.street"))
                .sendKeys(payeeAddress);

        driver.findElement(By.name("payee.address.city"))
                .sendKeys(payeeCity);

        driver.findElement(By.name("payee.address.state"))
                .sendKeys(payeeState);

        driver.findElement(By.name("payee.address.zipCode"))
                .sendKeys(payeeZipCode);

        driver.findElement(By.name("payee.phoneNumber"))
                .sendKeys(payeePhone);

        System.out.println("PASS: Payee information entered!");

        pause(4);

        // STEP 4: Enter payee account number
        driver.findElement(By.name("payee.accountNumber"))
                .sendKeys(payeeAccountNumber);

        // STEP 5: Confirm payee account number
        driver.findElement(By.name("verifyAccount"))
                .sendKeys(payeeAccountNumber);

        System.out.println("PASS: Payee account verified!");

        pause(5);

        // STEP 6: Enter bill amount
        WebElement amountField = driver.findElement(
                By.name("amount")
        );

        amountField.clear();
        amountField.sendKeys(billAmount);

        System.out.println("Bill Amount: $" + billAmount);

        // STEP 7: Wait for funding account
        wait.until(d -> {

            Select dropdown = new Select(
                    d.findElement(By.name("fromAccountId"))
            );

            return dropdown.getOptions()
                    .stream()
                    .anyMatch(option ->
                            fundingAccountId.equals(
                                    option.getDomProperty("value")
                            )
                    );
        });

        // STEP 8: Select original funding account
        Select fromAccount = new Select(
                driver.findElement(
                        By.name("fromAccountId")
                )
        );

        fromAccount.selectByValue(fundingAccountId);

        System.out.println(
                "Funding Account: " + fundingAccountId
        );

        // STEP 9: Verify selected account
        Assert.assertEquals(
                fromAccount.getFirstSelectedOption()
                        .getDomProperty("value"),
                fundingAccountId,
                "FAIL: Incorrect funding account selected!"
        );

        pause(5);
        System.out.println(
                "PASS: Funding account verified!"
        );

        // STEP 10: Verify bill amount before submission
        Assert.assertEquals(
                amountField.getDomProperty("value"),
                billAmount,
                "FAIL: Incorrect bill amount entered!"
        );

        pause(5);

        // STEP 11: Click Send Payment
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector(
                                "input[value='Send Payment']"
                        )
                )
        ).click();

        System.out.println("Bill payment submitted!");

        // STEP 12: Wait for payment confirmation
        WebElement confirmationHeading = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath(
                                "//div[@id='rightPanel']//h1[contains(.,'Bill Payment Complete')]"
                        )
                )
        );

        pause(3);

        // STEP 13: Assert successful payment
        Assert.assertTrue(
                confirmationHeading.getText()
                        .contains("Bill Payment Complete"),
                "FAIL: Bill payment confirmation missing!"
        );

        System.out.println(
                "\nTEST 5 PASSED: Bill payment successful!"
        );

        pause(5);

        // STEP 14: Capture confirmation message
        String confirmationText = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("rightPanel")
                )
        ).getText();

        pause(3);

        System.out.println(
                "Bill Payment Confirmation:\n"
                        + confirmationText
        );

        // STEP 15: Verify payee name
        Assert.assertTrue(
                confirmationText.contains(payeeName),
                "FAIL: Payee name not confirmed!"
        );

        // STEP 16: Verify payment amount
        Assert.assertTrue(
                confirmationText.contains("$25.00"),
                "FAIL: Bill payment amount not confirmed!"
        );

        // STEP 17: Verify source account
        Assert.assertTrue(
                confirmationText.contains(fundingAccountId),
                "FAIL: Funding account not confirmed!"
        );

        System.out.println(
                "PASS: Bill Payment confirmation verified!"
        );

        System.out.println(
                "PASS: Payee name verified!"
        );

        System.out.println(
                "PASS: Payment amount verified!"
        );

        System.out.println(
                "PASS: Funding account verified!"
        );

        System.out.println(
                "\nTEST 5 PASSED: Bill payment successful!"
        );
    }

    // ==========================================
    // TEST 6 - FIND TRANSACTIONS BY AMOUNT
    // ==========================================

    @Test(
            priority = 6,
            dependsOnMethods = "payBill"
    )
    public void findTransactions() {

        System.out.println(
                "\nStarting Test 6: Find Transactions..."
        );

        String validAmount = "100";
        String invalidAmount = "98765.43";

        pause(5);

        // STEP 1: Open Find Transactions
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.linkText("Find Transactions")
                )
        ).click();

        System.out.println(
                "PASS: Find Transactions page opened!"
        );

        // STEP 2: Wait for account dropdown
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("accountId")
                )
        );

        // Wait until our savings account is listed
        wait.until(d -> {

            Select accounts = new Select(
                    d.findElement(By.id("accountId"))
            );

            return accounts.getOptions()
                    .stream()
                    .anyMatch(option ->
                            newSavingsAccountId.equals(
                                    option.getDomProperty("value")
                            )
                    );
        });

        // STEP 3: Select savings account
        Select accountDropdown = new Select(
                driver.findElement(By.id("accountId"))
        );

        accountDropdown.selectByValue(
                newSavingsAccountId
        );

        System.out.println(
                "Selected Account: " + newSavingsAccountId
        );

        // STEP 4: Search for $100
        WebElement amountField = driver.findElement(
                By.id("amount")
        );

        amountField.clear();
        amountField.sendKeys(validAmount);

        System.out.println(
                "Searching for transaction amount: $100"
        );

        pause(5);

        // STEP 5: Click Find Transactions
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("findByAmount")
                )
        ).click();

        // STEP 6: Wait for transaction results
        WebElement transactionTable = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("transactionTable")
                )
        );

        String transactionHistory =
                transactionTable.getText();

        System.out.println(
                "Transaction History:\n"
                        + transactionHistory
        );

        // STEP 7: Verify $100 transaction exists
        List<WebElement> transactionRows =
                transactionTable.findElements(
                        By.cssSelector("tbody tr")
                );

        boolean foundTransaction = transactionRows
                .stream()
                .anyMatch(row ->
                        row.getText().contains("$100.00")
                );

        Assert.assertTrue(
                foundTransaction,
                "FAIL: $100 transaction was not found!"
        );

        System.out.println(
                "PASS: $100 transaction found successfully!"
        );

        pause(6);

        // ==========================================
        // NEGATIVE TEST - SEARCH UNUSED AMOUNT
        // ==========================================

        System.out.println(
                "\nStarting negative transaction search..."
        );

        pause(3);

        // STEP 8: Return to Find Transactions
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.linkText("Find Transactions")
                )
        ).click();

        // STEP 9: Select the same savings account
        wait.until(d -> {

            Select accounts = new Select(
                    d.findElement(By.id("accountId"))
            );

            return accounts.getOptions()
                    .stream()
                    .anyMatch(option ->
                            newSavingsAccountId.equals(
                                    option.getDomProperty("value")
                            )
                    );
        });

        Select negativeSearchAccount = new Select(
                driver.findElement(By.id("accountId"))
        );

        negativeSearchAccount.selectByValue(
                newSavingsAccountId
        );

        // STEP 10: Enter unused amount
        WebElement negativeAmountField = driver.findElement(
                By.id("amount")
        );

        negativeAmountField.clear();
        negativeAmountField.sendKeys(invalidAmount);

        System.out.println(
                "Searching for unused amount: $" + invalidAmount
        );

        pause(3);

        // STEP 11: Click Find Transactions
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("findByAmount")
                )
        ).click();

        // STEP 12: Wait for results or empty-state message
        wait.until(d -> {

            boolean tableVisible =
                    d.findElements(By.id("transactionTable"))
                            .stream()
                            .anyMatch(WebElement::isDisplayed);

            boolean noResultsMessage =
                    d.findElement(By.id("rightPanel"))
                            .getText()
                            .contains("No transactions found");

            return tableVisible || noResultsMessage;
        });

        String negativePageText = driver.findElement(
                By.id("rightPanel")
        ).getText();

        System.out.println(
                "Negative Search Result:\n"
                        + negativePageText
        );

        // STEP 13: Verify no transactions were returned
        List<WebElement> negativeTables =
                driver.findElements(
                        By.id("transactionTable")
                );

        boolean noMatchingRows = true;

        if (!negativeTables.isEmpty()) {

            List<WebElement> rows =
                    negativeTables.get(0)
                            .findElements(
                                    By.cssSelector("tbody tr")
                            );

            noMatchingRows = rows.stream()
                    .noneMatch(row ->
                            row.getText().contains("$")
                                    && row.getText()
                                    .contains(invalidAmount)
                    );

            // Ensure no transaction rows exist at all
            long dataRows = rows.stream()
                    .filter(row ->
                            !row.getText().trim().isEmpty()
                                    && !row.findElements(
                                    By.cssSelector("td")
                            ).isEmpty()
                    )
                    .count();

            noMatchingRows = noMatchingRows
                    && dataRows == 0;
        }

        boolean noResults =
                negativePageText.contains(
                        "No transactions found"
                ) || noMatchingRows;

        Assert.assertTrue(
                noResults,
                "FAIL: Unexpected transactions returned!"
        );

        System.out.println(
                "PASS: No transactions found for unused amount!"
        );

        System.out.println(
                "\nTEST 6 PASSED: Transaction searches verified!"
        );

        pause(3);
    }

    // ==========================================
    // TEST 7 - UPDATE CONTACT INFORMATION
    // ==========================================

    @Test(
            priority = 7,
            dependsOnMethods = "findTransactions"
    )
    public void updateContactInformation() {

        System.out.println(
                "\nStarting Test 7: Update Contact Information..."
        );

        // STEP 1: Click Update Contact Info
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.linkText("Update Contact Info")
                )
        ).click();

        System.out.println(
                "PASS: Update Contact Info page opened!"
        );

        pause(3);

        // STEP 2: Wait for ZIP code field
        WebElement zipCodeField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("customer.address.zipCode")
                )
        );

        // STEP 3: Get current ZIP code
        String oldZipCode =
                zipCodeField.getDomProperty("value");

        System.out.println(
                "Current ZIP Code: " + oldZipCode
        );

        // STEP 4: Extract last 5 digits of phone number
        Assert.assertTrue(
                phoneNumber.length() >= 5,
                "FAIL: Phone number has fewer than 5 digits!"
        );

        String newZipCode = phoneNumber.substring(
                phoneNumber.length() - 5
        );

        System.out.println(
                "Last 5 digits of phone number: " + newZipCode
        );

        // STEP 5: Clear existing ZIP code
        zipCodeField.clear();

        Assert.assertEquals(
                zipCodeField.getDomProperty("value"),
                "",
                "FAIL: ZIP code field was not cleared!"
        );

        System.out.println(
                "PASS: Existing ZIP code cleared!"
        );

        pause(3);

        // STEP 6: Enter new ZIP code
        zipCodeField.sendKeys(newZipCode);

        // STEP 7: Verify new value before submission
        String enteredZipCode =
                zipCodeField.getDomProperty("value");

        Assert.assertEquals(
                enteredZipCode,
                newZipCode,
                "FAIL: Incorrect ZIP code entered!"
        );

        System.out.println(
                "PASS: New ZIP code entered: " + enteredZipCode
        );

        pause(6);

        // STEP 8: Click Update Profile
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector(
                                "input[value='Update Profile']"
                        )
                )
        ).click();

        System.out.println(
                "Profile update submitted!"
        );

        // STEP 9: Wait for success confirmation
        WebElement successHeading = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath(
                                "//div[@id='rightPanel']//h1[" +
                                        "contains(.,'Profile Updated')]"
                        )
                )
        );

        // STEP 10: Verify successful update
        Assert.assertTrue(
                successHeading.getText()
                        .contains("Profile Updated"),
                "FAIL: Profile update confirmation missing!"
        );

        String confirmationText = driver.findElement(
                By.id("rightPanel")
        ).getText();

        Assert.assertTrue(
                confirmationText.contains(
                        "Your updated address and phone number"
                ),
                "FAIL: Profile update success message missing!"
        );

        System.out.println(
                "PASS: Profile update confirmation verified!"
        );

        pause(5);

        // STEP 11: Reopen Update Contact Info
        System.out.println(
                "Reopening profile to verify saved ZIP code..."
        );

        pause(5);

        // Click Update Contact Info again
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.linkText("Update Contact Info")
                )
        ).click();

        // Wait for the ZIP code field to appear
        WebElement savedZipCodeField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("customer.address.zipCode")
                )
        );

        // Allow the profile data to populate
        wait.until(d ->
                !d.findElement(
                        By.id("customer.address.zipCode")
                ).getDomProperty("value").isEmpty()
        );

        // Capture the actual saved value
        String savedZipCode =
                savedZipCodeField.getDomProperty("value");

        System.out.println(
                "Expected ZIP Code: " + newZipCode
        );

        System.out.println(
                "Actual Saved ZIP Code: " + savedZipCode
        );

        pause(3);

        // Assert that the new ZIP code persisted
        Assert.assertEquals(
                savedZipCode,
                newZipCode,
                "FAIL: Updated ZIP code was not saved correctly!"
        );

        System.out.println(
                "PASS: Updated ZIP code saved successfully!"
        );

        System.out.println(
                "\nTEST 7 PASSED: Contact information updated!"
        );

        pause(5);
    }

    // ==========================================
    // TEST 8 - SECTION B: BLAZEDEMO FLIGHT BOOKING
    // ==========================================

    @Test(priority = 8)
    public void bookFlightOnBlazeDemo() {

        System.out.println(
                "\nStarting Section B: BlazeDemo Flight Booking..."
        );

        // STEP 1: Save the current ParaBank window
        String paraBankWindow = driver.getWindowHandle();

        System.out.println(
                "Original ParaBank window: " + paraBankWindow
        );

        // STEP 2: Open a new tab without closing ParaBank
        driver.switchTo().newWindow(WindowType.TAB);

        String blazeDemoWindow = driver.getWindowHandle();

        // STEP 3: Navigate to BlazeDemo
        driver.get("https://blazedemo.com/");

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath(
                                "//h1[contains(.,'Welcome to the Simple Travel Agency')]"
                        )
                )
        );
        pause(3);

        System.out.println("PASS: BlazeDemo opened in a new tab!");

        // STEP 4: Select departure city - Boston
        Select departureCity = new Select(
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                By.name("fromPort")
                        )
                )
        );

        departureCity.selectByVisibleText("Boston");

        System.out.println("Departure City: Boston");

        // STEP 5: Select destination city - Rome
        Select destinationCity = new Select(
                driver.findElement(By.name("toPort"))
        );

        destinationCity.selectByVisibleText("Rome");

        System.out.println("Destination City: Rome");

        // STEP 6: Verify selected cities
        Assert.assertEquals(
                departureCity.getFirstSelectedOption().getText(),
                "Boston",
                "FAIL: Incorrect departure city!"
        );

        Assert.assertEquals(
                destinationCity.getFirstSelectedOption().getText(),
                "Rome",
                "FAIL: Incorrect destination city!"
        );

        System.out.println("PASS: Flight destinations verified!");

        pause(4);

        // STEP 7: Click Find Flights
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector(
                                "input[value='Find Flights']"
                        )
                )
        ).click();

        System.out.println("PASS: Find Flights clicked!");

        // STEP 8: Wait for flight results
        WebElement flightTable = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("table")
                )
        );

        // STEP 9: Retrieve available flights
        List<WebElement> flights =
                flightTable.findElements(
                        By.cssSelector("tbody tr")
                );

        Assert.assertFalse(
                flights.isEmpty(),
                "FAIL: No available flights found!"
        );

        System.out.println(
                "Available flights: " + flights.size()
        );

        // STEP 10: Select first available flight
        WebElement selectedFlight = flights.get(0);

        List<WebElement> flightDetails =
                selectedFlight.findElements(
                        By.tagName("td")
                );

        // Column 1 contains Choose This Flight
        // Column 2 contains Flight Number
        Assert.assertTrue(
                flightDetails.size() >= 2,
                "FAIL: Flight number column missing!"
        );

        // STEP 11: Capture flight number
        String flightNumber =
                flightDetails.get(1).getText().trim();

        Assert.assertFalse(
                flightNumber.isEmpty(),
                "FAIL: Flight number was not captured!"
        );

        System.out.println(
                "Selected Flight Number: " + flightNumber
        );

        pause(5);

        // STEP 12: Click Choose This Flight
        selectedFlight.findElement(
                By.cssSelector(
                        "input[type='submit']"
                )
        ).click();

        System.out.println("PASS: Flight selected successfully!");

        // STEP 13: Wait for purchase page
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("inputName")
                )
        );

        System.out.println("PASS: Purchase page opened!");

        // STEP 14: Enter passenger name
        driver.findElement(By.id("inputName"))
                .sendKeys("Test Customer");

        // STEP 15: Enter passenger address
        driver.findElement(By.id("address"))
                .sendKeys("12 Test Street");

        // STEP 16: Enter city
        driver.findElement(By.id("city"))
                .sendKeys("Valletta");

        // STEP 17: Enter state
        driver.findElement(By.id("state"))
                .sendKeys("Malta");

        // STEP 18: Enter flight number as ZIP code
        WebElement zipCodeField =
                driver.findElement(By.id("zipCode"));

        zipCodeField.clear();
        zipCodeField.sendKeys(flightNumber);

        System.out.println(
                "Flight Number entered as ZIP Code: "
                        + flightNumber
        );

        // STEP 19: Verify ZIP code matches flight number
        Assert.assertEquals(
                zipCodeField.getDomProperty("value"),
                flightNumber,
                "FAIL: ZIP code does not match flight number!"
        );

        System.out.println(
                "PASS: Flight number correctly entered as ZIP code!"
        );

        // STEP 20: Select credit card type
        Select cardType = new Select(
                driver.findElement(By.id("cardType"))
        );

        cardType.selectByVisibleText("Visa");

        // STEP 21: Enter test payment details
        driver.findElement(By.id("creditCardNumber"))
                .sendKeys("4111111111111111");

        WebElement expiryMonth =
                driver.findElement(By.id("creditCardMonth"));

        expiryMonth.clear();
        expiryMonth.sendKeys("12");

        WebElement expiryYear =
                driver.findElement(By.id("creditCardYear"));

        expiryYear.clear();
        expiryYear.sendKeys("2030");

        driver.findElement(By.id("nameOnCard"))
                .sendKeys("Test Customer");

        System.out.println(
                "PASS: Passenger and test payment details entered!"
        );

        // STEP 22: Tick Remember Me checkbox
        WebElement rememberMe = driver.findElement(
                By.id("rememberMe")
        );

        if (!rememberMe.isSelected()) {
            rememberMe.click();
        }

        // STEP 23: Verify checkbox is selected
        Assert.assertTrue(
                rememberMe.isSelected(),
                "FAIL: Remember Me checkbox is not selected!"
        );

        System.out.println(
                "PASS: Remember Me checkbox selected!"
        );

        pause(5);

        // STEP 24: Click Purchase Flight
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector(
                                "input[value='Purchase Flight']"
                        )
                )
        ).click();

        System.out.println("Flight purchase submitted!");

        // STEP 25: Wait for confirmation page
        WebElement confirmationHeading = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath(
                                "//h1[contains(.,'Thank you for your purchase today')]"
                        )
                )
        );

        // STEP 26: Verify booking confirmation
        Assert.assertTrue(
                confirmationHeading.getText().contains(
                        "Thank you for your purchase today!"
                ),
                "FAIL: Flight purchase confirmation missing!"
        );

        System.out.println(
                "PASS: Flight purchase confirmed!"
        );

        pause(5);

        // STEP 27: Print confirmation details
        String confirmationText = driver.findElement(
                By.tagName("body")
        ).getText();

        System.out.println(
                "\nBooking Confirmation:\n"
                        + confirmationText
        );

        // STEP 28: Verify original ParaBank tab still exists
        Assert.assertTrue(
                driver.getWindowHandles().contains(
                        paraBankWindow
                ),
                "FAIL: Original ParaBank window was closed!"
        );

        Assert.assertTrue(
                driver.getWindowHandles().contains(
                        blazeDemoWindow
                ),
                "FAIL: BlazeDemo window is no longer open!"
        );

        System.out.println(
                "PASS: Original ParaBank tab remains open!"
        );

        System.out.println(
                "\nTEST 8 PASSED: BlazeDemo flight booking successful!"
        );
    }


    // ==========================================
    // TEARDOWN - SCREENSHOT AND CLOSE BROWSER
    // ==========================================

    @AfterClass(alwaysRun = true)
    public void tearDown() {

        if (driver != null) {

            // Save screenshot of final page
            try {

                Path screenshotDirectory = Path.of(
                        "target",
                        "screenshots"
                );

                Files.createDirectories(
                        screenshotDirectory
                );

                byte[] screenshot =
                        ((TakesScreenshot) driver)
                                .getScreenshotAs(
                                        OutputType.BYTES
                                );

                Path screenshotPath =
                        screenshotDirectory.resolve(
                                "final-test-result.png"
                        );

                Files.write(
                        screenshotPath,
                        screenshot
                );

                System.out.println(
                        "Screenshot saved at: "
                                + screenshotPath.toAbsolutePath()
                );

            } catch (Exception e) {

                System.out.println(
                        "Screenshot could not be saved: "
                                + e.getMessage()
                );
            }

            // Keep browser open for 10 seconds
            System.out.println(
                    "\nBrowser will close in 10 seconds..."
            );

            try {

                Thread.sleep(10000);

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();
            }

            driver.quit();

            System.out.println(
                    "Browser closed successfully!"
            );
        }
    }
}
