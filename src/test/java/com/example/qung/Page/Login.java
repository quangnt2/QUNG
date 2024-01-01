package com.example.qung.Page;


import com.example.qung.Element.LoginElement;
import com.example.qung.Helper.ExcelReaderService;
import com.example.qung.Helper.Validate;
import org.openqa.selenium.WebDriver;

import java.util.List;

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

    public void login(String User, String Pass) {
        validate.setText(element.user, User);
        validate.setText(element.pass, Pass);
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
                if (URL.equals("http://103.138.113.158:9904/app/record/record-list")){
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
}

