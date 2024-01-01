package com.example.qung.Element;

import org.openqa.selenium.By;

public class LoginElement {
    public By user = By.name("userNameOrEmailAddress");
    public By pass = By.name("password");
    public By submit = By.xpath("//body/app-root[1]/ng-component[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/ng-component[1]/div[1]/form[1]/div[4]");
    public By btnProfile = By.id("kt_quick_user_toggle");
    public By btnLogout = By.xpath("//body/app-root[1]/ng-component[1]/div[1]/default-layout[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[3]/div[1]/div[2]/div[1]/user-menu[1]/div[1]/div[2]/div[7]");
    public By popup = By.xpath("//body/div[1]/div[1]");
    public By config = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[6]/button[1]");
}
