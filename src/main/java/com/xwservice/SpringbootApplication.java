package com.xwservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.scheduling.annotation.EnableScheduling;


@ComponentScan(basePackages={"com.xwservice"}) // 扫描该包路径下的所有spring组件
/*@EnableJpaRepositories("me.shijunjie.dao") // JPA扫描该包路径下的Repositorie
 */
//@EntityScan("com.xwservice.bean") // 扫描实体类
//@ComponentScans({ @ComponentScan("com.xwservice.config")})
@EnableScheduling
@SpringBootApplication
public class SpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }
}
