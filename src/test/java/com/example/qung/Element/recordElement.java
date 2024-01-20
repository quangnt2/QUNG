package com.example.qung.Element;

import org.openqa.selenium.By;

public class recordElement {
    public By createdBtn = By.xpath("//button[contains(text(),'Tạo mới')]");
    public By ChooserBaseUnit = By.xpath("//span[contains(text(),'Chọn đơn vị')]");
    public By filterBaseUnit = By.xpath("//body/app-root[1]/ng-component[1]/div[1]/default-layout[1]/div[1]/div[1]/div[2]/div[2]/app-create-record[1]/div[1]/div[2]/div[1]/form[1]/p-accordion[1]/div[1]/p-accordiontab[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/p-dropdown[1]/div[1]/div[3]/div[1]/div[1]/input[1]");
    public By ContactPerson = By.name("ContactPerson");
    public By Htnp = By.xpath("//body/app-root[1]/ng-component[1]/div[1]/default-layout[1]/div[1]/div[1]/div[2]/div[2]/app-create-record[1]/div[1]/div[2]/div[1]/form[1]/p-accordion[1]/div[1]/p-accordiontab[1]/div[1]/div[2]/div[1]/div[1]/div[3]/div[1]/p-dropdown[1]/div[1]");
    public By PhoneNumber = By.name("PhoneNumber");
    public By Email = By.name("Email");
    public By Address = By.name("Address");
    public By addVBCC = By.xpath("//body/app-root[1]/ng-component[1]/div[1]/default-layout[1]/div[1]/div[1]/div[2]/div[2]/app-create-record[1]/div[1]/div[2]/div[1]/form[1]/p-accordion[1]/div[1]/p-accordiontab[2]/div[1]/div[2]/div[1]/div[1]/div[2]/button[1]");
    public By dropdowLoaiPhoi = By.id("pr_id_14_label");
    public By getListboxLoaiphoi = By.xpath("//tbody/tr[1]/td[2]/span[2]/p-dropdown[1]/div[1]/div[3]/div[2]");

    public By findLoaiPhoi = By.xpath("//tbody/tr[1]/td[2]/span[2]/p-dropdown[1]/div[1]/div[3]/div[1]/div[1]/input[1]");
}
