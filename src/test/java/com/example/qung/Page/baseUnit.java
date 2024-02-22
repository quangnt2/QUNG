package com.example.qung.Page;

import com.example.qung.Helper.ConnectDatabase;
import com.example.qung.Helper.validation;
import com.example.qung.element.baseUnitsElement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.testng.Assert.fail;

public class baseUnit {
    public WebDriver driver;
    public validation validation;
    public WebDriverWait wait;
    com.example.qung.element.baseUnitsElement baseUnitsElement = new baseUnitsElement();

    public baseUnit(WebDriver driver) {
        this.driver = driver;
        validation = new validation(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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

    public void getTitle() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(baseUnitsElement.header));
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

    public static List<String> sqlQuery3() throws SQLException {
        ConnectDatabase database = new ConnectDatabase();
        Connection connection = database.getConnection();
        String query = "select \"Name\" from \"BaseUnits\" where \"IsDeleted\" = false and \"CreationTime\" <= CURRENT_DATE";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet rs = statement.executeQuery();
        List<String> list = new ArrayList<>();
        while (rs.next()) {
            String NameBaseUnit = rs.getString(1);
            System.out.println("Quang" + NameBaseUnit);
            list.add(NameBaseUnit);
        }
        return list;
    }

    public void SearchBaseUnitCreatedCurren_date() throws SQLException {
        List<String> list = sqlQuery3();
        if (!list.isEmpty()) {
            Random random = new Random();
            int index = random.nextInt(list.size());
            String getName = list.get(index);
            validation.setText(baseUnitsElement.filterText, getName);
            validation.Click(baseUnitsElement.searhBtn);        }
    }

    public void getCountBaseUnit() throws SQLException {
        WebElement element = driver.findElement(baseUnitsElement.countBaseUnit);
        String text = element.getText();
        String[] parts = text.split("\\s+");
        String numberString = parts[0];
        try {
            String number = String.valueOf(Integer.parseInt(numberString));
            Assert.assertEquals(sqlQuery(), number);
        } catch (NumberFormatException e) {
            System.out.println("Không thể chuyển đổi thành số.");
        }
    }

    public void createdBaseUnit() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(baseUnitsElement.btnCreated));
        if (element.isDisplayed()) {
            validation.Click(baseUnitsElement.btnCreated);
            getTextfield();
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
            WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pr_id_5-table"))); /// tìm table
            List<WebElement> rows = table.findElements(By.tagName("tr"));//tìm cột
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
        validation.Click(baseUnitsElement.Clearfilter); /// click vào xóa data tìm kiếm
        Thread.sleep(2000);
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(baseUnitsElement.countBaseUnit));
            String text = element.getText();
            String[] parts = text.split("\\s+"); /// split tách text từ 1 chuỗi thành 1 mảng: VD Nguyễn Trọng Quang => "Nguyễn", "Trọng', "Quang"
            String numberString = parts[0]; /// Lấy index trong mảng
            System.out.println(numberString);
            Assert.assertEquals(numberString, sqlQuery()); /// so sánh
        } catch (NoSuchElementException e) {
        }
    }

    public void deleteBaseUnit() throws SQLException, InterruptedException {
        SearchBaseUnitCreatedCurren_date();
        validation.Click(baseUnitsElement.deletebtn);
        validation.Click(baseUnitsElement.cancel);
        try {
            Thread.sleep(2000);
            WebElement webElement = driver.findElement(baseUnitsElement.cancel);
            if (webElement.isDisplayed()) {
                fail("Popup không được đóng sau khi nhấn cancel");
            }
        } catch (NoSuchElementException e) {
            SearchBaseUnitCreatedCurren_date();
            validation.Click(baseUnitsElement.deletebtn);
            validation.Click(baseUnitsElement.config);
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='swal2-html-container']")));
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
        WebElement layout = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/app-root[1]/ng-component[1]/div[1]/default-layout[1]/div[1]/div[1]/div[2]/div[2]/ng-component[1]/div[1]/div[2]/div[1]/div[1]/createoreditbaseunitmodal[1]/div[1]/div[1]/div[1]/form[1]/div[2]")));
        List<WebElement> label = layout.findElements(By.tagName("label"));
        for (int i = 0; i < textField.length; i++) {
            WebElement getIndex = label.get(i);
            String getText = getIndex.getText();
            Assert.assertEquals(textField[i], getText);
        }
    }
}
