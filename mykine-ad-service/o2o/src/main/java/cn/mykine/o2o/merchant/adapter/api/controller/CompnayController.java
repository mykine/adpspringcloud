package cn.mykine.o2o.merchant.adapter.api.controller;

import cn.mykine.o2o.infracore.api.CommonResponse;
import cn.mykine.o2o.merchant.application.dto.CompanyDto;
import cn.mykine.o2o.merchant.application.service.CompanyService;
import cn.mykine.o2o.merchant.domain.Company;
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
import java.util.List;


/**
 * @author: 
 * @description:
 */
@RestController
@RequestMapping("/merchant")
public class CompnayController {

  @Autowired
  private CompanyService companyService;

  @RequestMapping(value = "/query", method = RequestMethod.GET)
  @ResponseBody
  public CommonResponse<List<CompanyDto>> query(@RequestParam String key) {
    CommonResponse<List<CompanyDto>> res = CommonResponse.success(companyService.queryCompanies(key));
    return res;
  }

  @RequestMapping(value = "/userTest", method = RequestMethod.GET)
  @ResponseBody
  public CommonResponse<String> userTest( @RequestParam String openId) {

    return CommonResponse.success("userTest:"+openId);
  }

}
