package com.example.qung.TestCase;

import com.example.qung.Helper.BaseSetup;
import com.example.qung.Page.Login;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest extends BaseSetup {
    private Login login;

    @BeforeClass
    public void setUp() {
        login = new Login(getDriver());
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
    public void LoginAccountInActive() throws InterruptedException {
        login.loginAcountInActive("LC", "123");
    }
    @Test
    @Severity(SeverityLevel.TRIVIAL)
    public void NullUser() throws InterruptedException {
        login.checkValidation("", "121");
    }
    @Test
    @Severity(SeverityLevel.TRIVIAL)
    public void NullPass() throws InterruptedException {
        login.checkValidation("vanthu", "");
    }
    @Test
    @Severity(SeverityLevel.TRIVIAL)
    public void NullUserAndPass() throws InterruptedException {
        login.checkValidation("", "");
    }
    @Test
    @Severity(SeverityLevel.TRIVIAL)
    public void checkStatus() throws InterruptedException {
        login.checkStatusBtn();
    }
}
