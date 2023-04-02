package ug.aduser.infrastructure.dataobject;

import lombok.Data;

@Data
public class LoginUserDataDo {

    private String id;

    /**
     * 登录的时间(毫秒级)
     */
    private Long loginTime;

    /**
     * 是否是新用户:1是,0否
     */
    private Integer isNew;

    /**
     * ios设备信息,各大广告平台加密后传过来的值,一般加密后值
     */
    private String iosDeviceid;

    /**
     * 安卓串号,一般加密后值
     */
    private String imei;

    /**
     * 移动安全联盟提出的安卓系统移动终端设备补充标识,一般加密后值
     */
    private String oaid;

    /**
     * 安卓设备id,一般加密后值
     */
    private String androidId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }

    public Integer getIsNew() {
        return isNew;
    }

    public void setIsNew(Integer isNew) {
        this.isNew = isNew;
    }

    public String getIosDeviceid() {
        return iosDeviceid;
    }

    public void setIosDeviceid(String iosDeviceid) {
        this.iosDeviceid = iosDeviceid;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getOaid() {
        return oaid;
    }

    public void setOaid(String oaid) {
        this.oaid = oaid;
    }

    public String getAndroidId() {
        return androidId;
    }

    public void setAndroidId(String androidId) {
        this.androidId = androidId;
    }
}
