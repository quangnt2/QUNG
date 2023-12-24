package com.example.qung.Page;


import org.openqa.selenium.WebDriver;

public class Login {
    private WebDriver driver;


    public Login(WebDriver driver) {
        this.driver = driver;

    }
    public void navigateToLoginURL() {
        driver.get("http://103.138.113.158:4110/auth/login");
    }

    public void navigateToLoginURL1() {
        driver.get("http://103.138.113.158:9904");
    }


}

