package cn.mykine.ad.service;

import cn.mykine.ad.vo.CreativeRequest;
import cn.mykine.ad.vo.CreativeResponse;

/**
 * Created by Jo@mykine
 */
public interface ICreativeService {

    CreativeResponse createCreative(CreativeRequest request);
}
