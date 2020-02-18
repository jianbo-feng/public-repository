package com.feng.pdf.test;

import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * itextpdf 使用示例——生成PDF文档
 * @author feng
 * @date 2020/02/18
 * @see <a href="https://blog.csdn.net/rhy0619/article/details/101032218">参考</a>
 * <dependency>
 *     <groupId>com.itextpdf</groupId>
 *     <artifactId>itextpdf</artifactId>
 *     <version>5.5.13.1</version>
 * </dependency>
 * <dependency>
 *     <groupId>com.itextpdf</groupId>
 *     <artifactId>itext-asian</artifactId>
 *     <version>5.2.0</version>
 * </dependency>
 */

public class ItextPdfDemo extends PdfPageEventHelper {

    static String PATH = "/Users/baby/work/test";
    static String PDF_FILE1 = "itextpdf-test1.pdf";

    public static void main(String[] args) {
        generatePdf();
    }
    /**
     * 页眉
     */
    public String header = "";

    /**
     * 文档字体大小，页脚页眉最好和文本大小一致
     */
    public int presentFontSize = 12;

    /**
     * 文档页面大小，最好前面传入，否则默认为A4纸张
     */
    public Rectangle pageSize = PageSize.A4;

    // 模板
    public PdfTemplate total;

    // 基础字体对象
    public BaseFont bf = null;

    // 利用基础字体生成的字体对象，一般用于生成中文文字
    public Font fontDetail = null;

