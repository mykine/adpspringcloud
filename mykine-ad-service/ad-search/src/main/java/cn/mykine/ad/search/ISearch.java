package cn.mykine.ad.search;

import cn.mykine.ad.search.vo.SearchRequest;
import cn.mykine.ad.search.vo.SearchResponse;

/**
 * Created by Jo@mykine
 */
public interface ISearch {

    SearchResponse fetchAds(SearchRequest request);
}
