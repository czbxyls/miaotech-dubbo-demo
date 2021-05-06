package com.miaotech.web.common;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class WebConfig {

    @Value("${rest.response.wrapper}")
    private boolean isRespWrapper;
}
