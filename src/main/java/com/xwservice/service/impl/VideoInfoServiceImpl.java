package com.xwservice.service.impl;

import com.xwservice.bean.VideoUrlInfo;
import com.xwservice.dao.VideoUrlInfoDao;
import com.xwservice.service.VideoInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoInfoServiceImpl implements VideoInfoService {

    @Autowired
    private VideoUrlInfoDao videoUrlInfoDao;

    public void insertVideoInfo(VideoUrlInfo videoInfo) {
        videoUrlInfoDao.insertVideoInfo(videoInfo);
    }

}
