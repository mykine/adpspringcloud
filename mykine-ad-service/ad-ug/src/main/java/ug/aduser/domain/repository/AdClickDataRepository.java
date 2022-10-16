package ug.aduser.domain.repository;

import org.apache.ibatis.annotations.Param;
import ug.aduser.infrastructure.dataobject.AdClickDataDo;

import java.util.List;

/**
 * @author: 
 * @description:
 */
public interface AdClickDataRepository {

  /**
   * 用户点击广告行为数据
   * @param record
   * @return
   */
  int insert(AdClickDataDo record);

  List<AdClickDataDo> selectRecordsByStatusPlatformDeviceId(@Param("status") Integer status, @Param("platform") Integer platform, @Param("muid") String muid, @Param("iosDeviceid") String iosDeviceid,
                                                            @Param("imei") String imei, @Param("oaid") String oaid,
                                                            @Param("androidId") String androidId);

  List<AdClickDataDo> findOneByStatusPlatformDeviceId(@Param("status") Integer status,@Param("platform") Integer platform,@Param("muid") String muid, @Param("iosDeviceid") String iosDeviceid,
                                                      @Param("imei") String imei, @Param("oaid") String oaid,
                                                      @Param("androidId") String androidId);
  List<AdClickDataDo> findOneByStatusDeviceId(@Param("status") Integer status,@Param("muid") String muid, @Param("iosDeviceid") String iosDeviceid,
                                              @Param("imei") String imei, @Param("oaid") String oaid,@Param("oaidMd5") String oaidMd5,
                                              @Param("androidId") String androidId);

  List<AdClickDataDo> selectByDeviceId(@Param("status") Integer status,@Param("platform") Integer platform,@Param("muid") String muid, @Param("iosDeviceid") String iosDeviceid,
                                       @Param("imei") String imei, @Param("oaid") String oaid,
                                       @Param("androidId") String androidId);


  int updateActiveDataById(AdClickDataDo record);

  int updateActiveDataByPlatFormDeviceId(AdClickDataDo record);

  int updateClickDataByStatusPlatformDeviceId(AdClickDataDo record);

}
