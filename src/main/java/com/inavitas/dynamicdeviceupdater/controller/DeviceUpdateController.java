package com.inavitas.dynamicdeviceupdater.controller;

import com.inavitas.dynamicdeviceupdater.bean.IPAddressData;
import com.inavitas.dynamicdeviceupdater.bean.KafkaData;
import com.inavitas.dynamicdeviceupdater.constants.EnvironmentVariables;
import com.inavitas.dynamicdeviceupdater.helper.DeviceUpdateHelper;
import com.inavitas.dynamicdeviceupdater.kafka.KafkaMessageProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/devices")
public class DeviceUpdateController implements InitializingBean{

    public static final Logger LOGGER = LoggerFactory.getLogger(DeviceUpdateController.class);

    //@Value(value = "${kafka.topic.device-update}")
    //private String deviceUpdateTopic;


   // @Autowired
  //  KafkaMessageProducer kafkaMessageProducer;


    private String mdcDeviceIdentifierParam;

    @PutMapping(path = "/{deviceSerial}/metadata", consumes = "application/json")
    public void updateDeviceEventListener(@PathVariable(value = "deviceSerial") String deviceSerial, @RequestParam String protocolCode, @RequestBody IPAddressData ipAddressData) {

        KafkaData kafkaData = new KafkaData(deviceSerial, ipAddressData != null ? ipAddressData.getIp() : "", DeviceUpdateHelper.getNow(), protocolCode);

        LOGGER.info("IP address object : {} has been received for device serial : {}", ipAddressData.getIp(), deviceSerial);

        final Map<String, String> contextMap = MDC.getCopyOfContextMap();
        LOGGER.info("Checking MDC content {}", contextMap != null? contextMap.get("deviceSerial") : "");

       // kafkaMessageProducer.sendToTopic(kafkaData, MDC.getCopyOfContextMap());

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        MDC.clear();
    }
}
