package com.test.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelRead {

	Workbook workBook = null;
	Sheet sheet = null;
	DataFormatter formatter = new DataFormatter();
	public String getCellValue(int rowNum, String columnName) throws IOException {
		int columnNumber = 0;
		FileInputStream fileInputStream = new FileInputStream(
				new File(System.getProperty("user.dir") + new ConfigFileReader().readProperty("testdatapath") ));
		workBook = new HSSFWorkbook(fileInputStream);
		sheet = workBook.getSheet("studentlist");
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
		for (int i = 0; i < sheet.getRow(0).getLastCellNum(); i++) {
			if (columnName.equalsIgnoreCase(sheet.getRow(0).getCell(i).getStringCellValue())) {
				columnNumber = i;
				break;
			}
		}

		return formatter.formatCellValue(sheet.getRow(rowCount).getCell(columnNumber));
	}

}
