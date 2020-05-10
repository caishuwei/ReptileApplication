package com.csw.java.site.ppekk;

import com.csw.java.bean.DetailData;
import com.csw.java.bean.VideoListItem;
import com.csw.java.site.base.IVideoSearchAPI;
import com.csw.java.utils.GSONUtils;
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
    public static final String SITE_NAME = "ppekk";
    public static final String SITE_KEY = "http://www.ppekk.com";
    private static final String SEARCH_PATH = "/vod-search";

    @Override
    public List<VideoListItem> search(String baseUrl, String keyWord) {
        List<VideoListItem> videoListItems = new ArrayList<>();
        try {
            WebRequest webRequest = new WebRequest(new URL(baseUrl + SEARCH_PATH), HttpMethod.POST);
            webRequest.setRequestParameters(HttpUtils.generateRequestParameters("wd", URLEncoder.encode(keyWord, "UTF-8")));
            Document document = HttpUtils.getInstance().getHtmlPageResponseAsDocument(webRequest);
            Element searchBox = document.getElementsByClass("conBox search-box").first();
            Elements lis = searchBox.getElementsByTag("li");
            VideoListItem item;
            Element a, img, plot;
            for (Element li : lis) {
                item = new VideoListItem();
                item.setSiteKey(SITE_KEY);
                item.setBaseUrl(baseUrl);
                a = li.selectFirst("a.play-pic");
                if (a != null) {
                    item.setDetailUrl(baseUrl + a.attr("href"));
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

    @Override
    public DetailData getPlaySrc(String baseUrl, String detailUrl) {
        try {
            Document document = HttpUtils.getInstance().getHtmlPageResponseAsDocument(detailUrl);
            DetailData detailData = new DetailData();
            detailData.setSiteKey(SITE_KEY);
            detailData.setBaseUrl(baseUrl);
            Element title = document.getElementsByClass("detail-title fn-right").first();
            detailData.setTitle(title.selectFirst("h2>strong").text());
            List<DetailData.ResourceGroup> resourceGroups = detailData.getResourceGroupList();
            Element detailList = document.getElementById("detail-list");
            Elements elements = detailList.getElementsByClass("play-list-box");
            for (Element element : elements) {
                DetailData.ResourceGroup resourceGroup = new DetailData.ResourceGroup();
                String type = element.selectFirst("div>h4>a").attr("name");
                resourceGroup.setName(element.selectFirst("div>h4>a>img").attr("alt"));
                Element playList = element.getElementsByClass("play-list").first();
                Elements as = playList.getElementsByTag("a");
                for (Element a : as) {
                    DetailData.Resource resource = new DetailData.Resource();
                    resource.setName(a.text());
                    resource.setPlayUrl(baseUrl + a.attr("href"));
                    DetailData.ExtraData extraData = new DetailData.ExtraData();
                    extraData.getMap().put("type", type);
                    resource.setExtraData(extraData);
                    resourceGroup.getResourceList().add(resource);
                }
                resourceGroups.add(resourceGroup);
            }
            return detailData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getVideoUrl(String baseUrl, String playUrl, String dataMap) {
        try {
            Document document = HttpUtils.getInstance().getHtmlPageResponseAsDocument(playUrl);
            Element element = document.selectFirst("script[language]");
            /*取得JS变量数组*/
            String[] data = element.data().toString().split("var");
            /*取得单个JS变量*/
            for (String variable : data) {
                /*过滤variable为空的数据*/
                if (variable.contains("=")) {
                    /*取到满足条件的JS变量*/
                    if (variable.contains("ff_urls")) {
                        String value = variable.split("=")[1];
                        int firstIndex = value.indexOf("'");
                        if (firstIndex != -1) {
                            value = value.substring(firstIndex + 1, value.length());
                        }
                        int lastIndex = value.indexOf("'");
                        if (lastIndex != -1) {
                            value = value.substring(0, lastIndex);
                        }
                        PlaySrc playSrc = GSONUtils.parseObject(value, PlaySrc.class);
//                        DetailData.ExtraData extraData = GSONUtils.parseObject(dataMap, DetailData.ExtraData.class);
//                        String type = extraData.getMap().get("type");
                        for (PlaySrc.Data d : playSrc.getData()) {
                            for (List<String> unit : d.getPlayurls()) {
                                if (playUrl.endsWith(unit.get(2))) {
                                    return "https://yun.qqplayerjx.com/m3u8/index.php?url=" + unit.get(1);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
