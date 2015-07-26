package meru.document.pdf;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDJpeg;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;

public class PDFBuilder {

  private File mPDFFile;
  private PDDocument mPDDocument;
  private PDPage mPDPage;
  private PDPageContentStream mPDPageContentStream;
  private PDFont mPDFont;
  private int mPDFontSize;

  public PDFBuilder(File pdfFile) throws IOException {
    this(pdfFile,
         PDType1Font.HELVETICA,
         7);
  }

  public PDFBuilder(File pdfFile, PDFont font, int fontSize) throws IOException {
    mPDFFile = pdfFile;
    mPDFont = font;
    mPDFontSize = fontSize;

    mPDDocument = new PDDocument();
    addPage();
  }

  public PDFBuilder addPage() throws IOException {
    mPDPage = new PDPage();
    mPDPage.setMediaBox(PDPage.PAGE_SIZE_A4);
    mPDDocument.addPage(mPDPage);
    mPDPageContentStream = new PDPageContentStream(mPDDocument,
                                                   mPDPage,
                                                   true,
                                                   true);

    // page.setRotation(table.isLandscape() ? 90 : 0);
    return this;
  }

  public PDFBuilder addImage(File imgFile,
                             float x,
                             float y) throws IOException {

    String imgFileName = imgFile.getName()
                                .toLowerCase();
    PDXObjectImage ximage = null;
    if (imgFileName.endsWith(".jpg")) {
      ximage = new PDJpeg(mPDDocument,
                          new FileInputStream(imgFile));
    }
    else {
      throw new IOException("Image type not supported: " + imgFile);
    }
    mPDPageContentStream.drawImage(ximage,
                                   x,
                                   y);

    return this;
  }

  public PDFBuilder drawLine(float xStart,
                              float yStart,
                              float xEnd,
                              float yEnd) throws IOException {
    mPDPageContentStream.drawLine(xStart,
                                  yStart,
                                  xEnd,
                                  yEnd);
    return this;
  }

  public PDFBuilder drawVLine(float x,
                              float y) throws IOException {
    mPDPageContentStream.drawLine(x,
                                  y,
                                  PDPage.PAGE_SIZE_A4.getWidth(),
                                  y);
    return this;
  }

  public PDFBuilder addString(String text,
                              float x,
                              float y) throws IOException {
    mPDPageContentStream.beginText();
    mPDPageContentStream.setFont(mPDFont,
                                 mPDFontSize);
    mPDPageContentStream.moveTextPositionByAmount(x,
                                                  y);
    mPDPageContentStream.drawString(text);
    mPDPageContentStream.endText();

    return this;
  }

  public PDFBuilder addString(String text,
                              PDFont font,
                              int size,
                              float x,
                              float y) throws IOException {
    mPDPageContentStream.beginText();
    mPDPageContentStream.setFont(font,
                                 size);
    mPDPageContentStream.moveTextPositionByAmount(x,
                                                  y);
    mPDPageContentStream.drawString(text);
    mPDPageContentStream.endText();

    return this;

  }

  public void build() throws IOException {
    mPDPageContentStream.close();

    try {
      mPDDocument.save(mPDFFile);
    } catch (COSVisitorException e) {
      throw new IOException(e);
    }
    mPDDocument.close();
  }

