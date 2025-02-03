package com.ruoyi.common.utils.crawler;

import cn.hutool.http.HttpUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * 爬虫工具类
 *
 * @author : sinvon
 * @since :  2025/2/4 上午2:16
 */
public class CrawlerUtil {

    /**
     * 爬取目标链接的图标、标题和描述信息
     *
     * @param url 目标链接，例如 "https://juejin.cn/user/2388605244547997/posts"
     * @return 返回一个包含 icon、title 和 description 的 Map 对象
     * @throws Exception 网络请求失败或解析异常时抛出
     */
    public static Map<String, String> urlCrawl(String url) throws Exception {
        // 使用 Hutool 获取页面 HTML（自动处理 User-Agent）
        String html = HttpUtil.get(url);

        // 解析 HTML
        Document doc = Jsoup.parse(html, url);

        // 获取页面 title
        String title = doc.title();

        // 获取 description（优先使用 meta[name="description"]）
        String description = "";
        Element metaDesc = doc.selectFirst("meta[name=description]");
        if (metaDesc != null) {
            description = metaDesc.attr("content").trim();
        }

        // 获取 favicon
        String icon = "";
        Element linkIcon = doc.selectFirst("link[rel=icon]");
        if (linkIcon == null) {
            linkIcon = doc.selectFirst("link[rel=shortcut icon]");
        }
        if (linkIcon != null) {
            icon = linkIcon.attr("href").trim();
            // 处理相对路径
            if (!icon.startsWith("http")) {
                URL base = new URL(url);
                icon = new URL(base, icon).toString();
            }
        } else {
            // 默认 favicon 规则
            URL base = new URL(url);
            icon = base.getProtocol() + "://" + base.getHost() + "/favicon.ico";
        }

        // 封装结果
        Map<String, String> result = new HashMap<>();
        result.put("icon", icon);
        result.put("title", title);
        result.put("description", description);
        return result;
    }
}
