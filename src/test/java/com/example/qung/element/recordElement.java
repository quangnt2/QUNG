package com.example.qung.element;

import org.openqa.selenium.By;

public class recordElement {
    public By createdBtn = By.xpath("//button[contains(text(),'Tạo mới')]");
    public By queryControl = By.name("queryControl");
    public By ChooserBaseUnit = By.xpath("//span[contains(text(),'Chọn đơn vị')]");
    public By getdataBaseUnit = By.xpath("//body/app-root[1]/ng-component[1]/div[1]/default-layout[1]/div[1]/div[1]/div[2]/div[2]/app-create-record[1]/div[1]/div[2]/div[1]/form[1]/p-accordion[1]/div[1]/p-accordiontab[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/p-dropdown[1]/div[1]/div[3]/div[2]");
    public By filterBaseUnit = By.xpath("//body/app-root[1]/ng-component[1]/div[1]/default-layout[1]/div[1]/div[1]/div[2]/div[2]/app-create-record[1]/div[1]/div[2]/div[1]/form[1]/p-accordion[1]/div[1]/p-accordiontab[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/p-dropdown[1]/div[1]/div[3]/div[1]/div[1]/input[1]");
    public By ContactPerson = By.name("ContactPerson");
    public By Htnp = By.xpath("//body/app-root[1]/ng-component[1]/div[1]/default-layout[1]/div[1]/div[1]/div[2]/div[2]/app-create-record[1]/div[1]/div[2]/div[1]/form[1]/p-accordion[1]/div[1]/p-accordiontab[1]/div[1]/div[2]/div[1]/div[1]/div[3]/div[1]/p-dropdown[1]/div[1]");
    public By PhoneNumber = By.name("PhoneNumber");
    public By Email = By.name("Email");
    public By OfficialLetterNumber = By.name("OfficialLetterNumber");
    public By save = By.xpath("//button[contains(text(),'Lưu')]");
    public By listButonInStatusDraft = By.xpath("//body/app-root[1]/ng-component[1]/div[1]/default-layout[1]/div[1]/div[1]/div[2]/div[2]/app-edit-record[1]/div[1]/div[1]/div[1]");
    public By GuiHoSo = By.xpath("//button[contains(text(),'Gửi')]");
    public By Xoa = By.xpath("//button[contains(text(),'Xóa')]");
    public By PopupXacNhanXoa = By.xpath("//body/div[1]/div[1]");
    public By XacNhanXoa = By.xpath("//button[contains(text(),'Có')]");
    public By HuyXacNhan = By.xpath("//body/div[1]/div[1]/div[6]/button[3]");
    public By Sua = By.xpath("button[contains(text(),'Sửa')]");
    public By QuayLai = By.xpath("//body/app-root[1]/ng-component[1]/div[1]/default-layout[1]/div[1]/div[1]/div[2]/div[2]/app-edit-record[1]/div[1]/div[1]/div[1]/button[1]");

    public By Address = By.name("Address");
    public By addVBCC = By.xpath("//body/app-root[1]/ng-component[1]/div[1]/default-layout[1]/div[1]/div[1]/div[2]/div[2]/app-create-record[1]/div[1]/div[2]/div[1]/form[1]/p-accordion[1]/div[1]/p-accordiontab[2]/div[1]/div[2]/div[1]/div[1]/div[2]/button[1]");
    public By dropdowLoaiPhoi_0 = By.id("pr_id_14_label");
    public By dropdowLoaiPhoi_1 = By.id("pr_id_16_label");
    public By getListboxLoaiphoi = By.xpath("//tbody/tr[1]/td[2]/span[2]/p-dropdown[1]/div[1]/div[3]/div[2]");
    public By getListboxLoaiphoi1 = By.xpath("//tbody/tr[2]/td[2]/span[2]/p-dropdown[1]/div[1]/div[3]/div[2]");
    public By soluongdenghi = By.xpath("//tbody/tr[1]/td[4]/span[2]/p-inputnumber[1]/span[1]/input[1]");
    public By soluongdenghi1 = By.xpath("//tbody/tr[2]/td[4]/span[2]/p-inputnumber[1]/span[1]/input[1]");
    public By findLoaiPhoi = By.xpath("//tbody/tr[1]/td[2]/span[2]/p-dropdown[1]/div[1]/div[3]/div[1]/div[1]/input[1]");
}
