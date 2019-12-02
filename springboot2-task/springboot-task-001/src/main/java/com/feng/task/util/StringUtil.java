package com.feng.task.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;


/**
 * 字符串工具类
 * @author Feng
 * @date 2018/08/18
 */
public class StringUtil {

    private StringUtil() {

    }

    static Logger logger = LoggerFactory.getLogger(StringUtil.class);

    /**
     * (默认)随机(生成的)字符串长度为6
     */
    public static int RANDOM_STRING_LENGTH = 6;

    /**
     * (基础)字符串:"a-zA-Z0-9"
     */
    public static String BASE_STRING = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    /**
     * 编码随机字符串:"A-Z0-9"
     */
    public static String BASE_CODE_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";


    /**
     * 获得随机唯一字符串
     * @return
     */
    public static String getRandomUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 根据字符串Key从Map中获得值
     * @param data 若为null，则返回null
     * @param key 若为null或""，null
     * @return
     */
    public static Object getValByStrKey(Map<String,Object> data,String key) {
        return getValueByStrKey(data,key,null);
    }

    /**
     * 根据字符串Key从Map中获得值
     * @param data 若为null，则返回默认值
     * @param key 若为null或""，则返回默认值
     * @param def 若无法获取值，则返回默认值
     * @return
     */
    public static Object getValueByStrKey(Map<String,Object> data,String key,Object def) {
        key = trim(key);
        return getValByKey(data,key,def);
    }

    /**
     * 根据Key从Map中获得值
     * @param data 若为null，则返回默认值
     * @param key 若为null，则返回默认值
     * @param def 若无法获取值，则返回默认值
     * @return
     */
    public static Object getValByKey(Map<String,Object> data,Object key,Object def) {
        if(data == null || key == null) {
            return def;
        }
        else if(data.containsKey(key)) {
            Object value = data.get(key);
            return value == null ? def : value;
        }
        else {
            return def;
        }
    }


    /**
     * 字符串转数字
     * @param str 源字符串
     * @return integer
     */
    public static Integer strToInt(String str) {
        return strToInt(str,null);
    }

    /**
     * 字符串转数字
     * @param str 源字符串
     * @param defult 默认返回值
     * @return 若字符串转换Integer数字异常，则返回默认数字
     */
    public static Integer strToInt(String str, Integer defult) {
        if (!("".equals(str) || str == null)) {
            try {
                defult = Integer.valueOf(str.trim());
            }
            catch(NumberFormatException nfe) {
                nfe.printStackTrace();
                logger.debug(nfe.getMessage(), nfe);
            }
        }
        return defult;
    }

    /**
     * 字符串转数字
     * @param str 源字符串
     * @return integer
     */
    public static Long strToLong(String str) {
        return strToLong(str,null);
    }

    /**
     * 字符串转Long型数字
     * @param str 源字符串
     * @param defult 默认返回值
     * @return 若字符串转换Long型数字异常，则返回默认数字
     */
    public static Long strToLong(String str, Long defult) {
        if (!("".equals(str) || str == null)) {
            try {
                defult = Long.valueOf(str.trim());
            }
            catch(NumberFormatException nfe) {
                nfe.printStackTrace();
                logger.debug(nfe.getMessage(), nfe);
            }
        }
        return defult;
    }

    /**
     * object转换为Integer
     * @param object 源对象
     * @return
     */
    public static Integer objToInteger(Object object){
        Integer integer = null;
        if(null != object) {
            integer = strToInt(object.toString());
        }
        return integer;
    }

    /**
     * object转换为Integer
     * @param object 源对象
     * @return
     */
    public static Integer objToInteger(Object object, Integer defult){
        if(null != object) {
            defult = strToInt(object.toString());
        }
        return defult;
    }

    /**
     * object转换为Double
     * @param object 源对象
     * @return
     */
    public static Double objToDouble(Object object){
        Double dou = null;
        if(null != object) {
            dou = strToDouble(object.toString());
        }
        return dou;
    }

    /**
     * object转换为Double
     * @param object 源对象
     * @return
     */
    public static Double objToDouble(Object object, Double defult){
        if(null != object) {
            defult = strToDouble(object.toString());
        }
        return defult;
    }


