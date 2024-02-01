package com.example.qung.TestCase;

import com.example.qung.Page.record;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;

public class RecordTest extends LoginClass {
    private record record;

    @BeforeClass
    public void setUp() {

        record = new record(getDriver());
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    public void getURL() throws InterruptedException, SQLException {
        VanthuLogin();
        Thread.sleep(2000);
        record.getUrl();
        Thread.sleep(2000);
        record.XoaHoSo();
//        record.creadRecord();
//        record.checkButonInDetailRecord();
//        record.GuiHoSo();
//        record.getUrl();
//        Thread.sleep();
//        record.XoaHoSo();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    public void GetButtonRecordInStatusDard() throws InterruptedException {
        record.checkButonInDetailRecord();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    public void GuiHoSo() throws InterruptedException {
        record.GuiHoSo();
    }
}
