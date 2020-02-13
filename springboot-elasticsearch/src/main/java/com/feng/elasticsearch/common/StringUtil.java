package com.feng.elasticsearch.common;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 字符串工具类
 * @author Feng
 * create 2018/08/18
 */
public class StringUtil {

    /**
     * 去除空格(若为null或"null"则替换为"")
     * @param src
     * @return
     * 如果为null，则返回""
     */
    public static String trim(String src) {
        return trim(src,"");
    }

    /**
     * 字符串去除(左右)空格,如果为null则返回默认值
     * @param src
     * @param def
     * @return
     */
    public static String trim(String src,String def) {
        src = src == null ? def : src.trim();
        return src.toLowerCase().equals("null") ? "" : src;
    }

    /**
     * Object转换为String,去除空格
     * @param object
     * @return
     */
    public static String trimObject(Object object) {
        return trim(String.valueOf(object));
    }

    /**
     * 如果对象为"null"、null，则替换为目标字符串
     * @param object
     * @param def
     * @return
     */
    public static String trimObject(Object object,String def) {
        return trim(trimObject(object),def);
    }

    /**
     * 获得随机唯一字符串
     * @return
     */
    public static String getRandomUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 返回结果集
     * @param code 状态编码
     * @param msg 异常消息
     * @param data 数据
     * @return {Map&lt;String,Object&gt;}
     */
    public static Map<String,Object> getResultVo(int code, String msg, Object data) {
        Map<String,Object> vo = new HashMap<String,Object>();
        vo.put(Constants.KEY_CODE,code);
        vo.put(Constants.KEY_MSG,msg);
        vo.put(Constants.KEY_DATA,data);
        return vo;
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
        key = StringUtil.trim(key);
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

}
