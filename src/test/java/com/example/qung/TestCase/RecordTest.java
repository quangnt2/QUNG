package com.example.qung.TestCase;

import com.example.qung.Page.record;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;
@Owner("QuangNT2")
public class RecordTest extends LoginClass {
    private record record;

    @BeforeClass
    public void setUp() {

        record = new record(getDriver());
    }

    @Test(priority = 0)
    @Severity(SeverityLevel.MINOR)
    public void getURL() throws InterruptedException {
        VanthuLogin();
        Thread.sleep(2000);
        record.getUrl();
    }
    @Test(priority = 2)
    @Severity(SeverityLevel.MINOR)
    public void TimKiemHoSoTrangThaiNhap() throws InterruptedException, SQLException {
        record.TimKiemHoSoTrangThaiNhap();
    }
    @Test(priority = 3)
    @Severity(SeverityLevel.CRITICAL)
    public void TaoHoSo() throws InterruptedException, SQLException {
        record.creadRecord();
    }
    @Test(priority = 4)
    @Severity(SeverityLevel.CRITICAL)
    public void GuiHoSo() throws InterruptedException, SQLException {
        record.GuiHoSo();
    }
    @Test(priority = 4)
    @Severity(SeverityLevel.CRITICAL)
    public void ChiTietHoSoSauTaoMoi() throws InterruptedException, SQLException {
        record.checkButtonInDetailRecord();
    }
    @Test(priority = 5)
    @Severity(SeverityLevel.MINOR)
    public void XoaHoSo() throws InterruptedException, SQLException {
        record.XoaHoSo();
    }

}
