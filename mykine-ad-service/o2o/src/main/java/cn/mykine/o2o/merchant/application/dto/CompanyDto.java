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

  private String provinceName;

  private String cityName;

  private String tags;

  private String serveStars;

  public CompanyDto(Company company) {
    this.id = company.getId();
    this.name = company.getName();
    this.location = company.getLocation();
    this.provinceName = company.getProvinceName();
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
}