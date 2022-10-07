package cn.mykine.o2o.merchant.domain;

import cn.mykine.o2o.infracore.api.CommonError;
import cn.mykine.o2o.infracore.exception.DomainException;

/**
 * @author: 
 * @description:
 */
public class Company {

  private long id;

  private String name;

  private String location;

  private Integer provinceId;

  private String provinceName;

  private Integer cityId;

  private String cityName;

  private String tags;

  private String serveStars;

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
