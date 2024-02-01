package com.example.qung.Page;

import com.example.qung.Helper.ConnectDatabase;
import com.example.qung.Helper.validation;
import com.example.qung.element.baseUnitsElement;
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

import static org.testng.Assert.fail;

public class baseUnit {
    public WebDriver driver;
    public validation validation;
    com.example.qung.element.baseUnitsElement baseUnitsElement = new baseUnitsElement();

    public baseUnit(WebDriver driver) {
        this.driver = driver;
        validation = new validation(driver);
    }

    public void getURL() {
        String url = "http://103.138.113.158:9904/app/setting/base-units";
        driver.get(url);

    }

    public void URLConfig() {
        String currentUrl = driver.getCurrentUrl();
        String url = "http://103.138.113.158:9904/app/setting/base-units";
        Assert.assertEquals(currentUrl, url);
    }

    public void getTitle() throws InterruptedException {
        Thread.sleep(2000);
        WebElement element = driver.findElement(baseUnitsElement.header);
        String expected = element.getText();
        String actual = "Quản lý đơn vị cơ sở";
        Assert.assertEquals(actual, expected);
    }

    public static String sqlQuery() throws SQLException {
        ConnectDatabase database = new ConnectDatabase();
        Connection conm = database.getConnection();
        String query = "SELECT COUNT(*) FROM \"BaseUnits\" WHERE \"IsDeleted\" = false";
        PreparedStatement stm = conm.prepareStatement(query);
        ResultSet rs = stm.executeQuery();
        String count = "";
        while (rs.next()) {
            count = rs.getString(1);
        }
        return count;
    }


    public static List<String> sqlQuery2() throws SQLException {
        ConnectDatabase database = new ConnectDatabase();
        Connection conm = database.getConnection();
        String query = "select \"Name\" from \"BaseUnits\" where \"IsDeleted\" = false";
        PreparedStatement statement = conm.prepareStatement(query);
        ResultSet rs = statement.executeQuery();
        List<String> list = new ArrayList<>();
        while (rs.next()) {
            String NameBaseUnit = rs.getString(1);
            list.add(NameBaseUnit);
        }
        return list;
    }

    public void getCountBaseUnit() throws SQLException {
        WebElement element = driver.findElement(baseUnitsElement.countBaseUnit);
        String text = element.getText();
        String[] parts = text.split("\\s+");
        String numberString = parts[0];
        try {
            String number = String.valueOf(Integer.parseInt(numberString));
            System.out.println(number);
            Assert.assertEquals(sqlQuery(), number);
        } catch (NumberFormatException e) {
            System.out.println("Không thể chuyển đổi thành số.");
        }
    }

    public void createdBaseUnit() throws InterruptedException, SQLException {
        WebElement element = driver.findElement(baseUnitsElement.btnCreated);
        if (element.isDisplayed()) {
            validation.Click(baseUnitsElement.btnCreated);
            Thread.sleep(2000);
            getTextfield();
            Thread.sleep(2000);
            validation.setText(baseUnitsElement.Id, validation.randomId());
            validation.setText(baseUnitsElement.Name, validation.RamdomName());
            validation.setText(baseUnitsElement.Email, validation.RamdomEmail());
            validation.setText(baseUnitsElement.Phone, validation.ramdomPhone());
            validation.setText(baseUnitsElement.Address, "Hà Nội");
            validation.Click(baseUnitsElement.BtnCreated);
        } else {
            fail("Test Faild");
        }
    }

    public void searchBaseUint() throws SQLException {
        List<String> arrayList = sqlQuery2();
        List<String> list = new ArrayList<>();
        for (String value : arrayList) {
            list.add(value);
        }
        Random random = new Random();
        int ramdomIndex = random.nextInt(list.size());
        String getName = list.get(ramdomIndex);
        try {
            validation.setText(baseUnitsElement.filterText, getName);
            validation.Click(baseUnitsElement.searhBtn);
            Thread.sleep(2000);
            String data = "";
            WebElement table = driver.findElement(By.id("pr_id_5-table")); /// tìm table
            List<WebElement> rows = table.findElements(By.tagName("tr"));//tìm oootj
            for (WebElement row : rows) {
                List<WebElement> cells = row.findElements(By.xpath(".//td[3]"));// lấy cột hàng thứ 3
                for (WebElement cell : cells) {
                    data += cell.getText() + "\t";
                    System.out.println(data);
                }
                data += "\n";
            }
        } catch (NoSuchElementException e) {

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void clearfilter() throws SQLException, InterruptedException {
        Thread.sleep(2000);
        validation.Click(baseUnitsElement.Clearfilter); /// click vào xóa data tìm kiếm
        Thread.sleep(2000);
        try {/// check sau khi xóa data so bản ghi đã trar về mặc định chưa
            WebElement element = driver.findElement(baseUnitsElement.countBaseUnit);
            String text = element.getText();
            String[] parts = text.split("\\s+"); /// split tách text từ 1 chuỗi thành 1 mảng: VD Nguyễn Trọng Quang => "Nguyễn", "Trọng', "Quang"
            String numberString = parts[0]; /// Lấy index trong mảng
            Assert.assertEquals(numberString, sqlQuery()); /// so sánh
        } catch (NoSuchElementException e) {
        }
    }

    public void deleteBaseUnit() throws SQLException, InterruptedException {
        searchBaseUint();
        validation.Click(baseUnitsElement.deletebtn);
        validation.Click(baseUnitsElement.cancel);
        Thread.sleep(2000);
        try {
            WebElement webElement = driver.findElement(baseUnitsElement.cancel);
            if (webElement.isDisplayed()) {
                fail("Popup không được đóng sau khi nhấn cancel");
            }
        } catch (NoSuchElementException e) {
            searchBaseUint();
            validation.Click(baseUnitsElement.deletebtn);
            validation.Click(baseUnitsElement.config);
            Thread.sleep(1000);
            WebElement element = driver.findElement(By.xpath("//div[@id='swal2-html-container']"));
            String mess = "Xóa thành công";
            String response = element.getText();
            if (response.equals(mess)) {
                System.out.println("Xóa thành công");
            } else {
                System.out.println("Dữ liệu đã phát sinh");
            }
        }
    }

    public void getTextfield() {
        String[] textField = {"Mã", "Tên đơn vị cơ sở", "Email", "Số điện thoại", "Địa chỉ"};
        WebElement layout = driver.findElement(By.xpath("//body/app-root[1]/ng-component[1]/div[1]/default-layout[1]/div[1]/div[1]/div[2]/div[2]/ng-component[1]/div[1]/div[2]/div[1]/div[1]/createoreditbaseunitmodal[1]/div[1]/div[1]/div[1]/form[1]/div[2]"));
        List<WebElement> laybel = layout.findElements(By.tagName("label"));
        for (int i = 0; i < textField.length; i++) {
            WebElement getIndex = laybel.get(i);
            String getText = getIndex.getText();
            Assert.assertEquals(textField[i], getText);
        }
    }
}
