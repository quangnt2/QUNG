package com.example.qung.TestCase;

import com.example.qung.Helper.BaseSetup;
import com.example.qung.Page.Login;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class LoginTest extends BaseSetup {
    private Login login;

    @BeforeClass
    public void setUp() {
        login = new Login(getDriver());
        login.navigateToLoginURL();
    }



    public void loginExcel() throws InterruptedException {
        login.loginExcel();
    }

    public void login() throws InterruptedException {
        login.login("admin", "123");
    }

    public void LoginAccountInActive() throws InterruptedException {
        login.loginAcountInActive("LC", "123");
    }

    public void NullUser() throws InterruptedException {
        login.checkValidation("", "121");
    }

    public void NullPass() throws InterruptedException {
        login.checkValidation("vanthu", "");
    }

    public void NullUserAndPass() throws InterruptedException {
        login.checkValidation("", "");
    }

    public void checkStatus() throws InterruptedException {
        login.checkStatusBtn();
    }
}
