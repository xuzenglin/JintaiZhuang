package com.chris.lanfeng.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 * Created by xiaoxu on 2017/8/4.
 */
public class DateUtil {
    public static final long SECOND = 1000;
    public static final long MINUTES = SECOND * 60;
    public static final long HOUR = 60 * MINUTES;
    public static final long DAY = 24 * HOUR;
    public static final long YEAR = 365 * DAY;

    public static void main(String[] args) {
        System.out.println(meetTimeToStr(System.currentTimeMillis()));
        System.out.println(Instant.now().toEpochMilli());
        System.out.println(getDayBegin(System.currentTimeMillis()));
        System.out.println(getMinutes(getDayBegin(System.currentTimeMillis())));
        try {
            System.out.println(URLEncoder.encode("RKm3gshjsUe5+rO7qWL2jMCdRc220ucCWLvstubCiRjzi7TJOtx6u3e67DSdIX6daGNQ3+GL0mI=", "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println(60%60);
    }

    /**
     * 获取几小时之前的时间戳
     *
     * @param hour
     * @return
     */
    public static long getTimeByHour(int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + hour);
        return calendar.getTimeInMillis();

    }

    /**
     * 获取距离当前时间分钟数
     *
     * @param time
     * @return
     */
    public static long getMinutes(long time) {
        return Math.abs(Instant.now().toEpochMilli() - time) / (1000 * 60);
    }

    /**
     * 获取距离当前时间的小时数
     *
     * @param time
     * @return
     */
    public static long getHours(long time) {
        return Math.abs(Instant.now().toEpochMilli() - time) / (1000 * 60 * 60);
    }


    //当前时间的前N天，毫秒值
    public static long getLastAnyDayBeginTime(int days) {
        //获取0点时间戳
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -days);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }


    /**
     * 获取前一个月的时间戳
     *
     * @return
     */
    public static long getPreviousMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);    //得到前一个月
        return calendar.getTimeInMillis();
    }

    /**
     * 根据时间戳获取时间
     *
     * @param time
     * @return
     */
    public static Date getDate(long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        return cal.getTime();
    }

    public static long age2Birth(byte age) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) - age - 1);
        cal.add(Calendar.DAY_OF_YEAR, 1);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    /**
     * 根据时间获取时间戳
     *
     * @param time
     * @return
     */
    public static long getDate(Date time) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        return cal.getTimeInMillis();
    }

    /**
     * 获取指定日期当前最早时刻 00:00:00
     *
     * @param time
     * @return
     */
    public static long getDayBegin(long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    /**
     * 获取指定日志当天最晚时刻 23:59:59
     *
     * @param time
     * @return
     */
    public static long getDayEnd(long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        cal.set(Calendar.HOUR_OF_DAY, 24);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, -1);
        return cal.getTimeInMillis();
    }

    /**
     * 取得long时间表示的小时,最小精度小时
     *
     * @param mills
     * @return
     */
    public static String getMinute(long mills) {
        SimpleDateFormat sdf = new SimpleDateFormat("mm");
        return sdf.format(new Date(mills));
    }

    /**
     * 取得long时间表示的小时分钟
     *
     * @param mills
     * @return
     */
    public static String getHHmm(long mills) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(new Date(mills));
    }

    public static String getTimeByMills(long mills) {
        if (mills < MINUTES) {
            return "1分钟内";
        }
        if (mills < HOUR) {
            return "1小时内";
        }
        return "24小时内";
    }

    /**
     * 获取时间自然月的1号00:00:00
     *
     * @param time
     * @return
     */
    public static long getMonthBegin(long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        cal.add(Calendar.MONTH, 0);
        cal.set(Calendar.DATE, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    /**
     * 获取时间自然月的最后一日23:59:59
     *
     * @param time
     * @return
     */
    public static long getMonthEnd(long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        cal.add(Calendar.MONDAY, 1);
        cal.set(Calendar.DATE, 0);
        cal.set(Calendar.HOUR_OF_DAY, 24);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, -1);
        return cal.getTimeInMillis();
    }

    /**
     * 获取指定日期下一天 00:00:00
     *
     * @param time
     * @return
     */
    public static long getNextDay(long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        cal.add(Calendar.DATE, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    /**
     * 获取指定时间，往前推，第一个时间的分数末尾是5的时间的long
     *
     * @param ctime
     * @return
     */
    public static long getPreLast5(long ctime, int time) {
        SimpleDateFormat sdf = new SimpleDateFormat("mm");
        long ret = 0L;
        // 当前时间的分数
        int minite = new Integer(sdf.format(ctime));
        if (minite / time == 0) {
            System.out.println("0 : " + (time + minite % time));
            ret = ctime - (time + minite % time) * 60 * 1000L;
        } else if (minite / time % 2 == 0) {
            System.out.println("d : " + (minite - (minite / time - 1) * time));
            ret = ctime - (minite - (minite / time - 1) * time) * 60 * 1000L;
        } else {
            System.out.println("j : " + (minite - (minite / time) * time));
            ret = ctime - (minite - (minite / time) * time) * 60 * 1000L;
        }
        return ret;
    }

    /**
     * 获取时间自然周的周一00:00:00
     *
     * @param time
     * @return
     */
    public static long getWeekBegin(long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        int dayOfWeekInUs = cal.get(Calendar.DAY_OF_WEEK);
        int beginDayInCh = dayOfWeekInUs == 1 ? -6 : 2 - dayOfWeekInUs;
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis() + beginDayInCh * DAY;
    }

    /**
     * 获取时间自然周的周日23:59:59
     *
     * @param time
     * @return
     */
    public static long getWeekEnd(long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        int dayOfWeekInUs = cal.get(Calendar.DAY_OF_WEEK);
        int beginDayInCh = dayOfWeekInUs == 1 ? 0 : 8 - dayOfWeekInUs;
        cal.set(Calendar.HOUR_OF_DAY, 24);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, -1);
        return cal.getTimeInMillis() + beginDayInCh * DAY;
    }

    public static long getPointTime(int year, int month, int day, int hour, int minute, int second) {
        Calendar cal = Calendar.getInstance();
        // The first month of the year in the Gregorian and Julian calendars is JANUARY which is 0
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DATE, day);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, second);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }


    /**
     * 将日期字符串转化日期对象 支持格式： YYYY-MM-DD
     *
     * @param date
     * @return
     */
    public static Date toDateFormat(String date) {
        if (StringUtils.isBlank(date)) {
            return null;
        }
        SimpleDateFormat sdf = null;
        try {
            if (date.indexOf("-") != -1) {
                sdf = new SimpleDateFormat("yyyy-MM-dd");
                return sdf.parse(date);
            }
            return null;
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 计算两个时间之间的天数
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static int getDateSpace(Date startTime, Date endTime) {
        Calendar calStart = Calendar.getInstance();
        Calendar calEnd = Calendar.getInstance();
        calStart.setTime(startTime);
        calEnd.setTime(endTime);
        //设置时间为0时
        calStart.set(Calendar.HOUR_OF_DAY, 0);
        calStart.set(Calendar.MINUTE, 0);
        calStart.set(Calendar.SECOND, 0);
        calEnd.set(Calendar.HOUR_OF_DAY, 0);
        calEnd.set(Calendar.MINUTE, 0);
        calEnd.set(Calendar.SECOND, 0);
        int result = ((int) (calEnd.getTime().getTime() / 1000) - (int) (calStart.getTime().getTime() / 1000)) / 3600 / 24;
        return result == 0 ? 1 : Math.abs(result);
    }

    public static int getAge(long start) {
        if (start == 0) return 0;
        Instant startIn = Instant.ofEpochMilli(start);
        LocalDateTime localDate = LocalDateTime.ofInstant(startIn, ZoneOffset.UTC);
        return Period.between(localDate.toLocalDate(), LocalDate.now()).getYears();
    }

    public static String dateToString(long dateTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return sdf.format(new Date(dateTime));
    }

    public static String meetTimeToStr(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(new Date(time)) + "-" + sdf.format(new Date(time + 90 * DateUtils.MILLIS_PER_MINUTE));
    }

    public static String simpleDateToString(long dateTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date(dateTime));
    }

    /**
     * 获取当前时间为星期几  0：星期天
     *
     * @return
     */
    public static int getWeekOfDate(long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(time));
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return w;
    }

}
