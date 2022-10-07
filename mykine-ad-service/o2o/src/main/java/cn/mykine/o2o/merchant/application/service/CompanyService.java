package cn.mykine.o2o.merchant.application.service;

import cn.mykine.o2o.merchant.application.dto.CompanyDto;
import cn.mykine.o2o.merchant.domain.Company;
import cn.mykine.o2o.user.application.dto.*;

import java.util.List;

/**
 * @author: 
 * @description: 门店上下文应用层服务
 */
public interface CompanyService {

  /**
   * 通过关键词搜索门店列表
   *
   * @param nameKey
   * @return
   */
  List<CompanyDto> queryCompanies(String nameKey);


  /**
   * 添加一个门店
   *
   * @return
   */
  boolean addCompany(CompanyDto companyDto);


}
