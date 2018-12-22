package fr.bicomat.ExcelDownload;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;

import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import fr.bicomat.entities.Client;




public class ExportClientToExcel {
	
	public static ByteArrayInputStream clientsToExcel(List<Client> clients) throws IOException {
		String[] COLUMNs = {"Id", "Nom", "Prénom", "Adresse"};
		try(
				Workbook workbook = new XSSFWorkbook();
				ByteArrayOutputStream out = new ByteArrayOutputStream();
		){
	
	 
			Sheet sheet = workbook.createSheet("Clients");
	 
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setColor(IndexedColors.BLUE.getIndex());
	 
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);
	 
			// Row for Header
			Row headerRow = sheet.createRow(0);
	 
			// Header
			for (int col = 0; col < COLUMNs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(COLUMNs[col]);
				cell.setCellStyle(headerCellStyle);
			}
	 
			
			int rowIdx = 1;
			for (Client client : clients) {
				Row row = sheet.createRow(rowIdx++);
	 
				row.createCell(0).setCellValue(client.getIdclient());
				row.createCell(1).setCellValue(client.getNomClient());
				row.createCell(2).setCellValue(client.getPrenomClient());
				row.createCell(3).setCellValue(client.getAdresse());
				

			}
	 
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}
}
