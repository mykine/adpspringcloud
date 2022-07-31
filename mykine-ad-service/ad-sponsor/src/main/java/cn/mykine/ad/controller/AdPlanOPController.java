package cn.mykine.ad.controller;

import com.alibaba.fastjson.JSON;
import cn.mykine.ad.entity.AdPlan;
import cn.mykine.ad.exception.AdException;
import cn.mykine.ad.service.IAdPlanService;
import cn.mykine.ad.vo.AdPlanGetRequest;
import cn.mykine.ad.vo.AdPlanRequest;
import cn.mykine.ad.vo.AdPlanResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Jo@mykine
 */
@Slf4j
@RestController
public class AdPlanOPController {

    private final IAdPlanService adPlanService;

    @Autowired
    public AdPlanOPController(IAdPlanService adPlanService) {
        this.adPlanService = adPlanService;
    }

    @PostMapping("/create/adPlan")
    public AdPlanResponse createAdPlan(
            @RequestBody AdPlanRequest request) throws AdException {
        log.info("ad-sponsor: createAdPlan -> {}",
                JSON.toJSONString(request));
        return adPlanService.createAdPlan(request);
    }

    @PostMapping("/get/adPlan")
    public List<AdPlan> getAdPlanByIds(
            @RequestBody AdPlanGetRequest request) throws AdException {
        log.info("ad-sponsor: getAdPlanByIds -> {}",
                JSON.toJSONString(request));
        return adPlanService.getAdPlanByIds(request);
    }

//    @GetMapping("/list/adPlan")
//    public List<AdPlan> listAdPlan(
//            @RequestBody AdPlanGetRequest request) throws AdException {
//        log.info("ad-sponsor: getAdPlanByIds -> {}",
//                JSON.toJSONString(request));
//        return adPlanService.getAdPlanByIds(request);
//    }

    @PutMapping("/update/adPlan")
    public AdPlanResponse updateAdPlan(
            @RequestBody AdPlanRequest request) throws AdException {
        log.info("ad-sponsor: updateAdPlan -> {}",
                JSON.toJSONString(request));
        return adPlanService.updateAdPlan(request);
    }

    @DeleteMapping("/delete/adPlan")
    public void deleteAdPlan(
            @RequestBody AdPlanRequest request) throws AdException {
        log.info("ad-sponsor: deleteAdPlan -> {}",
                JSON.toJSONString(request));
        adPlanService.deleteAdPlan(request);
    }
}
