package com.example.qung.TestCase;

import com.example.qung.Page.User;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Record extends LoginClass {
    private User User;
    @BeforeClass
    public void setUp() {
        User = new User(getDriver());
    }
    @Test(priority = 0)
    @Severity(SeverityLevel.CRITICAL)
    public void getURL() throws InterruptedException {
        VanthuLogin();
        Thread.sleep(2000);
        User.getUrl();
        User.creadRecord();
    }

}
