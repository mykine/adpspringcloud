package ug.aduser.application.dto;



/**
 * @author: 
 * @description:
 */
public class SignInResultDto {

  private SignInResultCode result;
  private String token;

  public SignInResultCode getResult() {
    return result;
  }

  public void setResult(SignInResultCode result) {
    this.result = result;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
