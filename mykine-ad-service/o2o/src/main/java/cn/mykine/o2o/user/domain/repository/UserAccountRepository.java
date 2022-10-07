package cn.mykine.o2o.user.domain.repository;

import cn.mykine.o2o.user.domain.UserAccount;

/**
 * @author: 
 * @description:
 */
public interface UserAccountRepository {

  UserAccount getByOpenId(String openId);

  void add(UserAccount account);

  void update(UserAccount account);

}
