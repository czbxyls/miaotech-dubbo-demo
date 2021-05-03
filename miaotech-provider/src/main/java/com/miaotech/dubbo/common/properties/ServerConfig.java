package com.miaotech.dubbo.common.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@RefreshScope
@Data
@Configuration
public class ServerConfig {

    @Value("${maxCollectionLimit:100}")
    int maxCollectionLimit;

    @Value("${userSessionTime:86400}")
    int userSessionTime;

}
