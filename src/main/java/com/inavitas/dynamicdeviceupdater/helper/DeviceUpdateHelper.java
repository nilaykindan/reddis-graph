package com.inavitas.dynamicdeviceupdater.helper;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


public class DeviceUpdateHelper {

    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");

    public static String getNow(){

        LocalDateTime now = LocalDateTime.now();

        //ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Europe/Istanbul"));

        String formattedDateTime = now.format(formatter);

        return formattedDateTime;
    }
}
