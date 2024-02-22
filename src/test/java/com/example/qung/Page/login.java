package com.example.qung.Page;


import com.example.qung.element.loginElement;
import com.example.qung.Helper.excelReaderService;
import com.example.qung.Helper.validation;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.testng.Assert.fail;

public class login {
    private WebDriver driver;
    private validation validation;
    public WebDriverWait wait;
    loginElement element = new loginElement();

    public login(WebDriver driver) {
        this.driver = driver;
        validation = new validation(driver);

    }


    public void navigateToLoginURL() {
        driver.get("http://103.138.113.158:9904");
        validation.waitForPageToLoad(driver, 10, "VN-NARIC");

    }

    public void login(String User, String Pass) throws InterruptedException {
        Thread.sleep(2000);
        validation.setText(element.user, User);
        validation.setText(element.pass, Pass);
        validation.Click(element.submit);
        Thread.sleep(2000);
        String url = driver.getCurrentUrl();
        if (url.equals("http://103.138.113.158:9904/app/record/record-list")) {
            Logout(driver);
        } else {
            fail("Đăng xuất thất bại sau khi đã đâng xuất");
        }
    }

    public void loginAdmin(String User, String Pass) throws InterruptedException {
        validation.setText(element.user, User);
        validation.setText(element.pass, Pass);
        validation.Click(element.submit);
    }

    public static void main(String[] args) {
        excelReaderService excelReaderService = new excelReaderService();
        int usernameColumnIndex = 0;
        int passwordColumnIndex = 1;
        String excelFile = "C:\\Users\\admin\\OneDrive\\Máy tính\\Book1.xlsx";
        List<List<String>> excelData = excelReaderService.readExcel(excelFile);
        for (List<String> row : excelData) {
            String user = row.get(usernameColumnIndex);
            String pass = row.get(passwordColumnIndex);
            System.out.println(user + "" + pass);
        }
    }

    public void loginExcel() throws InterruptedException {
        excelReaderService excelReaderService = new excelReaderService();
        String excelFile = "C:\\Users\\admin\\OneDrive\\Máy tính\\Book1.xlsx";
        int usernameColumnIndex = 0;
        int passwordColumnIndex = 1;
        List<List<String>> excelData = excelReaderService.readExcel(excelFile);
        for (List<String> row : excelData) {
            if (usernameColumnIndex >= 0 && usernameColumnIndex <= excelData.size() &&
                    passwordColumnIndex >= 0 && passwordColumnIndex <= excelData.size()) {
                String user = row.get(usernameColumnIndex);
                String pass = row.get(passwordColumnIndex);
                validation.setText(element.user, user);
                validation.setText(element.pass, pass);
                validation.Click(element.submit);
                Thread.sleep(3000);
                String URL = driver.getCurrentUrl();
                if (URL.equals("http://103.138.113.158:9904/app/record/record-list")) {
                    System.out.println(user + "/" + pass + " hợp lệ");
                    Logout(driver);
                    driver.get("http://103.138.113.158:9904/");
                    Thread.sleep(2000);
                }
                if (URL.equals("http://103.138.113.158:9904/account/login")) {
                    validation.Click(element.config);
                }

            }
        }
    }

    public void Logout(WebDriver driver) {
        validation.Click(element.btnProfile);
        validation.Click(element.btnLogout);
    }

    public void loginAcountInActive(String user, String pass) throws InterruptedException {
        validation.setText(element.user, user);
        validation.setText(element.pass, pass);
        validation.Click(element.submit);
        try {
            WebElement elm = wait.until(ExpectedConditions.visibilityOfElementLocated(element.swal2));
            if (elm.isDisplayed()) {
                String getText = elm.getText();
                System.out.println(getText);
                validation.Click(element.config);
            } else {
                fail("Không hiển thị thông báo lỗi");
            }
        } catch (NoSuchElementException e) {
            Thread.sleep(2000);
            String url = driver.getCurrentUrl();
            if (url.equals("http://103.138.113.158:9904/app/record/record-list")) {
                fail("Tài khoản đã khóa vẫn có thể đăng nhập");
            }
        }
    }

    public void checkValidation(String user, String pass) throws InterruptedException {
        try {
            validation.setText(element.user, user);
            validation.setText(element.pass, pass);
            validation.Click(element.submit);
            WebElement statusButton = driver.findElement(element.submit);
            if (statusButton.isEnabled()) {

            }
        } catch (NoSuchElementException e) {

        }

    }

    public void checkStatusBtn() throws InterruptedException {
        navigateToLoginURL();
        WebElement webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(element.submit)) ;
        String aaa = webElement.getAttribute("disabled");
        System.out.println(aaa);
        if (webElement.isEnabled()) {
            System.out.println("1");
        } else {
            System.out.println("2");
        }
    }
}

