package ug.aduser.application.service;


import org.springframework.stereotype.Service;
import ug.aduser.adapter.api.vo.AdClickdataVO;
import ug.aduser.adapter.api.vo.LoginUserDataVO;
import ug.aduser.infrastructure.dataobject.AdClickDataDo;

import java.util.List;

/**
 */
@Service
public interface AdClickdataService {

    int saveClickData(AdClickdataVO adClickdataVO);

    void sendClickData(AdClickdataVO adClickdataVO);

    void sendLoginUserData(LoginUserDataVO vo);

    List<AdClickDataDo> selectLastSomeRecordsByStatusPlatformDeviceId(
            Integer status,
            Integer platform ,
            String muid,
            String iosDeviceid,
            String imei,
            String oaid,
            String androidId
    );

    AdClickDataDo findLastOneRecordByStatusPlatformDeviceId(
            Integer status,
            Integer platform ,
            String muid,
            String iosDeviceid,
            String imei,
            String oaid,
            String androidId
    );

    AdClickDataDo findLastOneRecordByStatusDeviceId(
            Integer status,
            String muid,
            String iosDeviceid,
            String imei,
            String oaid,
            String oaidMd5,
            String androidId
    );

    public List<AdClickDataDo> selectUserDataById(AdClickDataDo adClickDataDo);

    AdClickDataDo activeUserData(AdClickdataVO adClickdataVO);


}
