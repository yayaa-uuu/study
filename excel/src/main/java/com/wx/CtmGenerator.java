package com.wx;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbookFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CtmGenerator {

    void create(String str) throws URISyntaxException, IOException {
        XSSFWorkbookFactory factory = new XSSFWorkbookFactory();
        XSSFWorkbook workbook = factory.create(Files.newInputStream(Path.of(ClassLoader.getSystemClassLoader().getResource(str).toURI())));
        XSSFSheet sheet = workbook.getSheetAt(0);
        int firstRowNum = sheet.getFirstRowNum();
        int lastRowNum = sheet.getLastRowNum();
        System.out.println(firstRowNum);
        System.out.println(lastRowNum);
    }


}
