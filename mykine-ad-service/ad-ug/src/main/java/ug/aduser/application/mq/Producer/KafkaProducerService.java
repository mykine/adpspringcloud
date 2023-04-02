package ug.aduser.application.mq.Producer;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ug.aduser.application.mq.topic.KafkaTopic;
import ug.aduser.infrastructure.dataobject.AdClickDataDo;
import ug.aduser.infrastructure.dataobject.LoginUserDataDo;

import java.util.concurrent.Future;

@Service
@Slf4j
public class KafkaProducerService {

    @Autowired
    private Producer producer;

    public void sendClickData(AdClickDataDo record){
        ProducerRecord<String, Object> msg = new ProducerRecord<>(
                KafkaTopic.ADCLICK_MONITOR.getTopic(),
                record.getPlatform() + "_" + record.getClickId(),
                JSON.toJSONString(record)
        );
        try {
            producer.send(msg);
//            Future send = producer.send(msg);
//            Object res = send.get();
//            log.info("res={}",res);
        }catch (Exception e){
            log.error("kafka send err,topic:{},record:{}",
                    KafkaTopic.ADCLICK_MONITOR.getTopic(),record,e);
        }
    }


    public void sendLoginUserData(LoginUserDataDo record){
        ProducerRecord<String, Object> msg = new ProducerRecord<>(
                KafkaTopic.USER_ACTIVE_MONITOR.getTopic(),
                record.getId(),
                JSON.toJSONString(record)
        );
        try {
            producer.send(msg);
//            Future send = producer.send(msg);
//            Object res = send.get();
//            log.info("res={}",res);
        }catch (Exception e){
            log.error("sendLoginUserData kafka send err,topic:{},record:{}",
                    KafkaTopic.ADCLICK_MONITOR.getTopic(),record,e);
        }
    }

}
