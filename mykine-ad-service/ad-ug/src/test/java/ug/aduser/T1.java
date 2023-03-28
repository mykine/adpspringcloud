package ug.aduser;

import ug.infracore.common.CommonUtil;

import java.util.UUID;

public class T1 {
    public static void main(String[] args) {
//        System.out.println(CommonUtil.randomStr("imei"));
//        System.out.println(CommonUtil.randomStr("oaid"));
//        System.out.println(CommonUtil.randomStr("androidId"));
//        System.out.println(CommonUtil.randomStr("idfa"));
        System.out.println(CommonUtil.getRandomString(32));
        System.out.println(CommonUtil.getRandomString(32));
        System.out.println(CommonUtil.getRandomString(32));
        System.out.println(CommonUtil.getRandomString(32));
        System.out.println(CommonUtil.getRandomString(32));
    }
}
