package cn.mykine.o2o.user.adapter.wxplatform.callback;

import cn.mykine.o2o.user.application.dto.PaymentContractInfoDto;
import cn.mykine.o2o.user.application.dto.WxContractSigningNotification;
import cn.mykine.o2o.user.application.dto.WxContractSigningResponse;
import cn.mykine.o2o.user.application.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: 
 * @description:
 */
@RestController
@RequestMapping("/mock/user/callback")
public class MockCallbackController {

  @Autowired
  AppUserService appUserService;

  @RequestMapping(value = "/wxContractSign", method = RequestMethod.POST)
  @ResponseBody
  public WxContractSigningResponse notifyContractSigningResult(
      @RequestBody WxContractSigningNotification notification) {
    PaymentContractInfoDto contractInfoDto = new PaymentContractInfoDto();
    contractInfoDto.setContractId(notification.getContractId());
    contractInfoDto.setOpenId(notification.getOpenid());
    contractInfoDto.setContractCode(notification.getContractCode());
    appUserService.onPaymentContractSigned(contractInfoDto);
    WxContractSigningResponse response = new WxContractSigningResponse();
    response.setReturnCode("SUCCESS");
    response.setReturnMsg("OK");
    return response;
  }
}
