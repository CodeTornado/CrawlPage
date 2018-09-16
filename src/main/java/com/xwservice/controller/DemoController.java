package com.xwservice.controller;


import javax.annotation.Resource;

import com.xwservice.bean.VideoUrlInfo;
import com.xwservice.service.GrabBilBilVideoInfoService;
import com.xwservice.service.VideoInfoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo1")
public class DemoController {

    @Resource
    private VideoInfoService videoInfoService;

    @Resource
    private GrabBilBilVideoInfoService grabBilBilVideoInfoService;

    /**
     * 测试保存数据方法.
     *
     * @return
     */
    @RequestMapping("/insertVideoInfo")
    public String save() {

        VideoUrlInfo videoInfo = new VideoUrlInfo();

        videoInfo.setImgUrl("bbbbb");
        videoInfo.setUpName("bbbaaaabb");

        videoInfoService.insertVideoInfo(videoInfo);
        return "ok.DemoController.save";

    }


    /**
     * 获得bilbil游戏区热门视频信息并全部入库
     *
     * @return
     */
    @RequestMapping("/getBilBilVideoGameAreaPopularInfoToDB")
    public String getBilBilVideoGameAreaPopularInfoToDB() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                grabBilBilVideoInfoService.getBilBilVideoGameAreaPopularInfoToDB();
            }
        }).start();

        return "ok.getBilBilVideoGameAreaPopularInfoToDB>>>run";

    }
}