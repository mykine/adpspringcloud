package ug.aduser.application.mq.topic;

import lombok.Getter;

@Getter
public enum KafkaTopic {

    ADCLICK_MONITOR("adclickMonitor","广告点击监测");

    private String topic;
    private String desc;

    KafkaTopic(String topic,String desc){
        this.topic = topic;
        this.desc = desc;
    }
}
