package com.example.qung.TestCase;

import com.example.qung.Helper.BaseSetup;
import com.example.qung.Page.Login;
import org.testng.annotations.Test;

public class LoginTest extends BaseSetup {
    private Login login;

    @Test
    public void loginExcel() throws InterruptedException {
        login = new Login(getDriver());
        login.navigateToLoginURL();
        login.loginExcel();
    }
}
