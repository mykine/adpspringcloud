package cn.mykine.o2o.merchant.application.dto;

import cn.mykine.o2o.merchant.domain.Company;

/**
 * @author: 
 * @description:
 */
public class CompanyDto {

  private long id;

  private String name;

  private String location;

  private Integer provinceId;

  private String provinceName;

  private Integer cityId;

  private String cityName;

  private String tags;

  private String serveStars;

  public CompanyDto(){}

  public CompanyDto(Company company) {
    this.id = company.getId();
    this.name = company.getName();
    this.location = company.getLocation();
    this.provinceId = company.getProvinceId();
    this.provinceName = company.getProvinceName();
    this.cityId = company.getCityId();
    this.cityName = company.getCityName();
    this.tags = company.getTags();
    this.serveStars = company.getServeStars();
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getProvinceName() {
    return provinceName;
  }

  public void setProvinceName(String provinceName) {
    this.provinceName = provinceName;
  }

  public String getCityName() {
    return cityName;
  }

  public void setCityName(String cityName) {
    this.cityName = cityName;
  }

  public String getTags() {
    return tags;
  }

  public void setTags(String tags) {
    this.tags = tags;
  }

  public String getServeStars() {
    return serveStars;
  }

  public void setServeStars(String serveStars) {
    this.serveStars = serveStars;
  }

  public Integer getProvinceId() {
    return provinceId;
  }

  public void setProvinceId(Integer provinceId) {
    this.provinceId = provinceId;
  }

  public Integer getCityId() {
    return cityId;
  }

  public void setCityId(Integer cityId) {
    this.cityId = cityId;
  }
}
