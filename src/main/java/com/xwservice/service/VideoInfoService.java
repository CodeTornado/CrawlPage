package com.xwservice.service;

import com.xwservice.bean.VideoUrlInfo;

/**
 * 视频信息
 */
public interface VideoInfoService {

    /**
     * 视频详细信息入DB
     * @param videoInfo
     */
    public void insertVideoInfo(VideoUrlInfo videoInfo);

}
