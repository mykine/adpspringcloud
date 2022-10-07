package cn.mykine.o2o.user.adapter.wxminipro.controller;

import cn.mykine.o2o.infracore.api.CommonResponse;
import cn.mykine.o2o.user.adapter.wxminipro.vo.SignInResultVo;
import cn.mykine.o2o.user.application.dto.SignInCommandDto;
import cn.mykine.o2o.user.application.dto.SignInResultDto;
import cn.mykine.o2o.user.application.dto.UserInfoDto;
import cn.mykine.o2o.user.application.dto.UserInfoQueryDto;
import cn.mykine.o2o.user.application.service.AppUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


/**
 * @author: 
 * @description:
 */
@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private AppUserService appUserService;

  @RequestMapping(value = "/signIn", method = RequestMethod.POST)
  @ResponseBody
  public CommonResponse<SignInResultVo> signIn(@Validated @RequestBody SignInCommandDto cmd,
      final HttpServletResponse response) {
    SignInResultDto result = appUserService.signInOrSignUp(cmd);
    if (result.getToken() != null) {
      response.setHeader("Authorization", result.getToken());
    }
    SignInResultVo data = new SignInResultVo();
    data.setResult(result.getResult().code());
    return CommonResponse.success(data);
  }

  @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
  @ResponseBody
  public CommonResponse<UserInfoDto> getUserInfo( @RequestParam String openId) {
    UserInfoQueryDto query = new UserInfoQueryDto();
    query.setOpenId(openId);
    return CommonResponse.success(appUserService.getUserInfo(query));
  }

  @RequestMapping(value = "/userTest", method = RequestMethod.GET)
  @ResponseBody
  public CommonResponse<String> userTest( @RequestParam String openId) {

    return CommonResponse.success("userTest:"+openId);
  }

}
