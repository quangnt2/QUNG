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
    public void login() throws InterruptedException {
        login = new Login(getDriver());
        Thread.sleep(4000);
        login.navigateToLoginURL();
        Thread.sleep(4000);
        login.loginAdmin("admin", "123");
    }
}
