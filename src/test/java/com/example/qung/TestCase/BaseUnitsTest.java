package com.example.qung.TestCase;

import com.example.qung.Page.BaseUnit;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BaseUnitsTest extends LoginClass {
    private BaseUnit baseUnit;

    @BeforeClass
    public void Before() {
        baseUnit = new BaseUnit(getDriver());
    }

    public void getURL() throws InterruptedException {
        login();
        Thread.sleep(4000);
        baseUnit.getURL();
    }

    public void getTitle() throws InterruptedException {
        baseUnit.getTitle();

    }
}
