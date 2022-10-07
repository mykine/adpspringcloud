package cn.mykine.o2o.merchant.infrastructure.mapper;

import cn.mykine.o2o.merchant.infrastructure.dataobject.CompanyDo;
import cn.mykine.o2o.user.infrastructure.dataobject.UserAccountDo;
import org.apache.ibatis.annotations.*;

/**
 * @author: 
 * @description:
 */
@Mapper
public interface CompanyMapper {

  @Select({"SELECT * from db_companies where id = #{id}"})
  CompanyDo selectById(String id);

}
