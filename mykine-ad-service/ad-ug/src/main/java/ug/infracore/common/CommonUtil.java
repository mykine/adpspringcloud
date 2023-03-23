package ug.infracore.common;



import com.alibaba.druid.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 通用工具类
 * */
public class CommonUtil {

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
}
