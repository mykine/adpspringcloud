package ug.aduser.application.client;


import ug.aduser.application.dto.WxCode2SessionResultDto;

/**
 * @author: 
 * @description:
 */
public interface WxPlatformClient {

  WxCode2SessionResultDto code2Session(String code);

}
