package classes.pdfexport;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;

import javax.servlet.ServletContext;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import classes.wrapper.MarkSheet;

public class ExportMarkSheet {
	private MarkSheet markSheet;
	private Document document;
	private String fileName;
	private String fileRelativePath;

	public ExportMarkSheet(MarkSheet iMarkSheet) {
		this.markSheet = iMarkSheet;
		document = new Document();
		this.fileName = markSheet.getRollNumber()+".pdf";
		fileRelativePath = iMarkSheet.getFileRelativePath();
	}

	public void export() {
		addImage();
		addText(markSheet.getInstituteName());
		addText(markSheet.getExamName());
		addText(markSheet.getStudentName());
		addText(markSheet.getRollNumber());
		addTable();
		setAttributes();
	}

	private void addImage() {
		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(this.fileName));
			document.open();

			// Add Image
			Image image1 = Image.getInstance(fileRelativePath + "instituteLogo.jpg");
			// Fixed Positioning
			image1.setAbsolutePosition(100f, 550f);
			// Scale to new height and new width of image
			image1.scaleAbsolute(100, 100);
			// Add to document
			document.add(image1);

			String imageUrl = "http:/google.co.in";
			Image image2 = Image.getInstance(new URL(imageUrl));
			document.add(image2);

			document.close();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setAttributes() {
		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(this.fileName));
			document.open();

			// Set attributes here
			document.addAuthor(markSheet.getAuthor());
			document.addCreationDate();
			document.addCreator(markSheet.getInstituteName());
			document.addTitle(markSheet.getExamName());
			document.addSubject(markSheet.getExamName() + " result");

			document.close();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addTable() {

		Document document = new Document();
		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(this.fileName));
			document.open();

			PdfPTable table = new PdfPTable(markSheet.getRow1().size()); // 3
																			// columns.
			table.setWidthPercentage(100); // Width 100%
			table.setSpacingBefore(10f); // Space before table
			table.setSpacingAfter(10f); // Space after table

			// Set Column widths
			float[] columnWidths = { 1f, 1f, 1f };
			table.setWidths(columnWidths);
			for (String cellVal : markSheet.getRow1()) {
				PdfPCell cell1 = new PdfPCell(new Paragraph(cellVal));
				cell1.setBorderColor(BaseColor.BLUE);
				cell1.setPaddingLeft(10);
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell1);
			}

			for (String cellVal : markSheet.getRow2()) {
				PdfPCell cell1 = new PdfPCell(new Paragraph(cellVal));
				cell1.setBorderColor(BaseColor.BLUE);
				cell1.setPaddingLeft(10);
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell1);
			}

			for (String cellVal : markSheet.getRow3()) {
				PdfPCell cell1 = new PdfPCell(new Paragraph(cellVal));
				cell1.setBorderColor(BaseColor.BLUE);
				cell1.setPaddingLeft(10);
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell1);
			}

			for (String cellVal : markSheet.getRow4()) {
				PdfPCell cell1 = new PdfPCell(new Paragraph(cellVal));
				cell1.setBorderColor(BaseColor.BLUE);
				cell1.setPaddingLeft(10);
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell1);
			}

			document.add(table);

			document.close();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void addText(String iText){
		try
	      {
	         PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(this.fileName));
	         document.open();
	         document.add(new Paragraph(iText));
	         document.close();
	         writer.close();
	      } catch (DocumentException e)
	      {
	         e.printStackTrace();
	      } catch (FileNotFoundException e)
	      {
	         e.printStackTrace();
	      }
	}
}
