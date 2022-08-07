package cn.mykine.ad.client;

import cn.mykine.ad.client.vo.AdPlan;
import cn.mykine.ad.client.vo.AdPlanGetRequest;
import cn.mykine.ad.vo.CommonResponse;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Jo@mykine
 */
@Component
public class SponsorClientHystrix implements SponsorClient {

    @Override
    public CommonResponse<List<AdPlan>> getAdPlans(
            AdPlanGetRequest request) {
        return new CommonResponse<>(-1,
                "ad-sponsor error");
    }

    @Override
    public String test() {
        return "requst ad-sponsor test happens error";
    }


}
