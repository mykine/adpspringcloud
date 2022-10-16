package ug.aduser.application.service.impl;

import com.alibaba.druid.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import ug.aduser.adapter.api.vo.AdClickdataVO;
import ug.aduser.application.service.AdClickdataService;
import ug.aduser.domain.repository.AdClickDataRepository;
import ug.aduser.infrastructure.dataobject.AdClickDataDo;
import ug.aduser.infrastructure.mapper.AdClickDataMapper;
import ug.infracore.common.CommonUtil;

import java.util.List;


@Service
@Slf4j
public class AdClickdataServiceImpl implements AdClickdataService {

    @Autowired
    private AdClickDataRepository adClickDataRepository;

    @Override
    public int saveClickData(AdClickdataVO adClickdataVO) {
        AdClickDataDo adClickDataDo = new AdClickDataDo();
        adClickDataDo.setPlatform(adClickdataVO.getPlatform());
        adClickDataDo.setIosDeviceid(adClickdataVO.getIosDeviceid());
        adClickDataDo.setImei(adClickdataVO.getImei());
        adClickDataDo.setOaid(adClickdataVO.getOaid());
        adClickDataDo.setAndroidId(adClickdataVO.getAndroidId());
        adClickDataDo.setCampainId(adClickdataVO.getCampainId());
        adClickDataDo.setUnitId(adClickdataVO.getUnitId());
        adClickDataDo.setAdId(adClickdataVO.getAdId());
        adClickDataDo.setAdName(adClickdataVO.getAdName());
        adClickDataDo.setProgress(adClickdataVO.getProgress());
        adClickDataDo.setExtra(adClickdataVO.getExtra());
        adClickDataDo.setClickTime(adClickdataVO.getClickTime());
        adClickDataDo.setClickId(adClickdataVO.getClickId());
        adClickDataDo.setParamStr(adClickdataVO.getParamStr());
        adClickDataDo.setPkg(adClickdataVO.getPkg());
        adClickDataDo.setCallbackParam(adClickdataVO.getCallbackParam());
        adClickDataDo.setAccountId(adClickdataVO.getAccountId());
        adClickDataDo.setMaterialId(adClickdataVO.getMaterialId());
        try {
            return adClickDataRepository.insert(adClickDataDo);
        } catch (DuplicateKeyException ex) {
            log.info("adclick setFirstData is insert error:{},adClickdataVO:{}", ex.getMessage(),adClickdataVO);
            return 0;
        }
    }

    @Override
    public List<AdClickDataDo> selectLastSomeRecordsByStatusPlatformDeviceId(
            Integer status,
            Integer platform ,
            String muid,
            String iosDeviceid,
            String imei,
            String oaid,
            String androidId
    ) {
        return adClickDataRepository.selectRecordsByStatusPlatformDeviceId(status,platform,muid, iosDeviceid, imei, oaid,androidId);
    }

    @Override
    public AdClickDataDo findLastOneRecordByStatusPlatformDeviceId(
            Integer status,
            Integer platform ,
            String muid,
            String iosDeviceid,
            String imei,
            String oaid,
            String androidId
    ) {
        List<AdClickDataDo> list = adClickDataRepository.findOneByStatusPlatformDeviceId(status,platform,muid, iosDeviceid, imei, oaid,androidId);
        AdClickDataDo one = ( CollectionUtils.isEmpty(list) ? null : list.get(0) );
        return one;
    }

    @Override
    public AdClickDataDo findLastOneRecordByStatusDeviceId(
            Integer status,
            String muid,
            String iosDeviceid,
            String imei,
            String oaid,
            String oaidMd5,
            String androidId
    ) {
        List<AdClickDataDo> list = adClickDataRepository.findOneByStatusDeviceId(status,muid, iosDeviceid, imei, oaid,oaidMd5,androidId);
        AdClickDataDo one = ( CollectionUtils.isEmpty(list) ? null : list.get(0) );
        return one;
    }


    @Override
    public List<AdClickDataDo> selectUserDataById(AdClickDataDo adClickDataDo) {
        //通用字段
        Integer status = adClickDataDo.getStatus();
        Integer platfrom = adClickDataDo.getPlatform();
        String muid = adClickDataDo.getMuid();

        //ios 使用
        String iosDeviceid = adClickDataDo.getIosDeviceid();

        //android 使用
        String imei = adClickDataDo.getImei();
        String oaid = adClickDataDo.getOaid();
        String androidId = adClickDataDo.getAndroidId();
        return adClickDataRepository.selectByDeviceId(status,platfrom,muid,iosDeviceid,imei,oaid,androidId);
    }

    @Override
    public AdClickDataDo activeUserData(AdClickdataVO adClickdataVO) {
        AdClickDataDo activeRecord = null;//被激活的对象
        log.info("adclickdata begin  updateActiveDataById {}", adClickdataVO);

        Long cid = adClickdataVO.getCid();
        String iosDeviceid = adClickdataVO.getIosDeviceid();
        String imei = adClickdataVO.getImei();
        String oaid = adClickdataVO.getOaid();
        String androidId = adClickdataVO.getAndroidId();

        int status;
        if (!StringUtils.isEmpty(iosDeviceid) || !StringUtils.isEmpty(imei) ||
                !StringUtils.isEmpty(oaid) || !StringUtils.isEmpty(androidId)) {

            //用户最近未激活的行为记录列表
            activeRecord= this.findLastOneRecordByStatusDeviceId(
                    0,
                    adClickdataVO.getMuid(),
                    adClickdataVO.getIosDeviceid(),
                    adClickdataVO.getImei(),
                    adClickdataVO.getOaid(),
                    adClickdataVO.getOaidMd5(),
                    adClickdataVO.getAndroidId()
            );

            if (activeRecord==null) {
                log.info("adCLICKData 没有匹配{}", adClickdataVO);
                return null;
            }
            log.info("activeRecord 匹配{}", activeRecord);

            //判断是否属于激活条件
            Long nowTimeMillSeconds = CommonUtil.nowTimeMillSeconds();
            boolean checkActive = CommonUtil.checkActive(activeRecord.getClickTime(),nowTimeMillSeconds);
            if ( !checkActive ) {
                log.info("upload clickData update data is out of 5 days , activeRecord is {},nowTimeMillSeconds={}",activeRecord,nowTimeMillSeconds);
                return null;//超过5天的记录不算激活
            }

            //构造要保存的数据，更新记录
            AdClickDataDo adClickDataDo = new AdClickDataDo();
            adClickDataDo.setId(activeRecord.getId());
            adClickDataDo.setCid(cid);
            adClickDataDo.setStatus(1);//状态1-已回传激活,2-已回传次留
            adClickDataDo.setActiveTime(nowTimeMillSeconds);
            adClickDataDo.setActiveDevice(adClickdataVO.getActiveDevice());
            int updateRes =  adClickDataRepository.updateActiveDataById(adClickDataDo);
            log.info("upload clickData update data is {}, updateRes is {}",adClickDataDo, updateRes);
        }
        return activeRecord;//激活的记录
    }


}
