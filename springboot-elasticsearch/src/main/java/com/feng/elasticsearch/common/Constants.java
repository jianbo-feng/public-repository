package com.feng.elasticsearch.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局常量
 * @author Feng
 * create 2018/08/23
 */
public class Constants {

    /**
     * SPRING_SECURITY框架的Session Key
     * <br>"SPRING_SECURITY_CONTEXT"</br>
     */
    public final static String SPRING_SECURITY_SESSION_KEY = "SPRING_SECURITY_CONTEXT";

    /**
     * 信息返回数据中key：code
     */
    public final static String KEY_CODE = "code";

    /**
     * 信息返回数据中key：msg
     */
    public final static String KEY_MSG = "msg";

    /**
     * 信息返回数据中key：data
     */
    public final static String KEY_DATA = "data";

    /**
     * 字符串标识-正常：'NORMAL'
     */
    public final static String STR_NORMAL = "NORMAL";

    /**
     * 字符串标识-删除：'DELETE'
     */
    public final static String STR_DELETE = "DELETE";

    /**
     * 字符串标识-冻结：'FREEZE'
     */
    public final static String STR_FREEZE = "FREEZE";

    /**
     * 字符串标识-成功：'SUCCESS'
     */
    public final static String STR_SUCCESS = "SUCCESS";

    /**
     * 字符串标识-失败：'FAILURE'
     */
    public final static String STR_FAILURE = "FAILURE";

    /**
     * 数字标识-正常：1
     */
    public final static int INT_NORMAL = 1;

    /**
     * 数字标识-删除：0
     */
    public final static int INT_DEL = 0;

    /**
     * 数字标识-冻结：-1
     */
    public final static int INT_FREEZE = -1;

    /**
     * 字符串：true （'T'）
     */
    public final static String STR_TRUE = "T";

    /**
     * 字符串：false （'F'）
     */
    public final static String STR_FALSE = "F";

    /**
     * 布尔值: TRUE (true)
     */
    public final static boolean TRUE = true;

    /**
     * 布尔值: FALSE (true)
     */
    public final static boolean FALSE = false;

    /**
     * 成功: SUCCESS (true)
     */
    public final static boolean SUCCESS = true;

    /**
     * 成功: FAILURE (false)
     */
    public final static boolean FAILURE = false;

    /** 图片格式 */
    public final static Map<String,String> PIC_FORMATS = new HashMap<String,String>(){{
        put("bmp",".bmp");put("jpg",".jpg");put("png",".png");put("tiff",".tiff");
        put("gif",".gif");put("pcx",".pcx");put("tga",".tga");put("exif",".exif");
        put("cdr",".cdr");put("dxf",".dxf");put("ufo",".ufo");put("eps",".eps");
        put("ai",".ai");put("raw",".raw");put("wmf",".wmf");put("webp",".webp");
        put("svg",".svg");put("psd",".psd");put("fpx",".fpx");put("jpeg",".jpeg");
    }};
}
