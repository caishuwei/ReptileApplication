package com.csw.java.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetailData implements Serializable {
    private static final long serialVersionUID = 4814300455865995637L;

    private String title;
    private String author;
    private String desc;
    private String siteKey;
    private String baseUrl;
    private List<ResourceGroup> resourceGroupList = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public List<ResourceGroup> getResourceGroupList() {
        return resourceGroupList;
    }

    public void setResourceGroupList(List<ResourceGroup> resourceGroupList) {
        this.resourceGroupList = resourceGroupList;
    }

    public static class ResourceGroup {
        private String name;
        private String desc;

        private List<Resource> resourceList = new ArrayList<>();


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public List<Resource> getResourceList() {
            return resourceList;
        }

        public void setResourceList(List<Resource> resourceList) {
            this.resourceList = resourceList;
        }
    }

    public static class Resource {
        private String name;
        private String playUrl;
        private ExtraData extraData;


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlayUrl() {
            return playUrl;
        }

        public void setPlayUrl(String playUrl) {
            this.playUrl = playUrl;
        }

        public ExtraData getExtraData() {
            return extraData;
        }

        public void setExtraData(ExtraData extraData) {
            this.extraData = extraData;
        }
    }

    public static class ExtraData {
        private Map<String, String> map = new HashMap<>();

        public Map<String, String> getMap() {
            return map;
        }

        public void setMap(Map<String, String> map) {
            this.map = map;
        }
    }
}
