package com.feng.pdf2ppt.tools;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.poi.sl.usermodel.*;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.xslf.usermodel.*;
import org.apache.poi.xssf.usermodel.TextAlign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class PdfToImage {

    private static final Logger logger = LoggerFactory.getLogger(PdfToImage.class);

    //经过测试,dpi为96,100,105,120,150,200中,105显示效果较为清晰,体积稳定,dpi越高图片体积越大,一般电脑显示分辨率为96
    public static final float DEFAULT_DPI = 105;
    //默认转换的图片格式为jpg
    public static final String DEFAULT_FORMAT = "jpg";

    public static final String DEFAULT_IMG_FORMAT = "png";

    /**
     * pdf转图片
     *
     * @param pdfPath PDF路径
     * @return 图片路径
     */
    public static void pdfToImage(String pdfPath, String imgPath) {
        try {

            System.setProperty("sun.java2d.cmm","sun.java2d.cmm.kcms.KcmsServiceProvider");
            //图像合并使用参数
            // 总宽度
            int width = 0;
            // 保存一张图片中的RGB数据
            int[] singleImgRGB;
            int shiftHeight = 0;
            //保存每张图片的像素值
            BufferedImage imageResult = null;
            //利用PdfBox生成图像
            PDDocument pdDocument = PDDocument.load(new File(pdfPath));
            PDFRenderer renderer = new PDFRenderer(pdDocument);

            File imgSaveDir = new File(imgPath).getParentFile();
            if (!imgSaveDir.exists()) {
                imgSaveDir.mkdirs();
            }

            //循环每个页码
            for (int i = 0, len = pdDocument.getNumberOfPages(); i < len; i++) {
                BufferedImage image = renderer.renderImageWithDPI(i, DEFAULT_DPI, ImageType.RGB);
                int imageHeight = image.getHeight();
                int imageWidth = image.getWidth();
                //计算高度和偏移量
                if (i == 0) {
                    //使用第一张图片宽度;
                    width = imageWidth;
                    //保存每页图片的像素值
                    imageResult = new BufferedImage(width, imageHeight * len, BufferedImage.TYPE_INT_RGB);
                } else {
                    // 计算偏移高度
                    shiftHeight += imageHeight;
                }
                singleImgRGB = image.getRGB(0, 0, width, imageHeight, null, 0, width);
                // 写入流中
                imageResult.setRGB(0, shiftHeight, width, imageHeight, singleImgRGB, 0, width);

                ImageIO.write(image, DEFAULT_FORMAT, new File(imgSaveDir.getAbsolutePath() + File.separator + i+".png"));
            }
            pdDocument.close();
            // 写图片
            ImageIO.write(imageResult, DEFAULT_FORMAT, new File(imgPath));
        } catch (Exception e) {
            logger.error("PDF转图片失败");
            e.printStackTrace();
        }
    }

    /**
     * pdf转图片组(每个文档一张图片)
     *
     * @param pdfPath PDF路径
     * @return 图片路径
     */
    public static void pdfToImages(String pdfPath, String imgPath) {
        System.setProperty("sun.java2d.cmm","sun.java2d.cmm.kcms.KcmsServiceProvider");
        File file;
        PDDocument doc;
        try {
            file = new File(pdfPath);
            doc = PDDocument.load(file);
            PDFRenderer renderer = new PDFRenderer(doc);
            int pageCount = doc.getNumberOfPages();
            for(int i=0;i<pageCount;i++){
                BufferedImage image = renderer.renderImageWithDPI(i, DEFAULT_DPI, ImageType.RGB);
                String path = imgPath + File.separator;
                File mewPath = new File(path);
                if(!mewPath.exists()){
                    mewPath.mkdirs();
                }
                ImageIO.write(image, "PNG", new File(imgPath + File.separator+i+".png"));
            }
        }
        catch (InvalidPasswordException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成幻灯片
     * @param writePath 输出路径
     * @param pages 图片文件(名)组
     * @param imgDir 图片存储目录
     */
    public static void ppt(String writePath, String[] pages, String imgDir) {
        try {
            System.out.println("-------------------------开始生成！------------------------");
            XMLSlideShow pptx = new XMLSlideShow();
            // 初始化每页PPT
            int pageSize = pages.length;
            XSLFSlide[] slides = new XSLFSlide[pageSize];
            int width = pptx.getPageSize().width, height = pptx.getPageSize().height;
            for (int i = 0; i < pageSize; i++) {
                slides[i] = pptx.createSlide();
                PictureData pictureData = pptx.addPicture(new File(imgDir + File.separator + i + PictureData.PictureType.PNG.extension), PictureData.PictureType.PNG);
                XSLFPictureShape shape = slides[i].createPicture(pictureData);
                shape.setAnchor(new java.awt.Rectangle(0, 0, width, height));
            }
            pptx.write(new FileOutputStream(writePath));
            System.out.println("-------------------------生成成功！------------------------");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * PDF文件转换为ppt
     * @param pdfPath PDF文件路径
     * @param pptPath PPT文件路径(若为null或空，则保存至PDF文件当前目录)
     */
    public static void pdfConvertSlideShow(String pdfPath, String pptPath) {
        try {
            System.setProperty("sun.java2d.cmm", "sun.java2d.cmm.kcms.KcmsServiceProvider");
            File pdfFile = new File(pdfPath);
            if (pdfFile.exists()) {
                File pptFile = null;
                pptPath = null == pptPath ? "" : pptPath.trim();
                if ("".equals(pptPath)) {
                    pptPath = pdfFile.getAbsolutePath();
                    pptPath = pptPath.substring(0, pptPath.lastIndexOf('.') + 1) + "ppt";
                    System.err.println("ppt文件名：" + pptPath);
                    pptFile = new File(pptPath);
                }
                else {
                    pptFile = new File(pptPath);
                    File pptDir = pptFile.getParentFile();
                    if (!pptDir.exists() && !pptDir.isDirectory()) {
                        pptDir.mkdirs();
                    }
                }
                pdfInputStreamConvertSlideShow(new FileInputStream(pdfFile), pptFile);
                System.out.println("-------------------------生成成功！------------------------");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * PDF文件转换为ppt
     * @param pdfUrl PDF文件链接
     * @param pptPath PPT文件路径
     */
    public static void pdfConvertSlideShow(URL pdfUrl, String pptPath) {
        try {
            System.setProperty("sun.java2d.cmm", "sun.java2d.cmm.kcms.KcmsServiceProvider");
            HttpURLConnection conn = (HttpURLConnection)pdfUrl.openConnection();
            //设置超时间为3秒
            conn.setConnectTimeout(3 * 1000);
            //防止屏蔽程序抓取而返回403错误
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

            File pptFile = new File(pptPath);
            File pptDir = pptFile.getParentFile();
            if (!pptDir.exists() && !pptDir.isDirectory()) {
                pptDir.mkdirs();
            }

            pdfInputStreamConvertSlideShow(conn.getInputStream(), pptFile);
            System.out.println("-------------------------生成成功！------------------------");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * PDF文件转换为ppt
     * @param pdfUrl PDF文件链接
     * @param pptPath PPT文件路径
     */
    public static void pdfUrlConvertSlideShow(String pdfUrl, String pptPath) {
        try {
            System.setProperty("sun.java2d.cmm", "sun.java2d.cmm.kcms.KcmsServiceProvider");
            URL url = new URL(pdfUrl);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            //设置超时间为3秒
            conn.setConnectTimeout(3 * 1000);
            //防止屏蔽程序抓取而返回403错误
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

            File pptFile = new File(pptPath);
            File pptDir = pptFile.getParentFile();
            if (!pptDir.exists() && !pptDir.isDirectory()) {
                pptDir.mkdirs();
            }

            pdfInputStreamConvertSlideShow(conn.getInputStream(), pptFile);
            System.out.println("-------------------------生成成功！------------------------");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * PDF文件流转换为ppt文件
     * @param inputStream
     * @param pptFile
     * @throws IOException
     */
    public static void pdfInputStreamConvertSlideShow(InputStream inputStream, File pptFile) throws IOException {
        XMLSlideShow pptx = new XMLSlideShow();
        int width = pptx.getPageSize().width, height = pptx.getPageSize().height;

        PDDocument doc = PDDocument.load(inputStream);
        PDFRenderer renderer = new PDFRenderer(doc);
        int pageCount = doc.getNumberOfPages();
        for(int i=0; i< pageCount; i++){
            BufferedImage image = renderer.renderImageWithDPI(i, DEFAULT_DPI, ImageType.RGB);
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(image, DEFAULT_IMG_FORMAT, os);
            InputStream input = new ByteArrayInputStream(os.toByteArray());

            XSLFSlide slide = pptx.createSlide();
            PictureData pictureData = pptx.addPicture(input, PictureData.PictureType.PNG);
            XSLFPictureShape shape = slide.createPicture(pictureData);
            shape.setAnchor(new java.awt.Rectangle(0, 0, width, height));
        }
        pptx.write(new FileOutputStream(pptFile));
    }

    public static void main(String[] args) {
        String pdfPath = "C:\\work\\test//test.pdf";
        String writePath = "C:\\work\\test\\pdf-img2\\test.ppt";
    //    pdfToImage("C:\\work\\test//test.pdf","C:\\work\\test\\pdf-img\\temp_1546570068959.jpg");

    //    pdfToImages("C:\\work\\test//test.pdf","C:\\work\\test\\pdf-img2");

//        ppt(writePath, new String[]{"0.png", "1.png", "2.png", "3.png", "4.png", "5.png", "6.png", "7.png"}, "C:\\work\\test\\pdf-img2");

        pdfConvertSlideShow(pdfPath, "");
    }
}
