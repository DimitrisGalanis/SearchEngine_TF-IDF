package org.comp.scrapper;


import org.comp.item.Item;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Scrapper {
        private static Scrapper singleton;
        private Document document;
        private Scrapper() {}


    public static Scrapper getSingleton() {
            if ( singleton == null )
                return singleton = new Scrapper();
            else
                return singleton;
    }


    /**
     * Crawls all URLS the user provided as argument in the method ( String url )
     * @param url ---> String
     * @return a List with urls
     */
    public List<String> crawlURL(String url) {
        List<String> URLS = new ArrayList<>();


        try {
            document = Jsoup.connect(url).get();
            Elements newsHeadlines = document.select("div.image_container > a[href]");

            for (Element headline : newsHeadlines) {
                headline.select("a[href]").first();
                headline.select("*").remove();

                String URL = url + "catalogue/" + headline.toString().replace("<a href=\"catalogue/","").replace("\"></a>","");
                URLS.add(URL);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return URLS;
    }

    /**
     * Scrapes the title and the content of the page
     * @param URLS ---> List<String>
     * @return List<Item>
     */
    public List<Item> scrapeURLS(List<String> URLS) {
        List<Item> items = new ArrayList<>();

        for (String url : URLS) {
            try {
            document = Jsoup.connect(url).get();
            Element title = document.select("h1").first();
            Element description = document.select("p").get(3);
            String str_title = title!=null?title.toString().replace("<h1>","").replace("</h1>",""):"";
            String str_description =description!=null? description.toString().replace("<p>","").replace("</p>",""):"";
            Item item = new Item(str_title,str_description,url);
            items.add(item);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }



        return items;
    }
}
