package microservices.backend.tecrep.files.import_number_file;

import java.io.FileOutputStream;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import utilities.generic.time.Timestamp;

public class ImportNumberFile {

	private String filename;
	private ArrayList<ImportNumberRow> rows;

	public ImportNumberFile() {
		super();
		rows = new ArrayList<ImportNumberRow>();
		filename = "import_msisdns_auto_" + Timestamp.getCurrentTimestamp("yyyyMMddHHmmss") + ".xlsx";
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public ArrayList<ImportNumberRow> getRows() {
		return rows;
	}

	public void setRows(ArrayList<ImportNumberRow> rows) {
		this.rows = rows;
	}

	public String writeToFile(String folderPath) {
		String path = folderPath + "\\" + filename;

		try {
			XSSFWorkbook wb = new XSSFWorkbook();

			wb.setMissingCellPolicy(MissingCellPolicy.CREATE_NULL_AS_BLANK);
			XSSFSheet sheet = wb.createSheet("Creation");

			String[] headers = { "MSISDN", "STATUS", "GOLDENNUMBER", "NATURE", "INVENTORY_POOL", "COMMENT" };

			int rowNumber = 0;

			// write the header row
			XSSFRow newRow = sheet.createRow(rowNumber);
			for (int i = 0; i < headers.length; i++) {
				XSSFCell cell = newRow.createCell(i);
				cell.setCellValue(headers[i]);
			}

			rowNumber++;

			for (ImportNumberRow row : rows) {
				newRow = sheet.createRow(rowNumber);
				XSSFCell cell = newRow.createCell(0);
				cell.setCellValue(row.getMsisdn());
				cell = newRow.createCell(1);
				cell.setCellValue(row.getStatus());
				cell = newRow.createCell(2);
				cell.setCellValue(row.getGoldenNumber());
				cell = newRow.createCell(3);
				cell.setCellValue(row.getNature());
				cell = newRow.createCell(4);
				cell.setCellValue(row.getInventoryPool().toString());
				cell = newRow.createCell(5);
				cell.setCellValue(row.getComment());
				rowNumber++;
			}

			while (true) {
				try {
					FileOutputStream fos = new FileOutputStream(path);
					wb.write(fos);
					fos.close();
					wb.close();
					System.out.println("File written: " + path);
					return path;
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
