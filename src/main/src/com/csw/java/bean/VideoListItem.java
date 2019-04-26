package com.csw.java.bean;

import java.io.Serializable;

public class VideoListItem implements Serializable {

    private String name;//片名
    private String coverUrl;//视频封面
    private String desc;//视频描述
    private String detailPageKey;//详情页key

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDetailPageKey() {
        return detailPageKey;
    }

    public void setDetailPageKey(String detailPageKey) {
        this.detailPageKey = detailPageKey;
    }

    public boolean enable() {
        return name != null && detailPageKey != null;
    }
}
