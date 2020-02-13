package technicalservices;

import java.io.OutputStream;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;

import model.products.ProductQuantity;
import model.purchases.Purchase;

public class PdfCreator {

	public static void generatePurchaseReceipt(OutputStream outputStream, Purchase order, String imagePath) throws Exception {
		
		Document document = new Document();
        PdfWriter.getInstance(document, outputStream);

        document.open();
        
        document.addTitle("Ricevuta di acquisto ordine n: " + order.getId());
        document.addSubject("Ricevuta di acquisto ordine n: " + order.getId());
        document.addKeywords("iText, email");
        document.addAuthor("TechnoWorld");
        document.addCreator("TechnoWorld");

        Image logo = Image.getInstance(imagePath);
        logo.scaleAbsolute(100,100);
        logo.setAlignment(PdfPCell.ALIGN_MIDDLE);
        Paragraph logoParagraph = new Paragraph();
        logoParagraph.add(logo);

        Paragraph paragraph = new Paragraph();
        Chunk orderRec = new Chunk("\n\nRicevuta Ordine n: " + order.getId());
        orderRec.setFont(new Font(Font.FontFamily.TIMES_ROMAN, (float) 20.0, Font.BOLD));
        paragraph.add(orderRec);
        Chunk summary = new Chunk("\n\nRiepilogo:"); 
        summary.setFont(new Font(Font.FontFamily.UNDEFINED, (float) 14.0));
        paragraph.add(summary);
        
        Chunk glue = new Chunk(new VerticalPositionMark());
        paragraph.add(glue);
        PdfPTable table = new PdfPTable(3); //create a table with 3 columns
        table.setWidthPercentage(100);
        float total = 0;
        int quantity = 0;
        for (ProductQuantity product : order.getProducts()) {
        	total += product.getProduct().getPrice()*product.getQuantity();
        	quantity += product.getQuantity();
        	table.addCell(getCell(product.getProduct().getManufacturer() + " " + product.getProduct().getModel(), PdfPCell.ALIGN_LEFT));
        	table.addCell(getCell("qta. " + product.getQuantity(), PdfPCell.ALIGN_CENTER));
        	table.addCell(getCell("prezzo: " + product.getProduct().getPrice() + " €", PdfPCell.ALIGN_RIGHT));
		}
        table.addCell(getCell("\nTotale:", PdfPCell.ALIGN_LEFT));
        table.addCell(getCell("\nqta. " + quantity, PdfPCell.ALIGN_CENTER));
        
        double a = total * 0.22;
        a = Math.floor(a*100) / 100;
        
        table.addCell(getCell(""+total+"€"+"\n di cui IVA " + (a) + " €", PdfPCell.ALIGN_RIGHT));
        paragraph.add(table);
        Chunk transaction = new Chunk("\n\n\nPagamento gestito da PayPal.\nCodice Transazione: " + order.getPayment().getTransaction());
        paragraph.add(transaction);
        document.add(logoParagraph);
        document.add(paragraph);
        
        
        document.close();
    }
	
	
	public static PdfPCell getCell(String text, int alignment) {
	     PdfPCell cell = new PdfPCell(new Phrase(text));
	     cell.setPadding(0);
	     cell.setHorizontalAlignment(alignment);
	     cell.setBorderWidthLeft(PdfPCell.NO_BORDER);
	     cell.setBorderWidthTop(PdfPCell.NO_BORDER);
	     cell.setBorderWidthRight(PdfPCell.NO_BORDER);
	     cell.setPaddingBottom((float) 2.5); 
	     cell.setBorderWidthBottom((float) 0.5);
	     return cell;
	 }
	
	
}
	
	
	

