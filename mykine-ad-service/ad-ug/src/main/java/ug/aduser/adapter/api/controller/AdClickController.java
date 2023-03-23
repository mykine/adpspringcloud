package ug.aduser.adapter.api.controller;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ug.aduser.adapter.api.request.AdClickRequest;
import ug.aduser.adapter.api.vo.AdClickResult;
import ug.aduser.adapter.api.vo.AdClickdataVO;
import ug.aduser.application.service.AdClickdataService;
import ug.infracore.common.CommonUtil;

@Api(value = "广告点击demo接口")
@RestController
@RequestMapping("/adclick")
@Slf4j
public class AdClickController {

//    //这是创建log4j2的日志对象,在org.apache.logging.log4j包路径下，而log4j的包路径是log4j
//    private static final Logger log = LogManager.getLogger();

//private static Logger log = LoggerFactory.getLogger(AdClickController.class);


    @Autowired
    AdClickdataService adClickdataService;


    @ApiOperation("广告点击行为记录")
    @GetMapping("/record")
    public AdClickResult record(@ApiParam(value = "点击行为数据",required = true) AdClickRequest request){
        log.info("AdClickController record ,request is {}", request);
        String threadName = Thread.currentThread().getName();
        AdClickdataVO adClickdataVO = AdClickdataVO.builder()
                .platform(request.getPlatform())
                .iosDeviceid(CommonUtil.parseDeviceIdStr(request.getIdfa()))
                .imei(CommonUtil.parseDeviceIdStr(request.getImei_md5()))
                .oaid(CommonUtil.parseDeviceIdStr(request.getOaid()))
                .androidId(CommonUtil.parseDeviceIdStr(request.getAndroid_id_md5()))
//                .clickTime(request.getTs()==null ? System.currentTimeMillis() : request.getTs())
                .clickTime(System.currentTimeMillis())
                .adId(request.getAid())
                .adName(CommonUtil.parseStrValue(request.getAdName()))
//                .unitId(request.getUid())
//                .campainId(request.getPid())
                .clickId(request.getClick_id())
//                .accountId(request.getUserid())
//                .paramStr(request.getSign())
//                .callbackParam(CommonUtil.parseStrValue(request.getCallback_url()))
//                .extra(JSON.toJSONString(request))
                .build();
        try{
            //判空
            if(
                    StringUtils.isEmpty(adClickdataVO.getImei())
                            && StringUtils.isEmpty(adClickdataVO.getOaid())
                            && StringUtils.isEmpty(adClickdataVO.getAndroidId())
                            && StringUtils.isEmpty(adClickdataVO.getIosDeviceid())
            ){
                return AdClickResult.success(50001,"empty");
            }
//            int ret = adClickdataService.saveClickData(adClickdataVO);
            adClickdataService.sendClickData(adClickdataVO);
            return AdClickResult.success(0,"ok");
        }catch (Exception e){
            log.warn("BaidusemController setDataFromBaiduSem error {} ,baidusemRequest={}", e,request);
        }
        return AdClickResult.success(50000,"error");
    }


}
