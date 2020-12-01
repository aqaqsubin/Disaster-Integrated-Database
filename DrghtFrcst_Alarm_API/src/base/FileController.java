package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FileController {

	public List<Long> getCdList(String filePath) {

		FileInputStream fins = null;
		XSSFWorkbook workbook = null;

		List<Long> cdList = new ArrayList<>();

		try {

			fins = new FileInputStream(filePath);
			workbook = new XSSFWorkbook(fins);

			XSSFSheet sheet;

			int rowNum;

			XSSFRow rowXSS;
			XSSFCell cell;
			long cellVal;

			for (int sheetIdx = 0; sheetIdx < workbook.getNumberOfSheets(); sheetIdx++) {
				sheet = workbook.getSheetAt(sheetIdx);
				rowNum = sheet.getPhysicalNumberOfRows();

				for (int rowIdx = 1; rowIdx < rowNum; rowIdx++) {

					rowXSS = sheet.getRow(rowIdx);

					cell = rowXSS.getCell(0);
					if (cell.getCellType() == XSSFCell.CELL_TYPE_BLANK)
						break;

					cellVal = (cell.getCellType() == XSSFCell.CELL_TYPE_STRING)
							? Long.parseLong(cell.getStringCellValue())
							: (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC)
									? (long) cell.getNumericCellValue()
									: null;

					cdList.add(cellVal);
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

		return cdList;
	}

	
}
