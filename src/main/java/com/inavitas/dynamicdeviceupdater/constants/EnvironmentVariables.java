package com.inavitas.dynamicdeviceupdater.constants;

public enum EnvironmentVariables {
    KAFKA_BROKER("KAFKA_BROKER"),
    MDC_DEVICE_PARAM("MDC_DEVICE_PARAM"),
    KAFKA_TOPIC_DEVICE_UPDATE("KAFKA_TOPIC_DEVICE_UPDATE");

    private final String name;

    EnvironmentVariables(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public String getValue(){
        return System.getenv(this.name );
    }
}