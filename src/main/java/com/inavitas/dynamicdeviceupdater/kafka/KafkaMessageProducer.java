package com.inavitas.dynamicdeviceupdater.kafka;

//import com.inavitas.dynamicdeviceupdater.KafkaProducerConfig;
import com.inavitas.dynamicdeviceupdater.bean.KafkaData;
import com.inavitas.dynamicdeviceupdater.constants.EnvironmentVariables;
import com.inavitas.dynamicdeviceupdater.controller.DeviceUpdateController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Map;

@Service
public class KafkaMessageProducer {

    public static final Logger LOGGER = LoggerFactory.getLogger(DeviceUpdateController.class);

    //@Value(value = "${kafka.topic.device-update}")
    //private String deviceUpdateTopic;

    @Autowired
    KafkaTemplate kafkaTemplate;

    public void sendToTopic(KafkaData kafkaMessage, Map<String, String> contextMap) {

        ListenableFuture<SendResult<String, String>> sendResultListenableFuture = kafkaTemplate.send(/*KafkaProducerConfig.deviceUpdateTopic*/"device.update.topic", kafkaMessage);

        sendResultListenableFuture.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onFailure(Throwable ex) {
                MDC.setContextMap(contextMap);

                LOGGER.error("Error in sending record to kafka : {}. JSON : {} for device Serial : {} ", new Object[] { ex
                        .getMessage(), kafkaMessage.getIp(), kafkaMessage.getDeviceSerial() });
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                MDC.setContextMap(contextMap);

                LOGGER.info("Record sent to Kafka with ip {} for device serial : {} to partition {} with offset {} ", new Object[] {
                        kafkaMessage.getIp(), kafkaMessage.getDeviceSerial(), Integer.valueOf(result.getRecordMetadata().partition()), Long.valueOf(result.getRecordMetadata().offset()) });

            }
        });
    }
}




