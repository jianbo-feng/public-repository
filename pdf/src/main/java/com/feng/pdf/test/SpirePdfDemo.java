package com.feng.pdf.test;

import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfPageBase;
import com.spire.pdf.general.find.PdfTextFind;

import java.awt.*;
import java.io.File;

/**
 * Spire螺旋
 * 解析PDF示例
 */
public class SpirePdfDemo {

    public static void main(String... args) throws Exception {
        // 加载示例PDF文档
        String path = "/Users/baby/work/test";
        String srcFile = "elasticsearch 集群以及springboot配置.pdf", newFile = "spire-test.pdf";
        String key = "node";
        PdfDocument pdf = new PdfDocument();
        pdf.loadFromFile(path + File.separator + srcFile);
        PdfTextFind[] result = null;

        // 遍历文档每一页
        for(int i = 0; i < pdf.getPages().getCount(); i ++) {
            // 获取特定页
            PdfPageBase page = pdf.getPages().get(i);
            result = page.findText(key).getFinds();
            for (PdfTextFind find : result) {
                // 高亮显示查询结果
                find.applyHighLight(Color.yellow);
                double x = find.getPosition().getX();
                double y = find.getPosition().getY();
                System.err.println("x:" + x + ", y = " + y);
            }

            // 保存文档
            pdf.saveToFile(path + File.separator + newFile);
            pdf.close();
        }
    }

}
