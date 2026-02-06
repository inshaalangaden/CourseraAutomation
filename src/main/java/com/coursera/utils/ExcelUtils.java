package com.coursera.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtils {

    public static Object[][] getTestData(String filePath, String sheetName) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet(sheetName);

        int rowCount = sheet.getLastRowNum();
        int colCount = 12;

        Object[][] data = new Object[rowCount][colCount];

        DataFormatter formatter = new DataFormatter();

        for (int i = 0; i < rowCount; i++) {
            Row row = sheet.getRow(i + 1);
            for (int j = 0; j < colCount; j++) {
                if(row != null && row.getCell(j) != null) {
                    data[i][j] = formatter.formatCellValue(row.getCell(j));
                }else{
                    data[i][j] = null;                }
            }
        }
        return data;
    }

    public static void setCellData(String filePath, String sheetName, int rowNum, String status) throws IOException {
        XSSFWorkbook workbook = null;
        try{
            FileInputStream fis = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fis);
        }catch(Exception e){
            e.printStackTrace();
        }
        XSSFSheet sheet = workbook.getSheet(sheetName);

        Row headerRow = sheet.getRow(0);

        int resultCol = -1;
        for(int i=0; i<headerRow.getLastCellNum();i++){
            if(headerRow.getCell(i).getStringCellValue().equalsIgnoreCase("Test Result")){
                resultCol = i;
                break;
            }
        }
        if(resultCol == -1){
            resultCol = headerRow.getLastCellNum();
            headerRow.createCell(resultCol).setCellValue("Test Result");
        }

        Row row = sheet.getRow(rowNum);
        if (row == null) row = sheet.createRow(rowNum);

        // We write to Column 12 (the 13th column)
        Cell cell = row.createCell(resultCol);
        cell.setCellValue(status);

        // Explicitly write the changes back to the file
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            workbook.write(fos);
        }
    }
}
