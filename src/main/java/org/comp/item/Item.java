package org.comp.item;

import java.util.HashMap;
import java.util.Objects;

public class Item {
    private final String title;
    private final String content;
    private HashMap<String,Integer> frequencyTable;
    private int numberOfTermsInItem;
    private double tf_idf;
    private final String url;

    public double getTf_idf() {
        return tf_idf;
    }

    public void setTf_idf(double tf_idf) {
        this.tf_idf = tf_idf;
    }

    public int getNumberOfTermsInItem() {
        return numberOfTermsInItem;
    }

    private static int _id = 1;
    private final int id;

    public String getUrl() {
        return url;
    }

    public int getId() {
        return id;
    }

    public HashMap<String, Integer> getFrequencyTable() {
        return frequencyTable;
    }

    public void setNumberOfTermsInItem(int numberOfTermsInItem) {
        this.numberOfTermsInItem = numberOfTermsInItem;
    }

    public void setFrequencyTable(HashMap<String, Integer> frequencyTable) {
        this.frequencyTable = frequencyTable;
    }

    public Item(String title, String content,String url) {
        this.tf_idf = -1;
        this.title = title;
        this.url = url;
        this.content = content;
        this.id = _id;
        _id++;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(getTitle(), item.getTitle()) && Objects.equals(getContent(), item.getContent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getContent());
    }

}
