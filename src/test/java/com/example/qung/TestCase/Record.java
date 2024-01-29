package com.example.qung.TestCase;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Record extends LoginClass {
    private com.example.qung.Page.Record User;
    @BeforeClass
    public void setUp() {
        User = new com.example.qung.Page.Record(getDriver());
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
