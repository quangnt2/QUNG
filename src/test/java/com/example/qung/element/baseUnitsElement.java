package com.example.qung.element;

import org.openqa.selenium.By;

public class baseUnitsElement {
    public By btnCreated = By.xpath("//button[contains(text(),'Tạo mới')]");
    public By Id = By.name("Code");
    public By Name = By.name("Name");
    public By Email = By.name("Email");
    public By Phone = By.name("Phone");
    public By Address = By.name("Address");
    public By Clearfilter = By.xpath("//body/app-root[1]/ng-component[1]/div[1]/default-layout[1]/div[1]/div[1]/div[2]/div[2]/ng-component[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[1]/div[2]/button[1]");
    public By BtnCreated = By.xpath("//body/app-root[1]/ng-component[1]/div[1]/default-layout[1]/div[1]/div[1]/div[2]/div[2]/ng-component[1]/div[1]/div[2]/div[1]/div[1]/createoreditbaseunitmodal[1]/div[1]/div[1]/div[1]/form[1]/div[3]/button[2]");
    public By filterText = By.name("filterText");
    public By deletebtn = By.xpath("//tbody/tr[1]/td[7]/button[2]");

    public By cancel = By.xpath("//body/div[1]/div[1]/div[6]/button[3]");
    public By config = By.xpath("//button[contains(text(),'Có')]");
    public By updatebtn = By.xpath("//tbody/tr[1]/td[7]/button[1]");
    public By searhBtn = By.xpath("//body/app-root[1]/ng-component[1]/div[1]/default-layout[1]/div[1]/div[1]/div[2]/div[2]/ng-component[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[1]/button[1]");
    public By countBaseUnit = By.xpath("/html[1]/body[1]/app-root[1]/ng-component[1]/div[1]/default-layout[1]/div[1]/div[1]/div[2]/div[2]/ng-component[1]/div[1]/div[1]/div[1]/div[2]");
    public By header = By.xpath("//body/app-root[1]/ng-component[1]/div[1]/default-layout[1]/div[1]/div[1]/div[2]/div[2]/ng-component[1]/div[1]/div[1]/div[1]/div[1]");
}
