package com.az.Utilities;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class CreateColumnList {
	public HashMap<String, Object> baseMap;
	public static List<String> colNames = new ArrayList<>();
	XSSFSheet sheet;

	public CreateColumnList(XSSFSheet sheetLocal) {
		this.sheet = sheetLocal;
		loadColumNamesinlist();
	}

	public void loadColumNamesinlist() {		
		XSSFRow row = sheet.getRow(0);
		int col_num = -1;
		for (int i = 0; i < row.getLastCellNum(); i++) {
			colNames.add(row.getCell(i).getStringCellValue().trim());
		}
	}
}
