package com.feng.office.util;


import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.poi.hssf.converter.ExcelToHtmlConverter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.xwpf.converter.core.BasicURIResolver;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.core.XWPFConverterException;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.w3c.dom.Document;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Office工具类
 * @author
 * @date 2019/12/08
 * @参考：<a href="https://blog.csdn.net/fyhx2010/article/details/69569310">参考1</a>、<a href="https://blog.csdn.net/babylovewei/article/details/80105983">参考2</a>
 */
public class OfficeUtil {

    //经过测试,dpi为96,100,105,120,150,200中,105显示效果较为清晰,体积稳定,dpi越高图片体积越大,一般电脑显示分辨率为96
    public static final float DEFAULT_DPI = 105;

    //默认转换的图片格式为jpg
    public static final String DEFAULT_FORMAT = "jpg";

    private OfficeUtil() {}

    /**
     * docx文件转html
     * @param path 路径
     * @param fileName 文件名
     * @return
     */
    public static int docxToHtml(String path, String fileName) {
        int rv = 0;
        try {
//            String path =  presentationDto.getWordPath();
            //word路径
//            String wordPath = path.substring(0, path.indexOf("upload") + 6);
            //word文件名
//            String wordName = path.substring(path.lastIndexOf(File.separator)+1, path.lastIndexOf("."));
            //后缀
//            String suffix = path.substring(path.lastIndexOf("."));
            String suffix = fileName.substring(fileName.lastIndexOf("."));
            String fileNameNotContainSuffix = fileName.substring(0, fileName.lastIndexOf("."));
            System.err.println("文件名(不带后缀) : " + fileNameNotContainSuffix);

            //生成html路径
//            String htmlPath = path + File.separator + System.currentTimeMillis() + "_show" + File.separator;
            String htmlPath = path;

            //生成html文件名
            String htmlName = System.currentTimeMillis() + ".html";
            htmlName = fileNameNotContainSuffix + ".html";

            //图片路径
            String imagePath = htmlPath + File.separator + "image" + File.separator;

            //判断html文件是否存在
            File htmlFile = new File(path, htmlName);

            //word文件
//            File wordFile = new File(wordPath + File.separator + wordName + suffix);

            File wordFile = new File(path, fileName);

            // 1) 加载word文档生成 XWPFDocument对象
            InputStream in = new FileInputStream(wordFile);
            XWPFDocument document = new XWPFDocument(in);

            // 2) 解析 XHTML配置 (这里设置IURIResolver来设置图片存放的目录)
            File imgFolder = new File(imagePath);
            XHTMLOptions options = XHTMLOptions.create();
            options.setExtractor(new FileImageExtractor(imgFolder));
            //html中图片的路径 相对路径
            options.URIResolver(new BasicURIResolver("image"));
            options.setIgnoreStylesIfUnused(false);
            options.setFragment(true);

            // 3) 将 XWPFDocument转换成XHTML
            //生成html文件上级文件夹
            File folder = new File(htmlPath);
            if(!folder.exists()){
                folder.mkdirs();
            }
            OutputStream out = new FileOutputStream(htmlFile);
            XHTMLConverter.getInstance().convert(document, out, options);

            // 4) 转换为项目访问路径
            String absolutePath = htmlFile.getAbsolutePath();
//            htmlPath = tempContextUrl + absolutePath.substring(absolutePath.indexOf("upload"));
            System.err.println("html 保存路径：" + htmlPath);
//            presentationDto.setHtmlPath(htmlPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return rv;
        } catch (XWPFConverterException e) {
            e.printStackTrace();
            return rv;
        } catch (IOException e) {
            e.printStackTrace();
            return rv;
        }
        rv = 1;
        return rv;
    }

