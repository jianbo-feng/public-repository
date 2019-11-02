package com.feng.elasticsearch.common;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 时间工具类
 * @author Feng
 * create 2018/08/23
 */
public class DateUtil {

    /**yyyy-MM-dd HH:mm:ss*/
    public static final SimpleDateFormat FORMAT_DATE_TIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**yyyy-MM-dd*/
    public static final SimpleDateFormat FORMAT_DATE = new SimpleDateFormat("yyyy-MM-dd");

    /**HH:mm:ss*/
    public static final SimpleDateFormat FORMAT_TIME = new SimpleDateFormat("HH:mm:ss");

    /**yyyy-MM*/
    public static final SimpleDateFormat FORMAT_MONTH = new SimpleDateFormat("yyyy-MM");

    /**yyyyMMddHHmmss*/
    public static final SimpleDateFormat File_Name = new SimpleDateFormat("yyyyMMddHHmmss");

    /**yyyyMMdd*/
    public static final SimpleDateFormat FORMAT_DATE1 = new SimpleDateFormat("yyyyMMdd");

    /**yyyyMM*/
    public static final SimpleDateFormat FORMAT_DATE2 = new SimpleDateFormat("yyyyMM");

    /**dd*/
    public static final SimpleDateFormat FORMAT_DATE3 = new SimpleDateFormat("dd");

    /**yyyy*/
    public static final SimpleDateFormat FORMAT_DATE4 = new SimpleDateFormat("yyyy");

    /**MM*/
    public static final SimpleDateFormat FORMAT_DATE5 = new SimpleDateFormat("MM");

    public static String date2Str(Date date, SimpleDateFormat dateFmt) {
        if (date == null)
            return "";
        return new SimpleDateFormat(dateFmt.toPattern()).format(date);
    }

    public static Date str2Date(String dateStr, SimpleDateFormat dateFmt) {
        if (dateStr != null && dateFmt != null) {
            try {
                return new SimpleDateFormat(dateFmt.toPattern()).parse(dateStr);
            } catch (Exception e) {

            }
        }
        return null;
    }

    public static String getHandleDate() {
        Calendar cl = Calendar.getInstance();
        cl.add(Calendar.DAY_OF_MONTH, -1);
        String dt = date2Str(cl.getTime(), FORMAT_DATE_TIME);

        return dt;

    }

    /** 日期转字符串，采用“yyyy-MM-dd”的表示形式 */
    public static String getShortDate(Date nDate) {
        if (nDate == null)
            return "";
        return new SimpleDateFormat(FORMAT_DATE.toPattern()).format(nDate);
    }

    // 获取月份的第一天
    public static Date computeFirstDayOfMonth(Date day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(day);
        // 月份的第一天.
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /** 获取月份的最后一天**/
    public static Date computeLastDayOfMonth(Date day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(day);
        // 月份的最后一天.
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int td = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, td - 1);
        //		calendar.set(calendar.MONTH, +1);
        //		calendar.set(Calendar.DAY_OF_MONTH, -);
        return calendar.getTime();
    }

