package cn.mykine.o2o.merchant.application.service.impl;

import cn.mykine.o2o.infracore.exception.DomainException;
import cn.mykine.o2o.infracore.security.Authority;
import cn.mykine.o2o.infracore.security.JwtUtil;
import cn.mykine.o2o.merchant.application.dto.CompanyDto;
import cn.mykine.o2o.merchant.application.service.CompanyService;
import cn.mykine.o2o.merchant.domain.Company;
import cn.mykine.o2o.merchant.domain.repository.CompanyRepository;
import cn.mykine.o2o.user.application.client.WxPlatformClient;
import cn.mykine.o2o.user.application.dto.*;
import cn.mykine.o2o.user.application.service.AppUserService;
import cn.mykine.o2o.user.domain.UserAccount;
import cn.mykine.o2o.user.domain.repository.UserAccountRepository;
import cn.mykine.o2o.user.infrastructure.UserError;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: 
 * @description:
 */
@Service
public class CompanyServiceImpl implements CompanyService {

  private Logger LOGGER = LoggerFactory.getLogger(CompanyServiceImpl.class);

  @Autowired
  CompanyRepository companyRepository;

  @Override
  public List<CompanyDto> queryCompanies(String nameKey) {
    List<CompanyDto> res = new ArrayList<>();
    List<Company> companies = companyRepository.listQuery(nameKey);
    for (Company item :
    companies) {
      res.add(new CompanyDto(item));
    }
    return res;
  }

  @Override
  public boolean addCompany(CompanyDto companyDto) {
    Company company = new Company();
    company.setId(companyDto.getId());
    company.setName(companyDto.getName());
    company.setLocation(companyDto.getLocation());
    company.setProvinceId(companyDto.getProvinceId());
    company.setProvinceName(companyDto.getProvinceName());
    company.setCityId(companyDto.getCityId());
    company.setCityName(companyDto.getCityName());
    company.setServeStars(companyDto.getServeStars());
    company.setTags(companyDto.getTags());
    return companyRepository.addOneSync(company);
  }
}
