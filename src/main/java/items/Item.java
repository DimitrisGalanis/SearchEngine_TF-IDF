package items;

import java.util.HashMap;
import java.util.Objects;

public class Item {
    private final String name;
    private final String content;
    private HashMap<String,Integer> frequencyTable;
    private int numberOfTermsInItem;
    private double tf_idf;

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

    public Item(String name, String content) {
        this.tf_idf = -1;
        this.name = name;
        this.content = content;
        this.id = _id;
        _id++;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(getName(), item.getName()) && Objects.equals(getContent(), item.getContent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getContent());
    }

}
