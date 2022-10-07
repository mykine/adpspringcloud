package cn.mykine.o2o.user.application.client;

import cn.mykine.o2o.user.application.dto.WxCode2SessionResultDto;

/**
 * @author: 
 * @description:
 */
public interface WxPlatformClient {

  WxCode2SessionResultDto code2Session(String code);

}
