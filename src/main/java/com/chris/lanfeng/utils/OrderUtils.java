package com.chris.lanfeng.utils;

import org.apache.commons.lang3.math.NumberUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by Ben on 2017/11/3.
 */
public class OrderUtils {

    private static Random R = new Random();
    private static DateFormat DF = new SimpleDateFormat("yyyyMMdd");
    private static DateFormat DF_YY = new SimpleDateFormat("yyMMdd");

    /**
     * 订单编号 8位时间＋4位随机数＋用户id后4位
     *
     * @param userId
     * @return
     */
    public static long generateOrderNum(long userId) {
        String dateStr = DF.format(new Date());
        long dateNum = NumberUtils.toLong(dateStr);
        int randNum = getRandomNum();
        long userNum = userId % 10000;
        return dateNum * 100000000L + randNum * 10000 + userNum;
    }

    /**
     * 生成群统计ID
     *
     * @return
     */
    public static long generateGroupStatId() {
        return NumberUtils.toLong(DF.format(new Date()));
    }

    /**
     * 订单号 加订单次数
     *
     * @param orderNum
     * @param refundCount
     * @return
     */
    public static long generateRefundNum(long orderNum, int refundCount) {
        return orderNum * 100L + refundCount;
    }

    /**
     * 计算退款金额
     *
     * @param price
     * @param hours
     * @return
     */
    public static Double calculateRefundPrice(int price, long hours) {
        if (hours >= 48) {
            return price * 0.9;
        } else if (hours >= 24 && hours < 48) {
            return price * 0.7;
        } else {
            return price * 0.5;
        }
    }

    public static int calculateDeposit(int price, double ratio) {
        return (int) (price * ratio);
    }

    /**
     * 生成四位随机数 1000~9999
     *
     * @return
     */
    private static int getRandomNum() {
        return R.nextInt(9000) + 1000;
    }

    public static void main(String[] args) {
        System.out.println(generateOrderNum(19988));
        System.out.println(generateRefundNum(2017122636820347L, 2));
    }

}
