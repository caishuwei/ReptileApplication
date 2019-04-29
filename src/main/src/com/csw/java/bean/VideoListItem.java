package com.csw.java.bean;

import java.io.Serializable;

public class VideoListItem implements Serializable {

    private static final long serialVersionUID = -4198603155745075903L;
    private String name;//片名
    private String coverUrl;//视频封面
    private String desc;//视频描述
    private String siteKey;//网站Key
    private String baseUrl;
    private String detailUrl;//详情页地址

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

    public String getSiteKey() {
        return siteKey;
    }

    public void setSiteKey(String siteKey) {
        this.siteKey = siteKey;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public boolean enable() {
        return name != null && detailUrl != null;
    }
}