    // 获取一个日期的后面N天的日期
    public static Date getDay(Date d, int n) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        int td = c.get(Calendar.DAY_OF_MONTH);
        c.set(Calendar.DAY_OF_MONTH, td + n);
        Date towDay = c.getTime();
        return towDay;
    }

    // 获取日期前N天的Date日期
    public static Date getPreNDay(Date d, int n) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        int td = c.get(Calendar.DAY_OF_MONTH);
        c.set(Calendar.DAY_OF_MONTH, td - n);
        Date towDay = c.getTime();
        return towDay;
    }

    /** 获取前多少天的时间,格式由FMT决定**/
    public static String getPastDateStr(int day) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -day);
        return FORMAT_DATE1.format(cal.getTime());
    }

    /**获取指定月份的，前（或后）几个月的月份*/
    public static String getBeforeOrAfterMonthStr(String month, int beforeOrAfterNum) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Date date = sdf.parse(month + "01");
            Date nextDate = getBeforeOrAfterMonth(date, beforeOrAfterNum);
            return sdf.format(nextDate).substring(0, 6);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /** 获取前多少月的时间,格式由FMT决定**/
    public static String getPastMonthStr(int month) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -month);
        return FORMAT_DATE2.format(cal.getTime());
    }

    /** 获取前多少月的时间,格式由FMT决定**/
    public static String getPastMonthStr(String pointMonth, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Integer.parseInt(pointMonth.substring(0, 4)), Integer.parseInt(pointMonth.substring(4, 6)) - 1, 1);
        cal.add(Calendar.MONTH, -month);
        return FORMAT_DATE2.format(cal.getTime());
    }

    public static String getDateStr(Date date) {
        return FORMAT_DATE1.format(date);
    }

    /**
     * 获得之前或之后多少小时的日期
     * @author jianbo.feng
     * @param date
     * @return
     */
    public static Date getBeforeOrAfterHour(Date date, int hour) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, hour);
        return calendar.getTime();
    }

    /**
     * 获得之前或之后多少天的日期
     * @author jianbo.feng
     * @param date
     * @param day
     * @return
     */
    public static Date getBeforeOrAfterDay(Date date, int day) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, day);
        return calendar.getTime();
    }

    /**
     * 获得之前或之后多少月的日期
     * @author jianbo.feng
     * @param date
     * @param month
     * @return
     */
    public static Date getBeforeOrAfterMonth(Date date, int month) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }

    /**
     * 获得之前或之后多少年的日期
     * @author jianbo.feng
     * @param date
     * @param year
     * @return
     */
    public static Date getBeforeOrAfterYear(Date date, int year) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, year);
        return calendar.getTime();
    }

    /***
     * 根据起止日期获得(包括起止日期在内的)所有日期的列表
     * @param startDate {yyyyMMdd}
     * @param endDate {yyyyMMdd}
     * @return
     */
    public static List<String> getDateRangesDate(String startDate,String endDate) {
        List<String> list = new ArrayList<String>(0);
        List<String[]> timeStrs = getDateRanges(startDate,endDate);
        if(!timeStrs.isEmpty()) {
            for(String[] sStrs : timeStrs) {
                for(int i=Integer.parseInt(sStrs[0]);i<=Integer.parseInt(sStrs[1]);i++) {
                    String _s = i+"";
                    if(!list.contains(_s)) {
                        list.add(_s);
                    }
                }
            }
        }
        return list;
    }

    /***
     * 根据起止日期获得每月起止日期组信息
     * @param startDate {yyyyMMdd}
     * @param endDate {yyyyMMdd}
     * @return
     */
    public static List<String[]> getDateRanges(String startDate,String endDate) {
        String $d1 = startDate.substring(6, 8),$d2 = endDate.substring(6, 8);
        int y1 = Integer.parseInt(startDate.substring(0, 4)),y2 = Integer.parseInt(endDate.substring(0, 4));
        int m1 = Integer.parseInt(startDate.substring(4, 6)),m2 = Integer.parseInt(endDate.substring(4, 6));
        int yearSpace = 0;
        List<String[]> timeStrs = new ArrayList<String[]>(0);
        if(y1 < y2) {
            yearSpace = y2 - y1;
        }
        List<Integer> monthList = Arrays.asList(new Integer[]{1,3,5,7,8,10,12});
        if(yearSpace == 0) {
            for(int i=m1;i<=m2;i++) {
                String iStr = (i <= 9)?"0"+i:i+"";
                String sStr = "",eStr = "";
                if(i == m1) { sStr = y1+""+iStr+""+$d1; }
                else { sStr = y1+""+iStr+"01"; }

                if(i== m2) { eStr = y1+""+iStr+""+$d2; }
                else { String monthEndDay = "30"; if(monthList.contains(i)) { monthEndDay = "31"; } eStr = y1+""+iStr+monthEndDay; }
                timeStrs.add(new String[]{sStr,eStr});
            }
        }
        else {
            for(int i=m1;i<=12;i++) {
                String iStr = (i <= 9)?"0"+i:i+"";
                String sStr = "",eStr = "";
                if(i == m1) { sStr = y1+""+iStr+""+$d1; } else { sStr = y1+""+iStr+"01"; }
                String monthEndDay = "30";
                if(monthList.contains(i)) { monthEndDay = "31"; }
                eStr = y1+""+iStr+monthEndDay;
                timeStrs.add(new String[]{sStr,eStr});
            }

            for(int i=1;i<=m2;i++) {
                String iStr = (i <= 9)?"0"+i:i+"";
                String sStr = y2+""+iStr+"01",eStr = "";
                if(i == m2) { eStr = y2+""+iStr+""+$d2; }
                else { String monthEndDay = "30"; if(monthList.contains(i)) { monthEndDay = "31"; } eStr = y2+""+iStr+monthEndDay; }
                timeStrs.add(new String[]{sStr,eStr});
            }
        }
        return timeStrs;
    }
}