  public static void main(String[] args) throws IOException {

    PDFBuilder pdfBuilder = new PDFBuilder(new File("C:/temp/test.pdf"));

    float margin = 20;
    
    float pageTopLeftX = margin;
    float pageTopLeftY = PDPage.PAGE_SIZE_A4.getHeight()-margin;
    float pageTopRightX = PDPage.PAGE_SIZE_A4.getWidth()-margin;
    float pageBottomRightY = margin;
    
    
    //page
    pdfBuilder.drawLine(pageTopLeftX,
                        pageTopLeftY,
                        pageTopRightX,
                        pageTopLeftY);
    
    pdfBuilder.drawLine(pageTopRightX,
                        pageTopLeftY,
                        pageTopRightX,
                        pageBottomRightY);
    
    pdfBuilder.drawLine(pageTopLeftX,
                        pageBottomRightY,
                        pageTopRightX,
                        pageBottomRightY);
    
    pdfBuilder.drawLine(pageTopLeftX,
                        pageTopLeftY,
                        pageTopLeftX,
                        pageBottomRightY);
    
   
    float y = pageTopLeftY - 20;
    
    pdfBuilder.addString("Invoice",
                         PDType1Font.HELVETICA_BOLD,
                         14,
                         pageTopRightX/2,
                         y);

    
    y -= 70;
    pdfBuilder.drawLine(pageTopLeftX,
                        y,
                        pageTopRightX,
                        y);
    
    float tableXStart = margin;
    float tableYStart = y-70;
    float tableWidth = PDPage.PAGE_SIZE_A4.getWidth()-margin-margin;
    float tableHeight = 450;
    
    float headerHeight = 30;
    float columnY = 17;
    
    float prevY = y;
    y = tableYStart;
    float invoiceNoX = pageTopRightX-200;
    
    pdfBuilder.drawLine(invoiceNoX,
                        prevY,
                        invoiceNoX,
                        y);


    pdfBuilder.addString("Invoice No. :",
                         invoiceNoX+10,
                         prevY-16);
    
    pdfBuilder.addString("          Date :",
                         invoiceNoX+10,
                         prevY-36);

    pdfBuilder.addString("            TIN :",
                         invoiceNoX+10,
                         prevY-56);

    
    pdfBuilder.drawLine(tableXStart,
                        tableYStart,
                        tableXStart+tableWidth,
                        tableYStart);
    
    pdfBuilder.drawLine(tableXStart,
                        tableYStart-tableHeight,
                        tableXStart+tableWidth,
                        tableYStart-tableHeight);
    
    
    //header
    
    pdfBuilder.drawLine(tableXStart,
                        tableYStart-headerHeight,
                        tableXStart+tableWidth,
                        tableYStart-headerHeight);
    
    
    //columns

    float x = tableXStart;
    pdfBuilder.addString("S.No",
                         x+5,
                         tableYStart-columnY);

    x = tableXStart+30;
    
    pdfBuilder.drawLine(x,
                        tableYStart,
                        x,
                        tableYStart-tableHeight);
    

    pdfBuilder.addString("Code",
                         x+10,
                         tableYStart-columnY);

    x += 50;
    pdfBuilder.drawLine(x,
                        tableYStart,
                        x,
                        tableYStart-tableHeight);
    
    pdfBuilder.addString("Description",
                         x+10,
                         tableYStart-columnY);

    x += 300;
    
    pdfBuilder.drawLine(x,
                        tableYStart,
                        x,
                        tableYStart-tableHeight);

    pdfBuilder.addString("Quantity",
                         x+10,
                         tableYStart-columnY);
    
    
    x += 50;
    
    pdfBuilder.drawLine(x,
                        tableYStart,
                        x,
                        20+margin);

    pdfBuilder.addString("Price",
                         x+10,
                         tableYStart-columnY);
    
    float qtyStart = x;
    x += 50;
    y -= tableHeight;
    
    float by = 120;
    
    pdfBuilder.drawLine(x,
                        tableYStart,
                        x,
                        by);

    pdfBuilder.addString("Total",
                         x+10,
                         tableYStart-columnY);
    
    float subY = y-30;
    
    
    pdfBuilder.addString("Net Value",
                         qtyStart+65,
                         y-18);
    
    pdfBuilder.drawLine(qtyStart,
                        subY,
                        pageTopRightX,
                        subY);
    
    pdfBuilder.addString("1% VAT",
                         qtyStart+70,
                         subY-18);
    
    

    subY -=30;
    
    pdfBuilder.drawLine(qtyStart,
                        subY,
                        pageTopRightX,
                        subY);

    pdfBuilder.addString("Total",
                         qtyStart+80,
                         subY-18);
    
    
    pdfBuilder.drawLine(tableXStart,
                        by,
                        tableXStart+tableWidth,
                        by);
    
    
    pdfBuilder.addString("Rajesh Export Ltd. shall not obliged",
                         margin+5,
                         by-10);
    
    y = 20+margin;
    
    pdfBuilder.drawLine(tableXStart,
                        y,
                        tableXStart+tableWidth,
                        y);
    pdfBuilder.addString("CA of M/S RAJESH EXPORTS LTD",
                         margin+5,
                         y-12);
    
    pdfBuilder.build();
  }

  public static void main1(String[] args) throws IOException {
    PDFBuilder pdfBuilder = new PDFBuilder(new File("C:/temp/test.pdf"));
    pdfBuilder.addString("Invoice",
                         PDType1Font.HELVETICA_BOLD,
                         14,
                         250,
                         700);
    pdfBuilder.addImage(new File("D:/application/apache-tomcat-7.0.34/webapps/ROOT/common/img/slogo.jpg"),
                        10,
                        675);
    pdfBuilder.addString("DewBag Retail Private Limited",
                         150,
                         720);

    pdfBuilder.addString("138/13 1st Cross, NS Palaya,Bannerghatta Road,Bangalore - 560078. Phone - 2678 0209",
                         150,
                         710);

    pdfBuilder.addString("TIN : 29550684384",
                         150,
                         700);

    pdfBuilder.drawLine(1,
                        690,
                        PDPage.PAGE_SIZE_A4.getWidth(),
                        0);

    pdfBuilder.addString("Invoice No. :",
                         10,
                         680);

    pdfBuilder.addString("Invoice Date :",
                         10,
                         670);
    pdfBuilder.addString("Order No. :",
                         10,
                         660);
    pdfBuilder.addString("Delivery Date :",
                         10,
                         650);
    pdfBuilder.addString("Delivery Slot :",
                         10,
                         640);

    pdfBuilder.addString("Delivery Address :",
                         PDType1Font.HELVETICA_BOLD,
                         8,
                         400,
                         680);

    pdfBuilder.addString("Address 1 ",
                         400,
                         670);

    pdfBuilder.build();
  }

}
