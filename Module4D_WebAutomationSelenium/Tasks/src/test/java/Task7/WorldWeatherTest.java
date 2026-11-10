
package Task7;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertFalse;

public class WorldWeatherTest {

    @Test
    public void getWeatherForecast() throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        try {
            // Maximize browser
            driver.manage().window().maximize();

            WebDriverWait wait = new WebDriverWait(
                    driver, Duration.ofSeconds(20));

            // =========================================
            // STEP 1: Open World Weather Website
            // =========================================

            driver.get(
                    "https://worldweather.wmo.int/en/home.html"
            );

            System.out.println("PASS: Website opened");
            Thread.sleep(2000);


            // =========================================
            // STEP 2: Search for Malta
            // =========================================

            WebElement searchBox = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath(
                                    "//input[contains(@placeholder,'city')]"
                            )
                    )
            );

            searchBox.sendKeys("Malta");
            Thread.sleep(2000);

            // Select Malta
            WebElement malta = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath(
                                    "//*[self::a or self::li]" +
                                            "[normalize-space()='Malta']"
                            )
                    )
            );

            malta.click();

            System.out.println("PASS: Malta selected");
            Thread.sleep(2000);


            // =========================================
            // STEP 3: Click Luqa, Malta
            // =========================================

            WebElement luqa = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath(
                                    "//a[contains(normalize-space(.)," +
                                            "'Luqa, Malta')]"
                            )
                    )
            );

            luqa.click();

            wait.until(
                    ExpectedConditions.urlContains("city.html")
            );

            System.out.println("PASS: Luqa forecast opened");
            Thread.sleep(2000);


            // =========================================
            // STEP 4: Locate All Forecast Days
            // =========================================

            By forecastLocator = By.cssSelector(
                    ".city_forecast_day_object"
            );

            List<WebElement> forecastDays = wait.until(
                    ExpectedConditions
                            .numberOfElementsToBeMoreThan(
                                    forecastLocator, 0
                            )
            );

            assertFalse(
                    "No forecast days found",
                    forecastDays.isEmpty()
            );


            // =========================================
            // STEP 5: Extract Date and Weather
            // =========================================

            System.out.println();
            System.out.println(
                    "===================================="
            );
            System.out.println(
                    "      WEATHER FORECAST FOR LUQA"
            );
            System.out.println(
                    "===================================="
            );

            int count = 0;

            for (WebElement day : forecastDays) {

                // Get date
                WebElement dateElement = day.findElement(
                        By.cssSelector(
                                ".city_weekday_n_date"
                        )
                );

                // Get temperature
                WebElement temperatureElement =
                        day.findElement(
                                By.cssSelector(
                                        ".city_fc_temp"
                                )
                        );

                // Get weather description
                WebElement descriptionElement =
                        day.findElement(
                                By.cssSelector(
                                        ".city_fc_desc"
                                )
                        );

                String date =
                        dateElement.getText().trim();

                String temperature =
                        temperatureElement.getText().trim();

                String description =
                        descriptionElement.getText().trim();

                // Print only valid forecasts
                if (!date.isEmpty()
                        && !description.isEmpty()) {

                    count++;

                    System.out.println();
                    System.out.println(
                            "DAY " + count
                    );

                    System.out.println(
                            "Date: " + date
                    );

                    System.out.println(
                            "Temperature: " + temperature
                    );

                    System.out.println(
                            "Weather: " + description
                    );

                    System.out.println(
                            "--------------------------------"
                    );
                }
            }


            // =========================================
            // STEP 6: Verify Results
            // =========================================

            assertFalse(
                    "No weather descriptions were extracted",
                    count == 0
            );

            System.out.println();
            System.out.println(
                    "Total forecast days: " + count
            );
            // Keep browser open for observation
            Thread.sleep(5000);

        } finally {

            driver.quit();
        }
    }
}
