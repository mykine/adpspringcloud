package ug.aduser.application.dto;

/**
 * @author: 
 * @description:
 */
public class PaymentContractInfoDto {

  private String contractCode;
  private String openId;
  private String contractId;

  public String getContractCode() {
    return contractCode;
  }

  public void setContractCode(String contractCode) {
    this.contractCode = contractCode;
  }

  public String getOpenId() {
    return openId;
  }

  public void setOpenId(String openId) {
    this.openId = openId;
  }

  public String getContractId() {
    return contractId;
  }

  public void setContractId(String contractId) {
    this.contractId = contractId;
  }
}
