package cn.mykine.o2o.merchant.adapter.repository.impl;

import cn.mykine.o2o.merchant.domain.Company;
import cn.mykine.o2o.merchant.domain.repository.CompanyRepository;
import cn.mykine.o2o.merchant.infrastructure.mapper.CompanyMapper;
import cn.mykine.o2o.user.domain.UserAccount;
import cn.mykine.o2o.user.domain.repository.UserAccountRepository;
import cn.mykine.o2o.user.infrastructure.dataobject.UserAccountDo;
import cn.mykine.o2o.user.infrastructure.mapper.UserAccountMapper;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author: 
 * @description:
 */
@Repository
@Slf4j
public class CompanyRepositoryImpl implements CompanyRepository {

  @Autowired
  private CompanyMapper companyMapper;

  @Autowired
  private RestHighLevelClient highLevelClient;

  @Override
  public Company getById(String id) {
    return null;
  }

  @Override
  public List<Company> listQuery(String key) {

    List<Company> list = new ArrayList<>();
    try {
      SearchRequest searchRequest = new SearchRequest();
      SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
      sourceBuilder.query(QueryBuilders.matchQuery("name",key));
      sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
      searchRequest.source(sourceBuilder);
      SearchResponse searchRes = highLevelClient.search(searchRequest, RequestOptions.DEFAULT);
      SearchHit[] hits = searchRes.getHits().getHits();
      for (int i =0;i<hits.length;i++){
        Company item = new Company();
        item.setId(Long.valueOf(hits[i].getSourceAsMap().get("id").toString()));
        item.setName(hits[i].getSourceAsMap().get("name").toString());
        item.setProvinceName(hits[i].getSourceAsMap().get("province_name").toString());
        item.setCityName(hits[i].getSourceAsMap().get("city_name").toString());
        item.setServeStars(hits[i].getSourceAsMap().get("serve_stars").toString());
        list.add(item);
      }
    }catch (Exception e){
      log.error("listQuery exception,key:{}",key,e);
    }

    return list;
  }


}
