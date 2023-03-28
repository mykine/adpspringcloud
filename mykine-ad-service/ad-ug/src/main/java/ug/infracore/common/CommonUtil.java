package ug.infracore.common;



import com.alibaba.druid.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 通用工具类
 * */
public class CommonUtil {

    public static final String strStr="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    /**
     * 当前时间戳（秒）
     * */
    public static int nowTime(){
        return (int)(System.currentTimeMillis()/1000);
    }

/**
 * 当前时间戳（毫秒）
 * */
    public static Long nowTimeMillSeconds(){
        return System.currentTimeMillis();
    }

    /**
     * 根据时间差判断用户是否激活
     * */
    public static boolean checkActive(Long clickTime,Long activeTime){
        //点击时间是5天内的算激活
        Integer clickTimeSeconds = (int)(clickTime/1000);
        Integer activeTimeSeconds = (int)(activeTime/1000);
        int gapSeconds = activeTimeSeconds - clickTimeSeconds;
        return gapSeconds <= 432000 ? true : false;
    }

    /**
     * 解析设备id的值,
     * */
    public static String parseDeviceIdStr(String deviceID){
        if(StringUtils.isEmpty(deviceID) || "NULL".equals(deviceID) || "0".equals(deviceID)|| "00000000-0000-0000-0000-000000000000".equals(deviceID)|| "00000000000000000000000000000000".equals(deviceID) ){
            return "";
        }
        deviceID = deviceID.trim();
        if(StringUtils.isEmpty(deviceID) || "NULL".equals(deviceID) || "0".equals(deviceID)|| "00000000-0000-0000-0000-000000000000".equals(deviceID)|| "00000000000000000000000000000000".equals(deviceID) ){
            return "";
        }
        return deviceID+"-"+Thread.currentThread().getName();
    }

    /**
     * 解析字符串类型值
     * */
    public static String parseStrValue(String str){
        if( StringUtils.isEmpty(str) ){
            return "";
        }
        return str;
    }


    /**
     * 毫秒时间戳格式化指定时间字符串
     * */
    public static String formatDateTime(Long theMillTime,String sdf ){
        SimpleDateFormat df = new SimpleDateFormat(sdf);
        Date dt = new Date(theMillTime);
        String str = df.format(dt);
        return str;
    }


    /**
     * 随机值
     * */
    public static String randomStr(String prefix){
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        //生成一个10000~99999的伪随机整数
        int val1 = rand.nextInt(100 , 999);
        return prefix+"_"+System.currentTimeMillis()+"_"+val1;
    }

    /**
     * 随机值
     * */
    public static String uuidStr(String prefix){
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replace("-", "");

        return uuid;
    }



    public static String getRandomString(int length){

        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(strStr.charAt(number));
        }
        return sb.toString();
    }


    /**
     * 随机值
     * */
    public static String randomAdName(){
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        String[] adArr = new String[]{
                "路人王广告",
                "NBA广告",
                "CBA广告",
                "英雄联盟广告",
                "足球广告",
                "网球广告",
                "世界杯广告",
        };
        int i = rand.nextInt(0 , 5);
        return adArr[i];
    }

    /**
     * 随机值
     * */
    public static String randomOS(){
        ThreadLocalRandom rand = ThreadLocalRandom.current();
       ;
        int i = rand.nextInt(1 , 10);
        return i+"";
    }

    /**
     * 随机值
     * */
    public static int randomPlatform(){
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        int i = rand.nextInt(1 , 10);
        return i;
    }

}
