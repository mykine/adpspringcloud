package cn.mykine.o2o.infracore.idgenerator.impl;

import cn.mykine.o2o.infracore.idgenerator.UniqueIdGenerator;
import cn.mykine.o2o.infracore.idgenerator.impl.mapper.UniqueIdMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: 
 * @description:
 */
@Service
public class UniqueIdGeneratorImpl implements UniqueIdGenerator {

  @Autowired
  UniqueIdMapper mapper;

  @Override
  public long next() {
    UniqueIdDo id = new UniqueIdDo();
    mapper.nextUniqueId(id);
    return id.getId();
  }
}
