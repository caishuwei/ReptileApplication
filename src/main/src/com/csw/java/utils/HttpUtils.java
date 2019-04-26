package com.csw.java.utils;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.NameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HttpUtils {
    /**
     * 请求超时时间,默认20000ms
     */
    private int timeout = 20000;
    /**
     * 等待异步JS执行时间,默认20000ms
     */
    private int waitForBackgroundJavaScript = 20000;

    private static HttpUtils httpUtils;

    private HttpUtils() {
    }

    /**
     * 获取实例
     *
     * @return
     */
    public static HttpUtils getInstance() {
        if (httpUtils == null)
            httpUtils = new HttpUtils();
        return httpUtils;
    }

    public int getTimeout() {
        return timeout;
    }

    /**
     * 设置请求超时时间
     *
     * @param timeout
     */
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getWaitForBackgroundJavaScript() {
        return waitForBackgroundJavaScript;
    }

    /**
     * 设置获取完整HTML页面时等待异步JS执行的时间
     *
     * @param waitForBackgroundJavaScript
     */
    public void setWaitForBackgroundJavaScript(int waitForBackgroundJavaScript) {
        this.waitForBackgroundJavaScript = waitForBackgroundJavaScript;
    }

    /**
     * 将网页返回为解析后的文档格式
     *
     * @param html
     * @return
     * @throws Exception
     */
    public static Document parseHtmlToDoc(String html) throws Exception {
        return removeHtmlSpace(html);
    }

    private static Document removeHtmlSpace(String str) {
        Document doc = Jsoup.parse(str);
        String result = doc.html().replace("&nbsp;", "");
        return Jsoup.parse(result);
    }

    /**
     * 获取页面文档字串(等待异步JS执行)
     *
     * @param url 页面URL
     * @return
     * @throws Exception
     */
    public String getHtmlPageResponse(String url) throws Exception {
        return getHtmlPageResponse(new WebRequest(new URL(url)));
    }

    public String getHtmlPageResponse(WebRequest webRequest) throws Exception {
        String result = "";
        final WebClient webClient = new WebClientBuilder().build();
        HtmlPage page;
        try {
            page = webClient.getPage(webRequest);
        } catch (Exception e) {
            webClient.close();
            throw e;
        }
        webClient.waitForBackgroundJavaScript(waitForBackgroundJavaScript);//该方法阻塞线程
        result = page.asXml();
        webClient.close();
        return result;
    }

    /**
     * 获取页面文档Document对象(等待异步JS执行)
     *
     * @param url 页面URL
     * @return
     * @throws Exception
     */
    public Document getHtmlPageResponseAsDocument(String url) throws Exception {
        return parseHtmlToDoc(getHtmlPageResponse(url));
    }


    public Document getHtmlPageResponseAsDocument(WebRequest webRequest) throws Exception {
        return parseHtmlToDoc(getHtmlPageResponse(webRequest));
    }

    /**
     * 生成请求参数
     * @param nvnvnv {name1,value1,name2,value2......}
     */
    public static List<NameValuePair> generateRequestParameters(String... nvnvnv) {
        List<NameValuePair> requestParameters = new ArrayList<>();
        for (int i = 0; i + 1 < nvnvnv.length; i += 2) {
            requestParameters.add(new NameValuePair(nvnvnv[i], nvnvnv[i + 1]));
        }
        return requestParameters;
    }

    private class WebClientBuilder {
        private boolean throwExceptionOnScriptError = false;//当JS执行出错的时候是否抛出异常
        private boolean throwExceptionOnFailingStatusCode = false;//当HTTP的状态非200时是否抛出异常
        private boolean activeXNative = false;
        private boolean cssEnabled = false;
        private boolean javaScriptEnabled = false;//启用js
        private boolean ajaxControllerEnable = true;//支持AJAX
        private int connectTimeOut = timeout;//设置“浏览器”的请求超时时间
        private int javaScriptTimeout = timeout;//设置JS执行的超时时间

        public WebClientBuilder setThrowExceptionOnScriptError(boolean throwExceptionOnScriptError) {
            this.throwExceptionOnScriptError = throwExceptionOnScriptError;
            return this;
        }

        public WebClientBuilder setThrowExceptionOnFailingStatusCode(boolean throwExceptionOnFailingStatusCode) {
            this.throwExceptionOnFailingStatusCode = throwExceptionOnFailingStatusCode;
            return this;
        }

        public WebClientBuilder setActiveXNative(boolean activeXNative) {
            this.activeXNative = activeXNative;
            return this;
        }

        public WebClientBuilder setCssEnabled(boolean cssEnabled) {
            this.cssEnabled = cssEnabled;
            return this;
        }

        public WebClientBuilder setJavaScriptEnabled(boolean javaScriptEnabled) {
            this.javaScriptEnabled = javaScriptEnabled;
            return this;
        }

        public WebClientBuilder setAjaxControllerEnable(boolean ajaxControllerEnable) {
            this.ajaxControllerEnable = ajaxControllerEnable;
            return this;
        }

        public WebClientBuilder setConnectTimeOut(int connectTimeOut) {
            this.connectTimeOut = connectTimeOut;
            return this;
        }

        public WebClientBuilder setJavaScriptTimeout(int javaScriptTimeout) {
            this.javaScriptTimeout = javaScriptTimeout;
            return this;
        }

        public WebClient build() {
            final WebClient webClient = new WebClient(BrowserVersion.CHROME);
            webClient.getOptions().setThrowExceptionOnScriptError(throwExceptionOnScriptError);
            webClient.getOptions().setThrowExceptionOnFailingStatusCode(throwExceptionOnFailingStatusCode);
            webClient.getOptions().setActiveXNative(activeXNative);
            webClient.getOptions().setCssEnabled(cssEnabled);
            webClient.getOptions().setJavaScriptEnabled(javaScriptEnabled);
            if (ajaxControllerEnable) {
                webClient.setAjaxController(new NicelyResynchronizingAjaxController());
            }
            webClient.getOptions().setTimeout(connectTimeOut);
            webClient.setJavaScriptTimeout(javaScriptTimeout);
            return webClient;
        }
    }


}
