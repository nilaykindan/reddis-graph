package com.inavitas.dynamicdeviceupdater.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KafkaData {
  private String deviceSerial;

  private String ip;

  private String timestamp;

  private String protocolCode;

  public KafkaData(String deviceSerial, String ip, String timestamp, String protocolCode) {
    this.deviceSerial = deviceSerial;
    this.ip = ip;
    this.timestamp = timestamp;
    this.protocolCode = protocolCode;
  }
}
