package ug.aduser.adapter.repository.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ug.aduser.domain.repository.AdClickDataRepository;
import ug.aduser.infrastructure.dataobject.AdClickDataDo;
import ug.aduser.infrastructure.mapper.AdClickDataMapper;

import java.util.List;

/**
 * @author: 
 * @description:
 */
@Repository
public class AdClickDataRepositoryImpl implements AdClickDataRepository {

  @Autowired
  private AdClickDataMapper adClickDataMapper;


  @Override
  public int insert(AdClickDataDo record) {
    return adClickDataMapper.insert(record);
  }

  @Override
  public List<AdClickDataDo> selectRecordsByStatusPlatformDeviceId(Integer status,
                                                                   Integer platform,
                                                                   String muid,
                                                                   String iosDeviceid,
                                                                   String imei,
                                                                   String oaid,
                                                                   String androidId) {
    return adClickDataMapper.selectRecordsByStatusPlatformDeviceId(status,platform,muid,iosDeviceid,imei,oaid,androidId);
  }

  @Override
  public List<AdClickDataDo> findOneByStatusPlatformDeviceId(Integer status,
                                                             Integer platform,
                                                             String muid,
                                                             String iosDeviceid,
                                                             String imei,
                                                             String oaid,
                                                             String androidId) {
    return adClickDataMapper.findOneByStatusPlatformDeviceId(status,platform,muid,iosDeviceid,imei,oaid,androidId);
  }

  @Override
  public List<AdClickDataDo> findOneByStatusDeviceId(Integer status,
                                                     String muid,
                                                     String iosDeviceid,
                                                     String imei,
                                                     String oaid,
                                                     String oaidMd5,
                                                     String androidId) {
    return adClickDataMapper.findOneByStatusDeviceId(status,muid,iosDeviceid,imei,oaid,oaidMd5,androidId);
  }

  @Override
  public List<AdClickDataDo> selectByDeviceId(Integer status,
                                              Integer platform,
                                              String muid,
                                              String iosDeviceid,
                                              String imei,
                                              String oaid,
                                              String androidId) {
    return adClickDataMapper.selectByDeviceId(status,platform,muid,iosDeviceid,imei,oaid,androidId);
  }

  @Override
  public int updateActiveDataById(AdClickDataDo record) {
    return adClickDataMapper.updateActiveDataById(record);
  }

  @Override
  public int updateActiveDataByPlatFormDeviceId(AdClickDataDo record) {
    return adClickDataMapper.updateActiveDataByPlatFormDeviceId(record);
  }

  @Override
  public int updateClickDataByStatusPlatformDeviceId(AdClickDataDo record) {
    return adClickDataMapper.updateActiveDataById(record);
  }
}
