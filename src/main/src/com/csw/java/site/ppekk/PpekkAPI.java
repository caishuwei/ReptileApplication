package com.csw.java.site.ppekk;

import com.csw.java.bean.VideoListItem;
import com.csw.java.site.base.IVideoSearchAPI;
import com.csw.java.utils.HttpUtils;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.WebRequest;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class PpekkAPI implements IVideoSearchAPI {
    public static String SITE_HOST = "https://www.ppekk2.com";
    private static final String SEARCH_URL = SITE_HOST + "/vod-search";

    @Override
    public List<VideoListItem> search(String keyWord) {
        List<VideoListItem> videoListItems = new ArrayList<>();
        try {
            WebRequest webRequest = new WebRequest(new URL(SEARCH_URL), HttpMethod.POST);
            webRequest.setRequestParameters(HttpUtils.generateRequestParameters("wd", URLEncoder.encode(keyWord, "UTF-8")));
            Document document = HttpUtils.getInstance().getHtmlPageResponseAsDocument(webRequest);
            Element searchBox = document.getElementsByClass("conBox search-box").first();
            Elements lis = searchBox.getElementsByTag("li");
            VideoListItem item;
            Element a, img, plot;
            for (Element li : lis) {
                item = new VideoListItem();
                a = li.selectFirst("a.play-pic");
                if (a != null) {
                    item.setDetailPageKey(SITE_HOST + a.attr("href"));
                    img = a.selectFirst("img[alt]");
                    if (img != null) {
                        item.setCoverUrl(img.attr("src"));
                        item.setName(img.attr("alt"));
                    }
                }
                plot = li.selectFirst("p.plot");
                if (plot != null) {
                    item.setDesc(plot.ownText());
                }
                if (item.enable()) {
                    videoListItems.add(item);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return videoListItems;
    }
}
