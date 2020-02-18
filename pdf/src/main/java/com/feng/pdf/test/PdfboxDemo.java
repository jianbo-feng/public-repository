package com.feng.pdf.test;

import org.apache.pdfbox.contentstream.PDContentStream;
import org.apache.pdfbox.pdfparser.PDFStreamParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * pdfbox测试示例
 * @author jianbo.feng
 * @date 2020/02/18
 * @see <a href="https://blog.csdn.net/qq_41429002/article/details/82191163">参考</a>
 */
public class PdfboxDemo {

    static String PATH = "/Users/baby/work/test";
    static String PDF_FILE1 = "pdfbox-test1.pdf";

    /**
     * 创建pdf文档
     */
    public void createHelloPdf() {
        PDDocument doc = null;
        PDPage page = null;

        try {
            doc = new PDDocument();
            page = new PDPage();
            doc.addPage(page);
            PDFont font = PDType1Font.HELVETICA_BOLD;
            PDPageContentStream contentStream = new PDPageContentStream(doc, page);
            contentStream.beginText();
            contentStream.setFont(font, 12);
            contentStream.moveTextPositionByAmount(100, 700);
//            contentStream.moveTo(100, 700);
            contentStream.drawString("hello");
            contentStream.endText();
            contentStream.close();
            doc.save(new File(PATH, PDF_FILE1));
            doc.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取pdf文档
     */
    public void readPdf() {
        PDDocument document = null;
        try {
            document = PDDocument.load(new File(PATH, PDF_FILE1));
            PDFTextStripper textStripper = new PDFTextStripper();
            String text = textStripper.getText(document);
            System.err.println("pdf文档：" + text);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * 修改pdf
     * @param srcFile 源文件
     * @param destFile 目标文件
     * @param strToFind 需要查找的字符串
     * @param message 写入文档的消息
     */
    public void modifyPdf(File srcFile, File destFile, String strToFind, String message)
            throws IOException {
        PDDocument doc = null;
        try {
            doc = PDDocument.load(srcFile);
            PDPageTree pdPageTree = doc.getDocumentCatalog().getPages();
            int pages = pdPageTree.getCount();
//            for (int i = 0; i < pages; i ++) {
//                PDPage pdPage = pdPageTree.get(i);
//                InputStream content = pdPage.getContents();
//                PDContentStream pdContentStream = null;
//                PDFStreamParser parser = new PDFStreamParser(pdContentStream);
//            }


        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        finally {
            if (null != doc) {
                doc.close();
            }
        }
    }

}
