package com.csw.java.site.base;

import com.csw.java.bean.DetailData;
import com.csw.java.bean.VideoListItem;

import java.util.List;

public interface IVideoSearchAPI {

    /**
     * 根据关键字搜索相关视频
     *
     *
     * @param baseUrl
     * @param keyWord 关键字
     * @return 相关视频列表
     */
    List<VideoListItem> search(String baseUrl, String keyWord);

    /**
     * 获取播放源
     * @param baseUrl 服务器地址
     * @param detailUrl 详情页地址
     */
    DetailData getPlaySrc(String baseUrl, String detailUrl);

    /**
     * 获取视频链接
     * @param baseUrl 服务器地址
     * @param playUrl 播放页地址
     * @param data
     */
    String getVideoUrl(String baseUrl, String playUrl, String data);
}
