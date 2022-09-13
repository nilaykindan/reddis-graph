package com.inavitas.dynamicdeviceupdater.bean;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Getter
@Setter
public class TopicConfig {

  //@SerializedName("Broker")
  private String broker;
  
  //@SerializedName("MessageCount")
  private int messageCount;
  
  //@SerializedName("ClientId")
  private String clientId;
  
  //@SerializedName("TopicName")
  private String topicName;
  
  //@SerializedName("GroupIdConfig")
  private String groupIdConfig;
  
  //@SerializedName("MaxNoMessageFoundCount")
  private int maxNoMessageFoundCount;
  
  //@SerializedName("OffsetResetLatest")
  private String offsetResetLatest;
  
  //@SerializedName("OffsetResetEarlier")
  private String offsetResetEarlier;
  
  //@SerializedName("MaxPollRecords")
  private int maxPollRecords;
}
