package com.example.qung.TestCase;

import com.example.qung.Helper.BaseSetup;
import com.example.qung.Page.login;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest extends BaseSetup {
    private com.example.qung.Page.login login;

    @BeforeClass
    public void setUp() {
        login = new login(getDriver());
        login.navigateToLoginURL();
    }
    @Test
    @Severity(SeverityLevel.TRIVIAL)
    public void loginExcel() throws InterruptedException {
        login.loginExcel();
    }
    @Test
    @Severity(SeverityLevel.TRIVIAL)
    public void login() throws InterruptedException {
        login.login("admin", "123");
    }
    @Test
    @Severity(SeverityLevel.TRIVIAL)
    public void loginAccountInActive() throws InterruptedException {
        login.loginAcountInActive("LC", "123");
    }
    @Test
    @Severity(SeverityLevel.TRIVIAL)
    public void nullUser() throws InterruptedException {
        login.checkValidation("", "121");
    }
    @Test
    @Severity(SeverityLevel.TRIVIAL)
    public void nullPass() throws InterruptedException {
        login.checkValidation("vanthu", "");
    }
    @Test
    @Severity(SeverityLevel.TRIVIAL)
    public void nullUserAndPass() throws InterruptedException {
        login.checkValidation("", "");
    }
    @Test
    @Severity(SeverityLevel.TRIVIAL)
    public void checkStatus() throws InterruptedException {
        login.checkStatusBtn();
    }
}
