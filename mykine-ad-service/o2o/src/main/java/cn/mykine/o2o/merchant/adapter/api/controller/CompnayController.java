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
    CommonResponse<List<CompanyDto>> res
            = CommonResponse.success(companyService.queryCompanies(key));
    return res;
  }

  @RequestMapping(value = "/addOne", method = RequestMethod.POST)
  @ResponseBody
  public CommonResponse<Boolean> addOne(@RequestBody  CompanyDto companyDto) {
    Boolean res = companyService.addCompany(companyDto);
    return CommonResponse.success(res);
  }


  @RequestMapping(value = "/userTest", method = RequestMethod.GET)
  @ResponseBody
  public CommonResponse<CompanyDto> userTest( @RequestParam String openId) {
    CompanyDto obj = new CompanyDto();
    obj.setTags("专用 测试");
    obj.setName("门店A1");
    obj.setProvinceId(440000);
    obj.setProvinceName("广东省");
    obj.setCityId(440300);
    obj.setCityName("深圳市");
    obj.setLocation("22.550012,113.939902");
    obj.setServeStars("3.9");
    return CommonResponse.success(obj);
  }

}
