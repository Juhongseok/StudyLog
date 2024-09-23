package com.jhs.dynamictable.global.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "dynamic")
public record TableProperties(
        String classPath
) {

}
