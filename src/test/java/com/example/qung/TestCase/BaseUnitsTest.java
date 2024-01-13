package com.example.qung.TestCase;

import com.example.qung.Page.BaseUnit;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;

public class BaseUnitsTest extends LoginClass {
    private BaseUnit baseUnit;

    @BeforeClass
    public void Before() {
        baseUnit = new BaseUnit(getDriver());
    }

    @Test(priority = 0)
    @Severity(SeverityLevel.CRITICAL)
    public void getURL() throws InterruptedException {
        login();
        Thread.sleep(4000);
        baseUnit.getURL();
    }

    @Test(priority = 1)
    @Severity(SeverityLevel.CRITICAL)
    public void checkURL() {
        baseUnit.URLConfig();
    }

    @Test(priority = 2)
    @Severity(SeverityLevel.NORMAL)
    public void search() throws SQLException {
        baseUnit.searchBaseUint();
    }

    @Test(priority = 3)
    @Severity(SeverityLevel.MINOR)
    public void clearDataFilter() throws SQLException, InterruptedException {
        baseUnit.clearfilter();
    }

    @Test(priority = 4)
    @Severity(SeverityLevel.TRIVIAL)
    public void getTitle() throws InterruptedException, SQLException {
        baseUnit.getTitle();
        baseUnit.getCountBaseUnit();
    }
    @Test(priority = 5)
    @Severity(SeverityLevel.CRITICAL)
    public void CreatedBaseUnit() throws InterruptedException, SQLException {
        baseUnit.createdBaseUnit();
    }
    @Test(priority = 5)
    @Severity(SeverityLevel.CRITICAL)
    public void DeleteBaseUnit() throws InterruptedException, SQLException {
        baseUnit.deleteBaseUnitFail();
    }
}
