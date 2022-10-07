package cn.mykine.o2o.user.infrastructure.mapper;

import cn.mykine.o2o.user.infrastructure.dataobject.UserAccountDo;
import org.apache.ibatis.annotations.*;

/**
 * @author: 
 * @description:
 */
@Mapper
public interface UserAccountMapper {

  @Select({"SELECT * from user_account where wx_open_id = #{wxOpenId}"})
  UserAccountDo selectByOpenId(String wxOpenId);

  @Update({
      "update user_account set contract_id = #{contractId}, wx_open_id = #{wxOpenId}, wx_union_id = #{wxUnionId} where account_id = #{accountId}"})
  int update(UserAccountDo userAccountDo);

  @Insert({
      "insert into user_account (contract_id, wx_open_id, wx_union_id) values (#{contractId}, #{wxOpenId}, #{wxUnionId})"})
  @Options(useGeneratedKeys = true, keyProperty = "accountId")
  int insert(UserAccountDo userAccountDo);

}