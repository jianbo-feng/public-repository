package com.feng.pdf.test;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class ItextpdfDemo2 {

    static String PATH = "/Users/baby/work/test";
    static String PDF_FILE = "itextpdf-test2.pdf";

    public static void main(String... args) throws Exception {
        createSimplePdf();
    }

    /**
     * 创建示例文档
     * @throws FileNotFoundException
     * @throws DocumentException
     */
    public static void createSimplePdf() throws FileNotFoundException, DocumentException {
        //String path = "E:/demo/pdfCreat/"+System.currentTimeMillis()+".pdf";//输出pdf的路径
        Document doc = new Document();
        PdfWriter.getInstance(doc, new FileOutputStream(new File(PATH, PDF_FILE)));
        doc.open();
        doc.add(new Paragraph("test"));//在pdf中打印文本
        doc.close();


    }
}
