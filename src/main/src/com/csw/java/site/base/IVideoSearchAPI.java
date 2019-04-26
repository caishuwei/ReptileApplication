package com.csw.java.site.base;

import com.csw.java.bean.VideoListItem;

import java.util.List;

public interface IVideoSearchAPI {

    /**
     * 根据关键字搜索相关视频
     *
     * @param keyWord 关键字
     * @return 相关视频列表
     */
    List<VideoListItem> search(String keyWord);

}
