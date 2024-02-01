package com.example.qung.TestCase;

import com.example.qung.Helper.BaseSetup;
import com.example.qung.Page.login;

public class LoginClass extends BaseSetup {

    private com.example.qung.Page.login login;
    public void login() throws InterruptedException {
        login = new login(getDriver());
        login.navigateToLoginURL();
        login.loginAdmin("admin", "123");
    }
    public void VanthuLogin() throws InterruptedException {
        login = new login(getDriver());
        login.navigateToLoginURL();
        login.loginAdmin("vanthu", "123");
    }
}
