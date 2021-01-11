package top.chao58.blog.util;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class CommonUtils {
    public static String analyseHtmlByImg(String content) {
        ArrayList<String> list = new ArrayList<>();
        Elements elements = Jsoup.parse(content).select("img[src]");
        elements.forEach(element -> {
            list.add(element.attr("src"));
        });
        return list.toString();
    }

}
