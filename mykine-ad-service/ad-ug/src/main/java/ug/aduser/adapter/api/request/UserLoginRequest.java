package ug.aduser.adapter.api.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 模拟用户登录
 *
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginRequest {

    /**
     * 用户ID
     */
    private String uid;

    /**
     * 是否是新用户:1是,0否
     */
    private Integer isNew;

    /**
     * {{IDFA}}
     */
    private String idfa;

    /**
     * {{IMEI_MD5}}
     */
    private String imei;

    /**
     * {{OAID}}
     */
    private String oaid;

    /**
     * {{ANDROID_ID_MD5}}
     */
    private String androidId;


}
