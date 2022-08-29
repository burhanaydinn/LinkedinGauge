package com.linkedin.driver;

import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class BaseTest {
    public static WebDriver driver;
    public static WebDriverWait webWait;

    public static JavascriptExecutor getJSExecutor;

    public static final String WEB_URL = "https://www.linkedin.com";
    @BeforeScenario
    public void setup() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-notifications");
        //chromeOptions.setExperimentalOption("excludeSwitches", Arrays.asList("--disable-popup-blocking"));
        chromeOptions.addArguments("--disable-popup-blocking");
        chromeOptions.addArguments("--disable-translate");
        chromeOptions.addArguments("--disable-gpu");
        System.setProperty("webdriver.chrome.driver", "webdriver/chromedriver");
        driver = new ChromeDriver(chromeOptions);
        getJSExecutor = (JavascriptExecutor) driver;
        webWait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        webWait.withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofMillis(300)).ignoring(NoSuchElementException.class);
        driver.manage().window().maximize();
        driver.get(WEB_URL);
    }

    @AfterScenario()
    public void tearDow() {
        driver.quit();
    }
}
