package cn.mykine.o2o.merchant.infrastructure.dataobject;

import java.math.BigDecimal;

/**
 * @author: 
 * @description:
 */
public class CompanyEsDo {

  private Long id;

  private String name;

  private String enterpriseName;

  private String location;

  private String tags;

  private int provinceId;

  private String provinceName;

  private int cityId;

  private String cityName;

  private BigDecimal serveStars;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEnterpriseName() {
    return enterpriseName;
  }

  public void setEnterpriseName(String enterpriseName) {
    this.enterpriseName = enterpriseName;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getTags() {
    return tags;
  }

  public void setTags(String tags) {
    this.tags = tags;
  }

  public int getProvinceId() {
    return provinceId;
  }

  public void setProvinceId(int provinceId) {
    this.provinceId = provinceId;
  }

  public String getProvinceName() {
    return provinceName;
  }

  public void setProvinceName(String provinceName) {
    this.provinceName = provinceName;
  }

  public int getCityId() {
    return cityId;
  }

  public void setCityId(int cityId) {
    this.cityId = cityId;
  }

  public String getCityName() {
    return cityName;
  }

  public void setCityName(String cityName) {
    this.cityName = cityName;
  }

  public BigDecimal getServeStars() {
    return serveStars;
  }

  public void setServeStars(BigDecimal serveStars) {
    this.serveStars = serveStars;
  }

}
