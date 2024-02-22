package com.example.qung.Helper;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public class BaseSetup {
    public static WebDriver driver = null;

    @BeforeClass
    public void SetUpDriver() {
        String os = System.getProperty("os.name");
        switch (os) {
            case "Windows 10":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                System.out.println("Setup chromedriver success");
                break;
            case "Mac Os":
                WebDriverManager.safaridriver().setup();
                driver = new SafariDriver();
                driver.manage().window().maximize();
                System.out.println("Setup SafariDriver success");
                break;
            case "Linux":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
                System.out.println("Setup FirefoxDriver success");
                break;
            default:
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                driver.manage().window().maximize();
                System.out.println("Setup Edgedriver success");
        }
        if (driver != null) {
            System.out.println("BrowserVersion " + getBrowserVersion(driver));
            System.out.println("BrowserName " + getBrowserName(driver));
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public String getBrowserVersion(WebDriver driver) {
        Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
        String version = capabilities.getBrowserVersion();
        return version;
    }

    public String getBrowserName(WebDriver driver) {
        Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
        String name = capabilities.getBrowserName();
        return name;
    }
@AfterMethod
    public void afterMethod(ITestResult testResult) {
        takeScreenshotService screenshotServic = new takeScreenshotService();
        screenshotServic.takeScreenshotOnFailue(testResult);

    }
}

