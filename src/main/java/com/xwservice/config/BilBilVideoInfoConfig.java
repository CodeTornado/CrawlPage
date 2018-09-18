package com.xwservice.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


/**
 * 哔哩哔哩  配置类
 */
@Configuration
@PropertySource("classpath:bilbilConfig.properties")
public class BilBilVideoInfoConfig {

    @Value("${bilbil.sleepTime}")
    private long sleepTime;

    public long getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(long sleepTime) {
        this.sleepTime = sleepTime;
    }

    /**
     * 游戏区排行榜
     */
    @Value("${gamingZoneLeaderboardUrl}")
    private String gamingZoneLeaderboardUrl;

    public String getGamingZoneLeaderboardUrl() {
        return gamingZoneLeaderboardUrl;
    }

    public void setGamingZoneLeaderboardUrl(String gamingZoneLeaderboardUrl) {
        this.gamingZoneLeaderboardUrl = gamingZoneLeaderboardUrl;
    }

    @Bean
    public BilBilVideoInfoConfig getBilBilVideoInfoConfig() {
        BilBilVideoInfoConfig bilBilVideoInfoConfig = new BilBilVideoInfoConfig();
        bilBilVideoInfoConfig.setGamingZoneLeaderboardUrl(gamingZoneLeaderboardUrl);
        return bilBilVideoInfoConfig;
    }
}
