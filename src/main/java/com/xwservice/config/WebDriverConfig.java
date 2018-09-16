package com.xwservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 浏览器插件信息配置类
 */
@Configuration
@PropertySource("classpath:application.properties")
public class WebDriverConfig {

    /**
     * google浏览器位置
     */
    @Value("${GoogleDriverPath}")
    private String googleDriverPath;

    public String getGoogleDriverPath() {
        return googleDriverPath;
    }

    public void setGoogleDriverPath(String googleDriverPath) {
        this.googleDriverPath = googleDriverPath;
    }

    @Bean
    public WebDriverConfig getWebDriverConfig() {
        WebDriverConfig webDriverConfig = new WebDriverConfig();
        webDriverConfig.setGoogleDriverPath(googleDriverPath);
        return webDriverConfig;
    }
}
