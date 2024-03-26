package org.comp;

import items.Item;
import org.comp.scrapper.Scrapper;
import org.comp.tf_idf.TF_IDF;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scrapper scrapper = Scrapper.getSingleton();
        List<String> URLS = scrapper.crawlURL("https://books.toscrape.com/");
        List<Item> items = scrapper.scrapeURLS(URLS);


        TF_IDF.createFrequencyTable(items);  // creates frequency table
        TF_IDF.calculateTF_IDF(items,"a");  // calculates the TF_IDF for the term passed

        items.sort((item1, item2) -> Double.compare(item2.getTf_idf(), item1.getTf_idf())); // ordered by tf_idf value --> DESC

        for ( Item item : items) {
            System.out.printf(" url = %s,   tf_idf = %f \n",item.getUrl(), item.getTf_idf() );
        }
    }
}