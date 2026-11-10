
package Task13;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.*;

public class DropdownSelectionTest {

    @Test
    public void selectDropdownOptions()
            throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        try {
            driver.manage().window().maximize();

            WebDriverWait wait = new WebDriverWait(
                    driver, Duration.ofSeconds(20));

            // ========================================
            // STEP 1: Open Selenium Practice Blog
            // ========================================

            driver.get(
                    "https://selenium08.blogspot.com/"
            );

            System.out.println("PASS: Blog opened");
            Thread.sleep(2000);


            // ========================================
            // STEP 2: Search for Demo dropdown
            // ========================================

            // Open search field if collapsed
            List<WebElement> searchFields =
                    driver.findElements(
                            By.cssSelector(
                                    "input[name='q']"
                            )
                    );

            if (searchFields.isEmpty()
                    || !searchFields.get(0).isDisplayed()) {

                WebElement searchIcon = wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.cssSelector(".search-expand")
                        )
                );

                searchIcon.click();
            }

            WebElement searchBox = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.cssSelector("input[name='q']")
                    )
            );

            searchBox.sendKeys("Demo dropdown");
            Thread.sleep(2000);
            searchBox.sendKeys(Keys.ENTER);

            System.out.println("PASS: Search submitted");

            // Click the matching blog result
            WebElement dropdownPost = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath(
                                    "//a[contains(" +
                                            "translate(normalize-space(.)," +
                                            "'ABCDEFGHIJKLMNOPQRSTUVWXYZ'," +
                                            "'abcdefghijklmnopqrstuvwxyz')," +
                                            "'demo dropdown')]"
                            )
                    )
            );

            dropdownPost.click();

            System.out.println("PASS: Dropdown page opened");
            Thread.sleep(3000);


            // ========================================
            // STEP 3: Select Nigeria
            // ========================================

            // Locate dropdown containing Nigeria
            WebElement countryDropdown = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath(
                                    "//select[option[" +
                                            "normalize-space()='Nigeria']]"
                            )
                    )
            );

            Select country = new Select(countryDropdown);

            // Select Nigeria
            country.selectByVisibleText("Nigeria");

            Thread.sleep(2000);

            // Verify country selection
            String selectedCountry =
                    country.getFirstSelectedOption()
                            .getText().trim();

            assertEquals("Nigeria", selectedCountry);

            System.out.println(
                    "PASS: Country selected - "
                            + selectedCountry
            );


            // ========================================
            // STEP 4: Select January, February, March
            // ========================================

            WebElement monthsDropdown = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath(
                                    "//select[@multiple and option[" +
                                            "normalize-space()='January']]"
                            )
                    )
            );

            Select months = new Select(monthsDropdown);

            // Verify it supports multiple selections
            assertTrue(
                    "Month dropdown is not a multi-select",
                    months.isMultiple()
            );

            // Clear previous selections
            months.deselectAll();

            // Select January
            months.selectByVisibleText("January");
            Thread.sleep(1500);

            // Select February
            months.selectByVisibleText("February");
            Thread.sleep(1500);

            // Select March
            months.selectByVisibleText("March");
            Thread.sleep(2000);


            // ========================================
            // STEP 5: Verify selected months
            // ========================================

            List<WebElement> selectedMonths =
                    months.getAllSelectedOptions();

            assertEquals(
                    "Expected exactly three selected months",
                    3,
                    selectedMonths.size()
            );

            String[] expectedMonths = {
                    "January", "February", "March"
            };

            for (String month : expectedMonths) {

                boolean found = false;

                for (WebElement option : selectedMonths) {

                    if (option.getText().trim()
                            .equals(month)) {

                        found = true;
                        break;
                    }
                }

                assertTrue(
                        month + " was not selected",
                        found
                );
            }


            // ========================================
            // PRINT SELECTED VALUES
            // ========================================

            System.out.println();
            System.out.println(
                    "=================================="
            );

            System.out.println(
                    "       RESULTS"
            );

            System.out.println(
                    "=================================="
            );

            System.out.println(
                    "Country: " + selectedCountry
            );

            System.out.println("Selected Months:");

            for (WebElement option : selectedMonths) {

                System.out.println(
                        "- " + option.getText()
                );
            }

            // Keep browser open for observation
            Thread.sleep(5000);

        } finally {
            driver.quit();
        }
    }
}
