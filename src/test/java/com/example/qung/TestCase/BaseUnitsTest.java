package com.example.qung.TestCase;

import com.example.qung.Page.baseUnit;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;

public class BaseUnitsTest extends LoginClass {
    private com.example.qung.Page.baseUnit baseUnit;

    @BeforeClass
    public void Before() {
        baseUnit = new baseUnit(getDriver());
    }

    @Test(priority = 0)
    @Severity(SeverityLevel.CRITICAL)
    public void getURL() throws InterruptedException {
        login();
        Thread.sleep(2000);
        baseUnit.getURL();
    }

    @Test(priority = 1)
    @Severity(SeverityLevel.CRITICAL)
    public void checkURL() {
        baseUnit.URLConfig();
    }

    @Test(priority = 2)
    @Severity(SeverityLevel.NORMAL)
    public void search() throws SQLException, InterruptedException {
        login();
        Thread.sleep(3000);
        baseUnit.getURL();
        baseUnit.searchBaseUint();
    }
    @Test(priority = 3)
    @Severity(SeverityLevel.NORMAL)
    public void search2() throws SQLException, InterruptedException {
        login();
        Thread.sleep(3000);
        baseUnit.getURL();
        baseUnit.SearchBaseUnitCreatedCurren_date();
    }
    @Test(priority = 4)
    @Severity(SeverityLevel.MINOR)
    public void clearDataFilter() throws SQLException, InterruptedException {
        baseUnit.clearfilter();
    }

    @Test(priority = 5)
    @Severity(SeverityLevel.TRIVIAL)
    public void getTitle() throws InterruptedException, SQLException {
        baseUnit.getTitle();
        baseUnit.getCountBaseUnit();
    }

    @Test(priority = 6)
    @Severity(SeverityLevel.CRITICAL)
    public void CreatedBaseUnit() throws InterruptedException, SQLException {
        baseUnit.createdBaseUnit();
    }

    @Test(priority = 7)
    @Severity(SeverityLevel.CRITICAL)
    public void DeleteBaseUnit() throws InterruptedException, SQLException {
        baseUnit.deleteBaseUnit();
    }
}
