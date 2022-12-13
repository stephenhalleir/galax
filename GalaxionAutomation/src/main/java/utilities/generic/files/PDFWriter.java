package utilities.generic.files;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFWriter {

	/**
	 * Write a text string to a PDF file at a named location
	 * 
	 * @param content
	 * @param destFilepath
	 * @return
	 */
	public static boolean writePDF(String content, String destFilepath) {
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream(destFilepath));
			document.open();
			Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);

			for (int i = 0; i < content.split("\n").length; i++) {
				Paragraph p = new Paragraph(content.split("\n")[i], font);
				document.add(p);
				document.add(Chunk.NEWLINE);
			}

			document.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return true;
	}
}
