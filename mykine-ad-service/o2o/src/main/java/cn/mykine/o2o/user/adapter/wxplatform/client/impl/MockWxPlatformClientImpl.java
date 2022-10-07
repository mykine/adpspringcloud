package cn.mykine.o2o.user.adapter.wxplatform.client.impl;

import cn.mykine.o2o.user.application.client.WxPlatformClient;
import cn.mykine.o2o.user.application.dto.WxCode2SessionResultDto;
import org.springframework.stereotype.Component;

/**
 * @author: 
 * @description: 模拟微信开放平台接口响应
 */
@Component
public class MockWxPlatformClientImpl implements WxPlatformClient {

  @Override
  public WxCode2SessionResultDto code2Session(String code) {
    WxCode2SessionResultDto ret = new WxCode2SessionResultDto();
    ret.setSessionKey("session_key_" + code);
    ret.setOpenId("open_id_" + code);
    ret.setErrcode(0);
    ret.setErrmsg("");
    return ret;
  }
}
