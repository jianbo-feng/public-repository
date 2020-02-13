package com.feng.pdf.controller;

import com.feng.pdf.common.Constants;
import com.feng.pdf.util.FileUtil;
import com.feng.pdf.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {

    /**
     * (以流的方式)显示pdf文件
     * @param request
     * @param response
     * @param session
     */
    @RequestMapping("/dl/pdf")
    public void dlPdf(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        response.setContentType("application/pdf");
        FileInputStream in = null;
        OutputStream out = null;
        try {
            File file = FileUtil.createFile("/Users/baby/work/test", "test-01.pdf");
//            in = new FileInputStream(new File(request.getParameter("path")));
            in = new FileInputStream(file);
            out = response.getOutputStream();
            byte[] b = new byte[1024];
            while((in.read(b)) != -1) {
                out.write(b);
            }
            out.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (null != in) {
                try {
                    in.close();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (null != out) {
                try {
                    out.close();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 根据url链接下载
     * @param request 请求对象
     * @param response 响应对象
     */
    @RequestMapping("/dl/url")
    public void dlUrl(HttpServletRequest request, HttpServletResponse response) {
        String fileName = "";
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            String url = request.getParameter("url");
            System.err.println("url:" + url);
            url = StringUtil.trim(url);

            if (!"".equals(url)) {

                fileName = url.substring(url.lastIndexOf("/") + 1);

                // PDF文件特殊处理
                if (url.toLowerCase(Constants.LOCALE_DEFAULT).endsWith(".pdf")) {

                }
            }
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);
            connection.setRequestMethod("GET");


            byte[] buff = new byte[1024];
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=utf-8");
//            response.setContentType("multipart/form-data");
            // 设置被下载而不是被打开
            response.setContentType("application/gorce-download");
            // 设置被第三方工具打开,设置下载的文件名
            response.setHeader("Content-Disposition", "attachment; fileName=" +  fileName + ";filename*=utf-8''" + URLEncoder.encode(fileName,"UTF-8"));

            os = response.getOutputStream();
            bis = new BufferedInputStream(connection.getInputStream());

            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff,0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        }
        catch(IOException ioe) {
            //ioe.printStackTrace();
            log.error(ioe.getMessage());
        }
        finally {
            if (os != null) {
                try {
                    os.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(bis != null) {
                try {
                    bis.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @RequestMapping("/dl/file/{path}")
    public void dl(HttpServletResponse response, @PathVariable String path) {
        String fileName = "test-01.pdf";
        String filePath = "/Users/baby/work/test";
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {

            System.err.println("path:" + path);

            path = "".equals(path) ? fileName : path;

            File file = FileUtil.createFile(filePath, path);
            byte[] buff = new byte[1024];
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=utf-8");
//            response.setContentType("multipart/form-data");
            // 设置被下载而不是被打开
            response.setContentType("application/gorce-download");
            // 设置被第三方工具打开,设置下载的文件名
            response.setHeader("Content-Disposition", "attachment; fileName=" +  path + ";filename*=utf-8''" + URLEncoder.encode(path,"UTF-8"));

            os = response.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(file));

            //方式一
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff,0,buff.length);
                os.flush();
                i = bis.read(buff);
            }

            //方式二
//            int len = 0;
//            while ((len = bis.read(buff)) != -1) {
//                os.write(buff,0,len);
//            }
        }
        catch(IOException ioe) {
            //ioe.printStackTrace();
            log.error(ioe.getMessage());
        }
        finally {
            if(bis != null) {
                try {
                    bis.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
