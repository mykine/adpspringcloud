package cn.mykine.o2o.merchant.domain.repository;

import cn.mykine.o2o.merchant.domain.Company;

import java.util.List;

/**
 * @author: 
 * @description:
 */
public interface CompanyRepository {

  Company getById(String id);

  List<Company> listQuery(String key);


}
