package cn.mykine.ad.service;

import cn.mykine.ad.exception.AdException;
import cn.mykine.ad.vo.*;

/**
 * Created by Jo@mykine
 */
public interface IAdUnitService {

    AdUnitResponse createUnit(AdUnitRequest request) throws AdException;

    AdUnitKeywordResponse createUnitKeyword(AdUnitKeywordRequest request)
        throws AdException;

    AdUnitItResponse createUnitIt(AdUnitItRequest request)
        throws AdException;

    AdUnitDistrictResponse createUnitDistrict(AdUnitDistrictRequest request)
        throws AdException;

    CreativeUnitResponse createCreativeUnit(CreativeUnitRequest request)
        throws AdException;
}
