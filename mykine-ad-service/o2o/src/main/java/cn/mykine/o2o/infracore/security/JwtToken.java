package cn.mykine.o2o.infracore.security;

import io.jsonwebtoken.Claims;
import java.util.Date;

/**
 * @author: 
 * @description:
 */
public class JwtToken {

  private Claims claims;

  private JwtUtil jwtUtil;

  public JwtToken(JwtUtil util, String token) {
    this.jwtUtil = util;
    claims = jwtUtil.decode(token);
  }

  public boolean isTokenExpired(String token) {
    Date expiredDate = claims.getExpiration();
    return expiredDate.before(new Date());
  }

  public String getSubject() {
    return claims.getSubject();
  }

  public <T> T getClaim(String claim) {
    return (T) claims.get(claim);
  }

}
