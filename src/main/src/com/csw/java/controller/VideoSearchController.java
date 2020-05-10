package com.csw.java.controller;

import com.csw.java.bean.DetailData;
import com.csw.java.bean.Site;
import com.csw.java.bean.VideoListItem;
import com.csw.java.site.SiteFactory;
import com.csw.java.site.ppekk.PpekkAPI;
import com.csw.java.utils.GSONUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/video")
public class VideoSearchController {


    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String getVideoHomePage(Model model) {
        List<Site> sites = SiteFactory.getEnableSites();
        model.addAttribute("sites", GSONUtils.toJSONString(sites));
        return "home";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/search")
    public String searchVideo(Model model, String word, String siteKey, String baseUrl) {
        if (siteKey == null || baseUrl == null) {
            return "error";
        }
        if (word == null) {
            word = "";
        }
        if (baseUrl.endsWith("/")) {
            baseUrl = baseUrl.substring(0, baseUrl.length() - 1);
        }
        model.addAttribute("title", word);
        switch (siteKey) {
            case PpekkAPI.SITE_KEY:
                List<VideoListItem> itemList = new PpekkAPI().search(baseUrl, word);
                model.addAttribute("videoListItems", GSONUtils.toJSONString(itemList));
                break;
        }
        return "search";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/detail")
    public String getVideoDetail(Model model, String siteKey, String baseUrl, String detailUrl, String videoName) {
        if (siteKey == null || baseUrl == null || detailUrl == null) {
            return "error";
        }
        model.addAttribute("title", videoName);
        model.addAttribute("videoName", videoName);
        if (baseUrl.endsWith("/")) {
            baseUrl = baseUrl.substring(0, baseUrl.length() - 1);
        }
        switch (siteKey) {
            case PpekkAPI.SITE_KEY:
                DetailData detailData = new PpekkAPI().getPlaySrc(baseUrl, detailUrl);
                model.addAttribute("detailData", GSONUtils.toJSONString(detailData));
                break;
        }
        return "detail";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/play")
    public String getPlayHtml(Model model, String siteKey, String baseUrl, String playUrl, String data, String videoName, String sectionName) {
        if (siteKey == null || baseUrl == null || playUrl == null) {
            return "error";
        }
        model.addAttribute("title", videoName + " " + sectionName);
        if (baseUrl.endsWith("/")) {
            baseUrl = baseUrl.substring(0, baseUrl.length() - 1);
        }
        switch (siteKey) {
            case PpekkAPI.SITE_KEY:
                String url = new PpekkAPI().getVideoUrl(baseUrl, playUrl, data);
                model.addAttribute("url", url);
                break;
        }
        return "play";
    }
}
