package com.example.qung.TestCase;

import com.example.qung.Helper.BaseSetup;
import com.example.qung.Helper.takeScreenshotService;
import com.example.qung.Page.Login;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class LoginClass extends BaseSetup {

    private Login login;
    @Test
    public void login() throws InterruptedException {
        login = new Login(getDriver());
        login.navigateToLoginURL();
        Thread.sleep(4000);
        login.login("admin", "1d23");
    }
    @AfterMethod
    public void afterMethod(ITestResult result) {
        takeScreenshotService screenshotService = new takeScreenshotService();
        screenshotService.takeScreenshotOnFailue(result);
    }
}
