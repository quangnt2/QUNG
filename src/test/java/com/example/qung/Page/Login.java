package com.example.qung.Page;


import com.example.qung.Element.LoginElement;
import com.example.qung.Helper.ExcelReaderService;
import com.example.qung.Helper.Validate;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.testng.Assert.fail;

public class Login {
    private WebDriver driver;
    private Validate validate;
    LoginElement element = new LoginElement();

    public Login(WebDriver driver) {
        this.driver = driver;
        validate = new Validate(driver);

    }


    public void navigateToLoginURL() {
        driver.get("http://103.138.113.158:9904");
    }

    public void login(String User, String Pass) throws InterruptedException {
        Thread.sleep(3000);
        validate.setText(element.user, User);
        validate.setText(element.pass, Pass);
        validate.Click(element.submit);
        Thread.sleep(3000);
        String url = driver.getCurrentUrl();
        if (url.equals("http://103.138.113.158:9904/app/record/record-list")) {
            Logout(driver);
        }else {
            fail("lỗi nha");
        }
    }
    public void loginAdmin(String User, String Pass) throws InterruptedException {
        validate.setText(element.user, User);
        validate.setText(element.pass, Pass);
        validate.Click(element.submit);
    }
    public static void main(String[] args) {
        ExcelReaderService excelReaderService = new ExcelReaderService();
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
        ExcelReaderService excelReaderService = new ExcelReaderService();
        String excelFile = "C:\\Users\\admin\\OneDrive\\Máy tính\\Book1.xlsx";
        int usernameColumnIndex = 0;
        int passwordColumnIndex = 1;
        List<List<String>> excelData = excelReaderService.readExcel(excelFile);
        for (List<String> row : excelData) {
            if (usernameColumnIndex >= 0 && usernameColumnIndex <= excelData.size() &&
                    passwordColumnIndex >= 0 && passwordColumnIndex <= excelData.size()) {
                String user = row.get(usernameColumnIndex);
                String pass = row.get(passwordColumnIndex);
                validate.setText(element.user, user);
                validate.setText(element.pass, pass);
                validate.Click(element.submit);
                Thread.sleep(3000);
                String URL = driver.getCurrentUrl();
                if (URL.equals("http://103.138.113.158:9904/app/record/record-list")) {
                    System.out.println(user + "/" + pass + " hợp lệ");
                    Logout(driver);
                    driver.get("http://103.138.113.158:9904/");
                    Thread.sleep(3000);
                }
                if (URL.equals("http://103.138.113.158:9904/account/login")) {
                    validate.Click(element.config);
                }

            }
        }
    }

    public void Logout(WebDriver driver) {
        validate.Click(element.btnProfile);
        validate.Click(element.btnLogout);
    }

    public void loginAcountInActive(String user, String pass) throws InterruptedException {
        Thread.sleep(4000);
        validate.setText(element.user, user);
        validate.setText(element.pass, pass);
        validate.Click(element.submit);
        Thread.sleep(2000);
        try {
            WebElement elm = driver.findElement(element.swal2);
            if (elm.isDisplayed()) {
                String getText = elm.getText();
                System.out.println(getText);
                validate.Click(element.config);
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
            Thread.sleep(3000);
            validate.setText(element.user, user);
            validate.setText(element.pass, pass);
            validate.Click(element.submit);
            WebElement statusButton = driver.findElement(element.submit);
            if (statusButton.isEnabled()) {

            }
        } catch (NoSuchElementException e) {

        }

    }
    public void checkStatusBtn() throws InterruptedException {
        navigateToLoginURL();
        Thread.sleep(3000);
        WebElement webElement = driver.findElement(element.submit);
        String aaa = webElement.getAttribute("disabled");
        System.out.println(aaa);
        if(webElement.isEnabled()){
            System.out.println("1");
        } else {
            System.out.println("2");
        }
    }
}

