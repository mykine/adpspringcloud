package cn.mykine.o2o.merchant.infrastructure.dataobject;

import java.math.BigDecimal;

/**
 * @author: 
 * @description:
 */
public class CompanyDo {

  private Long id;

  private String name;

  private String enterpriseName;

  private String lat;

  private String lng;

  private Integer provinceId;

  private String provinceName;

  private Integer cityId;

  private String cityName;

  private Integer districtId;

  private String districtName;

  private Integer streetId;

  private String streetName;

  private String address;

  private String introduction;

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

  public String getLat() {
    return lat;
  }

  public void setLat(String lat) {
    this.lat = lat;
  }

  public String getLng() {
    return lng;
  }

  public void setLng(String lng) {
    this.lng = lng;
  }

  public Integer getProvinceId() {
    return provinceId;
  }

  public void setProvinceId(Integer provinceId) {
    this.provinceId = provinceId;
  }

  public String getProvinceName() {
    return provinceName;
  }

  public void setProvinceName(String provinceName) {
    this.provinceName = provinceName;
  }

  public Integer getCityId() {
    return cityId;
  }

  public void setCityId(Integer cityId) {
    this.cityId = cityId;
  }

  public String getCityName() {
    return cityName;
  }

  public void setCityName(String cityName) {
    this.cityName = cityName;
  }

  public Integer getDistrictId() {
    return districtId;
  }

  public void setDistrictId(Integer districtId) {
    this.districtId = districtId;
  }

  public String getDistrictName() {
    return districtName;
  }

  public void setDistrictName(String districtName) {
    this.districtName = districtName;
  }

  public Integer getStreetId() {
    return streetId;
  }

  public void setStreetId(Integer streetId) {
    this.streetId = streetId;
  }

  public String getStreetName() {
    return streetName;
  }

  public void setStreetName(String streetName) {
    this.streetName = streetName;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getIntroduction() {
    return introduction;
  }

  public void setIntroduction(String introduction) {
    this.introduction = introduction;
  }

  public BigDecimal getServeStars() {
    return serveStars;
  }

  public void setServeStars(BigDecimal serveStars) {
    this.serveStars = serveStars;
  }
}
