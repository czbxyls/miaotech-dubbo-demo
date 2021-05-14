package com.miaotech.user.common.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@RefreshScope
@Data
@Configuration
public class ServerConfig {

    @Value("${collection.maxLimit:100}")
    int maxCollectionLimit;

    @Value("${user.sessionTime:86400}")
    int userSessionTime;

}
