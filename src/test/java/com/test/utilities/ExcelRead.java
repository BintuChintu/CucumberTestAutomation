/*
 * *Author : RaviKumar Mogulluru
 */
package com.test.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelRead {

	Workbook workBook = null;
	Sheet sheet = null;
	ConfigFileReader configFileReader = new ConfigFileReader();
	DataFormatter formatter = new DataFormatter();
	private static final Logger log = LogManager.getLogger(ExcelRead.class);
	public String getCellValue(String columnName) throws IOException {
		int columnNumber = 0;
		FileInputStream fileInputStream = new FileInputStream(
				new File(System.getProperty("user.dir") + configFileReader.readProperty("testdatapath") ));
		workBook = new HSSFWorkbook(fileInputStream);
		sheet = workBook.getSheet(AmazonTestConstants.sheetName);
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
