package cn.mykine.ad.client;

import cn.mykine.ad.client.vo.AdPlan;
import cn.mykine.ad.client.vo.AdPlanGetRequest;
import cn.mykine.ad.vo.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Jo@mykine
 */
@FeignClient(value = "ad-sponsor",
        fallback = SponsorClientHystrix.class)
public interface SponsorClient {

    @RequestMapping(value = "/ad-sponsor/get/adPlan",
            method = RequestMethod.POST)
    CommonResponse<List<AdPlan>> getAdPlans(
            @RequestBody AdPlanGetRequest request);

    @RequestMapping(value = "/ad-sponsor/test",
            method = RequestMethod.GET)
    String test();
}
