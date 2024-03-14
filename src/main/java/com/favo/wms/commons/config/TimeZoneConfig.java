package com.favo.wms.commons.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Configuration
public class TimeZoneConfig {

    public static final Logger console = LoggerFactory.getLogger(TimeZoneConfig.class);

    @Bean
    public ZoneId defaultZoneId(){

        var timezone = ZonedDateTime.now(ZoneId.systemDefault());
        console.info(String.format("@@WMS api started with default timezone: %s", timezone.getZone().getId()));

        return timezone.getZone();
    }

}
