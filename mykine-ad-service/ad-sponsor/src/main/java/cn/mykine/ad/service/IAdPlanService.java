package cn.mykine.ad.service;

import cn.mykine.ad.entity.AdPlan;
import cn.mykine.ad.exception.AdException;
import cn.mykine.ad.vo.AdPlanGetRequest;
import cn.mykine.ad.vo.AdPlanRequest;
import cn.mykine.ad.vo.AdPlanResponse;

import java.util.List;

/**
 * Created by Jo@mykine
 */
public interface IAdPlanService {

    /**
     * <h2>创建推广计划</h2>
     * */
    AdPlanResponse createAdPlan(AdPlanRequest request) throws AdException;

    /**
     * <h2>获取推广计划</h2>
     * */
    List<AdPlan> getAdPlanByIds(AdPlanGetRequest request) throws AdException;

    /**
     * <h2>更新推广计划</h2>
     * */
    AdPlanResponse updateAdPlan(AdPlanRequest request) throws AdException;

    /**
     * <h2>删除推广计划</h2>
     * */
    void deleteAdPlan(AdPlanRequest request) throws AdException;
}
