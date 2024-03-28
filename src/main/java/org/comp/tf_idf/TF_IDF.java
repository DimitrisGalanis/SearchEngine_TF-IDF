package org.comp.tf_idf;

import org.comp.item.Item;

import java.util.HashMap;
import java.util.List;


/**
 *
 *  TF_DF(t,d,D) = tf(t,d) * idf (t,D)
 *  tf(t,d) = n/N         is the term frequency, where n {@number of times term appear in document d} and N {@total_number of terms in document d}
 *  idf(t,D) = log(N/n)   is the inverse document frequency, where N {@number of documentsin data set} and n{@number of documents that contain term in data set}
 *
 */

public class TF_IDF {

    public TF_IDF() {}

    /**
     * Iterates through the items passed as arguments
     * Split the content of each item
     * (1)--  Sets the numberOfTermsInItem
     * Iterates through the content and
     * (2)-- Sets the frequencyTable
     * @param items
     */
    public void createFrequencyTable(List<Item> items) {

        for (Item item : items) {
            HashMap<String, Integer> frequencyTable = new HashMap<>();

            String[] words = item.getContent().split("\\s+");
            item.setNumberOfTermsInItem(words.length);

            for (String word : words) {
                String lowercaseWord = word.strip().toLowerCase();
                frequencyTable.put(lowercaseWord, frequencyTable.getOrDefault(lowercaseWord, 0) + 1);
            }

            item.setFrequencyTable(frequencyTable);
        }
    }

    /**
     * Iterates through the items and IF contains the term sets the TF_IDF
     * @param items
     * @param term
     */
    public  void calculateTF_IDF(List<Item> items , String term) {
        double idf = calculate_IDF(items,term);


        for (Item item : items) {
            if ( item.getFrequencyTable().containsKey(term) ) {
                double tf = 1.0 * item.getFrequencyTable().get(term) / item.getNumberOfTermsInItem();
                item.setTf_idf( tf * idf);
            }
        }
    }


    /**
     * N -> Number of Documents = items.size();
     * n -> number of documents that contain the term = numberOfDocContainsTerm
     * @param items
     * @param term
     * @return IDF = Log (N / n)
     */
    private static double calculate_IDF(List<Item> items, String term) {
        int numberOfDocuments = items.size();
        int numberOfDocsContainsTerm = 0 ;


        for (Item item : items) {
            if ( item.getFrequencyTable().containsKey(term)) {
                numberOfDocsContainsTerm++;
            }
        }


        return numberOfDocsContainsTerm !=0 ?  Math.log(1.0*numberOfDocuments / numberOfDocsContainsTerm) : 0;
    }
}


