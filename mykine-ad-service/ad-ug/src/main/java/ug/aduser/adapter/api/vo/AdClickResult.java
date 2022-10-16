package ug.aduser.adapter.api.vo;

import java.io.Serializable;

public class AdClickResult implements Serializable {
    private static final long serialVersionUID = -7930135262144871769L;
    private String Msg = null;
    private Integer Code = null;
    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        this.Msg = msg;
    }

    public Integer getCode() {
        return Code;
    }

    public void setCode(Integer code) {
        Code = code;
    }

    public static <T> AdClickResult success(Integer code, String msg) {
        AdClickResult result = new AdClickResult();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
