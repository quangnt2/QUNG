package com.example.qung.Page;

import com.example.qung.Element.BaseUnitsElement;
import com.example.qung.Helper.Validate;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

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

    }

    public void getTitle() throws InterruptedException {
        Thread.sleep(2000);
        WebElement element = driver.findElement(baseUnitsElement.header);
        String expected = element.getText();
        String  actual= "Quản lý đơn vị cơ sở";
        Assert.assertEquals(actual,expected);
    }
}
