package com.example.qung.Page;

import com.example.qung.Element.recordElement;
import com.example.qung.Helper.Validate;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Record {
    private static WebDriver driver;
    private static Validate validate;
    private static recordElement recordElement = new recordElement();

    public Record(WebDriver driver) {
        this.driver = driver;
        validate = new Validate(driver);
    }

    public void getUrl() {
        driver.get("http://103.138.113.158:9904/app/record/record-list");
    }

    public void s() throws InterruptedException {

        WebElement div = driver.findElement(By.xpath("//body/app-root[1]/ng-component[1]/div[1]/default-layout[1]/div[1]/div[1]/div[2]/div[2]/app-create-record[1]/div[1]/div[2]/div[1]/form[1]/p-accordion[1]/div[1]/p-accordiontab[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/p-dropdown[1]/div[1]/div[3]/div[2]"));
    }


    public void creadRecord() throws InterruptedException {
        Thread.sleep(2000);
        validate.Click(recordElement.createdBtn);
        validate.Click(recordElement.ChooserBaseUnit);
        getDataDropdow(recordElement.getdataBaseUnit);
        String getRandom = randomdata(recordElement.getdataBaseUnit);
        WebElement div = driver.findElement(recordElement.getdataBaseUnit);
        WebElement dropdownOption = div.findElement(By.xpath(".//span[contains(text(), '" + getRandom + "')]"));
        dropdownOption.click();
        try {
            validate.setText(recordElement.ContactPerson, Validate.RamdomName());
            validate.setText(recordElement.PhoneNumber, Validate.ramdomPhone());
            validate.setText(recordElement.Email, Validate.RamdomEmail());
            Thread.sleep(2000);
            WebElement element = driver.findElement(recordElement.Address);
            String getText = element.getAttribute("value");
            if (getText == null || getText.isEmpty()) {
                System.out.println("Không có địa chỉ, và sẽ tự động lấy tên đơn vị");
                validate.setText(recordElement.Address, getRandom);
            }
            for (int i = 0; i < 2; i++) {
                validate.Click(recordElement.addVBCC);
            }
            /// thêm phôi
            validate.Click(recordElement.dropdowLoaiPhoi_0);
            getDataDropdow(recordElement.getListboxLoaiphoi);
            WebElement loaiphoi = driver.findElement(recordElement.getListboxLoaiphoi);
            String ran = randomdata(recordElement.getListboxLoaiphoi);
            WebElement ChonPhoi = loaiphoi.findElement(By.xpath(".//span[contains(text(), '" + ran + "')]"));
            ChonPhoi.click();
            validate.setText(recordElement.soluongdenghi, Validate.randomInt());

            validate.Click(recordElement.dropdowLoaiPhoi_1);
            getDataDropdow(recordElement.getListboxLoaiphoi1);
            WebElement loaiphoi1 = driver.findElement(recordElement.getListboxLoaiphoi1);
            String ran1 = randomdata(recordElement.getListboxLoaiphoi1);
            WebElement ChonPhoi1 = loaiphoi1.findElement(By.xpath(".//span[contains(text(), '" + ran1 + "')]"));
            ChonPhoi1.click();
            validate.setText(recordElement.soluongdenghi1, Validate.randomInt());
            validate.setText(recordElement.OfficialLetterNumber, Validate.randomOfficialLetterNumber());

        } catch (NoSuchElementException e) {

        }
        validate.Click(recordElement.save);
        //// check ho so sau luu: thanh cong - that bai
        Thread.sleep(1000);
        WebElement mess = driver.findElement(By.xpath("//div[@id='swal2-html-container']"));
        String getText = mess.getText();
        if (getText.equals("Lưu thành công")) {
            System.out.println("Thành công");
        }
        if (getText.equals("Nhập những thông tin bắt buộc")) {
            System.out.println("Thiếu thông tin bắt buộc");
        } else{
            Assert.fail("lỗi");
        }
    }

    public static List<String> getDataDropdow(By element) {
        WebElement listdata = driver.findElement(element);
        List<String> list = new ArrayList<>();
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
