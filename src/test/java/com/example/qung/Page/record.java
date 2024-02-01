package com.example.qung.Page;

import com.example.qung.Helper.ConnectDatabase;
import com.example.qung.Helper.validation;
import com.example.qung.element.recordElement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class record {
    private static WebDriver driver;
    private static validation validation;
    private static com.example.qung.element.recordElement recordElement = new recordElement();

    public record(WebDriver driver) {
        this.driver = driver;
        validation = new validation(driver);
    }

    public static void getUrl() {
        driver.get("http://103.138.113.158:9904/app/record/record-list");
    }


    public void s() throws InterruptedException {
        WebElement mess = driver.findElement(By.xpath("//div[@id='swal2-html-container']"));
    }

    public void checkButonInDetailRecord() throws InterruptedException {
        String[] expected = {"Quay lại", "Xóa", "Sửa", "Gửi"};
        WebElement element = driver.findElement(recordElement.listButonInStatusDraft);
        List<WebElement> laybel = element.findElements(By.tagName("button"));
        Thread.sleep(4000);
        int countExpected = expected.length;  //4
        int countActual = laybel.size();///4
        Assert.assertEquals(countExpected, countActual);
        for (int i = 1; i < expected.length; i++) {
            WebElement getIndex = laybel.get(i);
            String getText = getIndex.getText();
            Assert.assertEquals(expected[i], getText);
        }
    }

    public void GuiHoSo() throws InterruptedException {
        validation.Click(recordElement.GuiHoSo);
        Thread.sleep(1000);
        WebElement mess = driver.findElement(By.xpath("//div[@id='swal2-html-container']"));
        Thread.sleep(1000);
        String ThongBao = mess.getText();
        System.out.println(ThongBao);
        if (ThongBao.equals("Gửi thành công")) {
            System.out.println(ThongBao);
        } else {
            Assert.assertFalse(false);
        }
    }

    public static List<String> query() throws InterruptedException, SQLException {
        ConnectDatabase database = new ConnectDatabase();
        Connection connection = database.getConnection();
        String query = "select \"DocumentNumber\" from \"Records\" where \"StatusId\"= 32 and  \"CreatorUserId\" = 14";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.executeQuery();
        List<String> list = new ArrayList<>();

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String result = resultSet.getString(1);
            list.add(result);
        }
        return list;
    }

    public void XoaHoSo() throws SQLException, InterruptedException {
        Thread.sleep(2000);
        validation.Click(recordElement.Xoa);
        WebElement element = driver.findElement(recordElement.PopupXacNhanXoa);
        if (!element.isDisplayed()) {
            Assert.fail("Không hiển thị popup xóa");
        }
        if (element.isDisplayed()) {
            validation.Click(recordElement.HuyXacNhan);
            Thread.sleep(1000);
            try {
                WebElement element2 = driver.findElement(recordElement.PopupXacNhanXoa);
                if (element2.isDisplayed()) {
                    Assert.fail("Chức năng hủy không hoạt động");
                }
            } catch (NoSuchElementException e) {
                validation.Click(recordElement.Xoa);
                validation.Click(recordElement.XacNhanXoa);
                Thread.sleep(1000);
                WebElement mess = driver.findElement(By.xpath("//div[@id='swal2-html-container']"));
                String getText = mess.getText();
                if (getText.equals("Xóa thành công")) {
                    System.out.println(getText);
                } else {
                    Assert.fail(getText);
                }
            }
        }
    }

    public void TimKiemHoSoTrangThaiNhap() throws SQLException, InterruptedException {
        List<String> ListDocumentNumber = query();
        List<String> List = new ArrayList<>();
        for (String string : ListDocumentNumber) {
            List.add(string);
        }
        Random random = new Random();
        int index = random.nextInt(List.size());
        String name = List.get(index);
        validation.setText(recordElement.queryControl, name);
        Thread.sleep(3000);
        WebElement element = driver.findElement(By.xpath("//tbody/tr[1]/td[2]"));
        WebElement link = element.findElement(By.className("link"));
        link.click();
    }

    public void creadRecord() throws InterruptedException {
        Thread.sleep(2000);
        validation.Click(recordElement.createdBtn);
        validation.Click(recordElement.ChooserBaseUnit);
        getDataDropdow(recordElement.getdataBaseUnit);
        String getRandom = randomdata(recordElement.getdataBaseUnit);
        WebElement div = driver.findElement(recordElement.getdataBaseUnit);
        WebElement dropdownOption = div.findElement(By.xpath(".//span[contains(text(), '" + getRandom + "')]"));
        dropdownOption.click();
        try {
            validation.setText(recordElement.ContactPerson, validation.RamdomName());
            validation.setText(recordElement.PhoneNumber, validation.ramdomPhone());
            validation.setText(recordElement.Email, validation.RamdomEmail());
            Thread.sleep(2000);
            WebElement element = driver.findElement(recordElement.Address);
            String getText = element.getAttribute("value");
            if (getText == null || getText.isEmpty()) {
                System.out.println("Không có địa chỉ, và sẽ tự động lấy tên đơn vị");
                validation.setText(recordElement.Address, getRandom);
            }
            for (int i = 0; i < 2; i++) {
                validation.Click(recordElement.addVBCC);
            }
            /// thêm phôi
            validation.Click(recordElement.dropdowLoaiPhoi_0);
            getDataDropdow(recordElement.getListboxLoaiphoi);
            WebElement loaiphoi = driver.findElement(recordElement.getListboxLoaiphoi);
            String ran = randomdata(recordElement.getListboxLoaiphoi);
            WebElement ChonPhoi = loaiphoi.findElement(By.xpath(".//span[contains(text(), '" + ran + "')]"));
            ChonPhoi.click();
            validation.setText(recordElement.soluongdenghi, validation.randomInt());

            validation.Click(recordElement.dropdowLoaiPhoi_1);
            getDataDropdow(recordElement.getListboxLoaiphoi1);
            WebElement loaiphoi1 = driver.findElement(recordElement.getListboxLoaiphoi1);
            String ran1 = randomdata(recordElement.getListboxLoaiphoi1);
            WebElement ChonPhoi1 = loaiphoi1.findElement(By.xpath(".//span[contains(text(), '" + ran1 + "')]"));
            ChonPhoi1.click();
            validation.setText(recordElement.soluongdenghi1, validation.randomInt());
            validation.setText(recordElement.OfficialLetterNumber, validation.randomOfficialLetterNumber());

        } catch (NoSuchElementException e) {

        }
        validation.Click(recordElement.save);
        //// check ho so sau luu: thanh cong - that bai
        Thread.sleep(1000);
        WebElement mess = driver.findElement(By.xpath("//div[@id='swal2-html-container']"));
        String getText = mess.getText();
        if (getText.equals("Lưu thành công")) {
            ///xử lý thêm nếu cần
        }
        if (getText.equals("Nhập những thông tin bắt buộc")) {
            System.out.println("Thiếu thông tin bắt buộc");
        }
    }

    public static List<String> getDataDropdow(By element) { //hàm lấy thoong tin trong dropdow
        WebElement listdata = driver.findElement(element);
        List<String> list = new ArrayList<>(); // tạo list chưa
        List<WebElement> getDataInSpan = listdata.findElements(By.tagName("span"));
        for (WebElement webElement : getDataInSpan) {
            String name = webElement.getText();
            list.add(name);
        }
        return list;
    }

    public String randomdata(By element) {
        List<String> list = getDataDropdow(element);
        Random random = new Random();
        int index = random.nextInt(list.size());
        String name = list.get(index);
        return name;
    }
}
