package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ZoneCodeFileController {

	public List<List<String>> getZoneCdList(String filePath) {

		FileInputStream fins = null;
		XSSFWorkbook workbook = null;

		List<List<String>> zoneCodeList = new ArrayList<List<String>>();

		try {

			fins = new FileInputStream(filePath);
			workbook = new XSSFWorkbook(fins);

			XSSFSheet sheet;

			int rowNum;
			int cellNum;

			List<String> rowList = null;

			XSSFRow rowXSS;
			XSSFCell cell;
			String cellVal;

			for (int sheetIdx = 0; sheetIdx < workbook.getNumberOfSheets(); sheetIdx++) {
				sheet = workbook.getSheetAt(sheetIdx);
				rowNum = sheet.getPhysicalNumberOfRows();

				for (int rowIdx = 1; rowIdx < rowNum; rowIdx++) {

					rowList = new ArrayList<>();
					rowXSS = sheet.getRow(rowIdx);

					cellNum = (rowXSS == null) ? 0 : rowXSS.getPhysicalNumberOfCells();

					for (int colIdx = 0; colIdx < cellNum; colIdx++) {

						cell = rowXSS.getCell(colIdx);
						if (cell.getCellType() == XSSFCell.CELL_TYPE_BLANK)
							break;

						cellVal = (cell.getCellType() == XSSFCell.CELL_TYPE_STRING)
								? cell.getStringCellValue().toString()
								: (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC)
										? Integer.toString((int) cell.getNumericCellValue())
										: null;

						rowList.add(cellVal);
					}
					if (rowList.size() != 0)
						zoneCodeList.add(rowList);

				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			try {
				if (fins != null) {
					fins.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return zoneCodeList;
	}

	public Map<String, List<List<String>>> getSpecialZoneCdList(String filePath) {

		FileInputStream fins = null;
		XSSFWorkbook workbook = null;

		Map<String, List<List<String>>> sheetZoneCodeList = new HashMap<>();

		try {

			fins = new FileInputStream(filePath);
			workbook = new XSSFWorkbook(fins);

			XSSFSheet sheet;
			String sheetName;

			int rowNum;
			int cellNum;

			List<List<String>> zoneCodeList;
			List<String> rowList = null;

			XSSFRow rowXSS;
			XSSFCell cell;
			String cellVal;

			for (int sheetIdx = 0; sheetIdx < workbook.getNumberOfSheets(); sheetIdx++) {

				zoneCodeList = new ArrayList<List<String>>();

				sheet = workbook.getSheetAt(sheetIdx);
				rowNum = sheet.getPhysicalNumberOfRows();
				sheetName = sheet.getSheetName();

				for (int rowIdx = 1; rowIdx < rowNum; rowIdx++) {

					rowList = new ArrayList<>();
					rowXSS = sheet.getRow(rowIdx);

					cellNum = (rowXSS == null) ? 0 : rowXSS.getPhysicalNumberOfCells();

					for (int colIdx = 0; colIdx < cellNum; colIdx++) {

						cell = rowXSS.getCell(colIdx);
						if (cell.getCellType() == XSSFCell.CELL_TYPE_BLANK)
							break;

						cellVal = (cell.getCellType() == XSSFCell.CELL_TYPE_STRING)
								? cell.getStringCellValue().toString()
								: (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC)
										? Integer.toString((int) cell.getNumericCellValue())
										: null;

						rowList.add(cellVal);
					}
					if (rowList.size() != 0)
						zoneCodeList.add(rowList);

				}
				sheetZoneCodeList.put(sheetName, zoneCodeList);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			try {
				if (fins != null) {
					fins.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return sheetZoneCodeList;
	}
	
}
