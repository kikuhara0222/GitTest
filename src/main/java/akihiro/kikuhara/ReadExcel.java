package akihiro.kikuhara;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadExcel {

	public String readExcel() throws IOException, EncryptedDocumentException, InvalidFormatException {
		String filePath = "C:\\Users\\a.kikuhara\\Desktop\\Book1.xlsx";
		String retText = "";

		try {
			InputStream input = new FileInputStream(filePath);
			Workbook workbook = WorkbookFactory.create(input);
			
			Sheet sheet = workbook.getSheetAt(0);
			
			for(int i = 0; i < 5; i++){
				Row row = sheet.getRow(i);
				retText = retText + String.valueOf(row.getCell(3));
			}
			
		} catch (IOException e) {
			throw e;
		} catch (EncryptedDocumentException e) {
			throw e;
		} catch (InvalidFormatException e) {
			throw e;
		}

		return retText;
	}
}
