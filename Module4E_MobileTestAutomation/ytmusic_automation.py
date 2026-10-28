
from appium import webdriver
from appium.webdriver.common.appiumby import AppiumBy
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from appium.options.android import UiAutomator2Options



def main():
    desired_caps = {
        "platformName": "Android",
        "deviceName": "sdk_gphone64_x86_64",
        "appPackage": "com.google.android.apps.youtube.music",
        "appActivity": "com.google.android.apps.youtube.music.activities.MainActivity",
        "automationName": "UiAutomator2"
    }
    options = UiAutomator2Options().load_capabilities(desired_caps)

    driver = webdriver.Remote(
    command_executor="http://127.0.0.1:4725/wd/hub",
    options=options
)



if __name__ == "__main__":
    main()
