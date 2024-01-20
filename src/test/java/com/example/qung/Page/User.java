package com.example.qung.Page;

import com.example.qung.Element.recordElement;
import com.example.qung.Helper.Validate;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class User {
    private WebDriver driver;
    private Validate validate;
    recordElement recordElement = new recordElement();

    public User(WebDriver driver) {
        this.driver = driver;
        validate = new Validate(driver);
    }

    public void getUrl() {
        driver.get("http://103.138.113.158:9904/app/record/record-list");
    }

    public void creadRecord() throws InterruptedException {
        Thread.sleep(2000);
        validate.Click(recordElement.createdBtn);
        validate.Click(recordElement.ChooserBaseUnit);
        WebElement div = driver.findElement(By.xpath("//body/app-root[1]/ng-component[1]/div[1]/default-layout[1]/div[1]/div[1]/div[2]/div[2]/app-create-record[1]/div[1]/div[2]/div[1]/form[1]/p-accordion[1]/div[1]/p-accordiontab[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/p-dropdown[1]/div[1]/div[3]/div[2]"));
        List<WebElement> span = div.findElements(By.tagName("span"));
        List<String> baseUnit = new ArrayList<>();
        for (WebElement element : span) {
            String getText = element.getText();
            baseUnit.add(getText);
        }
        Random random = new Random();
        int index = random.nextInt(baseUnit.size());
        String name = baseUnit.get(index);
        try {
            validate.setText(recordElement.filterBaseUnit, name);
            Thread.sleep(2000);
            WebElement dropdownOption = div.findElement(By.xpath(".//span[contains(text(), '" + name + "')]"));
            dropdownOption.click();
            validate.setText(recordElement.ContactPerson, Validate.RamdomName());
            validate.setText(recordElement.PhoneNumber, Validate.ramdomPhone());
            validate.setText(recordElement.Email, Validate.RamdomEmail());
            WebElement element = driver.findElement(recordElement.Address);
            String currentAddress = element.getText();
            if (currentAddress == null || currentAddress.isEmpty()) {
                validate.setText(recordElement.Address, name);
            }
            /// thêm loại phôi cần thêm
            validate.Click(recordElement.addVBCC);
            validate.Click(recordElement.dropdowLoaiPhoi);
            WebElement div1 = driver.findElement(recordElement.getListboxLoaiphoi);
            List<WebElement> span1 = div1.findElements(By.tagName("span"));
            List<String> list = new ArrayList<>();
            for (WebElement webElement : span1) {
                String name1 = webElement.getText();
                list.add(name1);
            }
            Random random1 = new Random();
            int i = random1.nextInt(list.size());
            String namerandom = list.get(i);
            validate.setText(recordElement.findLoaiPhoi, namerandom);
            Thread.sleep(2000);
            WebElement dropdownOption1 = div1.findElement(By.xpath(".//span[contains(text(), '" + namerandom + "')]"));
            System.out.println(dropdownOption1.getText());
        } catch (NoSuchElementException e) {

        }

    }
}
