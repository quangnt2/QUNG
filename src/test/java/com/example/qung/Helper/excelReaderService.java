package com.example.qung.Helper;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class excelReaderService {
    public static List<List<String>> readExcel(String excelFile) {
        DecimalFormat  decimalFormat = new DecimalFormat(); /// loại bỏ số thập phân
        List<List<String>> data = new ArrayList<>(); ///tạo 1 list chứa data sau khi xử lý
        try {
            File file = new File(excelFile);
            Workbook workbook = WorkbookFactory.create(file); /// khởi tạo file excecl 
            Sheet sheet = workbook.getSheetAt(0);/// lấy seet đầu tiên của file excel
            int rowCount = sheet.getLastRowNum(); /// lấy số hàng
            for (int i = 0; i <= rowCount; i++) { /// tạo vòng lơp duyệt các cột các hàng 
                Row row = sheet.getRow(i); 
                int cellCount = row.getLastCellNum();
                List<String> rowdata = new ArrayList<>();
                for (int j = 0; j < cellCount; j++) {
                    Cell cell = row.getCell(j);
                    String cellValue = "";
                    if (cell.getCellType() == CellType.STRING) {
                        cellValue = cell.toString();
                    }
                    if (cell.getCellType() == CellType.NUMERIC) {
                        cellValue = decimalFormat.format(cell.getNumericCellValue());
                    }
                    if (cell.getCellType() == CellType.BOOLEAN) {
                        cellValue = String.valueOf(cell.getBooleanCellValue());
                    }
                    rowdata.add(cellValue);
                }
                data.add(rowdata);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return data;
    }
}
