package cn.mykine.o2o.merchant.adapter.repository.impl;

import cn.mykine.o2o.infracore.config.EsTest;
import cn.mykine.o2o.merchant.domain.Company;
import cn.mykine.o2o.merchant.domain.repository.CompanyRepository;
import cn.mykine.o2o.merchant.infrastructure.dataobject.CompanyDo;
import cn.mykine.o2o.merchant.infrastructure.dataobject.CompanyEsDo;
import cn.mykine.o2o.merchant.infrastructure.mapper.CompanyMapper;
import cn.mykine.o2o.user.domain.UserAccount;
import cn.mykine.o2o.user.domain.repository.UserAccountRepository;
import cn.mykine.o2o.user.infrastructure.dataobject.UserAccountDo;
import cn.mykine.o2o.user.infrastructure.mapper.UserAccountMapper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.action.support.replication.ReplicationResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
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

  @Autowired
  private BulkProcessor bulkProcessor;

  @Override
  public Company getById(String id) {
    return null;
  }

  @Override
  public List<Company> listQuery(String key) {

    List<Company> list = new ArrayList<>();
    try {
      SearchRequest searchRequest = new SearchRequest("companies");
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

  /**
   * 单条数据同步新增数据
   * */
  @Override
  public boolean addOneSync(Company company) {
    CompanyEsDo companyDo = new CompanyEsDo();
    companyDo.setId(company.getId());
    companyDo.setName(company.getName());
    companyDo.setProvinceId(company.getProvinceId());
    companyDo.setProvinceName(company.getProvinceName());
    companyDo.setCityId(company.getCityId());
    companyDo.setCityName(company.getCityName());
    companyDo.setTags(company.getTags());
    companyDo.setServeStars(BigDecimal.valueOf(Double.valueOf(company.getServeStars())));

    IndexRequest indexRequest = new IndexRequest("companies");
    JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(companyDo));
    //    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    //    jsonObject.put("@timestamp", sdf.format(new Date()));
    jsonObject.put("@timestamp", new Date());//添加时间戳
    jsonObject.put("@version", "1");//添加版本
    indexRequest.source(JSON.toJSONString(jsonObject), XContentType.JSON);
    indexRequest.timeout(TimeValue.timeValueSeconds(1));
    indexRequest.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);
    indexRequest.create(true);
    indexRequest.id(companyDo.getId() + "");
    try {
      final IndexResponse res = highLevelClient.index(indexRequest, RequestOptions.DEFAULT);
      if(res.status().equals(RestStatus.CREATED)){
        return true;
      }
      log.warn("addOne fail,res:{}",res);
    }catch (Exception e){
      log.error("addOne exception,company:{}",company,e);
    }
    return false;
  }

  /**
   * 单条数据异步新增数据
   * */
  @Override
  public void addOneAsync(Company company) {
    IndexRequest indexRequest = new IndexRequest("test_demo");
    indexRequest.source(JSON.toJSONString(company), XContentType.JSON);
    indexRequest.timeout(TimeValue.timeValueSeconds(1));
    indexRequest.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);
    //数据为存储而不是更新
    indexRequest.create(false);
    indexRequest.id(company.getId() + "");
    highLevelClient.indexAsync(indexRequest, RequestOptions.DEFAULT, new ActionListener<IndexResponse>() {
      @Override
      public void onResponse(IndexResponse indexResponse) {
        ReplicationResponse.ShardInfo shardInfo = indexResponse.getShardInfo();
        if (shardInfo.getFailed() > 0) {
          for (ReplicationResponse.ShardInfo.Failure failure : shardInfo.getFailures()) {
            log.error("将id为：{}的数据存入ES时存在失败的分片，原因为：{}", indexRequest.id(), failure.getCause());
          }
        }
      }

      @Override
      public void onFailure(Exception e) {
        log.error("{}:存储es时异常，数据信息为", indexRequest.id(), e);
      }
    });

  }

  @Override
  public void addList(List<Company> companies) {
    List<IndexRequest> indexRequests = new ArrayList<>();

    companies.forEach(e -> {
      IndexRequest request = new IndexRequest("test_demo");
      //填充id
      request.id(e.getId() + "");
      //先不修改id
      request.source(JSON.toJSONString(e), XContentType.JSON);
      request.opType(DocWriteRequest.OpType.CREATE);
      indexRequests.add(request);
    });
    indexRequests.forEach(bulkProcessor::add);
  }
}
