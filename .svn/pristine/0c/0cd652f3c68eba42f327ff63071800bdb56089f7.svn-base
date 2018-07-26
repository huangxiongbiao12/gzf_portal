package com.gzf.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by ywg on 2017/5/11.
 */
public class TimeUtil {


    /**
     * 将String类型的日期转换成Date类型
     * @param timeStr 	字符串类型的日期
     * @param formatStr	日期格式
     * @return
     */
    public static Date getDateByTimeStr(String timeStr,String formatStr){
        SimpleDateFormat sdf = null;
        Date date = null;
        try {
            sdf = new SimpleDateFormat(formatStr);
            date = sdf.parse(timeStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }
    /**
     * 计算两个Date相差的月份
     * @param dateEnd
     * @param dateStart
     * @return
     */
    public static int getMonthDenf(Date dateEnd,Date dateStart) {
        Calendar bef = Calendar.getInstance();
        Calendar aft = Calendar.getInstance();
        bef.setTime(dateEnd);
        aft.setTime(dateStart);
        int result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
        int month = (aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR)) * 12;
        return Math.abs(month + result);
    }
    /**
     * 计算日期是几号
     * @param date
     *
     * @return
     */
    public static int getDayByDate(Date date) {
        Calendar bef = Calendar.getInstance();
        bef.setTime(date);
        int result =  bef.get(Calendar.DAY_OF_MONTH);
        return result;
    }

    /**
     * 将String类型的日期转换成Date类型
     * @param timeStr
     * @return
     */
    public static Date convertToDate(String timeStr) throws Exception{
        SimpleDateFormat sdf = null;
        if(timeStr.length() == 10 || timeStr.length() == 9 || timeStr.length() == 8){
            //有可能会出现2017-06-7或者2017-6-1的日期
            sdf = new SimpleDateFormat("yyyy-MM-dd");
        }else if(timeStr.length() == 19){
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }else if(timeStr.length() == 16){
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        }else{
            throw new Exception("日期格式错误");
        }
        return sdf.parse(timeStr);
    }
    /**
     * 获取当前时间
     * @return
     */
    public static Date getNowTime(){
        Date nowTime = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar calendar = Calendar.getInstance();//使用目前的时区和方法得到一个日历
            nowTime = dateFormat.parse(dateFormat.format(calendar.getTime()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nowTime;
    }
    /**
     * 获取当前时间
     * @return
     */
    public static Date getNowTime(String formatStr){
        Date nowTime = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(formatStr);
            Calendar calendar = Calendar.getInstance();//使用目前的时区和方法得到一个日历
            nowTime = dateFormat.parse(dateFormat.format(calendar.getTime()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nowTime;
    }
    /**
     * 获取当前时间字符串
     * @return
     * 作者: hbl
     * 创建时间: 2017年5月15日 上午8:59:58
     */
    public static String getNowTimeStr(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();//使用目前的时区和方法得到一个日历
        String nowTime = dateFormat.format(calendar.getTime());
        return nowTime;
    }
    /**
     * 根据格式获取当前时间字符串
     * @return
     * 作者: hbl
     * 创建时间: 2017年5月15日 上午8:59:58
     */
    public static String getNowTimeFormat(String formatStr){
        SimpleDateFormat dateFormat = new SimpleDateFormat(formatStr);
        Calendar calendar = Calendar.getInstance();//使用目前的时区和方法得到一个日历
        String nowTime = dateFormat.format(calendar.getTime());
        return nowTime;
    }
    /**
     * 时间格式化
     * @param time
     * @param formatStr
     * @return
     */
    public static Date formatDate(Date time,String formatStr){
        Date date = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(formatStr);
            date = dateFormat.parse(dateFormat.format(time));
        }catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }
    /**
     * 时间格式化
     * @param time
     * @param formatStr 例如：yyyy-MM-dd和yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String resultFormatDateStr(Date time,String formatStr){
        SimpleDateFormat dateFormat = new SimpleDateFormat(formatStr);
        return dateFormat.format(time);
    }

    public static String getDoublePrice(String price) {
        String all = "";
        double price1 = Double.parseDouble(price)/100;
        BigDecimal subA = new BigDecimal(price1);
        DecimalFormat df = new DecimalFormat("0.00");
        all = df.format(subA.doubleValue());
        return all;
    }

    /**
     * 计算两个日期的天数、小时数以及分钟数(list.get(0)为天数,list.get(1)为小时数,list(2)为分钟数)
     * @param maxDate	最大日期
     * @param minDate	最小日期
     * @return
     */
    public static List<Integer> differDate(Date maxDate, Date minDate){
        long nd = 1000 * 24 * 60 * 60;	//一天的毫秒数
        long nh = 1000 * 60 * 60;//一小时的毫秒数
        long nm = 1000 * 60;//一分钟的毫秒数
        long i = 0;
        i = maxDate.getTime() - minDate.getTime();
        Integer day = (int) (i / nd);
        Integer hour = (int) (i % nd / nh);
        Integer minute = (int) (i % nd % nh / nm);
        List<Integer> list = new ArrayList<Integer>();
        list.add(day);
        list.add(hour);
        list.add(minute);
        return list;
    }

    /**
     * 计算两个日期相差的月数
     * @param maxDate
     * @param minDate
     * @return
     * @throws Exception
     */
    public static Integer differMonths(Date maxDate,Date minDate){
        Integer month = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(minDate);
        minDate = formatDate(calendar.getTime(), "yyyy-MM");
        maxDate = formatDate(maxDate, "yyyy-MM");
        while (maxDate.compareTo(minDate) > 0){
            calendar.add(Calendar.MONTH, 1);
            minDate = formatDate(calendar.getTime(), "yyyy-MM");
            ++month;
        }
        return month;
    }

    /**
     * 计算两个日期相差的月数 小于一个月的不计算
     * @param maxDate
     * @param minDate
     * @return
     * @throws Exception
     */
    public static Integer differMonthsForDay30(Date maxDate,Date minDate){
        Integer month = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(minDate);
//        minDate = formatDate(calendar.getTime(), "yyyy-MM-dd");
//        maxDate = formatDate(maxDate, "yyyy-MM-dd");

        while (maxDate.compareTo(minDate) > 0){
            calendar.add(Calendar.MONTH, 1);
            minDate = calendar.getTime();
            ++month;
        }
        return month-1;
    }

    /**
     * 获取两个日期相差的天数(两个日期的顺序不能乱)
     * @param maxDate
     * @param minDate
     * @return
     */
    public static int daysBetween(Date maxDate,Date minDate){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            maxDate = sdf.parse(sdf.format(maxDate));
            minDate = sdf.parse(sdf.format(minDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(maxDate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(minDate);
        long time2 = cal.getTimeInMillis();
        long between_days=(time1-time2)/(1000*3600*24);
        return Integer.parseInt(String.valueOf(between_days));
    }

    public static Date getDateAfterMonth(Date date,int month) {
        Calendar c = Calendar.getInstance();//获得一个日历的实例
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        c.setTime(date);//设置日历时间
        c.add(Calendar.MONTH,month);//在日历的月份上增加6个月
        return c.getTime();//得到6个月后的日期
    }

    public static Date getDateAfterDay(Date date,int day) {
        Calendar c = Calendar.getInstance();//获得一个日历的实例
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        c.setTime(date);//设置日历时间
        c.add(Calendar.DAY_OF_MONTH,day);//在日历的月份上增加6个月
        return c.getTime();//得到6个月后的日期
    }

    /**
     * 判断time是否在from，to之内
     *
     * @param time 指定日期
     * @param from 开始日期
     * @param to   结束日期
     * @return
     */
    public static boolean belongCalendar(Date time, Date from, Date to) {
        Calendar date = Calendar.getInstance();
        date.setTime(time);

        Calendar after = Calendar.getInstance();
        after.setTime(from);

        Calendar before = Calendar.getInstance();
        before.setTime(to);

        if (date.after(after) && date.before(before)) {
            return true;
        } else {
            return false;
        }
    }

}