    private static void generatePdf() {

        try {
            //Document document = new Document(PageSize.A4.rotate());
            Document document = new Document();
            File file = new File(PATH, PDF_FILE1);
            PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(file));

            BaseColor baseColor = new BaseColor(241, 248, 253);
            BaseColor lightGrey = new BaseColor(0xCC, 0xCC, 0xCC);
            //设置字体
            BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            Font FontChinese10 = new Font(bfChinese, 10, Font.NORMAL);

            setFooter(pdfWriter,bfChinese,10,PageSize.A4);
            document.open();

            PdfPTable table1 = new PdfPTable(5);
            int width[] = {20, 20, 20, 20, 20};
            table1.setWidths(width);

            //第一行
            PdfPCell cell11 = new PdfPCell(new Paragraph("汇交项目信息",FontChinese10));
            PdfPCell cell12 = new PdfPCell(new Paragraph("项目名称",FontChinese10));
            PdfPCell cell13 = new PdfPCell(new Paragraph("项目名称XXXX",FontChinese10));
            PdfPCell cell14 = new PdfPCell(new Paragraph("项目编号",FontChinese10));
            PdfPCell cell15 = new PdfPCell(new Paragraph("项目编号XXXX",FontChinese10));

            //第二行
            PdfPCell cell22 = new PdfPCell(new Paragraph("项目负责人",FontChinese10));
            PdfPCell cell23 = new PdfPCell(new Paragraph("项目负责人XXX",FontChinese10));
            PdfPCell cell24 = new PdfPCell(new Paragraph("项目起止时间",FontChinese10));
            PdfPCell cell25 = new PdfPCell(new Paragraph("项目起止时间XXX",FontChinese10));


            //第三行
            PdfPCell cell32 = new PdfPCell(new Paragraph("项目负责单位",FontChinese10));
            PdfPCell cell33 = new PdfPCell(new Paragraph("项目负责单位XXX",FontChinese10));

            //第四行
            PdfPCell cell42 = new PdfPCell(new Paragraph("汇交人",FontChinese10));
            PdfPCell cell43 = new PdfPCell(new Paragraph("汇交人XXX",FontChinese10));
            PdfPCell cell44 = new PdfPCell(new Paragraph("联系电话",FontChinese10));
            PdfPCell cell45 = new PdfPCell(new Paragraph("联系电话XXX",FontChinese10));

            //第五行
            PdfPCell cell52 = new PdfPCell(new Paragraph("电子邮箱",FontChinese10));
            PdfPCell cell53 = new PdfPCell(new Paragraph("电子邮箱XXX",FontChinese10));

            cell11.setRowspan(5);
            cell11.setFixedHeight(150);cell11.setHorizontalAlignment(Element.ALIGN_CENTER);cell11.setVerticalAlignment(Element.ALIGN_MIDDLE);cell11.setBorderColor(lightGrey);
            cell12.setFixedHeight(30);cell12.setHorizontalAlignment(Element.ALIGN_CENTER);cell12.setVerticalAlignment(Element.ALIGN_MIDDLE);cell12.setBorderColor(lightGrey);
            cell13.setFixedHeight(30);cell13.setHorizontalAlignment(Element.ALIGN_LEFT);cell13.setVerticalAlignment(Element.ALIGN_MIDDLE);cell13.setBorderColor(lightGrey);
            cell14.setFixedHeight(30);cell14.setHorizontalAlignment(Element.ALIGN_CENTER);cell14.setVerticalAlignment(Element.ALIGN_MIDDLE);cell14.setBorderColor(lightGrey);
            cell15.setFixedHeight(30);cell15.setHorizontalAlignment(Element.ALIGN_LEFT);cell15.setVerticalAlignment(Element.ALIGN_MIDDLE);cell15.setBorderColor(lightGrey);

            cell22.setFixedHeight(30);cell22.setHorizontalAlignment(Element.ALIGN_CENTER);cell22.setVerticalAlignment(Element.ALIGN_MIDDLE);cell22.setBorderColor(lightGrey);
            cell23.setFixedHeight(30);cell23.setHorizontalAlignment(Element.ALIGN_LEFT);cell23.setVerticalAlignment(Element.ALIGN_MIDDLE);cell23.setBorderColor(lightGrey);
            cell24.setFixedHeight(30);cell24.setHorizontalAlignment(Element.ALIGN_CENTER);cell24.setVerticalAlignment(Element.ALIGN_MIDDLE);cell24.setBorderColor(lightGrey);
            cell25.setFixedHeight(30);cell25.setHorizontalAlignment(Element.ALIGN_LEFT);cell25.setVerticalAlignment(Element.ALIGN_MIDDLE);cell25.setBorderColor(lightGrey);
            cell22.setBackgroundColor(baseColor);
            cell23.setBackgroundColor(baseColor);
            cell24.setBackgroundColor(baseColor);
            cell25.setBackgroundColor(baseColor);

            cell33.setColspan(3);
            cell32.setFixedHeight(30);cell32.setHorizontalAlignment(Element.ALIGN_CENTER);cell32.setVerticalAlignment(Element.ALIGN_MIDDLE);cell32.setBorderColor(lightGrey);
            cell33.setFixedHeight(30);cell33.setHorizontalAlignment(Element.ALIGN_LEFT);cell33.setVerticalAlignment(Element.ALIGN_MIDDLE);cell33.setBorderColor(lightGrey);

            cell42.setFixedHeight(30);cell42.setHorizontalAlignment(Element.ALIGN_CENTER);cell42.setVerticalAlignment(Element.ALIGN_MIDDLE);cell42.setBorderColor(lightGrey);
            cell43.setFixedHeight(30);cell43.setHorizontalAlignment(Element.ALIGN_LEFT);cell43.setVerticalAlignment(Element.ALIGN_MIDDLE);cell43.setBorderColor(lightGrey);
            cell44.setFixedHeight(30);cell44.setHorizontalAlignment(Element.ALIGN_CENTER);cell44.setVerticalAlignment(Element.ALIGN_MIDDLE);cell44.setBorderColor(lightGrey);
            cell45.setFixedHeight(30);cell45.setHorizontalAlignment(Element.ALIGN_LEFT);cell45.setVerticalAlignment(Element.ALIGN_MIDDLE);cell45.setBorderColor(lightGrey);
            cell42.setBackgroundColor(baseColor);
            cell43.setBackgroundColor(baseColor);
            cell44.setBackgroundColor(baseColor);
            cell45.setBackgroundColor(baseColor);

            cell53.setColspan(3);
            cell52.setFixedHeight(30);cell52.setHorizontalAlignment(Element.ALIGN_CENTER);cell52.setVerticalAlignment(Element.ALIGN_MIDDLE);cell52.setBorderColor(lightGrey);
            cell53.setFixedHeight(30);cell53.setHorizontalAlignment(Element.ALIGN_LEFT);cell53.setVerticalAlignment(Element.ALIGN_MIDDLE);cell53.setBorderColor(lightGrey);

            table1.addCell(cell11);
            table1.addCell(cell12);
            table1.addCell(cell13);
            table1.addCell(cell14);
            table1.addCell(cell15);

            table1.addCell(cell22);
            table1.addCell(cell23);
            table1.addCell(cell24);
            table1.addCell(cell25);

            table1.addCell(cell32);
            table1.addCell(cell33);

            table1.addCell(cell42);
            table1.addCell(cell43);
            table1.addCell(cell44);
            table1.addCell(cell45);

            table1.addCell(cell52);
            table1.addCell(cell53);

            document.add(table1);

            PdfPTable table2 = new PdfPTable(2);
            int width2[] = {20, 80};
            table2.setWidths(width2);

            //第六行
            PdfPCell cell61 = new PdfPCell(new Paragraph("汇交方式",FontChinese10));
            PdfPCell cell62 = new PdfPCell(new Paragraph("□实时传输  √光盘 □移动银盘  □纸质  □其他",FontChinese10));

            //第七行□√
            PdfPCell cell71 = new PdfPCell(new Paragraph("其他材料或说明",FontChinese10));
            PdfPCell cell72 = new PdfPCell(new Paragraph("其他材料或说明XXX",FontChinese10));

            cell61.setFixedHeight(30);cell61.setHorizontalAlignment(Element.ALIGN_CENTER);cell61.setVerticalAlignment(Element.ALIGN_MIDDLE);cell61.setBorderColor(lightGrey);
            cell62.setFixedHeight(30);cell62.setHorizontalAlignment(Element.ALIGN_LEFT);cell62.setVerticalAlignment(Element.ALIGN_MIDDLE);cell62.setBorderColor(lightGrey);
            cell61.setBackgroundColor(baseColor);
            cell62.setBackgroundColor(baseColor);

            cell71.setFixedHeight(30);cell71.setHorizontalAlignment(Element.ALIGN_CENTER);cell71.setVerticalAlignment(Element.ALIGN_MIDDLE);cell71.setBorderColor(lightGrey);
            cell72.setFixedHeight(30);cell72.setHorizontalAlignment(Element.ALIGN_LEFT);cell72.setVerticalAlignment(Element.ALIGN_MIDDLE);cell72.setBorderColor(lightGrey);

            table2.addCell(cell61);
            table2.addCell(cell62);

            table2.addCell(cell71);
            table2.addCell(cell72);

            document.add(table2);

            PdfPTable table3 = new PdfPTable(1);
            int width3[] = {100};
            table3.setWidths(width3);

            //第八行
            PdfPCell cell81 = new PdfPCell(new Paragraph("数据资源汇交方承诺：\n所汇交的数据资源是项目产出的全部数据",FontChinese10));
            cell81.setFixedHeight(80);cell81.setHorizontalAlignment(Element.ALIGN_LEFT);cell81.setVerticalAlignment(Element.ALIGN_TOP);cell81.setBorderColor(lightGrey);
            cell81.setBackgroundColor(baseColor);

            table3.addCell(cell81);
            document.add(table3);

            //汇交数据清单
            java.util.List<HashMap<String,String>> dataList = getData();

            if(dataList != null && dataList.size() > 0 && dataList.size() <=7){
                PdfPTable table7 = new PdfPTable(6);
                int width7[] = {25,15,15,15,15,15};
                table7.setWidths(width7);

                PdfPCell cellh11 = new PdfPCell(new Paragraph("汇交数据清单",FontChinese10));
                PdfPCell cellh12 = new PdfPCell(new Paragraph("序号",FontChinese10));
                PdfPCell cellh13 = new PdfPCell(new Paragraph("数据名称",FontChinese10));
                PdfPCell cellh14 = new PdfPCell(new Paragraph("数据量(MB)",FontChinese10));
                PdfPCell cellh15 = new PdfPCell(new Paragraph("记录数(条)",FontChinese10));
                PdfPCell cellh16 = new PdfPCell(new Paragraph("数据格式",FontChinese10));

                cellh11.setRowspan(dataList.size()+1);
                cellh11.setFixedHeight((dataList.size()+1)*30);cellh11.setHorizontalAlignment(Element.ALIGN_CENTER);cellh11.setVerticalAlignment(Element.ALIGN_MIDDLE);cellh11.setBorderColor(lightGrey);
                cellh12.setFixedHeight(30);cellh12.setHorizontalAlignment(Element.ALIGN_CENTER);cellh12.setVerticalAlignment(Element.ALIGN_MIDDLE);cellh12.setBorderColor(lightGrey);
                cellh13.setFixedHeight(30);cellh13.setHorizontalAlignment(Element.ALIGN_CENTER);cellh13.setVerticalAlignment(Element.ALIGN_MIDDLE);cellh13.setBorderColor(lightGrey);
                cellh14.setFixedHeight(30);cellh14.setHorizontalAlignment(Element.ALIGN_CENTER);cellh14.setVerticalAlignment(Element.ALIGN_MIDDLE);cellh14.setBorderColor(lightGrey);
                cellh15.setFixedHeight(30);cellh15.setHorizontalAlignment(Element.ALIGN_CENTER);cellh15.setVerticalAlignment(Element.ALIGN_MIDDLE);cellh15.setBorderColor(lightGrey);
                cellh16.setFixedHeight(30);cellh16.setHorizontalAlignment(Element.ALIGN_CENTER);cellh16.setVerticalAlignment(Element.ALIGN_MIDDLE);cellh16.setBorderColor(lightGrey);

                table7.addCell(cellh11);
                table7.addCell(cellh12);
                table7.addCell(cellh13);
                table7.addCell(cellh14);
                table7.addCell(cellh15);
                table7.addCell(cellh16);


                for(int i=0;i<dataList.size();i++){
                    PdfPCell  dnum = new PdfPCell(new Paragraph(dataList.get(i).get("dnum"),FontChinese10));
                    PdfPCell  dname = new PdfPCell(new Paragraph(dataList.get(i).get("dname"),FontChinese10));
                    PdfPCell  dcount = new PdfPCell(new Paragraph(dataList.get(i).get("dcount"),FontChinese10));
                    PdfPCell  dtotal = new PdfPCell(new Paragraph(dataList.get(i).get("dtotal"),FontChinese10));
                    PdfPCell  dtype = new PdfPCell(new Paragraph(dataList.get(i).get("dtype"),FontChinese10));

                    dnum.setFixedHeight(30);dnum.setHorizontalAlignment(Element.ALIGN_CENTER);dnum.setVerticalAlignment(Element.ALIGN_MIDDLE);dnum.setBorderColor(lightGrey);
                    dname.setFixedHeight(30);dname.setHorizontalAlignment(Element.ALIGN_CENTER);dname.setVerticalAlignment(Element.ALIGN_MIDDLE);dname.setBorderColor(lightGrey);
                    dcount.setFixedHeight(30);dcount.setHorizontalAlignment(Element.ALIGN_CENTER);dcount.setVerticalAlignment(Element.ALIGN_MIDDLE);dcount.setBorderColor(lightGrey);
                    dtotal.setFixedHeight(30);dtotal.setHorizontalAlignment(Element.ALIGN_CENTER);dtotal.setVerticalAlignment(Element.ALIGN_MIDDLE);dtotal.setBorderColor(lightGrey);
                    dtype.setFixedHeight(30);dtype.setHorizontalAlignment(Element.ALIGN_CENTER);dtype.setVerticalAlignment(Element.ALIGN_MIDDLE);dtype.setBorderColor(lightGrey);

                    if(i%2 == 0){

                        dnum.setBackgroundColor(baseColor);
                        dname.setBackgroundColor(baseColor);
                        dcount.setBackgroundColor(baseColor);
                        dtotal.setBackgroundColor(baseColor);
                        dtype.setBackgroundColor(baseColor);
                    }
                    table7.addCell(dnum);
                    table7.addCell(dname);
                    table7.addCell(dcount);
                    table7.addCell(dtotal);
                    table7.addCell(dtype);
                }

                document.add(table7);
            }else if(dataList.size() >7){

                PdfPTable table8 = new PdfPTable(2);
                int width8[] = {25,75};
                table8.setWidths(width8);

                PdfPCell cellbrief10 = new PdfPCell(new Paragraph("汇交数据清单",FontChinese10));
                PdfPCell cellbrief11 = new PdfPCell(new Paragraph("见详情",FontChinese10));
                cellbrief10.setFixedHeight(30);cellbrief10.setHorizontalAlignment(Element.ALIGN_CENTER);cellbrief10.setVerticalAlignment(Element.ALIGN_MIDDLE);cellbrief10.setBorderColor(lightGrey);
                cellbrief11.setFixedHeight(30);cellbrief11.setHorizontalAlignment(Element.ALIGN_CENTER);cellbrief11.setVerticalAlignment(Element.ALIGN_MIDDLE);cellbrief11.setBorderColor(lightGrey);

                table8.addCell(cellbrief10);
                table8.addCell(cellbrief11);

                document.add(table8);
            }
            PdfPTable table4 = new PdfPTable(4);
            int width4[] = {25,25,25,25};
            table4.setWidths(width4);

            //第九行
            PdfPCell cell91 = new PdfPCell(new Paragraph("项目负责人签字",FontChinese10));
            PdfPCell cell92 = new PdfPCell(new Paragraph("项目负责人签字XXX",FontChinese10));

            //第十行
            PdfPCell cell101 = new PdfPCell(new Paragraph("项目承担单位公章",FontChinese10));
            PdfPCell cell102 = new PdfPCell(new Paragraph("项目承担单位公章XXX",FontChinese10));
            PdfPCell cell103 = new PdfPCell(new Paragraph("日期",FontChinese10));
            PdfPCell cell104 = new PdfPCell(new Paragraph("日期XXX",FontChinese10));

            cell92.setColspan(3);
            cell91.setFixedHeight(30);cell91.setHorizontalAlignment(Element.ALIGN_CENTER);cell91.setVerticalAlignment(Element.ALIGN_MIDDLE);cell91.setBorderColor(lightGrey);
            cell92.setFixedHeight(30);cell92.setHorizontalAlignment(Element.ALIGN_LEFT);cell92.setVerticalAlignment(Element.ALIGN_MIDDLE);cell92.setBorderColor(lightGrey);
            if((dataList.size()%2 == 0 && dataList.size()>0) ||dataList.size()>7){
                cell91.setBackgroundColor(baseColor);
                cell92.setBackgroundColor(baseColor);
            }

            cell101.setFixedHeight(30);cell101.setHorizontalAlignment(Element.ALIGN_CENTER);cell101.setVerticalAlignment(Element.ALIGN_MIDDLE);cell101.setBorderColor(lightGrey);
            cell102.setFixedHeight(30);cell102.setHorizontalAlignment(Element.ALIGN_LEFT);cell102.setVerticalAlignment(Element.ALIGN_MIDDLE);cell102.setBorderColor(lightGrey);
            cell103.setFixedHeight(30);cell103.setHorizontalAlignment(Element.ALIGN_CENTER);cell103.setVerticalAlignment(Element.ALIGN_MIDDLE);cell103.setBorderColor(lightGrey);
            cell104.setFixedHeight(30);cell104.setHorizontalAlignment(Element.ALIGN_LEFT);cell104.setVerticalAlignment(Element.ALIGN_MIDDLE);cell104.setBorderColor(lightGrey);
            if((dataList.size()%2 == 1 && dataList.size()<=7 )|| dataList.size() ==0 ) {
                cell101.setBackgroundColor(baseColor);
                cell102.setBackgroundColor(baseColor);
                cell103.setBackgroundColor(baseColor);
                cell104.setBackgroundColor(baseColor);
            }
            table4.addCell(cell91);
            table4.addCell(cell92);

            table4.addCell(cell101);
            table4.addCell(cell102);
            table4.addCell(cell103);
            table4.addCell(cell104);

            document.add(table4);

            PdfPTable table5 = new PdfPTable(1);
            int width5[] = {100};
            table5.setWidths(width5);

            //第十一行
            PdfPCell cell111 = new PdfPCell(new Paragraph("以下由国家人口与健康科学数据共享服务平台填写",FontChinese10));
            cell111.setFixedHeight(30);cell111.setHorizontalAlignment(Element.ALIGN_CENTER);cell111.setVerticalAlignment(Element.ALIGN_MIDDLE);cell111.setBorderColor(lightGrey);
            if((dataList.size()%2 == 0 && dataList.size()>0)||dataList.size()>7) {
                cell111.setBackgroundColor(baseColor);
            }
            table5.addCell(cell111);

            document.add(table5);

            PdfPTable table6 = new PdfPTable(4);
            int width6[] = {25,25,25,25};
            table6.setWidths(width6);

            //第十二行
            PdfPCell cell121 = new PdfPCell(new Paragraph("接收意见",FontChinese10));
            PdfPCell cell122 = new PdfPCell(new Paragraph("接收意见XXX",FontChinese10));

            //第十三行
            PdfPCell cell131 = new PdfPCell(new Paragraph("接收人",FontChinese10));
            PdfPCell cell132 = new PdfPCell(new Paragraph("接收人XXX",FontChinese10));
            PdfPCell cell133 = new PdfPCell(new Paragraph("联系电话",FontChinese10));
            PdfPCell cell134 = new PdfPCell(new Paragraph("联系电话XXX",FontChinese10));
            //第十四行
            PdfPCell cell141 = new PdfPCell(new Paragraph("中心负责人签字/盖章",FontChinese10));
            PdfPCell cell142 = new PdfPCell(new Paragraph("中心负责人签字/盖章XXX",FontChinese10));
            PdfPCell cell143 = new PdfPCell(new Paragraph("日期",FontChinese10));
            PdfPCell cell144 = new PdfPCell(new Paragraph("日期XXX",FontChinese10));

            cell122.setColspan(3);
            cell121.setFixedHeight(30);cell121.setHorizontalAlignment(Element.ALIGN_CENTER);cell121.setVerticalAlignment(Element.ALIGN_MIDDLE);cell121.setBorderColor(lightGrey);
            cell122.setFixedHeight(30);cell122.setHorizontalAlignment(Element.ALIGN_LEFT);cell122.setVerticalAlignment(Element.ALIGN_MIDDLE);cell122.setBorderColor(lightGrey);
            if((dataList.size()%2 == 1 && dataList.size() <= 7) || dataList.size() ==0) {
                cell121.setBackgroundColor(baseColor);
                cell122.setBackgroundColor(baseColor);
            }

            cell131.setFixedHeight(30);cell131.setHorizontalAlignment(Element.ALIGN_CENTER);cell131.setVerticalAlignment(Element.ALIGN_MIDDLE);cell131.setBorderColor(lightGrey);
            cell132.setFixedHeight(30);cell132.setHorizontalAlignment(Element.ALIGN_LEFT);cell132.setVerticalAlignment(Element.ALIGN_MIDDLE);cell132.setBorderColor(lightGrey);
            cell133.setFixedHeight(30);cell133.setHorizontalAlignment(Element.ALIGN_CENTER);cell133.setVerticalAlignment(Element.ALIGN_MIDDLE);cell133.setBorderColor(lightGrey);
            cell134.setFixedHeight(30);cell134.setHorizontalAlignment(Element.ALIGN_LEFT);cell134.setVerticalAlignment(Element.ALIGN_MIDDLE);cell134.setBorderColor(lightGrey);
            if((dataList.size()%2 == 0 && dataList.size()>0) || dataList.size() > 7) {
                cell131.setBackgroundColor(baseColor);
                cell132.setBackgroundColor(baseColor);
                cell133.setBackgroundColor(baseColor);
                cell134.setBackgroundColor(baseColor);
            }

            cell141.setFixedHeight(30);cell141.setHorizontalAlignment(Element.ALIGN_CENTER);cell141.setVerticalAlignment(Element.ALIGN_MIDDLE);cell141.setBorderColor(lightGrey);
            cell142.setFixedHeight(30);cell142.setHorizontalAlignment(Element.ALIGN_LEFT);cell142.setVerticalAlignment(Element.ALIGN_MIDDLE);cell142.setBorderColor(lightGrey);
            cell143.setFixedHeight(30);cell143.setHorizontalAlignment(Element.ALIGN_CENTER);cell143.setVerticalAlignment(Element.ALIGN_MIDDLE);cell143.setBorderColor(lightGrey);
            cell144.setFixedHeight(30);cell144.setHorizontalAlignment(Element.ALIGN_LEFT);cell144.setVerticalAlignment(Element.ALIGN_MIDDLE);cell144.setBorderColor(lightGrey);
            if((dataList.size()%2 == 1 && dataList.size() <= 7) || dataList.size() ==0) {
                cell141.setBackgroundColor(baseColor);
                cell142.setBackgroundColor(baseColor);
                cell143.setBackgroundColor(baseColor);
                cell144.setBackgroundColor(baseColor);
            }

            table6.addCell(cell121);
            table6.addCell(cell122);
            table6.addCell(cell131);
            table6.addCell(cell132);
            table6.addCell(cell133);
            table6.addCell(cell134);
            table6.addCell(cell141);
            table6.addCell(cell142);
            table6.addCell(cell143);
            table6.addCell(cell144);

            document.add(table6);

            Paragraph buttomConent = new Paragraph(18f, "注：  本凭证是汇交人履行汇交义务的证明，也是汇交人维护合法权益的凭证，请妥善保管。", FontChinese10);
            buttomConent.setIndentationLeft(50);
            buttomConent.setIndentationRight(47);
            document.add(buttomConent);

            Paragraph buttomConent2 = new Paragraph(18f, "本表一式两份。汇交方、接收方各执一份。", FontChinese10);
            buttomConent2.setIndentationLeft(73);
            document.add(buttomConent2);

            if(dataList != null && dataList.size() > 0 && dataList.size() > 7) {

                document.newPage();
                Paragraph topConent = new Paragraph(18f, "详情表", new Font(bfChinese, 12, Font.BOLD));
                topConent.setAlignment(Element.ALIGN_CENTER);

                document.add(topConent);
                document.add(new Paragraph(18f, " ", FontChinese10));

                for (int j = 0; j < (dataList.size()%23 == 0 ? dataList.size() / 23 : dataList.size() / 23+1); j++) {


                    if (j > 0) {
                        document.newPage();
                    }
                    PdfPTable table7 = new PdfPTable(5);
                    int width7[] = {15, 25, 15, 15, 15};
                    table7.setWidths(width7);

                    // PdfPCell cellh11 = new PdfPCell(new Paragraph("汇交数据清单", FontChinese10));
                    PdfPCell cellh12 = new PdfPCell(new Paragraph("序号", FontChinese10));
                    PdfPCell cellh13 = new PdfPCell(new Paragraph("数据名称", FontChinese10));
                    PdfPCell cellh14 = new PdfPCell(new Paragraph("数据量(MB)", FontChinese10));
                    PdfPCell cellh15 = new PdfPCell(new Paragraph("记录数(条)", FontChinese10));
                    PdfPCell cellh16 = new PdfPCell(new Paragraph("数据格式", FontChinese10));

                   /* if(j == (dataList.size()/23)){
                        cellh11.setRowspan(dataList.size()%23 + 1);
                        cellh11.setFixedHeight((dataList.size()%23 + 1) * 30);
                    }else{
                        cellh11.setRowspan(23 + 1);
                        cellh11.setFixedHeight((23 + 1) * 30);
                    }
                    cellh11.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellh11.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cellh11.setBorderColor(lightGrey);*/
                    cellh12.setFixedHeight(30);
                    cellh12.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellh12.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cellh12.setBorderColor(lightGrey);
                    cellh13.setFixedHeight(30);
                    cellh13.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellh13.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cellh13.setBorderColor(lightGrey);
                    cellh14.setFixedHeight(30);
                    cellh14.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellh14.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cellh14.setBorderColor(lightGrey);
                    cellh15.setFixedHeight(30);
                    cellh15.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellh15.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cellh15.setBorderColor(lightGrey);
                    cellh16.setFixedHeight(30);
                    cellh16.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellh16.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cellh16.setBorderColor(lightGrey);

                    //table7.addCell(cellh11);
                    table7.addCell(cellh12);
                    table7.addCell(cellh13);
                    table7.addCell(cellh14);
                    table7.addCell(cellh15);
                    table7.addCell(cellh16);

                    int k = j*23 +23;
                    if(k > dataList.size()){
                        k = dataList.size();
                    }
                    for (int i = j*23; i < k; i++) {

                        PdfPCell dnum = new PdfPCell(new Paragraph(dataList.get(i).get("dnum"), FontChinese10));
                        PdfPCell dname = new PdfPCell(new Paragraph(dataList.get(i).get("dname"), FontChinese10));
                        PdfPCell dcount = new PdfPCell(new Paragraph(dataList.get(i).get("dcount"), FontChinese10));
                        PdfPCell dtotal = new PdfPCell(new Paragraph(dataList.get(i).get("dtotal"), FontChinese10));
                        PdfPCell dtype = new PdfPCell(new Paragraph(dataList.get(i).get("dtype"), FontChinese10));

                        dnum.setFixedHeight(30);
                        dnum.setHorizontalAlignment(Element.ALIGN_CENTER);
                        dnum.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        dnum.setBorderColor(lightGrey);
                        dname.setFixedHeight(30);
                        dname.setHorizontalAlignment(Element.ALIGN_CENTER);
                        dname.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        dname.setBorderColor(lightGrey);
                        dcount.setFixedHeight(30);
                        dcount.setHorizontalAlignment(Element.ALIGN_CENTER);
                        dcount.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        dcount.setBorderColor(lightGrey);
                        dtotal.setFixedHeight(30);
                        dtotal.setHorizontalAlignment(Element.ALIGN_CENTER);
                        dtotal.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        dtotal.setBorderColor(lightGrey);
                        dtype.setFixedHeight(30);
                        dtype.setHorizontalAlignment(Element.ALIGN_CENTER);
                        dtype.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        dtype.setBorderColor(lightGrey);

                        //if (i % 2 == 0) {
                        if ((j+1 +i) % 2 == 1) {

                            dnum.setBackgroundColor(baseColor);
                            dname.setBackgroundColor(baseColor);
                            dcount.setBackgroundColor(baseColor);
                            dtotal.setBackgroundColor(baseColor);
                            dtype.setBackgroundColor(baseColor);
                        }
                        table7.addCell(dnum);
                        table7.addCell(dname);
                        table7.addCell(dcount);
                        table7.addCell(dtotal);
                        table7.addCell(dtype);

                    }
                    document.add(table7);
                }

            }

            document.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static void setFooter(PdfWriter writer, BaseFont bf, int presentFontSize, Rectangle pageSize){
        ItextPdfDemo headerFooter = new ItextPdfDemo(bf,presentFontSize,pageSize);
        writer.setPageEvent(headerFooter);
    }

    /**
     *
     * 无参构造方法.
     *
     */
    public ItextPdfDemo() {

    }

    /**
     *
     * @param bf
     *         基础字体大小
     * @param presentFontSize
     *         数据字体大小
     * @param pageSize
     *         文档格式
     */
    public ItextPdfDemo(BaseFont bf, int presentFontSize, Rectangle pageSize) {
        this.bf = bf;
        this.presentFontSize = presentFontSize;
        this.pageSize = pageSize;
    }

    /**
     *
     *  构造方法.
     *
     * @param yeMei
     *            页眉字符串
     * @param presentFontSize
     *            数据体字体大小
     * @param pageSize
     *            页面文档大小，A4，A5，A6横转翻转等Rectangle对象
     */
    public ItextPdfDemo(String yeMei, int presentFontSize, Rectangle pageSize) {
        this.header = yeMei;
        this.presentFontSize = presentFontSize;
        this.pageSize = pageSize;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setPresentFontSize(int presentFontSize) {
        this.presentFontSize = presentFontSize;
    }

    /**
     *
     * TODO 文档打开时创建模板
     *
     */
    public void onOpenDocument(PdfWriter writer, Document document) {
        total = writer.getDirectContent().createTemplate(50, 50);// 共 页 的矩形的长宽高
    }

    /**
     * TODO 关闭每页的时候，写入页眉，写入'第几页共'这几个字。
     */
    public void onEndPage(PdfWriter writer, Document document) {
        try {
            if (fontDetail == null) {
                fontDetail = new Font(bf, presentFontSize, Font.NORMAL);// 数据体字体
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 1.写入页眉
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, new Phrase(header, fontDetail),document.left(), document.top() + 20, 0);

        // 2.写入前半部分的 第 X页/共
        int pageS = writer.getPageNumber();
        String foot1 = "第 " + pageS + " 页/共";
        Phrase footer = new Phrase(foot1, fontDetail);

        // 3.计算前半部分的foot1的长度，后面好定位最后一部分的'Y页'这俩字的x轴坐标，字体长度也要计算进去 = len
        float len = bf.getWidthPoint(foot1, presentFontSize);

        // 4.拿到当前的PdfContentByte
        PdfContentByte cb = writer.getDirectContent();

        // 5.写入页脚1，x轴就是(右margin+左margin + right() -left()- len)/2.0F
        // ,y轴就是底边界-20,否则就贴边重叠到数据体里了就不是页脚了；注意Y轴是从下往上累加的，最上方的Top值是大于Bottom好几百开外的。
        ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, footer,(document.rightMargin() + document.right() + document.leftMargin() - document.left() - len) / 2.0F + 20F,document.bottom() - 20, 0);

        // 6.写入页脚2的模板（就是页脚的Y页这俩字）添加到文档中，计算模板的和Y轴,X=(右边界-左边界 - 前半部分的len值)/2.0F +
        // len ， y 轴和之前的保持一致，底边界-20
        cb.addTemplate(total,(document.rightMargin() + document.right() + document.leftMargin() - document.left()) / 2.0F + 20F,document.bottom() - 20);
        // 调节模版显示的位置
    }

    /**
     *
     * TODO 关闭文档时，替换模板，完成整个页眉页脚组件
     *
     */
    public void onCloseDocument(PdfWriter writer, Document document) {
        // 7.最后一步了，就是关闭文档的时候，将模板替换成实际的 Y 值,至此，page x of y 制作完毕，完美兼容各种文档size。
        total.beginText();
        total.setFontAndSize(bf, presentFontSize);// 生成的模版的字体、颜色
        String foot2 = " " + (writer.getPageNumber()) + " 页";
        total.showText(foot2);// 模版显示的内容
        total.endText();
        total.closePath();
    }
    private static List<HashMap<String, String>> getData() {

        List<HashMap<String,String>> dataList = new ArrayList<HashMap<String,String>>();

        for(int i=0;i<75;i++){

            HashMap<String,String> hashMap = new HashMap<String,String>();
            hashMap.put("dnum",String.valueOf(i+1));
            hashMap.put("dname","数据名称"+(i+1));
            hashMap.put("dcount","数据量"+(i+1));
            hashMap.put("dtotal","记录数"+(i+1));
            hashMap.put("dtype","数据格式"+(i+1));

            dataList.add(hashMap);
        }

        return dataList;
    }
}
