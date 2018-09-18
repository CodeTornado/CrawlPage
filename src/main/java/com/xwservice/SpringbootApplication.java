package com.xwservice;

import com.xwservice.service.GrabBilBilVideoInfoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.Resource;


@ComponentScan(basePackages={"com.xwservice"}) // 扫描该包路径下的所有spring组件
/*@EnableJpaRepositories("me.shijunjie.dao") // JPA扫描该包路径下的Repositorie
 */
//@EntityScan("com.xwservice.bean") // 扫描实体类
//@ComponentScans({ @ComponentScan("com.xwservice.config")})
@EnableScheduling
@SpringBootApplication
public class SpringbootApplication implements CommandLineRunner {


    @Resource
    private GrabBilBilVideoInfoService grabBilBilVideoInfoService;


    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

    // springboot运行后此方法首先被调用
    // 实现CommandLineRunner抽象类中的run方法
    @Override
    public void run(String... args) throws Exception {
        System.out.println("springboot启动完成！");

//        获得bilbil游戏区热门视频信息并全部入库
        new Thread(new Runnable() {
            @Override
            public void run() {
                grabBilBilVideoInfoService.getBilBilVideoGameAreaPopularInfoToDB();
            }
        }).start();
        System.out.println("开始抓取数据" );
    }
}
