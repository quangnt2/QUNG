package com.example.qung.Helper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;

public class Validate {
    public WebDriver driver;
    public WebDriverWait wait;

    public Validate(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofMinutes(5));
    }

    public void setText(By element, String text) {
        Scroll(element);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        driver.findElement(element).clear();
        driver.findElement(element).sendKeys(text);
    }

    public void Scroll(By element) {
        WebElement element1 = driver.findElement(element);
        if (element != null) {
            Actions actions = new Actions(driver);
            actions.moveToElement(element1);
            int Offset = element1.getSize().getHeight() / -2;
            actions.moveByOffset(0, Offset);
            actions.perform();
        } else {
            System.out.println("Không tim thấy phần tử");
        }
    }

    public void Click(By element) {
        Scroll(element);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        driver.findElement(element).click();
    }

    public static String RamdomName() {
        Random random = new Random();
        String[] FistName = {"Nguyễn Văn", "Trần Thị", "Lê Minh", "Phạm Hồng", "Hoàng Quang", "Vũ Thị", "Đặng Văn", "Mai Thị", "Lý Văn", "Trịnh Thị"};
        String[] LastName = {"Nguyên", "Trấn", "Lê", "Minh", "Phạm", "Hồng", "Hoàng", "Quang", "Vũ", "Đặng", "Văn", "Mai", "Lý", "Trịnh"};
        String name1 = FistName[random.nextInt(FistName.length)];
        String name2 = LastName[random.nextInt(LastName.length)];
        return name1 + name2;
    }

    public static String RamdomEmail() {
        String email = UUID.randomUUID().toString();
        email = email.substring(0, Math.min(email.length(), 4)) + "@gmail.com";
        return email;
    }

    public static String ramdomPhone() {
        Random random = new Random();
        String[] networkCodes = {"032", "033", "034", "035", "036", "037", "038", "039", "070", "079", "077", "076", "078",
                "083", "084", "085", "081", "082",
                "086", "088", "089"};
        String ramnetwork = networkCodes[random.nextInt(networkCodes.length)];
        String phoneNumber = String.format("%05d", random.nextInt(1000));
        return ramnetwork + phoneNumber;
    }

    public static String randomAge() {
        int maxage = 99;
        int minage = 1;
        Random random = new Random();
        String randomage = String.valueOf(random.nextInt(maxage - minage + 1) + minage);
        return randomage;
    }
}

