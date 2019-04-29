package com.csw.java.site.ppekk;

import java.util.List;

public class PlaySrc {

    private List<String> Vod;
    private List<Data> Data;

    public List<String> getVod() {
        return Vod;
    }

    public void setVod(List<String> vod) {
        Vod = vod;
    }

    public List<PlaySrc.Data> getData() {
        return Data;
    }

    public void setData(List<PlaySrc.Data> data) {
        Data = data;
    }

    public static class Data {
        private String servername;
        private String playname;
        private List<List<String>> playurls;

        public String getServername() {
            return servername;
        }

        public void setServername(String servername) {
            this.servername = servername;
        }

        public String getPlayname() {
            return playname;
        }

        public void setPlayname(String playname) {
            this.playname = playname;
        }

        public List<List<String>> getPlayurls() {
            return playurls;
        }

        public void setPlayurls(List<List<String>> playurls) {
            this.playurls = playurls;
        }
    }
}