    /**
     * String转换为Boolean
     * @param src
     * @return 仅当src为"true"时则返回true(对象Boolean)，{src不区分大小写}
     */
    public static Boolean strToBoolean(String src) {
        return Boolean.valueOf(src);
    }

    /**
     * String转换为boolean
     * @param src
     * @return 仅当src为"true"时则返回true(对象Boolean)，{src不区分大小写}
     */
    public static boolean strToBool(String src) {
        return Boolean.parseBoolean(src);
    }

    /**
     * String转为Double
     * @param src 字符串
     * @return 若字符串转换Double型数字异常，则返回null
     */
    public static Double strToDouble(String src) {
        return strToDouble(src,null);
    }

    /**
     * String转为Double
     * @param src 字符串
     * @param defult 默认值
     * @return 若字符串转换Double型数字异常，则返回defult
     */
    public static Double strToDouble(String src,Double defult) {
        if (!("".equals(src) || src == null)) {
            try {
                defult = Double.valueOf(src.trim());
            }
            catch(NumberFormatException nfe) {
                nfe.printStackTrace();
                logger.debug(nfe.getMessage(), nfe);
            }
        }
        return defult;
    }

    /**
     * String转为Float
     * @param src 字符串
     * @return 若字符串转换Float型数字异常，则返回null
     */
    public static Float strToFloat(String src) {
        return strToFloat(src,null);
    }

    /**
     * String转为Float
     * @param src 字符串
     * @param defult 默认值
     * @return 若字符串转换Float型数字异常，则返回defult
     */
    public static Float strToFloat(String src,Float defult) {
        if (!("".equals(src) || src == null)) {
            try {
                defult = Float.valueOf(src.trim());
            }
            catch(NumberFormatException nfe) {
                nfe.printStackTrace();
                logger.debug(nfe.getMessage(), nfe);
            }
        }
        return defult;
    }

    /**
     * String转为Short
     * @param src 字符串
     * @return 若字符串转换Short型数字异常，则返回null
     */
    public static Short strToShort(String src) {
        return strToShort(src,null);
    }

    /**
     * String转为Short
     * @param src 字符串
     * @param defult 默认值
     * @return 若字符串转换Short型数字异常，则返回defult
     */
    public static Short strToShort(String src,Short defult) {
        if (!("".equals(src) || src == null)) {
            try {
                defult = Short.valueOf(src.trim());
            }
            catch(NumberFormatException nfe) {
                nfe.printStackTrace();
                logger.debug(nfe.getMessage(), nfe);
            }
        }
        return defult;
    }

    /**
     * String转为Byte
     * @param src 字符串
     * @return 若字符串转换Byte型数字异常，则返回null
     */
    public static Byte strToByte(String src) {
        return strToByte(src,null);
    }

    /**
     * String转为Byte
     * @param src 字符串
     * @param defult 默认值
     * @return 若字符串转换Byte型数字异常，则返回defult
     */
    public static Byte strToByte(String src,Byte defult) {
        if (!("".equals(src) || src == null)) {
            try {
                defult = Byte.valueOf(src.trim());
            }
            catch(NumberFormatException nfe) {
                nfe.printStackTrace();
                logger.debug(nfe.getMessage(), nfe);
            }
        }
        return defult;
    }

