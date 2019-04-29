package com.csw.java.site;

import com.csw.java.bean.Site;
import com.csw.java.site.ppekk.PpekkAPI;
import com.csw.java.utils.HttpUtils;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;

public class SiteFactory {

    /**
     * 获取可用的站点列表
     */
    public static List<Site> getEnableSites() {
        List<Site> sites = new ArrayList<>();
        try {
            Site site = new Site();
            site.setName(PpekkAPI.SITE_NAME);
            site.setKey(PpekkAPI.SITE_KEY);
            HtmlPage htmlPage = HttpUtils.getInstance().getHtmlPage(site.getKey());
            site.setBaseUrl(htmlPage.getBaseURI());
            sites.add(site);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sites;
    }
}
