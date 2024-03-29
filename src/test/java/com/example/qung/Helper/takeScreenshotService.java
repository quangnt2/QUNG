package com.example.qung.Helper;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class takeScreenshotService {
    public void takeScreenshotOnFailue(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE_yyyy_MM_dd_HH_mm",new Locale("vi","VN"));
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("EEEE_yyyy_MM_dd");
            String date = dateFormat.format(new Date());
            String date2 = dateFormat2.format(new Date());
            WebDriver driver = BaseSetup.getDriver();
            if (driver instanceof TakesScreenshot) {
                TakesScreenshot ts = (TakesScreenshot) driver;
                File source = ts.getScreenshotAs(OutputType.FILE);
                String fileName = result.getName() + "_" + date +".png";
                String filePath = "./screenshots/" + date2 + "/" + fileName;
                try {
                    FileUtils.forceMkdir(new File("./screenshots/" + date2));
                    FileUtils.copyFile(source, new File(filePath));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    Allure.addAttachment(fileName, new FileInputStream(filePath));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                // Nếu driver không hỗ trợ chụp ảnh, in ra thông báo
                System.out.println("Driver does not support screenshots");
            }
        }
    }
}