    /**
     * 随机字符串
     * @param baseString 基础字符串(如果为null或空，则使用的默认的"a-zA-Z0-9")
     * @param length 表示生成字符串的长度(<b>length必须大于0,否则默认为1</b>)
     * @return
     */
    public static String randomString(String baseString,int length) {
        length = length <= 0 ? 1 : length;
        baseString = trim(baseString);
        int len = length;
        baseString = "".equals(baseString) ? BASE_STRING : baseString;
        if(baseString.length() < length) {
            len = baseString.length();
        }
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < len; i++) {
            int number = random.nextInt(baseString.length());
            sb.append(baseString.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 随机字符串
     * @param length 表示生成字符串的长度(<b>length必须大于0,否则默认为1</b>)
     * @return
     */
    public static String randomString(int length) {
        return randomString(null,length);
    }

    /**
     * 获得默认随机字符串，长度为6
     * @return
     */
    public static String getDefRandomString() {
        return randomString(null,RANDOM_STRING_LENGTH);
    }

    /**
     * 获得默认随机编码字符串，长度为6
     * @return
     */
    public static String getDefRandomCodeString() {
        return randomString(BASE_CODE_STRING,RANDOM_STRING_LENGTH);
    }

    /**
     * 获得子字符串
     * @param src 源字符串
     * @param splitLen 截取长度
     * @return
     */
    public static String subString(String src,int splitLen) {
        String dest = null;
        if(src != null && splitLen > 0) {
            int len = src.length();
            if(len > splitLen) {
                dest = src.substring(0, splitLen);
            }
            else {
                dest = src;
            }
        }
        return dest;
    }

    /**
     * 去除空格(若为null或"null"则替换为"")
     * @param src 源字符串
     * @return
     * 如果为null，则返回""
     */
    public static String trim(String src) {
        return trim(src, "");
    }

    /**
     * 字符串去除(左右)空格,如果为空字符串或null则返回默认值
     * @param src 源字符串
     * @param def 默认字符串
     * @return
     */
    public static String trim(String src, String def) {
        src = src == null ? def : src.trim();
        return ("null".equals(src.toLowerCase(Locale.getDefault())) || "".equals(src)) ? def : src;
    }

    /**
     * Object转换为String,去除空格
     * @param object 源对象
     * @return
     */
    public static String trimObject(Object object) {
        return trim(String.valueOf(object));
    }

    /**
     * 如果对象为null，则替换为目标对象
     * @param object 源对象
     * @param def 默认对象
     * @return
     */
    public static Object trimObject(Object object, Object def) {
        return object == null ? def : object;
    }

    /**
     * 如果对象为"null"、null，则替换为目标字符串
     * @param object 源对象
     * @param def 默认字符串
     * @return
     */
    public static String trimObject(Object object, String def) {
        return trim(trimObject(object), def);
    }

    /**
     * 根据keys从map中获得值
     * @param map 图信息
     * @param keys 键组(如果第一个键获取不到值，则使用下一个键)
     * @return
     */
    public static Object getValByMapKey(Map<Object,Object> map, Object... keys) {
        Object ret = null;
        if(map != null && !map.isEmpty() && keys != null && keys.length > 0) {
            for(Object key : keys) {
                if(map.containsKey(key)) {
                    ret = map.get(key);
                    if(ret != null) {
                        break;
                    }
                }
            }
        }
        return ret;
    }

    /**
     * 根据keys从map中获得值(若不能获取值或获取值为null，则用默认值替换)
     * @param map 图信息
     * @param def 默认值
     * @param keys 键组(如果第一个键获取不到值，则使用下一个键)
     * @return
     */
    public static Object getValByMapKey(Map<Object,Object> map, Object def, Object... keys) {
        Object ret = getValByMapKey(map, keys);
        return ret == null ? def : ret;
    }

    /**
     * 根据keys从map中获得值
     * @param map 图信息
     * @param keys 键组(如果第一个键获取不到值，则使用下一个键)
     * @return
     */
    public static Object getValByMapKey(Map<String,Object> map, String... keys) {
        return getValByMapKey(map,keys);
    }

    /**
     * 根据keys从map中获得值(若不能获取值或获取值为null，则用默认值替换)
     * @param map 图信息
     * @param def 默认值
     * @param keys 键组(如果第一个键获取不到值，则使用下一个键)
     * @return
     */
    public static Object getValByMapKey(Map<String,Object> map, Object def, String... keys) {
        return getValByMapKey(map,def,keys);
    }

    /**
     * 根据key从map中获得值(若不能获取值或获取值为null，则用默认值替换)
     * @param map 图信息
     * @param key 键
     * @param def 默认值
     */
    public static Object getMapValueByKey(Map<Object, Object> map, Object key, Object def) {
        Object value = null;
        if (map != null) {
            value = map.getOrDefault(key, def);
        }
        return value == null ? def : value;
    }
}