    /**
     * XLS文件转HTML
     * @param path 文件路径
     * @param fileName 文件名
     * @param htmlName 生成的Html名(若为null或空，则生成为同名html文件)
     * @return
     */
    public static int xlsToHtml(String path, String fileName, String htmlName){
        int rv = 0;
//        String path =  presentationDto.getWordPath();
        //word路径
//        String wordPath = path.substring(0, path.indexOf("upload")+6) + File.separator;
        //word文件名
//        String wordName = fileName;
        try {
            InputStream input = new FileInputStream(new File(path, fileName));
//            Workbook workbook = WorkbookFactory.create(input);
            HSSFWorkbook excelBook = new HSSFWorkbook(input);
            ExcelToHtmlConverter excelToHtmlConverter = new ExcelToHtmlConverter (DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument() );
            excelToHtmlConverter.processWorkbook(excelBook);
            List pics = excelBook.getAllPictures();
            if (pics != null) {
                for (int i = 0; i < pics.size(); i++) {
                    Picture pic = (Picture) pics.get (i);
                    try {
                        pic.writeImageContent (new FileOutputStream (path + pic.suggestFullFileName() ) );
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
            Document htmlDocument = excelToHtmlConverter.getDocument();
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            DOMSource domSource = new DOMSource(htmlDocument);
            StreamResult streamResult = new StreamResult(outStream);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer serializer = tf.newTransformer();
            serializer.setOutputProperty (OutputKeys.ENCODING, "utf-8");
            serializer.setOutputProperty (OutputKeys.INDENT, "yes");
            serializer.setOutputProperty (OutputKeys.METHOD, "html");
            serializer.transform (domSource, streamResult);
            outStream.close();

            String content = new String (outStream.toByteArray(),"utf-8");

            String uuid = UUID.randomUUID().toString();
            htmlName = htmlName == null ? "" : htmlName.trim();
            if ("".equals(htmlName)) {
                htmlName = fileName.substring(0, fileName.lastIndexOf(".")) + "html";
            }
            FileUtils.writeStringToFile(new File(path, htmlName), content, "utf-8");
//            presentationDto.setHtmlPath(tempContextUrl + "upload" + File.separator + uuid+".html");
        } catch (Exception e) {
            e.printStackTrace();
            return rv;
        }
        rv = 1;
        return rv;
    }

    /**
     * ppt转html
     * @param path 文件所在路径
     * @param fileName 文件名
     * @param targetImageFileDir 图片文件目录(相对文件的路径)
     * @param imgFormat 图片格式（默认：jpg）
     * @param htmlName html文件名
     * @return
     */
    public static int pptToHtml(String path, String fileName, String targetImageFileDir, String imgFormat, String htmlName){
        imgFormat = imgFormat == null ? "jpg" : imgFormat.trim();
        imgFormat = "".equals(imgFormat) ? "jpg" : imgFormat;
        List<String> imgList = PPTUtil.converPPTtoImage(path + File.separator + fileName,
                path + File.separator + targetImageFileDir + File.separator, imgFormat, 8);
        int rv = createHtml(path, htmlName, targetImageFileDir, imgList);
        return rv;
    }

    /**
     * ppt转html时生成html
     * @param path	文件所在目录
     * @param imgList 所有幻灯片路径
     * @param targetImageFileDir 图片文件目录(相对文件的路径)
     * @return
     */
    private static int createHtml(String path, String htmlName, String targetImageFileDir, List<String> imgList){
        int rv = 0;
        StringBuilder sb = new StringBuilder("<!doctype html><html><head><meta charset='utf-8'><title>无标题文档</title></head><body>");
        if (imgList != null && !imgList.isEmpty()) {
            for (String img : imgList) {
                sb.append("<img src='" + targetImageFileDir + "/" + img + "' /><br>");
            }
        }
        sb.append("</body></html>");
        try {
            File file = new File(path, htmlName);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"UTF-8"));
            bufferedWriter.write(sb.toString());
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            return rv;
        }
//        presentationDto.setHtmlPath(tempContextUrl + "upload" + File.separator + uuid+".html");
        return 1;
    }

    /**
     * pdf转html
     * @param path 文件路径
     * @param fileName 文件名
     * @param imgPath 图片文件存放目录
     * @return 图片文件目录(相对文件的路径)
     * @param htmlName html文件名
     */
    public static int pdfToHtml(String path, String fileName, String imgPath, String htmlName){
        int rv = 0;
        List<String> imgList = pdfToImage(path, fileName, imgPath);
        rv = createHtml(path, htmlName, imgPath, imgList);
        return 1;
    }

    /**
     * PDF转图片（每50页转成一张图片）
     * @param path 路径
     * @param fileName 文件名
     * @param imgPath 图片保存路径
     * @throws IOException
     * @see <a href="https://blog.csdn.net/YatesJu/article/details/83687371">参考</a>
     */
    public static List<String> pdfToImage(String path, String fileName, String imgPath) {
        List<String> result = new ArrayList<>();
        try {
            /*图像合并使用参数*/
            // 定义宽度
            int width = 0;
            // 保存一张图片中的RGB数据
            int[] singleImgRGB;
            // 定义高度，后面用于叠加
            int shiftHeight = 0;
            //保存每张图片的像素值
            BufferedImage imageResult = null;
            // 利用PdfBox生成图像
            PDDocument pdDocument = PDDocument.load(new File(path, fileName));
            PDFRenderer renderer = new PDFRenderer(pdDocument);
            /*根据总页数, 按照50页生成一张长图片的逻辑, 进行拆分*/
            // 每50页转成1张图片
            int pageLength = 50;
            // 总计循环的次数
            int totalCount = pdDocument.getNumberOfPages() / pageLength + 1;
            for (int m = 0; m < totalCount; m++) {
                for (int i = 0; i < pageLength; i++) {
                    int pageIndex = i + (m * pageLength);
                    if (pageIndex == pdDocument.getNumberOfPages()) {
                        System.out.println("m = " + m);
                        break;
                    }
                    // 96为图片的dpi，dpi越大，则图片越清晰，图片越大，转换耗费的时间也越多
                    BufferedImage image = renderer.renderImageWithDPI(pageIndex, DEFAULT_DPI, ImageType.RGB);
                    int imageHeight = image.getHeight();
                    int imageWidth = image.getWidth();
                    if (i == 0) {
                        //计算高度和偏移量
                        //使用第一张图片宽度;
                        width = imageWidth;
                        // 保存每页图片的像素值
                        // 加个判断：如果m次循环后所剩的图片总数小于pageLength，则图片高度按剩余的张数绘制，否则会出现长图片下面全是黑色的情况
                        if ((pdDocument.getNumberOfPages() - m * pageLength) < pageLength) {
                            imageResult = new BufferedImage(width, imageHeight * (pdDocument.getNumberOfPages() - m * pageLength), BufferedImage.TYPE_INT_RGB);
                        } else {
                            imageResult = new BufferedImage(width, imageHeight * pageLength, BufferedImage.TYPE_INT_RGB);
                        }
                    } else {
                        // 将高度不断累加
                        shiftHeight += imageHeight;
                    }
                    singleImgRGB = image.getRGB(0, 0, width, imageHeight, null, 0, width);
                    imageResult.setRGB(0, shiftHeight, width, imageHeight, singleImgRGB, 0, width);
                }
                System.out.println("m = " + m);
                File imageDir = new File(path + File.separator + imgPath);
                if (!imageDir.exists() || !imageDir.isDirectory()) {
                    imageDir.mkdirs();
                }
                String __name = m + "." + DEFAULT_FORMAT;
                File outFile = new File(path + File.separator + imgPath, __name);
                System.out.println(outFile.getName());
                result.add( __name);
                // 写图片
                ImageIO.write(imageResult, DEFAULT_FORMAT, outFile);
                // 这个很重要，下面会有说明
                shiftHeight = 0;
            }
            pdDocument.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }



    public static void main(String... args) throws Exception {
        String path = "/Users/baby/work/test";
        String fileName = "test.docx";
//        docxToHtml(path, fileName);
//        xlsToHtml(path, "test-xls.xlsx", "test-xls-html.html");

    //    pptToHtml(path, "tianshiyuemo.pptx", "ppt-tianshi-img", "jpg", "tianshiyuemo.html");

    //    pdfToHtml(path, "elasticsearch 集群以及springboot配置.pdf", "pdf-img", "jpg", "springboot-es.html");

//        pdfToImage(path, "elasticsearch 集群以及springboot配置.pdf", "pdf-img");
        pdfToHtml(path, "elasticsearch 集群以及springboot配置.pdf", "pdf-img2", "springboot-es.html");
    }
}
