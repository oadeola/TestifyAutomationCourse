import appium
import traceback
from appium import webdriver
from appium.options.android import UiAutomator2Options
from appium.webdriver.common.appiumby import AppiumBy
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC


options = UiAutomator2Options()

options.platform_name = "Android"
options.automation_name = "UiAutomator2"
options.device_name = "emulator-5554"
options.app = 'C:/git/TestifyAutomationCourse/Module4E_MobileTestAutomation/MyDemoApp/Android-MyDemoAppRN.1.1.0.build-226.apk'
options.app_package = "com.saucelabs.mydemoapp.rn"
options.app_activity = ".MainActivity"
options.no_reset = True

driver = None

try:
    driver = webdriver.Remote(
        "http://127.0.0.1:4725",
        options=options
    )

    wait = WebDriverWait(driver, 20)

    # Track test results
    passed = 0
    failed = 0

    # ==============================================
    # NAVIGATE TO LOGIN PAGE
    # ==============================================
    #
    side_navigator = driver.find_element(By.XPATH, '//android.view.ViewGroup[@content-desc="open menu"]/android.widget.ImageView')
    side_navigator.click()

    login_page = driver.find_element(By.XPATH, '//android.widget.TextView[@text="Log In"]')
    login_page.click()

    login_username = wait.until(EC.element_to_be_clickable((AppiumBy.ACCESSIBILITY_ID, 'Username input field')))
    login_username.click()
    login_username.clear()
    #login_username.send_keys("")

    login_password = wait.until(EC.element_to_be_clickable((AppiumBy.ACCESSIBILITY_ID, 'Password input field')))
    login_password.click()
    login_password.clear()
    #login_password.send_keys("")

    login_button = wait.until(EC.element_to_be_clickable((AppiumBy.ACCESSIBILITY_ID, 'Login button')))
    login_button.click()

    # ==============================================
    # NEGATIVE TEST 1: Empty Username
    # ==============================================

    try:
        print("\nRunning Negative Test 1...")

        login_username = driver.find_element(
            AppiumBy.ACCESSIBILITY_ID, "Username input field"
        )
        login_username.click()
        login_username.clear()

        login_password = driver.find_element(
            AppiumBy.ACCESSIBILITY_ID, "Password input field"
        )
        login_password.click()
        login_password.clear()

        login_button = driver.find_element(
            AppiumBy.ACCESSIBILITY_ID, "Login button"
        )
        login_button.click()

        # Assert that username validation is displayed
        wait.until(
            lambda d: "Username is required" in d.page_source
        )

        assert "Username is required" in driver.page_source, \
            "FAIL: Username validation error not displayed"

        print("PASS: Empty username validation successful")
        passed += 1

    except Exception as e:
        print("FAIL: Negative Test 1 -", str(e))
        failed += 1

        # ==============================================
        # NEGATIVE TEST 2: Empty Password
        # ==============================================

    try:
        print("\nRunning Negative Test 2...")

        login_username = driver.find_element(
            AppiumBy.ACCESSIBILITY_ID, "Username input field"
        )
        login_username.click()
        login_username.clear()
        login_username.send_keys("bob@example.com")

        login_password = driver.find_element(
            AppiumBy.ACCESSIBILITY_ID, "Password input field"
        )
        login_password.click()
        login_password.clear()

        login_button = driver.find_element(
            AppiumBy.ACCESSIBILITY_ID, "Login button"
        )
        login_button.click()

        # Assert that password validation is displayed
        wait.until(
            lambda d: "Password is required" in d.page_source
        )

        assert "Password is required" in driver.page_source, \
            "FAIL: Password validation error not displayed"

        print("PASS: Empty password validation successful")
        passed += 1

    except Exception as e:
        print("FAIL: Negative Test 2 -", str(e))
        failed += 1

    # ==============================================
    # NEGATIVE TEST 3: Invalid Credentials
    # ==============================================

    try:
        print("\nRunning Negative Test 3...")

        login_username = driver.find_element(
            AppiumBy.ACCESSIBILITY_ID, "Username input field"
        )
        login_username.click()
        login_username.clear()
        login_username.send_keys("baby@example.com")

        login_password = driver.find_element(
            AppiumBy.ACCESSIBILITY_ID, "Password input field"
        )
        login_password.click()
        login_password.clear()
        login_password.send_keys("1234")

        if driver.is_keyboard_shown():
            driver.hide_keyboard()

        login_button = driver.find_element(
            AppiumBy.ACCESSIBILITY_ID, "Login button"
        )
        login_button.click()

        expected_error = (
            "Provided credentials do not match any user in this service."
        )

        wait.until(
            lambda d: expected_error in d.page_source
        )

        assert expected_error in driver.page_source, \
            "FAIL: Invalid credentials error not displayed"

        print("PASS: Invalid credentials rejected successfully")
        passed += 1

    except Exception as e:
        print("FAIL: Negative Test 3 -", str(e))
        failed += 1

    # ==============================================
    # NEGATIVE TEST 4: Locked-out Account
    # ==============================================

    try:
        print("\nRunning Negative Test 4...")

        login_username = driver.find_element(
            AppiumBy.ACCESSIBILITY_ID, "Username input field"
        )
        login_username.click()
        login_username.clear()
        login_username.send_keys("alice@example.com")

        login_password = wait.until(
            EC.element_to_be_clickable((
                AppiumBy.ACCESSIBILITY_ID, "Password input field"
            ))
        )
        login_password.click()
        login_password.clear()
        login_password.send_keys("10203040")

        if driver.is_keyboard_shown():
            driver.hide_keyboard()

        login_button = wait.until(
            EC.element_to_be_clickable((
                AppiumBy.ACCESSIBILITY_ID, "Login button"
            ))
        )
        login_button.click()

        # Assert that the locked-out error is displayed
        expected_error = "Sorry, this user has been locked out."

        wait.until(
            lambda d: expected_error in d.page_source
        )

        assert expected_error in driver.page_source, \
            "FAIL: Locked-out account error not displayed"

        print("PASS: Locked-out account rejected successfully")
        passed += 1

    except Exception as e:
        print("FAIL: Negative Test 4 -", str(e))
        failed += 1

    # ==============================================
    # POSITIVE TEST 5: Login with Valid Credentials
    # ==============================================
    #
    try:
        print("\nRunning Positive Test 5...")

        login_username = driver.find_element(
            AppiumBy.ACCESSIBILITY_ID, "Username input field"
        )
        login_username.click()
        login_username.clear()
        login_username.send_keys("bob@example.com")

        login_password = wait.until(
            EC.element_to_be_clickable((
                AppiumBy.ACCESSIBILITY_ID, "Password input field"
            ))
        )
        login_password.click()
        login_password.clear()
        login_password.send_keys("10203040")

        if driver.is_keyboard_shown():
            driver.hide_keyboard()

        login_button = wait.until(
            EC.element_to_be_clickable((
                AppiumBy.ACCESSIBILITY_ID, "Login button"
            ))
        )
        login_button.click()

        # Assert that PRODUCTS page is displayed
        products_page = wait.until(
            EC.visibility_of_element_located((
                AppiumBy.XPATH,
                '//*[@text="Products" or @text="PRODUCTS"]'
            ))
        )

        assert products_page.is_displayed(), \
            "FAIL: PRODUCTS page is not displayed"

        print("PASS: Valid login successful")
        print("PASS: PRODUCTS page displayed successfully")

        passed += 1

    except Exception as e:
        print("FAIL: Positive Test 5 -", str(e))
        failed += 1

    # ==============================================
    # TEST 6: Sort Products by Price - Ascending
    # ==============================================

    try:
        print("\nRunning Test 6: Sort Products by Price - Ascending...")

        # Locate and click the sorting icon
        sort_button = wait.until(
            EC.element_to_be_clickable((
                AppiumBy.ACCESSIBILITY_ID, "sort button"
            ))
        )
        sort_button.click()

        # Select Price - Ascending
        ascending_option = wait.until(
            EC.element_to_be_clickable((
                AppiumBy.XPATH,
                '//android.widget.TextView[@text="Price - Ascending"]'
            ))
        )
        ascending_option.click()

        print("Price - Ascending option selected successfully")

        # Find all visible product prices
        price_elements = wait.until(
            lambda d: d.find_elements(
                AppiumBy.XPATH,
                '//android.widget.TextView[@content-desc="store item price"]'
            )
        )

        # Extract prices as numbers
        product_prices = []

        for price_element in price_elements:
            price_text = price_element.text
            price = float(price_text.replace("$", "").strip())
            product_prices.append(price)

        print("Visible product prices:", product_prices)

        # Assert that at least two prices were retrieved
        assert len(product_prices) >= 2, \
            "FAIL: Not enough product prices found"

        # Assert that products are arranged in ascending order
        assert product_prices == sorted(product_prices), \
            "FAIL: Products are not sorted by ascending price"

        print("PASS: Visible products sorted by Price - Ascending")
        passed += 1

    except Exception as e:
        print("FAIL: Test 6 -", str(e))
        failed += 1



    # ==============================================
    # TEST 7: Add All Products to Cart
    # ==============================================
    #
    try:
        print("\nRunning Test 7: Add all products to cart...")

        discovered_products = []
        added_products = set()

        product_locator = (
            AppiumBy.XPATH,
            '//*[@content-desc="store item text"]'
        )


        # Function to scroll in either direction
        def swipe_products(direction):
            size = driver.get_window_size()

            driver.execute_script(
                "mobile: swipeGesture",
                {
                    "left": int(size["width"] * 0.10),
                    "top": int(size["height"] * 0.28),
                    "width": int(size["width"] * 0.80),
                    "height": int(size["height"] * 0.55),
                    "direction": direction,
                    "percent": 0.65
                }
            )


        # Function to read visible product names
        def get_visible_products():
            elements = driver.find_elements(*product_locator)

            return [
                element.text.strip()
                for element in elements
                if element.text.strip()
            ]


        # Function to return to the top of Products
        def scroll_to_top():
            for attempt in range(12):
                before = get_visible_products()
                swipe_products("down")
                after = get_visible_products()

                if after == before:
                    break


        # ==============================================
        # PHASE 1: Discover Products
        # ==============================================

        print("\nDiscovering products...")

        scroll_to_top()

        unchanged_scrolls = 0

        for attempt in range(30):

            visible_products = get_visible_products()

            print("Visible:", visible_products)

            for product_name in visible_products:
                if product_name not in discovered_products:
                    discovered_products.append(product_name)
                    print("Discovered:", product_name)

            # Swipe up to move down the catalogue
            swipe_products("up")

            after_scroll = get_visible_products()

            print("After scrolling:", after_scroll)

            if after_scroll == visible_products:
                unchanged_scrolls += 1
            else:
                unchanged_scrolls = 0

            # Stop after repeatedly reaching the same view
            if unchanged_scrolls >= 3:
                break

        print("\nTotal products discovered:",
              len(discovered_products))

        # This assignment's catalogue contains 6 products
        assert len(discovered_products) == 6, \
            f"FAIL: Expected 6 products, discovered {len(discovered_products)}"

        # ==============================================
        # PHASE 2: Add Products to Cart
        # ==============================================

        print("\nAdding products to cart...")

        for product_name in discovered_products:

            print("\nSearching for:", product_name)

            # Always start from the top after navigation
            scroll_to_top()

            found_product = None

            for attempt in range(30):

                # Retrieve fresh elements after each scroll
                product_elements = driver.find_elements(
                    *product_locator
                )

                for product in product_elements:

                    if (
                            product.text.strip() == product_name
                            and product.is_displayed()
                    ):
                        found_product = product
                        break

                if found_product:
                    break

                before = get_visible_products()
                swipe_products("up")
                after = get_visible_products()

                if after == before:
                    break

            assert found_product is not None, \
                f"FAIL: Product not found: {product_name}"

            # Open selected product
            found_product.click()

            print("Opened:", product_name)

            # Add product to cart
            add_button = wait.until(
                EC.element_to_be_clickable((
                    AppiumBy.ACCESSIBILITY_ID,
                    "Add To Cart button"
                ))
            )

            # Click Add To Cart
            add_button.click()

            print("Add To Cart clicked:", product_name)

            # Record that the add action completed
            added_products.add(product_name)

            print("Recorded product:", product_name)
            print("Products recorded so far:", len(added_products))

            # Return to Products page
            driver.back()

            # Wait for Products page to load
            wait.until(
                lambda d: len(d.find_elements(
                    AppiumBy.XPATH,
                    '//*[@content-desc="store item text"]'
                )) > 0
            )

            print("PASS: Returned to Products page")

            # Check if product names are available again
            try:
                wait.until(
                    lambda d: len(d.find_elements(
                        AppiumBy.XPATH,
                        '//*[@content-desc="store item text"]'
                    )) > 0
                )

                print("PASS: Returned to Products page")


            except Exception:
                print("Products grid not found after navigating back")

                print("Current activity:", driver.current_activity)
                print("Current page source:", driver.page_source)

                raise

        # ==============================================
        # PHASE 3: Assertions
        # ==============================================

        assert len(added_products) == len(discovered_products), \
            "FAIL: Not all discovered products were added"

        expected_cart_count = len(added_products)

        assert expected_cart_count == 6, \
            f"FAIL: Expected 6 products, added {expected_cart_count}"

        print("\n========== TEST 7 RESULTS ==========")
        print("Products discovered:", len(discovered_products))
        print("Products added:", len(added_products))

        for product_name in added_products:
            print("Added:", product_name)

        print("PASS: All 6 products added to cart")
        passed += 1

    except Exception as e:
        print("FAIL: Test 7 -", repr(e))
        traceback.print_exc()
        failed += 1


    # ==============================================
    # TEST 8: Click Cart Icon to View My Cart
    # ==============================================

    try:
        print("\nRunning Test 8: Navigate to My Cart...")

        # Locate and click the cart icon
        cart_icon = wait.until(
            EC.element_to_be_clickable((
                AppiumBy.ACCESSIBILITY_ID,
                "cart badge"
            ))
        )
        cart_icon.click()

        print("Cart icon clicked successfully")

        # Assert that My Cart page is displayed
        my_cart_page = wait.until(
            EC.visibility_of_element_located((
                AppiumBy.XPATH,
                '//*[@content-desc="cart screen"]'
            ))
        )

        assert my_cart_page.is_displayed(), \
            "FAIL: My Cart page is not displayed"

        print("PASS: Successfully navigated to My Cart")
        passed += 1

    except Exception as e:
        print("FAIL: Test 8 -", repr(e))
        failed += 1

    # ==============================================
    # TEST 9: Verify All Products in My Cart
    # ==============================================

    try:
        print("\nRunning Test 9: Verify all cart products...")

        # Get products recorded during Test 7
        expected_products = set(added_products)
        actual_products = set()

        print("Expected products:", sorted(expected_products))

        # Ensure Test 7 recorded products successfully
        assert expected_products, \
            "FAIL: No products were recorded during Test 7"

        # Confirm that My Cart page is displayed
        my_cart_page = wait.until(
            EC.visibility_of_element_located((
                AppiumBy.XPATH,
                '//*[@content-desc="cart screen"]'
            ))
        )

        assert my_cart_page.is_displayed(), \
            "FAIL: My Cart page is not displayed"

        print("My Cart page displayed successfully")

        # Scroll through My Cart and collect product names
        for attempt in range(25):

            # Find all product names currently visible
            cart_items = driver.find_elements(
                AppiumBy.ACCESSIBILITY_ID,
                "product label"
            )

            # Record product names
            for item in cart_items:
                product_name = item.text.strip()

                if product_name:
                    actual_products.add(product_name)

            print("Products found:", sorted(actual_products))

            # Stop when all expected products are found
            if actual_products == expected_products:
                break

            # Get emulator screen dimensions
            size = driver.get_window_size()

            # Swipe upward to scroll down the cart
            driver.execute_script(
                "mobile: swipeGesture",
                {
                    "left": int(size["width"] * 0.10),
                    "top": int(size["height"] * 0.25),
                    "width": int(size["width"] * 0.80),
                    "height": int(size["height"] * 0.55),
                    "direction": "up",
                    "percent": 0.65
                }
            )

        # Collect products after the final scroll
        cart_items = driver.find_elements(
            AppiumBy.ACCESSIBILITY_ID,
            "product label"
        )

        for item in cart_items:
            product_name = item.text.strip()

            if product_name:
                actual_products.add(product_name)

        # Display final results
        print("\nExpected products:", sorted(expected_products))
        print("Actual products:", sorted(actual_products))

        # Assert that all expected products are present
        assert actual_products == expected_products, \
            (
                "FAIL: Cart contents do not match.\n"
                f"Missing: {expected_products - actual_products}\n"
                f"Unexpected: {actual_products - expected_products}"
            )

        # Assert the total number of unique products
        assert len(actual_products) == expected_cart_count, \
            (
                f"FAIL: Expected {expected_cart_count} products, "
                f"but found {len(actual_products)}"
            )

        print("PASS: All cart products verified successfully")
        passed += 1

    except Exception as e:
        print("FAIL: Test 9 -", repr(e))
        failed += 1

    # ==============================================
    # TEST 10: Remove Two Items From My Cart
    # ==============================================

    try:
        print("\nRunning Test 10: Remove two items from cart...")

        # Confirm My Cart is displayed
        my_cart_page = wait.until(
            EC.visibility_of_element_located((
                AppiumBy.XPATH,
                '//*[@content-desc="cart screen"]'
            ))
        )

        assert my_cart_page.is_displayed(), \
            "FAIL: My Cart page is not displayed"

        # Verify Remove Item buttons are available
        removed_count = 0

        for i in range(2):
            # Find a Remove Item button currently visible
            remove_button = wait.until(
                EC.element_to_be_clickable((
                    AppiumBy.XPATH,
                    '//*[@text="Remove Item"]'
                ))
            )

            # Click Remove Item
            remove_button.click()

            removed_count += 1

            print(f"Remove Item clicked: {removed_count}")

            # Wait for the cart screen to remain available
            wait.until(
                EC.visibility_of_element_located((
                    AppiumBy.XPATH,
                    '//*[@content-desc="cart screen"]'
                ))
            )

        assert removed_count == 2, \
            "FAIL: Expected two removal actions"

        print("CHECK: Two removal actions completed")
        print("Verify the remaining cart quantity in Test 11")

    except Exception as e:
        print("FAIL: Test 10 -", repr(e))
        failed += 1

    # ==============================================
    # TEST 11: Verify Four Items Remain
    # ==============================================

    import re

    try:
        print("\nRunning Test 11: Verify remaining cart count...")

        # Confirm My Cart page
        my_cart_page = wait.until(
            EC.visibility_of_element_located((
                AppiumBy.XPATH,
                '//*[@content-desc="cart screen"]'
            ))
        )

        assert my_cart_page.is_displayed(), \
            "FAIL: My Cart page not displayed"

        # Find the element containing the item quantity
        cart_count_element = wait.until(
            EC.visibility_of_element_located((
                AppiumBy.XPATH,
                '//*[contains(@text, "items") or '
                'contains(@content-desc, "items")]'
            ))
        )

        # Read text or accessibility description
        count_text = (
                cart_count_element.text
                or cart_count_element.get_attribute("content-desc")
                or ""
        )

        print("Cart count text:", count_text)

        # Extract number from "4 items" or "Total: 4 items"
        match = re.search(r"(\d+)\s+items?\b", count_text)

        assert match is not None, \
            f"FAIL: Unable to extract item count from {count_text!r}"

        remaining_count = int(match.group(1))

        assert remaining_count == 4, \
            f"FAIL: Expected 4 items, found {remaining_count}"

        print("PASS: Four items remain in My Cart")
        passed += 1

    except Exception as e:
        print("FAIL: Test 11 -", repr(e))
        failed += 1

    # ==============================================
    # TEST 12: Proceed to Checkout
    # ==============================================

    try:
        print("\nRunning Test 12: Proceed to Checkout...")

        # Confirm My Cart page is displayed
        my_cart_page = wait.until(
            EC.visibility_of_element_located((
                AppiumBy.XPATH,
                '//*[@content-desc="cart screen"]'
            ))
        )

        assert my_cart_page.is_displayed(), \
            "FAIL: My Cart page is not displayed"

        # Locate Proceed To Checkout button
        checkout_button = wait.until(
            EC.element_to_be_clickable((
                AppiumBy.XPATH,
                '//android.view.ViewGroup[@content-desc="Proceed To Checkout button"]'
            ))
        )

        # Click Proceed To Checkout
        checkout_button.click()

        print("Proceed To Checkout button clicked successfully")

        # Assert Checkout Address screen is displayed
        checkout_page = wait.until(
            EC.visibility_of_element_located((
                AppiumBy.XPATH,
                '//android.widget.ScrollView[@content-desc="checkout address screen"]'
            ))
        )

        assert checkout_page.is_displayed(), \
            "FAIL: Checkout Address screen is not displayed"

        print("PASS: Successfully navigated to Checkout Address screen")
        passed += 1

    except Exception as e:
        print("FAIL: Test 12 -", repr(e))
        failed += 1

    # ==============================================
    # TEST 13: Enter Shipping Details
    # ==============================================

    try:
        print("\nRunning Test 13: Enter Shipping Details...")

        # Verify Checkout Address screen is displayed
        checkout_page = wait.until(
            EC.visibility_of_element_located((
                AppiumBy.XPATH,
                '//android.widget.ScrollView[@content-desc="checkout address screen"]'
            ))
        )

        assert checkout_page.is_displayed(), \
            "FAIL: Checkout Address screen is not displayed"

        # Shipping test data
        shipping_details = {
            "Full Name* input field": "Ade Ola",
            "Address Line 1* input field": "25 Ola Street",
            "Address Line 2 input field": "Apartment 14",
            "City* input field": "Valletta",
            "State/Region input field": "Malta",
            "Zip Code* input field": "VLT 1117",
            "Country* input field": "Malta"
        }

        # Enter all shipping details
        for field_id, value in shipping_details.items():
            print("Entering:", field_id)

            # Locate field using Accessibility ID
            field = wait.until(
                EC.element_to_be_clickable((
                    AppiumBy.ACCESSIBILITY_ID,
                    field_id
                ))
            )

            # Click and clear existing value
            field.click()
            field.clear()

            # Enter new value
            field.send_keys(value)

            # Assert field contains expected value
            actual_value = field.get_attribute("text")

            assert actual_value == value, \
                (
                    f"FAIL: Incorrect value for {field_id}. "
                    f"Expected '{value}', found '{actual_value}'"
                )

            print("PASS:", field_id, "=", actual_value)

        # Hide the keyboard after entering details
        if driver.is_keyboard_shown():
            driver.hide_keyboard()

        # Verify all seven fields were populated
        assert len(shipping_details) == 7, \
            "FAIL: Shipping details are incomplete"

        print("\nPASS: All shipping details entered successfully")
        passed += 1

    except Exception as e:
        print("FAIL: Test 13 -", repr(e))
        failed += 1

    # ==============================================
    # TEST 14: Proceed to Payment Page
    # ==============================================

    try:
        print("\nRunning Test 14: Proceed to Payment Page...")

        # Confirm Checkout Address screen is displayed
        checkout_page = wait.until(
            EC.visibility_of_element_located((
                AppiumBy.XPATH,
                '//android.widget.ScrollView[@content-desc="checkout address screen"]'
            ))
        )

        assert checkout_page.is_displayed(), \
            "FAIL: Checkout Address screen is not displayed"

        # Locate the To Payment button
        payment_button = wait.until(
            EC.element_to_be_clickable((
                AppiumBy.XPATH,
                '//android.view.ViewGroup[@content-desc="To Payment button"]'
            ))
        )

        # Click To Payment
        payment_button.click()

        print("To Payment button clicked successfully")

        # Check whether the Payment page is displayed
        # This heading locator is provisional
        payment_page = wait.until(
            EC.visibility_of_element_located((
                AppiumBy.XPATH,
                '//*[@text="Checkout: Payment" or @text="Payment"]'
            ))
        )

        assert payment_page.is_displayed(), \
            "FAIL: Payment page is not displayed"

        print("PASS: Successfully navigated to Payment page")
        passed += 1

    except Exception as e:
        print("FAIL: Test 14 -", repr(e))
        failed += 1

    # ==============================================
    # TEST 15: Enter Payment Information
    # ==============================================

    try:
        print("\nRunning Test 15: Enter Payment Information...")

        # Verify Payment screen is displayed
        payment_page = wait.until(
            EC.visibility_of_element_located((
                AppiumBy.ACCESSIBILITY_ID,
                "checkout payment screen"
            ))
        )

        assert payment_page.is_displayed(), \
            "FAIL: Payment screen is not displayed"

        print("PASS: Payment screen displayed successfully")

        # Fictional demo payment details
        payment_details = {
            "Full Name* input field": "Ade Ola",
            "Card Number* input field": "4111111111111111",
            "Expiration Date* input field": "11/30",
            "Security Code* input field": "456"
        }

        # Enter payment information
        for field_id, value in payment_details.items():

            print("Entering:", field_id)

            payment_field = wait.until(
                EC.element_to_be_clickable((
                    AppiumBy.ACCESSIBILITY_ID,
                    field_id
                ))
            )

            payment_field.click()
            payment_field.clear()
            payment_field.send_keys(value)

            # Verify entered value
            actual_value = (
                    payment_field.get_attribute("text") or ""
            )

            # Card number may be automatically formatted
            # with spaces by the application
            if field_id == "Card Number* input field":
                actual_value = actual_value.replace(" ", "")
                expected_value = value.replace(" ", "")
            else:
                expected_value = value

            assert actual_value == expected_value, \
                (
                    f"FAIL: Incorrect value for {field_id}. "
                    f"Expected '{expected_value}', "
                    f"found '{actual_value}'"
                )

            print("PASS:", field_id, "entered correctly")

        # Hide keyboard after entering payment details
        if driver.is_keyboard_shown():
            driver.hide_keyboard()

        # Keep billing address same as shipping address
        # Checkbox is already selected by default
        print("Billing address will remain same as shipping")

        print("\nPASS: Payment information entered successfully")
        passed += 1

    except Exception as e:
        print("FAIL: Test 15 -", repr(e))
        failed += 1

    # ==============================================
    # TEST 16: Navigate to Review Order
    # ==============================================

    try:
        print("\nRunning Test 16: Navigate to Review Order...")

        # Confirm Payment screen is displayed
        payment_page = wait.until(
            EC.visibility_of_element_located((
                AppiumBy.ACCESSIBILITY_ID,
                "checkout payment screen"
            ))
        )

        assert payment_page.is_displayed(), \
            "FAIL: Payment screen is not displayed"

        # Locate the Review Order button
        review_button = wait.until(
            EC.element_to_be_clickable((
                AppiumBy.ACCESSIBILITY_ID,
                "Review Order button"
            ))
        )

        # Click Review Order
        review_button.click()

        print("Review Order button clicked successfully")

        # Verify that we have left the Payment screen
        wait.until(
            EC.invisibility_of_element_located((
                AppiumBy.ACCESSIBILITY_ID,
                "checkout payment screen"
            ))
        )

        # Check the new screen's hierarchy for debugging
        print("Payment screen closed successfully")

        # TODO: Assert the Order Review screen using
        # its confirmed locator from Appium Inspector.

        print("CHECK: Inspect the Order Review screen")

    except Exception as e:
        print("FAIL: Test 16 -", repr(e))
        failed += 1

    # ==============================================
    # TEST 16: Review Order and Verify Details
    # ==============================================

    def scroll_review_page(direction="down"):

        # Locate the scrollable Review Order screen
        review_scroll = wait.until(
            EC.presence_of_element_located((
                AppiumBy.ACCESSIBILITY_ID,
                "checkout review order screen"
            ))
        )

        # Scroll the Review Order screen
        can_scroll_more = driver.execute_script(
            "mobile: scrollGesture",
            {
                "elementId": review_scroll.id,
                "direction": direction,
                "percent": 0.80
            }
        )

        print(
            f"Review page scrolled {direction}. "
            f"Can scroll further: {can_scroll_more}"
        )

        return can_scroll_more

    try:
        print("\nRunning Test 16: Review Order...")

        # Check whether the Review Order screen is already open
        review_page = driver.find_elements(
            AppiumBy.ACCESSIBILITY_ID,
            "checkout review order screen"
        )

        if not review_page:
            # If still on Payment, click Review Order
            review_button = wait.until(
                EC.element_to_be_clickable((
                    AppiumBy.ACCESSIBILITY_ID,
                    "Review Order button"
                ))
            )

            review_button.click()
            print("Review Order button clicked")

        # Confirm Review Order screen
        review_page = wait.until(
            EC.presence_of_element_located((
                AppiumBy.ACCESSIBILITY_ID,
                "checkout review order screen"
            ))
        )

        assert review_page is not None, \
            "FAIL: Review Order screen not found"

        print("PASS: Review Order screen displayed")

        # Verify the review sections exist
        review_sections = [
            "checkout delivery address",
            "checkout payment info",
            "checkout billing address",
            "checkout delivery details"
        ]

        for section_name in review_sections:
            print("Checking:", section_name)

            section = wait.until(
                EC.presence_of_element_located((
                    AppiumBy.ACCESSIBILITY_ID,
                    section_name
                ))
            )

            assert section is not None, \
                f"FAIL: {section_name} not found"

            print("PASS:", section_name)

        # Verify order quantity from the summary
        import re

        order_count_element = wait.until(
            EC.presence_of_element_located((
                AppiumBy.XPATH,
                '//*[contains(@text, "items")]'
            ))
        )

        order_count_text = order_count_element.text.strip()
        print("Order quantity text:", order_count_text)

        match = re.search(r"(\d+)\s+items?", order_count_text)

        assert match is not None, \
            "FAIL: Unable to extract order quantity"

        order_item_count = int(match.group(1))

        assert order_item_count == 4, \
            f"FAIL: Expected 4 items, found {order_item_count}"

        print("PASS: Order contains 4 items")

        # Verify Place Order button exists
        place_order_button = wait.until(
            EC.presence_of_element_located((
                AppiumBy.ACCESSIBILITY_ID,
                "Place Order button"
            ))
        )

        assert place_order_button is not None, \
            "FAIL: Place Order button not found"

        print("PASS: Place Order button found")

        print("\nPASS: Order review verification completed")
        passed += 1

    except Exception as e:
        print("FAIL: Test 16 -", repr(e))
        failed += 1

    # ==============================================
    # TEST 17: Place Order
    # ==============================================

    def scroll_review_page(direction="down"):

        # Locate the scrollable Review Order screen
        review_scroll = wait.until(
            EC.presence_of_element_located((
                AppiumBy.ACCESSIBILITY_ID,
                "checkout review order screen"
            ))
        )

        # Scroll the Review Order screen
        can_scroll_more = driver.execute_script(
            "mobile: scrollGesture",
            {
                "elementId": review_scroll.id,
                "direction": direction,
                "percent": 0.80
            }
        )

        print(
            f"Review page scrolled {direction}. "
            f"Can scroll further: {can_scroll_more}"
        )

        return can_scroll_more

    try:
        print("\nRunning Test 17: Place Order...")

        # Confirm Review Order screen is displayed
        review_page = wait.until(
            EC.presence_of_element_located((
                AppiumBy.ACCESSIBILITY_ID,
                "checkout review order screen"
            ))
        )

        assert review_page is not None, \
            "FAIL: Review Order screen is not displayed"

        # Scroll to the bottom of the review screen
        for attempt in range(10):

            can_scroll_more = scroll_review_page("down")

            if not can_scroll_more:
                print("Reached the bottom of Review Order")
                break

        # Locate Place Order button
        place_order_button = wait.until(
            EC.element_to_be_clickable((
                AppiumBy.ACCESSIBILITY_ID,
                "Place Order button"
            ))
        )

        assert place_order_button.is_displayed(), \
            "FAIL: Place Order button is not displayed"

        # Click Place Order
        place_order_button.click()

        print("Place Order button clicked successfully")

        # Verify the Checkout Complete screen
        checkout_complete = wait.until(
            EC.visibility_of_element_located((
                AppiumBy.ACCESSIBILITY_ID,
                "checkout complete screen"
            ))
        )

        assert checkout_complete.is_displayed(), \
            "FAIL: Checkout Complete screen not displayed"

        print("PASS: Order submitted and Checkout Complete displayed")
        passed += 1

    except Exception as e:
        print("FAIL: Test 17 -", repr(e))
        failed += 1

    # ==============================================
    # TEST 18: Verify Order Confirmation
    # ==============================================

    try:
        print("\nRunning Test 18: Verify Order Confirmation...")

        # Confirm Checkout Complete  screen is displayed
        checkout_complete_page = wait.until(
            EC.visibility_of_element_located((
                AppiumBy.ACCESSIBILITY_ID,
                "checkout complete screen"
            ))
        )

        assert checkout_complete_page.is_displayed(), \
            "FAIL: Checkout Complete screen is not displayed"

        print("PASS: Checkout Complete screen displayed")

        # Verify Checkout Complete heading
        checkout_heading = wait.until(
            EC.visibility_of_element_located((
                AppiumBy.XPATH,
                '//android.widget.TextView[@text="Checkout Complete"]'
            ))
        )

        assert checkout_heading.is_displayed(), \
            "FAIL: Checkout Complete heading not displayed"

        # Verify Thank You message
        thank_you_message = wait.until(
            EC.visibility_of_element_located((
                AppiumBy.XPATH,
                '//android.widget.TextView[@text="Thank you for your order"]'
            ))
        )

        assert thank_you_message.is_displayed(), \
            "FAIL: Thank you message not displayed"

        assert thank_you_message.text == "Thank you for your order", \
            "FAIL: Incorrect order confirmation message"

        print("PASS: Thank you for your order message verified")

        # Verify additional success message
        success_message = wait.until(
            EC.visibility_of_element_located((
                AppiumBy.XPATH,
                '//android.widget.TextView[@text="Your new swag is on its way"]'
            ))
        )

        assert success_message.is_displayed(), \
            "FAIL: Additional success message not displayed"

        print("PASS: Additional order confirmation verified")

        # Verify Continue Shopping button is available
        continue_shopping_button = wait.until(
            EC.visibility_of_element_located((
                AppiumBy.ACCESSIBILITY_ID,
                "Continue Shopping button"
            ))
        )

        assert continue_shopping_button.is_displayed(), \
            "FAIL: Continue Shopping button not displayed"

        print("PASS: Continue Shopping button available")

        print("\nPASS: Order placed and confirmed successfully!")
        passed += 1

    except Exception as e:
        print("FAIL: Test 18 -", repr(e))
        failed += 1

    # ==============================================
    # TEST SUMMARY
    # ==============================================

    print("\n========== TEST SUMMARY ==========")
    print("Total Tests:", passed + failed)
    print("Passed:", passed)
    print("Failed:", failed)
    print("==================================")


#Final except instance
except Exception as e:
    print("Test failed:", e)
    if driver is not None:
        input("Press Enter to close the session...")

finally:
    if driver is not None:
        driver.quit()
