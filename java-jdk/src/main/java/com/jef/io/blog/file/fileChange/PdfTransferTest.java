package com.jef.io.blog.file.fileChange;

import com.jef.io.blog.FileGlobal;

import com.spire.pdf.FileFormat;
import com.spire.pdf.PdfDocument;
import net.sourceforge.tess4j.TesseractException;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * pdf转换
 *
 * @author Jef
 * @date 2022/4/8
 */
public class PdfTransferTest {

    @Test
    public void testPdfToWord() {
        // Create a PdfDocument instance
        PdfDocument pdf = new PdfDocument();
        // Load a PDF file .
        pdf.loadFromFile(FileGlobal.PDF_LOCAL);
        //Save to .docx file
        pdf.saveToFile(FileGlobal.DOCX_LOCAL, FileFormat.DOCX);
        pdf.close();
    }

    @Test
    public void testPdfToExcel() {
        // Create a PdfDocument instance
        PdfDocument pdf = new PdfDocument();
        // Load a PDF file .
        pdf.loadFromFile(FileGlobal.PDF_LOCAL);
        //Save to .docx file
        pdf.saveToFile(FileGlobal.XLSX_LOCAL, FileFormat.XLSX);
        pdf.close();
    }

    @Test
    public void testPdfToImage() throws IOException {
        // Create a PdfDocument instance
        PdfDocument pdf = new PdfDocument();
        // Load a PDF file .
        pdf.loadFromFile(FileGlobal.PDF_LOCAL);
        //Declare a Bufferedlmage variable
        BufferedImage image;
        //Loop through the pages
        for (int i = 0; i < pdf.getPages().getCount(); i++) {
            //Save the specific page as image data
            image = pdf.saveAsImage(i);
            //Write image data to png file
            File file = new File(String.format("D:/out/ToImage %d.png", i));
            ImageIO.write(image, "PNG", file);
        }
        pdf.close();

    }

    @Test
    public void testPdfToImageThanOut() throws IOException, TesseractException {
        // Create a PdfDocument instance
        PdfDocument pdf = new PdfDocument();
        // Load a PDF file .
        pdf.loadFromFile(FileGlobal.PDF_LOCAL);
        //Declare a Bufferedlmage variable
        BufferedImage image;
        //Loop through the pages
        for (int i = 0; i < pdf.getPages().getCount(); i++) {
            //Save the specific page as image data
            image = pdf.saveAsImage(i);
            //Write image data to png file
            String imageUrl = String.format("D:/out/ToImage %d.png", i);
            File file = new File(imageUrl);
            ImageIO.write(image, "PNG", file);
            ImageTransferUtil.imageToWord(imageUrl);
        }
        pdf.close();

    }

}