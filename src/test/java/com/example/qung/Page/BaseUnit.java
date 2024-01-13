package com.example.qung.Page;

import com.example.qung.Element.BaseUnitsElement;
import com.example.qung.Helper.ConnectDatabase;
import com.example.qung.Helper.Validate;
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

public class BaseUnit {
    public WebDriver driver;
    public Validate validate;
    BaseUnitsElement baseUnitsElement = new BaseUnitsElement();

    public BaseUnit(WebDriver driver) {
        this.driver = driver;
        validate = new Validate(driver);
    }

    public void getURL() {
        String url = "http://103.138.113.158:9904/app/setting/base-units";
        driver.get(url);

    }

    public void URLConfig() {
        String currentUrl = driver.getCurrentUrl();
        String url = "http://103.138.113.158:9904/app/setting/base-units";
        if (currentUrl.equals(url)) {
            System.out.println(url);
        } else {
            fail("url không hợp lệ " + currentUrl);
        }
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


    public static ArrayList<String> sqlQuery2() throws SQLException {
        ConnectDatabase database = new ConnectDatabase();
        Connection conm = database.getConnection();
        String query = "select \"Name\" from \"BaseUnits\" where \"IsDeleted\" = false";
        PreparedStatement statement = conm.prepareStatement(query);
        ResultSet rs = statement.executeQuery();
        ArrayList<String> list = new ArrayList<>();
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
            validate.Click(baseUnitsElement.btnCreated);
            Thread.sleep(5000);
            validate.setText(baseUnitsElement.Id, Validate.randomId());
            validate.setText(baseUnitsElement.Name, Validate.RamdomName());
            validate.setText(baseUnitsElement.Email, Validate.RamdomEmail());
            validate.setText(baseUnitsElement.Phone, Validate.ramdomPhone());
            validate.setText(baseUnitsElement.Address, "Hà Nội");
            validate.Click(baseUnitsElement.BtnCreated);
        } else {
            fail("Test Faild");
        }
    }

    public void searchBaseUint() throws SQLException {
        ArrayList<String> arrayList = sqlQuery2();
        List<String> list = new ArrayList<>();
        for (String value : arrayList) {
            list.add(value);
        }
        Random random = new Random();
        int ramdomIndex = random.nextInt(list.size());
        String getName = list.get(ramdomIndex);
        try {
            validate.setText(baseUnitsElement.filterText, getName);
            validate.Click(baseUnitsElement.searhBtn);
            Thread.sleep(3000);
            String data = "";
            WebElement table = driver.findElement(By.id("pr_id_5-table"));
            List<WebElement> rows = table.findElements(By.tagName("tr"));
            for (WebElement row : rows) {
                List<WebElement> cells = row.findElements(By.xpath(".//td[3]"));
                for (WebElement cell : cells) {
                    data += cell.getText() + "\t";
                }
                data += "\n";

            }
            System.out.println(data);


        } catch (NoSuchElementException e) {

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void clearfilter() throws SQLException, InterruptedException {
        validate.Click(baseUnitsElement.Clearfilter);
        Thread.sleep(3000);
        try {
            WebElement element = driver.findElement(baseUnitsElement.countBaseUnit);
            String text = element.getText();
            String[] parts = text.split("\\s+"); /// split tách text từ 1 chuỗi thành 1 mảng: VD Nguyễn Trọng Quang => "Nguyễn", "Trọng', "Quang"
            String numberString = parts[0]; /// Lấy giá trị trong mảng
            Assert.assertEquals(numberString, sqlQuery());
        } catch (NoSuchElementException e) {

        }
    }

    public void deleteBaseUnitPass() {
        /// chọn 1 đv trong danh sách; 1 nhấn xóa, 2 xác nhận xóa
        /// check data sau khi nhấn hủy, xác nhận
        /// check trạng thái xóa trong db

    }

    public void deleteBaseUnitFail() throws SQLException {
        searchBaseUint();
        validate.Click(baseUnitsElement.deletebtn);
    }
}
