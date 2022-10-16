package ug.aduser.adapter.api.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 百度SEM监测搜索点击数据
 *
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdClickRequest {

    /**
     *  {{PLATFORM}} 平台类型:1-广点通,2-应用宝,3-华为,4-粉丝通,5-小米,6-OPPO,7-VIVO,8-百度SEM,9-UC
     */
    private Integer platform;

    /**
     * {{IDFA}}
     */
    private String idfa;

    /**
     * {{IMEI_MD5}}
     */
    private String imei_md5;

    /**
     * {{ANDROID_ID_MD5}}
     */
    private String android_id_md5;

    /**
     * {{OAID}}
     */
    private String oaid;

    /**
     * {{USER_ID}}账户ID
     */
    private String userid;

    /**
     * {{IDEA_ID}}创意ID
     */
    private String aid;

    /**
     * {{PLAN_ID}}计划ID
     */
    private String pid;

    /**
     * {{UNIT_ID}}单元ID
     */
    private String uid;


    /**
     * {{CLICK_ID}}
     */
    private String click_id;

    /**
     * {{MAC}}md5加密值，去除分隔符 ":"（例：32738C807A28），再进行标准32位小写MD5编码
     */
    private String mac;

    /**
     * {{MAC_MD5}}md5加密值，保留分隔符 ":"（例：32:73:8C:80:7A:28），再进行标准32位小写MD5编码
     */
    private String mac_md5;

    /**
     * {{IP}}
     */
    private String ip;

    /**
     * {{UA}}
     */
    private String ua;

    /**
     * {{OS}}
     */
    private String os;

    /**
     * {{TS}}点击时间,时间戳，单位：毫秒(ms)
     */
    private Long ts;

    /**
     * {{CALLBACK_URL}}产生转化时广告主替换相关参数 (a_type,a_value)后生成签名进行回调，callback_url中的s、o、ext_info 由百度server端处理
     */
    private String callback_url;



    /***************************  附加字段begin *********************************************/

    /**
     * 百度在监测链接自动附加上,替换通配符后的完整监测URL(不包含&sign=) +akey进行标准32位md5，生成签名值，在监测URL后添加&sign=签名值
     */
    private String akey;

    /**
     * {{SIGN}}监测URL签名:替换通配符后的完整监测URL(不包含&sign=) +akey进行标准32位md5，生成签名值，在监测URL后添加&sign=签名值
     */
    private String sign;


    /***************************  附加字段end *********************************************/

}
